package tn.esprit.autoloc.service;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tn.esprit.autoloc.domain.Agence;
import tn.esprit.autoloc.domain.Equipement;
import tn.esprit.autoloc.domain.Vehicule;
import tn.esprit.autoloc.dto.IMapper;
import tn.esprit.autoloc.dto.VehiculeDTO;
import tn.esprit.autoloc.exception.ResourceNotFoundException;
import tn.esprit.autoloc.repository.IAgenceRepository;
import tn.esprit.autoloc.repository.IEquipementRepository;
import tn.esprit.autoloc.repository.IVehiculeRepository;

@Service
@RequiredArgsConstructor
public class VehiculeServiceImpl implements IVehiculeService {

    private final IVehiculeRepository vehiculeRepository;
    private final IAgenceRepository agenceRepository;
    private final IEquipementRepository equipementRepository;
    private final IMapper mapper;

    @Override
    public VehiculeDTO create(VehiculeDTO vehiculeDTO) {
        Vehicule vehicule = mapper.toEntity(vehiculeDTO);
        if (vehicule.getIdVehicule() != null) {
            throw new IllegalArgumentException(
                    "Un nouveau véhicule ne doit pas avoir d'identifiant");
        }

        return mapper.toDTO(vehiculeRepository.save(vehicule));
    }

    @Override
    public VehiculeDTO findById(Long id) {
        Vehicule vehicule = vehiculeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Vehicule", id));
        return mapper.toDTO(vehicule);
    }

    @Override
    public List<VehiculeDTO> findAll() {
        return mapper.vToDTOList(vehiculeRepository.findAll());
    }

    @Override
    public VehiculeDTO update(Long id, Vehicule vehicule) {
        Vehicule existant = mapper.toEntity(findById(id));
        existant.setImmatriculation(vehicule.getImmatriculation());
        existant.setMarque(vehicule.getMarque());
        existant.setModele(vehicule.getModele());
        existant.setCategorie(vehicule.getCategorie());
        existant.setTarifJournalier(vehicule.getTarifJournalier());
        existant.setStatut(vehicule.getStatut());
        existant.setAgence(vehicule.getAgence());
        existant.setEquipements(vehicule.getEquipements());
        return mapper.toDTO(vehiculeRepository.save(existant));
    }

    @Override
    public void deleteById(Long id) {
        if (!vehiculeRepository.existsById(id)) {
            throw new ResourceNotFoundException("Vehicule", id);
        }
        vehiculeRepository.deleteById(id);
    }

    @Override
    @Transactional
    public VehiculeDTO affecterVehiculeAAgence(Long idVehicule, Long idAgence) {
        Vehicule vehicule = mapper.toEntity(findById(idVehicule));
        Agence agence = agenceRepository.findById(idAgence)
                .orElseThrow(() -> new ResourceNotFoundException("Agence", idAgence));
        vehicule.setAgence(agence);
        vehicule.getAgence().setVehicules(null);
        return mapper.toDTO(vehiculeRepository.save(vehicule));
    }

    @Override
    @Transactional
    public VehiculeDTO affecterVehiculeAEquipement(Long idVehicule, Long idEquipement) {
        Vehicule vehicule = mapper.toEntity(findById(idVehicule));
        Equipement equipement = equipementRepository.findById(idEquipement)
                .orElseThrow(() -> new ResourceNotFoundException("Equipement", idEquipement));

        vehicule.getEquipements().add(equipement);

        return mapper.toDTO(vehiculeRepository.save(vehicule));
    }

    @Override
    public List<VehiculeDTO> findByContrat(long id) {return mapper.vToDTOList(vehiculeRepository.findByReservationsContratIdContrat(id));}

    @Override
    public List<VehiculeDTO> findByMaintenance(long id) {return mapper.vToDTOList(vehiculeRepository.findByMaintenanceIdMaintenance(id));}
}

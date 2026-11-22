package tn.esprit.autoloc.service;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tn.esprit.autoloc.domain.Maintenance;
import tn.esprit.autoloc.domain.Vehicule;
import tn.esprit.autoloc.dto.IMapper;
import tn.esprit.autoloc.dto.MaintenanceDTO;
import tn.esprit.autoloc.exception.ResourceNotFoundException;
import tn.esprit.autoloc.repository.IMaintenanceRepository;
import tn.esprit.autoloc.repository.IVehiculeRepository;

@Service
@RequiredArgsConstructor
public class MaintenanceServiceImpl implements IMaintenanceService {

    private final IMaintenanceRepository maintenanceRepository;
    private final IVehiculeRepository vehiculeRepository;
    private final IMapper mapper;

    @Override
    public MaintenanceDTO create(MaintenanceDTO maintenance) {
        Maintenance m = mapper.toEntity(maintenance);
        if (m.getIdMaintenance() != null) {
            throw new IllegalArgumentException(
                    "Une nouvelle maintenance ne doit pas avoir d'identifiant");
        }

        return mapper.toDTO(maintenanceRepository.save(m));
    }

    @Override
    public MaintenanceDTO findById(Long id) {
        return mapper.toDTO(maintenanceRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Maintenance", id)));
    }

    @Override
    public List<MaintenanceDTO> findAll() {
        return mapper.mToDTOList(maintenanceRepository.findAll());
    }

    @Override
    public MaintenanceDTO update(Long id, Maintenance maintenance) {
        Maintenance existant = mapper.toEntity(findById(id));
        existant.setDateDebut(maintenance.getDateDebut());
        existant.setDateFin(maintenance.getDateFin());
        existant.setDescription(maintenance.getDescription());
        existant.setVehicule(maintenance.getVehicule());
        return mapper.toDTO(maintenanceRepository.save(existant));
    }

    @Override
    public void deleteById(Long id) {
        if (!maintenanceRepository.existsById(id)) {
            throw new ResourceNotFoundException("Maintenance", id);
        }
        maintenanceRepository.deleteById(id);
    }

    @Override
    @Transactional
    public MaintenanceDTO affecterMaintenanceAVehicule(Long idMaintenance, Long idVehicule) {
        Maintenance maintenance = mapper.toEntity(findById(idMaintenance));
        Vehicule vehicule = vehiculeRepository.findById(idVehicule)
                .orElseThrow(() -> new ResourceNotFoundException("Vehicule", idVehicule));
        maintenance.setVehicule(vehicule);
        return mapper.toDTO(maintenanceRepository.save(maintenance));
    }

    @Override
    @Transactional
    public MaintenanceDTO ajouterEtAffecterMaintenanceAVehicule(Maintenance maintenance, Long idVehicule) {
        Vehicule vehicule = vehiculeRepository.findById(idVehicule)
                .orElseThrow(() -> new ResourceNotFoundException("Vehicule", idVehicule));
        maintenance.setVehicule(vehicule);
        return mapper.toDTO(maintenanceRepository.save(maintenance));
    }

    @Override
    public List<MaintenanceDTO> findByVehicule(long id) {
        return mapper.mToDTOList(maintenanceRepository.findByVehiculeIdVehicule(id));
    }
}

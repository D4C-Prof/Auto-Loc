package tn.esprit.autoloc.service;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.autoloc.domain.Contrat;
import tn.esprit.autoloc.dto.ContratDTO;
import tn.esprit.autoloc.dto.IMapper;
import tn.esprit.autoloc.exception.ResourceNotFoundException;
import tn.esprit.autoloc.repository.IContratRepository;
@Service
@RequiredArgsConstructor
public class ContratServiceImpl implements IContratService {
    private final IContratRepository contratRepository;
    private final IMapper mapper;

    @Override
    public ContratDTO create(ContratDTO contrat) {
        if (contrat.getIdContrat() != null) {
            throw new IllegalArgumentException(
                    "Un nouveau contrat ne doit pas avoir d'identifiant");
        }
        return mapper.toDTO(contratRepository.save(mapper.toEntity(contrat)));
    }
    @Override
    public ContratDTO findById(Long id) {
        return mapper.toDTO(contratRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Contrat", id)));
    }
    @Override
    public List<ContratDTO> findAll() {
        return mapper.conToDTOList(contratRepository.findAll());
    }
    @Override
    public ContratDTO update(Long id, Contrat contrat) {
        Contrat existant = mapper.toEntity(findById(id));
        existant.setDateSignature(contrat.getDateSignature());
        existant.setMontantTotal(contrat.getMontantTotal());
        existant.setValide(contrat.getValide());
        existant.setReservation(contrat.getReservation());
        return mapper.toDTO(contratRepository.save(existant));
    }
    @Override
    public void deleteById(Long id) {
        if (!contratRepository.existsById(id)) {
            throw new ResourceNotFoundException("Contrat", id);
        }
        contratRepository.deleteById(id);
    }

    @Override
    public List<ContratDTO> findByVehicule(long id) {
        return mapper.conToDTOList(contratRepository.findByReservationVehiculeIdVehicule(id));
    }
}
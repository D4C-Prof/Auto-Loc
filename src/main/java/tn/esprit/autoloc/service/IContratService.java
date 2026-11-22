package tn.esprit.autoloc.service;


import java.util.List;
import tn.esprit.autoloc.domain.Contrat;
import tn.esprit.autoloc.dto.ContratDTO;


public interface IContratService {

    ContratDTO create(ContratDTO contrat);
    ContratDTO findById(Long id);
    List<ContratDTO> findAll();
    ContratDTO update(Long id, Contrat contrat);
    void deleteById(Long id);
    List<ContratDTO> findByVehicule(long id);
}
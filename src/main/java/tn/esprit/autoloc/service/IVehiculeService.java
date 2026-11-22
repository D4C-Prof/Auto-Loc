package tn.esprit.autoloc.service;

import java.util.List;
import tn.esprit.autoloc.domain.Vehicule;
import tn.esprit.autoloc.dto.VehiculeDTO;

public interface IVehiculeService {

    VehiculeDTO create(VehiculeDTO vehicule);
    VehiculeDTO findById(Long id);
    List<VehiculeDTO> findAll();
    VehiculeDTO update(Long id, Vehicule vehicule);
    void deleteById(Long id);
    VehiculeDTO affecterVehiculeAAgence(Long idVehicule, Long idAgence);
    VehiculeDTO affecterVehiculeAEquipement(Long idVehicule, Long idEquipement);
    List<VehiculeDTO> findByContrat(long id);
    List<VehiculeDTO> findByMaintenance(long id);
}
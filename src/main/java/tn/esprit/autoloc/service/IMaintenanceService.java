package tn.esprit.autoloc.service;

import java.util.List;
import tn.esprit.autoloc.domain.Maintenance;
import tn.esprit.autoloc.dto.MaintenanceDTO;

public interface IMaintenanceService {

    MaintenanceDTO create(MaintenanceDTO maintenance);
    MaintenanceDTO findById(Long id);
    List<MaintenanceDTO> findAll();
    MaintenanceDTO update(Long id, Maintenance maintenance);
    void deleteById(Long id);
    MaintenanceDTO affecterMaintenanceAVehicule(Long idMaintenance, Long idVehicule);
    MaintenanceDTO ajouterEtAffecterMaintenanceAVehicule(Maintenance maintenance, Long idVehicule);
    List<MaintenanceDTO> findByVehicule(long id);
}

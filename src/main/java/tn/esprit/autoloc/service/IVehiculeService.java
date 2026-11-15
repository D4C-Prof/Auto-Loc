package tn.esprit.autoloc.service;

import java.util.List;
import tn.esprit.autoloc.domain.Vehicule;

public interface IVehiculeService {

    Vehicule create(Vehicule vehicule);
    Vehicule findById(Long id);
    List<Vehicule> findAll();
    Vehicule update(Long id, Vehicule vehicule);
    void deleteById(Long id);
    Vehicule affecterVehiculeAAgence(Long idVehicule, Long idAgence);
    Vehicule affecterVehiculeAEquipement(Long idVehicule, Long idEquipement);
}
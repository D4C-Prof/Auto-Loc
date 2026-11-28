package tn.esprit.autoloc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import tn.esprit.autoloc.domain.Maintenance;

import java.util.List;

public interface IMaintenanceRepository extends JpaRepository<Maintenance, Long> {
    List<Maintenance> findByVehiculeIdVehicule(long idVehicule);

    @Query(value = "select m from Maintenance m " +
            "where m.vehicule.idVehicule= :idVehicule")
    List<Maintenance> findByIdVehicule(@Param("idVehicule") long idVehicule);
}

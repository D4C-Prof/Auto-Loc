package tn.esprit.autoloc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import tn.esprit.autoloc.domain.Vehicule;

import java.util.List;

public interface IVehiculeRepository extends JpaRepository<Vehicule, Long> {
    List<Vehicule> findByReservationsContratIdContrat(long idContrat);

    @Query("select m.vehicule from Maintenance m where m.idMaintenance = :idMaintenance")
    List<Vehicule> findByMaintenanceId(@Param("idMaintenance") long id);

    @Query(value = "select v.* from Vehicule v " +
            "join Maintenance m on v.id_vehicule=m.vehicule_id_vehicule where m.id_maintenance = :idMaintenance",
            nativeQuery = true)
    List<Vehicule> findByMaintenanceIdNative(@Param("idMaintenance") long id);
}

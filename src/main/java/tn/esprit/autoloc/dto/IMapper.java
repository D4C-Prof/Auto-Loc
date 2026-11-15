package tn.esprit.autoloc.dto;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import tn.esprit.autoloc.domain.*;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IMapper {
    // Vehicule
    VehiculeDTO VtoDTO(Vehicule vehicule);
    Vehicule VtoEntity(VehiculeDTO vehiculeDTO);
    List<VehiculeDTO> VtoDTOList(List<Vehicule> vehicules);

    // Agence
    AgenceDTO AtoDTO(Agence agence);
    Agence AtoEntity(AgenceDTO agenceDTO);
    List<AgenceDTO> AtoDTOList(List<Agence> agences);

    // Equipement
    EquipementDTO EqtoDTO(Equipement equipement);
    Equipement EqtoEntity(EquipementDTO equipementDTO);
    List<EquipementDTO> EqtoDTOList(List<Equipement> equipements);

    // Maintenance
    MaintenanceDTO MtoDTO(Maintenance maintenance);
    Maintenance MtoEntity(MaintenanceDTO maintenanceDTO);
    List<MaintenanceDTO> MtoDTOList(List<Maintenance> maintenances);

    // Client
    ClientDTO CtoDTO(Client client);
    Client CtoEntity(ClientDTO clientDTO);
    List<ClientDTO> CtoDTOList(List<Client> clients);

    // Contrat
    ContratDTO ContratToDTO(Contrat contrat);
    Contrat ContratToEntity(ContratDTO contratDTO);
    List<ContratDTO> ContratToDTOList(List<Contrat> contrats);

    // Employe
    EmployeDTO EtoDTO(Employe employe);
    Employe EtoEntity(EmployeDTO employeDTO);
    List<EmployeDTO> EtoDTOList(List<Employe> employes);

    // Paiement
    PaiementDTO PtoDTO(Paiement paiement);
    Paiement PtoEntity(PaiementDTO paiementDTO);
    List<PaiementDTO> PtoDTOList(List<Paiement> paiements);

    // Reservation
    ReservationDTO RtoDTO(Reservation reservation);
    Reservation RtoEntity(ReservationDTO reservationDTO);
    List<ReservationDTO> RtoDTOList(List<Reservation> reservations);
}

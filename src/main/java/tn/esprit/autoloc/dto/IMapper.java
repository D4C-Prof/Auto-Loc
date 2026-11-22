package tn.esprit.autoloc.dto;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import tn.esprit.autoloc.domain.*;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IMapper {
    // Vehicule
    VehiculeDTO toDTO(Vehicule vehicule);
    Vehicule toEntity(VehiculeDTO vehiculeDTO);
    List<VehiculeDTO> vToDTOList(List<Vehicule> vehicules);

    // Agence
    AgenceDTO toDTO(Agence agence);
    Agence toEntity(AgenceDTO agenceDTO);
    List<AgenceDTO> aToDTOList(List<Agence> agences);

    // Equipement
    EquipementDTO toDTO(Equipement equipement);
    Equipement toEntity(EquipementDTO equipementDTO);
    List<EquipementDTO> toDTOList(List<Equipement> equipements);

    // Maintenance
    MaintenanceDTO toDTO(Maintenance maintenance);
    Maintenance toEntity(MaintenanceDTO maintenanceDTO);
    List<MaintenanceDTO> mToDTOList(List<Maintenance> maintenances);

    // Client
    ClientDTO toDTO(Client client);
    Client toEntity(ClientDTO clientDTO);
    List<ClientDTO> cToDTOList(List<Client> clients);

    // Contrat
    ContratDTO toDTO(Contrat contrat);
    Contrat toEntity(ContratDTO contratDTO);
    List<ContratDTO> conToDTOList(List<Contrat> contrats);

    // Employe
    EmployeDTO toDTO(Employe employe);
    Employe toEntity(EmployeDTO employeDTO);
    List<EmployeDTO> eToDTOList(List<Employe> employes);

    // Paiement
    PaiementDTO toDTO(Paiement paiement);
    Paiement toEntity(PaiementDTO paiementDTO);
    List<PaiementDTO> pToDTOList(List<Paiement> paiements);

    // Reservation
    ReservationDTO toDTO(Reservation reservation);
    Reservation toEntity(ReservationDTO reservationDTO);
    List<ReservationDTO> rToDTOList(List<Reservation> reservations);
}

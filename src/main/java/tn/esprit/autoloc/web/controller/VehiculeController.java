package tn.esprit.autoloc.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.domain.Vehicule;
import tn.esprit.autoloc.dto.IMapper;
import tn.esprit.autoloc.dto.VehiculeDTO;
import tn.esprit.autoloc.service.IVehiculeService;

import java.util.List;


@Tag(name = "Véhicules", description = "Gestion du parc de véhicules")
@RestController
@RequestMapping("/vehicules")
@RequiredArgsConstructor
public class VehiculeController {
    private final IVehiculeService vehiculeService;
    private final IMapper mapper;

    @Operation(summary = "Liste de tous les véhicules")
    @GetMapping
    public List<VehiculeDTO> findAll() {
        return vehiculeService.findAll();
    }

    @Operation(summary = "Consulter un véhicule")
    @ApiResponse(responseCode = "200", description = "Véhicule trouvé")
    @ApiResponse(responseCode = "404", description = "Véhicule introuvable")
    @GetMapping("/{id}")
    public VehiculeDTO findById(@PathVariable Long id) {
        return vehiculeService.findById(id);
    }

    @Operation(summary = "Créer un véhicule")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public VehiculeDTO create(@Valid @RequestBody VehiculeDTO vehiculeDTO) {
        return vehiculeService.create(vehiculeDTO);
    }

    @Operation(summary = "Mettre à jour un véhicule")
    @PutMapping("/{id}")
    public VehiculeDTO update(@PathVariable Long id, @Valid @RequestBody VehiculeDTO vehiculeDTO) {
        Vehicule vehicule = mapper.toEntity(vehiculeDTO);
        return vehiculeService.update(id, vehicule);
    }

    @Operation(summary = "Supprimer un véhicule")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Long id) {
        vehiculeService.deleteById(id);
    }

    @Operation(summary = "Affecter un véhicule à une agence")
    @ApiResponse(responseCode = "200", description = "Véhicule affecté à l'agence avec succès")
    @ApiResponse(responseCode = "404", description = "Véhicule ou agence introuvable")
    @PutMapping(value = {"/{idVehicule}/agence/{idAgence}", "/{idVehicule}/affecter-agence/{idAgence}"})
    public VehiculeDTO affecterVehiculeAAgence(
            @PathVariable Long idVehicule,
            @PathVariable Long idAgence) {
        return vehiculeService.affecterVehiculeAAgence(idVehicule, idAgence);
    }

    @Operation(summary = "Affecter un véhicule à un équipement")
    @ApiResponse(responseCode = "200", description = "Véhicule affecté à l'équipement avec succès")
    @ApiResponse(responseCode = "404", description = "Véhicule ou équipement introuvable")
    @PutMapping(value = {"/{idVehicule}/equipement/{idEquipement}", "/{idVehicule}/equipements/{idEquipement}", "/{idVehicule}/affecter-equipement/{idEquipement}"})
    public VehiculeDTO affecterVehiculeAEquipement(
            @PathVariable Long idVehicule,
            @PathVariable Long idEquipement) {
        return vehiculeService.affecterVehiculeAEquipement(idVehicule, idEquipement);

    }

    @Operation(summary = "Trouver voitures par agence")
    @ApiResponse(responseCode = "200", description = "Liste des voitures trouves")
    @ApiResponse(responseCode = "404", description = "Liste des voitures non trouves")
    @GetMapping("/byAgence/{id}")
    public List<VehiculeDTO> afficherParContrat(@PathVariable long id){
        return vehiculeService.findByContrat(id);
    }

    @Operation(summary = "Trouver voitures par maintenance")
    @ApiResponse(responseCode = "200", description = "Liste des voitures trouves")
    @ApiResponse(responseCode = "404", description = "Liste des voitures non trouves")
    @GetMapping("/byMaintenance/{id}")
    public List<VehiculeDTO> afficherParMaintenance(@PathVariable long id){
        return vehiculeService.findByMaintenance(id);
    }
}

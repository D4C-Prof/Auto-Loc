package tn.esprit.autoloc.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.domain.Maintenance;
import tn.esprit.autoloc.dto.IMapper;
import tn.esprit.autoloc.dto.MaintenanceDTO;
import tn.esprit.autoloc.service.IMaintenanceService;

import java.util.List;

@Tag(name = "Maintenances", description = "Gestion des maintenances de véhicules")
@RestController
@RequestMapping("/maintenances")
@RequiredArgsConstructor
public class MaintenanceController {
    private final IMaintenanceService maintenanceService;
    private final IMapper mapper;

    @Operation(summary = "Liste de toutes les maintenances")
    @GetMapping
    public List<MaintenanceDTO> findAll() {
        return mapper.MtoDTOList(maintenanceService.findAll());
    }

    @Operation(summary = "Consulter une maintenance")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Maintenance trouvée"),
            @ApiResponse(responseCode = "404", description = "Maintenance introuvable")
    })
    @GetMapping("/{id}")
    public MaintenanceDTO findById(@PathVariable Long id) {
        return mapper.MtoDTO(maintenanceService.findById(id));
    }

    @Operation(summary = "Créer une maintenance")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MaintenanceDTO create(@Valid @RequestBody MaintenanceDTO maintenanceDTO) {
        Maintenance maintenance = mapper.MtoEntity(maintenanceDTO);
        return mapper.MtoDTO(maintenanceService.create(maintenance));
    }

    @Operation(summary = "Mettre à jour une maintenance")
    @PutMapping("/{id}")
    public MaintenanceDTO update(@PathVariable Long id, @Valid @RequestBody MaintenanceDTO maintenanceDTO) {
        Maintenance maintenance = mapper.MtoEntity(maintenanceDTO);
        return mapper.MtoDTO(maintenanceService.update(id, maintenance));
    }

    @Operation(summary = "Supprimer une maintenance")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Long id) {
        maintenanceService.deleteById(id);
    }

    @Operation(summary = "Affecter une maintenance à un véhicule")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Maintenance affectée au véhicule avec succès"),
            @ApiResponse(responseCode = "404", description = "Maintenance ou véhicule introuvable")
    })
    @PutMapping(value = {"/{idMaintenance}/vehicule/{idVehicule}", "/{idMaintenance}/vehicules/{idVehicule}", "/{idMaintenance}/affecter-vehicule/{idVehicule}"})
    public MaintenanceDTO affecterMaintenanceAVehicule(
            @PathVariable Long idMaintenance,
            @PathVariable Long idVehicule) {
        Maintenance maintenance = maintenanceService.affecterMaintenanceAVehicule(idMaintenance, idVehicule);
        return mapper.MtoDTO(maintenance);
    }

    @Operation(summary = "Ajouter et affecter une maintenance à un véhicule")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Maintenance créée et affectée avec succès"),
            @ApiResponse(responseCode = "404", description = "Véhicule introuvable")
    })
    @PostMapping(value = {"/vehicule/{idVehicule}", "/affecter/{idVehicule}"})
    @ResponseStatus(HttpStatus.CREATED)
    public MaintenanceDTO ajouterEtAffecterMaintenanceAVehicule(
            @PathVariable Long idVehicule,
            @Valid @RequestBody MaintenanceDTO maintenanceDTO) {
        Maintenance maintenance = mapper.MtoEntity(maintenanceDTO);
        Maintenance saved = maintenanceService.ajouterEtAffecterMaintenanceAVehicule(maintenance, idVehicule);
        return mapper.MtoDTO(saved);
    }
}

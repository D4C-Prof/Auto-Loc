package tn.esprit.autoloc.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.domain.Contrat;
import tn.esprit.autoloc.dto.ContratDTO;
import tn.esprit.autoloc.dto.IMapper;
import tn.esprit.autoloc.service.IContratService;

import java.util.List;

@Tag(name = "Contrats", description = "Gestion des contrats de location")
@RestController
@RequestMapping("/contrats")
@RequiredArgsConstructor
public class ContratController {
    private final IContratService contratService;
    private final IMapper mapper;

    @Operation(summary = "Liste de tous les contrats")
    @GetMapping
    public List<ContratDTO> findAll() {
        return contratService.findAll();
    }

    @Operation(summary = "Consulter un contrat")

    @ApiResponse(responseCode = "200", description = "Contrat trouvé")
    @ApiResponse(responseCode = "404", description = "Contrat introuvable")
    @ApiResponse(responseCode = "405", description = "Not allowed")

    @GetMapping("/{id}")
    public ContratDTO findById(@PathVariable Long id) {
        return contratService.findById(id);
    }

    @Operation(summary = "Créer un contrat")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ContratDTO create(@RequestBody ContratDTO contrat) {
        return contratService.create(contrat);
    }

    @Operation(summary = "Mettre à jour un contrat")
    @PutMapping("/{id}")
    public ContratDTO update(@PathVariable Long id, @RequestBody ContratDTO contrat) {
        return contratService.update(id, mapper.toEntity(contrat));
    }

    @Operation(summary = "Supprimer un contrat")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Long id) {
        contratService.deleteById(id);
    }

    @Operation(summary = "trouver contrats des vehicules")
    @ApiResponse(responseCode = "200", description = "Contrats trouves")
    @ApiResponse(responseCode = "404", description = "Contrats non trouves")
    @GetMapping("/byVehicule/{id}")
    public List<ContratDTO> afficherParVehicules(@PathVariable long id){
        return contratService.findByVehicule(id);
    }
}

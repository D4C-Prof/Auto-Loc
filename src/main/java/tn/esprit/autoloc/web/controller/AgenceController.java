package tn.esprit.autoloc.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.domain.Agence;
import tn.esprit.autoloc.dto.AgenceDTO;
import tn.esprit.autoloc.dto.IMapper;
import tn.esprit.autoloc.service.IAgenceService;

import java.util.List;

@Tag(name = "Agences", description = "Gestion des agences")
@RestController
@RequestMapping("/agences")
@RequiredArgsConstructor
public class AgenceController {
    private final IAgenceService agenceService;
    private final IMapper mapper;

    @Operation(summary = "Liste de toutes les agences")
    @GetMapping
    public List<AgenceDTO> findAll() {
        return mapper.AtoDTOList(agenceService.findAll());
    }

    @Operation(summary = "Consulter une agence")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Agence trouvée"),
            @ApiResponse(responseCode = "404", description = "Agence introuvable")
    })
    @GetMapping("/{id}")
    public AgenceDTO findById(@PathVariable Long id) {
        return mapper.AtoDTO(agenceService.findById(id));
    }

    @Operation(summary = "Créer une agence")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AgenceDTO create(@Valid @RequestBody AgenceDTO agenceDTO) {
        Agence agence = mapper.AtoEntity(agenceDTO);
        return mapper.AtoDTO(agenceService.create(agence));
    }

    @Operation(summary = "Mettre à jour une agence")
    @PutMapping("/{id}")
    public AgenceDTO update(@PathVariable Long id, @Valid @RequestBody AgenceDTO agenceDTO) {
        Agence agence = mapper.AtoEntity(agenceDTO);
        return mapper.AtoDTO(agenceService.update(id, agence));
    }

    @Operation(summary = "Supprimer une agence")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Long id) {
        agenceService.deleteById(id);
    }
}

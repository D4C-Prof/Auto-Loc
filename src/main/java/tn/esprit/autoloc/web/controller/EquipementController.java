package tn.esprit.autoloc.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.domain.Equipement;
import tn.esprit.autoloc.dto.EquipementDTO;
import tn.esprit.autoloc.dto.IMapper;
import tn.esprit.autoloc.service.IEquipementService;

import java.util.List;

@Tag(name = "Équipements", description = "Gestion des équipements")
@RestController
@RequestMapping("/equipements")
@RequiredArgsConstructor
public class EquipementController {
    private final IEquipementService equipementService;
    private final IMapper mapper;

    @Operation(summary = "Liste de tous les équipements")
    @GetMapping
    public List<EquipementDTO> findAll() {
        return mapper.EqtoDTOList(equipementService.findAll());
    }

    @Operation(summary = "Consulter un équipement")
    @ApiResponse(responseCode = "200", description = "Équipement trouvé")
    @ApiResponse(responseCode = "404", description = "Équipement introuvable")
    @GetMapping("/{id}")
    public EquipementDTO findById(@PathVariable Long id) {
        return mapper.EqtoDTO(equipementService.findById(id));
    }

    @Operation(summary = "Créer un équipement")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EquipementDTO create(@Valid @RequestBody EquipementDTO equipementDTO) {
        Equipement equipement = mapper.EqtoEntity(equipementDTO);
        return mapper.EqtoDTO(equipementService.create(equipement));
    }

    @Operation(summary = "Mettre à jour un équipement")
    @PutMapping("/{id}")
    public EquipementDTO update(@PathVariable Long id, @Valid @RequestBody EquipementDTO equipementDTO) {
        Equipement equipement = mapper.EqtoEntity(equipementDTO);
        return mapper.EqtoDTO(equipementService.update(id, equipement));
    }

    @Operation(summary = "Supprimer un équipement")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Long id) {
        equipementService.deleteById(id);
    }
}

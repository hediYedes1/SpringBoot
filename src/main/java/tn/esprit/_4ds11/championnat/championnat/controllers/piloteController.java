package tn.esprit._4ds11.championnat.championnat.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit._4ds11.championnat.championnat.entities.Pilote;
import tn.esprit._4ds11.championnat.championnat.services.IPiloteService;

@Tag(name = "gestion des pilotes")
@RestController
@AllArgsConstructor
@RequestMapping("/api/pilotes")
public class piloteController {

    private final IPiloteService piloteService;

    @Operation(description = "ajouter un pilote dans la base de donnee")
    @PostMapping("/add-pilote")
    @ResponseBody
    public Pilote addPilote(@RequestBody Pilote p) {
        return piloteService.ajouterPilote(p);
    }

    @Operation(description = "ajouter un pilote avec positions associees")
    @PostMapping("/addPiloteEtPositionAssocie")
    @ResponseBody
    public Pilote addPiloteEtPositionAssocie(@RequestBody Pilote p) {
        return piloteService.addPiloteEtPositionAssocie(p);
    }
}

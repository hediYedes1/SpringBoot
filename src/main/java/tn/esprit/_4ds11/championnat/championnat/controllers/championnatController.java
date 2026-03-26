package tn.esprit._4ds11.championnat.championnat.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit._4ds11.championnat.championnat.entities.Championnat;
import tn.esprit._4ds11.championnat.championnat.entities.DetailChampionnat;
import tn.esprit._4ds11.championnat.championnat.services.IChampionnatService;

@Tag(name= "Gestion Championnat")
@RestController
@AllArgsConstructor
@RequestMapping("/api/championnats")
public class championnatController {

    private final IChampionnatService championnatService;

    @Operation(description = "ajouter un championnat dans la base de donnee")
    @PostMapping("/add-championnat")
    @ResponseBody
    public Championnat ajouterChampionnat(@RequestBody Championnat championnat) {
        return championnatService.ajouterChampionnat(championnat);
    }

    @Operation(description = "affecter un detail a un championnat")
    @PutMapping("/affecterDetailschampionnatToChampionnat/{idChampionnat}")
    @ResponseBody
    public Championnat affecterDetailChampionnatToChampionnat(@RequestBody DetailChampionnat dt,
                                                               @PathVariable Long idChampionnat) {
        return championnatService.affecterDetailChampionnatToChampionnat(dt, idChampionnat);
    }
}

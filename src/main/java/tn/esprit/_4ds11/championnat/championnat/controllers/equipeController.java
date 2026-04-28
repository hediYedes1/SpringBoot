package tn.esprit._4ds11.championnat.championnat.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit._4ds11.championnat.championnat.entities.Equipe;
import tn.esprit._4ds11.championnat.championnat.services.IEquipeService;
import tn.esprit._4ds11.championnat.championnat.services.championnatService;
import tn.esprit._4ds11.championnat.championnat.services.equipeService;

import java.util.HashMap;

@Tag(name= "Gestion Equipe")
@RestController
@AllArgsConstructor
@RequestMapping("/api/equipes")
public class equipeController {

    private final IEquipeService equipeService;
    private final equipeService equipeSe;;
    private final championnatService champService;

    @Operation(description = "ajouter une equipe dans la base de donnee")
    @PostMapping("/add-equipe")
    @ResponseBody
    public Equipe ajouterEquipe(@RequestBody Equipe equipe) {
        return equipeService.ajouterEquipe(equipe);
    }

    @Operation(description = "ajouter une equipe avec contrats associes")
    @PostMapping("/addEquipeEtContratAssocie")
    @ResponseBody
    public Equipe addEquipeEtContratAssocie(@RequestBody Equipe e) {
        return equipeService.addEquipeEtContratAssocie(e);
    }

    @Operation(description = "ajouter une equipe avec pilotes associes")
    @PostMapping("/addEquipeEtPiloteAssocie")
    @ResponseBody
    public Equipe addEquipeEtPiloteAssocie(@RequestBody Equipe e) {
        return equipeService.addEquipeEtPiloteAssocie(e);
    }

    @Operation(description = "historique des contrats")
    @GetMapping("/historique-contrats/{libelleEquipe}")
    public ResponseEntity<HashMap<String, Float>> historiqueContrats(
            @PathVariable String libelleEquipe) {
        return ResponseEntity.ok(
                champService.historiqueContratsEquipe(libelleEquipe)
        );
    }

    @GetMapping("/points-equipe")
    public ResponseEntity<Integer> nbPointsEquipe(
            @RequestParam Long idEquipe,
            @RequestParam Long idChampionnat,
            @RequestParam String annee) {
        return ResponseEntity.ok(
                equipeSe.nbPointsParPilotesUneEquipeChampionnatPourUneAnne(
                        idEquipe, idChampionnat, annee)
        );
    }
}

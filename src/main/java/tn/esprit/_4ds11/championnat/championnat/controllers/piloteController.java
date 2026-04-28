package tn.esprit._4ds11.championnat.championnat.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit._4ds11.championnat.championnat.dto.PiloteDto;
import tn.esprit._4ds11.championnat.championnat.entities.Pilote;
import tn.esprit._4ds11.championnat.championnat.services.IPiloteService;
import tn.esprit._4ds11.championnat.championnat.services.championnatService;

import java.time.LocalDate;
import java.util.List;

@Tag(name = "gestion des pilotes")
@RestController
@AllArgsConstructor
@RequestMapping("/api/pilotes")
public class piloteController {

    private final IPiloteService piloteService;
    private final championnatService championnatservice;

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
    @GetMapping("/winners/{annee}")
    public List<PiloteDto> getWinners(@PathVariable Integer annee) {
        return piloteService.listeWinners(annee);
    }

    @GetMapping("/moyenne-positions")
    public ResponseEntity<Float> moyennePositions(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate,
            @RequestParam String libelleP) {
        return ResponseEntity.ok(
                piloteService.moyennePositionsEntreDeuxDate(startDate, endDate, libelleP)
        );
    }
}

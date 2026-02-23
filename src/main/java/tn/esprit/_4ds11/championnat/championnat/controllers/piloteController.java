package tn.esprit._4ds11.championnat.championnat.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public String addPilote(@RequestBody Pilote p) {
        Pilote savedPilote = piloteService.ajouterPilote(p);
        return "Pilote ajouté avec succès avec l'ID: " + savedPilote.getIdPilote();
    }
}

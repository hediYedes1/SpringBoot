package tn.esprit._4ds11.championnat.championnat.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit._4ds11.championnat.championnat.entities.Pilote;
import tn.esprit._4ds11.championnat.championnat.services.IPiloteService;

@RestController
@RequestMapping("/api/pilotes")
public class piloteController {
    
    @Autowired
    private IPiloteService piloteService;

    @PostMapping("/add-pilote")
    public String addPilote(@RequestBody Pilote p) {
        Pilote savedPilote = piloteService.ajouterPilote(p);
        return "Pilote ajouté avec succès avec l'ID: " + savedPilote.getIdPilote();
    }
}

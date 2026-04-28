package tn.esprit._4ds11.championnat.championnat.controllers;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit._4ds11.championnat.championnat.dto.ContratDto;
import tn.esprit._4ds11.championnat.championnat.entities.Contrat;
import tn.esprit._4ds11.championnat.championnat.services.contratService;

@RestController
@AllArgsConstructor
@RequestMapping("/api/contrat")
public class contratController {

    private final contratService cr;
    @PostMapping("/contrataffecter/{libelleEquipe}/{nomSponsor}/{pays}")
    public ContratDto ajouterContrat(
            @RequestBody Contrat contrat,
            @PathVariable String libelleEquipe,
            @PathVariable String nomSponsor,
            @PathVariable String pays) {

        return cr.ajoutContratEtAffecterASponsorEtEquipe(
                contrat, libelleEquipe, nomSponsor, pays);
    }
}

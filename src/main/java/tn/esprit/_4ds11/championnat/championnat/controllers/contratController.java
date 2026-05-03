package tn.esprit._4ds11.championnat.championnat.controllers;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit._4ds11.championnat.championnat.dto.ContratDto;
import tn.esprit._4ds11.championnat.championnat.entities.Contrat;
import tn.esprit._4ds11.championnat.championnat.services.IContratService;

@RestController
@AllArgsConstructor
@RequestMapping("/api/contrat")
public class contratController {

    private final IContratService cr;
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
/*
Relation n--1 Contrat (n) -> Equipe (1) et Contrat (n) -> Sponsor (1)
POST /api/contrat/contrataffecter/{libelleEquipe}/{nomSponsor}/{pays}
Exemple: /api/contrat/contrataffecter/Mercedes/Petronas/Malaysia
{
  "montant": 250000,
  "annee": "2026"
}
 */

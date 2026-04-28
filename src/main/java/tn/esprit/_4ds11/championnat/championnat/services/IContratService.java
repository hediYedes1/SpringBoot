package tn.esprit._4ds11.championnat.championnat.services;

import tn.esprit._4ds11.championnat.championnat.dto.ContratDto;
import tn.esprit._4ds11.championnat.championnat.entities.Contrat;

public interface IContratService {

    ContratDto ajoutContratEtAffecterASponsorEtEquipe(
            Contrat contrat,
            String libelleEquipe,
            String nomSponsor,
            String pays
    );
}

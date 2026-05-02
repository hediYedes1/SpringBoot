package tn.esprit._4ds11.championnat.championnat.services;

import tn.esprit._4ds11.championnat.championnat.dto.ContratDto;
import tn.esprit._4ds11.championnat.championnat.entities.Contrat;
import tn.esprit._4ds11.championnat.championnat.entities.Equipe;

import java.util.List;

public interface IContratService {

    ContratDto ajoutContratEtAffecterASponsorEtEquipe(
            Contrat contrat,
            String libelleEquipe,
            String nomSponsor,
            String pays
    );

    // ByEquipeLibelle
    List<Contrat> listerContratsParEquipe(String libelleEquipe);

    // And
    List<Contrat> listerContratsParEquipeEtSponsor(String libelleEquipe, String nomSponsor);

    // ContainingIgnoreCase
    List<Contrat> listerContratsParLibelleEquipeEtPaysSponsor(String motEquipe, String motPays);

    // OrderBy...Desc
    List<Equipe> listerEquipesParMotCleTrieesParSponsorDesc(String motCleEquipe);
}

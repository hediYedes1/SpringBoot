package tn.esprit._4ds11.championnat.championnat.services;

import tn.esprit._4ds11.championnat.championnat.entities.Equipe;

public interface IEquipeService {
    Equipe ajouterEquipe(Equipe equipe);
    Equipe addEquipeEtContratAssocie(Equipe e);
    Equipe addEquipeEtPiloteAssocie(Equipe e);
}

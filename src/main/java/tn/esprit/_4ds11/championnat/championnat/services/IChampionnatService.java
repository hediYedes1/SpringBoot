package tn.esprit._4ds11.championnat.championnat.services;

import tn.esprit._4ds11.championnat.championnat.entities.Championnat;
import tn.esprit._4ds11.championnat.championnat.entities.DetailChampionnat;

public interface IChampionnatService {
    Championnat ajouterChampionnat(Championnat championnat);
    Championnat affecterDetailChampionnatToChampionnat(DetailChampionnat dt, Long idChampionnat);
}

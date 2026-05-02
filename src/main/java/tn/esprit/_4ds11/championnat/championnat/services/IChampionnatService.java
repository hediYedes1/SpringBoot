package tn.esprit._4ds11.championnat.championnat.services;

import tn.esprit._4ds11.championnat.championnat.entities.Categorie;
import tn.esprit._4ds11.championnat.championnat.entities.Championnat;
import tn.esprit._4ds11.championnat.championnat.entities.DetailChampionnat;

import java.util.HashMap;
import java.util.List;

public interface IChampionnatService {
    Championnat ajouterChampionnat(Championnat championnat);

    Championnat affecterDetailChampionnatToChampionnat(DetailChampionnat dt, Long idChampionnat);

    void affecterChampionnatToCourse(Long idChampionnat, Long idCourse);

    HashMap<String, Float> historiqueContratsEquipe(String libelleEquipe);

    // Distinct + ByCategorie
    List<Championnat> listerChampionnatsParCategorie(Categorie categorie);

    // Distinct + navigation relationnelle
    List<Championnat> listerChampionnatsParEquipe(String libelleEquipe);

    // IsNotNull
    List<Championnat> listerChampionnatsParEquipeAvecDescriptionNonNulle(String libelleEquipe);

    // StartingWith + ContainingIgnoreCase
    List<Championnat> listerChampionnatsParNomEtMotDescription(String prefix, String mot);

    // DeleteBy + In
    long supprimerChampionnatsParCategories(List<Categorie> categories);
}

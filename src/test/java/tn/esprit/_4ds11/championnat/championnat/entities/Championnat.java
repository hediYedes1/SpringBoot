package tn.esprit._4ds11.championnat.championnat.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.EnumType;

@Entity
public class Championnat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idChampionnat;

    @Enumerated(EnumType.STRING)
    private Categorie categorie;

    private String libellec; // Note: nom étrange, probablement "libelle"
    private Integer annee; // Note: corrigé de "onnee" à "annee"

    // Constructeurs
    public Championnat() {}

    public Championnat(Categorie categorie, String libellec, Integer annee) {
        this.categorie = categorie;
        this.libellec = libellec;
        this.annee = annee;
    }

    // Getters et Setters
    public Long getIdChampionnat() {
        return idChampionnat;
    }

    public void setIdChampionnat(Long idChampionnat) {
        this.idChampionnat = idChampionnat;
    }

    public Categorie getCategorie() {
        return categorie;
    }

    public void setCategorie(Categorie categorie) {
        this.categorie = categorie;
    }

    public String getLibellec() {
        return libellec;
    }

    public void setLibellec(String libellec) {
        this.libellec = libellec;
    }

    public Integer getAnnee() {
        return annee;
    }

    public void setAnnee(Integer annee) {
        this.annee = annee;
    }
}

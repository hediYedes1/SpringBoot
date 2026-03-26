package tn.esprit._4ds11.championnat.championnat.entities;

import jakarta.persistence.*;
import lombok.Getter;

import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "championnat")
public class Championnat implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idChampionnat;

    @Enumerated(EnumType.STRING)
    private Categorie categorie;

    private String libellec; // Note: nom étrange, probablement "libelle"
    private Integer annee; // Note: corrigé de "onnee" à "annee"

    @OneToOne(cascade = CascadeType.ALL)
    private DetailChampionnat detailChampionnat ;

    @ManyToMany
    private List<Course> courses;

    // Constructeurs
    public Championnat() {
    }

    public Championnat(Categorie categorie, String libellec, Integer annee) {
        this.categorie = categorie;
        this.libellec = libellec;
        this.annee = annee;
    }

    // Getters et Setters

    public void setIdChampionnat(Long idChampionnat) {
        this.idChampionnat = idChampionnat;
    }

    public void setCategorie(Categorie categorie) {
        this.categorie = categorie;
    }

    public void setLibellec(String libellec) {
        this.libellec = libellec;
    }

    public void setAnnee(Integer annee) {
        this.annee = annee;
    }

    public void setDetailChampionnat(DetailChampionnat detailChampionnat) {
        this.detailChampionnat = detailChampionnat;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }
}

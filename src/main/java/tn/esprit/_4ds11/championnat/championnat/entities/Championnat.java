package tn.esprit._4ds11.championnat.championnat.entities;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "championnat")
public class Championnat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idChampionnat;

    @Enumerated(EnumType.STRING)
    private Categorie categorie;

    private String libellec; // Note: nom étrange, probablement "libelle"
    private Integer annee; // Note: corrigé de "onnee" à "annee"

    @OneToOne
    private DetailChampionnat detailChampionnat;

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

    public DetailChampionnat getDetailChampionnat() {
        return detailChampionnat;
    }

    public void setDetailChampionnat(DetailChampionnat detailChampionnat) {
        this.detailChampionnat = detailChampionnat;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }
}

package tn.esprit._4ds11.championnat.championnat.entities;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "equipe")
public class Equipe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idEquipe;

    private String libelle;
    private Integer nbPointsTotal;
    private Integer classementGeneral;

    @OneToMany(mappedBy = "equipe", cascade = CascadeType.ALL)
    private List<Pilote> pilotes;

    @OneToMany(mappedBy = "equipe", cascade = CascadeType.ALL)
    private List<Contrat> contrats;

    // Constructeurs
    public Equipe() {}

    public Equipe(String libelle, Integer nbPointsTotal, Integer classementGeneral) {
        this.libelle = libelle;
        this.nbPointsTotal = nbPointsTotal;
        this.classementGeneral = classementGeneral;
    }

    // Getters et Setters
    public Long getIdEquipe() {
        return idEquipe;
    }

    public void setIdEquipe(Long idEquipe) {
        this.idEquipe = idEquipe;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public Integer getNbPointsTotal() {
        return nbPointsTotal;
    }

    public void setNbPointsTotal(Integer nbPointsTotal) {
        this.nbPointsTotal = nbPointsTotal;
    }

    public Integer getClassementGeneral() {
        return classementGeneral;
    }

    public void setClassementGeneral(Integer classementGeneral) {
        this.classementGeneral = classementGeneral;
    }

    public List<Pilote> getPilotes() {
        return pilotes;
    }

    public void setPilotes(List<Pilote> pilotes) {
        this.pilotes = pilotes;
    }

    public List<Contrat> getContrats() {
        return contrats;
    }

    public void setContrats(List<Contrat> contrats) {
        this.contrats = contrats;
    }
}

package tn.esprit._4ds11.championnat.championnat.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

@Entity
public class Pilote {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPilote;

    private String libelleP;
    private Integer nbPointsTotal;
    private Integer classementGeneral;

    // Constructeurs
    public Pilote() {}

    public Pilote(String libelleP, Integer nbPointsTotal, Integer classementGeneral) {
        this.libelleP = libelleP;
        this.nbPointsTotal = nbPointsTotal;
        this.classementGeneral = classementGeneral;
    }

    // Getters et Setters
    public Long getIdPilote() {
        return idPilote;
    }

    public void setIdPilote(Long idPilote) {
        this.idPilote = idPilote;
    }

    public String getLibelleP() {
        return libelleP;
    }

    public void setLibelleP(String libelleP) {
        this.libelleP = libelleP;
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



}

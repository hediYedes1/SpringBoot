package tn.esprit._4ds11.championnat.championnat.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

@Entity
public class Position {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPosition;

    private Integer classement;
    private Integer nbPoints;

    // Constructeurs
    public Position() {}

    public Position(Integer classement, Integer nbPoints) {
        this.classement = classement;
        this.nbPoints = nbPoints;
    }

    // Getters et Setters
    public Long getIdPosition() {
        return idPosition;
    }

    public void setIdPosition(Long idPosition) {
        this.idPosition = idPosition;
    }

    public Integer getClassement() {
        return classement;
    }

    public void setClassement(Integer classement) {
        this.classement = classement;
    }

    public Integer getNbPoints() {
        return nbPoints;
    }

    public void setNbPoints(Integer nbPoints) {
        this.nbPoints = nbPoints;
    }
}

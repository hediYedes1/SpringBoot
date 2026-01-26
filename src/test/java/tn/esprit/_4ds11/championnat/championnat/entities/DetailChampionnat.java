package tn.esprit._4ds11.championnat.championnat.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

@Entity
public class DetailChampionnat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String idDetailChamp; // Note: Normalement Long, mais spécifié String dans le diagramme

    private String code;
    private String description;

    // Constructeurs
    public DetailChampionnat() {}

    public DetailChampionnat(String code, String description) {
        this.code = code;
        this.description = description;
    }

    // Getters et Setters
    public String getIdDetailChamp() {
        return idDetailChamp;
    }

    public void setIdDetailChamp(String idDetailChamp) {
        this.idDetailChamp = idDetailChamp;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

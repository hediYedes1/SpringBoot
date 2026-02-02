package tn.esprit._4ds11.championnat.championnat.entities;

import jakarta.persistence.*;

@Entity
public class DetailChampionnat {

    @Id
    private String idDetailChamp; // Note: Normalement Long, mais spécifié String dans le diagramme
    private String code;
    private String description;

    @OneToOne(mappedBy = "detailChampionnat")
    private Championnat championnat;
    // Constructeurs
    public DetailChampionnat() {}

    public DetailChampionnat(String code, String description) {
        this.code = code;
        this.description = description;
    }




}

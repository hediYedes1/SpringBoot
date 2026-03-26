package tn.esprit._4ds11.championnat.championnat.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.UUID;

@Entity
public class DetailChampionnat implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    private String idDetailChamp;

    private String code;
    private String description;

    @OneToOne(mappedBy = "detailChampionnat")
    @JsonIgnore
    private Championnat championnat;

    public DetailChampionnat() {}

    public DetailChampionnat(String code, String description) {
        this.code = code;
        this.description = description;
    }

    @PrePersist
    public void prePersist() {
        if (this.idDetailChamp == null || this.idDetailChamp.isBlank()) {
            this.idDetailChamp = UUID.randomUUID().toString();
        }
    }

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

    public Championnat getChampionnat() {
        return championnat;
    }

    public void setChampionnat(Championnat championnat) {
        this.championnat = championnat;
    }
}

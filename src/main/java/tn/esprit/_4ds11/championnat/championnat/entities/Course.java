package tn.esprit._4ds11.championnat.championnat.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "course")
public class Course implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCourse;

    private String emplacement;
    private LocalDate dateCourse;

    @ManyToMany(mappedBy = "courses", cascade = CascadeType.ALL)
    private List<Championnat> championnats ;

    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Position> positions;

    // Constructeurs
    public Course() {}

    public Course(String emplacement, LocalDate dateCourse) {
        this.emplacement = emplacement;
        this.dateCourse = dateCourse;
    }

    // Getters et Setters
    public Long getIdCourse() {
        return idCourse;
    }

    public void setIdCourse(Long idCourse) {
        this.idCourse = idCourse;
    }

    public String getEmplacement() {
        return emplacement;
    }

    public void setEmplacement(String emplacement) {
        this.emplacement = emplacement;
    }

    public LocalDate getDateCourse() {
        return dateCourse;
    }

    public void setDateCourse(LocalDate dateCourse) {
        this.dateCourse = dateCourse;
    }

    public List<Championnat> getChampionnats() {
        return championnats;
    }

    public void setChampionnats(List<Championnat> championnats) {
        this.championnats = championnats;
    }

    public List<Position> getPositions() {
        return positions;
    }

    public void setPositions(List<Position> positions) {
        this.positions = positions;
    }
}

package tn.esprit._4ds11.championnat.championnat.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import java.time.LocalDate;

@Entity
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCourse;

    private String emplacement;
    private LocalDate dateCourse;

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
}

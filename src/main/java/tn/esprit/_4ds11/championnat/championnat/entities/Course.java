package tn.esprit._4ds11.championnat.championnat.entities;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "course")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCourse;

    private String emplacement;
    private LocalDate dateCourse;

    @ManyToMany(mappedBy = "courses", cascade = CascadeType.ALL)
    private List<Championnat> championnats ;

    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL)
    private List<Position> positions;

    // Constructeurs
    public Course() {}

    public Course(String emplacement, LocalDate dateCourse) {
        this.emplacement = emplacement;
        this.dateCourse = dateCourse;
    }

    // Getters et Setters

}

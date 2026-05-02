package tn.esprit._4ds11.championnat.championnat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import tn.esprit._4ds11.championnat.championnat.entities.Course;

import java.time.LocalDate;

public interface courseRepository extends JpaRepository<Course, Long> {

    // JPQL equivalent de CountBy + Between.
    @Query("SELECT COUNT(c) FROM Course c WHERE c.dateCourse BETWEEN :startDate AND :endDate")
    long countCoursesBetweenDatesJPQL(
            @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate
    );
}

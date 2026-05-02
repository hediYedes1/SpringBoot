package tn.esprit._4ds11.championnat.championnat.services;

import tn.esprit._4ds11.championnat.championnat.entities.Course;

import java.time.LocalDate;

public interface ICourseService {
    Course ajouterCourse(Course course);

    Course addCourseEtPositionAssocie(Course c);

    // Keyword CountBy + Between
    long compterCoursesEntreDeuxDates(LocalDate startDate, LocalDate endDate);
}

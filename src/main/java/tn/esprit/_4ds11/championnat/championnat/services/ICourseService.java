package tn.esprit._4ds11.championnat.championnat.services;

import tn.esprit._4ds11.championnat.championnat.entities.Course;

public interface ICourseService {
    Course ajouterCourse(Course course);
    Course addCourseEtPositionAssocie(Course c);
}

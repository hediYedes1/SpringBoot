package tn.esprit._4ds11.championnat.championnat.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit._4ds11.championnat.championnat.entities.Course;
import tn.esprit._4ds11.championnat.championnat.repository.courseRepository;
import tn.esprit._4ds11.championnat.championnat.repository.positionRepository;

@Service
@RequiredArgsConstructor
public class courseService implements ICourseService {

    private final courseRepository cor;
    private final positionRepository posr;

    @Override
    public Course ajouterCourse(Course course) {
        return cor.save(course);
    }

    @Override
    public Course addCourseEtPositionAssocie(Course c) {
        Course course = cor.save(c);
        if (course.getPositions() != null) {
            course.getPositions().forEach(position -> {
                position.setCourse(course);
                posr.save(position);
            });
        }
        return course;
    }
}

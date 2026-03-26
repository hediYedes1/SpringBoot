package tn.esprit._4ds11.championnat.championnat.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit._4ds11.championnat.championnat.entities.Championnat;
import tn.esprit._4ds11.championnat.championnat.entities.Course;
import tn.esprit._4ds11.championnat.championnat.entities.DetailChampionnat;
import tn.esprit._4ds11.championnat.championnat.repository.championnatRepository;
import tn.esprit._4ds11.championnat.championnat.repository.courseRepository;
import tn.esprit._4ds11.championnat.championnat.repository.detailChampionnatRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class championnatService implements IChampionnatService {

    private final championnatRepository cr;
    private final detailChampionnatRepository dcr;
    private final courseRepository cor;

    @Override
    public Championnat ajouterChampionnat(Championnat championnat) {
        return cr.save(championnat);
    }

    @Override
    public Championnat affecterDetailChampionnatToChampionnat(DetailChampionnat dt, Long idChampionnat) {
        Championnat championnat = cr.findById(idChampionnat)
                .orElseThrow(() -> new RuntimeException("Championnat introuvable"));

        DetailChampionnat detailChampionnat = dcr.save(dt);
        championnat.setDetailChampionnat(detailChampionnat);
        return cr.save(championnat);
    }

    @Override
    public void affecterChampionnatToCourse(Long idChampionnat, Long idCourse) {
        Championnat championnat = cr.findById(idChampionnat)
                .orElseThrow(() -> new RuntimeException("Championnat introuvable"));
        Course course = cor.findById(idCourse)
                .orElseThrow(() -> new RuntimeException("Course introuvable"));

        List<Course> coursesMisesAjour = championnat.getCourses();
        if (coursesMisesAjour == null) {
            coursesMisesAjour = new ArrayList<>();
        }

        boolean dejaAffecte = coursesMisesAjour.stream()
                .anyMatch(c -> c.getIdCourse().equals(idCourse));

        if (!dejaAffecte) {
            coursesMisesAjour.add(course);
        }

        championnat.setCourses(coursesMisesAjour);
        cr.save(championnat);
    }
}

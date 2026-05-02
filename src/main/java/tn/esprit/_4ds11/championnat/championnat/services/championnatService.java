package tn.esprit._4ds11.championnat.championnat.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tn.esprit._4ds11.championnat.championnat.entities.Categorie;
import tn.esprit._4ds11.championnat.championnat.entities.Championnat;
import tn.esprit._4ds11.championnat.championnat.entities.Course;
import tn.esprit._4ds11.championnat.championnat.entities.DetailChampionnat;
import tn.esprit._4ds11.championnat.championnat.repository.championnatRepository;
import tn.esprit._4ds11.championnat.championnat.repository.contratRepository;
import tn.esprit._4ds11.championnat.championnat.repository.courseRepository;
import tn.esprit._4ds11.championnat.championnat.repository.detailChampionnatRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
@RequiredArgsConstructor
public class championnatService implements IChampionnatService {

    private final championnatRepository cr;
    private final detailChampionnatRepository dcr;
    private final courseRepository cor;
    private final contratRepository contratRepo;

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

    @Override
    public HashMap<String, Float> historiqueContratsEquipe(String libelleEquipe) {
        List<Object[]> results = contratRepo.findHistoriqueContratsByEquipe(libelleEquipe);

        HashMap<String, Float> historique = new HashMap<>();
        for (Object[] row : results) {
            String annee = (String) row[0];
            Float montant = ((Number) row[1]).floatValue();
            historique.put(annee, montant);
        }
        return historique;
    }

    // Keyword Distinct + ByCategorie.
    @Override
    public List<Championnat> listerChampionnatsParCategorie(Categorie categorie) {
        return cr.findDistinctByCategorieJPQL(categorie);
    }

    // Keyword Distinct + navigation relationnelle.
    @Override
    public List<Championnat> listerChampionnatsParEquipe(String libelleEquipe) {
        return cr.findChampionnatsByEquipeJPQL(libelleEquipe);
    }

    // Keyword IsNotNull.
    @Override
    public List<Championnat> listerChampionnatsParEquipeAvecDescriptionNonNulle(String libelleEquipe) {
        return cr.findChampionnatsByEquipeAvecDescriptionNonNulleJPQL(
                libelleEquipe
        );
    }

    // Keyword StartingWith + ContainingIgnoreCase.
    @Override
    public List<Championnat> listerChampionnatsParNomEtMotDescription(String prefix, String mot) {
        return cr.findByNomPrefixAndDescriptionContainsJPQL(prefix, mot);
    }

    // Keyword DeleteBy + In.
    @Override
    @Transactional
    public long supprimerChampionnatsParCategories(List<Categorie> categories) {
        return cr.deleteChampionnatsByCategoriesJPQL(categories);
    }
}

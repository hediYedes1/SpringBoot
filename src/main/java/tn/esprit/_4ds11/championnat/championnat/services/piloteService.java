package tn.esprit._4ds11.championnat.championnat.services;

import lombok.extern.slf4j.Slf4j;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tn.esprit._4ds11.championnat.championnat.entities.Categorie;
import tn.esprit._4ds11.championnat.championnat.entities.Pilote;
import tn.esprit._4ds11.championnat.championnat.entities.Position;
import tn.esprit._4ds11.championnat.championnat.repository.piloteRepository;
import tn.esprit._4ds11.championnat.championnat.repository.positionRepository;

import java.time.Year;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class piloteService implements IPiloteService{


    private final piloteRepository pr;
    private final positionRepository posr;
    @Value("${app.scheduler.categorie-cible:FORMULA1}")
    private Categorie categorieCible;

    @Override
    public Pilote ajouterPilote(Pilote pilote) {
        return pr.save(pilote);
    }

    @Override
    public Pilote addPiloteEtPositionAssocie(Pilote p) {
        Pilote pilote = pr.save(p);
        if (pilote.getPositions() != null) {
            pilote.getPositions().forEach(position -> {
                position.setPilote(pilote);
                posr.save(position);
            });
        }
        return pilote;
    }

    @Scheduled(cron = "0 15 11 31 12 *", zone = "Europe/Paris")
    @Transactional
    public void mettreAJourPointsEtClassementPilotesFinAnnee() {
        int currentYear = Year.now().getValue();

        List<Pilote> pilotes = pr.findAll();
        List<Position> positions = posr.findAll();
        Map<Long, Integer> pointsParPilote = new HashMap<>();

        for (Position position : positions) {
            if (position.getPilote() == null || position.getCourse() == null) {
                continue;
            }
            if (position.getCourse().getDateCourse() == null
                    || position.getCourse().getDateCourse().getYear() != currentYear) {
                continue;
            }
            if (position.getCourse().getChampionnats() == null
                    || position.getCourse().getChampionnats().stream().noneMatch(ch ->
                    ch.getCategorie() == categorieCible && ch.getAnnee() != null && ch.getAnnee() == currentYear)) {
                continue;
            }

            int points = position.getNbPoints() == null ? 0 : position.getNbPoints();
            pointsParPilote.merge(position.getPilote().getIdPilote(), points, Integer::sum);
        }

        for (Pilote pilote : pilotes) {
            int points = pointsParPilote.getOrDefault(pilote.getIdPilote(), 0);
            pilote.setNbPointsTotal(points);
        }

        pilotes.sort(Comparator.comparing(Pilote::getNbPointsTotal, Comparator.nullsFirst(Integer::compareTo)).reversed());

        for (int i = 0; i < pilotes.size(); i++) {
            pilotes.get(i).setClassementGeneral(i + 1);
        }

        pr.saveAll(pilotes);
        log.info("Mise a jour annuelle terminee pour la categorie {} (annee {})", categorieCible, currentYear);
    }
}

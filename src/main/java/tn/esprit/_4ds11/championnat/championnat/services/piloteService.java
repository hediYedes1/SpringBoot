package tn.esprit._4ds11.championnat.championnat.services;

import lombok.extern.slf4j.Slf4j;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tn.esprit._4ds11.championnat.championnat.dto.PiloteDto;
import tn.esprit._4ds11.championnat.championnat.entities.Categorie;
import tn.esprit._4ds11.championnat.championnat.entities.Championnat;
import tn.esprit._4ds11.championnat.championnat.entities.Pilote;
import tn.esprit._4ds11.championnat.championnat.entities.Position;
import tn.esprit._4ds11.championnat.championnat.repository.championnatRepository;
import tn.esprit._4ds11.championnat.championnat.repository.piloteRepository;
import tn.esprit._4ds11.championnat.championnat.repository.positionRepository;

import java.time.LocalDate;
import java.time.Year;
import java.util.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class piloteService implements IPiloteService{


    private final piloteRepository pr;
    private final positionRepository posr;
    private final championnatRepository championnatRepo;
    private final positionRepository  posrRepo;
    @Value("${app.scheduler.categorie-cible:FORMULA1}")
    private Categorie categorieCible;

    public PiloteDto getPiloteDTO(Long id) {
        Pilote pilote = pr.findById(id)
                .orElseThrow(() -> new RuntimeException("Pilote non trouve"));
        return convertToDto(pilote, null); // pas de championnat ici
    }

    private PiloteDto convertToDto(Pilote pilote, Championnat ch) {
        PiloteDto dto = new PiloteDto();
        dto.setLibelleP(pilote.getLibelleP());
        dto.setNbpointsTotal(pilote.getNbPointsTotal());
        // libelleC seulement si un championnat est fourni
        dto.setLibelleC(ch != null ? ch.getLibellec() : null);
        return dto;
    }

    @Override
    public List<PiloteDto> listeWinners(Integer annee) {
        List<PiloteDto> winners = new ArrayList<>();


        List<Championnat> championnats = championnatRepo.findByAnneeGreaterThan(annee);

        for (Championnat ch : championnats) {

            Optional<Pilote> gagnant = pr.findAll()
                    .stream()
                    .filter(p -> p.getClassementGeneral() != null
                            && p.getClassementGeneral() == 1)
                    .findFirst();

            if (gagnant.isPresent()) {
                PiloteDto dto = convertToDto(gagnant.get(), ch);
                winners.add(dto);
            }
        }

        return winners;
    }

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

    public Float moyennePositionsEntreDeuxDate(
            LocalDate startDate, LocalDate endDate, String libelleP) {

        Float moyenne = posrRepo
                .avgClassementByPiloteAndDateBetween(libelleP, startDate, endDate);

        return moyenne != null ? moyenne : 0f;
    }
}
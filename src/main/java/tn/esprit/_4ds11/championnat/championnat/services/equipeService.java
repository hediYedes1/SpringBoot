package tn.esprit._4ds11.championnat.championnat.services;

import lombok.extern.slf4j.Slf4j;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tn.esprit._4ds11.championnat.championnat.entities.Contrat;
import tn.esprit._4ds11.championnat.championnat.entities.Equipe;
import tn.esprit._4ds11.championnat.championnat.repository.contratRepository;
import tn.esprit._4ds11.championnat.championnat.repository.equipeRepository;
import tn.esprit._4ds11.championnat.championnat.repository.piloteRepository;

import java.time.Year;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class equipeService implements IEquipeService {

    private final equipeRepository er;
    private final contratRepository cr;
    private final piloteRepository pr;

    @Override
    public Equipe ajouterEquipe(Equipe equipe) {
        return er.save(equipe);
    }

    @Override
    public Equipe addEquipeEtContratAssocie(Equipe e) {
        Equipe equipe = er.save(e);
        if (equipe.getContrats() != null) {
            equipe.getContrats().forEach(contrat -> {
                contrat.setEquipe(equipe);
                cr.save(contrat);
            });
        }
        return equipe;
    }

    @Override
    public Equipe addEquipeEtPiloteAssocie(Equipe e) {
        Equipe equipe = er.save(e);
        if (equipe.getPilotes() != null) {
            equipe.getPilotes().forEach(pilote -> {
                pilote.setEquipe(equipe);
                pr.save(pilote);
            });
        }
        return equipe;
    }

    @Scheduled(fixedRate = 30000)
    @Transactional
    public void archiverContratsExpireesEtAffichageContratsActifsParEquipe() {
        int currentYear = Year.now().getValue();
        List<Contrat> contratsToArchive = new ArrayList<>();
        List<Contrat> allContrats = cr.findAll();

        for (Contrat contrat : allContrats) {
            int contratYear = parseYear(contrat.getAnnee());
            if (contratYear != -1 && contratYear < currentYear && !Boolean.TRUE.equals(contrat.getArchived())) {
                contrat.setArchived(true);
                contratsToArchive.add(contrat);
            }
        }

        if (!contratsToArchive.isEmpty()) {
            cr.saveAll(contratsToArchive);
            log.info("{} contrat(s) expiré(s) archivé(s)", contratsToArchive.size());
        }

        for (Contrat contrat : allContrats) {
            int contratYear = parseYear(contrat.getAnnee());
            if (contratYear >= currentYear
                    && !Boolean.TRUE.equals(contrat.getArchived())
                    && contrat.getEquipe() != null
                    && contrat.getSponsor() != null) {
                log.info("L'equipe {} a un contrat d'un montant de {} avec le sponsor {}",
                        contrat.getEquipe().getLibelle(),
                        contrat.getMontant(),
                        contrat.getSponsor().getNom());
            }
        }
    }

    private int parseYear(String yearValue) {
        try {
            return Integer.parseInt(yearValue);
        } catch (NumberFormatException e) {
            return -1;
        }
    }
}

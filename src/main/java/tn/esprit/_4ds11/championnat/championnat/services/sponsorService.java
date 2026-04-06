package tn.esprit._4ds11.championnat.championnat.services;

import lombok.extern.slf4j.Slf4j;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tn.esprit._4ds11.championnat.championnat.entities.Contrat;
import tn.esprit._4ds11.championnat.championnat.entities.Sponsor;
import tn.esprit._4ds11.championnat.championnat.repository.contratRepository;
import tn.esprit._4ds11.championnat.championnat.repository.sponsorRepository;

import java.time.Year;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class sponsorService implements ISponsorService{

private final sponsorRepository sr;
private final contratRepository cr;
    @Override
    public Sponsor ajouterSponsor(Sponsor sponsor) {
        return sr.save(sponsor);
    }

    @Override
    public List<Sponsor> ajouterSponsors(List<Sponsor> sponsors) {

       return sr.saveAll(sponsors);
    }

    @Override
    public Sponsor modifierSponsor(Sponsor sponsor) {
        return sr.save(sponsor);
    }

    @Override
    public void supprimerSponsor(Long idSponsor) {
        sr.deleteById(idSponsor);
    }

    @Override
    public List<Sponsor> listSponsors() {
        return sr.findAll();
    }

    @Override
    public Sponsor recupererSponsor(Long idSponsor) {
        return sr.findById(idSponsor).orElse(null);
    }

    @Override
    public Boolean archiverSponsor(Long idSponsor) {
        Optional<Sponsor> sponsorOpt = sr.findById(idSponsor);

        if (sponsorOpt.isPresent()) {
            Sponsor sponsor = sponsorOpt.get();
            sponsor.setArchived(true);
            sr.save(sponsor);
            return true;
        }
        return false;
    }

    @Override
    public Sponsor addSponsorEtContratAssocie(Sponsor s)
    {
        Sponsor sponsor = sr.save(s);
        if (sponsor.getContrats() != null) {
            sponsor.getContrats().forEach(contrat ->
            {
                contrat.setSponsor(sponsor);
                cr.save(contrat);
            });
        }
        return sponsor;
    }

    @Override
    @Transactional(readOnly = true)
    public Float pourcentageBudgetAnnuelConsomme(Long idSponsor) {
        Sponsor sponsor = sr.findById(idSponsor).orElse(null);
        if (sponsor == null || sponsor.getBudgetAnnuel() == null || sponsor.getBudgetAnnuel() <= 0) {
            return 0f;
        }

        int currentYear = Year.now().getValue();
        float totalContrats = 0f;

        if (sponsor.getContrats() != null) {
            for (Contrat contrat : sponsor.getContrats()) {
                if (contrat == null || Boolean.TRUE.equals(contrat.getArchived())) {
                    continue;
                }
                int contratYear = parseYear(contrat.getAnnee());
                if (contratYear == currentYear) {
                    totalContrats += contrat.getMontant() == null ? 0f : contrat.getMontant();
                }
            }
        }

        return (totalContrats / sponsor.getBudgetAnnuel()) * 100f;
    }

    @Scheduled(cron = "0 0 9 * * MON", zone = "Europe/Paris")
    @Transactional
    public void afficherPourcentageBudgetSponsorsLundi9h() {
        List<Sponsor> sponsors = sr.findAll();

        for (Sponsor sponsor : sponsors) {
            Float pourcentage = pourcentageBudgetAnnuelConsomme(sponsor.getIdSponsor());
            log.info("sponsor: {} pourcentage : {}", sponsor.getNom(), pourcentage);

            if (pourcentage > 100f) {
                log.info("budget depasse!! vous ne pouvez plus faire de contrats");
                sponsor.setBloquerContrat(true);
                sr.save(sponsor);
            } else if (pourcentage > 70f) {
                log.info("attention budget presque consomme : {} % !", pourcentage);
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


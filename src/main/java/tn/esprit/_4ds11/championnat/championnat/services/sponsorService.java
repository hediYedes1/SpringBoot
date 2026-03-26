package tn.esprit._4ds11.championnat.championnat.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit._4ds11.championnat.championnat.entities.Equipe;
import tn.esprit._4ds11.championnat.championnat.entities.Sponsor;
import tn.esprit._4ds11.championnat.championnat.repository.contratRepository;
import tn.esprit._4ds11.championnat.championnat.repository.sponsorRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
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
        sponsor.getContrats().forEach(contrat ->
        {
            contrat.setSponsor(sponsor);
            cr.save(contrat);
        });
        return sponsor;
    }
    }


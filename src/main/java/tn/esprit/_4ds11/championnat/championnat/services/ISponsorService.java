package tn.esprit._4ds11.championnat.championnat.services;

import tn.esprit._4ds11.championnat.championnat.dto.SponsorResumeDto;
import tn.esprit._4ds11.championnat.championnat.entities.Sponsor;

import java.util.List;
import java.util.Optional;

public interface ISponsorService {

    Sponsor ajouterSponsor(Sponsor sponsor);

    List<Sponsor> ajouterSponsors(List<Sponsor> sponsors);

    Sponsor modifierSponsor(Sponsor sponsor);

    void supprimerSponsor(Long idSponsor);

    List<Sponsor> listSponsors();

    Sponsor recupererSponsor(Long idSponsor);

    Boolean archiverSponsor(Long idSponsor);

    Sponsor addSponsorEtContratAssocie(Sponsor s);

    Float pourcentageBudgetAnnuelConsomme(Long idSponsor);

    // Keyword DistinctFirstBy + IgnoreCase
    Optional<Sponsor> recupererSponsorDistinctParNom(String nom);

    Sponsor updateSponsor(Long idSponsor, Sponsor sponsor);

    SponsorResumeDto getSponsorResumeDto(Long idSponsor);
}

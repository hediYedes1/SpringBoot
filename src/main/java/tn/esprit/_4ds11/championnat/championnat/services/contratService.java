package tn.esprit._4ds11.championnat.championnat.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tn.esprit._4ds11.championnat.championnat.dto.ContratDto;
import tn.esprit._4ds11.championnat.championnat.entities.Contrat;
import tn.esprit._4ds11.championnat.championnat.entities.Equipe;
import tn.esprit._4ds11.championnat.championnat.entities.Sponsor;
import tn.esprit._4ds11.championnat.championnat.repository.contratRepository;
import tn.esprit._4ds11.championnat.championnat.repository.equipeRepository;
import tn.esprit._4ds11.championnat.championnat.repository.sponsorRepository;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class contratService implements IContratService {

    private final equipeRepository equipeRepo;
    private final contratRepository contratRepo;
    private final sponsorRepository sponsorRepo;

    @Override
    @Transactional
    public ContratDto ajoutContratEtAffecterASponsorEtEquipe(
            Contrat contrat,
            String libelleEquipe,
            String nomSponsor,
            String pays
    ) {
        if (contrat == null) {
            throw new IllegalArgumentException("Le contrat ne peut pas etre null");
        }

        Equipe equipe = equipeRepo.findByLibelle(libelleEquipe)
                .orElseThrow(() -> new RuntimeException("Equipe non trouvee : " + libelleEquipe));

        Sponsor sponsor = sponsorRepo.findByNomAndPays(nomSponsor, pays)
                .orElseThrow(() -> new RuntimeException("Sponsor non trouve : " + nomSponsor + " (" + pays + ")"));

        if (Boolean.TRUE.equals(sponsor.getBloquerContrat())) {
            throw new RuntimeException("Contrat refuse : le sponsor est bloque pour les nouveaux contrats");
        }

        if (sponsor.isArchived()) {
            throw new RuntimeException("Contrat refuse : le sponsor est archive");
        }

        contrat.setEquipe(equipe);
        contrat.setSponsor(sponsor);
        if (contrat.getArchived() == null) {
            contrat.setArchived(false);
        }

        Contrat saved = contratRepo.save(contrat);

        ContratDto dto = new ContratDto();
        dto.setIdContrat(saved.getIdContrat());
        dto.setMontant(saved.getMontant());
        dto.setAnnee(saved.getAnnee());
        dto.setLibelleEquipe(equipe.getLibelle());
        dto.setNomSponsor(sponsor.getNom());
        return dto;
    }

    // Keyword ByEquipeLibelle.
    @Override
    public List<Contrat> listerContratsParEquipe(String libelleEquipe) {
        return contratRepo.findContratsByEquipeJPQL(libelleEquipe);
    }

    // Keyword And.
    @Override
    public List<Contrat> listerContratsParEquipeEtSponsor(String libelleEquipe, String nomSponsor) {
        return contratRepo.findContratsByEquipeAndSponsorJPQL(libelleEquipe, nomSponsor);
    }

    // Keyword ContainingIgnoreCase.
    @Override
    public List<Contrat> listerContratsParLibelleEquipeEtPaysSponsor(String motEquipe, String motPays) {
        return contratRepo.findContratsByLibelleEquipeAndPaysSponsorJPQL(
                motEquipe,
                motPays
        );
    }

    // Keyword OrderBy...Desc.
    @Override
    public List<Equipe> listerEquipesParMotCleTrieesParSponsorDesc(String motCleEquipe) {
        List<Contrat> contratsTries = contratRepo.findContratsByEquipeOrderBySponsorNomDescJPQL(
                motCleEquipe
        );

        Set<Long> idsVus = new LinkedHashSet<>();
        List<Equipe> equipes = new ArrayList<>();
        for (Contrat contrat : contratsTries) {
            Equipe equipe = contrat.getEquipe();
            if (equipe != null && equipe.getIdEquipe() != null && idsVus.add(equipe.getIdEquipe())) {
                equipes.add(equipe);
            }
        }
        return equipes;
    }
}

package tn.esprit._4ds11.championnat.championnat.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit._4ds11.championnat.championnat.dto.ContratDto;
import tn.esprit._4ds11.championnat.championnat.entities.Contrat;
import tn.esprit._4ds11.championnat.championnat.entities.Equipe;
import tn.esprit._4ds11.championnat.championnat.entities.Sponsor;
import tn.esprit._4ds11.championnat.championnat.repository.contratRepository;
import tn.esprit._4ds11.championnat.championnat.repository.equipeRepository;
import tn.esprit._4ds11.championnat.championnat.repository.sponsorRepository;

@Service
@RequiredArgsConstructor
public class contratService implements IContratService{

    private final equipeRepository equipeRepo;
    private final contratRepository contratRepo;
    private final sponsorRepository sponsorRepo;
    @Override
    public ContratDto ajoutContratEtAffecterASponsorEtEquipe(Contrat contrat, String libelleEquipe, String nomSponsor, String pays) {
        // 1. Trouver l'équipe par son libellé
        Equipe equipe = equipeRepo.findByLibelle(libelleEquipe)
                .orElseThrow(() -> new RuntimeException("Equipe non trouvée : " + libelleEquipe));

        // 2. Trouver le sponsor par son nom et pays
        Sponsor sponsor = sponsorRepo.findByNomAndPays(nomSponsor, pays)
                .orElseThrow(() -> new RuntimeException("Sponsor non trouvé : " + nomSponsor));

        // 3. Affecter les relations
        contrat.setEquipe(equipe);
        contrat.setSponsor(sponsor);

        // 4. Sauvegarder le contrat
        Contrat saved = contratRepo.save(contrat);

        // 5. Conversion manuelle Entité → DTO
        ContratDto dto = new ContratDto();
        dto.setIdContrat(saved.getIdContrat());
        dto.setMontant(saved.getMontant());
        dto.setAnnee(saved.getAnnee());
        dto.setLibelleEquipe(equipe.getLibelle());   // depuis Equipe
        dto.setNomSponsor(sponsor.getNom());          // depuis Sponsor
        // archived non copié → invisible dans la réponse

        return dto;
    }
}

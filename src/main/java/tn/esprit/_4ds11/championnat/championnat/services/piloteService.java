package tn.esprit._4ds11.championnat.championnat.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit._4ds11.championnat.championnat.entities.Pilote;
import tn.esprit._4ds11.championnat.championnat.repository.piloteRepository;
import tn.esprit._4ds11.championnat.championnat.repository.positionRepository;

@Service
@RequiredArgsConstructor
public class piloteService implements IPiloteService{


    private final piloteRepository pr;
    private final positionRepository posr;

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
}

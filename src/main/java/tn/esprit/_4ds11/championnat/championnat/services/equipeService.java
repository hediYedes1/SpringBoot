package tn.esprit._4ds11.championnat.championnat.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit._4ds11.championnat.championnat.entities.Equipe;
import tn.esprit._4ds11.championnat.championnat.repository.contratRepository;
import tn.esprit._4ds11.championnat.championnat.repository.equipeRepository;
import tn.esprit._4ds11.championnat.championnat.repository.piloteRepository;

@Service
@RequiredArgsConstructor
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
}

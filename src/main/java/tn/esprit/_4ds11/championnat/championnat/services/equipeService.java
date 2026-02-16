package tn.esprit._4ds11.championnat.championnat.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit._4ds11.championnat.championnat.entities.Equipe;
import tn.esprit._4ds11.championnat.championnat.repository.equipeRepository;

@Service
public class equipeService implements IEquipeService{

    @Autowired
    private equipeRepository er;

    @Override
    public Equipe ajouterEquipe(Equipe equipe) {
        return er.save(equipe);
    }
}

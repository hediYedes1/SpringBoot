package tn.esprit._4ds11.championnat.championnat.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit._4ds11.championnat.championnat.entities.Pilote;
import tn.esprit._4ds11.championnat.championnat.repository.piloteRepository;

@Service
public class piloteService implements IPiloteService{

    @Autowired
    private piloteRepository pr;
    
    @Override
    public Pilote ajouterPilote(Pilote pilote) {
        return pr.save(pilote);
    }
}

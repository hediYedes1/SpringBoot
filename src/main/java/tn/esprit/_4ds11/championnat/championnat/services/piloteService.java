package tn.esprit._4ds11.championnat.championnat.services;

import tn.esprit._4ds11.championnat.championnat.entities.Pilote;
import tn.esprit._4ds11.championnat.championnat.repository.piloteRepository;

public class piloteService implements IPiloteService{

    private piloteRepository pr;
    @Override
    public Pilote ajouterPilote(Pilote pilote) {
        return pr.save(pilote);
    }
}

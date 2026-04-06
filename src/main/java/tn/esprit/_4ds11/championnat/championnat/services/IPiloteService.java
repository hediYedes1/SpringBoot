package tn.esprit._4ds11.championnat.championnat.services;

import tn.esprit._4ds11.championnat.championnat.entities.Pilote;

public interface IPiloteService {

    Pilote ajouterPilote(Pilote pilote);
    Pilote addPiloteEtPositionAssocie(Pilote p);
    void mettreAJourPointsEtClassementPilotesFinAnnee();
}

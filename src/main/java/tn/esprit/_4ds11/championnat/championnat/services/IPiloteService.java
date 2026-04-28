package tn.esprit._4ds11.championnat.championnat.services;

import tn.esprit._4ds11.championnat.championnat.dto.PiloteDto;
import tn.esprit._4ds11.championnat.championnat.entities.Pilote;

import java.util.List;

public interface IPiloteService {

    List<PiloteDto> listeWinners(Integer annee);

    Pilote ajouterPilote(Pilote pilote);
    Pilote addPiloteEtPositionAssocie(Pilote p);
    void mettreAJourPointsEtClassementPilotesFinAnnee();
}

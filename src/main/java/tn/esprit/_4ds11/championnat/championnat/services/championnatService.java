package tn.esprit._4ds11.championnat.championnat.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit._4ds11.championnat.championnat.entities.Championnat;
import tn.esprit._4ds11.championnat.championnat.entities.DetailChampionnat;
import tn.esprit._4ds11.championnat.championnat.repository.championnatRepository;
import tn.esprit._4ds11.championnat.championnat.repository.detailChampionnatRepository;

@Service
@RequiredArgsConstructor
public class championnatService implements IChampionnatService{

    private final championnatRepository cr;
    private final detailChampionnatRepository dcr;

    @Override
    public Championnat ajouterChampionnat(Championnat championnat)
    {
        return cr.save(championnat);
    }

    @Override
    public Championnat affecterDetailChampionnatToChampionnat(DetailChampionnat dt, Long idChampionnat) {
        Championnat championnat = cr.findById(idChampionnat)
                .orElseThrow(() -> new RuntimeException("Championnat introuvable"));

        DetailChampionnat detailChampionnat = dcr.save(dt);
        championnat.setDetailChampionnat(detailChampionnat);
        return cr.save(championnat);
    }
}

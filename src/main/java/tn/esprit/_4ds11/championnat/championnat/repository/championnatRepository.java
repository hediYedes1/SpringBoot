package tn.esprit._4ds11.championnat.championnat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit._4ds11.championnat.championnat.entities.Championnat;

import java.util.List;

public interface championnatRepository extends JpaRepository<Championnat,Long> {

    List<Championnat> findByAnneeGreaterThan(Integer annee);
}

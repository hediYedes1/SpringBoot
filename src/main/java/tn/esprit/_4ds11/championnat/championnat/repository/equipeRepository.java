package tn.esprit._4ds11.championnat.championnat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit._4ds11.championnat.championnat.entities.Equipe;

import java.util.Optional;

public interface equipeRepository extends JpaRepository<Equipe,Long> {

    Optional<Equipe> findByLibelle(String libelle);
}

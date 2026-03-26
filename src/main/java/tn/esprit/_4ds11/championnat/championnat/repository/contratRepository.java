package tn.esprit._4ds11.championnat.championnat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit._4ds11.championnat.championnat.entities.Contrat;

public interface contratRepository extends JpaRepository<Contrat,Long> {
}

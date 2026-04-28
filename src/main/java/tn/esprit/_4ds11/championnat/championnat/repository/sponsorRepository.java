package tn.esprit._4ds11.championnat.championnat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit._4ds11.championnat.championnat.entities.Sponsor;

import java.util.Optional;

public interface sponsorRepository extends JpaRepository <Sponsor, Long> {
    Optional<Sponsor> findByNomAndPays(String nom, String pays);
}

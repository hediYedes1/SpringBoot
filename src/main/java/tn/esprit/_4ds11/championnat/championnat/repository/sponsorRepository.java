package tn.esprit._4ds11.championnat.championnat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import tn.esprit._4ds11.championnat.championnat.entities.Sponsor;

import java.util.Optional;

public interface sponsorRepository extends JpaRepository<Sponsor, Long> {
    Optional<Sponsor> findByNomAndPays(String nom, String pays);

    // JPQL equivalent de DistinctFirstBy + IgnoreCase.
    @Query("SELECT s FROM Sponsor s " +
            "WHERE LOWER(s.nom) = LOWER(:nom) " +
            "AND s.idSponsor = (" +
            "SELECT MIN(s2.idSponsor) FROM Sponsor s2 WHERE LOWER(s2.nom) = LOWER(:nom)" +
            ")")
    Optional<Sponsor> findSponsorDistinctParNomJPQL(@Param("nom") String nom);
}

package tn.esprit._4ds11.championnat.championnat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import tn.esprit._4ds11.championnat.championnat.entities.Equipe;

import java.util.Optional;

public interface equipeRepository extends JpaRepository<Equipe, Long> {

    Optional<Equipe> findByLibelle(String libelle);

    // JPQL equivalent de ExistsBy + IgnoreCase.
    @Query("SELECT CASE WHEN COUNT(e) > 0 THEN true ELSE false END " +
            "FROM Equipe e WHERE LOWER(e.libelle) = LOWER(:libelle)")
    boolean existsByLibelleIgnoreCaseJPQL(@Param("libelle") String libelle);
}

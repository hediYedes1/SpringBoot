package tn.esprit._4ds11.championnat.championnat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import tn.esprit._4ds11.championnat.championnat.entities.Categorie;
import tn.esprit._4ds11.championnat.championnat.entities.Championnat;

import java.util.List;

public interface championnatRepository extends JpaRepository<Championnat, Long> {

    List<Championnat> findByAnneeGreaterThan(Integer annee);

    // JPQL Distinct + categorie.
    @Query("SELECT DISTINCT ch FROM Championnat ch WHERE ch.categorie = :categorie")
    List<Championnat> findDistinctByCategorieJPQL(@Param("categorie") Categorie categorie);

    // JPQL Distinct + navigation relationnelle par equipe.
    @Query("SELECT DISTINCT ch FROM Championnat ch " +
            "JOIN ch.courses co " +
            "JOIN co.positions pos " +
            "JOIN pos.pilote p " +
            "JOIN p.equipe e " +
            "WHERE e.libelle = :libelleEquipe")
    List<Championnat> findChampionnatsByEquipeJPQL(@Param("libelleEquipe") String libelleEquipe);

    // JPQL IsNotNull via "IS NOT NULL".
    @Query("SELECT DISTINCT ch FROM Championnat ch " +
            "JOIN ch.courses co " +
            "JOIN co.positions pos " +
            "JOIN pos.pilote p " +
            "JOIN p.equipe e " +
            "WHERE e.libelle = :libelleEquipe " +
            "AND ch.detailChampionnat.description IS NOT NULL")
    List<Championnat> findChampionnatsByEquipeAvecDescriptionNonNulleJPQL(
            @Param("libelleEquipe") String libelleEquipe
    );

    // JPQL equivalent de StartingWith + ContainingIgnoreCase.
    @Query("SELECT ch FROM Championnat ch " +
            "WHERE ch.libellec LIKE CONCAT(:prefix, '%') " +
            "AND LOWER(ch.detailChampionnat.description) LIKE LOWER(CONCAT('%', :mot, '%'))")
    List<Championnat> findByNomPrefixAndDescriptionContainsJPQL(
            @Param("prefix") String prefix,
            @Param("mot") String mot
    );

    // JPQL delete IN.
    @Modifying
    @Query("DELETE FROM Championnat ch WHERE ch.categorie IN :categories")
    long deleteChampionnatsByCategoriesJPQL(@Param("categories") List<Categorie> categories);
}

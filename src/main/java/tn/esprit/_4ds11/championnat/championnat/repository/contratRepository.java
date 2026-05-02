package tn.esprit._4ds11.championnat.championnat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import tn.esprit._4ds11.championnat.championnat.entities.Contrat;

import java.util.List;

public interface contratRepository extends JpaRepository<Contrat, Long> {

    @Query("SELECT c.annee, SUM(c.montant) FROM Contrat c " +
            "JOIN c.equipe e " +
            "WHERE e.libelle = :libelleEquipe AND c.archived = false " +
            "GROUP BY c.annee")
    List<Object[]> findHistoriqueContratsByEquipe(@Param("libelleEquipe") String libelleEquipe);

    // JPQL equivalent de ByEquipeLibelle.
    @Query("SELECT c FROM Contrat c WHERE c.equipe.libelle = :libelleEquipe")
    List<Contrat> findContratsByEquipeJPQL(@Param("libelleEquipe") String libelleEquipe);

    // JPQL equivalent de And.
    @Query("SELECT c FROM Contrat c WHERE c.equipe.libelle = :libelleEquipe AND c.sponsor.nom = :nomSponsor")
    List<Contrat> findContratsByEquipeAndSponsorJPQL(
            @Param("libelleEquipe") String libelleEquipe,
            @Param("nomSponsor") String nomSponsor
    );

    // JPQL equivalent de ContainingIgnoreCase.
    @Query("SELECT c FROM Contrat c " +
            "WHERE LOWER(c.equipe.libelle) LIKE LOWER(CONCAT('%', :libelleEquipe, '%')) " +
            "AND LOWER(c.sponsor.pays) LIKE LOWER(CONCAT('%', :paysSponsor, '%'))")
    List<Contrat> findContratsByLibelleEquipeAndPaysSponsorJPQL(
            @Param("libelleEquipe") String libelleEquipe,
            @Param("paysSponsor") String paysSponsor
    );

    // JPQL equivalent de OrderBy...Desc.
    @Query("SELECT c FROM Contrat c " +
            "WHERE LOWER(c.equipe.libelle) LIKE LOWER(CONCAT('%', :motCleEquipe, '%')) " +
            "ORDER BY c.sponsor.nom DESC")
    List<Contrat> findContratsByEquipeOrderBySponsorNomDescJPQL(@Param("motCleEquipe") String motCleEquipe);
}

package tn.esprit._4ds11.championnat.championnat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import tn.esprit._4ds11.championnat.championnat.entities.Contrat;

import java.util.List;

public interface contratRepository extends JpaRepository<Contrat,Long> {
    // ContratRepository.java
    @Query("SELECT c.annee, SUM(c.montant) FROM Contrat c " +
            "JOIN c.equipe e " +
            "WHERE e.libelle = :libelleEquipe AND c.archived = false " +
            "GROUP BY c.annee")
    List<Object[]> findHistoriqueContratsByEquipe(@Param("libelleEquipe") String libelleEquipe);
}

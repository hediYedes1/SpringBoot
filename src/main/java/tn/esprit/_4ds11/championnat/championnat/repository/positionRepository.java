package tn.esprit._4ds11.championnat.championnat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import tn.esprit._4ds11.championnat.championnat.entities.Position;

import java.time.LocalDate;
import java.util.List;

public interface positionRepository extends JpaRepository<Position, Long> {

    // PositionRepository.java
    @Query("SELECT SUM(pos.nbPoints) FROM Position pos " +
            "JOIN pos.pilote p " +
            "JOIN p.equipe e " +
            "JOIN pos.course co " +
            "JOIN co.championnats ch " +
            "WHERE e.idEquipe = :idEquipe " +
            "AND ch.idChampionnat = :idChampionnat " +
            "AND ch.annee = :annee")
    Integer sumPointsPilotesByEquipeAndChampionnatAndAnnee(
            @Param("idEquipe") Long idEquipe,
            @Param("idChampionnat") Long idChampionnat,
            @Param("annee") Integer annee
    );

    // PositionRepository.java
    @Query("SELECT AVG(pos.classement) FROM Position pos " +
            "JOIN pos.pilote p " +
            "JOIN pos.course co " +
            "WHERE p.libelleP = :libelleP " +
            "AND co.dateCourse BETWEEN :startDate AND :endDate")
    Float avgClassementByPiloteAndDateBetween(
            @Param("libelleP") String libelleP,
            @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate
    );

    @Query("SELECT p, COALESCE(SUM(pos.nbPoints), 0) AS totalPoints " +
            "FROM Position pos " +
            "JOIN pos.pilote p " +
            "JOIN pos.course co " +
            "JOIN co.championnats ch " +
            "WHERE ch.idChampionnat = :idChampionnat " +
            "GROUP BY p.idPilote " +
            "ORDER BY totalPoints DESC, p.idPilote ASC")

    List<Object[]> findPilotesClassementByChampionnat(
            @Param("idChampionnat") Long idChampionnat,
            Pageable pageable
    );
}

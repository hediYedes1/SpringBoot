package tn.esprit._4ds11.championnat.championnat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit._4ds11.championnat.championnat.entities.DetailChampionnat;

public interface detailChampionnatRepository extends JpaRepository<DetailChampionnat, String> {
}

package tn.esprit._4ds11.championnat.championnat.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ContratDto {

    private Long idContrat;
    private Float montant;
    private String annee;
    private String libelleEquipe;
    private String nomSponsor;
}

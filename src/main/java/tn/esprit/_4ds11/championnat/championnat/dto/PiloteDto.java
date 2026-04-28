package tn.esprit._4ds11.championnat.championnat.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PiloteDto {

    private String libelleP;
    private Integer nbpointsTotal;
    private String libelleC;

    public String getLibelleP() {
        return libelleP;
    }

    public void setLibelleP(String libelleP) {
        this.libelleP = libelleP;
    }

    public Integer getNbpointsTotal() {
        return nbpointsTotal;
    }

    public void setNbpointsTotal(Integer nbpointsTotal) {
        this.nbpointsTotal = nbpointsTotal;
    }

    public String getLibelleC() {
        return libelleC;
    }

    public void setLibelleC(String libelleC) {
        this.libelleC = libelleC;
    }
}

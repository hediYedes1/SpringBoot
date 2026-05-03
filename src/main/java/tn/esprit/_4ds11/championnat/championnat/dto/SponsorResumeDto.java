package tn.esprit._4ds11.championnat.championnat.dto;

public class SponsorResumeDto {

    private Long sponsorId;
    private String sponsorName;
    private String sponsorCountry;
    private Float yearlyBudget;
    private Boolean contractBlocked;

    public Long getSponsorId() {
        return sponsorId;
    }

    public void setSponsorId(Long sponsorId) {
        this.sponsorId = sponsorId;
    }

    public String getSponsorName() {
        return sponsorName;
    }

    public void setSponsorName(String sponsorName) {
        this.sponsorName = sponsorName;
    }

    public String getSponsorCountry() {
        return sponsorCountry;
    }

    public void setSponsorCountry(String sponsorCountry) {
        this.sponsorCountry = sponsorCountry;
    }

    public Float getYearlyBudget() {
        return yearlyBudget;
    }

    public void setYearlyBudget(Float yearlyBudget) {
        this.yearlyBudget = yearlyBudget;
    }

    public Boolean getContractBlocked() {
        return contractBlocked;
    }

    public void setContractBlocked(Boolean contractBlocked) {
        this.contractBlocked = contractBlocked;
    }
}

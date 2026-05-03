package tn.esprit._4ds11.championnat.championnat.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import tn.esprit._4ds11.championnat.championnat.dto.SponsorResumeDto;
import tn.esprit._4ds11.championnat.championnat.entities.Sponsor;

@Mapper(componentModel = "spring")
public interface SponsorMapper {

    @Mapping(source = "idSponsor", target = "sponsorId")
    @Mapping(source = "nom", target = "sponsorName")
    @Mapping(source = "pays", target = "sponsorCountry")
    @Mapping(source = "budgetAnnuel", target = "yearlyBudget")
    @Mapping(source = "bloquerContrat", target = "contractBlocked")
    SponsorResumeDto toResumeDto(Sponsor sponsor);
}

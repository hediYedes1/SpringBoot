package tn.esprit._4ds11.championnat.championnat.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit._4ds11.championnat.championnat.entities.Sponsor;
import tn.esprit._4ds11.championnat.championnat.services.ISponsorService;

import java.util.List;

@Tag(name = "gestion des sponsors")
@RestController
@AllArgsConstructor
@RequestMapping("/sponsors")
public class sponsorController {

    private final ISponsorService sponsorService;

    @Operation(description = "ajouter sponsor")
    @PostMapping("/add-sponsor")
    public Sponsor ajouterSponsor(@RequestBody Sponsor sponsor) {
        return sponsorService.ajouterSponsor(sponsor);
    }

    @Operation(description = "ajouter une liste des sponsors")
    @PostMapping("/add-sponsors")
    public List<Sponsor> ajouterSponsors(@RequestBody List<Sponsor> sponsors) {
        return sponsorService.ajouterSponsors(sponsors);
    }

    @Operation(description = "modifier un sponsor")
    @PutMapping("/update-sponsor")
    public Sponsor modifierSponsor(@RequestBody Sponsor sponsor) {
        return sponsorService.modifierSponsor(sponsor);
    }

    @Operation(description = "supprimer un sponsor")
    @DeleteMapping("/delete-sponsor/{sponsor-id}")
    public void supprimerSponsor(@PathVariable("sponsor-id") Long idSponsor) {
        sponsorService.supprimerSponsor(idSponsor);
    }

    @Operation(description = "recuperer la liste des sponsors")
    @GetMapping("/list-sponsor")
    public List<Sponsor> listSponsors() {
        return sponsorService.listSponsors();
    }

    @Operation(description = "afficher un sponsor")
    @GetMapping("/display-sponsor/{sponsor-id}")
    public Sponsor recupererSponsor(@PathVariable("sponsor-id") Long idSponsor) {
        return sponsorService.recupererSponsor(idSponsor);
    }

    @Operation(description = "archiver un sponsor")
    @PutMapping("/archiver-sponsor/{idsponsor}")
    public Boolean archiverSponsor(@PathVariable("idsponsor") Long idSponsor) {
        return sponsorService.archiverSponsor(idSponsor);
    }

    @Operation(description = "ajouter un sponsor avec contrats associes")
    @PostMapping("/addSponsorEtContratAssocie")
    @ResponseBody
    public Sponsor addSponsorEtContratAssocie(@RequestBody Sponsor s) {
        return sponsorService.addSponsorEtContratAssocie(s);
    }
}

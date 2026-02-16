package tn.esprit._4ds11.championnat.championnat.controllers;
import org.springframework.web.bind.annotation.*;

import tn.esprit._4ds11.championnat.championnat.entities.Sponsor;
import tn.esprit._4ds11.championnat.championnat.services.sponsorService;

import java.util.List;

@RestController
@RequestMapping("/sponsors")
public class sponsorController {

    private sponsorService sponsorService;

    @PostMapping("/add-sponsor")
    public Sponsor ajouterSponsor(@RequestBody Sponsor sponsor)
    {
        return sponsorService.ajouterSponsor(sponsor);
    }
        @PostMapping("/add-sponsors")
    public List<Sponsor> ajouterSponsors(@RequestBody List<Sponsor> sponsors) {
        return sponsorService.ajouterSponsors(sponsors);
    }
        @PutMapping("/update-sponsor")
    public Sponsor modifierSponsor(@RequestBody Sponsor sponsor) {
        return sponsorService.modifierSponsor(sponsor);
    }
        @DeleteMapping("/delete-sponsor/{sponsor-id}")
    public void supprimerSponsor (@PathVariable("sponsor-id") Long idSponsor)
        {
            sponsorService.supprimerSponsor(idSponsor);
        }
        @GetMapping("/list-sponsor")
    public List<Sponsor> listSponsors()
        {
            return sponsorService.listSponsors();
        }
        @GetMapping("/display-sponsor/{sponsor-id}")
        Sponsor recupererSponsor(@PathVariable("sponsor-id") Long idSponsor)
        {
            return sponsorService.recupererSponsor(idSponsor);
        }
        @PutMapping("/archiver-sponsor/{idsponsor}")
    public Boolean archiverSponsor(@PathVariable("idsponsor") Long idSponsor) {
        return sponsorService.archiverSponsor(idSponsor);
    }
}




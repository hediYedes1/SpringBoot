#Affectation
(parent) equipe 1---1 (child) detailEquipe
Affectation DetailEquipe à Equipe

@Override
public Equipe affecterDetailEquipeToEquipe(DetailEquipe dt, Integer idEquipe) {
Equipe equipe = equipeRepository.findById(idEquipe).get();
// equipe parent dans L'association donc affecter le child au parent
// sauvegarder L'objet detail equipe dans la bd
DetailEquipe detailEquipe = detailEquipeRepository.save(dt);
equipe.setDetailEquipe(detailEquipe);
// sauvegarder le nouveau état de l'objet avec le detail affecté
equipeRepository.save(equipe);
return equipe;
}


// http://Localhost:8089/Kaddem/equipe/affecterDetailEquipeToEquipe/1
/* cette méthode permet d'offecter un détail équipe à son équipe */
@PutMapping("/affecDetailEquipeToEquipe/{idEquipe}")
@ResponseBody
public Equipe affecterDetailEquipeToEquipe(@RequestBody DetailEquipe dt,
@PathVariable("idEquipe") Integer idEquipe) {
Equipe equipe = equipeService.affecterDetailEquipeToEquipe(dt, idEquipe);
return equipe;

}


OneToMany
(parent) Contrat *---1 (child) Etudiant

Ajout Etudiant avec les contrats associes

@Override
public Etudiant addEtudiantEtContratAssocie(Etudiant e) {
// Sauvegarder l'objet fils etudiant
Etudiant etudiant = etudiantRepository.save(e);
// parcourir la liste des contrats (Parent)
etudiant.getContrats().forEach(contrat ->

// affecter le child Etudiant au parent Contrat et sauvegarder le Parent
{contrat.setEtudiant(etudiant);
contratRepository.save(contrat);

return etudiant;
};
}

// http://Localhost:8089/Kaddem/etudiant/addEtudiantEtContratAssocie
@PostMapping(Ov"/addEtudiantEtContratAssocie")
@ResponseBody
public Etudiant addEtudiantEtContratAssocie(@RequestBody Etudiant e) {
Etudiant etudiant= etudiantService.addEtudiantEtContratAssocie(e);
return etudiant;

}



les deux existe deja maintenant 

Affectation Département à Etudiant

@override
public void assignEtudiantToDepartement(Integer etudiantId, Integer departementId) {
Etudiant e = etudiantRepository.findById(etudiantId).get();
Departement d= departementRepository.findById(departementId).get();
// affecter le child departement au parent Etudiant
e.setDepartement(d);
// sauvegarder le nouveau état de l'objet parent avec le département affecté
etudiantRepository.save(e);

}

// http://Localhost:8089/Kaddem/etudiant/assignEtudiantToDepartement/1/1
@PutMapping(Qv"/assignEtudiantToDepartement/{etudiantId}/{departementId}")
@ResponseBody
public void assignEtudiantToDepartement(@PathVariable("etudiantId") Integer etudiantId
,@PathVariable("departementId") Integer departementId) {
etudiantService.assignEtudiantToDepartement(etudiantId, departementId);

}

manytomany
les deux exitent dans la base de donnee
(parent) etudiant *---*(child)equipe

@Override
public Etudiant affecterEquipeToEtudiant(Integer equipeId, Integer etudiantId) {
Equipe equipe=equipeRepository. findById(equipeId).get();
Etudiant etudiant = etudiantRepository. findById(etudiantId) .get();
// initialiser une liste d'équipe vide
List<Equipe> equipesMisesAjour = new ArrayList<>();
// récupérer les equipes deja présentes dans la base
if(etudiant.getEquipes() != null) {
equipesMisesAjour=etudiant.getEquipes();
}
// ajouter la nouvelle équipe à affecter
equipesMisesAjour.add(equipe);
// mettre à jour la liste des équipes
etudiant.setEquipes(equipesMisesAjour);
// Sauvegarder l'objet parent etudiant avec la liste des équipes mise à jour
etudiantRepository.save(etudiant);
return etudiant;

}


// http://Localhost:8089/Kaddem/etudiant/affecterEquipeToEtudiant/1/1
@PutMapping(@v"/affecterEquipeToEtudiant/{equipeId}/{etudiantId}")
@aResponseBody
public void affecterEquipeToEtudiant(@PathVariable("equipeId") Integer equipeId,
@PathVariable("etudiantId") Integer etudiantId) {
etudiantService.affecterEquipeToEtudiant(equipeId,etudiantId);

}


####################    AOP

execution( [modificateur]  [retour]  [package].[classe].[méthode]([params]) )
               ↑               ↑         ↑         ↑        ↑          ↑
           OPTIONNEL      OBLIGATOIRE  OPTIONNEL OPTIONNEL OBLIGATOIRE OBLIGATOIRE
(peut manquer)  (mais * si "tous")


// 1. Tous les services, toutes les méthodes
"execution(* tn.esprit._4ds11.championnat.championnat.services.*.*(..))"

// 2. Seulement les méthodes public du piloteService
"execution(public * tn.esprit._4ds11.championnat.championnat.services.piloteService.*(..))"

// 3. Seulement les méthodes qui retournent un PiloteDto
"execution(PiloteDto tn.esprit._4ds11.championnat.championnat.services.*.*(..))"

// 4. Seulement les méthodes qui commencent par "ajouter"
"execution(* tn.esprit._4ds11.championnat.championnat.services.*.ajouter*(..))"

// 5. Seulement les méthodes sans paramètre
"execution(* tn.esprit._4ds11.championnat.championnat.services.*.*())"

// 6. Seulement les méthodes qui prennent un Long en premier paramètre
"execution(* tn.esprit._4ds11.championnat.championnat.services.*.*(Long, ..))"

// 7. Tout le projet (package + sous-packages)
"execution(* tn.esprit._4ds11.championnat.championnat..*.*(..))"




@Scheduled(cron = "Secondes(0-59) Minutes(0-59) Heures(0-23) Jour(0-31) Mois(0-12/JAN-DEC) JourSemaine(0-7/MON-SUN)")
0 * * * * *           Chaque minute

*/30 * * * * *        Toutes les 30 secondes

0 0 8 * * *           Tous les jours à 8h00

0 30 9 * * MON        Lundi à 9h30

0 0 0 1 * *           1er de chaque mois

0 0 18 * * MON-FRI    Lundi-Vendredi à 18h

0 0 0 1 1 *           1er janvier à minuit


keyword
// championnatRepository

// Distinct + ByCategorie : retourne les championnats uniques selon une catégorie précise.
List<Championnat> findDistinctByCategorie(Categorie categorie);

// Distinct + navigation relationnelle + IsNotNull : championnats d'une équipe
// dont la description (detailChampionnat.description) n'est pas nulle.
List<Championnat> findDistinctByCoursesPositionsPiloteEquipeLibelleAndDetailChampionnatDescriptionIsNotNull(String libelleEquipe);

// StartingWith + Containing + IgnoreCase : libelle commence par un préfixe
// et description contient un mot (sans tenir compte de la casse).
List<Championnat> findByLibellecStartingWithAndDetailChampionnatDescriptionContainingIgnoreCase(String prefix, String mot);

// DeleteBy + In : supprime les championnats dont la catégorie est dans la liste fournie.
// Retourne le nombre de lignes supprimées.
long deleteByCategorieIn(List<Categorie> categories);

// contratRepository

// ByEquipeLibelle : récupère les contrats liés à une équipe (jointure implicite via equipe.libelle).
List<Contrat> findByEquipeLibelle(String libelleEquipe);

// And : combine 2 filtres (équipe + sponsor) dans une seule requête dérivée.
List<Contrat> findByEquipeLibelleAndSponsorNom(String libelleEquipe, String nomSponsor);

// ContainingIgnoreCase : recherche partielle insensible à la casse
// sur libellé équipe et pays sponsor.
List<Contrat> findByEquipeLibelleContainingIgnoreCaseAndSponsorPaysContainingIgnoreCase(String libelleEquipe, String paysSponsor);

// sponsorRepository

// DistinctFirstBy + IgnoreCase : prend une entreprise/sponsor unique par nom sans casse.
// FirstBy limite le résultat au premier match.
Optional<Sponsor> findDistinctFirstByNomIgnoreCase(String nom);

// equipeRepository

// ExistsBy + IgnoreCase : vérifie l'existence d'une équipe par nom (sans casse).
boolean existsByLibelleIgnoreCase(String libelle);

// DistinctBy + ContainingIgnoreCase + OrderBy...Desc : filtre les équipes par mot-clé
// puis trie en ordre décroissant sur le nom du sponsor via les contrats.
List<Equipe> findDistinctByLibelleContainingIgnoreCaseOrderByContratsSponsorNomDesc(String motCle);

// courseRepository

// CountBy + Between : compte les courses entre deux dates incluses.
long countByDateCourseBetween(LocalDate startDate, LocalDate endDate);
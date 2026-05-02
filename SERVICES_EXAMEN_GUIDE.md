# Guide Services - Championnat (Revision Examen)

Ce document est pense pour `CTRL+F` rapide pendant l'examen.

## Index rapide (par besoin)

- `AFFECTATION *--*` -> `championnatService.affecterChampionnatToCourse`
- `AFFECTATION 1--1` -> `championnatService.affecterDetailChampionnatToChampionnat`
- `AFFECTATION 1--* EQUIPE->CONTRAT` -> `equipeService.addEquipeEtContratAssocie`
- `AFFECTATION 1--* EQUIPE->PILOTE` -> `equipeService.addEquipeEtPiloteAssocie`
- `AFFECTATION 1--* COURSE->POSITION` -> `courseService.addCourseEtPositionAssocie`
- `AFFECTATION 1--* PILOTE->POSITION` -> `piloteService.addPiloteEtPositionAssocie`
- `AFFECTATION *--1 + *--1 (contrat vers equipe+sponsor)` -> `contratService.ajoutContratEtAffecterASponsorEtEquipe`
- `AFFECTATION 1--* SPONSOR->CONTRAT` -> `sponsorService.addSponsorEtContratAssocie`

---

## Relations JPA du projet (vue globale)

- `Championnat 1--1 DetailChampionnat`
- `Championnat *--* Course`
- `Course 1--* Position`
- `Pilote 1--* Position`
- `Equipe 1--* Pilote`
- `Equipe 1--* Contrat`
- `Sponsor 1--* Contrat`
- `Contrat *--1 Equipe` et `Contrat *--1 Sponsor`
- `Position *--1 Course` et `Position *--1 Pilote`

---

## SERVICE: championnatService

**Entite principale:** `Championnat`  
**Relations manipulees:** `Championnat<->DetailChampionnat (1--1)`, `Championnat<->Course (*--*)`

**Methodes:**

- `Championnat ajouterChampionnat(Championnat championnat)`
- `Championnat affecterDetailChampionnatToChampionnat(DetailChampionnat dt, Long idChampionnat)`
  - Cas examen: affectation `1--1`
- `void affecterChampionnatToCourse(Long idChampionnat, Long idCourse)`
  - Cas examen: affectation `*--*`
- `HashMap<String, Float> historiqueContratsEquipe(String libelleEquipe)`
  - Historique montant/an par equipe (via contrats)
- `List<Championnat> listerChampionnatsParCategorie(Categorie categorie)` (JPQL)
- `List<Championnat> listerChampionnatsParEquipe(String libelleEquipe)` (JPQL)
- `List<Championnat> listerChampionnatsParEquipeAvecDescriptionNonNulle(String libelleEquipe)` (JPQL)
- `List<Championnat> listerChampionnatsParNomEtMotDescription(String prefix, String mot)` (JPQL)
- `long supprimerChampionnatsParCategories(List<Categorie> categories)` (JPQL DELETE)

**Mots cle CTRL+F utiles:**  
`affecterChampionnatToCourse`, `affecterDetailChampionnatToChampionnat`, `*--*`, `1--1`

---

## SERVICE: equipeService

**Entite principale:** `Equipe`  
**Relations manipulees:** `Equipe->Contrat (1--*)`, `Equipe->Pilote (1--*)`

**Methodes:**

- `Equipe ajouterEquipe(Equipe equipe)`
- `Equipe addEquipeEtContratAssocie(Equipe e)`
  - Cas examen: affectation `1--*`
- `Equipe addEquipeEtPiloteAssocie(Equipe e)`
  - Cas examen: affectation `1--*`
- `void archiverContratsExpireesEtAffichageContratsActifsParEquipe()`
  - Tache planifiee: archive contrats expires + logs des contrats actifs
- `Integer nbPointsParPilotesUneEquipeChampionnatPourUneAnne(Long idEquipe, Long idChampionnat, String annee)`
  - Agregat points via `Position`
- `boolean equipeExisteParNomIgnoreCase(String libelle)` (JPQL)

**Mots cle CTRL+F utiles:**  
`addEquipeEtContratAssocie`, `addEquipeEtPiloteAssocie`, `equipeExisteParNomIgnoreCase`

---

## SERVICE: contratService

**Entite principale:** `Contrat`  
**Relations manipulees:** `Contrat->Equipe (*--1)`, `Contrat->Sponsor (*--1)`

**Methodes:**

- `ContratDto ajoutContratEtAffecterASponsorEtEquipe(Contrat contrat, String libelleEquipe, String nomSponsor, String pays)`
  - Cas examen: affectation d'un enfant vers 2 parents (`*--1` + `*--1`)
- `List<Contrat> listerContratsParEquipe(String libelleEquipe)` (JPQL)
- `List<Contrat> listerContratsParEquipeEtSponsor(String libelleEquipe, String nomSponsor)` (JPQL)
- `List<Contrat> listerContratsParLibelleEquipeEtPaysSponsor(String motEquipe, String motPays)` (JPQL)
- `List<Equipe> listerEquipesParMotCleTrieesParSponsorDesc(String motCleEquipe)` (JPQL + tri desc sponsor)

**Mots cle CTRL+F utiles:**  
`ajoutContratEtAffecterASponsorEtEquipe`, `ContratDto`, `*--1`

---

## SERVICE: courseService

**Entite principale:** `Course`  
**Relations manipulees:** `Course->Position (1--*)`

**Methodes:**

- `Course ajouterCourse(Course course)`
- `Course addCourseEtPositionAssocie(Course c)`
  - Cas examen: affectation `1--*`
- `long compterCoursesEntreDeuxDates(LocalDate startDate, LocalDate endDate)` (JPQL COUNT BETWEEN)

**Mots cle CTRL+F utiles:**  
`addCourseEtPositionAssocie`, `compterCoursesEntreDeuxDates`

---

## SERVICE: piloteService

**Entite principale:** `Pilote`  
**Relations manipulees:** `Pilote->Position (1--*)`, calcul via `Course` et `Championnat`

**Methodes:**

- `List<PiloteDto> listeWinners(Integer annee)`
- `Pilote ajouterPilote(Pilote pilote)`
- `Pilote addPiloteEtPositionAssocie(Pilote p)`
  - Cas examen: affectation `1--*`
- `void mettreAJourPointsEtClassementPilotesFinAnnee()`
  - Tache planifiee: met a jour points/classement des pilotes
- `Float moyennePositionsEntreDeuxDate(LocalDate startDate, LocalDate endDate, String libelleP)`
  - Moyenne classement pilote entre 2 dates

**Mots cle CTRL+F utiles:**  
`addPiloteEtPositionAssocie`, `mettreAJourPointsEtClassementPilotesFinAnnee`, `listeWinners`

---

## SERVICE: sponsorService

**Entite principale:** `Sponsor`  
**Relations manipulees:** `Sponsor->Contrat (1--*)`

**Methodes:**

- `Sponsor ajouterSponsor(Sponsor sponsor)`
- `List<Sponsor> ajouterSponsors(List<Sponsor> sponsors)`
- `Sponsor modifierSponsor(Sponsor sponsor)`
- `void supprimerSponsor(Long idSponsor)`
- `List<Sponsor> listSponsors()`
- `Sponsor recupererSponsor(Long idSponsor)`
- `Boolean archiverSponsor(Long idSponsor)`
- `Sponsor addSponsorEtContratAssocie(Sponsor s)`
  - Cas examen: affectation `1--*`
- `Float pourcentageBudgetAnnuelConsomme(Long idSponsor)`
- `Optional<Sponsor> recupererSponsorDistinctParNom(String nom)` (JPQL)
- `Sponsor updateSponsor(Long idSponsor, Sponsor sponsor)`

**Tache planifiee:**

- `afficherPourcentageBudgetSponsorsLundi9h()`
  - Log pourcentage budget et blocage contrat si depassement

**Mots cle CTRL+F utiles:**  
`addSponsorEtContratAssocie`, `updateSponsor`, `recupererSponsorDistinctParNom`

---

## Requetes d'affectation les plus frequentes (resume ultra-court)

1. `*--*`  
   `championnatService.affecterChampionnatToCourse(idChampionnat, idCourse)`
2. `1--1`  
   `championnatService.affecterDetailChampionnatToChampionnat(detail, idChampionnat)`
3. `1--*` parent + liste enfants  
   `addEquipeEtContratAssocie`, `addEquipeEtPiloteAssocie`, `addCourseEtPositionAssocie`, `addPiloteEtPositionAssocie`, `addSponsorEtContratAssocie`
4. `*--1` enfant vers parent(s) existant(s)  
   `ajoutContratEtAffecterASponsorEtEquipe`


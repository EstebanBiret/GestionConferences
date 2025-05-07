package utcapitole.miage.tp7.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpSession;
import utcapitole.miage.tp7.model.entity.Activite;
import utcapitole.miage.tp7.model.entity.ChoixActivite;
import utcapitole.miage.tp7.model.entity.ChoixThematique;
import utcapitole.miage.tp7.model.entity.Conference;
import utcapitole.miage.tp7.model.entity.Participant;
import utcapitole.miage.tp7.model.entity.Thematique;
import utcapitole.miage.tp7.repository.ChoixActivitesRepo;
import utcapitole.miage.tp7.repository.ChoixThematiquesRepo;
import utcapitole.miage.tp7.service.ActiviteService;
import utcapitole.miage.tp7.service.ChoixActivitesService;
import utcapitole.miage.tp7.service.ChoixThematiquesService;
import utcapitole.miage.tp7.service.ConferenceService;
import utcapitole.miage.tp7.service.ThematiqueService;

/**
 * Controller pour la gestion des conférences
 */
@Controller
@RequestMapping("/conferences")
public class ConferencesController {

    @Autowired
    private ConferenceService conferenceService;

    @Autowired
    private ThematiqueService thematiqueService;

    @Autowired
    private ActiviteService activiteService;

    @Autowired
    private ChoixActivitesService choixActivitesService;

    @Autowired
    private ChoixThematiquesService choixThematiquesService;

    @Autowired
    private ChoixActivitesRepo choixActiviteRepo;

    @Autowired
    private ChoixThematiquesRepo choixThematiqueRepo;

    /**
     * Affiche la liste des conférences.
     * 
     * @param model le modèle
     * @param session la session HTTP
     * @return conference_list.html 
     */
    @GetMapping("/list")
    public String listConferences(Model model, HttpSession session) {
        List<Conference> conferences = conferenceService.findAll();
        model.addAttribute("conferences", conferences);

        //si le participant est connecté, on lui signale ses conférences inscrites
        Participant participant = (Participant) session.getAttribute("participant");
        if (participant != null) {

            //on récupère les IDs des conférences auxquelles le participant est inscrit
            List<Integer> conferenceIdsInscrites = participant.getConferences()
            .stream()
            .map(Conference::getCodeConf)
            .toList();

            model.addAttribute("conferenceIdsInscrites", conferenceIdsInscrites);
        }
        return "conference_list";
    }

    /**
     * Affiche le formulaire de création d'une nouvelle conférence.
     * 
     * @param model le modèle
     * @return conference_form.html
     */
    @GetMapping("/new")
    public String newConferenceForm(Model model) {
        List<Thematique> thematiques = thematiqueService.findAll();
        List<Activite> activites = activiteService.findAll();
        model.addAttribute("thematiques", thematiques);
        model.addAttribute("activites", activites);
        return "conference_form";
    }

    /**
     * Insère une nouvelle conférence dans la base de données.
     * 
     * @param titreConf le titre de la conférence
     * @param numEditionConf le numéro d'édition de la conférence
     * @param dtDebutConf la date de début de la conférence
     * @param dtFinConf la date de fin de la conférence
     * @param urlSiteWebConf l'URL du site web de la conférence
     * @param fraisInscriptionConf les frais d'inscription de la conférence
     * @param thematiques les thématiques de la conférence
     * @param activites les activités de la conférence
     * @return redirect:/conferences/confirmation
     * @throws IOException 
     */
    @PostMapping("/insert")
    public String insertConference(
            @RequestParam String titreConf,
            @RequestParam Integer numEditionConf,
            @RequestParam String dtDebutConf,
            @RequestParam String dtFinConf,
            @RequestParam String urlSiteWebConf,
            @RequestParam Integer fraisInscriptionConf,
            @RequestParam List<Integer> thematiques,
            @RequestParam List<Integer> activites
    ) throws IOException {

        //on convertit les activités et les thématiques en objets
        ArrayList<Activite> activitesList = new ArrayList<>();
        ArrayList<Thematique> thematiquesList = new ArrayList<>();

        for(int a : activites) {
            System.out.println("Activite : " + a);
            activitesList.add(activiteService.findById(a));
        }
        for(int t : thematiques) {
            System.out.println("Thématique : " + t);
            thematiquesList.add(thematiqueService.findById(t));
        }
        Conference conference = new Conference(titreConf, numEditionConf, dtDebutConf, dtFinConf, urlSiteWebConf, fraisInscriptionConf, thematiquesList, activitesList);
        
        conferenceService.saveConference(conference);
        return "redirect:/conferences/confirmation";
    }

    /**
     * Affiche la page de confirmation de la création d'une conférence.
     * 
     * @param model le modèle.
     * @return conference_confirmation.html
     */
    @GetMapping("/confirmation")
    public String confirmation(Model model) {
        Conference last = conferenceService.getLastConference();
        model.addAttribute("conference", last);
        model.addAttribute("activites", last.getActivites());
        model.addAttribute("thematiques", last.getThematiques());
        return "conference_confirmation";
    }

    /**
     * Permet d'inscrire le participant connecté à une conférence en choisissant une/des activités et une/des thématiques de la conférence.
     * 
     * @param id l'identifiant de la conférence.
     * @param model le modèle.
     * @return inscription_form.html
     */
    @GetMapping("/inscription/{id}")
    public String inscription(@PathVariable int id, Model model, HttpSession session) {
        Conference conference = conferenceService.findById(id);

        //si conférence inexistante
        if(conference == null) {
            model.addAttribute("message", "La conférence demandée n'existe pas.");
            return "error";
        }

        //si le participant n'est pas connecté
        if(session.getAttribute("participant") == null) {
            model.addAttribute("message", "Vous devez être connecté pour vous inscrire à une conférence.");
            return "error";
        }

        //on récupère le participant connecté
        Participant participant = (Participant) session.getAttribute("participant");
        
        //participant déjà inscrit à la conférence
        if(conference.getParticipants().contains(participant)) {
            model.addAttribute("message", "Vous êtes déjà inscrit à cette conférence.");
            return "error";
        }

        model.addAttribute("conference", conference);
        model.addAttribute("thematiques", conference.getThematiques());
        model.addAttribute("activites", conference.getActivites());        
        return "inscription_form";
    }

    /**
     * Permet de confirmer l'inscription d'un participant à une conférence.
     * 
     * @param id l'identifiant de la conférence.
     * @param thematiqueIds liste des identifiants de thématiques choisies.
     * @param activiteIds liste des identifiants d'activités choisies.
     * @param model le modèle.
     * @param session la session HTTP.
     * @return inscription_confirmation.html
     */
    @PostMapping("/inscription/{id}/confirmation")
    public String confirmerInscription(
        @PathVariable int id,
        @RequestParam(required = true) List<Integer> thematiqueIds,
        @RequestParam(required = true) List<Integer> activiteIds,
        Model model,
        HttpSession session
    ) {
        Conference conference = conferenceService.findById(id);

        //si conférence inexistante
        if(conference == null) {
            model.addAttribute("message", "La conférence demandée n'existe pas.");
            return "error";
        }

        //si le participant n'est pas connecté
        if(session.getAttribute("participant") == null) {
            model.addAttribute("message", "Vous devez être connecté pour vous inscrire à une conférence.");
            return "error";
        }

        //on récupère le participant connecté
        Participant participant = (Participant) session.getAttribute("participant");

        //participant déjà inscrit à la conférence
        if(conference.getParticipants().contains(participant)) {
            model.addAttribute("message", "Vous êtes déjà inscrit à cette conférence.");
            return "error";
        }

        //on récupère les thématiques et activités choisies par le participant
        List<Thematique> thematiques = thematiqueService.findAllByIds(thematiqueIds);
        List<Activite> activites = activiteService.findAllByIds(activiteIds);

        //on calcule le prix total
        double prixTotal = activites.stream().mapToDouble(Activite::getPrixActivite).sum();

        model.addAttribute("conference", conference);
        model.addAttribute("thematiques", thematiques);
        model.addAttribute("activites", activites);
        model.addAttribute("prixTotal", prixTotal);

        return "inscription_confirmation";
    }

    /**
     * Permet de valider l'inscription d'un participant à une conférence.
     * 
     * @param id l'identifiant de la conférence.
     * @param thematiqueIds liste des identifiants de thématiques choisies.
     * @param activiteIds liste des identifiants d'activités choisies.
     * @param session la session HTTP.
     * @param model le modèle.
     * 
     * @return redirect:/inscriptions
     */
    @PostMapping("/inscription/{id}/valider")
    public String validerInscription(
        @PathVariable int id,
        @RequestParam List<Integer> thematiqueIds,
        @RequestParam List<Integer> activiteIds,
        HttpSession session,
        Model model
    ) {
        Conference conference = conferenceService.findById(id);

        //si conférence inexistante
        if(conference == null) {
            model.addAttribute("message", "La conférence demandée n'existe pas.");
            return "error";
        }

        //si le participant n'est pas connecté
        if(session.getAttribute("participant") == null) {
            model.addAttribute("message", "Vous devez être connecté pour vous inscrire à une conférence.");
            return "error";
        }

        //on récupère le participant connecté
        Participant participant = (Participant) session.getAttribute("participant");

        //participant déjà inscrit à la conférence
        if(conference.getParticipants().contains(participant)) {
            model.addAttribute("message", "Vous êtes déjà inscrit à cette conférence.");
            return "error";
        }

        //on ajoute le participant à la conférence
        conference.getParticipants().add(participant);
        conferenceService.saveConference(conference);

        List<Thematique> thematiques = thematiqueService.findAllByIds(thematiqueIds);
        List<Activite> activites = activiteService.findAllByIds(activiteIds);

        for (Thematique t : thematiques) {
            ChoixThematique choix = new ChoixThematique(conference, t, participant);
            choixThematiquesService.save(choix);
        }

        for (Activite a : activites) {
            ChoixActivite choix = new ChoixActivite(conference, a, participant);
            choixActivitesService.save(choix);
        }

        return "redirect:/conferences/inscriptions";
    }

    /**
     * Permet de consulter les inscriptions d'un participant connecté.
     */
    @GetMapping("/inscriptions")
    public String inscriptions(Model model, HttpSession session) {
        //si le participant n'est pas connecté
        if(session.getAttribute("participant") == null) {
            model.addAttribute("message", "Vous devez être connecté pour consulter vos inscriptions.");
            return "error";
        }

        //on récupère le participant connecté
        Participant participant = (Participant) session.getAttribute("participant");

        List<Conference> conferences = participant.getConferences();

        Map<Integer, List<Activite>> activitesParConf = new HashMap<>();
        Map<Integer, List<Thematique>> thematiquesParConf = new HashMap<>();
        Map<Integer, Double> prixTotalParConf = new HashMap<>();

        for (Conference conf : conferences) {
            List<ChoixActivite> choixActivites = choixActiviteRepo.findByParticipantAndConference(participant, conf);
            List<Activite> activites = choixActivites.stream().map(ChoixActivite::getActivite).toList();
            activitesParConf.put(conf.getCodeConf(), activites);

            List<ChoixThematique> choixThematiques = choixThematiqueRepo.findByParticipantAndConference(participant, conf);
            List<Thematique> thematiques = choixThematiques.stream().map(ChoixThematique::getThematique).toList();
            thematiquesParConf.put(conf.getCodeConf(), thematiques);

             //calcul du prix total des activités pour chaque conférence
            double prixTotal = activites.stream().mapToDouble(Activite::getPrixActivite).sum();
            prixTotal += conf.getFraisInscriptionConf();
            prixTotalParConf.put(conf.getCodeConf(), prixTotal);
        }

        model.addAttribute("conferences", conferences);
        model.addAttribute("activitesParConf", activitesParConf);
        model.addAttribute("thematiquesParConf", thematiquesParConf);
        model.addAttribute("prixTotalParConf", prixTotalParConf);

        return "inscription_list";
    }
}
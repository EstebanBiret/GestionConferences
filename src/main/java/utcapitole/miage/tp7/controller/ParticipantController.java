package utcapitole.miage.tp7.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.servlet.http.HttpSession;
import utcapitole.miage.tp7.model.entity.Participant;
import utcapitole.miage.tp7.model.entity.Statut;
import utcapitole.miage.tp7.service.ParticipantService;
import utcapitole.miage.tp7.service.StatutService;

/**
 * Controller pour la gestion des participants
 */
@Controller
@RequestMapping("/participants")
public class ParticipantController {
    
    @Autowired
    private ParticipantService participantService;

    @Autowired
    private StatutService statutService;

    /**
     * Affiche la liste des participants.
     * 
     * @param model le modèle
     * @return participant_list.html
     */
    @GetMapping("/list")
    public String listParticipants(Model model) {
        List<Participant> participants = participantService.findAll();
        model.addAttribute("participants", participants);
        return "participant_list";
    }

    /**
     * Affiche le formulaire de création d'un participant.
     * 
     * @param model le modèle
     * @return participant_form.html
     */
    @GetMapping("/new")
    public String newParticipantForm(Model model) {
        model.addAttribute("statuts", statutService.findAll());
        return "participant_form";
    }

    /**
     * Insère un participant dans la base de données.
     * 
     * @param nomPart le nom du participant
     * @param prenomPart le prénom du participant
     * @param organismePart le nom de l'organisme du participant
     * @param cpPart le code postal du participant
     * @param adrPart l'adresse du participant
     * @param villePart la ville du participant
     * @param paysPart le pays du participant
     * @param emailPart l'email du participant
     * @param statutNom le nom du statut du participant
     * @param password le mot de passe du participant
     * @return redirect:/participants/confirmation
     * @throws IOException
     */
    @GetMapping("/insert")
    public String insertParticipant(
            @RequestParam String nomPart,
            @RequestParam String prenomPart,
            @RequestParam String organismePart,
            @RequestParam Integer cpPart,
            @RequestParam String adrPart,
            @RequestParam String villePart,
            @RequestParam String paysPart,
            @RequestParam String emailPart,
            @RequestParam String statutNom,
            @RequestParam String password
    ) throws IOException {

        if (!participantService.isEmailUnique(emailPart)) {
            return "redirect:/participants/confirmation?error=email";
        }

        String dtInscription = participantService.getCurrentDate();

        Statut statut = statutService.findByNomStatut(statutNom);
        
        Participant participant = new Participant(nomPart, prenomPart, organismePart, cpPart, adrPart, villePart, paysPart, emailPart, dtInscription, statut, password);
        participantService.saveParticipant(participant);
        return "redirect:/participants/confirmation";
    }

    /**
     * Affiche la page de confirmation après la création d'un participant.
     * 
     * @param model le modèle
     * @param error si l'email est déjà utilisé par un autre compte participant
     * @return participant_confirmation.html
     */
    @GetMapping("/confirmation")
    public String confirmation(Model model, @RequestParam(required = false) String error) {
        if (error != null) {
            model.addAttribute("error", "Cet email est déjà utilisé par un autre compte participant !");
        }
        else {
            model.addAttribute("error", null);
        }

        Participant participant = participantService.getLastParticipant();

        model.addAttribute("participant", participant);
        return "participant_confirmation";
    }

    /**
     * Authentifie un participant.
     * 
     * @param email le mail du participant
     * @param password le mot de passe du participant
     * @param session la session HTTP
     * @param redirectAttributes les attributs de redirection
     * @return redirect:/index
     */
    @PostMapping("/connect")
    public String connect(@RequestParam String email, @RequestParam String password, HttpSession session, RedirectAttributes redirectAttributes) {
        Participant participant = participantService.authenticate(email, password);

        if (participant == null) { //email ou mot de passe incorrect

            boolean emailExists = participantService.findAll().stream()
            .anyMatch(p -> p.getEmailPart().equals(email)); //on regarde si l'email existe dans la base de données

            if (!emailExists) {
                redirectAttributes.addFlashAttribute("error", "Aucun compte trouvé avec cet email.");
            } else {
                redirectAttributes.addFlashAttribute("error", "Mot de passe incorrect.");
            }
            return "redirect:/index";
        }
        session.setAttribute("participant", participant);
        return "redirect:/index";
    }

    /**
     * Déconnecte un participant.
     * 
     * @param session la session HTTP
     * @return redirect:/index
     */
    @GetMapping("/deconnect")
    public String deconnect(HttpSession session) {
        session.removeAttribute("participant");
        return "redirect:/index";
    }

    /**
     * Affiche le formulaire d'édition d'un participant.
     * 
     * @param id l'identifiant du participant
     * @param model le modèle
     * @return participant_edit.html
     */
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable int id, Model model) {
        Participant participant = participantService.findById(id);
            
        if (participant == null) {
            model.addAttribute("message", "Le participant demandé n'existe pas.");
            return "error";
        }
        model.addAttribute("participant", participant);
        model.addAttribute("statuts", statutService.findAll());
        return "participant_edit";
    }

    /**
     * Met à jour un participant dans la base de données.
     * 
     * @param id l'identifiant du participant
     * @param nomPart le nom du participant
     * @param prenomPart le prénom du participant
     * @param organismePart le nom de l'organisme du participant
     * @param cpPart le code postal du participant
     * @param adrPart l'adresse du participant
     * @param villePart la ville du participant
     * @param paysPart le pays du participant
     * @param password le mot de passe du participant
     * @param statutNom le nom du statut du participant
     * @return redirect:/participants/confirmation_modif/{id}
     * @throws IOException 
     */
    @GetMapping("/update")
    public String updateParticipant(
            @RequestParam Integer id,
            @RequestParam String nomPart,
            @RequestParam String prenomPart,
            @RequestParam String organismePart,
            @RequestParam Integer cpPart,
            @RequestParam String adrPart,
            @RequestParam String villePart,
            @RequestParam String paysPart,
            @RequestParam String password,
            @RequestParam String statutNom
    ) throws IOException {
        Participant updatedParticipant = participantService.updateParticipant(id, nomPart, prenomPart, organismePart, cpPart, adrPart, villePart, paysPart, password, statutNom);
        return "redirect:/participants/confirmation_modif/" + updatedParticipant.getCodeParticipant();
    }

    /**
     * Affiche la page de confirmation après la modification d'un participant.
     * 
     * @param id l'identifiant du participant
     * @param model le modèle
     * @return participant_confirmation_modif.html
     */
    @GetMapping("/confirmation_modif/{id}")
    public String confirmationModif(@PathVariable int id, Model model) {
        Participant participant = participantService.findById(id);
        if (participant == null) {
            model.addAttribute("message", "Le participant demandé n'existe pas.");
            return "error";
        }

        model.addAttribute("participant", participant);
        return "participant_confirmation_modif";
    }
}
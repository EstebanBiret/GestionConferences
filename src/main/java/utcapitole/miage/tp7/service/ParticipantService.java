package utcapitole.miage.tp7.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import utcapitole.miage.tp7.model.entity.Participant;
import utcapitole.miage.tp7.model.entity.Statut;
import utcapitole.miage.tp7.repository.ParticipantRepo;

/**
 * Service pour gérer les opérations liées aux participants.
 */
@Service
public class ParticipantService {

    @Autowired
    private ParticipantRepo participantRepo;

    @Autowired
    private StatutService statutService;

    /**
     * Vérifie si l'email du participant est unique.
     * 
     * @param email L'email à vérifier.
     * @return true si l'email est unique, false sinon.
     */
    public boolean isEmailUnique(String email) {
        return participantRepo.findByEmailPart(email) == null;
    }

    /**
     * Enregistre un participant dans la base de données.
     * 
     * @param participant Le participant à enregistrer.
     * @return Le participant enregistré.
     */
    public Participant saveParticipant(Participant participant) {
        return participantRepo.save(participant);
    }

    /**
     * Met à jour les informations d'un participant existant.
     * 
     * @param id L'ID du participant.
     * @param nomPart Le nom du participant.
     * @param prenomPart Le prénom du participant.
     * @param organismePart L'organisme du participant.
     * @param cpPart Le code postal du participant.
     * @param adrPart L'adresse du participant.
     * @param villePart La ville du participant.
     * @param paysPart Le pays du participant.
     * @param password Le mot de passe du participant.
     * @param statutNom Le nom du statut du participant.
     * @throws IllegalArgumentException Si le participant n'est pas trouvé.
     * @return Le participant mis à jour.
     */
    public Participant updateParticipant(Integer id, String nomPart, String prenomPart, String organismePart, 
                                          Integer cpPart, String adrPart, String villePart, String paysPart, 
                                          String password, String statutNom) {

        Participant participant = findById(id);
        if (participant == null) {
            throw new IllegalArgumentException("Participant non trouvé avec l'ID : " + id);
        }

        participant.setNomPart(nomPart);
        participant.setPrenomPart(prenomPart);
        participant.setOrganismePart(organismePart);
        participant.setCpPart(cpPart);
        participant.setAdrPart(adrPart);
        participant.setVillePart(villePart);
        participant.setPaysPart(paysPart);
        participant.setPassword(password);

        //on récupère le statut par son nom, puis on l'affecte au participant
        Statut statut = statutService.findByNomStatut(statutNom);
        participant.setStatut(statut);

        return participantRepo.save(participant);
    }

    /**
     * Récupère tous les participants de la base de données.
     * 
     * @return Une liste de participants.
     */
    public List<Participant> findAll() {
        return participantRepo.findAll();
    }

    /**
     * Récupère un participant par son ID.
     * 
     * @param id L'ID du participant.
     * @return Le participant correspondant à l'ID.
     */
    public Participant findById(int id) {
        return participantRepo.findById(id).orElse(null);
    }

    /**
     * Récupère le dernier participant enregistré dans la base de données.
     * 
     * @return Le dernier participant ou null si aucun participant n'est trouvé.
     */
    public Participant getLastParticipant() {
        List<Participant> participants = participantRepo.findAll();
        if (participants.isEmpty()) return null;
        return participants.get(participants.size() - 1);
    }

    /**
     * Authentifie un participant en vérifiant son email et son mot de passe.
     * 
     * @param email L'email du participant.
     * @param password Le mot de passe du participant.
     * @return Le participant authentifié ou null si l'authentification échoue.
     */
    public Participant authenticate(String email, String password) {
        Participant participant = participantRepo.findByEmailPart(email);
        if (participant == null) return null;
        if (!participant.getPassword().equals(password)) return null;
        return participant;
    }

    /**
     * Récupère la date actuelle au format "dd-MM-yyyy".
     *
     * @return La date actuelle sous forme de chaîne de caractères.
     */
    public String getCurrentDate() {
        java.util.Date date = new java.util.Date();
        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("dd-MM-yyyy");
        return sdf.format(date);
    }
}
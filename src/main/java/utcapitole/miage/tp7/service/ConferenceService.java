package utcapitole.miage.tp7.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import utcapitole.miage.tp7.model.entity.Conference;
import utcapitole.miage.tp7.repository.ConferenceRepo;

/**
 * Service pour gérer les opérations liées aux conférences.
 */
@Service
public class ConferenceService {

    @Autowired
    private ConferenceRepo conferenceRepo;

    /**
     * Sauvegarde une conférence dans la base de données.
     * 
     * @param conference La conférence à sauvegarder.
     * @return La conférence sauvegardée.
     */
    public Conference saveConference(Conference conference) {
        return conferenceRepo.save(conference);
    }

    /**
     * Récupère toutes les conférences de la base de données.
     * 
     * @return Une liste de toutes les conférences.
     */
    public List<Conference> findAll() {
        return conferenceRepo.findAll();
    }

    /**
     * Récupère une conférence par son ID.
     * 
     * @param id L'ID de la conférence à récupérer.
     * @return La conférence correspondante à l'ID.
     */
    public Conference findById(int id) {
        return conferenceRepo.findById(id).orElse(null);
    }

    /**
     * Récupère la dernière conférence ajoutée à la base de données.
     * 
     * @return La dernière conférence ajoutée, ou null si aucune conférence n'existe.
     */
    public Conference getLastConference() {
        List<Conference> conferences = conferenceRepo.findAll();
        if (conferences.isEmpty()) return null;
        return conferences.get(conferences.size() - 1);
    }
    
}
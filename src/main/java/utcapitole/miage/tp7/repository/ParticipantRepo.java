package utcapitole.miage.tp7.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import utcapitole.miage.tp7.model.entity.Participant;

/**
 * Repository pour les opérations sur les participants.
 */
@Repository
public interface ParticipantRepo extends JpaRepository<Participant, Integer> {
    
    /**
     * Recherche un participant par son adresse email.
     * 
     * @param emailPart L'adresse email du participant.
     * @return Le participant correspondant à l'adresse email, ou null si aucun participant n'est trouvé.
     */
    Participant findByEmailPart(String emailPart);
}
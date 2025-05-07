package utcapitole.miage.tp7.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import utcapitole.miage.tp7.model.entity.ChoixActivite;
import utcapitole.miage.tp7.model.entity.Conference;
import utcapitole.miage.tp7.model.entity.Participant;
import utcapitole.miage.tp7.model.entity.id.ChoixActiviteId;

/**
 * Repository pour les opérations sur les choix d'activités.
 */
@Repository
public interface ChoixActivitesRepo extends JpaRepository<ChoixActivite, ChoixActiviteId> {
    
    List<ChoixActivite> findByParticipant(Participant participant);

    List<ChoixActivite> findByParticipantAndConference(Participant participant, Conference conference);
}
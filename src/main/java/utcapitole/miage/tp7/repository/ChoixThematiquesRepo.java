package utcapitole.miage.tp7.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import utcapitole.miage.tp7.model.entity.ChoixThematique;
import utcapitole.miage.tp7.model.entity.Conference;
import utcapitole.miage.tp7.model.entity.Participant;
import utcapitole.miage.tp7.model.entity.id.ChoixThematiqueId;

/**
 * Repository pour les opérations sur les choix des thématiques.
 */
@Repository
public interface ChoixThematiquesRepo extends JpaRepository<ChoixThematique, ChoixThematiqueId> {

    List<ChoixThematique> findByParticipant(Participant participant);

    List<ChoixThematique> findByParticipantAndConference(Participant participant, Conference conference);
}
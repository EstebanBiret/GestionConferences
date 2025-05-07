package utcapitole.miage.tp7.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import utcapitole.miage.tp7.model.entity.Conference;

/**
 * Repository pour les opérations sur les conférences.
 */
@Repository
public interface ConferenceRepo extends JpaRepository<Conference, Integer> {
}
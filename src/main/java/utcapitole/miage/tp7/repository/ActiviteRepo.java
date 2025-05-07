package utcapitole.miage.tp7.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import utcapitole.miage.tp7.model.entity.Activite;

/**
 * Repository pour les opérations sur les activités.
 */
@Repository
public interface ActiviteRepo extends JpaRepository<Activite, Integer> {

    /**
     * Recherche une activité par son nom.
     */
    Activite findByNomActivite(String nomActivite);
}

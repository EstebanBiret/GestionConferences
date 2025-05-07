package utcapitole.miage.tp7.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import utcapitole.miage.tp7.model.entity.Statut;

/**
 * Repository pour les opérations sur les statuts.
 */
@Repository
public interface StatutRepo extends JpaRepository<Statut, Integer> {

    /**
     * Recherche un statut par son nom.
     * 
     * @param nomStatut Le nom du statut.
     * @return Le statut correspondant au nom, ou null si aucun statut n'est trouvé.
     */
    Statut findByNomStatut(String nomStatut);
}
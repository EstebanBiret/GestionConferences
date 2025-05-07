package utcapitole.miage.tp7.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import utcapitole.miage.tp7.model.entity.Thematique;

/**
 * Repository pour les opérations sur les thématiques.
 */
@Repository
public interface ThematiqueRepo extends JpaRepository<Thematique, Integer> {

    /**
     * Recherche une thématique par son nom.
     * 
     * @param nomThematique Le nom de la thématique.
     * @return La thématique correspondant au nom, ou null si aucune thématique n'est trouvée.
     */
    Thematique findByNomThematique(String nomThematique);
}

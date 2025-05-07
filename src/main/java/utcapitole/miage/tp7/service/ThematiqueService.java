package utcapitole.miage.tp7.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import utcapitole.miage.tp7.model.entity.Thematique;
import utcapitole.miage.tp7.repository.ThematiqueRepo;

/**
 * Service pour gérer les thématiques.
 */
@Service
public class ThematiqueService {

    @Autowired
    private ThematiqueRepo thematiqueRepo;

    /**
     * Récupère toutes les thématiques.
     * 
     * @return Liste de toutes les thématiques.
     */
    public List<Thematique> findAll() {
        return thematiqueRepo.findAll();
    }

    /**
     * Récupère une thématique par son ID.
     * 
     * @param id L'ID de la thématique à récupérer.
     * @return La thématique correspondante.
     */
    public Thematique findById(int id) {
        return thematiqueRepo.findById(id).orElse(null);
    }

    /**
     * Récupère une liste de thématiques par leurs IDs.
     * 
     * @param ids Liste des IDs des thématiques à récupérer.
     * @return Liste des thématiques correspondantes.
     */
    public List<Thematique> findAllByIds(List<Integer> ids) {
        return thematiqueRepo.findAllById(ids);
    }

}
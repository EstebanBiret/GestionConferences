package utcapitole.miage.tp7.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import utcapitole.miage.tp7.model.entity.Activite;
import utcapitole.miage.tp7.repository.ActiviteRepo;

/**
 * Service pour gérer les activités.
 */
@Service
public class ActiviteService {

    @Autowired
    private ActiviteRepo activiteRepo;

    /**
     * Récupère toutes les activités.
     * 
     * @return Une liste d'activités.
     */
    public List<Activite> findAll() {
        return activiteRepo.findAll();
    }

    /**
     * Récupère une activité par son ID.
     * 
     * @param id
     * @return L'activité correspondante.
     */
    public Activite findById(int id) {
        return activiteRepo.findById(id).orElse(null);  
    }

    /**
     * Récupère une liste d'activités par leurs IDs.
     * 
     * @param ids Liste des IDs des activités à récupérer.
     * @return Liste des activités correspondantes.
     */
    public List<Activite> findAllByIds(List<Integer> ids) {
        return activiteRepo.findAllById(ids);
    }

}
package utcapitole.miage.tp7.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import utcapitole.miage.tp7.model.entity.ChoixActivite;
import utcapitole.miage.tp7.repository.ChoixActivitesRepo;

/**
 * Service pour gérer les opérations liées aux choix d'activités.
 */
@Service
public class ChoixActivitesService {

    @Autowired
    private ChoixActivitesRepo choixActivitesRepo;

    public void save(ChoixActivite choix) {
        choixActivitesRepo.save(choix);
    }

    public List<ChoixActivite> findAll() {
        return choixActivitesRepo.findAll();
    }
}

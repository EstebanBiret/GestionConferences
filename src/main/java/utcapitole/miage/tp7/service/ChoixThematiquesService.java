package utcapitole.miage.tp7.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import utcapitole.miage.tp7.model.entity.ChoixThematique;
import utcapitole.miage.tp7.repository.ChoixThematiquesRepo;

/**
 * Service pour gérer les opérations liées aux choix des thématiques.
 */
@Service
public class ChoixThematiquesService {

    @Autowired
    private ChoixThematiquesRepo choixThematiquesRepo;

    public void save(ChoixThematique choix) {
        System.out.println("Saving choix: " + choix.getParticipant().getNomPart() + " - " + choix.getThematique().getNomThematique() + " - " + choix.getConference().getTitreConf());
        choixThematiquesRepo.save(choix);
    }

    public List<ChoixThematique> findAll() {
        return choixThematiquesRepo.findAll();
    }
}

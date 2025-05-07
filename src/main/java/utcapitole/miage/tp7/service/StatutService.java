package utcapitole.miage.tp7.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import utcapitole.miage.tp7.model.entity.Statut;
import utcapitole.miage.tp7.repository.StatutRepo;

/**
 * Service pour gérer les opérations liées aux statuts.
 */
@Service
public class StatutService {

    @Autowired
    private StatutRepo statutRepo;

    /**
     * Sauvegarde en base de données un statut.
     * 
     * @param statut Statut à sauvegarder
     * @return Le statut sauvegardé
     */
    public Statut saveStatut(Statut statut) {
        return statutRepo.save(statut);
    }

    /**
     * Récupère tous les statuts.
     * 
     * @return Liste de tous les statuts
     */
    public List<Statut> findAll() {
        return statutRepo.findAll();
    }

    /**
     * Récupère un statut par son ID.
     * 
     * @param id ID du statut à rechercher.
     * @return Le statut correspondant à l'ID.
     */
    public Statut findById(int id) {
        return statutRepo.findById(id).orElse(null);
    }

    /**
     * Récupère un statut par son nom.
     * 
     * @param nom Nom du statut à rechercher.
     * @return Le statut correspondant au nom.
     */
    public Statut findByNomStatut(String nom) {
        return statutRepo.findByNomStatut(nom);
    }
    
}
package utcapitole.miage.tp7.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * Entité représentant un statut d'un participant.
 */
@Entity
@Table(name = "statuts")
public class Statut {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer codeStatut;

    private String nomStatut;

    /**
     * Constructeur par défaut.
     */
    public Statut() {}

    /**
     * Constructeur avec paramètres.
     * 
     * @param nomStatut le nom du statut
     */
    public Statut(String nomStatut) {
        this.nomStatut = nomStatut;
    }

    /**
     * Getters et setters.
     */
    public Integer getCodeStatut() {return codeStatut;}
    public void setCodeStatut(Integer codeStatut) {this.codeStatut = codeStatut;}
    public String getNomStatut() {return nomStatut;}
    public void setNomStatut(String nomStatut) {this.nomStatut = nomStatut;}
}
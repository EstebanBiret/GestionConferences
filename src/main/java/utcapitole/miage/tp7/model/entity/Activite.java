package utcapitole.miage.tp7.model.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

/**
 * Entité représentant une activité proposée lors d'une conférence.
 */
@Entity
@Table(name = "activites")
public class Activite {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer codeActivite;

    private String nomActivite;
    private double prixActivite;
    private double prixActAccompagnant;
    private int nbMaxParticipants;

    //liens
    @ManyToMany
    @JoinTable(
        name = "proposer",
        joinColumns = @JoinColumn(name = "codeActivite"),
        inverseJoinColumns = @JoinColumn(name = "codeConf")
    )
    private List<Conference> conferences = new ArrayList<>();

    /**
     * Constructeur par défaut.
     */
    public Activite() {}

    /**
     * Constructeur avec paramètres.
     * 
     * @param nomActivite le nom de l'activité
     * @param prixActivite le prix de l'activité
     * @param prixActAccompagnant le prix de l'activité pour un accompagnant
     * @param nbMaxParticipants le nombre maximum de participants
     */
    public Activite(String nomActivite, double prixActivite, double prixActAccompagnant, int nbMaxParticipants) {
        this.nomActivite = nomActivite;
        this.prixActivite = prixActivite;
        this.prixActAccompagnant = prixActAccompagnant;
        this.nbMaxParticipants = nbMaxParticipants;
    }
    
    /**
     * Getters et setters.
     */
    public Integer getCodeActivite() {return codeActivite;}
    public void setCodeActivite(Integer codeActivite) {this.codeActivite = codeActivite;}
    public String getNomActivite() {return nomActivite;}
    public void setNomActivite(String nomActivite) {this.nomActivite = nomActivite;}
    public double getPrixActivite() {return prixActivite;}
    public void setPrixActivite(double prixActivite) {this.prixActivite = prixActivite;}
    public double getPrixActAccompagnant() {return prixActAccompagnant;}
    public void setPrixActAccompagnant(double prixActAccompagnant) {this.prixActAccompagnant = prixActAccompagnant;}
    public int getNbMaxParticipants() {return nbMaxParticipants;}
    public void setNbMaxParticipants(int nbMaxParticipants) {this.nbMaxParticipants = nbMaxParticipants;}    
    public List<Conference> getConferences() {return conferences;}
    public void setConference(ArrayList<Conference> conferences) {this.conferences = conferences;}
    public void addConference(Conference conference) {this.conferences.add(conference);}
    public void removeConference(Conference conference) {this.conferences.remove(conference);}
}
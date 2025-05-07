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
 * Entité représentant une thématique de conférence.
 */
@Entity
@Table(name = "thematiques")
public class Thematique {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer codeThematique;

    private String nomThematique;

    @ManyToMany
    @JoinTable(
        name = "traiter",
        joinColumns = @JoinColumn(name = "codeThematique"),
        inverseJoinColumns = @JoinColumn(name = "codeConf")
    )
    private List<Conference> conferences = new ArrayList<>();

    /**
     * Constructeur par défaut.
     */
    public Thematique() {}

    /**
     * Constructeur avec paramètres.
     * 
     * @param nomThematique le nom de la thématique
     */
    public Thematique(String nomThematique) {
        this.nomThematique = nomThematique;
    }

    /**
     * Getters et setters.
     */
    public Integer getCodeThematique() {return codeThematique;}
    public void setCodeThematique(Integer codeThematique) {this.codeThematique = codeThematique;}
    public String getNomThematique() {return nomThematique;}
    public void setNomThematique(String nomThematique) {this.nomThematique = nomThematique;}
    public List<Conference> getConferences() {return conferences;}
    public void setConferences(ArrayList<Conference> conferences) {this.conferences = conferences;}
    public void addConference(Conference conference) {this.conferences.add(conference);}
    public void removeConference(Conference conference) {this.conferences.remove(conference);}
}
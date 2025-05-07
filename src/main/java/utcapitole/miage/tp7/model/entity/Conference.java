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
 * Entité representant une conference.
 */
@Entity
@Table(name = "conferences")
public class Conference {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer codeConf;
    
    private String titreConf;
    private Integer numEditionConf;
    private String dtDebutConf;
    private String dtFinConf;
    private String urlSiteWebConf;
    private Integer fraisInscriptionConf;

    //liens
    @ManyToMany
    @JoinTable(
        name = "inscrire",
        joinColumns = @JoinColumn(name = "codeConf"),
        inverseJoinColumns = @JoinColumn(name = "codeParticipant")
    )
    private List<Participant> participants = new ArrayList<>();

    @ManyToMany
    @JoinTable(
        name = "traiter",
        joinColumns = @JoinColumn(name = "codeConf"),
        inverseJoinColumns = @JoinColumn(name = "codeThematique")
    )
    private List<Thematique> thematiques = new ArrayList<>();

    @ManyToMany
    @JoinTable(
        name = "proposer",
        joinColumns = @JoinColumn(name = "codeConf"),
        inverseJoinColumns = @JoinColumn(name = "codeActivite")
    )
    private List<Activite> activites = new ArrayList<>();

    /**
     * Constructeur par défaut.
     */
    public Conference() {}

    /**
     * Constructeur avec paramètres.
     * 
     * @param titreConf le titre de la conférence
     * @param numEditionConf le numéro d'édition de la conférence
     * @param dtDebutConf la date de début de la conférence
     * @param dtFinConf la date de fin de la conférence
     * @param urlSiteWebConf l'URL du site web de la conférence
     * @param fraisInscriptionConf les frais d'inscription à la conférence
     * @param thematiques les thématiques de la conférence
     * @param activites les activités de la conférence
     */
    public Conference(String titreConf, Integer numEditionConf, String dtDebutConf, String dtFinConf, String urlSiteWebConf, Integer fraisInscriptionConf, ArrayList<Thematique> thematiques, ArrayList<Activite> activites) {
        this.titreConf = titreConf;
        this.numEditionConf = numEditionConf;
        this.dtDebutConf = dtDebutConf;
        this.dtFinConf = dtFinConf;
        this.urlSiteWebConf = urlSiteWebConf;
        this.fraisInscriptionConf = fraisInscriptionConf;
        this.thematiques = thematiques;
        this.activites = activites;
    }

    /**
     * Getters et setters.
     */
    public Integer getCodeConf() {return codeConf;}
    public void setCodeConf(Integer codeConf) {this.codeConf = codeConf;}
    public String getTitreConf() {return titreConf;}
    public void setTitreConf(String titreConf) {this.titreConf = titreConf;}
    public Integer getNumEditionConf() {return numEditionConf;}
    public void setNumEditionConf(Integer numEditionConf) {this.numEditionConf = numEditionConf;}
    public String getDtDebutConf() {return dtDebutConf;}
    public void setDtDebutConf(String dtDebutConf) {this.dtDebutConf = dtDebutConf;}
    public String getDtFinConf() {return dtFinConf;}
    public void setDtFinConf(String dtFinConf) {this.dtFinConf = dtFinConf;}
    public String getUrlSiteWebConf() {return urlSiteWebConf;}
    public void setUrlSiteWebConf(String urlSiteWebConf) {this.urlSiteWebConf = urlSiteWebConf;}
    public Integer getFraisInscriptionConf() {return fraisInscriptionConf;}
    public void setFraisInscriptionConf(Integer fraisInscriptionConf) {this.fraisInscriptionConf = fraisInscriptionConf;}
    public List<Participant> getParticipants() {return participants;}
    public void setParticipants(ArrayList<Participant> participants) {this.participants = participants;}
    public void addParticipant(Participant participant) {this.participants.add(participant);}
    public void removeParticipant(Participant participant) {this.participants.remove(participant);}
    public List<Thematique> getThematiques() {return thematiques;}
    public void setThematiques(ArrayList<Thematique> thematiques) {this.thematiques = thematiques;}
    public void addThematique(Thematique thematique) {this.thematiques.add(thematique);}
    public void removeThematique(Thematique thematique) {this.thematiques.remove(thematique);}
    public List<Activite> getActivites() {return activites;}
    public void setActivites(ArrayList<Activite> activites) {this.activites = activites;}
    public void addActivite(Activite activite) {this.activites.add(activite);}
    public void removeActivite(Activite activite) {this.activites.remove(activite);}
} 
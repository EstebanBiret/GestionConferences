package utcapitole.miage.tp7.model.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

/**
 * Entité representant un participant
 */
@Entity
@Table(name = "participants")
public class Participant {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer codeParticipant;
    
    private String nomPart;
    private String prenomPart;
    private String organismePart;
    private Integer cpPart;
    private String adrPart;
    private String villePart;
    private String paysPart;
    private String emailPart;
    private String dtInscription;

    @ManyToOne
    @JoinColumn(name = "codeStatut")
    private Statut statut;

    private String password;

    //liens
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "inscrire",
        joinColumns = @JoinColumn(name = "codeParticipant"),
        inverseJoinColumns = @JoinColumn(name = "codeConf")
    )
    private List<Conference> conferences = new ArrayList<>();

    /**
     * Constructeur par défaut.
     */
    public Participant() {}

    /**
     * Constructeur avec paramètres.
     * 
     * @param nomPart le nom du participant
     * @param prenomPart le prénom du participant 
     * @param organismePart le nom de l'organisme du participant
     * @param cpPart le code postal du participant 
     * @param adrPart l'adresse du participant
     * @param villePart la ville du participant
     * @param paysPart le pays du participant
     * @param emailPart l'email du participant
     * @param dtInscription la date d'inscription du participant
     * @param statut le statut du participant
     * @param password le mot de passe du participant
     */
    public Participant(String nomPart, String prenomPart, String organismePart, Integer cpPart, String adrPart, String villePart, String paysPart, String emailPart, String dtInscription, Statut statut, String password) {
        this.nomPart = nomPart;
        this.prenomPart = prenomPart;
        this.organismePart = organismePart;
        this.cpPart = cpPart;
        this.adrPart = adrPart;
        this.villePart = villePart;
        this.paysPart = paysPart;
        this.emailPart = emailPart;
        this.dtInscription = dtInscription;
        this.statut = statut;
        this.password = password;
    }

    /**
     * Getters et setters.
     */
    public Integer getCodeParticipant() {return codeParticipant;}
    public void setCodeParticipant(Integer codeParticipant) {this.codeParticipant = codeParticipant;}
    public String getNomPart() {return nomPart;}
    public void setNomPart(String nomPart) {this.nomPart = nomPart;}
    public String getPrenomPart() {return prenomPart;}
    public void setPrenomPart(String prenomPart) {this.prenomPart = prenomPart;}
    public String getOrganismePart() {return organismePart;}
    public void setOrganismePart(String organismePart) {this.organismePart = organismePart;}
    public Integer getCpPart() {return cpPart;}
    public void setCpPart(Integer cpPart) {this.cpPart = cpPart;}
    public String getAdrPart() {return adrPart;}
    public void setAdrPart(String adrPart) {this.adrPart = adrPart;}
    public String getVillePart() {return villePart;}
    public void setVillePart(String villePart) {this.villePart = villePart;}
    public String getPaysPart() {return paysPart;}
    public void setPaysPart(String paysPart) {this.paysPart = paysPart;}
    public String getEmailPart() {return emailPart;}
    public void setEmailPart(String emailPart) {this.emailPart = emailPart;}
    public String getDtInscription() {return dtInscription;}
    public void setDtInscription(String dtInscription) {this.dtInscription = dtInscription;}
    public Statut getStatut() {return statut;}
    public void setStatut(Statut statut) {this.statut = statut;}
    public String getPassword() {return password;}
    public void setPassword(String password) {this.password = password;}
    public List<Conference> getConferences() {return conferences;}
    public void setConferences(ArrayList<Conference> conferences) {this.conferences = conferences;}
    public void addConference(Conference conference) {this.conferences.add(conference);}
    public void removeConference(Conference conference) {this.conferences.remove(conference);}

    /**
     * On les rédefinit pour la comparaison avec la session lors de l'inscription
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Participant that = (Participant) o;
        return Objects.equals(codeParticipant, that.codeParticipant);
    }

    @Override
    public int hashCode() {
        return Objects.hash(codeParticipant);
    }
} 
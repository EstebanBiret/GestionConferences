package utcapitole.miage.tp7.model.entity;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import utcapitole.miage.tp7.model.entity.id.ChoixActiviteId;

/**
 * Entité représentant le choix d'une activité par un participant dans une conférence.
 */
@Entity
@Table(name = "choix_activite")
public class ChoixActivite {

    @EmbeddedId
    private ChoixActiviteId id;

    @ManyToOne
    @MapsId("codeConf")
    @JoinColumn(name = "code_conf", insertable = false, updatable = false)
    private Conference conference;

    @ManyToOne
    @MapsId("codeActivite")
    @JoinColumn(name = "code_activite", insertable = false, updatable = false)
    private Activite activite;

    @ManyToOne
    @MapsId("codeParticipant")
    @JoinColumn(name = "code_participant", insertable = false, updatable = false)
    private Participant participant;

    /**
     * Constructeur par défaut.
     */
    public ChoixActivite() {}

    /**
     * Constructeur avec paramètres.
     * 
     * @param conference La conférence associée au choix d'activité.
     * @param activite L'activité choisie par le participant.
     * @param participant Le participant ayant fait le choix.
     */
    public ChoixActivite(Conference conference, Activite activite, Participant participant) {
        this.conference = conference;
        this.activite = activite;
        this.participant = participant;
        this.id = new ChoixActiviteId(conference.getCodeConf(), activite.getCodeActivite(), participant.getCodeParticipant());
    }

    /**
     * Getters et setters.
     */
    public ChoixActiviteId getId() { return id; }
    public void setId(ChoixActiviteId id) { this.id = id; }
    public Conference getConference() { return conference; }
    public void setConference(Conference conference) { this.conference = conference; }
    public Activite getActivite() { return activite; }
    public void setActivite(Activite activite) { this.activite = activite; }
    public Participant getParticipant() { return participant; }
    public void setParticipant(Participant participant) { this.participant = participant; }
}
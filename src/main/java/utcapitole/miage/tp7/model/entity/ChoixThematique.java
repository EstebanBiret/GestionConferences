package utcapitole.miage.tp7.model.entity;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import utcapitole.miage.tp7.model.entity.id.ChoixThematiqueId;

/**
 * Entité représentant le choix d'une thématique par un participant pour une conférence.
 */
@Entity
@Table(name = "choix_thematique")
public class ChoixThematique {

    @EmbeddedId
    private ChoixThematiqueId id;
    
    @ManyToOne
    @MapsId("codeConf")
    @JoinColumn(name = "code_conf", insertable = false, updatable = false)
    private Conference conference;

    @ManyToOne
    @MapsId("codeThematique")
    @JoinColumn(name = "code_thematique", insertable = false, updatable = false)
    private Thematique thematique;

    @ManyToOne
    @MapsId("codeParticipant")
    @JoinColumn(name = "code_participant", insertable = false, updatable = false)
    private Participant participant;

    /**
     * Constructeur par défaut.
     */
    public ChoixThematique() {}

    /**
     * Constructeur avec paramètres.
     * 
     * @param conference La conférence associée.
     * @param thematique La thématique associée.
     * @param participant Le participant associé.
     */
    public ChoixThematique(Conference conference, Thematique thematique, Participant participant) {
        this.conference = conference;
        this.thematique = thematique;
        this.participant = participant;
        this.id = new ChoixThematiqueId(conference.getCodeConf(), thematique.getCodeThematique(), participant.getCodeParticipant());
    }

    /**
     * Getters et setters.
     */
    public ChoixThematiqueId getId() { return id; }
    public void setId(ChoixThematiqueId id) { this.id = id; }
    public Conference getConference() { return conference; }
    public void setConference(Conference conference) { this.conference = conference; }
    public Thematique getThematique() { return thematique; }
    public void setThematique(Thematique thematique) { this.thematique = thematique; }
    public Participant getParticipant() { return participant; }
    public void setParticipant(Participant participant) { this.participant = participant; }
}
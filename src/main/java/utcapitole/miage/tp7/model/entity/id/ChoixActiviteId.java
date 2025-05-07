package utcapitole.miage.tp7.model.entity.id;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Embeddable;

/**
 * Clé primaire composite de la table ChoixActivite
 */
@Embeddable
public class ChoixActiviteId implements Serializable {

    private Integer codeConf;
    private Integer codeActivite;
    private Integer codeParticipant;

    /**
     * Constructeur par défaut
     */
    public ChoixActiviteId() {}

    /**
     * Constructeur avec paramètres
     * 
     * @param codeConf       Code de la conférence
     * @param codeActivite   Code de l'activité
     * @param codeParticipant Code du participant
     */
    public ChoixActiviteId(Integer codeConf, Integer codeActivite, Integer codeParticipant) {
        this.codeConf = codeConf;
        this.codeActivite = codeActivite;
        this.codeParticipant = codeParticipant;
    }

    /**
     * Getters & setters
     */
    public Integer getCodeConf() { return codeConf; }
    public void setCodeConf(Integer codeConf) { this.codeConf = codeConf; }
    public Integer getCodeActivite() { return codeActivite; }
    public void setCodeActivite(Integer codeActivite) { this.codeActivite = codeActivite; }
    public Integer getCodeParticipant() { return codeParticipant; }
    public void setCodeParticipant(Integer codeParticipant) { this.codeParticipant = codeParticipant; }

    /**
     * Equals & hashCode
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ChoixActiviteId that)) return false;
        return Objects.equals(codeConf, that.codeConf) &&
               Objects.equals(codeActivite, that.codeActivite) &&
               Objects.equals(codeParticipant, that.codeParticipant);
    }

    @Override
    public int hashCode() {
        return Objects.hash(codeConf, codeActivite, codeParticipant);
    }
}

package utcapitole.miage.tp7.model.entity.id;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Embeddable;

/**
 * Clé primaire composite de la table ChoixThematique
 */
@Embeddable
public class ChoixThematiqueId implements Serializable {

    private Integer codeConf;
    private Integer codeThematique;
    private Integer codeParticipant;

    /**
     * Constructeur par défaut
     */
    public ChoixThematiqueId() {}

    /**
     * Constructeur avec paramètres
     * 
     * @param codeConf
     * @param codeThematique
     * @param codeParticipant
     */
    public ChoixThematiqueId(Integer codeConf, Integer codeThematique, Integer codeParticipant) {
        this.codeConf = codeConf;
        this.codeThematique = codeThematique;
        this.codeParticipant = codeParticipant;
    }

    /**
     * Getters & setters
     */
    public Integer getCodeConf() { return codeConf; }
    public void setCodeConf(Integer codeConf) { this.codeConf = codeConf; }
    public Integer getCodeThematique() { return codeThematique; }
    public void setCodeThematique(Integer codeThematique) { this.codeThematique = codeThematique; }
    public Integer getCodeParticipant() { return codeParticipant; }
    public void setCodeParticipant(Integer codeParticipant) { this.codeParticipant = codeParticipant; }

    /**
     * Equals & hashCode
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ChoixThematiqueId that)) return false;
        return Objects.equals(codeConf, that.codeConf) &&
               Objects.equals(codeThematique, that.codeThematique) &&
               Objects.equals(codeParticipant, that.codeParticipant);
    }

    @Override
    public int hashCode() {
        return Objects.hash(codeConf, codeThematique, codeParticipant);
    }
}
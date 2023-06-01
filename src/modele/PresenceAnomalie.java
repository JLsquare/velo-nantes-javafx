package modele;

/**
 * The PresenceAnomalie enum which represents the presence of an anomaly in the data
 * @author Groupe 4B2
 */
public enum PresenceAnomalie{
    FAIBLE("faible"),
    FORTE("forte"),
    NULLE("nulle");

    private String nom;

    /**
     * Default constructor
     * @param nom the name of the presence of anomaly
     */
    PresenceAnomalie(String nom) {
        this.nom = nom;
    }

    /**
     * Get the name of the presence of anomaly
     * @return the name of the presence of anomaly
     */
    public String getNom() {
        return nom;
    }

    /**
     * Get the presence of anomaly with the name
     * @param nom the name of the presence of anomaly
     * @return the presence of anomaly
     */
    public static PresenceAnomalie getPresenceAnomalie(String nom) {
        for (PresenceAnomalie pa : PresenceAnomalie.values()) {
            if (pa.getNom().equalsIgnoreCase(nom)) {
                return pa;
            }
        }
        return NULLE;
    }
}

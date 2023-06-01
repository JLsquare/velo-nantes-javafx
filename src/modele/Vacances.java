package modele;

/**
 * The Vacances enum which represents the holidays in the data
 * @author Groupe 4B2
 */
public enum Vacances{
    NOEL("noel"),
    ASCENSION("ascension"),
    HIVERS("hivers"),
    ETE("ete"),
    TOUSSAINT("toussaint"),
    PRINTEMPS("printemps"),
    HORS_VACANCES("hors vacances");

    private String nom;

    /**
     * Default constructor
     * @param nom the name of the holidays
     */
    Vacances(String nom) {
        this.nom = nom;
    }

    /**
     * Get the name of the holidays
     * @return the name of the holidays
     */
    public String getNom() {
        return nom;
    }

    /**
     * Get the holidays with the name
     * @param nom the name of the holidays
     * @return the holidays
     */
    public static Vacances getVacances(String nom) {
        for (Vacances v : Vacances.values()) {
            if (v.getNom().equalsIgnoreCase(nom)) {
                return v;
            }
        }
        return HORS_VACANCES;
    }
}

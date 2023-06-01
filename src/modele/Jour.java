package modele;

/**
 * The Jour enum which represents the day of the week
 * @author Groupe 4B2
 */
public enum Jour {
    LUNDI("lundi"),
    MARDI("mardi"),
    MERCREDI("mercredi"),
    JEUDI("jeudi"),
    VENDREDI("vendredi"),
    SAMEDI("samedi"),
    DIMANCHE("dimanche");

    private String nom;

    /**
     * Default constructor
     * @param nom the name of the day
     */
    Jour(String nom) {
        this.nom = nom;
    }

    /**
     * Get the name of the day
     * @return the name of the day
     */
    public String getNom() {
        return nom;
    }

    /**
     * Get the day with the name
     * @param nom the name of the day
     * @return the day
     */
    public static Jour getJour(String nom) {
        Jour jour = null;
        for (Jour j : Jour.values()) {
            if (j.getNom().equalsIgnoreCase(nom)) {
                jour = j;
            }
        }
        return jour;
    }
}

package modele;

public enum Jour {
    LUNDI("lundi"),
    MARDI("mardi"),
    MERCREDI("mercredi"),
    JEUDI("jeudi"),
    VENDREDI("vendredi"),
    SAMEDI("samedi"),
    DIMANCHE("dimanche");

    private String nom;

    Jour(String nom) {
        this.nom = nom;
    }

    public String getNom() {
        return nom;
    }

    public static Jour getJour(String nom) {
        for (Jour j : Jour.values()) {
            if (j.getNom().equalsIgnoreCase(nom)) {
                return j;
            }
        }
        return null;
    }
}

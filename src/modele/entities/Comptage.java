package modele.entities;

import java.util.Arrays;

/**
 * The Comptage class which represents the Comptage table in the database
 * @author Groupe 4B2
 */
public class Comptage{

    // ---------------- Attributes ---------------- //

    private int[] passages;
    private PresenceAnomalie anomalie;
    private Compteur leCompteur;
    private DateInfo laDate;

    // ---------------- Constructors ---------------- //

    /**
     * Default constructor
     * @param passages the number of passages
     * @param anomalie the anomaly
     * @param leCompteur the Compteur
     * @param laDate the DateInfo
     */
    public Comptage(int[] passages, PresenceAnomalie anomalie, Compteur leCompteur, DateInfo laDate) throws NullPointerException {
        if(passages == null || anomalie == null){
            throw new NullPointerException("passages or anomalie is null");
        }
        this.passages = passages;
        this.anomalie = anomalie;
        this.leCompteur = leCompteur;
        this.laDate = laDate;
    }

    // ---------------- Getters & Setters ---------------- //

    /**
     * Get the number of passages
     * @param heure the hour
     * @return the number of passages
     */
    public int getPassage(int heure) {
        return passages[heure];
    }

    /**
     * Set the number of passages
     * @param heure the hour
     * @param passages the number of passages
     */
    public void setPassage(int heure, int passages) {
        this.passages[heure] = passages;
    }

    /**
     * Get the number of passages for every hour
     * @return the number of passages for every hour
     */
    public int[] getPassages() {
        return passages;
    }

    /**
     * Set the number of passages for every hour
     * @param passages the number of passages for every hour
     */
    public void setPassages(int[] passages) {
        this.passages = passages;
    }

    /**
     * Get the anomaly
     * @return the anomaly
     */
    public PresenceAnomalie getAnomalie() {
        return anomalie;
    }

    /**
     * Set the anomaly
     * @param anomalie the anomaly
     */
    public void setAnomalie(PresenceAnomalie anomalie) {
        this.anomalie = anomalie;
    }

    /**
     * Get the Compteur
     * @return the Compteur
     */
    public Compteur getLeCompteur() {
        return leCompteur;
    }

    /**
     * Set the Compteur
     * @param leCompteur the Compteur
     */
    public void setLeCompteur(Compteur leCompteur) {
        this.leCompteur = leCompteur;
    }

    /**
     * Get the DateInfo
     * @return the DateInfo
     */
    public DateInfo getLaDate() {
        return laDate;
    }

    /**
     * Set the DateInfo
     * @param laDate the DateInfo
     */
    public void setLaDate(DateInfo laDate) {
        this.laDate = laDate;
    }

    // ---------------- Methods ---------------- //

    /**
     * To String method
     * @return the String
     */
    public String toString(){
        String ret = "Comptage(";
        ret += "laDate : " + this.laDate.getLaDate() + ", ";
        ret += "leCompteur : " + this.leCompteur.getIdCompteur() + ", ";
        ret += "anomalie : " + this.anomalie.name() + ", ";
        ret += "passages : " + Arrays.toString(this.passages) + ")";
        return ret;
    }

    /**
     * Compute the total number of passages
     * @return the total number of passages
     */
    public int totalPassages(){
        int total = 0;
        for(int i = 0; i < 24; i++){
            total += this.passages[i];
        }
        return total;
    }

    /**
     * Compute the average number of passages
     * @return the average number of passages
     */
    public float averagePassages(){
        return this.totalPassages() / 24.0f;
    }
}
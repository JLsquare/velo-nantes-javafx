package modele;

import java.sql.*;

/**
 * The Comptage class which represents the Comptage table in the database
 * @author Groupe 4B2
 */
public class Comptage{

    // ---------------- Attributes ---------------- //

    private int[] passages;
    private PresenceAnomalie anomalie;
    private int leCompteur;
    private Date laDate;

    // ---------------- Constructors ---------------- //

    /**
     * Default constructor
     * @param passages the number of passages
     * @param anomalie the anomaly
     * @param leCompteur the Compteur
     * @param laDate the DateInfo
     */
    public Comptage(int[] passages, PresenceAnomalie anomalie, int leCompteur, Date laDate) throws NullPointerException {
        if(passages == null || anomalie == null){
            throw new NullPointerException("passages or anomalie is null");
        }
        this.passages = passages;
        this.anomalie = anomalie;
        this.leCompteur = leCompteur;
        this.laDate = laDate;
    }

    /**
     * Constructor with ResultSet
     * @param rs the ResultSet
     * @throws SQLException if there is an error with the ResultSet
     */
    public Comptage(ResultSet rs) throws SQLException {
        this.passages = new int[24];
        for(int i = 0; i < 24; i++){
            this.passages[i] = rs.getInt("h" + String.format("%02d", i));
        }
        if(rs.getString("presenceAnomalie") != null){
            this.anomalie = PresenceAnomalie.valueOf(rs.getString("presenceAnomalie"));
        } else {
            this.anomalie = PresenceAnomalie.Nulle;
        }
        this.leCompteur = rs.getInt("leCompteur");
        this.laDate = rs.getDate("dateComptage");
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
    public int getLeCompteur() {
        return leCompteur;
    }

    /**
     * Set the Compteur
     * @param leCompteur the Compteur
     */
    public void setLeCompteur(int leCompteur) {
        this.leCompteur = leCompteur;
    }

    /**
     * Get the DateInfo
     * @return the DateInfo
     */
    public Date getLaDate() {
        return laDate;
    }

    /**
     * Set the DateInfo
     * @param laDate the DateInfo
     */
    public void setLaDate(Date laDate) {
        this.laDate = laDate;
    }

    // ---------------- Methods ---------------- //

    /**
     * Get the total number of passages
     * @return the total number of passages
     */
    public int totalVeloCount(){
        int total = 0;
        for(int i = 0; i < 24; i++){
            total += this.passages[i];
        }
        return total;
    }

    /**
     * Get the average number of passages
     * @return the average number of passages
     */
    public float averageVeloCount(){
        return this.totalVeloCount() / 24.0f;
    }

    /**
     * To String method
     * @return the String
     */
    public String toString(){
        String str = "Comptage(" + this.laDate + ", " + this.leCompteur + ", " + this.anomalie.name() + ", " + this.totalVeloCount() + ", " + this.averageVeloCount() + ", ";
        for(int i = 0; i < 24; i++){
            str += this.passages[i] + ", ";
        }
        str = str.substring(0, str.length() - 2) + ")";
        return str;
    }
}
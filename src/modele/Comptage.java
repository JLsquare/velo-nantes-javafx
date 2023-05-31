package modele;

import java.sql.*;
import java.util.HashMap;

public class Comptage{

    // ---------------- Attributes ---------------- //

    public static HashMap<Integer, Comptage> lesComptages = new HashMap<Integer, Comptage>();
    private int[] passages;
    private String anomalie;
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
    public Comptage(int[] passages, String anomalie, int leCompteur, Date laDate) throws NullPointerException {
        if(passages == null || anomalie == null){
            throw new NullPointerException("passages or anomalie is null");
        }
        this.passages = passages;
        this.anomalie = anomalie;
        this.leCompteur = Compteur.getCompteur(leCompteur);
        this.laDate = DateInfo.getDateInfo(laDate);

        lesComptages.put(laDate.hashCode() + leCompteur, this);
    }

    /**
     * Constructor with ResultSet
     * @param rs the ResultSet
     * @throws SQLException if there is an error with the ResultSet
     */
    public Comptage(ResultSet rs) throws SQLException {
        this.passages = new int[24];
        for(int i = 0; i < 24; i++){
            this.passages[i] = rs.getInt("passage" + (i + 1));
        }
        this.anomalie = rs.getString("anomalie");
        this.leCompteur = Compteur.getCompteur(rs.getInt("leCompteur"));
        this.laDate = DateInfo.getDateInfo(rs.getDate("laDate"));

        lesComptages.put(laDate.hashCode() + leCompteur.getIdCompteur(), this);
    }

    // ---------------- Getters & Setters ---------------- //

    /**
     * Get the number of passages
     * @return the number of passages
     */
    public int getPassages(int heure) {
        return passages[heure];
    }

    /**
     * Set the number of passages
     * @param passages the number of passages
     */
    public void setPassages(int[] passages) {
        this.passages = passages;
    }

    /**
     * Get the anomaly
     * @return the anomaly
     */
    public String getAnomalie() {
        return anomalie;
    }

    /**
     * Set the anomaly
     * @param anomalie the anomaly
     */
    public void setAnomalie(String anomalie) {
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

    /**
     * Get the Comptage
     * @param laDate the date
     * @param leCompteur the Compteur
     * @return the Comptage
     */
    public static Comptage getComptage(Date laDate, Compteur leCompteur){
        return lesComptages.get(laDate.hashCode() + leCompteur.getIdCompteur());
    }

    // ---------------- Methods ---------------- //

    /**
     * To String method
     * @return the String
     */
    public String toString(){
        String str = "Comptage(" + this.laDate + " " + this.leCompteur + " " + this.anomalie + " ";
        for(int i = 0; i < 24; i++){
            str += this.passages[i] + ", ";
        }
        str = str.substring(0, str.length() - 2) + ")";
        return str;
    }

    /**
     * Generate the Comptage SQL Insert Query
     * @return the generated SQL Insert Query as a String
     */
    public String toInsertQuery() {
        String insert = "INSERT INTO COMPTAGE (leCompteur, dateComptage, ";
        for(int i = 0; i < 24; i++){
            insert += "h" + String.format("%02d", i) + ", ";
        }
        insert += "anomalie) VALUES (" + this.leCompteur.getIdCompteur() + ", '" + this.laDate + "', ";
        for(int i = 0; i < 24; i++){
            insert += this.passages[i] + ", ";
        }
        insert += "'" + this.anomalie + "')";

        return insert;
    }


    /**
     * Generate the Comptage SQL Update Query
     * @return the generated SQL Update Query as a String
     */
    public String toUpdateQuery() {
        String update = "UPDATE COMPTAGE SET ";
        for(int i = 0; i < 24; i++){
            update += "h" + String.format("%02d", i) + " = " + this.passages[i] + ", ";
        }
        update = update.substring(0, update.length() - 2) + "' WHERE leCompteur = " + this.leCompteur.getIdCompteur() + " AND dateComptage = '" + this.laDate + "'";

        return update;
    }

    /**
     * Generate the Comptage SQL Delete Query
     * @return the generated SQL Delete Query as a String
     */
    public String toDeleteQuery() {
        String delete = "DELETE FROM COMPTAGE WHERE leCompteur = " + this.leCompteur.getIdCompteur() + " AND dateComptage = '" + this.laDate + "'";
        return delete;
    }
}
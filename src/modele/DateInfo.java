package modele;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;

public class DateInfo {

    // ---------------- Attributes ---------------- //

    public static HashMap<Integer, DateInfo> lesDates = new HashMap<Integer, DateInfo>();
    private Date laDate;
    private float tempMoy;
    private Jour jour;
    private String vacances;

    // ---------------- Constructors ---------------- //

    /**
     * Default constructor
     * @param laDate the date
     * @param tempMoy the average temperature
     * @param jour the day
     * @param vacances the holidays
     */
    public DateInfo(Date laDate, float tempMoy, String jour, String vacances) {
        this.laDate = laDate;
        this.tempMoy = tempMoy;
        this.jour = Jour.getJour(jour);
        this.vacances = vacances;

        lesDates.put(laDate.hashCode(), this);
    }

    /**
     * Constructor with ResultSet
     * @param rs the ResultSet
     * @throws SQLException if there is an error with the ResultSet
     */
    public DateInfo(ResultSet rs) throws SQLException {
        this.laDate = rs.getDate("laDate");
        this.tempMoy = rs.getFloat("tempMoy");
        this.jour = Jour.getJour(rs.getString("jour"));
        this.vacances = rs.getString("vacances");

        lesDates.put(laDate.hashCode(), this);
    }

    // ---------------- Getters & Setters ---------------- //

    /**
     * Get the date
     * @return the date
     */
    public Date getLaDate() {
        return laDate;
    }

    /**
     * Set the date
     * @param laDate the date
     */
    public void setLaDate(Date laDate) {
        this.laDate = laDate;
    }

    /**
     * Get the average temperature
     * @return the average temperature
     */
    public float getTempMoy() {
        return tempMoy;
    }

    /**
     * Set the average temperature
     * @param tempMoy the average temperature
     */
    public void setTempMoy(float tempMoy) {
        this.tempMoy = tempMoy;
    }

    /**
     * Get the day
     * @return the day
     */
    public Jour getJour() {
        return jour;
    }

    /**
     * Set the day
     * @param jour the day
     */
    public void setJour(Jour jour) {
        this.jour = jour;
    }

    /**
     * Get the holidays
     * @return the holidays
     */
    public String getVacances() {
        return vacances;
    }

    /**
     * Set the holidays
     * @param vacances the holidays
     */
    public void setVacances(String vacances) {
        this.vacances = vacances;
    }

    /**
     * Get the DateInfo with the date
     * @param date the date
     * @return the DateInfo
     */
    public static DateInfo getDateInfo(Date date) {
        return lesDates.get(date.hashCode());
    }

    // ---------------- Methods ---------------- //

    /**
     * Compute the total velo count for the date
     * @return the total velo count for the date
     */
    public int totalVeloCount(){
        int ret = 0;
        ArrayList<Comptage> lesComptages = Comptage.getComptagesByDate(this.laDate);
        for (Comptage c : lesComptages){
            ret += c.totalVeloCount();
        }
        return ret;
    }

    /**
     * Compute the average velo count for the date
     * @return the average velo count for the date
     */
    public float averageVeloCount(){
        float ret = 0;
        ArrayList<Comptage> lesComptages = Comptage.getComptagesByDate(this.laDate);
        for (Comptage c : lesComptages){
            ret += c.averageVeloCount();
        }
        ret /= lesComptages.size();
        return ret;
    }

    /**
     * Get the String representation of the DateInfo
     * @return the String representation of the DateInfo
     */
    public String toString(){
        String ret = "DateInfo(" + laDate + ", " + tempMoy + ", " + jour.getNom() + ", " + vacances + ", " + this.totalVeloCount() + ", " + this.averageVeloCount() + ")";
        return ret;
    }

    /**
     * Get the String representation of all the DateInfo
     * @return the String representation of all the DateInfo
     */
    public static String toStringAll(){
        String ret = "";
        for (DateInfo d : lesDates.values()){
            ret += d.toString() + "\n";
        }
        return ret;
    }

    /**
     * Generate the DateInfo SQL Insert Query
     * @return the DateInfo SQL Insert Query as a String
     */
    public String toInsertQuery(){
        String ret = "INSERT INTO DATEINFO VALUES ('" + laDate + "', " + tempMoy + ", '" + jour + "', '" + vacances + "');";
        return ret;
    }

    /**
     * Generate the DateInfo SQL Update Query
     * @return the DateInfo SQL Update Query as a String
     */
    public String toUpdateQuery(){
        String ret = "UPDATE DATEINFO SET tempMoy = " + tempMoy + ", jour = '" + jour + "', vacances = '" + vacances + "' WHERE laDate = '" + laDate + "';";
        return ret;
    }

    /**
     * Generate the DateInfo SQL Delete Query
     * @return the DateInfo SQL Delete Query as a String
     */
    public String toDeleteQuery(){
        String ret = "DELETE FROM DATEINFO WHERE laDate = '" + laDate + "';";
        return ret;
    }

}

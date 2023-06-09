package modele;

import java.sql.*;
import java.util.ArrayList;

/**
 * The DateInfo class which represents the DateInfo table in the database
 * It's a model who can contain all attributes and methods to access at data
 * @author Groupe 4B2
 */
public class DateInfo {

    // ---------------- Attributes ---------------- //

    private Date laDate;
    private float tempMoy;
    private Jour jour;
    private Vacances vacances;
    private ArrayList<Comptage> lesComptages;

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
        this.jour = Jour.valueOf(vacances);
        this.vacances = Vacances.valueOf(vacances);
        this.lesComptages = new ArrayList<Comptage>();
    }

    /**
     * Constructor with ResultSet
     * @param rs the ResultSet
     * @throws SQLException if there is an error with the ResultSet
     */
    public DateInfo(ResultSet rs) throws SQLException {
        this.laDate = rs.getDate("laDate");
        this.tempMoy = rs.getFloat("tempMoy");
        this.jour = Jour.valueOf(rs.getString("jour"));
        if(rs.getString("vacances") != null){
            this.vacances = Vacances.valueOf(rs.getString("vacances"));
        }else{
            this.vacances = Vacances.Nulle;
        }
        this.lesComptages = new ArrayList<Comptage>();
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
    public Vacances getVacances() {
        return vacances;
    }

    /**
     * Set the holidays
     * @param vacances the holidays
     */
    public void setVacances(Vacances vacances) {
        this.vacances = vacances;
    }

    // ---------------- Methods ---------------- //

    /**
     * Add a Comptage to the DateInfo
     */
    public void addComptage(Comptage c){
        this.lesComptages.add(c);
    }

    /**
     * Get the String representation of the DateInfo
     * @return the String representation of the DateInfo
     */
    public String toString(){
        String ret = "DateInfo(" + this.laDate + ", " + this.tempMoy + ", " + this.jour.name() + ", " + this.vacances.name() + ", " + this.totalPassage() + ", " + this.averagePassages() + ")";
        return ret;
    }

    /**
     * Compute the total passage of the DateInfo
     * @return the total passage of the DateInfo
     */
    public int totalPassage(){
        int total = 0;
        for(Comptage c : this.lesComptages){
            total += c.totalPassages();
        }
        return total;
    }

    /**
     * Compute the average passage of the DateInfo
     * @return the average passage of the DateInfo
     */
    public float averagePassages(){
        float total = 0;
        for(Comptage c : this.lesComptages){
            total += c.averagePassages();
        }
        int nbComptages = this.lesComptages.size();
        if(nbComptages != 0){
            total /= nbComptages;
        }
        return total;
    }
}

package modele.entities;

import java.sql.*;
import java.util.ArrayList;

/**
 * The DateInfo class which represents the DateInfo table in the database
 * It's a model who can contain all attributes and methods to access at data
 * @author Groupe 4B2
 */
public class DateInfo {

    // ---------------- Attributes ---------------- //

    private Date date;
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
    public DateInfo(Date laDate, float tempMoy, Jour jour, Vacances vacances) {
        this.date = laDate;
        this.tempMoy = tempMoy;
        this.jour = jour;
        this.vacances = vacances;
        this.lesComptages = new ArrayList<Comptage>();
    }

    // ---------------- Getters & Setters ---------------- //

    /**
     * Get the date
     * @return the date
     */
    public Date getDate() {
        return date;
    }

    /**
     * Set the date
     * @param date the date
     */
    public void setDate(Date date) {
        this.date = date;
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

    // ---------------- Add & Remove ---------------- //

    /**
     * Add a Comptage to the DateInfo
     * @param comptage the Comptage
     */
    public void addComptage(Comptage comptage){
        this.lesComptages.add(comptage);
    }

    /**
     * Remove a Comptage to the DateInfo
     * @param comptage the Comptage
     */
    public void removeComptage(Comptage comptage){
        this.lesComptages.remove(comptage);
    }

    // ---------------- Methods ---------------- //

    /**
     * Get the String representation of the DateInfo
     * Use a StringBuilder to concatenate String
     * @return the String representation of the DateInfo
     */
    public String toString() {
        StringBuilder ret = new StringBuilder("DateInfo(");
        ret.append("laDate : ").append(this.date).append(", ");
        ret.append("tempMoy : ").append(this.tempMoy).append(", ");
        ret.append("jour : ").append(this.jour.name()).append(", ");
        ret.append("vacances : ").append(this.vacances.name()).append(")");
        return ret.toString();
    }

    /**
     * Check if the DateInfo is in holidays
     * @return true if the DateInfo is in holidays, false otherwise
     */
    public boolean isInVacances(){
        boolean ret = true;
        if(this.vacances == Vacances.Nulle){
            ret = false;
        }
        return ret;
    }


    /**
     * Check if the DateInfo is in weekend
     * @return true if the DateInfo is in weekend, false otherwise
     */
    public boolean isInWeekend(){
        boolean ret = false;
        if(this.jour == Jour.Samedi || this.jour == Jour.Dimanche){
            ret = true;
        }
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
    
    /**
     * Compute the total passage per hour of the DateInfo
     * @return the total passage per hour of the DateInfo
     */
    public int[] totalPassagesPerHour(){
        int[] total = new int[24];
        for(Comptage c : this.lesComptages){
            int[] totalComptage = c.getPassages();
            for(int i = 0; i < 24; i++){
                total[i] += totalComptage[i];
            }
        }
        return total;
    }

    /**
     * Compute the average passage per hour of the DateInfo
     * @return the average passage per hour of the DateInfo
     */
    public float[] averagePassagesPerHour(){
        float[] total = new float[24];
        for(Comptage c : this.lesComptages){
            int[] totalComptage = c.getPassages();
            for(int i = 0; i < 24; i++){
                total[i] += totalComptage[i];
            }
        }
        int nbComptages = this.lesComptages.size();
        if(nbComptages != 0){
            for(int i = 0; i < 24; i++){
                total[i] /= nbComptages;
            }
        }
        return total;
    }
}

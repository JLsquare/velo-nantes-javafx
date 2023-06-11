package modele.entities;

import java.util.ArrayList;

/**
 * The Compteur class which represents the Compteur table in the database
 * It's a model who can contain all attributes and methods to access at data
 * @author Groupe 4B2
 */
public class Compteur{

    // ---------------- Attributes ---------------- //

    private int idCompteur;
    private String nomCompteur;
    private String sens;
    private float coordX;
    private float coordY;
    private Quartier leQuartier;
    private ArrayList<Comptage> lesComptages;

    // ---------------- Constructors ---------------- //

    /**
     * Default constructor
     * @param idCompteur the id of the Compteur
     * @param nomCompteur the name of the Compteur
     * @param sens the direction of the Compteur
     * @param coordX the X coordinate of the Compteur
     * @param coordY the Y coordinate of the Compteur
     * @param leQuartier the Quartier of the Compteur
     * @throws NullPointerException if one of the parameters is null
     */
    public Compteur(int idCompteur, String nomCompteur, String sens, float coordX, float coordY, Quartier leQuartier) throws NullPointerException {
        if(nomCompteur == null || sens == null || leQuartier == null){
            throw new NullPointerException("nomCompteur or sens is null");
        }
        this.idCompteur = idCompteur;
        this.nomCompteur = nomCompteur;
        this.sens = sens;
        this.coordX = coordX;
        this.coordY = coordY;
        this.leQuartier = leQuartier;
        this.lesComptages = new ArrayList<Comptage>();
    }

    // ---------------- Getters & Setters ---------------- //

    /**
     * Get the id of the Compteur
     * @return the id of the Compteur
     */
    public int getIdCompteur() {
        return idCompteur;
    }

    /**
     * Set the id of the Compteur
     * @param idCompteur the id of the Compteur
     */
    public void setIdCompteur(int idCompteur) {
        this.idCompteur = idCompteur;
    }

    /**
     * Get the name of the Compteur
     * @return the name of the Compteur
     */
    public String getNomCompteur() {
        return nomCompteur;
    }

    /**
     * Set the name of the Compteur
     * @param nomCompteur the name of the Compteur
     * @throws NullPointerException if nomCompteur is null
     */
    public void setNomCompteur(String nomCompteur) throws NullPointerException{
        if(nomCompteur == null){
            throw new NullPointerException("nomCompteur is null");
        }
        this.nomCompteur = nomCompteur;
    }

    /**
     * Get the direction of the Compteur
     * @return the direction of the Compteur
     */
    public String getSens() {
        return sens;
    }

    /**
     * Set the direction of the Compteur
     * @param sens the direction of the Compteur
     */
    public void setSens(String sens) {
        this.sens = sens;
    }

    /**
     * Get the X coordinate of the Compteur
     * @return the X coordinate of the Compteur
     */
    public float getCoordX() {
        return coordX;
    }

    /**
     * Set the X coordinate of the Compteur
     * @param coordX the X coordinate of the Compteur
     */
    public void setCoordX(float coordX) {
        this.coordX = coordX;
    }

    /**
     * Get the Y coordinate of the Compteur
     * @return the Y coordinate of the Compteur
     */
    public float getCoordY() {
        return coordY;
    }

    /**
     * Set the Y coordinate of the Compteur
     * @param coordY the Y coordinate of the Compteur
     */
    public void setCoordY(float coordY) {
        this.coordY = coordY;
    }

    /**
     * Get the Quartier of the Compteur
     * @return the Quartier of the Compteur
     */
    public Quartier getLeQuartier() {
        return leQuartier;
    }

    /**
     * Set the Quartier of the Compteur
     * @param leQuartier the Quartier of the Compteur
     */
    public void setLeQuartier(Quartier leQuartier) {
        this.leQuartier = leQuartier;
    }

    // ---------------- Add & Remove ---------------- //

    /**
     * Add a Comptage to the Compteur
     * @param comptage the Comptage to add
     * @throws NullPointerException if comptage is null
     */
    public void addComptage(Comptage comptage) throws NullPointerException{
        if(comptage == null){
            throw new NullPointerException("comptage is null");
        }
        this.lesComptages.add(comptage);
    }

    /**
     * Remove a Comptage to the Compteur
     * @param comptage the Comptage to remove
     * @throws NullPointerException if comptage is null
     */
    public void removeComptage(Comptage comptage) throws NullPointerException{
        if(comptage == null){
            throw new NullPointerException("comptage is null");
        }
        this.lesComptages.remove(comptage);
    }

    // ---------------- Methods ---------------- //

    /**
     * To String method
     * @return the String of the Compteur
     */
    public String toString() {
        String ret = "Compteur(";
        ret += "idCompteur : " + this.idCompteur + ", ";
        ret += "nomCompteur : " + this.nomCompteur + ", ";
        ret += "sens : " + this.sens + ", ";
        ret += "coordX : " + this.coordX + ", ";
        ret += "coordY : " + this.coordY + ", ";
        ret += "leQuartier : " + this.leQuartier.getIdQuartier() + ", ";
        ret += "totalPassages : " + this.totalPassages(null) + ", ";
        ret += "averagePassages : " + this.averagePassages(null) + ")";
        return ret;
    }

    /**
     * Compute the total passage of the Compteur
     * @param laDate the date to compute, if null compute for all dates
     * @return the total passage of the Compteur
     */
    public int totalPassages(DateInfo laDate){
        int total = 0;
        for(Comptage c : this.lesComptages){
            if(c.getLaDate() == laDate || laDate == null){
                total += c.totalPassages();
            }
            total += c.totalPassages();
        }
        return total;
    }

    /**
     * Compute the average passage of the Compteur
     * @param laDate the date to compute, if null compute for all dates
     * @return the average passage of the Compteur
     */
    public float averagePassages(DateInfo laDate){
        float total = 0;
        int nbComptages = 0;
        for(Comptage c : this.lesComptages){
            if(c.getLaDate() == laDate || laDate == null){
                nbComptages++;
                total += c.averagePassages();
            }
        }
        if(nbComptages != 0){
            total /= nbComptages;
        }
        return total;
    }

    /**
     * Compute the total passage per hour of the Compteur
     * @param laDate the date to compute, if null compute for all dates
     * @return the total passage per hour of the Compteur
     */
    public int[] totalPassagesPerHour(DateInfo laDate){
        int[] total = new int[24];
        for(Comptage c : this.lesComptages){
            if(c.getLaDate() == laDate || laDate == null){
                int[] passages = c.getPassages();
                for(int i = 0; i < 24; i++){
                    total[i] += passages[i];
                }
            }
        }
        return total;
    } 

    /**
     * Compute the average passage per hour of the Compteur
     * @param laDate the date to compute, if null compute for all dates
     * @return the average passage per hour of the Compteur
     */
    public float[] averagePassagesPerHour(DateInfo laDate){
        float[] total = new float[24];
        int nbComptages = 0;
        for(Comptage c : this.lesComptages){
            if(c.getLaDate() == laDate || laDate == null){
                nbComptages++;
                int[] passages = c.getPassages();
                for(int i = 0; i < 24; i++){
                    total[i] += passages[i];
                }
            }
        }
        if(nbComptages != 0){
            for(int i = 0; i < 24; i++){
                total[i] /= nbComptages;
            }
        }
        return total;
    }

    /**
     * Compute the total passage per day of the Compteur
     * @param laDate the date to compute, if null compute for all dates
     * @return the total passage per day of the Compteur
     */
    public int[] totalPassagesPerDay(DateInfo laDate){
        int[] total = new int[7];
        for(Comptage c : this.lesComptages){
            if(c.getLaDate() == laDate || laDate == null){
                int[] passages = c.getPassages();
                Jour jour = c.getLaDate().getJour();
                for(int i = 0; i < 24; i++){
                    total[jour.ordinal()] += passages[i];
                }
            }
        }
        return total;
    }

    /**
     * Compute the average passage per day of the Compteur
     * @param laDate the date to compute, if null compute for all dates
     * @return the average passage per day of the Compteur
     */
    public float[] averagePassagesPerDay(DateInfo laDate){
        float[] total = new float[7];
        int nbComptages = 0;
        for(Comptage c : this.lesComptages){
            if(c.getLaDate() == laDate || laDate == null){
                nbComptages++;
                int[] passages = c.getPassages();
                Jour jour = c.getLaDate().getJour();
                for(int i = 0; i < 24; i++){
                    total[jour.ordinal()] += passages[i];
                }
            }
        }
        if(nbComptages != 0){
            for(int i = 0; i < 7; i++){
                total[i] /= nbComptages;
            }
        }
        return total;
    }
}
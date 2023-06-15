package modele.entities;

import java.sql.Date;
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
     * Use a StringBuilder to concatenate String
     * @return the String of the Compteur
     */
    public String toString() {
        StringBuilder ret = new StringBuilder("Compteur(");
        ret.append("idCompteur : ").append(this.idCompteur).append(", ");
        ret.append("nomCompteur : ").append(this.nomCompteur).append(", ");
        ret.append("sens : ").append(this.sens).append(", ");
        ret.append("coordX : ").append(this.coordX).append(", ");
        ret.append("coordY : ").append(this.coordY).append(", ");
        ret.append("leQuartier : ").append(this.leQuartier.getIdQuartier()).append(")");
        return ret.toString();
    }


    /**
     * Compute the total passage of the Compteur
     * @param dateDebut the start date to compute
     * @param dateFin the end date to compute
     * @return the total passage of the Compteur
     * @throws IllegalArgumentException if dateDebut or dateFin is null
     */
    public int totalPassages(DateInfo dateDebut, DateInfo dateFin) throws IllegalArgumentException {
        if(dateDebut == null || dateFin == null){
            throw new IllegalArgumentException("dateDebut or dateFin is null");
        }
        int total = 0;
        Date debut = dateDebut.getDate();
        Date fin = dateFin.getDate();
        for(Comptage c : this.lesComptages){
            DateInfo laDate = c.getLaDate();
            Date date = laDate.getDate();
            if((date.after(debut) && date.before(fin))){
                total += c.totalPassages();
            }
        }
        return total;
    }

    /**
     * Compute the average passage of the Compteur
     * @param dateDebut the start date to compute
     * @param dateFin the end date to compute
     * @return the average passage of the Compteur
     * @throws IllegalArgumentException if either dateDebut or dateFin is null
     */
    public float averagePassages(DateInfo dateDebut, DateInfo dateFin) throws IllegalArgumentException{
        if(dateDebut == null || dateFin == null){
            throw new IllegalArgumentException("dateDebut or dateFin is null");
        }
        float total = 0;
        int nbComptages = 0;
        Date debut = dateDebut.getDate();
        Date fin = dateFin.getDate();
        for(Comptage c : this.lesComptages){
            Date date = c.getLaDate().getDate();
            if(date.after(debut) && date.before(fin)){
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
     * @param dateDebut the start date to compute
     * @param dateFin the end date to compute
     * @return the total passage per hour of the Compteur
     * @throws IllegalArgumentException if either dateDebut or dateFin is null
     */
    public int[] totalPassagesPerHour(DateInfo dateDebut, DateInfo dateFin) throws IllegalArgumentException {
        if(dateDebut == null || dateFin == null){
            throw new IllegalArgumentException("dateDebut or dateFin is null");
        }
        int[] total = new int[24];
        Date debut = dateDebut.getDate();
        Date fin = dateFin.getDate();
        for(Comptage c : this.lesComptages){
            Date date = c.getLaDate().getDate();
            if(date.after(debut) && date.before(fin)){
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
     * @param dateDebut the start date to compute
     * @param dateFin the end date to compute
     * @return the average passage per hour of the Compteur
     * @throws IllegalArgumentException if either dateDebut or dateFin is null
     */
    public float[] averagePassagesPerHour(DateInfo dateDebut, DateInfo dateFin) throws IllegalArgumentException {
        if(dateDebut == null || dateFin == null){
            throw new IllegalArgumentException("dateDebut or dateFin is null");
        }
        float[] total = new float[24];
        int nbComptages = 0;
        Date debut = dateDebut.getDate();
        Date fin = dateFin.getDate();
        for(Comptage c : this.lesComptages){
            Date date = c.getLaDate().getDate();
            if(date.after(debut) && date.before(fin)){
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
     * @param dateDebut the start date to compute
     * @param dateFin the end date to compute
     * @return the total passage per day of the Compteur
     * @throws IllegalArgumentException if either dateDebut or dateFin is null
     */
    public int[] totalPassagesPerDay(DateInfo dateDebut, DateInfo dateFin) throws IllegalArgumentException {
        if(dateDebut == null || dateFin == null){
            throw new IllegalArgumentException("dateDebut or dateFin is null");
        }
        int[] total = new int[7];
        Date debut = dateDebut.getDate();
        Date fin = dateFin.getDate();
        for(Comptage c : this.lesComptages){
            Date date = c.getLaDate().getDate();
            if(date.after(debut) && date.before(fin)){
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
     * @param dateDebut the start date to compute
     * @param dateFin the end date to compute
     * @return the average passage per day of the Compteur
     * @throws IllegalArgumentException if either dateDebut or dateFin is null
     */
    public float[] averagePassagesPerDay(DateInfo dateDebut, DateInfo dateFin) throws IllegalArgumentException {
        if(dateDebut == null || dateFin == null){
            throw new IllegalArgumentException("dateDebut or dateFin is null");
        }
        float[] total = new float[7];
        int nbComptages = 0;
        Date debut = dateDebut.getDate();
        Date fin = dateFin.getDate();
        for(Comptage c : this.lesComptages){
            Date date = c.getLaDate().getDate();
            if(date.after(debut) && date.before(fin)){
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
package modele.entities;

import java.util.ArrayList;


/**
 * Class Quartier who represent a MySQL table
 * It's a model who can contain all attributes and methods to access at data
 * It also use JDBC to interact with the database
 * @author Groupe 4B2
 */
public class Quartier {

    // ---------------- Attributes ---------------- //

    private int idQuartier;
    private String nomQuartier;
    private float longueurPisteVelo;
    private ArrayList<Compteur> lesCompteurs;

    // ---------------- Constructors ---------------- //

    /**
     * Default constructor
     * @param idQuartier the id of the Quartier
     * @param nomQuartier the name of the Quartier
     * @param longueurPisteVelo the length of the bike path
     */
    public Quartier(int idQuartier, String nomQuartier, float longueurPisteVelo) {
        this.idQuartier = idQuartier;
        this.nomQuartier = nomQuartier;
        this.longueurPisteVelo = longueurPisteVelo;
        this.lesCompteurs = new ArrayList<Compteur>();
    }

    // ---------------- Getters & Setters ---------------- //

    /**
     * Get the id of the Quartier
     * @return the id of the Quartier
     */
    public int getIdQuartier() {
        return idQuartier;
    }

    /**
     * Set the id of the Quartier
     * @param idQuartier the id of the Quartier
     */
    public void setIdQuartier(int idQuartier) {
        this.idQuartier = idQuartier;
    }

    /**
     * Get the name of the Quartier
     * @return the name of the Quartier
     */
    public String getNomQuartier() {
        return nomQuartier;
    }

    /**
     * Set the name of the Quartier
     * @param nomQuartier the name of the Quartier
     * @throws NullPointerException if nomQuartier is null
     */
    public void setNomQuartier(String nomQuartier) throws NullPointerException{
        if(nomQuartier == null){
            throw new NullPointerException("nomQuartier ne peut pas être null");
        }
        this.nomQuartier = nomQuartier;
    }

    /**
     * Get the length of the bike path
     * @return the length of the bike path
     */
    public float getLongueurPisteVelo() {
        return longueurPisteVelo;
    }

    /**
     * Set the length of the bike path
     * @param longueurPisteVelo the length of the bike path
     */
    public void setLongueurPisteVelo(float longueurPisteVelo) {
        this.longueurPisteVelo = longueurPisteVelo;
    }

    // ---------------- Add & Remove ---------------- //

    /**
     * Add a Compteur to the Quartier
     * @param compteur the Compteur to add
     */
    public void addCompteur(Compteur compteur){
        this.lesCompteurs.add(compteur);
    }

    /**
     * Remove a Compteur to the Quartier
     * @param compteur the Compteur to remove
     */
    public void removeCompteur(Compteur compteur){
        this.lesCompteurs.remove(compteur);
    }

    // ---------------- Methods ---------------- //

    /**
     * To String method
     * Use a StringBuilder to concatenate String
     * @return the String of the Quartier
     */
    public String toString() {
        StringBuilder ret = new StringBuilder("Quartier(");
        ret.append("idQuartier : ").append(this.idQuartier).append(", ");
        ret.append("nomQuartier : ").append(this.nomQuartier).append(", ");
        ret.append("longueurPisteVelo : ").append(this.longueurPisteVelo).append(")");
        return ret.toString();
    }


    /**
     * Compute the total passage of the Quartier
     * @param dateDebut the start date to compute
     * @param dateFin the end date to compute
     * @return the total passage of the Quartier
     * @throws IllegalArgumentException if either dateDebut or dateFin is null
     */
    public int totalPassages(DateInfo dateDebut, DateInfo dateFin) throws IllegalArgumentException {
        if(dateDebut == null || dateFin == null){
            throw new IllegalArgumentException("dateDebut or dateFin is null");
        }
        int total = 0;
        for(Compteur compteur : this.lesCompteurs){
            total += compteur.totalPassages(dateDebut, dateFin);
        }
        return total;
    }


    /**
     * Compute the average passage of the Quartier
     * @param dateDebut the start date to compute
     * @param dateFin the end date to compute
     * @return the average passage of the Quartier
     * @throws IllegalArgumentException if either dateDebut or dateFin is null
     */
    public float averagePassages(DateInfo dateDebut, DateInfo dateFin) throws IllegalArgumentException {
        if(dateDebut == null || dateFin == null){
            throw new IllegalArgumentException("dateDebut or dateFin is null");
        }
        float total = 0;
        for(Compteur compteur : this.lesCompteurs){
            total += compteur.averagePassages(dateDebut, dateFin);
        }
        int nbCompteurs = this.lesCompteurs.size();
        if(nbCompteurs != 0){
            total /= nbCompteurs;
        }
        return total;
    }


    /**
     * Compute the total passage per hour of the Quartier
     * @param dateDebut the start date to compute
     * @param dateFin the end date to compute
     * @return the total passage per hour of the Quartier
     * @throws IllegalArgumentException if either dateDebut or dateFin is null
     */
    public int[] totalPassagesPerHour(DateInfo dateDebut, DateInfo dateFin) throws IllegalArgumentException {
        if(dateDebut == null || dateFin == null){
            throw new IllegalArgumentException("dateDebut or dateFin is null");
        }
        int[] total = new int[24];
        for(Compteur compteur : this.lesCompteurs){
            int[] totalCompteur = compteur.totalPassagesPerHour(dateDebut, dateFin);
            for(int i = 0; i < 24; i++){
                total[i] += totalCompteur[i];
            }
        }
        return total;
    }


    /**
     * Compute the average passage per hour of the Quartier
     * @param dateDebut the start date to compute
     * @param dateFin the end date to compute
     * @return the average passage per hour of the Quartier
     * @throws IllegalArgumentException if either dateDebut or dateFin is null
     */
    public float[] averagePassagesPerHour(DateInfo dateDebut, DateInfo dateFin) throws IllegalArgumentException {
        if(dateDebut == null || dateFin == null){
            throw new IllegalArgumentException("dateDebut or dateFin is null");
        }
        float[] total = new float[24];
        for(Compteur compteur : this.lesCompteurs){
            float[] totalCompteur = compteur.averagePassagesPerHour(dateDebut, dateFin);
            for(int i = 0; i < 24; i++){
                total[i] += totalCompteur[i];
            }
        }
        int nbCompteurs = this.lesCompteurs.size();
        if(nbCompteurs != 0){
            for(int i = 0; i < 24; i++){
                total[i] /= nbCompteurs;
            }
        }
        return total;
    }


    /**
     * Compute the total passage per day of the Quartier
     * @param dateDebut the start date to compute
     * @param dateFin the end date to compute
     * @return the total passage per day of the Quartier
     * @throws IllegalArgumentException if either dateDebut or dateFin is null
     */
    public int[] totalPassagesPerDay(DateInfo dateDebut, DateInfo dateFin) throws IllegalArgumentException {
        if(dateDebut == null || dateFin == null){
            throw new IllegalArgumentException("dateDebut or dateFin is null");
        }
        int[] total = new int[7];
        for(Compteur compteur : this.lesCompteurs){
            int[] totalCompteur = compteur.totalPassagesPerDay(dateDebut, dateFin);
            for(int i = 0; i < 7; i++){
                total[i] += totalCompteur[i];
            }
        }
        return total;
    }


    /**
     * Compute the average passage per day of the Quartier
     * @param dateDebut the start date to compute
     * @param dateFin the end date to compute
     * @return the average passage per day of the Quartier
     * @throws IllegalArgumentException if either dateDebut or dateFin is null
     */
    public float[] averagePassagesPerDay(DateInfo dateDebut, DateInfo dateFin) throws IllegalArgumentException {
        if(dateDebut == null || dateFin == null){
            throw new IllegalArgumentException("dateDebut or dateFin is null");
        }
        float[] total = new float[7];
        for(Compteur compteur : this.lesCompteurs){
            float[] totalCompteur = compteur.averagePassagesPerDay(dateDebut, dateFin);
            for(int i = 0; i < 7; i++){
                total[i] += totalCompteur[i];
            }
        }
        int nbCompteurs = this.lesCompteurs.size();
        if(nbCompteurs != 0){
            for(int i = 0; i < 7; i++){
                total[i] /= nbCompteurs;
            }
        }
        return total;
    }

}
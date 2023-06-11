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
     * @return the String of the Quartier
     */
    public String toString() {
        String ret = "Quartier(";
        ret += "idQuartier : " + this.idQuartier + ", ";
        ret += "nomQuartier : " + this.nomQuartier + ", ";
        ret += "longueurPisteVelo : " + this.longueurPisteVelo + ", ";
        ret += "totalPassages : " + this.totalPassages(null) + ", ";
        ret += "averagePassages : " + this.averagePassages(null) + ")";
        return ret;
    }

    /**
     * Compute the total passage of the Quartier
     * @param laDate the date to compute, if null, compute for all dates
     * @return the total passage of the Quartier
     */
    public int totalPassages(DateInfo laDate){
        int total = 0;
        for(Compteur compteur : this.lesCompteurs){
            total += compteur.totalPassages(laDate);
        }
        return total;
    }

    /**
     * Compute the average passage of the Quartier
     * @param laDate the date to compute, if null, compute for all dates
     * @return the average passage of the Quartier
     */
    public float averagePassages(DateInfo laDate){
        float total = 0;
        for(Compteur compteur : this.lesCompteurs){
            total += compteur.averagePassages(laDate);
        }
        int nbCompteurs = this.lesCompteurs.size();
        if(nbCompteurs != 0){
            total /= nbCompteurs;
        }
        return total;
    }

    /**
     * Compute the total passage per hour of the Quartier
     * @param laDate the date to compute, if null, compute for all dates
     * @return the total passage per hour of the Quartier
     */
    public int[] totalPassagesPerHour(DateInfo laDate){
        int[] total = new int[24];
        for(Compteur compteur : this.lesCompteurs){
            int[] totalCompteur = compteur.totalPassagesPerHour(laDate);
            for(int i = 0; i < 24; i++){
                total[i] += totalCompteur[i];
            }
        }
        return total;
    }

    /**
     * Compute the average passage per hour of the Quartier
     * @param laDate the date to compute, if null, compute for all dates
     * @return the average passage per hour of the Quartier
     */
    public float[] averagePassagesPerHour(DateInfo laDate){
        float[] total = new float[24];
        for(Compteur compteur : this.lesCompteurs){
            float[] totalCompteur = compteur.averagePassagesPerHour(laDate);
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
     * @param laDate the date to compute, if null, compute for all dates
     * @return the total passage per day of the Quartier
     */
    public int[] totalPassagesPerDay(DateInfo laDate){
        int[] total = new int[7];
        for(Compteur compteur : this.lesCompteurs){
            int[] totalCompteur = compteur.totalPassagesPerDay(laDate);
            for(int i = 0; i < 7; i++){
                total[i] += totalCompteur[i];
            }
        }
        return total;
    }

    /**
     * Compute the average passage per day of the Quartier
     * @param laDate the date to compute, if null, compute for all dates
     * @return the average passage per day of the Quartier
     */
    public float[] averagePassagesPerDay(DateInfo laDate){
        float[] total = new float[7];
        for(Compteur compteur : this.lesCompteurs){
            float[] totalCompteur = compteur.averagePassagesPerDay(laDate);
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
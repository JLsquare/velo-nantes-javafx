package modele;

import java.sql.*;
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

    /**
     * Constructor with ResultSet
     * @param rs the ResultSet
     * @throws SQLException if there is an error with the ResultSet
     */
    public Quartier(ResultSet rs) throws SQLException{
        this.idQuartier = rs.getInt("idQuartier");
        this.nomQuartier = rs.getString("nomQuartier");
        this.longueurPisteVelo = rs.getFloat("longueurPisteVelo");
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

    // ---------------- Methods ---------------- //

    /**
     * Add a Compteur to the Quartier
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

    /**
     * To String method
     * @return the String of the Quartier
     */
    public String toString() {
        String string = "Quartier(" + this.idQuartier + ", " + this.nomQuartier + ", " + this.longueurPisteVelo + ", " + this.totalPassage() + ", " + this.averagePassages() + ')';
        return string;
    }

    /**
     * Compute the total passage of the Quartier
     * @return the total passage of the Quartier
     */
    public int totalPassage(){
        int total = 0;
        for(Compteur compteur : this.lesCompteurs){
            total += compteur.totalPassage();
        }
        return total;
    }

    /**
     * Compute the average passage of the Quartier
     * @return the average passage of the Quartier
     */
    public float averagePassages(){
        float total = 0;
        for(Compteur compteur : this.lesCompteurs){
            total += compteur.averagePassages();
        }
        int nbCompteurs = this.lesCompteurs.size();
        if(nbCompteurs != 0){
            total /= nbCompteurs;
        }
        return total;
    }
}
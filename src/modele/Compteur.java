package modele;

import java.sql.*;

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
    private int leQuartier;

    // ---------------- Constructors ---------------- //

    /**
     * Default constructor
     * @param idCompteur the id of the Compteur
     * @param nomCompteur the name of the Compteur
     * @param sens the direction of the Compteur
     * @param coordX the X coordinate of the Compteur
     * @param coordY the Y coordinate of the Compteur
     * @param leQuartier the Quartier of the Compteur
     */
    public Compteur(int idCompteur, String nomCompteur, String sens, float coordX, float coordY, int leQuartier) throws NullPointerException {
        if(nomCompteur == null || sens == null){
            throw new NullPointerException("nomCompteur or sens is null");
        }
        this.idCompteur = idCompteur;
        this.nomCompteur = nomCompteur;
        this.sens = sens;
        this.coordX = coordX;
        this.coordY = coordY;
        this.leQuartier = leQuartier;
    }

    /**
     * Constructor with ResultSet
     * @param rs the ResultSet
     * @throws SQLException if there is an error with the ResultSet
     */
    public Compteur(ResultSet rs) throws SQLException {
        this.idCompteur = rs.getInt("idCompteur");
        this.nomCompteur = rs.getString("nomCompteur");
        this.sens = rs.getString("sens");
        this.coordX = rs.getFloat("coord_X");
        this.coordY = rs.getFloat("coord_Y");
        this.leQuartier = rs.getInt("leQuartier");
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
     */
    public void setNomCompteur(String nomCompteur) {
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
    public int getLeQuartier() {
        return leQuartier;
    }

    /**
     * Set the Quartier of the Compteur
     * @param leQuartier the Quartier of the Compteur
     */
    public void setLeQuartier(int leQuartier) {
        this.leQuartier = leQuartier;
    }

    // ---------------- Methods ---------------- //

    /**
     * To String method
     * @return the String of the Compteur
     */
    public String toString() {
        String ret = "Compteur(" + this.idCompteur + ", " + this.nomCompteur + ", " + this.sens + ", " + this.coordX + ", " + this.coordY + ", " + this.leQuartier + ")";
        return ret;
    }
}
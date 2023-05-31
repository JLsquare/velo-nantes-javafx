package modele;

import java.sql.*;
import java.util.HashMap;

public class Compteur{

    // ---------------- Attributes ---------------- //

    public static HashMap<Integer, Compteur> lesCompteurs = new HashMap<Integer, Compteur>();
    private int idCompteur;
    private String nomCompteur;
    private String sens;
    private float coord_X;
    private float coord_Y;
    private Quartier leQuartier;

    // ---------------- Constructors ---------------- //

    /**
     * Default constructor
     * @param idCompteur the id of the Compteur
     * @param nomCompteur the name of the Compteur
     * @param sens the direction of the Compteur
     * @param coord_X the X coordinate of the Compteur
     * @param coord_Y the Y coordinate of the Compteur
     * @param leQuartier the Quartier of the Compteur
     */
    public Compteur(int idCompteur, String nomCompteur, String sens, float coord_X, float coord_Y, int leQuartier) throws NullPointerException {
        if(nomCompteur == null || sens == null){
            throw new NullPointerException("nomCompteur or sens is null");
        }
        this.idCompteur = idCompteur;
        this.nomCompteur = nomCompteur;
        this.sens = sens;
        this.coord_X = coord_X;
        this.coord_Y = coord_Y;
        this.leQuartier = Quartier.getQuartier(leQuartier);

        lesCompteurs.put(idCompteur, this);
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
        this.coord_X = rs.getFloat("coord_X");
        this.coord_Y = rs.getFloat("coord_Y");
        this.leQuartier = Quartier.getQuartier(idCompteur);

        lesCompteurs.put(idCompteur, this);
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
    public float getCoord_X() {
        return coord_X;
    }

    /**
     * Set the X coordinate of the Compteur
     * @param coord_X the X coordinate of the Compteur
     */
    public void setCoord_X(float coord_X) {
        this.coord_X = coord_X;
    }

    /**
     * Get the Y coordinate of the Compteur
     * @return the Y coordinate of the Compteur
     */
    public float getCoord_Y() {
        return coord_Y;
    }

    /**
     * Set the Y coordinate of the Compteur
     * @param coord_Y the Y coordinate of the Compteur
     */
    public void setCoord_Y(float coord_Y) {
        this.coord_Y = coord_Y;
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

    /**
     * Get the Compteur with the id
     * @param idCompteur the id of the Compteur
     * @return the Compteur with the id
     * @throws NullPointerException if the Compteur is not found
     */
    public static Compteur getCompteur(int idCompteur) throws NullPointerException{
        Compteur ret = lesCompteurs.get(idCompteur);
        if(ret == null){
            throw new NullPointerException("Compteur not found");
        }
        return ret;
    }

    // ---------------- Methods ---------------- //

    /**
     * To String method
     * @return the String of the Compteur
     */
    public String toString() {
        String ret = "Compteur(" + idCompteur + ", " + nomCompteur + ", " + sens + ", " + coord_X + ", " + coord_Y + ", " + leQuartier + ")";
        return ret;
    }

    /**
     * Get the query to insert the Compteur
     * @return the query to insert the Compteur as a String
     */
    public String toInsertQuery(){
        String ret = "INSERT INTO COMPTEUR VALUES (" + idCompteur + ", '" + nomCompteur + "', '" + sens + "', " + coord_X + ", " + coord_Y + ", " + leQuartier.getIdQuartier() + ")";
        return ret;
    }  

    /**
     * Get the query to update the Compteur
     * @return the query to update the Compteur as a String
     */
    public String toUpdateQuery(){
        String ret = "UPDATE COMPTEUR SET nomCompteur = '" + nomCompteur + "', sens = '" + sens + "', coord_X = " + coord_X + ", coord_Y = " + coord_Y + ", idQuartier = " + leQuartier.getIdQuartier() + " WHERE idCompteur = " + idCompteur;
        return ret;
    }

    /**
     * Get the query to delete the Compteur
     * @return the query to delete the Compteur as a String
     */
    public String toDeleteQuery(){
        String ret = "DELETE FROM COMPTEUR WHERE idCompteur = " + idCompteur;
        return ret;
    }
}
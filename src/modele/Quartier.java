package modele;

import java.util.HashMap;
import java.sql.*;


/**
 * Class Quartier who represent a MySQL table
 * It's a model who can contain all attributes and methods to access at data
 * It also use JDBC to interact with the database
 * @author Groupe 4B2
 */
public class Quartier {

    // ---------------- Attributes ---------------- //

    public static HashMap<Integer, Quartier> lesQuartiers = new HashMap<Integer, Quartier>();
    private int idQuartier;
    private String nomQuartier;
    private float longueurPisteVelo;

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

        lesQuartiers.put(idQuartier, this);
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

        lesQuartiers.put(idQuartier, this);
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

    /**
     * Get the Quartier with the id
     * @param idQuartier the id of the Quartier
     * @return the Quartier with the id
     * @throws NullPointerException if the Quartier doesn't exist
     */
    public static Quartier getQuartier(int idQuartier) throws NullPointerException{
        Quartier ret = lesQuartiers.get(idQuartier);
        if(ret == null){
            throw new NullPointerException("The Quartier with id " + idQuartier + " doesn't exist");
        }
        return ret;
    }

    // ---------------- Methods ---------------- //

    /**
     * To String method
     * @return the String of the Quartier
     */
    public String toString() {
        String string = "Quartier(" + this.idQuartier + ", " + this.nomQuartier + ", " + this.longueurPisteVelo + ')';
        return string;
    }

    /**
     * Generate the Quartier SQL Insert Query
     * @return the generated SQL Insert Query as a String
     */
    public String toInsertQuery(){
        String query = "INSERT INTO QUARTIER VALUES(" + this.idQuartier + ", '" + this.nomQuartier + "', " + this.longueurPisteVelo + ");";
        return query;
    }

    /**
     * Generate the Quartier SQL Update Query
     * @return the generated SQL Update Query as a String
     */
    public String toUpdateQuery(){
        String query = "UPDATE QUARTIER SET nomQuartier = '" + this.nomQuartier + "', longueurPisteVelo = " + this.longueurPisteVelo + " WHERE idQuartier = " + this.idQuartier + ";";
        return query;
    }

    /**
     * Generate the Quartier SQL Delete Query
     * @return the generated SQL Delete Query as a String
     */
    public String toDeleteQuery(){
        String query = "DELETE FROM QUARTIER WHERE idQuartier = " + this.idQuartier + ";";
        return query;
    }

}
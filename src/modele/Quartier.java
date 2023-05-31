package modele;

import java.util.ArrayList;
import java.sql.*;


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
     * Constructor with ArrayList of Compteur
     * @param idQuartier the id of the Quartier
     * @param nomQuartier the name of the Quartier
     * @param longueurPisteVelo the length of the bike path
     * @param lesCompteurs the ArrayList of Compteur
     * @throws NullPointerException if lesCompteurs is null
     */
    public Quartier(int idQuartier, String nomQuartier, float longueurPisteVelo, ArrayList<Compteur> lesCompteurs) throws NullPointerException{
        if(lesCompteurs == null){
            throw new NullPointerException("lesCompteurs ne peut pas être null");
        }
        this.idQuartier = idQuartier;
        this.nomQuartier = nomQuartier;
        this.longueurPisteVelo = longueurPisteVelo;
        this.lesCompteurs = lesCompteurs;
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

    // ---------------- Compteur ---------------- //

    /**
     * Add a Compteur to the ArrayList of Compteur
     * @param unCompteur the Compteur to add
     * @throws NullPointerException if unCompteur is null
     */
    public void addCompteur(Compteur unCompteur) throws NullPointerException{
        if(unCompteur == null){
            throw new NullPointerException("unCompteur ne peut pas être null");
        }
        this.lesCompteurs.add(unCompteur);
    }

    /**
     * Get a Compteur from the ArrayList of Compteur
     * @param index the index of the Compteur
     * @return the Compteur
     * @throws IndexOutOfBoundsException if index is out of bounds
     */
    public Compteur getCompteur(int index) throws IndexOutOfBoundsException{
        if(index < 0 || index >= this.lesCompteurs.size()){
            throw new IndexOutOfBoundsException("index doit être compris entre 0 et " + (this.lesCompteurs.size() - 1));
        }
        return this.lesCompteurs.get(index);
    }

    /**
     * Remove a Compteur from the ArrayList of Compteur
     * @param index the index of the Compteur
     * @throws IndexOutOfBoundsException if index is out of bounds
     */
    public void removeCompteur(int index) throws IndexOutOfBoundsException{
        if(index < 0 || index >= this.lesCompteurs.size()){
            throw new IndexOutOfBoundsException("index doit être compris entre 0 et " + (this.lesCompteurs.size() - 1));
        }
        this.lesCompteurs.remove(index);
    }

    // ---------------- Other ---------------- //

    /**
     * To String method
     * @return the String of the Quartier
     */
    public String toString() {
        String string = "Quartier{" + "idQuartier : " + this.idQuartier + ", nomQuartier : " + this.nomQuartier + ", longueurPisteVelo : " + this.longueurPisteVelo + '}';
        return string;
    }

    /**
     * Insert the Quartier into the database
     * @throws SQLException if there is an error with the SQL request
     * @throws NoConnectionException if there is no connection to the database
     */
    public void insert() throws SQLException, NoConnectionException{
        Connection connection = Database.getWriteConnection();
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement("INSERT INTO Quartier (idQuartier, nomQuartier, longueurPisteVelo) VALUES (?, ?, ?)");
            statement.setInt(1, this.idQuartier);
            statement.setString(2, this.nomQuartier);
            statement.setFloat(3, this.longueurPisteVelo);
            statement.executeUpdate();
        } finally {
            if (statement != null) {
                statement.close();
            }
        }
    }

    /**
     * Update the Quartier into the database
     * @throws SQLException if there is an error with the SQL request
     * @throws NoConnectionException if there is no connection to the database
     */
    public void update() throws SQLException, NoConnectionException{
        Connection connection = Database.getWriteConnection();
        PreparedStatement statement = null;
        try{
            statement = connection.prepareStatement("UPDATE Quartier SET nomQuartier = ?, longueurPisteVelo = ? WHERE idQuartier = ?");
            statement.setString(1, this.nomQuartier);
            statement.setFloat(2, this.longueurPisteVelo);
            statement.setInt(3, this.idQuartier);
            statement.executeUpdate();
        } finally {
            if (statement != null) {
                statement.close();
            }
        }

    }

    /**
     * Delete the Quartier from the database
     * @throws SQLException if there is an error with the SQL request
     * @throws NoConnectionException if there is no connection to the database
     */
    public void delete() throws SQLException, NoConnectionException{
        Connection connection = Database.getWriteConnection();
        PreparedStatement statement = null;
        try{
            statement = connection.prepareStatement("DELETE FROM Quartier WHERE idQuartier = ?");
            statement.setInt(1, this.idQuartier);
            statement.executeUpdate();
        } finally {
            if (statement != null) {
                statement.close();
            }
        }
    }
}
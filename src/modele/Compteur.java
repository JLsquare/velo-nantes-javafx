package modele;

import java.sql.*;

public class Compteur{

    // ---------------- Attributes ---------------- //

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
    public Compteur(int idCompteur, String nomCompteur, String sens, float coord_X, float coord_Y, Quartier leQuartier) throws NullPointerException {
        if(nomCompteur == null || sens == null || leQuartier == null){
            throw new NullPointerException("nomCompteur, sens or leQuartier is null");
        }
        this.idCompteur = idCompteur;
        this.nomCompteur = nomCompteur;
        this.sens = sens;
        this.coord_X = coord_X;
        this.coord_Y = coord_Y;
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
        this.coord_X = rs.getFloat("coord_X");
        this.coord_Y = rs.getFloat("coord_Y");
        this.leQuartier = Quartier.getQuartier(idCompteur);
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
     * Insert the Compteur into the database
     * @throws SQLException if there is an error with the SQL request
     * @throws NoConnectionException if there is no connection to the database
     */
    public void insert() throws SQLException, NoConnectionException{
        Connection connection = Database.getWriteConnection();
        PreparedStatement statement = null;
        try{
            statement = connection.prepareStatement("INSERT INTO Compteur (idCompteur, nomCompteur, sens, coord_X, coord_Y, idQuartier) VALUES (?, ?, ?, ?, ?, ?)");
            statement.setInt(1, idCompteur);
            statement.setString(2, nomCompteur);
            statement.setString(3, sens);
            statement.setFloat(4, coord_X);
            statement.setFloat(5, coord_Y);
            statement.setInt(6, leQuartier.getIdQuartier());
            statement.executeUpdate();
        } finally {
            if(statement != null){
                statement.close();
            }
        }
    }

    /**
     * Update the Compteur into the database
     * @throws SQLException if there is an error with the SQL request
     * @throws NoConnectionException if there is no connection to the database
     */
    public void update() throws SQLException, NoConnectionException{
        Connection connection = Database.getWriteConnection();
        PreparedStatement statement = null;
        try{
            statement = connection.prepareStatement("UPDATE Compteur SET nomCompteur = ?, sens = ?, coord_X = ?, coord_Y = ?, idQuartier = ? WHERE idCompteur = ?");
            statement.setString(1, nomCompteur);
            statement.setString(2, sens);
            statement.setFloat(3, coord_X);
            statement.setFloat(4, coord_Y);
            statement.setInt(5, leQuartier.getIdQuartier());
            statement.setInt(6, idCompteur);
            statement.executeUpdate();
        } finally {
            if(statement != null){
                statement.close();
            }
        }
    }

    /**
     * Delete the Compteur from the database
     * @throws SQLException if there is an error with the SQL request
     * @throws NoConnectionException if there is no connection to the database
     */
    public void delete() throws SQLException, NoConnectionException{
        Connection connection = Database.getWriteConnection();
        PreparedStatement statement = null;
        try{
            statement = connection.prepareStatement("DELETE FROM Compteur WHERE idCompteur = ?");
            statement.setInt(1, idCompteur);
            statement.executeUpdate();
        } finally {
            if(statement != null){
                statement.close();
            }
        }
    }    
}
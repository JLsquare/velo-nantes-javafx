package modele.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import modele.database.Database;
import modele.entities.Quartier;

/**
 * Class QuartierDao to implement the DAO pattern for Quartier
 * @author Groupe 4B2
 */
public class QuartierDao implements IDao<Quartier>{

    // ---------------- Attributes ---------------- //

    private Database database;
    private ArrayList<Quartier> lesQuartiers;

    // ---------------- Constructors ---------------- //

    /**
     * Constructor of QuartierDao
     * @param db the database
     */
    public QuartierDao(Database db) {
        this.database = db;
        this.lesQuartiers = new ArrayList<Quartier>();
    }

    // ---------------- Getters and Setters ---------------- //

    /**
     * Get a Quartier by its id
     * @param leQuartier the id of the Quartier
     * @return the Quartier
     */
    public Quartier get(int leQuartier) {
        Quartier quartier = null;
        boolean found = false;
        int i = 0;
        while(!found && i < lesQuartiers.size()) {
            if(lesQuartiers.get(i).getIdQuartier() == leQuartier) {
                quartier = lesQuartiers.get(i);
                found = true;
            }
            i++;
        }
        return quartier;
    }

    /**
     * Get all the Quartier
     * @return an ArrayList of Quartier
     */
    public ArrayList<Quartier> getAll() {
        return lesQuartiers;
    }

    // ---------------- Methods ---------------- //

    /**
     * Read all the Quartier from the database
     * @throws SQLException if an error occurs
     */
    public void readAll() throws SQLException {
        PreparedStatement ps = database.preparedReadStatment("SELECT * FROM QUARTIER");
        ResultSet rs = ps.executeQuery();
        while(rs.next()) {
            int idQuartier = rs.getInt("idQuartier");
            String nomQuartier = rs.getString("nomQuartier");
            float longueurPisteVelo = rs.getFloat("longueurPisteVelo");
            
            Quartier quartier = new Quartier(idQuartier, nomQuartier, longueurPisteVelo);
            this.lesQuartiers.add(quartier);
        }
        this.lesQuartiers.add(new Quartier(0, "Other", 0));
    }

    /**
     * Add a Quartier to the database
     * @param quartier the Quartier to add
     * @throws SQLException if an error occurs
     */
    public void add(Quartier quartier) throws SQLException {
        String query = "INSERT INTO QUARTIER VALUES(?, ?, ?)";
        PreparedStatement ps = database.preparedWriteStatment(query);
        ps.setInt(1, quartier.getIdQuartier());
        ps.setString(2, quartier.getNomQuartier());
        ps.setFloat(3, quartier.getLongueurPisteVelo());
        ps.executeUpdate();
    }

    /**
     * Remove a Quartier from the database
     * @param quartier the Quartier to remove
     * @throws SQLException if an error occurs
     */
    public void remove(Quartier quartier) throws SQLException {
        String query = "DELETE FROM QUARTIER WHERE idQuartier = ?";
        PreparedStatement ps = database.preparedWriteStatment(query);
        ps.setInt(1, quartier.getIdQuartier());
        ps.executeUpdate();
        ps.close();
    }

    /**
     * Update a Quartier from the database
     * @param quartier the Quartier to update
     * @throws SQLException if an error occurs
     */
    public void update(Quartier quartier) throws SQLException {
        String query = "UPDATE QUARTIER SET nomQuartier = ?, longueurPisteVelo = ? WHERE idQuartier = ?";
        PreparedStatement ps = database.preparedWriteStatment(query);
        ps.setString(1, quartier.getNomQuartier());
        ps.setFloat(2, quartier.getLongueurPisteVelo());
        ps.setInt(3, quartier.getIdQuartier());
        ps.executeUpdate();
        ps.close();
    }
}

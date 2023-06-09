package modele;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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
     * @param compteurDao the CompteurDao to use
     */
    public QuartierDao(Database db) {
        this.database = db;
        this.lesQuartiers = new ArrayList<Quartier>();
    }

    // ---------------- Getters and Setters ---------------- //

    /**
     * Get a Quartier by its id
     * @param id the id of the Quartier
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
     */
    public void readAll() throws SQLException {
        PreparedStatement preparedStatement = database.preparedReadStatment("SELECT * FROM QUARTIER");
        ResultSet rs = preparedStatement.executeQuery();
        while(rs.next()) {
            Quartier quartier = new Quartier(rs);            
            this.lesQuartiers.add(quartier);
        }
    }

    /**
     * Add a Quartier to the database
     * @param quartier the Quartier to add
     */
    public void add(Quartier quartier) throws SQLException {
        String query = "INSERT INTO QUARTIER VALUES(?, ?, ?)";
        try (PreparedStatement stmt = database.preparedWriteStatment(query)) {
            stmt.setInt(1, quartier.getIdQuartier());
            stmt.setString(2, quartier.getNomQuartier());
            stmt.setFloat(3, quartier.getLongueurPisteVelo());
            stmt.executeUpdate();
        }
    }

    /**
     * Remove a Quartier from the database
     * @param quartier the Quartier to remove
     */
    public void remove(Quartier quartier) throws SQLException {
        String query = "DELETE FROM QUARTIER WHERE idQuartier = ?";
        try (PreparedStatement stmt = database.preparedWriteStatment(query)) {
            stmt.setInt(1, quartier.getIdQuartier());
            stmt.executeUpdate();
        }
    }

    /**
     * Update a Quartier from the database
     * @param quartier the Quartier to update
     */
    public void update(Quartier quartier) throws SQLException {
        String query = "UPDATE QUARTIER SET nomQuartier = ?, longueurPisteVelo = ? WHERE idQuartier = ?";
        try (PreparedStatement stmt = database.preparedWriteStatment(query)) {
            stmt.setString(1, quartier.getNomQuartier());
            stmt.setFloat(2, quartier.getLongueurPisteVelo());
            stmt.setInt(3, quartier.getIdQuartier());
            stmt.executeUpdate();
        }
    }
}

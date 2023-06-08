package modele;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Class ComptageDao to implement the DAO pattern for Comptage
 * @author Groupe 4B2
 */
public class ComptageDao implements IDao<Comptage>{

    // ---------------- Attributes ---------------- //

    private Database database;
    private ArrayList<Comptage> lesComptages;

    // ---------------- Constructors ---------------- //

    /**
     * Constructor of ComptageDao
     * @param db the database
     */
    public ComptageDao(Database db) {
        this.database = db;
        this.lesComptages = new ArrayList<Comptage>();
    }

    // ---------------- Getters and Setters ---------------- //

    /**
     * Get all the Comptage
     * @return an ArrayList of Comptage
     */
    public ArrayList<Comptage> getAll() {
        return lesComptages;
    }

    // ---------------- Methods ---------------- //

    /**
     * Get all the Comptage from the database
     */
    public void readAll() throws SQLException {
        PreparedStatement preparedStatement = database.preparedReadStatment("SELECT * FROM COMPTAGE");
        ResultSet rs = preparedStatement.executeQuery();
        while(rs.next()) {
            this.lesComptages.add(new Comptage(rs));
        }
    }

    /**
     * Add a Comptage to the database
     * @param comptage the Comptage to add
     */
    public void add(Comptage comptage) throws SQLException {
        StringBuilder query = new StringBuilder("INSERT INTO COMPTAGE VALUES(?, ?, ");
        for(int i = 0; i < 24; i++) {
            query.append("?, ");
        }
        query.append("?)");
        PreparedStatement preparedStatement = database.preparedWriteStatment(query.toString());
        preparedStatement.setInt(1, comptage.getLeCompteur());
        preparedStatement.setDate(2, comptage.getLaDate());
        for(int i = 0; i < 24; i++) {
            preparedStatement.setInt(i + 3, comptage.getPassages()[i]);
        }
        preparedStatement.setObject(27, comptage.getAnomalie());
        preparedStatement.executeUpdate();
    }

    /**
     * Remove a Comptage from the database
     * @param comptage the Comptage to remove
     */
    public void remove(Comptage comptage) throws SQLException {
        String query = "DELETE FROM COMPTAGE WHERE leCompteur = ? AND dateComptage = ?";
        PreparedStatement preparedStatement = database.preparedWriteStatment(query);
        preparedStatement.setInt(1, comptage.getLeCompteur());
        preparedStatement.setDate(2, comptage.getLaDate());
        preparedStatement.executeUpdate();
    }

    /**
     * Update a Comptage from the database
     * @param comptage the Comptage to update
     */
    public void update(Comptage comptage) throws SQLException {
        StringBuilder query = new StringBuilder("UPDATE COMPTAGE SET ");
        for(int i = 0; i < 24; i++){
            query.append("h").append(String.format("%02d", i)).append(" = ?, ");
        }
        query.append("anomalie = ? ");
        query.append(" WHERE leCompteur = ? AND dateComptage = ?");
        PreparedStatement preparedStatement = database.preparedWriteStatment(query.toString());
        for(int i = 0; i < 24; i++){
            preparedStatement.setInt(i + 1, comptage.getPassage(i));
        }
        preparedStatement.setString(25, comptage.getAnomalie().toString());
        preparedStatement.setInt(26, comptage.getLeCompteur());
        preparedStatement.setDate(27, comptage.getLaDate());
        preparedStatement.executeUpdate();
    }
}

package modele;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Class CompteurDao to implement the DAO pattern for Compteur
 * @author Groupe 4B2
 */
public class CompteurDao implements IDao<Compteur> {

    // ---------------- Attributes ---------------- //

    private Database database;
    private ComptageDao comptageDao;
    private ArrayList<Compteur> lesCompteurs;

    // ---------------- Constructors ---------------- //

    /**
     * Constructor of CompteurDao
     * @param db the database
     * @param comptageDao the ComptageDao to use
     */
    public CompteurDao(Database db, ComptageDao comptageDao) {
        this.database = db;
        this.comptageDao = comptageDao;
        this.lesCompteurs = new ArrayList<Compteur>();
    }

    // ---------------- Getters and Setters ---------------- //

    /**
     * Get all the Compteur
     * @return an ArrayList of Compteur
     */
    public ArrayList<Compteur> getAll() {
        return lesCompteurs;
    }

    // ---------------- Methods ---------------- //

    /**
     * Read all the Compteur from the database
     */
    public void readAll() throws SQLException {
        PreparedStatement preparedStatement = database.preparedReadStatment("SELECT * FROM COMPTEUR");
        ResultSet rs = preparedStatement.executeQuery();
        while(rs.next()) {
            ArrayList<Comptage> lesComptages = new ArrayList<Comptage>();
            for(Comptage comptage : comptageDao.getAll()) {
                if(comptage.getLeCompteur() == rs.getInt("idCompteur")) {
                    lesComptages.add(comptage);
                }
            }
            this.lesCompteurs.add(new Compteur(rs, lesComptages));
        }
    }

    /**
     * Add a Compteur to the database
     * @param compteur the Compteur to add
     */
    public void add(Compteur compteur) throws SQLException {
        String query = "INSERT INTO COMPTEUR VALUES(?, ?, ?, ?, ?, ?)";
        PreparedStatement preparedStatement = database.preparedReadStatment(query);
        preparedStatement.setInt(1, compteur.getIdCompteur());
        preparedStatement.setString(2, compteur.getNomCompteur());
        preparedStatement.setString(3, compteur.getSens());
        preparedStatement.setFloat(4, compteur.getCoordX());
        preparedStatement.setFloat(5, compteur.getCoordY());
        preparedStatement.setInt(6, compteur.getLeQuartier());
        preparedStatement.executeUpdate();
    }

    /**
     * Remove a Compteur from the database
     * @param compteur the Compteur to remove
     */
    public void remove(Compteur compteur) throws SQLException {
        String query = "DELETE FROM COMPTEUR WHERE idCompteur = ?";
        PreparedStatement preparedStatement = database.preparedWriteStatment(query);
        preparedStatement.setInt(1, compteur.getIdCompteur());
        preparedStatement.executeUpdate();
    }

    /**
     * Update a Compteur from the database
     * @param compteur the Compteur to update
     */
    public void update(Compteur compteur) throws SQLException {
        String query = "UPDATE COMPTEUR SET nomCompteur = ?, sens = ?, coordX = ?, coordY = ?, leQuartier = ? WHERE idCompteur = ?";
        PreparedStatement preparedStatement = database.preparedWriteStatment(query);
        preparedStatement.setString(1, compteur.getNomCompteur());
        preparedStatement.setString(2, compteur.getSens());
        preparedStatement.setFloat(3, compteur.getCoordX());
        preparedStatement.setFloat(4, compteur.getCoordY());
        preparedStatement.setInt(5, compteur.getLeQuartier());
        preparedStatement.setInt(6, compteur.getIdCompteur());
        preparedStatement.executeUpdate();
    }
}

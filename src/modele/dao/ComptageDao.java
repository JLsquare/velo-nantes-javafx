package modele.dao;

import java.sql.*;
import java.util.ArrayList;

import modele.database.Database;
import modele.entities.*;

/**
 * Class ComptageDao to implement the DAO pattern for Comptage
 * @author Groupe 4B2
 */
public class ComptageDao implements IDao<Comptage>{

    // ---------------- Attributes ---------------- //

    private Database database;
    private CompteurDao compteurDao;
    private DateInfoDao dateInfoDao;
    private ArrayList<Comptage> lesComptages;

    // ---------------- Constructors ---------------- //

    /**
     * Constructor of ComptageDao
     * @param db the database
     * @param compteurDao the CompteurDao to use
     * @param dateInfoDao the DateInfoDao to use
     */
    public ComptageDao(Database db, CompteurDao compteurDao, DateInfoDao dateInfoDao) {
        this.database = db;
        this.compteurDao = compteurDao;
        this.dateInfoDao = dateInfoDao;
        this.lesComptages = new ArrayList<Comptage>();
    }

    // ---------------- Getters and Setters ---------------- //

    /**
     * Get a Comptage by it's DateInfo and Compteur
     * @param laDate the DateInfo of the Comptage
     * @param leCompteur the Compteur of the Comptage 
     * @return the Comptage
     */
    public Comptage get(DateInfo laDate, Compteur leCompteur) {
        Comptage comptage = null;
        boolean found = false;
        int i = 0;
        while(!found && i < lesComptages.size()) {
            if(lesComptages.get(i).getLaDate() == laDate && lesComptages.get(i).getLeCompteur() == leCompteur) {
                comptage = lesComptages.get(i);
                found = true;
            }
            i++;
        }
        return comptage;
    }

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
     * @throws SQLException if an error occurs
     */
    public void readAll() throws SQLException {
        PreparedStatement ps = database.preparedReadStatment("SELECT * FROM COMPTAGE");
        ResultSet rs = ps.executeQuery();
        while(rs.next()) {
            int[] passages = new int[24];
            for(int i = 0; i < 24; i++) {
                passages[i] = rs.getInt("h" + String.format("%02d", i));            
            }
            PresenceAnomalie anomalie;
            if(rs.getString("presenceAnomalie") != null){
                anomalie = PresenceAnomalie.valueOf(rs.getString("presenceAnomalie"));
            } else {
                anomalie = PresenceAnomalie.Nulle;
            }
            Compteur leCompteur = compteurDao.get(rs.getInt("leCompteur"));
            DateInfo laDate = dateInfoDao.get(rs.getDate("dateComptage"));
            Comptage comptage = new Comptage(passages, anomalie, leCompteur, laDate);

            this.lesComptages.add(comptage);
            leCompteur.addComptage(comptage);
            laDate.addComptage(comptage);
        }
        rs.close();
    }

    /**
     * Add a Comptage to the database
     * @param comptage the Comptage to add
     * @throws SQLException if an error occurs
     */
    public void add(Comptage comptage) throws SQLException {
        String query = "INSERT INTO COMPTAGE VALUES(?, ?, ";
        for(int i = 0; i < 24; i++) {
            query += "?, ";
        }
        query += "?)";
        PreparedStatement ps = database.preparedWriteStatment(query.toString());
        ps.setInt(1, comptage.getLeCompteur().getIdCompteur());
        ps.setDate(2, comptage.getLaDate().getLaDate());
        for(int i = 0; i < 24; i++) {
            ps.setInt(i + 3, comptage.getPassages()[i]);
        }
        ps.setObject(27, comptage.getAnomalie());
        ps.executeUpdate();
        ps.close();

    }

    /**
     * Remove a Comptage from the database
     * @param comptage the Comptage to remove
     * @throws SQLException if an error occurs
     */
    public void remove(Comptage comptage) throws SQLException {
        String query = "DELETE FROM COMPTAGE WHERE leCompteur = ? AND dateComptage = ?";
        PreparedStatement ps = database.preparedWriteStatment(query);
        ps.setInt(1, comptage.getLeCompteur().getIdCompteur());
        ps.setDate(2, comptage.getLaDate().getLaDate());
        ps.executeUpdate();
        ps.close();
    }

    /**
     * Update a Comptage from the database
     * @param comptage the Comptage to update
     * @throws SQLException if an error occurs
     */
    public void update(Comptage comptage) throws SQLException {
        String query = "UPDATE COMPTAGE SET ";
        for(int i = 0; i < 24; i++){
            query += "h" + String.format("%02d", i) + " = ?, ";
        }
        query += "anomalie = ? ";
        query += " WHERE leCompteur = ? AND dateComptage = ?";
        PreparedStatement ps = database.preparedWriteStatment(query);
        for(int i = 0; i < 24; i++){
            ps.setInt(i + 1, comptage.getPassage(i));
        }
        ps.setString(25, comptage.getAnomalie().toString());
        ps.setInt(26, comptage.getLeCompteur().getIdCompteur());
        ps.setDate(27, comptage.getLaDate().getLaDate());
        ps.executeUpdate();
        ps.close();
    }
}

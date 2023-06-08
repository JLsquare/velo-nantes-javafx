package modele;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Class DateInfoDao to implement the DAO pattern for DateInfo
 * @author Groupe 4B2
 */
public class DateInfoDao implements IDao<DateInfo> {

    // ---------------- Attributes ---------------- //

    private Database database;
    private ComptageDao comptageDao;
    private ArrayList<DateInfo> lesDate;

    // ---------------- Constructors ---------------- //    

    /**
     * Constructor of DateInfoDao
     * @param db the database
     * @param comptageDao the ComptageDao to use
     */
    public DateInfoDao(Database db, ComptageDao comptageDao) {
        this.database = db;
        this.comptageDao = comptageDao;
        this.lesDate = new ArrayList<DateInfo>();
    }

    // ---------------- Getters and Setters ---------------- //

    /**
     * Get all the DateInfo
     * @return an ArrayList of DateInfo
     */
    public ArrayList<DateInfo> getAll() {
        return lesDate;
    }

    // ---------------- Methods ---------------- //

    /**
     * Read all the DateInfo from the database
     */
    public void readAll() throws SQLException {
        PreparedStatement query = database.preparedReadStatment("SELECT * FROM DATEINFO");
        ResultSet rs = query.executeQuery();
        while(rs.next()) {
            ArrayList<Comptage> lesComptages = new ArrayList<Comptage>();
            for(Comptage comptage : comptageDao.getAll()) {
                if(comptage.getLaDate() == rs.getDate("laDate")) {
                    lesComptages.add(comptage);
                }
            }
            this.lesDate.add(new DateInfo(rs, lesComptages));
        }
    }

    /**
     * Add a DateInfo to the database
     * @param dateInfo the DateInfo to add
     */
    public void add(DateInfo dateInfo) throws SQLException {
        String query = "INSERT INTO DATEINFO VALUES(?, ?, ?, ?)";
        PreparedStatement stmt = database.preparedWriteStatment(query);
        stmt.setDate(1, dateInfo.getLaDate());
        stmt.setFloat(2, dateInfo.getTempMoy());
        stmt.setString(3, dateInfo.getJour().name());
        stmt.setString(4, dateInfo.getVacances().name());
        stmt.executeUpdate();
    }

    /**
     * Remove a DateInfo from the database
     * @param dateInfo the DateInfo to remove
     */
    public void remove(DateInfo dateInfo) throws SQLException {
        String query = "DELETE FROM DATEINFO WHERE laDate = ?";
        PreparedStatement stmt = database.preparedWriteStatment(query);
        stmt.setDate(1, dateInfo.getLaDate());
        stmt.executeUpdate();
    }

    /**
     * Update a DateInfo from the database
     * @param dateInfo the DateInfo to update
     */
    public void update(DateInfo dateInfo) throws SQLException {
        String query = "UPDATE DATEINFO SET tempMoy = ?, jour = ?, vacances = ? WHERE laDate = ?";
        PreparedStatement stmt = database.preparedWriteStatment(query);
        stmt.setFloat(1, dateInfo.getTempMoy());
        stmt.setString(2, dateInfo.getJour().name());
        stmt.setString(3, dateInfo.getVacances().name());
        stmt.setDate(4, dateInfo.getLaDate());
        stmt.executeUpdate();
    }

}

package modele;

import java.sql.Date;
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
    private ArrayList<DateInfo> lesDate;

    // ---------------- Constructors ---------------- //    

    /**
     * Constructor of DateInfoDao
     * @param db the database
     * @param comptageDao the ComptageDao to use
     */
    public DateInfoDao(Database db) {
        this.database = db;
        this.lesDate = new ArrayList<DateInfo>();
    }

    // ---------------- Getters and Setters ---------------- //

    /**
     * Get a DateInfo by its id
     * @param id the id of the DateInfo
     * @return the DateInfo
     */
    public DateInfo get(Date laDate) {
        DateInfo dateInfo = null;
        boolean found = false;
        int i = 0;
        while(!found && i < lesDate.size()) {
            if(lesDate.get(i).getLaDate().equals(laDate)) {
                dateInfo = lesDate.get(i);
                found = true;
            }
            i++;
        }
        return dateInfo;
    }

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
            DateInfo dateInfo = new DateInfo(rs);
            this.lesDate.add(dateInfo);
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

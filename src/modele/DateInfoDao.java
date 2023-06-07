package modele;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DateInfoDao implements IDao<DateInfo>{
    private Database db;
    public static DateInfoDao instance;

    public DateInfoDao(Database db) {
        this.db = db;
        instance = this;
    }

    public DateInfo get(Date date) throws SQLException{
        DateInfo dateInfo = null;
        String query = "SELECT * FROM DATEINFO";
        query += " WHERE date = " + date;
        ResultSet rs = db.executeReadQuery(query);
        dateInfo = new DateInfo(rs);
        return dateInfo;
    }

    public ArrayList<DateInfo> getAll() throws SQLException {
        ArrayList<DateInfo> lesDateInfos = new ArrayList<DateInfo>();
        String query = "SELECT * FROM DATEINFO";
        ResultSet rs = db.executeReadQuery(query);
        while(rs.next()) {
            lesDateInfos.add(new DateInfo(rs));
        }
        return lesDateInfos;
    }

    public void add(DateInfo dateInfo) throws SQLException {
        String query = "INSERT INTO DATEINFO VALUES(";
        query += dateInfo.getLaDate() + ", ";
        query += dateInfo.getTempMoy() + ", ";
        query += dateInfo.getJour() + ", ";
        query += dateInfo.getVacances() + ")";
        db.executeWriteQuery(query);
    }

    public void remove(DateInfo dateInfo) throws SQLException {
        String query = "DELETE FROM DATEINFO";
        query += " WHERE date = " + dateInfo.getLaDate();
        db.executeWriteQuery(query);
    }

    public void update(DateInfo dateInfo) throws SQLException {
        String query = "UPDATE DATEINFO SET ";
        query += "tempMoy = " + dateInfo.getTempMoy() + ", ";
        query += "jour = " + dateInfo.getJour() + ", ";
        query += "vacances = " + dateInfo.getVacances();
        query += " WHERE date = " + dateInfo.getLaDate();
        db.executeWriteQuery(query);
    }


}

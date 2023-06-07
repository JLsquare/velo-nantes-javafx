package modele;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ComptageDao implements IDao<Comptage>{
    private Database db;
    public static ComptageDao instance;

    public ComptageDao(Database db) {
        this.db = db;
        instance = this;
    }

    public Comptage get(int idCompteur, Date dateComptage) throws SQLException {
        Comptage comptage = null;
        String query = "SELECT * FROM COMPTAGE";
        query += " WHERE leCompteur = " + idCompteur;
        query += " AND dataComptage = " + dateComptage;
        ResultSet rs = db.executeReadQuery(query);
        comptage = new Comptage(rs);
        return comptage;
    }

    public ArrayList<Comptage> getAll() throws SQLException {
        ArrayList<Comptage> lesComptages = new ArrayList<Comptage>();
        String query = "SELECT * FROM COMPTAGE";
        ResultSet rs = db.executeReadQuery(query);
        while(rs.next()) {
            lesComptages.add(new Comptage(rs));
        }
        return lesComptages;
    }

    public void add(Comptage comptage) throws SQLException {
        String query = "INSERT INTO COMPTAGE VALUES(";
        query += comptage.getLeCompteur() + ", ";
        query += comptage.getLaDate() + ", ";
        for(int i = 0; i < 24; i++) {
            query += comptage.getPassages()[i] + ", ";
        }
        query += comptage.getAnomalie().toString() + ")";
        db.executeWriteQuery(query);
    }

    public void remove(Comptage comptage) throws SQLException {
        String query = "DELETE FROM COMPTAGE";
        query += " WHERE leCompteur = " + comptage.getLeCompteur();
        query += " AND dateComptage = " + comptage.getLaDate();
        db.executeWriteQuery(query);
    }

    public void update(Comptage comptage) throws SQLException {
        String query = "UPDATE COMPTAGE SET ";
        for(int i = 0; i < 24; i++){
            query += "h" + String.format("%02d", i) + " = " + comptage.getPassage(i) + ", ";
        }
        query = query.substring(0, query.length() - 2);
        query += "WHERE leCompteur = " + comptage.getLeCompteur() + " AND dateComptage = '" + comptage.getLaDate();
        db.executeWriteQuery(query);
    }
}

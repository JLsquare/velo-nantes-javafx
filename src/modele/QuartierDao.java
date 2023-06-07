package modele;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class QuartierDao implements IDao<Quartier>{
    private Database db;
    public static QuartierDao instance;

    public QuartierDao(Database db) {
        this.db = db;
        instance = this;
    }

    public Quartier get(int idQuartier) throws SQLException{
        Quartier quartier = null;
        String query = "SELECT * FROM QUARTIER";
        query += " WHERE idQuartier = " + idQuartier;
        ResultSet rs = db.executeReadQuery(query);
        quartier = new Quartier(rs);
        return quartier;
    }

    public ArrayList<Quartier> getAll() throws SQLException {
        ArrayList<Quartier> lesQuartiers = new ArrayList<Quartier>();
        String query = "SELECT * FROM QUARTIER";
        ResultSet rs = db.executeReadQuery(query);
        while(rs.next()) {
            lesQuartiers.add(new Quartier(rs));
        }
        return lesQuartiers;
    }

    public void add(Quartier t) throws SQLException {
        String query = "INSERT INTO QUARTIER VALUES(";
        query += t.getIdQuartier() + ", ";
        query += t.getNomQuartier() + ", ";
        query += t.getLongueurPisteVelo() + ")";
        db.executeWriteQuery(query);
    }

    public void remove(Quartier t) throws SQLException {
        String query = "DELETE FROM QUARTIER";
        query += " WHERE idQuartier = " + t.getIdQuartier();
        db.executeWriteQuery(query);
    }

    public void update(Quartier t) throws SQLException {
        String query = "UPDATE QUARTIER SET ";
        query += "nomQuartier = " + t.getNomQuartier() + ", ";
        query += "longueurPisteVelo = " + t.getLongueurPisteVelo();
        query += " WHERE idQuartier = " + t.getIdQuartier();
        db.executeWriteQuery(query);
    }
}

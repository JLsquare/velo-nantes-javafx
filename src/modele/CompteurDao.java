package modele;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CompteurDao implements IDao<Compteur> {
    private Database db;
    public static CompteurDao instance;

    public CompteurDao(Database db) {
        this.db = db;
        instance = this;
    }

    public Compteur get(int idCompteur) throws SQLException {
        Compteur compteur = null;
        String query = "SELECT * FROM COMPTEUR";
        query += " WHERE idCompteur = " + idCompteur;
        ResultSet rs = db.executeReadQuery(query);
        compteur = new Compteur(rs);
        return compteur;
    }

    public ArrayList<Compteur> getAll() throws SQLException {
        ArrayList<Compteur> lesCompteurs = new ArrayList<Compteur>();
        String query = "SELECT * FROM COMPTEUR";
        ResultSet rs = db.executeReadQuery(query);
        while(rs.next()) {
            lesCompteurs.add(new Compteur(rs));
        }
        return lesCompteurs;
    }

    public void add(Compteur compteur) throws SQLException {
        String query = "INSERT INTO COMPTEUR VALUES(";
        query += compteur.getIdCompteur() + ", ";
        query += compteur.getNomCompteur() + ", ";
        query += compteur.getSens() + ", ";
        query += compteur.getCoordX() + ", ";
        query += compteur.getCoordY() + ", ";
        query += compteur.getLeQuartier() + ")";
        db.executeWriteQuery(query);
    }

    public void remove(Compteur compteur) throws SQLException {
        String query = "DELETE FROM COMPTEUR";
        query += " WHERE idCompteur = " + compteur.getIdCompteur();
        db.executeWriteQuery(query);
    }

    public void update(Compteur compteur) throws SQLException {
        String query = "UPDATE COMPTEUR SET ";
        query += "nomCompteur = " + compteur.getNomCompteur() + ", ";
        query += "sens = " + compteur.getSens() + ", ";
        query += "coordX = " + compteur.getCoordX() + ", ";
        query += "coordY = " + compteur.getCoordY() + ", ";
        query += "leQuartier = " + compteur.getLeQuartier();
        query += " WHERE idCompteur = " + compteur.getIdCompteur();
        db.executeWriteQuery(query);
    }

    
}

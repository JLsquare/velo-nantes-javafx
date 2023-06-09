import java.sql.SQLException;
import java.util.ArrayList;

import modele.dao.*;
import modele.database.*;
import modele.entities.*;

public class TestDatabase {
    public static void main(String[] args) {
        try {
            Database database = new Database("jdbc:mariadb://localhost:3306/bd_velo");
            database.openReadConnection("read", "read");

            QuartierDao quartierDao = new QuartierDao(database);
            DateInfoDao dateInfoDao = new DateInfoDao(database);
            CompteurDao compteurDao = new CompteurDao(database, quartierDao);
            ComptageDao comptageDao = new ComptageDao(database, compteurDao, dateInfoDao);

            quartierDao.readAll();
            dateInfoDao.readAll();
            compteurDao.readAll();
            comptageDao.readAll();
            
            ArrayList<Quartier> lesQuartiers = quartierDao.getAll();
            ArrayList<DateInfo> lesDates = dateInfoDao.getAll();
            ArrayList<Compteur> lesCompteurs = compteurDao.getAll();
            ArrayList<Comptage> lesComptages = comptageDao.getAll();

            for(Quartier quartier : lesQuartiers) {
                System.out.println(quartier);
            }
            for(DateInfo date : lesDates) {
                System.out.println(date);
            }
            for(Compteur compteur : lesCompteurs) {
                System.out.println(compteur);
            }
            for(Comptage comptage : lesComptages) {
                System.out.println(comptage);
            }

            database.closeReadConnection();
        } catch (SQLException e) {
            System.out.println("Error with the database");
            System.out.println(e.getMessage());
        }
    }
}

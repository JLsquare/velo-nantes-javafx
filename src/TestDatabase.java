import java.sql.SQLException;
import java.util.ArrayList;

import modele.*;

public class TestDatabase {
    public static void main(String[] args) {
        try {
            Database database = new Database("jdbc:mariadb://localhost:3306/bd_velo");
            database.openReadConnection("read", "read");

            ComptageDao comptageDao = new ComptageDao(database);
            DateInfoDao dateInfoDao = new DateInfoDao(database, comptageDao);
            CompteurDao compteurDao = new CompteurDao(database, comptageDao);
            QuartierDao quartierDao = new QuartierDao(database, compteurDao);

            comptageDao.readAll();
            dateInfoDao.readAll();
            compteurDao.readAll();
            quartierDao.readAll();
            
            ArrayList<Comptage> comptages = comptageDao.getAll();
            ArrayList<Compteur> compteurs = compteurDao.getAll();
            ArrayList<DateInfo> dateInfos = dateInfoDao.getAll();
            ArrayList<Quartier> quartiers = quartierDao.getAll();

            for(Quartier quartier : quartiers) {
                System.out.println(quartier);
            }

            for(DateInfo dateInfo : dateInfos) {
                System.out.println(dateInfo);
            }

            for(Compteur compteur : compteurs) {
                System.out.println(compteur);
            }

            for(Comptage comptage : comptages) {
                System.out.println(comptage);
            }

            database.closeReadConnection();
        } catch (SQLException e) {
            System.out.println("Error with the database");
            System.out.println(e.getMessage());
        }
    }
}

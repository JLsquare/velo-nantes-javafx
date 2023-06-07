import java.util.ArrayList;

import modele.*;

public class TestDatabase {
    public static void main(String[] args) {
        try {
            Database database = new Database("jdbc:mariadb://localhost:3306/bd_velo");
            database.openReadConnection("read", "read");
            
            QuartierDao quartierDao = new QuartierDao(database);
            CompteurDao compteurDao = new CompteurDao(database);
            DateInfoDao dateInfoDao = new DateInfoDao(database);
            ComptageDao comptageDao = new ComptageDao(database);

            ArrayList<Quartier> lesQuartiers = quartierDao.getAll();
            ArrayList<Compteur> lesCompteurs = compteurDao.getAll();
            ArrayList<DateInfo> lesDates = dateInfoDao.getAll();
            ArrayList<Comptage> lesComptages = comptageDao.getAll();

            for(Quartier quartier : lesQuartiers) {
                System.out.println(quartier);
            }
            for(Compteur compteur : lesCompteurs) {
                System.out.println(compteur);
            }
            for(DateInfo date : lesDates) {
                System.out.println(date);
            }
            for(Comptage comptage : lesComptages) {
                System.out.println(comptage);
            }

            database.closeReadConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

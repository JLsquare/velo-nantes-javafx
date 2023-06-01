import java.util.ArrayList;

import modele.*;

public class TestDatabase {
    public static void main(String[] args) {
        try {
            Database.openReadConnection("read", "read");
            Database.loadDatabase();

            ArrayList<Quartier> lesQuartiers = Quartier.getQuartiers();
            for(Quartier q : lesQuartiers){
                System.out.println(q);
            }

            ArrayList<Compteur> lesCompteurs = Compteur.getCompteurs();
            for(Compteur c : lesCompteurs){
                System.out.println(c);
            }

            ArrayList<DateInfo> lesDateInfos = DateInfo.getDateInfos();
            for(DateInfo d : lesDateInfos){
                System.out.println(d);
            }

            ArrayList<Comptage> lesComptages = Comptage.getComptages();
            for(Comptage c : lesComptages){
                System.out.println(c);
            }

            Database.closeReadConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

import modele.*;

public class TestDatabase {
    public static void main(String[] args) {
        try {
            Database.openReadConnection("read", "read");
            Database.loadQuartiers();
            Database.loadCompteurs();
            Database.loadDateInfos();
            Database.loadComptages();
            System.out.println(Quartier.toStringAll());
            System.out.println(Compteur.toStringAll());
            //System.out.println(DateInfo.toStringAll());
            //System.out.println(Comptage.toStringAll());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

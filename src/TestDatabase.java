import modele.*;

public class TestDatabase {
    public static void main(String[] args) {
        try {
            Database.openReadConnection("read", "read");
            Database.loadQuartiers();
            Database.loadCompteurs();
            Database.loadDateInfos();
            Database.loadComptages();
            Quartier quartier = Quartier.getQuartier(1);
            System.out.println(quartier);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

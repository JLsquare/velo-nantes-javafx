package modele;

public class Compteur{
    private int idCompteur;
    private String nomCompteur;
    private float coordX;
    private float coordY;
    private Quartier leQuartier;

    public Compteur(int idCompteur, String nomCompteur, float coordX, float coordY, Quartier leQuartier) {
        this.idCompteur = idCompteur;
        this.nomCompteur = nomCompteur;
        this.coordX = coordX;
        this.coordY = coordY;
        this.leQuartier = leQuartier;
    }

    public int getIdCompteur() {
        return idCompteur;
    }

    public void setIdCompteur(int idCompteur) {
        this.idCompteur = idCompteur;
    }

    public String getNomCompteur() {
        return nomCompteur;
    }

    public void setNomCompteur(String nomCompteur) {
        this.nomCompteur = nomCompteur;
    }

    public float getCoordX() {
        return coordX;
    }

    public void setCoordX(float coordX) {
        this.coordX = coordX;
    }

    public float getCoordY() {
        return coordY;
    }

    public void setCoordY(float coordY) {
        this.coordY = coordY;
    }

    public Quartier getLeQuartier() {
        return leQuartier;
    }

    public void setLeQuartier(Quartier leQuartier) {
        this.leQuartier = leQuartier;
    }
}
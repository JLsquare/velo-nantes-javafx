package controller;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import modele.entities.Comptage;
import modele.entities.Compteur;
import view.RemoveCompteur;
import view.VeloNantes;

public class RemoveCompteurListener implements ChangeListener<String>, EventHandler<ActionEvent> {
    private RemoveCompteur removeCompteur;
    private Compteur compteur;

    public RemoveCompteurListener(RemoveCompteur removeCompteur){
        this.removeCompteur = removeCompteur;
    }

    @Override
    public void changed(ObservableValue<? extends String> observable, String before, String after) {
        if(observable == this.removeCompteur.getCompteurField().valueProperty()){
            String[] splitCompteur = ((String) after).split(" ");
            int idCompteur = Integer.parseInt(splitCompteur[splitCompteur.length - 1]);
            this.compteur = VeloNantes.compteurDao.get(idCompteur);
        } else {
        this.removeCompteur.getCompteurField().getItems().clear();
            for(Compteur compteur : VeloNantes.compteurDao.getAll()) {
                if((compteur.getLeQuartier().getNomQuartier() + " " + compteur.getLeQuartier().getIdQuartier()).equals(this.removeCompteur.getQuartier()) || this.removeCompteur.getQuartier().equals("Tous")) {
                    String counter = compteur.getNomCompteur() + compteur.getSens() + " " + compteur.getIdCompteur();
                    this.removeCompteur.getCompteurField().getItems().add(counter);
                }
            }
        }
        System.out.println("RemoveCompteurListener: " + compteur);
    }
    
    @Override
    public void handle(ActionEvent event) {
        try{
            for(Comptage comptage : this.compteur.getLesComptages())
                VeloNantes.comptageDao.remove(comptage);
            VeloNantes.compteurDao.remove(this.compteur);
            this.removeCompteur.setOutput("Compteur supprimé");
        }catch(Exception e){
            System.out.println(e);
            this.removeCompteur.setOutput(e.getMessage());
        }
    }
}

package controller;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import modele.entities.Comptage;
import modele.entities.Compteur;
import modele.entities.Quartier;
import view.RemoveCompteur;
import view.VeloNantes;

public class RemoveCompteurListener implements ChangeListener<String>, EventHandler<ActionEvent> {
    private RemoveCompteur removeCompteur;
    private Compteur compteur;
    private Quartier quartier;

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
            if(after.equals("Tous")){
                this.quartier = null;
            } else {
                String[] splitQuartier = ((String) after).split(" ");
                int idQuartier = Integer.parseInt(splitQuartier[splitQuartier.length - 1]);
                this.quartier = VeloNantes.quartierDao.get(idQuartier);
            }
            this.updateCompteurs();
        }
        System.out.println("RemoveCompteurListener: " + compteur);
    }

    private void updateCompteurs() {
        System.out.println("updateCompteurs");
        this.removeCompteur.getCompteurField().getItems().clear();
        for (Compteur compteur : VeloNantes.compteurDao.getAll()) {
            if (compteur.getLeQuartier().equals(this.quartier) || this.quartier == null) {
                String counter = compteur.getNomCompteur() + compteur.getSens() + " " + compteur.getIdCompteur();
                this.removeCompteur.getCompteurField().getItems().add(counter);
            }
        }
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

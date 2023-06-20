package controller;

import java.sql.Date;
import java.time.LocalDate;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import modele.entities.Comptage;
import modele.entities.Compteur;
import modele.entities.DateInfo;
import modele.entities.Quartier;
import view.RemoveComptage;
import view.VeloNantes;

public class RemoveComptageListener implements ChangeListener<Object>, EventHandler<ActionEvent> {
    private RemoveComptage removeComptage;
    private Compteur compteur;
    private Quartier quartier;
    private DateInfo dateInfo;

    public RemoveComptageListener(RemoveComptage removeComptage){
        this.removeComptage = removeComptage;
    }

        @Override
    public void changed(ObservableValue<?> observable, Object before, Object after) {
        if (after instanceof String) {
            String afterString = (String) after;
            if(observable == removeComptage.getCompteurField().valueProperty()){
                String[] splitCompteur = afterString.split(" ");
                int idCompteur = Integer.parseInt(splitCompteur[splitCompteur.length - 1]);
                this.compteur = VeloNantes.compteurDao.get(idCompteur);
                this.updateOutput();
            } else {
                if(afterString.equals("Tous")){
                    this.quartier = null;
                } else {
                    String[] splitQuartier = afterString.split(" ");
                    int idQuartier = Integer.parseInt(splitQuartier[splitQuartier.length - 1]);
                    this.quartier = VeloNantes.quartierDao.get(idQuartier);
                    this.updateCompteurs();
                }
            }
        } else if (after instanceof LocalDate) {
            LocalDate date = (LocalDate) after;
            this.dateInfo = VeloNantes.dateInfoDao.get(Date.valueOf(date));
            this.updateOutput();
        }
    }

    private void updateCompteurs() {
        System.out.println("updateCompteurs");
        this.removeComptage.getCompteurField().getItems().clear();
        for (Compteur compteur : VeloNantes.compteurDao.getAll()) {
            if (compteur.getLeQuartier().equals(this.quartier) || this.quartier == null) {
                String counter = compteur.getNomCompteur() + compteur.getSens() + " " + compteur.getIdCompteur();
                this.removeComptage.getCompteurField().getItems().add(counter);
            }
        }
    }

    public void updateOutput(){
        if(this.compteur != null && this.dateInfo != null){
            Comptage comptage = VeloNantes.comptageDao.get(this.dateInfo, this.compteur);
            if(comptage == null){
                this.removeComptage.setOutput("Ce comptage n'est pas dans la base de données");
            } else {
                this.removeComptage.setOutput(comptage.toString());
            }
        }
        if(this.dateInfo == null){
            this.removeComptage.setOutput("Cette date n'est pas dans la base de données");
        }
    }
    
    @Override
    public void handle(ActionEvent event) {
        try{
            Comptage comptage = VeloNantes.comptageDao.get(this.dateInfo, this.compteur);
            VeloNantes.comptageDao.remove(comptage);
            this.removeComptage.setOutput("Comptage supprimé");
        }catch(Exception e){
            System.out.println(e);
            this.removeComptage.setOutput(e.getMessage());
        }
    }
}
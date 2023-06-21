package controller;

import java.sql.Date;
import java.time.LocalDate;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.TextField;
import modele.entities.Comptage;
import modele.entities.Compteur;
import modele.entities.DateInfo;
import modele.entities.PresenceAnomalie;
import modele.entities.Quartier;
import view.InsertComptage;
import view.VeloNantes;

public class InsertComptageListener implements ChangeListener<Object>, EventHandler<ActionEvent>{
    private InsertComptage insertComptage;
    private Quartier quartier;
    private Compteur compteur;
    private DateInfo dateInfo;
    private PresenceAnomalie anomalie;
    private int[] passages;

    public InsertComptageListener(InsertComptage insertComptage){
        this.insertComptage = insertComptage;
        this.passages = new int[24];
    }
    
    @Override
    public void changed(ObservableValue<? extends Object> observable, Object before, Object after){
        if(observable == this.insertComptage.getDateField().valueProperty()){
            LocalDate date = (LocalDate) after;
            this.dateInfo = VeloNantes.dateInfoDao.get(Date.valueOf(date));
        }

        if(observable == this.insertComptage.getQuartierField()){
            String quartierString = (String) after;
            if(quartierString.equals("Tous")){
                this.quartier = null;
            } else {
                String[] splitQuartier = quartierString.split(" ");
                int idQuartier = Integer.parseInt(splitQuartier[splitQuartier.length - 1]);
                this.quartier = VeloNantes.quartierDao.get(idQuartier);
            }
            this.updateCompteurs();
        }

        if(observable == this.insertComptage.getCompteurField().valueProperty()){
            String compteurString = (String) after;
            String[] splitCompteur = compteurString.split(" ");
            int idCompteur = Integer.parseInt(splitCompteur[splitCompteur.length - 1]);
            this.compteur = VeloNantes.compteurDao.get(idCompteur);
        }

        if(observable == this.insertComptage.getAnomalieField().valueProperty()){
            String anomalieString = (String) after;
            this.anomalie = PresenceAnomalie.valueOf(anomalieString);
        }

        TextField[] passagesField = this.insertComptage.getPassagesFields();
        for(int i = 0; i < 24; i++){
            if(observable == passagesField[i].textProperty()){
                try{
                    this.passages[i] = Integer.parseInt(passagesField[i].getText());
                }catch(NumberFormatException e){
                    this.insertComptage.setOutput("Le nombre de passages doit être un entier");
                }
            }
        }
    }

    private void updateCompteurs() {
        System.out.println("updateCompteurs");
        this.insertComptage.getCompteurField().getItems().clear();
        for (Compteur compteur : VeloNantes.compteurDao.getAll()) {
            if (compteur.getLeQuartier().equals(this.quartier) || this.quartier == null) {
                String counter = compteur.getNomCompteur() + compteur.getSens() + " " + compteur.getIdCompteur();
                this.insertComptage.getCompteurField().getItems().add(counter);
            }
        }
    }

    @Override
    public void handle(ActionEvent event){
        try{
            Comptage comptage = new Comptage(passages, anomalie, compteur, dateInfo);
            VeloNantes.comptageDao.add(comptage);
            this.insertComptage.setOutput("Le comptage a été ajouté");
        }catch(Exception e){
            System.out.println(e);
            this.insertComptage.setOutput(e.getMessage());
        }
    }
}

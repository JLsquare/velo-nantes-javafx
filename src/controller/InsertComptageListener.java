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
import view.InsertComptage;
import view.VeloNantes;

public class InsertComptageListener implements ChangeListener<Object>, EventHandler<ActionEvent>{
    private InsertComptage insertComptage;
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

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
import view.UpdateComptage;
import view.VeloNantes;

public class UpdateComptageListener implements ChangeListener<Object>, EventHandler<ActionEvent>{
    private UpdateComptage updateComptage;
    private Comptage comptage;
    private Compteur compteur;
    private DateInfo dateInfo;
    private PresenceAnomalie anomalie;
    private int[] passages;

    public UpdateComptageListener(UpdateComptage updateComptage){
        this.updateComptage = updateComptage;
        this.passages = new int[24];
    }
    
    @Override
    public void changed(ObservableValue<? extends Object> observable, Object before, Object after){
        this.updateComptage.setOutput("");

        if(observable == this.updateComptage.getDateField().valueProperty()){
            LocalDate date = (LocalDate) after;
            this.dateInfo = VeloNantes.dateInfoDao.get(Date.valueOf(date));
            this.update();
        }

        if(observable == this.updateComptage.getCompteurField().valueProperty()){
            String compteurString = (String) after;
            String[] splitCompteur = compteurString.split(" ");
            int idCompteur = Integer.parseInt(splitCompteur[splitCompteur.length - 1]);
            this.compteur = VeloNantes.compteurDao.get(idCompteur);
            this.update();
        }

        if(observable == this.updateComptage.getAnomalieField().valueProperty()){
            String anomalieString = (String) after;
            this.anomalie = PresenceAnomalie.valueOf(anomalieString);
        }

        TextField[] passagesField = this.updateComptage.getPassagesFields();
        for(int i = 0; i < 24; i++){
            if(observable == passagesField[i].textProperty()){
                try{
                    this.passages[i] = Integer.parseInt(passagesField[i].getText());
                }catch(NumberFormatException e){
                    this.updateComptage.setOutput("Le nombre de passages doit être un entier");
                }
            }
        }
    }

    public void update(){
        if(this.compteur != null && this.dateInfo != null){
            this.comptage = VeloNantes.comptageDao.get(this.dateInfo, this.compteur);
            if(comptage == null){
                this.updateComptage.setOutput("Ce comptage n'est pas dans la base de données");
            } else {
                this.updateComptage.setOutput(comptage.toString());
                this.updateComptage.setAnomalieField(comptage.getAnomalie());
                this.updateComptage.setPassagesFields(comptage.getPassages());
            }
        }
        if(this.dateInfo == null){
            this.updateComptage.setOutput("Cette date n'est pas dans la base de données");
        }
    }

    @Override
    public void handle(ActionEvent event){
        try{
            this.comptage.setAnomalie(this.anomalie);
            this.comptage.setPassages(this.passages);
            VeloNantes.comptageDao.update(this.comptage);
            this.updateComptage.setOutput("Le comptage a été mis à jour");
        }catch(Exception e){
            System.out.println(e);
            this.updateComptage.setOutput(e.getMessage());
        }
    }
}

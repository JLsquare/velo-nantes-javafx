package controller;

import java.sql.Date;
import java.time.LocalDate;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import modele.entities.Comptage;
import modele.entities.DateInfo;
import view.RemoveDate;
import view.VeloNantes;

public class RemoveDateListener implements ChangeListener<LocalDate>, EventHandler<ActionEvent>{
    private RemoveDate removeDate;
    private DateInfo dateInfo;

    public RemoveDateListener(RemoveDate removeDate) throws IllegalArgumentException{
        if(removeDate == null){
            throw new IllegalArgumentException("RemoveDateListener: RemoveDate cannot be null");
        }
        this.removeDate = removeDate;
    }

    @Override
    public void changed(ObservableValue<? extends LocalDate> observable, LocalDate before, LocalDate after) {
        this.removeDate.setOutput("");
        this.dateInfo = VeloNantes.dateInfoDao.get(Date.valueOf(after));
        if(this.dateInfo == null){
            this.removeDate.setOutput("Cette date n'est pas dans la base de données");
        } else {        
            this.removeDate.setTempMoyField(dateInfo.getTempMoy());
            this.removeDate.setJourField(dateInfo.getJour());
            this.removeDate.setVacancesField(dateInfo.getVacances());
        }
        System.out.println("RemoveDateListener: " + dateInfo);
    }

    @Override
    public void handle(ActionEvent event) {
        try{
            for(Comptage comptage : dateInfo.getLesComptages()){
                VeloNantes.comptageDao.remove(comptage);
            }
            VeloNantes.dateInfoDao.remove(this.dateInfo);
            this.removeDate.setOutput("La date a été supprimée");
        }catch(Exception e){
            System.out.println(e);
            this.removeDate.setOutput(e.getMessage());
        }
    }
    
}

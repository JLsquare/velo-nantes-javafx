package controller;

import java.sql.Date;
import java.time.LocalDate;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import modele.entities.DateInfo;
import modele.entities.Jour;
import modele.entities.Vacances;
import view.UpdateDate;
import view.VeloNantes;

public class UpdateDateListener implements ChangeListener<Object>, EventHandler<ActionEvent>{
    private UpdateDate updateDate;
    private DateInfo dateInfo;
    private Date date;
    private float tempMoy;
    private Jour jour;
    private Vacances vacances;

    public UpdateDateListener(UpdateDate updateDate){
        this.updateDate = updateDate;
    }

    @Override
    public void changed(ObservableValue<?> observable, Object before, Object after) {
        this.updateDate.setOutput("");

        if(after instanceof LocalDate){
            this.date = Date.valueOf((LocalDate) after);
            this.dateInfo = VeloNantes.dateInfoDao.get(this.date);
            if(this.dateInfo == null){
                this.updateDate.setOutput("Cette date n'est pas dans la base de données");
            } else {
                this.updateDate.setJourField(this.dateInfo.getJour());
                this.updateDate.setVacancesField(this.dateInfo.getVacances());
                this.updateDate.setTempMoyField(this.dateInfo.getTempMoy());
            }
        } 

        if(after instanceof String){
            String afterString = (String) after;
            if(observable == updateDate.getJourField().valueProperty()){
                this.jour = Jour.valueOf(afterString);
            }

            if(observable == updateDate.getVacancesField().valueProperty()){
                this.vacances = Vacances.valueOf(afterString);
            } 
            
            if(observable == updateDate.getTempMoyField().textProperty()){
                try {
                    this.tempMoy = Float.parseFloat(afterString);
                } catch (NumberFormatException e) {
                    this.updateDate.setOutput("La température moyenne doit être un nombre");
                }
            }
        } 
    }

    @Override
    public void handle(ActionEvent event) {
        try{
            this.dateInfo.setJour(this.jour);
            this.dateInfo.setVacances(this.vacances);
            this.dateInfo.setTempMoy(this.tempMoy);

            VeloNantes.dateInfoDao.update(this.dateInfo);

            this.updateDate.setOutput("Date mise à jour");
        } catch(Exception e){
            System.out.println(e);
            this.updateDate.setOutput(e.getMessage());
        }

    }
}

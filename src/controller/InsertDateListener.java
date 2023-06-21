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
import view.InsertDate;
import view.VeloNantes;

public class InsertDateListener implements ChangeListener<Object>, EventHandler<ActionEvent>{
    private InsertDate insertDate;
    private Date date;
    private float tempMoy;
    private Jour jour;
    private Vacances vacances;

    public InsertDateListener(InsertDate insertDate){
        this.insertDate = insertDate;
    }

    @Override
    public void changed(ObservableValue<?> observable, Object before, Object after) {
        this.insertDate.setOutput("");

        if(after instanceof LocalDate){
            this.date = Date.valueOf((LocalDate) after);
        } 

        if(after instanceof String){
            String afterString = (String) after;
            if(observable == insertDate.getJourField().valueProperty()){
                this.jour = Jour.valueOf(afterString);
            }

            if(observable == insertDate.getVacancesField().valueProperty()){
                this.vacances = Vacances.valueOf(afterString);
            } 
            
            if(observable == insertDate.getTempMoyField().textProperty()){
                try {
                    this.tempMoy = Float.parseFloat(afterString);
                } catch (NumberFormatException e) {
                    this.insertDate.setOutput("La température moyenne doit être un nombre");
                }
            }
        } 
    }

    @Override
    public void handle(ActionEvent event) {
        try{
            DateInfo dateInfo = new DateInfo(this.date, this.tempMoy, this.jour, this.vacances);
            VeloNantes.dateInfoDao.add(dateInfo);
        } catch(Exception e){
            System.out.println(e);
            this.insertDate.setOutput(e.getMessage());
        }

    }
}

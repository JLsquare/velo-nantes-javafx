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
import view.RemoveComptage;
import view.VeloNantes;

public class RemoveComptageListener implements ChangeListener<Object>, EventHandler<ActionEvent> {
    private RemoveComptage removeComptage;
    private Compteur compteur;
    private DateInfo dateInfo;

    public RemoveComptageListener(RemoveComptage removeComptage){
        this.removeComptage = removeComptage;
    }

        @Override
    public void changed(ObservableValue<?> observable, Object before, Object after) {
        if (after instanceof String) {
            String compteurString = (String) after;
            String[] splitCompteur = compteurString.split(" ");
            int idCompteur = Integer.parseInt(splitCompteur[splitCompteur.length - 1]);
            this.compteur = VeloNantes.compteurDao.get(idCompteur);
            this.updateOutput();
        } else if (after instanceof LocalDate) {
            LocalDate date = (LocalDate) after;
            this.dateInfo = VeloNantes.dateInfoDao.get(Date.valueOf(date));
            this.updateOutput();
        }
    }

    public void updateOutput(){
        if(this.compteur != null && this.dateInfo != null){
            Comptage comptage = VeloNantes.comptageDao.get(this.dateInfo, this.compteur);
            if(comptage == null){
                this.removeComptage.setOutput("Ce comptage n'est pas dans la base de données");
            } else {
                this.removeComptage.setOutput(comptage.toString());
                this.removeComptage.setAnomalieField(comptage.getAnomalie());
                this.removeComptage.setPassagesFields(comptage.getPassages());
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

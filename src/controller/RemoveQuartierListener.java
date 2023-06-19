package controller;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import modele.dao.QuartierDao;
import modele.entities.Quartier;
import view.RemoveQuartier;

public class RemoveQuartierListener implements ChangeListener<String>, EventHandler<ActionEvent> {
    private RemoveQuartier removeQuartier;
    private QuartierDao quartierDao;
    private Quartier quartier;

    public RemoveQuartierListener(RemoveQuartier removeQuartier, QuartierDao quartierDao){
        this.removeQuartier = removeQuartier;
        this.quartierDao = quartierDao;
    }

    @Override
    public void changed(ObservableValue<? extends String> observable, String before, String after) {
        for(Quartier quartier : this.quartierDao.getAll()){
            if((quartier.getNomQuartier() + " " + quartier.getIdQuartier()).equals(after)){
                this.quartier = quartier;
            }
        }
        System.out.println("RemoveQuartierListener: " + quartier);
    }
    
    @Override
    public void handle(ActionEvent event) {
        try{
            this.quartierDao.remove(this.quartier);
        }catch(Exception e){
            System.out.println(e);
            this.removeQuartier.setOutput(e.getMessage());
        }
    }
}

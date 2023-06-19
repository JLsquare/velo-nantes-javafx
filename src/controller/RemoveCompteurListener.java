package controller;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import modele.dao.CompteurDao;
import modele.entities.Compteur;
import view.RemoveCompteur;

public class RemoveCompteurListener implements ChangeListener<String>, EventHandler<ActionEvent> {
    private RemoveCompteur removeCompteur;
    private CompteurDao compteurDao;
    private Compteur compteur;

    public RemoveCompteurListener(RemoveCompteur removeCompteur, CompteurDao compteurDao){
        this.removeCompteur = removeCompteur;
        this.compteurDao = compteurDao;
    }

    @Override
    public void changed(ObservableValue<? extends String> observable, String before, String after) {
        for(Compteur compteur : this.compteurDao.getAll()){
            if((compteur.getNomCompteur() + " " + compteur.getIdCompteur()).equals(after)){
                this.compteur = compteur;
            }
        }
        this.removeCompteur.updateCompteurs();
        System.out.println("RemoveCompteurListener: " + compteur);
    }
    
    @Override
    public void handle(ActionEvent event) {
        try{
            this.compteurDao.remove(this.compteur);
        }catch(Exception e){
            System.out.println(e);
            this.removeCompteur.setOutput(e.getMessage());
        }
    }
}

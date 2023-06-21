package controller;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import modele.entities.Quartier;
import view.UpdateQuartier;
import view.VeloNantes;

public class UpdateQuartierListener implements ChangeListener<String>, EventHandler<ActionEvent>{
    private UpdateQuartier updateQuartier;
    private Quartier quartier;
    private String nomQuartier;
    private int idQuartier;
    private float longueurPisteVelo;
    
    public UpdateQuartierListener(UpdateQuartier updateQuartier){
        this.updateQuartier = updateQuartier;
    }

    @Override
    public void changed(ObservableValue<? extends String> observable, String before, String after) {
        this.updateQuartier.setOutput("");

        if(observable == this.updateQuartier.getQuartierField().valueProperty()){
            String[] splitQuartier = after.split(" ");
            this.idQuartier = Integer.parseInt(splitQuartier[splitQuartier.length - 1]);
            this.updateQuartier.setIdQuartierField(idQuartier);
            this.quartier = VeloNantes.quartierDao.get(this.idQuartier);
            this.updateQuartier.setOutput("Quartier sélectionné: " + this.quartier);
        }

        if(observable == this.updateQuartier.getNomQuartierField().textProperty()){
            if(after.length() > 0){
                this.nomQuartier = after;
            }else{
                this.updateQuartier.setOutput("Le nom du quartier ne peut pas être vide.");
            }
        }

        if(observable == this.updateQuartier.getLongueurPisteVeloField().textProperty()){
            try{
                this.longueurPisteVelo = Float.parseFloat(after);
            }catch(NumberFormatException e){
                this.updateQuartier.setOutput("La longueur de la piste cyclable doit être un nombre.");
            }
        }
    }

    @Override
    public void handle(ActionEvent event) {
        try{
            this.quartier.setNomQuartier(this.nomQuartier);
            this.quartier.setLongueurPisteVelo(this.longueurPisteVelo);
            VeloNantes.quartierDao.update(quartier);
            this.updateQuartier.setOutput("Quartier mis à jour: " + this.quartier);
        }catch(Exception e){
            System.out.println(e);
            this.updateQuartier.setOutput(e.getMessage());
        }
    }
}

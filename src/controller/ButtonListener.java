package controller;

import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import view.Authentification;
import view.VeloNantes;


public class ButtonListener implements EventHandler<ActionEvent> {
    private Authentification authentification;

    public ButtonListener(Authentification authentification) throws IllegalArgumentException{
        if(authentification == null){
            throw new IllegalArgumentException("ButtonListener: authentification cannot be null");
        }

        this.authentification = authentification;
    }

    @Override
    public void handle(ActionEvent event){
        String login = this.authentification.getLogin().getText();
        String password = this.authentification.getPassword().getText();

        try{
            VeloNantes.database.openWriteConnection(login, password);
            this.authentification.setError("Authentification réussie.");
            this.authentification.connected();
        }catch(Exception e){
            this.authentification.setError(e.getMessage());
            return;
        }
    }
}

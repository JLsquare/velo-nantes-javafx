package controller;

import javafx.event.EventHandler;
import modele.database.Database;
import javafx.event.ActionEvent;
import view.Authentification;


public class ButtonListener implements EventHandler<ActionEvent> {
    private Authentification authentification;
    private Database database;

    public ButtonListener(Authentification authentification, Database database) throws IllegalArgumentException{
        if(authentification == null){
            throw new IllegalArgumentException("ButtonListener: authentification cannot be null");
        }
        if(database == null){
            throw new IllegalArgumentException("ButtonListener: database cannot be null");
        }

        this.authentification = authentification;
        this.database = database;
    }

    @Override
    public void handle(ActionEvent event){
        String login = this.authentification.getLogin().getText();
        String password = this.authentification.getPassword().getText();

        try{
            this.database.openWriteConnection(login, password);
            this.authentification.setError("Authentification réussie.");
        }catch(Exception e){
            this.authentification.setError(e.getMessage());
            return;
        }
    }
}

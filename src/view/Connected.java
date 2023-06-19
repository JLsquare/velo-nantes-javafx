package view;

import java.time.LocalDateTime;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

public class Connected extends GridPane{
    private Label connectedLabel;
    private Label loginLabel;
    private Label login;
    private Label dateLabel;
    private Label date;
    private Button disconnectButton;

    public Connected(String login){
        this.initializeComponents(login);
    }

    private void initializeComponents(String login){
        this.connectedLabel = new Label("Connecté");
        this.loginLabel = new Label("Identifiant");
        this.login = new Label(login);
        this.dateLabel = new Label("Date");
        this.date = new Label(LocalDateTime.now().toString());
        this.disconnectButton = new Button("Déconnexion");

        this.setAlignment(Pos.CENTER);
        this.setVgap(10);
        this.setHgap(10);
        this.setPadding(new Insets(20));

        this.add(this.connectedLabel, 0, 0, 2, 1);
        this.add(this.loginLabel, 0, 2);
        this.add(this.login, 1, 2);
        this.add(this.dateLabel, 0, 3);
        this.add(this.date, 1, 3);
        this.add(this.disconnectButton, 1, 5);
    }
}

package view;

import controller.ButtonListener;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import modele.database.Database;

public class Authentification extends VBox {
    private GridPane gridPane;
    private Label authentificationLabel;
    private Pane spacer;
    private Label loginLabel;
    private Label passwordLabel;
    private TextField login;
    private PasswordField password;
    private Label error;
    private Button validateButton;
    private ButtonListener buttonListener;
    private Database database;

    public Authentification(Database database){
        this.database = database;
        this.initializeComponents();
    }

    private void initializeComponents(){
        this.gridPane = new GridPane();
        this.authentificationLabel = new Label("Authentification");
        this.spacer = new Pane();
        this.spacer.setMinHeight(20);
        this.loginLabel = new Label("Identifiant");
        this.passwordLabel = new Label("Mot de passe");
        this.login = new TextField();
        this.password = new PasswordField();
        this.error = new Label("No error.");
        this.error.maxWidth(100);
        this.error.setWrapText(true);
        this.validateButton = new Button("Valider");

        this.authentificationLabel.getStyleClass().add("auth-label");
        this.loginLabel.getStyleClass().add("label");
        this.passwordLabel.getStyleClass().add("label");
        this.login.getStyleClass().add("text-field");
        this.password.getStyleClass().add("password-field");
        this.validateButton.getStyleClass().add("button");
        
        this.buttonListener = new ButtonListener(this, this.database);
        this.validateButton.setOnAction(this.buttonListener);

        gridPane.setAlignment(Pos.CENTER);
        gridPane.setVgap(10);
        gridPane.setHgap(10);
        gridPane.setPadding(new Insets(20));

        gridPane.add(this.authentificationLabel, 0, 0, 2, 1);
        gridPane.add(this.loginLabel, 0, 2);
        gridPane.add(this.login, 1, 2);
        gridPane.add(this.passwordLabel, 0, 3);
        gridPane.add(this.password, 1, 3);
        gridPane.add(this.validateButton, 1, 5);

        this.getChildren().add(this.gridPane);
        this.getChildren().add(this.error);
    }

    public TextField getLogin(){
        return this.login;
    }

    public PasswordField getPassword(){
        return this.password;
    }

    public void setError(String error){
        String newError = "";
        for(int i = 0; i < error.length(); i++){
            if(i % 40 == 0 && i != 0){
                newError += "\n";
            }
            newError += error.charAt(i);
        }
        this.error.setText(newError);
    }
}

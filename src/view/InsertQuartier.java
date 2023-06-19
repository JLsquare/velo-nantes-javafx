package view;

import controller.InsertQuartierListener;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import modele.dao.QuartierDao;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class InsertQuartier extends VBox {
    private GridPane form;
    private InsertQuartierListener listener;
    private Label menuLabel;
    private Label nomQuartierLabel;
    private Label idQuartierLabel;
    private Label longueurPisteVeloLabel;
    private TextField nomQuartierField;
    private TextField idQuartierField;
    private TextField longueurPisteVeloField;
    private Button insertButton;
    private Label output;
    private QuartierDao quartierDao;

    public InsertQuartier(QuartierDao quartierDao) {
        super();
        this.quartierDao = quartierDao;
        this.listener = new InsertQuartierListener(this, this.quartierDao);
        initializeComponents();
    }

    public void initializeComponents() {
        this.form = new GridPane();
        this.menuLabel = new Label("Insérer un quartier");
        this.menuLabel.setStyle("-fx-font-size: 18px; -fx-font-weight: bold; -fx-padding: 10px 0px;");
        this.nomQuartierLabel = new Label("Nom du quartier : ");
        this.nomQuartierField = new TextField();
        this.idQuartierLabel = new Label("Id du quartier : ");
        this.idQuartierField = new TextField();
        this.longueurPisteVeloLabel = new Label("Longueur de la piste cyclable : ");
        this.longueurPisteVeloField = new TextField();
        this.insertButton = new Button("Insérer");
        this.output = new Label("");

        this.nomQuartierField.textProperty().addListener(this.listener);
        this.idQuartierField.textProperty().addListener(this.listener);
        this.longueurPisteVeloField.textProperty().addListener(this.listener);
        this.insertButton.setOnAction(this.listener);

        form.add(this.nomQuartierLabel, 0, 0);
        form.add(this.nomQuartierField, 1, 0);
        form.add(this.idQuartierLabel, 0, 1);
        form.add(this.idQuartierField, 1, 1);
        form.add(this.longueurPisteVeloLabel, 0, 2);
        form.add(this.longueurPisteVeloField, 1, 2);
        form.add(this.insertButton, 0, 3);

        this.getChildren().add(this.menuLabel);
        this.getChildren().add(this.form);
        this.getChildren().add(this.output);
    }

    public TextField getNomQuartierField() {
        return this.nomQuartierField;
    }

    public TextField getIdQuartierField() {
        return this.idQuartierField;
    }

    public TextField getLongueurPisteVeloField() {
        return this.longueurPisteVeloField;
    }

    public String getNomQuartier() {
        return this.nomQuartierField.getText();
    }

    public String getIdQuartier() {
        return this.idQuartierField.getText();
    }

    public String getLongueurPisteVelo() {
        return this.longueurPisteVeloField.getText();
    }

    public void setOutput(String output) {
        this.output.setText(output);
    }
}

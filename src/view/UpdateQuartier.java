package view;

import controller.UpdateQuartierListener;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import modele.entities.Quartier;

public class UpdateQuartier extends GridPane {
    private Label menuLabel;
    private Label quartierLabel;
    private Label nomQuartierLabel;
    private Label idQuartierLabel;
    private Label longueurPisteVeloLabel;

    private ComboBox<String> quartierField;
    private TextField nomQuartierField;
    private TextField idQuartierField;
    private TextField longueurPisteVeloField;

    private Button updateButton;
    private Label output;
    private UpdateQuartierListener listener;

    public UpdateQuartier() {
        super();
        this.listener = new UpdateQuartierListener(this);
        initializeComponents();
    }

    public void initializeComponents(){
        this.menuLabel = new Label("Modifier un quartier");
        this.menuLabel.setStyle("-fx-font-size: 18px; -fx-font-weight: bold; -fx-padding: 10px 0px;");
        this.quartierLabel = new Label("Quartier : ");
        this.quartierField = new ComboBox<String>();
        this.nomQuartierLabel = new Label("Nom du quartier : ");
        this.nomQuartierField = new TextField();
        this.idQuartierLabel = new Label("Id du quartier : ");
        this.idQuartierField = new TextField();
        this.idQuartierField.setDisable(true);
        this.longueurPisteVeloLabel = new Label("Longueur de la piste cyclable : ");
        this.longueurPisteVeloField = new TextField();
        this.updateButton = new Button("Modifier");
        this.output = new Label("");

        for(Quartier quartier : VeloNantes.quartierDao.getAll()){
            this.quartierField.getItems().add(quartier.getNomQuartier() + " " + quartier.getIdQuartier());
        }

        this.quartierField.valueProperty().addListener(this.listener);
        this.nomQuartierField.textProperty().addListener(this.listener);
        this.longueurPisteVeloField.textProperty().addListener(this.listener);
        this.updateButton.setOnAction(this.listener);

        this.add(this.menuLabel, 0, 0, 2, 1);
        this.add(this.quartierLabel, 0, 1);
        this.add(this.quartierField, 1, 1);
        this.add(this.nomQuartierLabel, 0, 2);
        this.add(this.nomQuartierField, 1, 2);
        this.add(this.idQuartierLabel, 0, 3);
        this.add(this.idQuartierField, 1, 3);
        this.add(this.longueurPisteVeloLabel, 0, 4);
        this.add(this.longueurPisteVeloField, 1, 4);
        this.add(this.updateButton, 1, 5);
        this.add(this.output, 0, 6, 2, 1);
    }

    public ComboBox<String> getQuartierField() {
        return this.quartierField;
    }

    public TextField getNomQuartierField() {
        return this.nomQuartierField;
    }

    public void setNomQuartierField(String nomQuartier) {
        this.nomQuartierField.setText(nomQuartier);
    }

    public void setIdQuartierField(int idQuartier) {
        this.idQuartierField.setText(idQuartier + "");
    }

    public TextField getLongueurPisteVeloField() {
        return this.longueurPisteVeloField;
    }

    public void setLongueurPisteVeloField(float longueurPisteVelo) {
        this.longueurPisteVeloField.setText(longueurPisteVelo + "");
    }

    public void setOutput(String output) {
        this.output.setText(output);
    }
}
package view;

import controller.InsertComptageListener;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import modele.entities.PresenceAnomalie;
import modele.entities.Quartier;

public class InsertComptage extends GridPane {
    private Label menuLabel;
    private Label dateLabel;
    private Label quartierLabel;
    private Label compteurLabel;
    private Label anomalieLabel;
    private Label passagesLabel;
    private Label[] passagesLabels;
    
    private DatePicker dateField;
    private ComboBox<String> quartierField;
    private ComboBox<String> compteurField;
    private ComboBox<String> anomalieField;
    private TextField[] passagesField;

    private GridPane passagesPane;
    private Button insertButton;
    private Label output;
    private InsertComptageListener listener;

    public InsertComptage() {
        super();
        this.listener = new InsertComptageListener(this);
        initializeComponents();
    }

    public void initializeComponents(){
        this.menuLabel = new Label("Insérer un comptage");
        this.menuLabel.setStyle("-fx-font-size: 18px; -fx-font-weight: bold; -fx-padding: 10px 0px;");
        this.dateLabel = new Label("Date : ");
        this.dateField = new DatePicker();
        this.quartierLabel = new Label("Quartier : ");
        this.quartierField = new ComboBox<String>();
        this.compteurLabel = new Label("Compteur : ");
        this.compteurField = new ComboBox<String>();
        this.anomalieLabel = new Label("Anomalie : ");
        this.anomalieField = new ComboBox<String>();
        this.passagesLabel = new Label("Passages : ");
        this.passagesLabels = new Label[24];
        this.passagesField = new TextField[24];
        this.passagesPane = new GridPane();
        this.insertButton = new Button("Insérer");
        this.output = new Label("");

        for(Quartier q : VeloNantes.quartierDao.getAll()){
            this.quartierField.getItems().add(q.getNomQuartier() + " " + q.getIdQuartier());
        }

        for(PresenceAnomalie a : PresenceAnomalie.values()){
            this.anomalieField.getItems().add(a.toString());
        }

        for(int i = 0; i < 24; i++){
            this.passagesLabels[i] = new Label("h" + i + " : ");
        }

        for(int i = 0; i < 24; i++){
            this.passagesField[i] = new TextField();
            this.passagesField[i].setPrefWidth(100);
        }

        for(int i = 0; i < 24; i++){
            this.passagesPane.add(this.passagesLabels[i], i/6, 2*(i%6));
            this.passagesPane.add(this.passagesField[i], i/6, 2*(i%6)+1); 
        }

        this.dateField.valueProperty().addListener(this.listener);
        this.quartierField.valueProperty().addListener(this.listener);
        this.compteurField.valueProperty().addListener(this.listener);
        this.anomalieField.valueProperty().addListener(this.listener);
        for(int i = 0; i < 24; i++){
            this.passagesField[i].textProperty().addListener(this.listener);
        }

        this.add(this.menuLabel, 0, 0, 2, 1);
        this.add(this.dateLabel, 0, 1);
        this.add(this.dateField, 1, 1);
        this.add(this.quartierLabel, 0, 2);
        this.add(this.quartierField, 1, 2);
        this.add(this.compteurLabel, 0, 3);
        this.add(this.compteurField, 1, 3);
        this.add(this.anomalieLabel, 0, 4);
        this.add(this.anomalieField, 1, 4);
        this.add(this.passagesLabel, 0, 5);
        this.add(this.passagesPane, 0, 6, 2, 1);
        this.add(this.insertButton, 0, 7);
        this.add(this.output, 1, 8, 2, 1);
    }

    public DatePicker getDateField() {
        return dateField;
    }

    public ComboBox<String> getQuartierField() {
        return quartierField;
    }

    public ComboBox<String> getCompteurField() {
        return compteurField;
    }

    public ComboBox<String> getAnomalieField() {
        return anomalieField;
    }

    public TextField[] getPassagesFields() {
        return passagesField;
    }

    public void setOutput(String output) {
        this.output.setText(output);
    }
}

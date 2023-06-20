package view;

import controller.RemoveComptageListener;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import modele.entities.Quartier;

public class RemoveComptage extends GridPane {
    private RemoveComptageListener listener;
    private Label menuLabel;
    private Label quartierLabel;
    private ComboBox<String> quartierField;
    private Label compteurLabel;
    private ComboBox<String> compteurField;
    private Label dateLabel;
    private DatePicker dateField;
    private Button removeButton;
    private Label output;
    private Label warning;
    
    public RemoveComptage() {
        super();
        this.listener = new RemoveComptageListener(this);
        initializeComponents();
    }

    public void initializeComponents(){
        this.menuLabel = new Label("Supprimer un comptage");
        this.menuLabel.setStyle("-fx-font-size: 18px; -fx-font-weight: bold; -fx-padding: 10px 0px;");

        this.quartierLabel = new Label("Nom du quartier : ");
        this.quartierField = new ComboBox<String>();
        this.quartierField.getItems().add("Tous");
        for(Quartier quartier : VeloNantes.quartierDao.getAll()){
            this.quartierField.getItems().add(quartier.getNomQuartier() + " " + quartier.getIdQuartier());
        }

        this.compteurLabel = new Label("Nom du compteur : ");
        this.compteurField = new ComboBox<String>();

        this.dateLabel = new Label("La date : ");
        this.dateField = new DatePicker();

        this.removeButton = new Button("Supprimer");

        this.output = new Label("");
        this.warning = new Label("Attention, cela supprimera aussi les comptages associés à ce compteur et cette date.");

        this.add(this.menuLabel, 0, 0, 2, 1);
        this.add(this.quartierLabel, 0, 1);
        this.add(this.quartierField, 1, 1);
        this.add(this.compteurLabel, 0, 2);
        this.add(this.compteurField, 1, 2);
        this.add(this.dateLabel, 0, 3);
        this.add(this.dateField, 1, 3);
        this.add(this.removeButton, 1, 4);
        this.add(this.warning, 0, 5, 2, 1);
        this.add(this.output, 0, 6, 2, 1);

        this.quartierField.valueProperty().addListener(this.listener);
        this.dateField.valueProperty().addListener(this.listener);
        this.compteurField.valueProperty().addListener(this.listener);
        this.quartierField.setValue("Tous");
        this.removeButton.setOnAction(this.listener);
    }

    public ComboBox<String> getCompteurField(){
        return this.compteurField;
    }

    public DatePicker getDateField(){
        return this.dateField;
    }

    public String getQuartier(){
        return this.quartierField.getValue();
    }

    public String getDate(){
        return this.dateField.getValue().toString();
    }

    public void setOutput(String output){
        this.output.setText(output);
    }
}

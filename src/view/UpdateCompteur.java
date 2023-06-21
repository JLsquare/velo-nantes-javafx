package view;

import controller.UpdateCompteurListener;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import modele.entities.Compteur;
import modele.entities.Quartier;

public class UpdateCompteur extends GridPane {
    private Label menuLabel;
    private Label compteurLabel;
    private Label nomCompteurLabel;
    private Label idCompteurLabel;
    private Label sensLabel;
    private Label coordXLabel;
    private Label coordYLabel;
    private Label quartierLabel;

    private ComboBox<String> compteurField;
    private TextField nomCompteurField;
    private TextField idCompteurField;
    private TextField sensField;
    private TextField coordXField;
    private TextField coordYField;
    private ComboBox<String> quartierField;

    private Button updateButton;
    private Label output;
    private UpdateCompteurListener listener;
    
    public UpdateCompteur() {
        super();
        this.listener = new UpdateCompteurListener(this);
        initializeComponents();
    }

    public void initializeComponents(){
        this.menuLabel = new Label("Modifier un compteur");
        this.menuLabel.setStyle("-fx-font-size: 18px; -fx-font-weight: bold; -fx-padding: 10px 0px;");
        this.compteurLabel = new Label("Compteur : ");
        this.compteurField = new ComboBox<String>();
        this.nomCompteurLabel = new Label("Nom du compteur : ");
        this.nomCompteurField = new TextField();
        this.idCompteurLabel = new Label("Id du compteur : ");
        this.idCompteurField = new TextField();
        this.idCompteurField.setDisable(true);
        this.sensLabel = new Label("Sens : ");
        this.sensField = new TextField();
        this.coordXLabel = new Label("Coordonnée X : ");
        this.coordXField = new TextField();
        this.coordYLabel = new Label("Coordonnée Y : ");
        this.coordYField = new TextField();
        this.quartierLabel = new Label("Quartier : ");
        this.quartierField = new ComboBox<String>();

        this.updateButton = new Button("Modifier");
        this.output = new Label("");
        
        for(Compteur compteur : VeloNantes.compteurDao.getAll()){
            this.compteurField.getItems().add(compteur.getNomCompteur() + " " + compteur.getIdCompteur());
        }

        for(Quartier quartier : VeloNantes.quartierDao.getAll()){
            this.quartierField.getItems().add(quartier.getNomQuartier() + " " + quartier.getIdQuartier());
        }

        this.compteurField.valueProperty().addListener(listener);
        this.nomCompteurField.textProperty().addListener(listener);
        this.sensField.textProperty().addListener(listener);
        this.coordXField.textProperty().addListener(listener);
        this.coordYField.textProperty().addListener(listener);
        this.quartierField.valueProperty().addListener(listener);
        this.updateButton.setOnAction(listener);        

        this.add(this.menuLabel, 0, 0, 2, 1);
        this.add(this.compteurLabel, 0, 1);
        this.add(this.compteurField, 1, 1);
        this.add(this.nomCompteurLabel, 0, 2);
        this.add(this.nomCompteurField, 1, 2);
        this.add(this.idCompteurLabel, 0, 3);
        this.add(this.idCompteurField, 1, 3);
        this.add(this.sensLabel, 0, 4);
        this.add(this.sensField, 1, 4);
        this.add(this.coordXLabel, 0, 5);
        this.add(this.coordXField, 1, 5);
        this.add(this.coordYLabel, 0, 6);
        this.add(this.coordYField, 1, 6);
        this.add(this.quartierLabel, 0, 7);
        this.add(this.quartierField, 1, 7);
        this.add(this.updateButton, 0, 8, 2, 1);
        this.add(this.output, 0, 9, 2, 1);
    }

    public ComboBox<String> getCompteurField() {
        return compteurField;
    }

    public TextField getNomCompteurField() {
        return nomCompteurField;
    }

    public void setNomCompteurField(String nom) {
        this.nomCompteurField.setText(nom);
    }

    public void setIdCompteurField(int id) {
        this.idCompteurField.setText(id + "");
    }

    public TextField getSensField() {
        return sensField;
    }

    public void setSensField(String sens) {
        this.sensField.setText(sens);
    }

    public TextField getCoordXField() {
        return coordXField;
    }

    public void setCoordXField(float coordX) {
        this.coordXField.setText(coordX + "");
    }

    public TextField getCoordYField() {
        return coordYField;
    }

    public void setCoordYField(float coordY) {
        this.coordYField.setText(coordY + "");
    }

    public ComboBox<String> getQuartierField() {
        return quartierField;
    }

    public void setQuartierField(String quartier) {
        this.quartierField.setValue(quartier);
    }

    public void setOutput(String output) {
        this.output.setText(output);
    }
}

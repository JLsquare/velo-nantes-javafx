package view;

import controller.RemoveCompteurListener;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import modele.entities.Quartier;

public class RemoveCompteur extends GridPane {
    private RemoveCompteurListener listener;
    private Label menuLabel;
    private Label quartierLabel;
    private ComboBox<String> quartierField;
    private Label compteurLabel;
    private ComboBox<String> compteurField;
    private Button removeButton;
    private Label output;
    private Label warning;

    public RemoveCompteur() {
        super();
        this.listener = new RemoveCompteurListener(this);
        initializeComponents();
    }

    public void initializeComponents(){
        this.menuLabel = new Label("Supprimer un compteur");
        this.menuLabel.setStyle("-fx-font-size: 18px; -fx-font-weight: bold; -fx-padding: 10px 0px;");

        this.quartierLabel = new Label("Nom du quartier : ");
        this.quartierField = new ComboBox<String>();
        this.quartierField.getItems().add("Tous");
        for(Quartier quartier : VeloNantes.quartierDao.getAll()){
            this.quartierField.getItems().add(quartier.getNomQuartier() + " " + quartier.getIdQuartier());
        }

        this.compteurLabel = new Label("Nom du compteur : ");
        this.compteurField = new ComboBox<String>();

        this.removeButton = new Button("Supprimer");

        this.output = new Label("");
        this.warning = new Label("Attention, cela supprimera aussi les comptages associés à ce compteur.");

        this.add(this.menuLabel, 0, 0, 2, 1);
        this.add(this.quartierLabel, 0, 1);
        this.add(this.quartierField, 1, 1);
        this.add(this.compteurLabel, 0, 2);
        this.add(this.compteurField, 1, 2);
        this.add(this.removeButton, 1, 3);
        this.add(this.warning, 0, 4, 2, 1);
        this.add(this.output, 0, 5, 2, 1);

        this.quartierField.valueProperty().addListener(this.listener);
        this.compteurField.valueProperty().addListener(this.listener);
        this.quartierField.setValue("Tous");
        this.removeButton.setOnAction(this.listener);
    }

    public ComboBox<String> getCompteurField(){
        return this.compteurField;
    }

    public String getQuartier(){
        return this.quartierField.getValue();
    }

    public void setOutput(String output){
        this.output.setText(output);
    }
}

package view;

import controller.RemoveQuartierListener;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import modele.entities.Quartier;

public class RemoveQuartier extends GridPane {
    private RemoveQuartierListener listener;
    private Label menuLabel;
    private Label nomQuartierLabel;
    private ComboBox<String> nomQuartierField;
    private Button removeButton;
    private Label output;
    private Label warning;

    public RemoveQuartier() {
        super();
        this.listener = new RemoveQuartierListener(this);
        initializeComponents();
    }

    public void initializeComponents(){
        this.menuLabel = new Label("Supprimer un quartier");
        this.menuLabel.setStyle("-fx-font-size: 18px; -fx-font-weight: bold; -fx-padding: 10px 0px;");

        this.nomQuartierLabel = new Label("Nom du quartier : ");
        this.nomQuartierField = new ComboBox<String>();
        for(Quartier quartier : VeloNantes.quartierDao.getAll()){
            this.nomQuartierField.getItems().add(quartier.getNomQuartier() + " " + quartier.getIdQuartier());
        }

        this.removeButton = new Button("Supprimer");

        this.output = new Label("");
        this.warning = new Label("Attention, cela supprimera aussi les comptages associés à ce quartier.");

        this.add(this.menuLabel, 0, 0, 2, 1);
        this.add(this.nomQuartierLabel, 0, 1);
        this.add(this.nomQuartierField, 1, 1);
        this.add(this.removeButton, 1, 2);
        this.add(this.warning, 0, 3, 2, 1);
        this.add(this.output, 0, 4, 2, 1);

        this.nomQuartierField.valueProperty().addListener(this.listener);
        this.removeButton.setOnAction(this.listener);
    }

    public void setOutput(String output){
        this.output.setText(output);
    }
}

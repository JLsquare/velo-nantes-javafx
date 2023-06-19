package view;

import controller.RemoveQuartierListener;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import modele.dao.QuartierDao;
import modele.entities.Quartier;

public class RemoveQuartier extends VBox {
    private GridPane form;
    private RemoveQuartierListener listener;
    private Label menuLabel;
    private Label nomQuartierLabel;
    private ComboBox<String> nomQuartierField;
    private Button removeButton;
    private Label output;
    private QuartierDao quartierDao;

    public RemoveQuartier(QuartierDao quartierDao) {
        super();
        this.quartierDao = quartierDao;
        this.listener = new RemoveQuartierListener(this, this.quartierDao);
        initializeComponents();
    }

    public void initializeComponents(){
        this.form = new GridPane();
        this.menuLabel = new Label("Supprimer un quartier");
        this.menuLabel.setStyle("-fx-font-size: 18px; -fx-font-weight: bold; -fx-padding: 10px 0px;");

        this.nomQuartierLabel = new Label("Nom du quartier : ");
        this.nomQuartierField = new ComboBox<String>();
        for(Quartier quartier : this.quartierDao.getAll()){
            this.nomQuartierField.getItems().add(quartier.getNomQuartier() + " " + quartier.getIdQuartier());
        }

        this.removeButton = new Button("Supprimer");

        this.output = new Label("");

        this.form.add(this.nomQuartierLabel, 0, 0);
        this.form.add(this.nomQuartierField, 1, 0);
        this.form.add(this.removeButton, 0, 1);

        this.nomQuartierField.valueProperty().addListener(this.listener);
        this.removeButton.setOnAction(this.listener);

        this.getChildren().add(this.menuLabel);
        this.getChildren().add(this.form);
    }

    public String getQuartier(){
        return this.nomQuartierField.getValue();
    }

    public void setOutput(String output){
        this.output.setText(output);
    }
}

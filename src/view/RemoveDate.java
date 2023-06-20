package view;

import controller.RemoveDateListener;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

public class RemoveDate extends GridPane {
    private RemoveDateListener listener;
    private Label menuLabel;
    private Label dateLabel;
    private DatePicker dateField;
    private Button removeButton;
    private Label output;
    private Label warning;

    public RemoveDate() {
        super();
        this.listener = new RemoveDateListener(this);
        initializeComponents();
    }

    public void initializeComponents(){
        this.menuLabel = new Label("Supprimer une date");
        this.menuLabel.setStyle("-fx-font-size: 18px; -fx-font-weight: bold; -fx-padding: 10px 0px;");

        this.dateLabel = new Label("La date : ");
        this.dateField = new DatePicker();

        this.removeButton = new Button("Supprimer");

        this.output = new Label("");
        this.warning = new Label("Attention, cela supprimera aussi les comptages associés à cette date.");

        this.add(this.menuLabel, 0, 0, 2, 1);
        this.add(this.dateLabel, 0, 1);
        this.add(this.dateField, 1, 1);
        this.add(this.removeButton, 1, 2);
        this.add(this.warning, 0, 3, 2, 1);
        this.add(this.output, 0, 4, 2, 1);

        this.dateField.valueProperty().addListener(this.listener);
        this.removeButton.setOnAction(this.listener);
    }

    public String getDate(){
        return this.dateField.getValue().toString();
    }

    public void setOutput(String output){
        this.output.setText(output);
    }
}

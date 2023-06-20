package view;

import controller.InsertDateListener;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import modele.entities.Jour;
import modele.entities.Vacances;

public class InsertDate extends GridPane{
    private Label menuLabel;
    private Label dateLabel;
    private Label tempMoyLabel;
    private Label jourLabel;
    private Label vacancesLabel;

    private DatePicker dateField;
    private TextField tempMoyField;
    private ComboBox<String> jourField;
    private ComboBox<String> vacancesField;

    private Button insertButton;
    private Label output;
    private InsertDateListener listener;

    public InsertDate() {
        super();
        this.listener = new InsertDateListener(this);
        initializeComponents();
    }

    public void initializeComponents(){
        this.menuLabel = new Label("Insérer une date");
        this.menuLabel.setStyle("-fx-font-size: 18px; -fx-font-weight: bold; -fx-padding: 10px 0px;");
        this.dateLabel = new Label("Date : ");
        this.dateField = new DatePicker();
        this.tempMoyLabel = new Label("Température moyenne : ");
        this.tempMoyField = new TextField();
        this.jourLabel = new Label("Jour : ");
        this.jourField = new ComboBox<String>();
        this.vacancesLabel = new Label("Vacances : ");
        this.vacancesField = new ComboBox<String>();
        this.insertButton = new Button("Insérer");
        this.output = new Label("");

        for(Jour j : Jour.values()){
            this.jourField.getItems().add(j.toString());
        }

        for(Vacances v : Vacances.values()){
            this.vacancesField.getItems().add(v.toString());
        }

        this.dateField.valueProperty().addListener(this.listener);
        this.tempMoyField.textProperty().addListener(this.listener);
        this.jourField.valueProperty().addListener(this.listener);
        this.vacancesField.valueProperty().addListener(this.listener);

        this.add(this.menuLabel, 0, 0, 2, 1);
        this.add(this.dateLabel, 0, 1);
        this.add(this.dateField, 1, 1);
        this.add(this.tempMoyLabel, 0, 2);
        this.add(this.tempMoyField, 1, 2);
        this.add(this.jourLabel, 0, 3);
        this.add(this.jourField, 1, 3);
        this.add(this.vacancesLabel, 0, 4);
        this.add(this.vacancesField, 1, 4);
        this.add(this.insertButton, 1, 5);
        this.add(this.output, 0, 6, 2, 1);
    }

    public DatePicker getDateField() {
        return dateField;
    }

    public TextField getTempMoyField() {
        return tempMoyField;
    }

    public ComboBox<String> getJourField() {
        return jourField;
    }

    public ComboBox<String> getVacancesField() {
        return vacancesField;
    }

    public void setOutput(String output) {
        this.output.setText(output);
    }
}

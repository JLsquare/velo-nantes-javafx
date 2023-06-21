package view;

import controller.UpdateDateListener;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import modele.entities.Jour;
import modele.entities.Vacances;

public class UpdateDate extends GridPane {
    private Label menuLabel;
    private Label dateLabel;
    private Label tempMoyLabel;
    private Label jourLabel;
    private Label vacancesLabel;

    private DatePicker dateField;
    private TextField tempMoyField;
    private ComboBox<String> jourField;
    private ComboBox<String> vacancesField;

    private Button updateButton;
    private Label output;
    private Label warning;
    private UpdateDateListener listener;

    public UpdateDate() {
        super();
        this.listener = new UpdateDateListener(this);
        initializeComponents();
    }

    public void initializeComponents(){
        this.menuLabel = new Label("Modifier une date");
        this.menuLabel.setStyle("-fx-font-size: 18px; -fx-font-weight: bold; -fx-padding: 10px 0px;");
        this.dateLabel = new Label("La date : ");
        this.dateField = new DatePicker();
        this.tempMoyLabel = new Label("Température moyenne : ");
        this.tempMoyField = new TextField();
        this.jourLabel = new Label("Jour : ");
        this.jourField = new ComboBox<String>();
        this.vacancesLabel = new Label("Vacances : ");
        this.vacancesField = new ComboBox<String>();
        this.updateButton = new Button("Modifier");
        this.output = new Label("");
        this.warning = new Label("Attention, cela supprimera aussi les comptages associés à cette date.");

        this.add(this.menuLabel, 0, 0, 2, 1);
        this.add(this.dateLabel, 0, 1);
        this.add(this.dateField, 1, 1);
        this.add(this.tempMoyLabel, 0, 2);
        this.add(this.tempMoyField, 1, 2);
        this.add(this.jourLabel, 0, 3);
        this.add(this.jourField, 1, 3);
        this.add(this.vacancesLabel, 0, 4);
        this.add(this.vacancesField, 1, 4);
        this.add(this.updateButton, 1, 5);
        this.add(this.warning, 0, 6, 2, 1);
        this.add(this.output, 0, 7, 2, 1);

        this.dateField.valueProperty().addListener(this.listener);
        this.tempMoyField.textProperty().addListener(this.listener);
        this.jourField.valueProperty().addListener(this.listener);
        this.vacancesField.valueProperty().addListener(this.listener);
        this.updateButton.setOnAction(this.listener);
    }

    public String getDate(){
        return this.dateField.getValue().toString();
    }

    public TextField getTempMoyField(){
        return this.tempMoyField;
    }

    public void setTempMoyField(float tempMoy){
        this.tempMoyField.setText(tempMoy + "");
    }

    public ComboBox<String> getJourField(){
        return this.jourField;
    }

    public void setJourField(Jour jour){
        this.jourField.setValue(jour.toString());
    }

    public ComboBox<String> getVacancesField(){
        return this.vacancesField;
    }

    public void setVacancesField(Vacances vacances){
        this.vacancesField.setValue(vacances.toString());
    }

    public void setOutput(String output){
        this.output.setText(output);
    }
}

package view;

import controller.RemoveCompteurListener;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import modele.dao.CompteurDao;
import modele.dao.QuartierDao;
import modele.entities.Compteur;
import modele.entities.Quartier;

public class RemoveCompteur extends VBox {
    private GridPane form;
    private RemoveCompteurListener listener;
    private Label menuLabel;
    private Label nomQuartierLabel;
    private ComboBox<String> nomQuartierField;
    private Label nomCompteurLabel;
    private ComboBox<String> nomCompteurField;
    private Button removeButton;
    private Label output;
    private QuartierDao quartierDao;
    private CompteurDao compteurDao;

    public RemoveCompteur(QuartierDao quartierDao, CompteurDao compteurDao) {
        super();
        this.quartierDao = quartierDao;
        this.compteurDao = compteurDao;
        this.listener = new RemoveCompteurListener(this, this.compteurDao);
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
        this.nomQuartierField.getItems().add("Tous");
        this.nomQuartierField.setValue("Tous");

        this.nomCompteurLabel = new Label("Nom du compteur : ");
        this.nomCompteurField = new ComboBox<String>();
        this.updateCompteurs();

        this.removeButton = new Button("Supprimer");

        this.output = new Label("");

        this.form.add(this.nomQuartierLabel, 0, 0);
        this.form.add(this.nomQuartierField, 1, 0);
        this.form.add(this.nomCompteurLabel, 0, 1);
        this.form.add(this.nomCompteurField, 1, 1);
        this.form.add(this.removeButton, 0, 2);

        this.nomQuartierField.valueProperty().addListener(this.listener);
        this.removeButton.setOnAction(this.listener);

        this.getChildren().add(this.menuLabel);
        this.getChildren().add(this.form);
    }

    public void updateCompteurs(){
        System.out.println("updateCompteurs");
        this.nomCompteurField.getItems().clear();
        for(Compteur compteur : this.compteurDao.getAll()) {
            if((compteur.getLeQuartier().getNomQuartier() + " " + compteur.getLeQuartier().getIdQuartier()).equals(this.nomQuartierField.getValue()) || this.nomQuartierField.getValue().equals("Tous")) {
                String counter = compteur.getNomCompteur() + compteur.getSens() + " " + compteur.getIdCompteur();
                this.nomCompteurField.getItems().add(counter);
            }
        }
    }

    public String getCompteur(){
        return this.nomCompteurField.getValue();
    }

    public void setOutput(String output){
        this.output.setText(output);
    }
}

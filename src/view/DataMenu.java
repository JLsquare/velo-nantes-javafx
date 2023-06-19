package view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import modele.dao.*;
import modele.database.Database;
import modele.entities.*;

public class DataMenu extends HBox {
    private Label dataLabel;
    private String mode;
    private String table;
    private GridPane quartierForm;
    private GridPane compteurForm;
    private GridPane dateInfoForm;
    private GridPane comptageForm;
    private QuartierDao quartierDao;
    private CompteurDao compteurDao;
    private DateInfoDao dateInfoDao;
    private ComptageDao comptageDao;
    private VBox dataPanel;
    private ComboBox<String> quartierField;
    private ComboBox<String> compteurField;
    private DatePicker dateInfoField;
    private DatePicker comptageDatePicker;
    private ComboBox<String> comptageCompteurField;

    public DataMenu(Database database, QuartierDao quartierDao, CompteurDao compteurDao, DateInfoDao dateInfoDao, ComptageDao comptageDao) throws IllegalArgumentException{
        if(database == null){
            throw new IllegalArgumentException("Database cannot be null");
        }
        if(quartierDao == null){
            throw new IllegalArgumentException("QuartierDao cannot be null");
        }
        if(compteurDao == null){
            throw new IllegalArgumentException("CompteurDao cannot be null");
        }
        if(dateInfoDao == null){
            throw new IllegalArgumentException("DateInfoDao cannot be null");
        }
        if(comptageDao == null){
            throw new IllegalArgumentException("ComptageDao cannot be null");
        }

        this.quartierDao = quartierDao;
        this.compteurDao = compteurDao;
        this.dateInfoDao = dateInfoDao;
        this.comptageDao = comptageDao;
        initializeComponents();
    }

    private void initializeComponents() {
        VBox menuList = new VBox();
        menuList.setSpacing(8); 
        menuList.setPadding(new Insets(10)); 

        String[] actions = new String[]{"Modification", "Saisie", "Suppression"};
        String[] tables = new String[]{"Quartier", "Compteur", "DateInfo", "Comptage"};

        for (String action : actions) {
            Label categoryLabel = new Label(action + " des données existantes");
            categoryLabel.setStyle("-fx-font-size: 18px; -fx-font-weight: bold; -fx-padding: 10px 0px;");
            menuList.getChildren().add(categoryLabel);

            for (String table : tables) {
                Button button;

                if(action.equals("Modification")){
                    button = new Button("Modifier " + table);
                } else if(action.equals("Saisie")){
                    button = new Button("Ajouter à " + table);
                } else {
                    button = new Button("Supprimer de " + table);
                }

                button.setOnAction(e -> {
                    this.mode = action;
                    this.table = table;
                    this.dataLabel.setText(this.mode + " " + this.table);
                    updateForm();
                });

                button.setMaxWidth(512);
                button.setAlignment(Pos.CENTER);
                button.setStyle("-fx-font-size: 16px; -fx-padding: 5px;");
                menuList.getChildren().add(button);
            }
        }

        this.dataPanel = new VBox();
        this.dataPanel.setPrefWidth(512);
        this.dataPanel.setAlignment(Pos.TOP_CENTER);

        this.dataLabel = new Label();
        this.dataLabel.setStyle("-fx-font-size: 18px; -fx-font-weight: bold; -fx-padding: 10px 0px;");
        this.dataPanel.getChildren().add(this.dataLabel);

        this.quartierForm = new GridPane();
        Label quartierLabel = new Label("Nom du quartier :");
        this.quartierField = new ComboBox<String>();
        for(Quartier quartier : quartierDao.getAll()){
            this.quartierField.getItems().add(quartier.getNomQuartier() + " " + quartier.getIdQuartier());
        }
        this.quartierForm.add(quartierLabel, 0, 0);
        this.quartierForm.add(quartierField, 1, 0);

        this.compteurForm = new GridPane();
        Label compteurLabel = new Label("Nom du compteur :");
        this.compteurField = new ComboBox<String>();
        for(Compteur compteur : compteurDao.getAll()){
            this.compteurField.getItems().add(compteur.getNomCompteur() + " " + compteur.getSens() + " " + compteur.getIdCompteur());
        }
        this.compteurForm.add(compteurLabel, 0, 0);
        this.compteurForm.add(compteurField, 1, 0);

        this.dateInfoForm = new GridPane();
        Label dateInfoLabel = new Label("Date :");
        this.dateInfoField = new DatePicker();
        dateInfoForm.add(dateInfoLabel, 0, 0);
        dateInfoForm.add(dateInfoField, 1, 0);

        this.comptageForm = new GridPane();
        Label comptageDataLabel = new Label("Date :");
        this.comptageDatePicker = new DatePicker();
        Label comptageCompteurLabel = new Label("Compteur :");
        this.comptageCompteurField = new ComboBox<String>();
        for(Compteur compteur : compteurDao.getAll()){
            this.comptageCompteurField.getItems().add(compteur.getNomCompteur() + " " + compteur.getSens() + " " + compteur.getIdCompteur());
        }
        
        this.comptageForm.add(comptageDataLabel, 0, 0);
        this.comptageForm.add(this.comptageDatePicker, 1, 0);
        this.comptageForm.add(comptageCompteurLabel, 0, 1);
        this.comptageForm.add(this.comptageCompteurField, 1, 1);

        this.getChildren().addAll(menuList, dataPanel);
    }

    private void updateForm() {
        dataPanel.getChildren().clear();
        dataPanel.getChildren().add(dataLabel);

        switch (table) {
            case "Quartier":
                dataPanel.getChildren().add(quartierForm);
                break;
            case "Compteur":
                dataPanel.getChildren().add(compteurForm);
                break;
            case "DateInfo":
                dataPanel.getChildren().add(dateInfoForm);
                break;
            case "Comptage":
                dataPanel.getChildren().add(comptageForm);
                break;
        }
    }
}

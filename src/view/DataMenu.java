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
    private String mode;
    private String table;
    private QuartierDao quartierDao;
    private CompteurDao compteurDao;
    private DateInfoDao dateInfoDao;
    private ComptageDao comptageDao;
    private InsertQuartier insertQuartier;
    private VBox menuList;

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
        menuList = new VBox();
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
                    updateForm();
                });

                button.setMaxWidth(512);
                button.setAlignment(Pos.CENTER);
                button.setStyle("-fx-font-size: 16px; -fx-padding: 5px;");
                menuList.getChildren().add(button);
            }
        }

        this.insertQuartier = new InsertQuartier(this.quartierDao);

        this.getChildren().addAll(menuList);
    }

    public void updateForm(){
        this.getChildren().clear();
        this.getChildren().add(this.menuList);
        if(this.mode.equals("Saisie")){
            if(this.table.equals("Quartier")){
                this.getChildren().add(this.insertQuartier);
            }
        }
    }
}

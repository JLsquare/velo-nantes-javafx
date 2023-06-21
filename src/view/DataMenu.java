package view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class DataMenu extends HBox {
    private String mode;
    private String table;
    private VBox menuList;

    private UpdateQuartier updateQuartier;
    private UpdateCompteur updateCompteur;
    private UpdateDate updateDate;
    private UpdateComptage updateComptage;

    private InsertQuartier insertQuartier;
    private InsertCompteur insertCompteur;
    private InsertDate insertDate;
    private InsertComptage insertComptage;

    private RemoveQuartier removeQuartier;
    private RemoveCompteur removeCompteur;
    private RemoveDate removeDate;
    private RemoveComptage removeComptage;

    public DataMenu() {
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

        this.updateQuartier = new UpdateQuartier();
        this.updateCompteur = new UpdateCompteur();
        this.updateDate = new UpdateDate();
        this.updateComptage = new UpdateComptage();

        this.insertQuartier = new InsertQuartier();
        this.insertCompteur = new InsertCompteur();
        this.insertDate = new InsertDate();
        this.insertComptage = new InsertComptage();

        this.removeQuartier = new RemoveQuartier();
        this.removeCompteur = new RemoveCompteur();
        this.removeDate = new RemoveDate();
        this.removeComptage = new RemoveComptage();

        this.getChildren().addAll(menuList);
    }

    public void updateForm(){
        this.getChildren().clear();
        this.getChildren().add(this.menuList);
        if(this.mode.equals("Modification")){
            if(this.table.equals("Quartier")){
                this.getChildren().add(this.updateQuartier);
            } else if(this.table.equals("Compteur")){
                this.getChildren().add(this.updateCompteur);
            } else if(this.table.equals("DateInfo")){
                this.getChildren().add(this.updateDate);
            } else if(this.table.equals("Comptage")){
                this.getChildren().add(this.updateComptage);
            }
        } else if(this.mode.equals("Saisie")){
            if(this.table.equals("Quartier")){
                this.getChildren().add(this.insertQuartier);
            } else if(this.table.equals("Compteur")){
                this.getChildren().add(this.insertCompteur);
            } else if(this.table.equals("DateInfo")){
                this.getChildren().add(this.insertDate);
            } else if(this.table.equals("Comptage")){
                this.getChildren().add(this.insertComptage);
            }
        } else if(this.mode.equals("Suppression")){
            if(this.table.equals("Quartier")){
                this.getChildren().add(this.removeQuartier);
            } else if(this.table.equals("Compteur")){
                this.getChildren().add(this.removeCompteur);
            } else if(this.table.equals("DateInfo")){
                this.getChildren().add(this.removeDate);
            } else if(this.table.equals("Comptage")){
                this.getChildren().add(this.removeComptage);
            }
        }
    }
}

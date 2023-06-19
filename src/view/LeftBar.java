package view;
import java.util.ArrayList;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Line;
import modele.database.Database;
import modele.entities.*;

public class LeftBar extends VBox{
    private Label title;
    private Line separator;
    private Pane spacer;
    private Filters filters;
    private Authentification authentification;
    private ArrayList<Quartier> quartiers;
    private ArrayList<Compteur> compteurs;
    private Graph graph;
    private Database database;

    public LeftBar(Graph graph, ArrayList<Quartier> quartiers, ArrayList<Compteur> compteurs, Database database) throws IllegalArgumentException{
        if(graph == null || quartiers == null || compteurs == null || database == null){
            throw new IllegalArgumentException("LeftBar: arguments cannot be null");
        }

        this.graph = graph;
        this.quartiers = quartiers;
        this.compteurs = compteurs;
        this.database = database;

        this.setPadding(new Insets(15, 12, 15, 12));
        this.setSpacing(10);
        this.setMinWidth(256); 

        this.initializeComponents();
    }

    public void initializeComponents(){
        this.title = new Label("Velo de Nantes");
        this.title.setStyle("-fx-font-size: 20;");
        this.getChildren().add(this.title);

        this.separator = new Line();
        this.separator.setEndX(256);
        this.getChildren().add(this.separator);

        this.spacer = new Pane();
        this.spacer.setMinHeight(100);
        this.getChildren().add(this.spacer);

        this.filters = new Filters(this.graph, this.quartiers, this.compteurs);
        this.getChildren().add(this.filters);

        this.authentification = new Authentification(this.database);
    }

    public Filters getFilters(){
        return this.filters;
    }

    public Authentification getAuthentification(){
        return this.authentification;
    }

    public void removeAll(){
        this.getChildren().remove(this.filters);
        this.getChildren().remove(this.authentification);
    }

    public void toGraph(){
        this.removeAll();
        this.getChildren().add(this.filters);
    }

    public void toData(){
        this.removeAll();
        this.getChildren().add(this.authentification);
    }
}

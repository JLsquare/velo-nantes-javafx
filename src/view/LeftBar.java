package view;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Line;

public class LeftBar extends VBox{
    private Label title;
    private Line separator;
    private Pane spacer;
    private Graph graph;
    private Filters filters;
    private Authentification authentification;
    private Connected connected;

    public LeftBar(Graph graph) throws IllegalArgumentException{
        if(graph == null){
            throw new IllegalArgumentException("Graph cannot be null");
        }

        this.graph = graph;

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

        this.filters = new Filters(this.graph);
        this.getChildren().add(this.filters);

        this.authentification = new Authentification(this);
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
        this.getChildren().remove(this.connected);
    }

    public void toGraph(){
        this.removeAll();
        this.getChildren().add(this.filters);
    }

    public void toAuthentification(){
        this.removeAll();
        if(this.connected != null){
            this.getChildren().add(this.connected);
        } else {
            this.getChildren().add(this.authentification);
        }
    }

    public void toConnected(String login){
        this.removeAll();
        this.connected = new Connected(login);
        this.getChildren().add(this.connected);
    }
}

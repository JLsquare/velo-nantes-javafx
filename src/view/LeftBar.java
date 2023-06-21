package view;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Line;

public class LeftBar extends VBox{

    // ---------------- Attributes ---------------- //

    private Label title;
    private Line separator;
    private Pane spacer;
    private Graph graph;
    private Filters filters;
    private Authentification authentification;
    private InfoGraphiques infoGraphiques;
    private Connected connected;

    // ---------------- Constructors ---------------- //

    /**
     * Constructor of the LeftBar class
     * @param graph the graph of the application
     * @throws IllegalArgumentException if graph is null
     */
    public LeftBar(Graph graph) throws IllegalArgumentException{
        if(graph == null){
            throw new IllegalArgumentException("Graph cannot be null");
        }

        this.graph = graph;
        this.initializeComponents();
    }

    // ---------------- Methods ---------------- //

    /**
     * Initialize the components of the LeftBar class
     */
    public void initializeComponents(){
        this.setPadding(new Insets(15, 12, 15, 12));
        this.setSpacing(10);
        this.setMinWidth(256); 

        this.title = new Label("Velo de Nantes");
        this.title.setStyle("-fx-font-size: 20;");

        this.separator = new Line();
        this.separator.setEndX(256);

        this.spacer = new Pane();
        this.spacer.setMinHeight(100);

        this.filters = new Filters(this.graph);
        this.authentification = new Authentification(this);
        this.infoGraphiques = new InfoGraphiques();

        this.getChildren().addAll(this.title, this.separator, this.spacer, this.filters);
    }

    /**
     * Remove all the components of the LeftBar
     * Without the title and the separator
     */
    public void removeAll(){
        this.getChildren().remove(this.filters);
        this.getChildren().remove(this.authentification);
        this.getChildren().remove(this.connected);
        this.getChildren().remove(this.infoGraphiques);
    }

    /**
     * Display the filters
     */
    public void toGraph(){
        this.removeAll();
        this.getChildren().add(this.filters);
    }

    /**
     * Display the authentification
     */
    public void toAuthentification(){
        this.removeAll();
        if(this.connected != null){
            this.getChildren().add(this.connected);
        } else {
            this.getChildren().add(this.authentification);
        }
    }

    /**
     * Display the connected
     * @param login the login of the user
     * @throws IllegalArgumentException if login is null
     */
    public void toConnected(String login) throws IllegalArgumentException{
        if(login == null){
            throw new IllegalArgumentException("Login cannot be null");
        }

        this.removeAll();
        this.connected = new Connected(login);
        this.getChildren().add(this.connected);
    }

    /**
     * Display the infoGraphiques
     */
    public void toGraphiques(){
        this.removeAll();
        this.getChildren().add(this.infoGraphiques);
    }

    // ---------------- Getters & Setters ---------------- //
    
    /**
     * Get the filters
     * @return the filters
     */
    public Filters getFilters(){
        return this.filters;
    }

    /**
     * Get the authentification
     * @return the authentification
     */
    public Authentification getAuthentification(){
        return this.authentification;
    }
}

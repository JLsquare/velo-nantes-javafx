package view;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.MenuButton;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import modele.dao.*;
import modele.database.Database;

import java.sql.SQLException;

public class VeloNantes extends Application {
    private LeftBar leftBar;
    private MenuButton menu;
    private Graph graph;
    private Database database;
    private QuartierDao quartierDao;
    private CompteurDao compteurDao;
    private DateInfoDao dateInfoDao;
    private ComptageDao comptageDao;
    private Stage primaryStage;

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.initializeData();
        primaryStage.setTitle("Velo de Nantes");
        this.initializeComponents();

    }

    private void initializeComponents(){
        this.menu = new MenuButton();
        this.menu.setText("Menu");
        this.menu.getItems().add(new javafx.scene.control.MenuItem("Graphes"));
        this.menu.getItems().add(new javafx.scene.control.MenuItem("Map"));
        this.menu.getItems().add(new javafx.scene.control.MenuItem("Données"));
        this.menu.getItems().add(new javafx.scene.control.MenuItem("Quitter"));
        //listener
        this.menu.getItems().get(0).setOnAction(e -> {
            System.out.println("Graphes");
            this.graph.setVisible(true);
            this.leftBar.getFilters().setVisible(true);
        });
        this.menu.getItems().get(1).setOnAction(e -> {
            System.out.println("Map");
            //hide graph
            this.graph.setVisible(false);
            this.leftBar.getFilters().setVisible(false);
        });
        this.menu.getItems().get(2).setOnAction(e -> {
            System.out.println("Données");
            this.graph.setVisible(false);
            this.leftBar.getFilters().setVisible(false);
        });
        this.menu.getItems().get(3).setOnAction(e -> {
            System.out.println("Quitter");
            this.primaryStage.close();
        });

        this.graph = new Graph(this.quartierDao.getAll(), this.compteurDao.getAll(), this.dateInfoDao.getAll());
        this.leftBar = new LeftBar(this.graph, this.quartierDao.getAll(), this.compteurDao.getAll());

        AnchorPane rightPane = new AnchorPane();
        rightPane.setPadding(new Insets(15, 12, 15, 12));
        rightPane.getChildren().addAll(menu, graph);

        AnchorPane.setTopAnchor(menu, 0.0);
        AnchorPane.setRightAnchor(menu, 0.0);

        AnchorPane.setTopAnchor(graph, 30.0);

        BorderPane root = new BorderPane();
        root.setLeft(this.leftBar);
        root.setCenter(rightPane);

        Scene scene = new Scene(root, 1280, 720);
        this.primaryStage.setScene(scene);
        this.primaryStage.show();
    }

    private void initializeData(){
        this.database = new Database("jdbc:mariadb://localhost:3306/bd_velo_4b2");
        try{
            database.openReadConnection("read_4b2", "read_4b2");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        this.quartierDao = new QuartierDao(this.database);
        this.compteurDao = new CompteurDao(this.database, this.quartierDao);
        this.dateInfoDao = new DateInfoDao(this.database);
        this.comptageDao = new ComptageDao(this.database, this.compteurDao, this.dateInfoDao);

        try {
            this.quartierDao.readAll();
            this.compteurDao.readAll();
            this.dateInfoDao.readAll();
            this.comptageDao.readAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }

    public Graph getGraph(){
        return this.graph;
    }
}

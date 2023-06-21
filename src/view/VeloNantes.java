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
    private Stage primaryStage;
    private LeftBar leftBar;
    private MenuButton menu;
    private Graph graph;
    private DataMenu dataMenu;
    private Map map;

    public static Database database;
    public static QuartierDao quartierDao;
    public static CompteurDao compteurDao;
    public static DateInfoDao dateInfoDao;
    public static ComptageDao comptageDao;

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

        AnchorPane rightPane = new AnchorPane();

        this.menu.getItems().get(0).setOnAction(e -> {
            System.out.println("Graphes");
            rightPane.getChildren().clear();
            rightPane.getChildren().addAll(menu, graph); 
            this.leftBar.toGraph();
        });

        this.menu.getItems().get(1).setOnAction(e -> {
            System.out.println("Map");
            rightPane.getChildren().clear(); 
            rightPane.getChildren().addAll(menu, map);
        });

        this.menu.getItems().get(2).setOnAction(e -> {
            System.out.println("Données");
            rightPane.getChildren().clear(); 
            rightPane.getChildren().addAll(menu, dataMenu); 
            this.leftBar.toAuthentification();
        });

        this.menu.getItems().get(3).setOnAction(e -> {
            System.out.println("Quitter");
            this.primaryStage.close();
        });

        this.graph = new Graph();
        this.leftBar = new LeftBar(graph);
        this.dataMenu = new DataMenu();
        this.map = new Map(this.leftBar.getFilters());

        rightPane.setPadding(new Insets(15, 12, 15, 12));
        rightPane.getChildren().addAll(menu, graph);

        AnchorPane.setTopAnchor(menu, 0.0);
        AnchorPane.setRightAnchor(menu, 0.0);

        AnchorPane.setTopAnchor(graph, 30.0);

        AnchorPane.setTopAnchor(dataMenu, 30.0);

        BorderPane root = new BorderPane();
        root.setLeft(this.leftBar);
        root.setCenter(rightPane);

        Scene scene = new Scene(root, 1280, 720);
        this.primaryStage.setScene(scene);
        this.primaryStage.show();
    }

    private void initializeData(){
        try{
            database = new Database("jdbc:mariadb://localhost:3306/bd_velo_4b2");
            database.openReadConnection("read_4b2", "read_4b2");
        } catch (SQLException e) {
            try {
                database = new Database("jdbc:mysql://localhost:3306/bd_velo_4b2");
                database.openReadConnection("read_4b2", "read_4b2");
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }

        quartierDao = new QuartierDao(database);
        compteurDao = new CompteurDao(database, quartierDao);
        dateInfoDao = new DateInfoDao(database);
        comptageDao = new ComptageDao(database, compteurDao, dateInfoDao);

        try {
            quartierDao.readAll();
            compteurDao.readAll();
            dateInfoDao.readAll();
            comptageDao.readAll();
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

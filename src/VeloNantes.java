import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.shape.Line;
import javafx.stage.Stage;
import modele.dao.*;
import modele.database.Database;

import java.sql.SQLException;
import java.time.LocalDate;

public class VeloNantes extends Application {
    private LeftBar leftBar;
    private LeMenu menu;
    private Graph graph;
    private Database database;
    private QuartierDao quartierDao;
    private CompteurDao compteurDao;
    private DateInfoDao dateInfoDao;
    private ComptageDao comptageDao;

    @Override
    public void start(Stage primaryStage) {
        this.initializeData();
        primaryStage.setTitle("Velo de Nantes");

        this.leftBar = new LeftBar(quartierDao.getAll(), compteurDao.getAll());
        this.menu = new LeMenu();
        this.graph = new Graph(this.leftBar);

        VBox rightPane = new VBox();
        rightPane.setPadding(new Insets(15, 12, 15, 12));
        rightPane.getChildren().add(menu);
        rightPane.getChildren().add(graph);

        BorderPane root = new BorderPane();
        root.setLeft(this.leftBar);
        root.setRight(rightPane);

        Scene scene = new Scene(root, 1280, 720);
        primaryStage.setScene(scene);
        primaryStage.show();
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
}

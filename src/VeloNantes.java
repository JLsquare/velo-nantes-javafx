import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

public class VeloNantes extends Application {
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Velo de Nantes");

        VBox leftPane = new VBox();
        leftPane.setPadding(new Insets(15, 12, 15, 12));
        leftPane.setSpacing(10);
        leftPane.setMinWidth(256); 

        Label title = new Label("Velo de Nantes");
        title.setStyle("-fx-font-size: 20;");
        leftPane.getChildren().add(title);

        Line separator = new Line();
        separator.setEndX(256);
        leftPane.getChildren().add(separator);

        Pane spacer = new Pane();
        spacer.setMinHeight(100);
        leftPane.getChildren().add(spacer);

        GridPane topFiltersGrid = new GridPane();
        topFiltersGrid.setHgap(10);
        topFiltersGrid.setVgap(10);
        topFiltersGrid.setPadding(new Insets(0, 10, 0, 10));

        GridPane bottomFiltersGrid = new GridPane();
        bottomFiltersGrid.setHgap(10);
        bottomFiltersGrid.setVgap(10);
        bottomFiltersGrid.setPadding(new Insets(0, 10, 0, 10));

        Label startDateLabel = new Label("Date début:");
        DatePicker startDatePicker = new DatePicker();
        Label endDateLabel = new Label("Date fin:");
        DatePicker endDatePicker = new DatePicker();

        Label neighborhoodLabel = new Label("Quartier:");
        TextField neighborhoodField = new TextField();
        neighborhoodField.setDisable(true); 
        Label counterLabel = new Label("Compteur:");
        TextField counterField = new TextField();
        counterField.setDisable(true);

        Label totalPassagesLabel = new Label("Total passages:");
        CheckBox totalPassages = new CheckBox();
        Label averagePassagesLabel = new Label("Moyenne passages:");
        CheckBox averagePassages = new CheckBox();
        Label totalPassagesPerHourLabel = new Label("Total passages par heure:");
        CheckBox totalPassagesPerHour = new CheckBox();
        Label averagePassagesPerHourLabel = new Label("Moyenne passages par heure:");
        CheckBox averagePassagesPerHour = new CheckBox();
        Label totalPassagesPerDayLabel = new Label("Total passage par jour:");
        CheckBox totalPassagesPerDay = new CheckBox();
        Label averagePassagesPerDayLabel = new Label("Moyenne passages par jour:");
        CheckBox averagePassagesPerDay = new CheckBox();

        topFiltersGrid.add(startDateLabel, 0, 0);
        topFiltersGrid.add(startDatePicker, 1, 0);
        topFiltersGrid.add(endDateLabel, 0, 1);
        topFiltersGrid.add(endDatePicker, 1, 1);
        topFiltersGrid.add(neighborhoodLabel, 0, 2);
        topFiltersGrid.add(neighborhoodField, 1, 2);
        topFiltersGrid.add(counterLabel, 0, 3);
        topFiltersGrid.add(counterField, 1, 3);
        bottomFiltersGrid.add(totalPassagesLabel, 0, 4);
        bottomFiltersGrid.add(totalPassages, 1, 4);
        bottomFiltersGrid.add(averagePassagesLabel, 0, 5);
        bottomFiltersGrid.add(averagePassages, 1, 5);
        bottomFiltersGrid.add(totalPassagesPerHourLabel, 0, 6);
        bottomFiltersGrid.add(totalPassagesPerHour, 1, 6);
        bottomFiltersGrid.add(averagePassagesPerHourLabel, 0, 7);
        bottomFiltersGrid.add(averagePassagesPerHour, 1, 7);
        bottomFiltersGrid.add(totalPassagesPerDayLabel, 0, 8);
        bottomFiltersGrid.add(totalPassagesPerDay, 1, 8);
        bottomFiltersGrid.add(averagePassagesPerDayLabel, 0, 9);
        bottomFiltersGrid.add(averagePassagesPerDay, 1, 9);

        leftPane.getChildren().add(topFiltersGrid);
        leftPane.getChildren().add(bottomFiltersGrid);

        MenuButton menuButton = new MenuButton("Menu");
        menuButton.getItems().addAll(
                new MenuItem("Option 1"),
                new MenuItem("Option 2"),
                new MenuItem("Option 3")
        );

        VBox rightPane = new VBox();
        rightPane.setPadding(new Insets(15, 12, 15, 12));
        rightPane.getChildren().add(menuButton);

        BorderPane root = new BorderPane();
        root.setLeft(leftPane);
        root.setRight(rightPane);

        Scene scene = new Scene(root, 1280, 720);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
}

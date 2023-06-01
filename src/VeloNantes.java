import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;

import java.io.IOException;

public class VeloNantes extends Application {
    @Override
    public void start(Stage primaryStage) throws IOException {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/Layout.fxml"));
            Scene scene = new Scene(root, 1080, 720);
            primaryStage.setTitle("Velo de Nantes");
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    public static void main(String[] args) {
        launch(args);
    }
}
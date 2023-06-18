import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;

public class VeloNantes extends Application {
    @Override
    public void start(Stage primaryStage) throws IOException {
        primaryStage.setTitle("Velo de Nantes");

        StackPane root = new StackPane();
        primaryStage.setScene(new Scene(root, 1280, 720));
        
        primaryStage.show(); 
    }
    public static void main(String[] args) {
        launch(args);
    }
}
package view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class Graphiques extends VBox {
    private ImageView imageView;
    private Button previousButton;
    private Button nextButton;
    private int currentIndex;

    private static final int MAX_INDEX = 7;

    public Graphiques() {
        initializeComponents();
        showImage(1); // Afficher la première image au démarrage
    }

    private void initializeComponents() {
        HBox buttonsBox = new HBox();
        buttonsBox.setAlignment(Pos.CENTER);
        buttonsBox.setSpacing(10);

        previousButton = new Button("Précédent");
        nextButton = new Button("Suivant");

        previousButton.setOnAction(e -> showPreviousImage());
        nextButton.setOnAction(e -> showNextImage());

        buttonsBox.getChildren().addAll(previousButton, nextButton);

        imageView = new ImageView();
        imageView.setPreserveRatio(true);

        this.getChildren().addAll(imageView, buttonsBox);
        this.setAlignment(Pos.CENTER);
        this.setSpacing(10);
        this.setPadding(new Insets(10));
    }

    private void showImage(int index) {
        if (index < 1 || index > MAX_INDEX) {
            return;
        }

        String imageName = "graphique" + index + ".png";
        String imagePath = "/data/" + imageName;

        Image image = new Image(getClass().getResourceAsStream(imagePath));
        imageView.setImage(image);
        currentIndex = index;
    }

    private void showPreviousImage() {
        int previousIndex = currentIndex - 1;
        if (previousIndex < 1) {
            previousIndex = MAX_INDEX;
        }
        showImage(previousIndex);
    }

    private void showNextImage() {
        int nextIndex = currentIndex + 1;
        if (nextIndex > MAX_INDEX) {
            nextIndex = 1;
        }
        showImage(nextIndex);
    }
}

package controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import view.Graphiques;

public class GraphiquesListener implements EventHandler<ActionEvent>{
    private Graphiques graphiques;
    private int currentIndex;
    private static final int MAX_INDEX = 8;

    public GraphiquesListener(Graphiques graphiques) {
        this.graphiques = graphiques;
        this.showImage(1);
    }

    @Override
    public void handle(ActionEvent event) {
        if(event.getSource() == this.graphiques.getPreviousButton()){
            int previousIndex = currentIndex - 1;
            if (previousIndex < 1) {
                previousIndex = MAX_INDEX;
            }
            this.showImage(previousIndex);
        }

        if(event.getSource() == this.graphiques.getNextButton()){
            int nextIndex = currentIndex + 1;
            if (nextIndex > MAX_INDEX) {
                nextIndex = 1;
            }
            this.showImage(nextIndex);
        }
    }

    private void showImage(int index) throws IllegalArgumentException {
        if (index < 1 || index > MAX_INDEX) {
            throw new IllegalArgumentException("Index must be between 1 and " + MAX_INDEX);
        }

        String imagePath = "graphique" + index + ".png";
        Image image = new Image(imagePath);
        this.graphiques.setImage(image);
        currentIndex = index;
    }
}

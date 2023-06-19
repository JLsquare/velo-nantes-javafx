package view;

import javafx.scene.control.ComboBox;

public interface IFilters {
    public void updateGraph();
    public ComboBox<String>  getNeighborhoodField();
    public void updateCompteurs();
}

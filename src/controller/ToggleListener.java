package controller;
import javafx.beans.value.ChangeListener;
import javafx.scene.control.Toggle;
import view.IFilters;

public class ToggleListener implements ChangeListener<Toggle> {
    private IFilters filters;

    public ToggleListener(IFilters filters) {
        this.filters = filters;
    }

    @Override
    public void changed(javafx.beans.value.ObservableValue<? extends Toggle> observable, Toggle oldValue, Toggle newValue) {
        System.out.println("ToggleListener: " + oldValue + " " + newValue);
        filters.updateGraph();
    }
}

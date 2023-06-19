package controller;
import javafx.beans.value.ChangeListener;
import view.IFilters;

public class StringListener implements ChangeListener<String> {
    private IFilters filters;

    public StringListener(IFilters filters) {
        this.filters = filters;
    }

    @Override
    public void changed(javafx.beans.value.ObservableValue<? extends String> observable, String oldValue, String newValue) {
        if(observable == this.filters.getNeighborhoodField().valueProperty()){
            this.filters.updateCompteurs();
        }
        System.out.println("StringListener: " + oldValue + " " + newValue);
        filters.updateGraph();
    }    
}

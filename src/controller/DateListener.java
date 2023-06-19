package controller;
import java.time.LocalDate;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import view.IFilters;

public class DateListener implements ChangeListener<LocalDate> {
    private IFilters filters;

    public DateListener(IFilters filters) {
        this.filters = filters;
    }

    @Override
    public void changed(ObservableValue<? extends LocalDate> observable, LocalDate oldValue, LocalDate newValue) {
        System.out.println("DateListener: " + oldValue);
        filters.updateGraph();
    }    
}

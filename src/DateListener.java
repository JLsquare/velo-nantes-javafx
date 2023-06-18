import java.time.LocalDate;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

public class DateListener implements ChangeListener<LocalDate> {
    private LeftBar leftBar;

    public DateListener(LeftBar leftBar) {
        this.leftBar = leftBar;
    }

    @Override
    public void changed(ObservableValue<? extends LocalDate> observable, LocalDate oldValue, LocalDate newValue) {
        System.out.println("DateListener: " + oldValue);
        leftBar.updateGraph();
    }    
}

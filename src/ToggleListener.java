import javafx.beans.value.ChangeListener;
import javafx.scene.control.Toggle;

public class ToggleListener implements ChangeListener<Toggle> {
    private LeftBar leftBar;

    public ToggleListener(LeftBar leftBar) {
        this.leftBar = leftBar;
    }

    @Override
    public void changed(javafx.beans.value.ObservableValue<? extends Toggle> observable, Toggle oldValue, Toggle newValue) {
        System.out.println("ToggleListener: " + oldValue + " " + newValue);
        leftBar.updateGraph();
    }
}

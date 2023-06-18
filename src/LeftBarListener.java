import javafx.beans.value.ChangeListener;

public class LeftBarListener implements ChangeListener<String> {
    private LeftBar leftBar;

    public LeftBarListener(LeftBar leftBar) {
        this.leftBar = leftBar;
    }

    @Override
    public void changed(javafx.beans.value.ObservableValue<? extends String> observable, String oldValue, String newValue) {
        if(observable == this.leftBar.getNeighborhoodField().valueProperty()){
            this.leftBar.updateCompteurs();
        }
        System.out.println("LeftBarListener: " + oldValue);
        leftBar.updateGraph();
    }    
}

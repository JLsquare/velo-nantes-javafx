import javafx.scene.layout.VBox;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;

public class Graph extends VBox{
    private LeftBar leftBar;
    private BarChart<String, Number> barChart;
    
    public Graph(LeftBar leftBar) {
        this.leftBar = leftBar;
        this.update();
    }

    public void update(){
        this.getChildren().clear();
        this.barChart = new BarChart<String, Number>(new CategoryAxis(), new NumberAxis());
        this.barChart.setTitle("Graphique");
        this.barChart.setLegendVisible(false);
        this.barChart.setMinWidth(960);
        this.barChart.setMinHeight(576);
        this.barChart.getData().add(new XYChart.Series<String, Number>());
        this.getChildren().add(this.barChart);
    }
}

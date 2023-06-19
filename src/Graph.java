import javafx.scene.layout.VBox;
import modele.entities.*;

import java.sql.Date;
import java.util.ArrayList;

import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;

public class Graph extends VBox{
    private LeftBar leftBar;
    private BarChart<String, Number> barChart;
    private ArrayList<Quartier> quartiers;
    private ArrayList<Compteur> compteurs;
    private ArrayList<DateInfo> dateInfos;
    
    public Graph(LeftBar leftBar, ArrayList<Quartier> quartiers, ArrayList<Compteur> compteurs, ArrayList<DateInfo> dateInfos) {
        this.quartiers = quartiers;
        this.compteurs = compteurs;
        this.dateInfos = dateInfos;
        this.leftBar = leftBar;
        this.update(GraphType.totalPassages);
    }

    public void update(GraphType graphType){
        this.getChildren().clear();
        this.barChart = new BarChart<String, Number>(new CategoryAxis(), new NumberAxis());
        this.barChart.setTitle("Graphique");
        this.barChart.setLegendVisible(false);
        this.barChart.setMinWidth(800);
        this.barChart.setMinHeight(576);
        this.barChart.getData().add(new XYChart.Series<String, Number>());
        this.getChildren().add(this.barChart);
        if(graphType == GraphType.totalPassages){
            this.simplePassages(false);
        } else if (graphType == GraphType.averagePassages){
            this.simplePassages(true);
        }
        /*else if(graphType == GraphType.totalPassagesPerHour)
            this.totalPassagesPerHour();
        else if(graphType == GraphType.averagePassagesPerHour)
            this.averagePassagesPerHour();
        else if(graphType == GraphType.totalPassagesPerDay)
            this.totalPassagesPerDay();
        else if(graphType == GraphType.averagePassagesPerDay)
            this.averagePassagesPerDay();*/
    }

    public void simplePassages(boolean isAverage){
        this.barChart.getData().clear();

        DateInfo startDate = null;
        DateInfo endDate = null;

        for(DateInfo dateInfo : this.dateInfos){
            if(dateInfo.getDate().equals(Date.valueOf(this.leftBar.getStartDate()))){
                startDate = dateInfo;
            }
            if(dateInfo.getDate().equals(Date.valueOf(this.leftBar.getEndDate()))){
                endDate = dateInfo;
            }
        }

        if(leftBar.getNeighborhood() == "Tous" && leftBar.getCounter() == "Tous"){
            for(Quartier quartier : this.quartiers){
                XYChart.Series<String, Number> series = new XYChart.Series<>();
                series.setName(quartier.getNomQuartier());
                if(isAverage){
                    float average = quartier.averagePassages(startDate, endDate);
                    series.getData().add(new XYChart.Data<String, Number>(quartier.getNomQuartier(), average));
                } else {
                    int total = quartier.totalPassages(startDate, endDate);
                    series.getData().add(new XYChart.Data<String, Number>(quartier.getNomQuartier(), total));
                }
                this.barChart.getData().add(series);
            }
        } else if (leftBar.getNeighborhood() != "Tous" && leftBar.getCounter() == "Tous"){
            Quartier selectedQuartier = null;
            for(Quartier quartier : this.quartiers){
                if(quartier.getNomQuartier().equals(leftBar.getNeighborhood())){
                    selectedQuartier = quartier;
                    break;
                }
            }
            if(selectedQuartier != null) {
                for(Compteur compteur : selectedQuartier.getLesCompteurs()){
                    String counter = compteur.getNomCompteur() + compteur.getSens() + " " + compteur.getIdCompteur();
                    XYChart.Series<String, Number> series = new XYChart.Series<>();
                    series.setName(compteur.getNomCompteur() + compteur.getSens());
                    if(isAverage){
                        float average = compteur.averagePassages(startDate, endDate);
                        series.getData().add(new XYChart.Data<String, Number>(counter, average));
                    } else {
                        int total = compteur.totalPassages(startDate, endDate);
                        series.getData().add(new XYChart.Data<String, Number>(counter, total));
                    }
                    this.barChart.getData().add(series);
                }
            }
        } else if (leftBar.getCounter() != "Tous"){
            for(Compteur compteur : this.compteurs){
                String counter = compteur.getNomCompteur() + compteur.getSens() + " " + compteur.getIdCompteur();
                if(counter.equals(leftBar.getCounter())){
                    XYChart.Series<String, Number> series = new XYChart.Series<>();
                    series.setName(compteur.getNomCompteur() + compteur.getSens());
                    if(isAverage){
                        float average = compteur.averagePassages(startDate, endDate);
                        series.getData().add(new XYChart.Data<String, Number>(counter, average));
                    } else {
                        int total = compteur.totalPassages(startDate, endDate);
                        series.getData().add(new XYChart.Data<String, Number>(counter, total));
                    }
                    this.barChart.getData().add(series);
                }
            }
        }
    }
}

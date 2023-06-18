import javafx.scene.layout.VBox;
import modele.entities.*;

import java.lang.reflect.Array;
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
        this.update();
    }

    public void update(){
        this.getChildren().clear();
        this.barChart = new BarChart<String, Number>(new CategoryAxis(), new NumberAxis());
        this.barChart.setTitle("Graphique");
        this.barChart.setLegendVisible(false);
        this.barChart.setMinWidth(800);
        this.barChart.setMinHeight(576);
        this.barChart.getData().add(new XYChart.Series<String, Number>());
        this.getChildren().add(this.barChart);
        this.totalPassages();
    }

    public void totalPassages(){
        this.barChart.getData().clear();
        this.barChart.getData().add(new XYChart.Series<String, Number>());
        this.barChart.getData().get(0).setName("Total passages");
        int total = 0;
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
                total += quartier.totalPassages(startDate, endDate);
            }
        } else if (leftBar.getNeighborhood() != "Tous" && leftBar.getCounter() == "Tous"){
            for(Quartier quartier : this.quartiers){
                if(quartier.getNomQuartier().equals(leftBar.getNeighborhood())){
                    total += quartier.totalPassages(startDate, endDate);
                }
            }
        } else if (leftBar.getCounter() != "Tous"){
            for(Compteur compteur : this.compteurs){
                if(compteur.getNomCompteur().equals(leftBar.getCounter())){
                    total += compteur.totalPassages(startDate, endDate);
                }
            }
        }

        this.barChart.getData().get(0).getData().add(new XYChart.Data<String, Number>("Total passages", total));
    }
}

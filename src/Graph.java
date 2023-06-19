import javafx.scene.layout.VBox;
import modele.entities.*;

import java.sql.Date;
import java.util.ArrayList;

import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart.Data;
import javafx.scene.chart.XYChart.Series;

public class Graph extends VBox{
    private LeftBar leftBar;
    private BarChart<String, Number> barChart;
    private ArrayList<Quartier> quartiers;
    private ArrayList<Compteur> compteurs;
    private ArrayList<DateInfo> dateInfos;
    
    /**
     * Constructor of the Graph class
     * @param leftBar the left bar of the application
     * @param quartiers the list of all the neighborhoods
     * @param compteurs the list of all the counters
     * @param dateInfos the list of all the date infos
     * @throws IllegalArgumentException if one of the arguments is null
     */
    public Graph(LeftBar leftBar, ArrayList<Quartier> quartiers, ArrayList<Compteur> compteurs, ArrayList<DateInfo> dateInfos) throws IllegalArgumentException {
        if(leftBar == null || quartiers == null || compteurs == null || dateInfos == null){
            throw new IllegalArgumentException("Null argument");
        }

        this.quartiers = quartiers;
        this.compteurs = compteurs;
        this.dateInfos = dateInfos;
        this.leftBar = leftBar;
        this.update(GraphType.totalPassages);
    }

    /**
     * Update the graph
     * @param graphType the type of the graph
     * @throws IllegalArgumentException if the argument is null
     */
    public void update(GraphType graphType) throws IllegalArgumentException{
        if(graphType == null){
            throw new IllegalArgumentException("Null argument");
        }

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

        if(graphType == GraphType.totalPassages){
            this.simplePassages(false, startDate, endDate);
        } else if (graphType == GraphType.averagePassages){
            this.simplePassages(true, startDate, endDate);
        } else if(graphType == GraphType.totalPassagesPerHour){
            this.passagesPerHour(false, startDate, endDate);
        } else if(graphType == GraphType.averagePassagesPerHour){
            this.passagesPerHour(true, startDate, endDate);
        } else if(graphType == GraphType.totalPassagesPerDay){
            this.passagesPerDay(false, startDate, endDate);
        } else if(graphType == GraphType.averagePassagesPerDay){
            this.passagesPerDay(true, startDate, endDate);
        }
    }

    /**
     * Setup the chart
     * @param title the title of the chart
     * @param xAxisLabel the label of the x axis
     * @param yAxisLabel the label of the y axis
     * @throws IllegalArgumentException if one of the arguments is null
     */
    public void setupChart(String title, String xAxisLabel, String yAxisLabel) throws IllegalArgumentException{
        if(title == null || xAxisLabel == null || yAxisLabel == null){
            throw new IllegalArgumentException("Null argument");
        }

        this.getChildren().clear();
        this.barChart = new BarChart<>(new CategoryAxis(), new NumberAxis());
        this.barChart.setTitle(title);
        this.barChart.getXAxis().setLabel(xAxisLabel);
        this.barChart.getYAxis().setLabel(yAxisLabel);
        this.barChart.setLegendVisible(false);
        this.barChart.setMinWidth(800);
        this.barChart.setMinHeight(576);
        this.getChildren().add(this.barChart);
        this.barChart.getData().clear();
    }

    /**
     * Get the selected neighborhood
     * @return the selected neighborhood
     */
    public Quartier getQuartier(){
        Quartier selectedQuartier = null;
        for(Quartier quartier : this.quartiers){
            if(quartier.getNomQuartier().equals(leftBar.getNeighborhood())){
                selectedQuartier = quartier;
            }
        }
        return selectedQuartier;
    }

    /**
     * Get the selected counter
     * @return the selected counter
     * @throws IllegalArgumentException if the selected neighborhood is null
     */
    public void addData(String name, float value) throws IllegalArgumentException{
        if(name == null){
            throw new IllegalArgumentException("Null argument");
        }

        Series<String, Number> series = new Series<>();
        series.setName(name);
        series.getData().add(new Data<String, Number>(name, value));
        this.barChart.getData().add(series);
    }

    /**
     * Display the total or average passages
     * @param isAverage true if we want the average, false if we want the total
     * @param startDate the start date
     * @param endDate the end date
     * @throws IllegalArgumentException if one of the arguments is null
     */
    public void simplePassages(boolean isAverage, DateInfo startDate, DateInfo endDate) throws IllegalArgumentException{
        if(startDate == null || endDate == null){
            throw new IllegalArgumentException("Null argument");
        }

        // Tous les quartiers, tous les compteurs
        if(leftBar.getNeighborhood().equals("Tous") && leftBar.getCounter().equals("Tous")){
            this.setupChart("Passages totaux", "Quartiers", "Passages");
            for(Quartier quartier : this.quartiers){
                if(isAverage){
                    float average = quartier.averagePassages(startDate, endDate);
                    this.addData(quartier.getNomQuartier(), average);
                } else {
                    int total = quartier.totalPassages(startDate, endDate);
                    this.addData(quartier.getNomQuartier(), total);
                }
            }
        } 
        
        // Quartier sélectionné, tous les compteurs du quartier
        if (!leftBar.getNeighborhood().equals("Tous") && leftBar.getCounter().equals("Tous")){
            this.setupChart("Passages totaux", "Compteurs", "Passages");
            Quartier selectedQuartier = this.getQuartier();
            for(Compteur compteur : selectedQuartier.getLesCompteurs()){
                String counter = compteur.getNomCompteur() + compteur.getSens() + " " + compteur.getIdCompteur();
                if(isAverage){
                    float average = compteur.averagePassages(startDate, endDate);
                    this.addData(counter, average);
                } else {
                    int total = compteur.totalPassages(startDate, endDate);
                    this.addData(counter, total);
                }
            }
        } 
        
        // compteur sélectionné
        if (!leftBar.getCounter().equals("Tous")){
            this.setupChart("Passages totaux", "Compteur", "Passages");
            for(Compteur compteur : this.compteurs){
                String counter = compteur.getNomCompteur() + compteur.getSens() + " " + compteur.getIdCompteur();
                if(counter.equals(leftBar.getCounter())){
                    if(isAverage){
                        float average = compteur.averagePassages(startDate, endDate);
                        this.addData(counter, average);
                    } else {
                        int total = compteur.totalPassages(startDate, endDate);
                        this.addData(counter, total);
                    }
                }
            }
        }
    }

    /**
     * Display the total or average passages per hour
     * @param isAverage true if we want the average, false if we want the total
     * @param startDate the start date
     * @param endDate the end date
     * @throws IllegalArgumentException if one of the arguments is null
     */
    public void passagesPerHour(boolean isAverage, DateInfo startDate, DateInfo endDate) throws IllegalArgumentException{
        if(startDate == null || endDate == null){
            throw new IllegalArgumentException("Null argument");
        }

        float[] totalPerHour = new float[24];
        this.setupChart("Passages par heure", "Heures", "Passages");

        // Tous les quartiers, tous les compteurs
        if(leftBar.getNeighborhood().equals("Tous") && leftBar.getCounter().equals("Tous")){
            for(Quartier quartier : this.quartiers){
                if(isAverage){
                    float[] average = quartier.averagePassagesPerHour(startDate, endDate);
                    for(int i = 0; i < 24; i++){
                        totalPerHour[i] += average[i];
                    }
                } else {
                    int[] total = quartier.totalPassagesPerHour(startDate, endDate);
                    for(int i = 0; i < 24; i++){
                        totalPerHour[i] += total[i];
                    }
                }
            }
        } 
        
        // Quartier sélectionné, tous les compteurs du quartier
        if(!leftBar.getNeighborhood().equals("Tous") && leftBar.getCounter().equals("Tous")){
            Quartier selectedQuartier = this.getQuartier();
            for(Compteur compteur : selectedQuartier.getLesCompteurs()){
                if(isAverage){
                    float[] average = compteur.averagePassagesPerHour(startDate, endDate);
                    for(int i = 0; i < 24; i++){
                        totalPerHour[i] += average[i];
                    }
                } else {
                    int[] total = compteur.totalPassagesPerHour(startDate, endDate);
                    for(int i = 0; i < 24; i++){
                        totalPerHour[i] += total[i];
                    }
                }
            }
        } 
        
        // compteur sélectionné
        if (!leftBar.getCounter().equals("Tous")){
            for(Compteur compteur : this.compteurs){
                String counter = compteur.getNomCompteur() + compteur.getSens() + " " + compteur.getIdCompteur();
                if(counter.equals(leftBar.getCounter())){
                    if(isAverage){
                        float[] average = compteur.averagePassagesPerHour(startDate, endDate);
                        for(int i = 0; i < 24; i++){
                            totalPerHour[i] = average[i];
                        }
                    } else {
                        int[] total = compteur.totalPassagesPerHour(startDate, endDate);
                        for(int i = 0; i < 24; i++){
                            totalPerHour[i] = total[i];
                        }
                    }
                }
            }
        }

        for(int i = 0; i < 24; i++){
            this.addData(Integer.toString(i), totalPerHour[i]);
        }
    }

    /**
     * Display the total or average passages per day
     * @param isAverage true if we want the average, false if we want the total
     * @param startDate the start date
     * @param endDate the end date
     * @throws IllegalArgumentException if one of the arguments is null
     */
    public void passagesPerDay(boolean isAverage, DateInfo startDate, DateInfo endDate) throws IllegalArgumentException{
        if(startDate == null || endDate == null){
            throw new IllegalArgumentException("Null argument");
        }

        float[] totalPerDay = new float[7];
        this.setupChart("Passages par jour", "Jours", "Passages");

        // Tous les quartiers, tous les compteurs
        if(leftBar.getNeighborhood().equals("Tous") && leftBar.getCounter().equals("Tous")){
            for(Quartier quartier : this.quartiers){
                if(isAverage){
                    float[] average = quartier.averagePassagesPerDay(startDate, endDate);
                    for(int i = 0; i < 7; i++){
                        totalPerDay[i] += average[i];
                    }
                } else {
                    int[] total = quartier.totalPassagesPerDay(startDate, endDate);
                    for(int i = 0; i < 7; i++){
                        totalPerDay[i] += total[i];
                    }
                }
            }
        } 
        
        // Quartier sélectionné, tous les compteurs du quartier
        if(!leftBar.getNeighborhood().equals("Tous") && leftBar.getCounter().equals("Tous")){
            Quartier selectedQuartier = getQuartier();
            for(Compteur compteur : selectedQuartier.getLesCompteurs()){
                if(isAverage){
                    float[] average = compteur.averagePassagesPerDay(startDate, endDate);
                    for(int i = 0; i < 7; i++){
                        totalPerDay[i] += average[i];
                    }
                } else {
                    int[] total = compteur.totalPassagesPerDay(startDate, endDate);
                    for(int i = 0; i < 7; i++){
                        totalPerDay[i] += total[i];
                    }
                }
            }
        } 
        
        // compteur sélectionné
        if(!leftBar.getCounter().equals("Tous")){
            for(Compteur compteur : this.compteurs){
                String counter = compteur.getNomCompteur() + compteur.getSens() + " " + compteur.getIdCompteur();
                if(counter.equals(leftBar.getCounter())){
                    if(isAverage){
                        float[] average = compteur.averagePassagesPerDay(startDate, endDate);
                        for(int i = 0; i < 7; i++){
                            totalPerDay[i] += average[i];
                        }
                    } else {
                        int[] total = compteur.totalPassagesPerDay(startDate, endDate);
                        for(int i = 0; i < 7; i++){
                            totalPerDay[i] += total[i];
                        }
                    }
                }
            }
        }

        for(int i = 0; i < 7; i++){
            this.addData(Integer.toString(i), totalPerDay[i]);
        }
    }
}

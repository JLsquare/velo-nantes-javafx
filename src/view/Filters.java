package view;
import java.time.LocalDate;
import java.util.ArrayList;

import controller.DateListener;
import controller.StringListener;
import controller.ToggleListener;
import javafx.geometry.Insets;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import modele.entities.Compteur;
import modele.entities.Quartier;

public class Filters extends VBox implements IFilters{
        private GridPane topFiltersGrid;
    private GridPane bottomFiltersGrid;

    private Label startDateLabel;
    private Label counterLabel;
    private Label neighborhoodLabel;
    private Label endDateLabel;

    private DatePicker startDatePicker;
    private DatePicker endDatePicker;
    private ComboBox<String> neighborhoodField;
    private ComboBox<String> counterField;

    private ToggleGroup group;

    private Label totalPassagesLabel;
    private Label totalPassagesPerDayLabel;
    private Label averagePassagesPerHourLabel;
    private Label totalPassagesPerHourLabel;
    private Label averagePassagesLabel;
    private Label averagePassagesPerDayLabel;

    private RadioButton totalPassages;
    private RadioButton averagePassages;
    private RadioButton totalPassagesPerHour;
    private RadioButton averagePassagesPerHour;
    private RadioButton totalPassagesPerDay;
    private RadioButton averagePassagesPerDay;

    private ArrayList<Quartier> quartiers;
    private ArrayList<Compteur> compteurs;

    private StringListener stringListener;
    private DateListener dateListener;
    private ToggleListener toggleListener;

    private Graph graph;

    public Filters(Graph graph, ArrayList<Quartier> quartiers, ArrayList<Compteur> compteurs) throws IllegalArgumentException{
        if(graph == null || quartiers == null || compteurs == null){
            throw new IllegalArgumentException("Arguments cannot be null");
        }

        this.graph = graph;
        this.quartiers = quartiers;
        this.compteurs = compteurs;

        this.initializeComponents();
        this.initializeListeners();

        this.updateGraph();
    }

    public void initializeComponents(){
        this.topFiltersGrid = new GridPane();
        this.topFiltersGrid.setHgap(10);
        this.topFiltersGrid.setVgap(10);
        this.topFiltersGrid.setPadding(new Insets(0, 10, 0, 10));

        this.bottomFiltersGrid = new GridPane();
        this.bottomFiltersGrid.setHgap(10);
        this.bottomFiltersGrid.setVgap(10);
        this.bottomFiltersGrid.setPadding(new Insets(0, 10, 0, 10));

        this.startDateLabel = new Label("Date début:");
        this.startDatePicker = new DatePicker();
        LocalDate startDate = LocalDate.of(2020, 1, 1);
        this.startDatePicker.setValue(startDate);

        this.endDateLabel = new Label("Date fin:");
        this.endDatePicker = new DatePicker();
        LocalDate endDate = LocalDate.of(2023, 01, 24);
        this.endDatePicker.setValue(endDate);

        this.neighborhoodLabel = new Label("Quartier:");
        this.neighborhoodField = new ComboBox<>();
        this.neighborhoodField.getItems().add("Tous");
        for(Quartier quartier : this.quartiers) {
            this.neighborhoodField.getItems().add(quartier.getNomQuartier() + " " + quartier.getIdQuartier());
        }
        this.neighborhoodField.setValue("Tous");

        this.counterLabel = new Label("Compteur:");
        this.counterField = new ComboBox<>();
        this.updateCompteurs();

        this.group = new ToggleGroup();

        this.totalPassagesLabel = new Label("Total passages:");
        this.totalPassages = new RadioButton("totalPassages");
        this.totalPassages.setToggleGroup(this.group);
        
        this.averagePassagesLabel = new Label("Moyenne passages:");
        this.averagePassages = new RadioButton("averagePassages");
        this.averagePassages.setToggleGroup(this.group);
        
        this.totalPassagesPerHourLabel = new Label("Total passages par heure:");
        this.totalPassagesPerHour = new RadioButton("totalPassagesPerHour");
        this.totalPassagesPerHour.setToggleGroup(this.group);
        
        this.averagePassagesPerHourLabel = new Label("Moyenne passages par heure:");
        this.averagePassagesPerHour = new RadioButton("averagePassagesPerHour");
        this.averagePassagesPerHour.setToggleGroup(this.group);
        
        this.totalPassagesPerDayLabel = new Label("Total passage par jour:");
        this.totalPassagesPerDay = new RadioButton("totalPassagesPerDay");
        this.totalPassagesPerDay.setToggleGroup(this.group);
        
        this.averagePassagesPerDayLabel = new Label("Moyenne passages par jour:");
        this.averagePassagesPerDay = new RadioButton("averagePassagesPerDay");
        this.averagePassagesPerDay.setToggleGroup(this.group);

        this.group.selectToggle(this.totalPassages);

        this.topFiltersGrid.add(this.startDateLabel, 0, 0);
        this.topFiltersGrid.add(this.startDatePicker, 1, 0);
        this.topFiltersGrid.add(this.endDateLabel, 0, 1);
        this.topFiltersGrid.add(this.endDatePicker, 1, 1);
        this.topFiltersGrid.add(this.neighborhoodLabel, 0, 2);
        this.topFiltersGrid.add(this.neighborhoodField, 1, 2);
        this.topFiltersGrid.add(this.counterLabel, 0, 3);
        this.topFiltersGrid.add(this.counterField, 1, 3);
        this.bottomFiltersGrid.add(this.totalPassagesLabel, 0, 4);
        this.bottomFiltersGrid.add(this.totalPassages, 1, 4);
        this.bottomFiltersGrid.add(this.averagePassagesLabel, 0, 5);
        this.bottomFiltersGrid.add(this.averagePassages, 1, 5);
        this.bottomFiltersGrid.add(this.totalPassagesPerHourLabel, 0, 6);
        this.bottomFiltersGrid.add(this.totalPassagesPerHour, 1, 6);
        this.bottomFiltersGrid.add(this.averagePassagesPerHourLabel, 0, 7);
        this.bottomFiltersGrid.add(this.averagePassagesPerHour, 1, 7);
        this.bottomFiltersGrid.add(this.totalPassagesPerDayLabel, 0, 8);
        this.bottomFiltersGrid.add(this.totalPassagesPerDay, 1, 8);
        this.bottomFiltersGrid.add(this.averagePassagesPerDayLabel, 0, 9);
        this.bottomFiltersGrid.add(this.averagePassagesPerDay, 1, 9);

        this.getChildren().add(this.topFiltersGrid);
        this.getChildren().add(this.bottomFiltersGrid);
    }

    public void initializeListeners(){
        this.stringListener = new StringListener(this);
        this.dateListener = new DateListener(this);
        this.toggleListener = new ToggleListener(this);

        this.startDatePicker.valueProperty().addListener(this.dateListener);
        this.endDatePicker.valueProperty().addListener(this.dateListener);
        this.neighborhoodField.valueProperty().addListener(this.stringListener);
        this.counterField.valueProperty().addListener(this.stringListener);
        this.group.selectedToggleProperty().addListener(this.toggleListener);
    }

    public void updateCompteurs(){
        System.out.println("updateCompteurs");
        this.counterField.getItems().clear();
        this.counterField.getItems().add("Tous");
        for(Compteur compteur : this.compteurs) {
            if(compteur.getLeQuartier().getNomQuartier().equals(this.neighborhoodField.getValue()) || this.neighborhoodField.getValue().equals("Tous")) {
                String counter = compteur.getNomCompteur() + compteur.getSens() + " " + compteur.getIdCompteur();
                this.counterField.getItems().add(counter);
            }
        }
        this.counterField.setValue("Tous");
    }

    public void updateGraph() {
        this.graph.update(this.getType(), this.getNeighborhood(), this.getCounter(), this.getStartDate(), this.getEndDate());
    }

    public LocalDate getStartDate() {
        return this.startDatePicker.getValue();
    }

    public LocalDate getEndDate() {
        return this.endDatePicker.getValue();
    }

    public String getNeighborhood() {
        return this.neighborhoodField.getValue();
    }

    public ComboBox<String> getNeighborhoodField() {
        return this.neighborhoodField;
    }

    public DatePicker getStartDatePicker() {
        return this.startDatePicker;
    }

    public String getCounter() {
        return this.counterField.getValue();
    }

    public String getType() {
        return ((RadioButton) this.group.getSelectedToggle()).getText();
    }
}

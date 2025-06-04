package com.example;

import javafx.fxml.FXML;

import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.StringConverter;

import java.sql.Timestamp;
import java.util.List;

public class MainController {
    @FXML
    private TableView<Donnee> tableView;

    @FXML
    private TableColumn<Donnee, String> dateCol;

    @FXML
    private TableColumn<Donnee, Integer> valeurCol;

    @FXML
    private TableColumn<Donnee, String> uniteCol;

    @FXML
    private LineChart<Number, Number> lineChartFrequence;
    @FXML
    private NumberAxis xAxisFrequence;
    @FXML
    private NumberAxis yAxisFrequence;

    @FXML
    private LineChart<Number, Number> lineChartTension;
    @FXML
    private NumberAxis xAxisTension;
    @FXML
    private NumberAxis yAxisTension;

    @FXML
    private LineChart<Number, Number> lineChartPuissance;
    @FXML
    private NumberAxis xAxisPuissance;
    @FXML
    private NumberAxis yAxisPuissance;

    @FXML
    private LineChart<Number, Number> lineChartIntensite;
    @FXML
    private NumberAxis xAxisIntensite;
    @FXML
    private NumberAxis yAxisIntensite;

    private void setGraphique(NumberAxis xAxis, NumberAxis yAxis, LineChart<Number, Number> lineChart,
                              String unit, long min, long max, long tick) {

        XYChart.Series<Number, Number> series = new XYChart.Series<>();

        List<Donnee> donnees = DonneeDAO.getAllDonneesByUnit(unit);

        long start = 0;
        long end = 0;

        for (Donnee donne: donnees) {
            Timestamp timestamp = Timestamp.valueOf(donne.getDate());
            long cur = timestamp.toInstant().toEpochMilli();
            if (start == 0){
                start = cur;
            }
            end = cur;
            series.getData().add(new XYChart.Data<>(cur / 60000, donne.getValeur()));
        }

        lineChart.getData().add(series);


        // l'echelle des X
        xAxis.setAutoRanging(false);
        xAxis.setLowerBound(start / 60000);
        xAxis.setUpperBound(end / 60000);
        xAxis.setTickUnit(60000);
        xAxis.setLabel("Temps (s)");

        xAxis.setTickLabelFormatter(new StringConverter<Number>() {
            @Override
            public String toString(Number object) {
                return "";
            }

            @Override
            public Number fromString(String string) {
                    return 0;
            }
        });

        // l'échelle des Y
        yAxis.setAutoRanging(false);
        yAxis.setLowerBound(min);
        yAxis.setUpperBound(max);
        yAxis.setTickUnit(tick);
        yAxis.setLabel("Valeur (" + unit + ")");

    }

    @FXML
    public void initialize() {

        // définition des 4 graphiques pour la fréquence, la tension, la puissance et l'intensité
        // on utilise la methode setGraphique définie plus haut pour cela
        setGraphique(xAxisFrequence, yAxisFrequence, lineChartFrequence, "HZ", 0, 100, 10);
        setGraphique(xAxisTension, yAxisTension, lineChartTension, "V", 0, 250, 10);
        setGraphique(xAxisPuissance, yAxisPuissance, lineChartPuissance, "KW", 0, 20, 1);
        setGraphique(xAxisIntensite, yAxisIntensite, lineChartIntensite, "A", 0, 100, 10);

        // définition du tableau présentant l'ensemble des mesures effectuées
        dateCol.setCellValueFactory(new PropertyValueFactory<>("date"));
        valeurCol.setCellValueFactory(new PropertyValueFactory<>("valeur"));
        uniteCol.setCellValueFactory(new PropertyValueFactory<>("unite"));
        tableView.getItems().addAll(DonneeDAO.getAllDonnees());
    }
}
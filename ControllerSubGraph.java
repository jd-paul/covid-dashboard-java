import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.Pane;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.Button;
import java.net.URL;
import java.util.ResourceBundle;
import java.io.IOException;
import javafx.scene.control.DatePicker;
import java.time.LocalDate;
import java.util.NoSuchElementException;
import javafx.scene.control.Label;
import java.util.Observable;
import java.util.Observer;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane; // Assuming rightPane is of type AnchorPane
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.time.format.DateTimeFormatter;
import javafx.scene.control.Alert;
import javafx.scene.chart.*;


public class ControllerSubGraph
{

    // Singleton pattern
    private static ControllerSubGraph instance;
    
    // Texts
    @FXML
    private LineChart<Number, Number> lineChart;

    /**
     * Constructor
     */
    public ControllerSubGraph(){}

    // Singleton getInstance() method
    public static ControllerSubGraph getInstance() {
        if (instance == null) {
            instance = new ControllerSubGraph();
        }
        return instance;
    }
    
     
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Create X and Y axes
        NumberAxis xAxis = new NumberAxis();
        NumberAxis yAxis = new NumberAxis();

        // Set labels for axes
        xAxis.setLabel("X Axis Label");
        yAxis.setLabel("Y Axis Label");

        // Create the line chart with the axes
        lineChart = new LineChart<>(xAxis, yAxis);
    }
    
    public void populateLineChart() {
        Graphs graphs = Graphs.getInstance();
        graphs.deathTollParse();
        ArrayList<DataPoint> dataPoints = graphs.getDataPoints();

        System.out.println("Number of data points: " + dataPoints.size()); // Debug statement

        XYChart.Series<Number, Number> series = new XYChart.Series<>();
        series.setName("Deaths");
    
        for (DataPoint dataPoint : dataPoints) {
            System.out.println("Adding data point: " + dataPoint.getDate() + ", " + dataPoint.getDeaths()); // Debug statement
            series.getData().add(new XYChart.Data<>(dataPoint.getDate().toEpochDay(), dataPoint.getDeaths()));
        }
    
        System.out.println("Number of points in series: " + series.getData().size()); // Debug statement
    
        //lineChart.getData().add(series);
    }
    // Method to populate the line chart with data points 
    /**
    public void populateLineChart() {
        // Parse data points
        Graphs graphs = Graphs.getInstance();
        graphs.deathTollParse();
        ArrayList<DataPoint> dataPoints = graphs.getDataPoints();

        // Create series
        XYChart.Series<Number, Number> series = new XYChart.Series<>();
        series.setName("Deaths");

        // Add data points to series
        for (DataPoint dataPoint : dataPoints) {
            series.getData().add(new XYChart.Data<>(dataPoint.getDate().toEpochDay(), dataPoint.getDeaths()));
        }
        
        lineChart.getData().add(series);
    }
    */
   
   
   
}

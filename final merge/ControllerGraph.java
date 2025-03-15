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
import javafx.scene.chart.*; 
import javafx.scene.chart.NumberAxis;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane; // Assuming rightPane is of type AnchorPane
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.time.format.DateTimeFormatter;
import javafx.scene.control.Alert;

/**
 * The ControllerGraph class controls the graphical representation of data in a line chart.
 * It manages updating the line chart with new data points based on changes in the underlying data.
 * 
 * @author John Paul San Diego
 * @author Jia Cheng Lim
 * @author Argya Pramusakti
 * @author Ahmed Almuallem
 */
public class ControllerGraph {    
    @FXML
    private LineChart<String, Integer> lineChart;
    
    /**
     * Updates the current panel by populating the line chart with data points.
     * This method is accessed by ControllerMain to update the graphical representation.
     */
    public void updateCurrentPanel() {populateLineChart();}
    
    /**
     * Populates the line chart with data points.
     * Data points are obtained from the underlying data source and added to the chart.
     */
    public void populateLineChart() {
        // Generate ArrayList of needed points to plot.
        Graphs graphs = Graphs.getInstance();
        graphs.deathTollParse();
        ArrayList<Graphs.StatisticsDeath> StatisticsDeaths = graphs.deathTollAggregated();

        // Initialize line chart.
        XYChart.Series<String, Integer> series = new XYChart.Series<>();
        series.setName(""); //Text at the bottom of the panel.
        
        
        // Plots points to chart
        if(!StatisticsDeaths.isEmpty()){
            for (Graphs.StatisticsDeath StatisticsDeath : StatisticsDeaths) {
                series.getData().add(new XYChart.Data<String, Integer>(StatisticsDeath.getDate().toString(), StatisticsDeath.getDeaths()));
            }
        }        
        lineChart.getData().add(series);
        
        // Label Axes
        lineChart.getXAxis().setLabel("Date");
        lineChart.getYAxis().setLabel("Deaths");
        lineChart.setLegendVisible(false);
    }
}

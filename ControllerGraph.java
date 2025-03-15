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

public class ControllerGraph {
    // Singleton pattern
    private static ControllerGraph instance;
    private int graphIdx;
    
    @FXML
    private AnchorPane graphMiniPanel;
    
    // Declare other controllers
    ControllerSubGraph controllerSubGraph;
    
    // Declare the statistics class generator
    Statistics statistics;
    
    // Singleton getInstance() method
    public static ControllerGraph getInstance() {
        if (instance == null) {
            instance = new ControllerGraph();
        }
        return instance;
    }
    
    /**
     * This update method will accessed by ControllerMain
     */
    public void updateCurrentPanel() {panelCycler(0);}
    
    /**
     * This sets the chosen pane and makes it load the chosen fxmlFile
     */
    private <T> T loadPanel(String fxmlFile, Pane pane) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
            Node panel = loader.load();
            pane.getChildren().setAll(panel);
            return loader.getController();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
    
    private void panelCycler(int counter) {
        if(counter == 0){
            handlePanelOne(null);
        }
        else if(counter==1){
            handlePanelTwo(null);
        }
        else if(counter==2){
            handlePanelThree(null);
        }
        else if(counter==3){
            handlePanelThree(null);
        }
    }

    @FXML
    private void incrementPanel(ActionEvent event) {
        graphIdx += 1;
        if(graphIdx>3){
            graphIdx = 0;
        }

        panelCycler(graphIdx);
    }

    @FXML
    private void decrementPanel(ActionEvent event) {
        graphIdx -= 1;
        if(graphIdx<0){
            graphIdx = 3;
        }

        panelCycler(graphIdx);
    }

    @FXML
    private void handlePanelOne(ActionEvent event) {     
        //statistics = statistics.getInstance();
        controllerSubGraph.getInstance();
        controllerSubGraph = (ControllerSubGraph) loadPanel("Graph1.fxml", graphMiniPanel);
        controllerSubGraph.populateLineChart();
        System.out.println("graphdebug");
        //controllerSubGraph.updateStatistic("(Page 1) Total deaths in this period: " + statistics.totalDeath());
    }

    @FXML
    private void handlePanelTwo(ActionEvent event) {
        //statistics = statistics.getInstance();
        
        //controllerSubGraph = (ControllerSubGraph) loadPanel("StatisticsTwo.fxml", graphMiniPanel);
        //controllerSubGraph.updateStatistic("(Page 2) Average cases per day in this period: " + statistics.averageCases());
    }

    @FXML
    private void handlePanelThree(ActionEvent event) {
        //statistics = statistics.getInstance();
        
        //controllerSubGraph = (ControllerSubGraph) loadPanel("StatisticsThree.fxml", graphMiniPanel);
        //controllerSunGraph.updateStatistic("(Page 3) Average mobility: " + statistics.averageTransit());
    }
    
    
    }

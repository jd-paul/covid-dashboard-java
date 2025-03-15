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

public class ControllerStatistic {
    // Singleton pattern
    private static ControllerStatistic instance;

    // Declare other controllers
    ControllerSubStat controllerSubStat;
    
    // Declare the models (Source of data)
    ModelMain modelMain;
    
    // Declare the statistics class generator
    Statistics statistics;
    
    // Panels
    @FXML
    private Pane statisticsMiniPanel;

    // Constructor to set the ModelMain instance
    public ControllerStatistic() {}

    // Singleton getInstance() method
    public static ControllerStatistic getInstance() {
        if (instance == null) {
            instance = new ControllerStatistic();
        }
        return instance;
    }

    /**
     * This update method will accessed by ControllerMain
     */
    public void updateCurrentPanel() {
        this.modelMain = modelMain.getInstance();
        panelCycler(modelMain.getStatIndex());
    }
    
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
        if(counter == 0){handlePanelOne(null);}
        else if(counter==1){handlePanelTwo(null);}
        else if(counter==2){handlePanelThree(null);}
    }

    @FXML
    private void incrementPanel(ActionEvent event) {
        modelMain.increaseStatIndex();
        if(modelMain.getStatIndex()>2){
            modelMain.resetStatIndex();
        }
        panelCycler(modelMain.getStatIndex());
    }

    @FXML
    private void decrementPanel(ActionEvent event) {
        modelMain.decreaseStatIndex();
        if(modelMain.getStatIndex()<0){
            modelMain.setStatIndex(2);
        }
        panelCycler(modelMain.getStatIndex());
    }

    @FXML
    private void handlePanelOne(ActionEvent event) {     
        statistics = statistics.getInstance();
        
        controllerSubStat = (ControllerSubStat) loadPanel("StatisticsOne.fxml", statisticsMiniPanel);
        controllerSubStat.updateStatistic("(Page 1) Total deaths in this period: " + statistics.totalDeath());
    }

    @FXML
    private void handlePanelTwo(ActionEvent event) {
        statistics = statistics.getInstance();
        
        controllerSubStat = (ControllerSubStat) loadPanel("StatisticsTwo.fxml", statisticsMiniPanel);
        controllerSubStat.updateStatistic("(Page 2) Average cases per day in this period: " + statistics.averageCases());
    }

    @FXML
    private void handlePanelThree(ActionEvent event) {
        statistics = statistics.getInstance();
        
        controllerSubStat = (ControllerSubStat) loadPanel("StatisticsThree.fxml", statisticsMiniPanel);
        controllerSubStat.updateStatistic("(Page 3) Average mobility: " + statistics.averageTransit());
    }
}


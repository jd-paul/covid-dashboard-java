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

/**
 * The ControllerStatistic class controls the display and cycling of statistical panels.
 * It manages the loading of different statistical panels based on user actions.
 * 
 * @author John Paul San Diego
 * @author Jia Cheng Lim
 * @author Argya Pramusakti
 * @author Ahmed Almuallem
 */
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

     /**
     * Constructor for objects of class ControllerStatistic
     */
    public ControllerStatistic() {}

    /**
     *  Singleton getInstance() method  
     *  @return The singleton instance of ModelMain
     */
    public static ControllerStatistic getInstance() {
        if (instance == null) {
            instance = new ControllerStatistic();
        }
        return instance;
    }

    /**
     * Updates the current panel based on the current statistical index.
     * This method is accessed by ControllerMain to update the displayed panel.
     */
    public void updateCurrentPanel() {
        this.modelMain = modelMain.getInstance();
        panelCycler(modelMain.getStatIndex());
    }
    
    /**
     * Loads the specified FXML file into the provided pane.
     * 
     * @param fxmlFile The name of the FXML file to load.
     * @param pane The pane where the FXML file will be loaded.
     * @return The controller associated with the loaded FXML file.
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
    
    /**
     * Handles cycling through statistical panels based on the provided counter value.
     * 
     * @param counter The counter value determining which panel to display.
     */
    private void panelCycler(int counter) {
        if(counter == 0){handlePanelOne(null);}
        else if(counter==1){handlePanelTwo(null);}
        else if(counter==2){handlePanelThree(null);}
    }
    
    /**
     * Handles the action event when the increment panel button is clicked.
     * It increases the statistical index and updates the displayed panel accordingly.
     * 
     * @param event The action event triggered by clicking the increment panel button.
     */
    @FXML
    private void incrementPanel(ActionEvent event) {
        modelMain.increaseStatIndex();
        if(modelMain.getStatIndex()>2){
            modelMain.resetStatIndex();
        }
        panelCycler(modelMain.getStatIndex());
    }
    
    /**
     * Handles the action event when the decrement panel button is clicked.
     * It decreases the statistical index and updates the displayed panel accordingly.
     * 
     * @param event The action event triggered by clicking the decrement panel button.
     */
    @FXML
    private void decrementPanel(ActionEvent event) {
        modelMain.decreaseStatIndex();
        if(modelMain.getStatIndex()<0){
            modelMain.setStatIndex(2);
        }
        panelCycler(modelMain.getStatIndex());
    }
    
     /**
     * Handles the action event to display the first statistical panel.
     * It loads the corresponding FXML file and updates the statistics displayed.
     * 
     * @param event The action event triggered by clicking the panel one button.
     */
    @FXML
    private void handlePanelOne(ActionEvent event) {     
        statistics = statistics.getInstance();
        
        controllerSubStat = (ControllerSubStat) loadPanel("StatisticsOne.fxml", statisticsMiniPanel);
        controllerSubStat.updateStatistic("(Page 1) Total deaths in this period: " + statistics.totalDeath());
    }
    
    /**
     * Handles the action event to display the second statistical panel.
     * It loads the corresponding FXML file and updates the statistics displayed.
     * 
     * @param event The action event triggered by clicking the panel two button.
     */
    @FXML
    private void handlePanelTwo(ActionEvent event) {
        statistics = statistics.getInstance();
        
        controllerSubStat = (ControllerSubStat) loadPanel("StatisticsTwo.fxml", statisticsMiniPanel);
        controllerSubStat.updateStatistic("(Page 2) Average cases per day in this period: " + statistics.averageCases());
    }
    
    /**
     * Handles the action event to display the third statistical panel.
     * It loads the corresponding FXML file and updates the statistics displayed.
     * 
     * @param event The action event triggered by clicking the panel three button.
     */
    @FXML
    private void handlePanelThree(ActionEvent event) {
        statistics = statistics.getInstance();
        
        controllerSubStat = (ControllerSubStat) loadPanel("StatisticsThree.fxml", statisticsMiniPanel);
        controllerSubStat.updateStatistic("(Page 3) Average mobility: " + statistics.averageTransit());
    }
}


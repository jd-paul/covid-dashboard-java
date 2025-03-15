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
import java.util.Observable;
import java.util.Observer;
import javafx.scene.control.Label;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane; // Assuming rightPane is of type AnchorPane
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.time.format.DateTimeFormatter;
import javafx.scene.control.Alert;
import javafx.application.Platform;

/**
 * This class is the main ControllerMain for the whole program. It will deal with the
 * loading of panels (Switching from panel to panel), and saving of the user's
 * inputted dates.
 * 
 * @author John Paul San Diego
 * @author Jia Cheng Lim
 * @author Argya Pramusakti
 * @author Ahmed Almuallem
 */
public class ControllerMain implements Observer {
    // Singleton pattern
    private static ControllerMain instance;

    // Declare other controllers
    ControllerAbout controllerAbout;
    ControllerStatistic controllerStatistic;
    ControllerMap controllerMap;
    ControllerGraph controllerGraph;

    // Declare the models (Source of data)
    ModelMain modelMain;

    // Panels
    @FXML
    private Pane rootLayout;
    @FXML
    private Pane rightPane;
    @FXML
    private Pane statsPane;
    @FXML
    private Pane leftPane;

    // Declaration of leftside buttons
    @FXML
    Button btnAbout;
    @FXML
    Button btnMap;
    @FXML
    Button btnStat;
    @FXML
    Button btnFour;

    // Date picker
    @FXML
    private DatePicker startDatePicker;
    @FXML
    private DatePicker endDatePicker;

    private LocalDate startDate;
    private LocalDate endDate;

    // Buttons (Fix this)
    @FXML
    private Button aboutButton;

    // Date variables
    LocalDate minDate;
    LocalDate maxDate;

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private boolean dateSet = false;
    
    // Text variables
    @FXML
    Label lblWarning;

    public ControllerMain() {}

    /**
     * Initializes the ControllerMain.
     * 
     * This method sets up the ControllerMain by initializing required components
     * such as models, controllers, and GUI elements. It also adds this class as
     * an observer to the main model.
     */
    public void initialize() {
        modelMain = modelMain.getInstance();
        modelMain.addObserver(this);
        
        LocalDate minDate = modelMain.findMinDate();
        LocalDate maxDate = modelMain.findMaxDate();
        
        handlePanelOne(null);
    }
    
    /**
     * {@inheritDoc}
     * Updates the panels based on changes in the main model.
     * 
     * @param o   The observable object.
     * @param arg The argument passed to the notifyObservers method.
     */
    @Override
    public void update(Observable o, Object arg) {
        updatePanelOne();
        updatePanelTwo();
        updatePanelThree();
        updatePanelFour();
    }
    
     /**
     * Updates Panel One (About).
     * This method updates Panel One (About) with the latest data from the main model.
     */
    public void updatePanelOne() {
        this.modelMain = modelMain.getInstance();

        if (modelMain.getIndex() == 0) {
            controllerAbout.updateDate(modelMain.getStartDate(), modelMain.getEndDate());
        }
    }
    
    /**
     * Updates Panel Two (Map).
     * This method updates Panel Two (Map) with the latest data from the main model.
     */
    public void updatePanelTwo() {
        // StatisticsBorough.getInstance();
        this.modelMain = modelMain.getInstance();
        
        if (modelMain.getIndex() == 1) {
            System.out.println("Panel two will now update colors");
            
            controllerMap = (ControllerMap) loadPanel("ViewMap.fxml",rightPane);
            controllerMap.updateColors();
        }
    }
    
    /**
     * Updates Panel Three (Statistic).
     * This method updates Panel Three (Statistic) with the latest data from the main model.
     */
    public void updatePanelThree() {
        this.modelMain = modelMain.getInstance();

        if (modelMain.getIndex() == 2) {
            controllerStatistic = (ControllerStatistic) loadPanel("ViewStatistics.fxml",rightPane);
            controllerStatistic.updateCurrentPanel();
        }
    }

        /**
     * Updates Panel Four (Graph).
     * This method updates Panel Two (Graph) with the latest data from the main model.
     */
    public void updatePanelFour() {
        this.modelMain = modelMain.getInstance();
        
        if (modelMain.getIndex() == 3) {
            controllerGraph = (ControllerGraph) loadPanel("Graph.fxml",rightPane);
            controllerGraph.updateCurrentPanel();
        }
    }
    
    /**
     * Handles cycling through panels based on the given counter value.
     * 
     * @param counter The counter value indicating which panel to switch to.
     */
    private void panelCycler(int counter) {
        if(counter == 0) {handlePanelOne(null);}
        else if(counter==1) {handlePanelTwo(null);}
        else if(counter==2) {handlePanelThree(null);}
        else if(counter==3) {handlePanelFour(null);}
    }
    
    /**
     * Handles incrementing the panel index and updating the displayed panel.
     * 
     * @param event The ActionEvent associated with the button click.
     */
    @FXML
    private void incrementPanel(ActionEvent event) {
        if(dateSet){
            modelMain.increaseIndex();
            if(modelMain.getIndex()>3){
                modelMain.resetIndex();
            }
            panelCycler(modelMain.getIndex());
        }
    }
    
    /**
     * Handles decrementing the panel index and updating the displayed panel.
     * 
     * @param event The ActionEvent associated with the button click.
     */
    @FXML
    private void decrementPanel(ActionEvent event) {
        if(dateSet){
            modelMain.decreaseIndex();
            if(modelMain.getIndex()<0){
                modelMain.setIndex(3);
            }
            panelCycler(modelMain.getIndex());
        }
    }
    
    /**
     * Handles switching to Panel One (About).
     * 
     * @param event The ActionEvent associated with the button click.
     */
    @FXML
    private void handlePanelOne(ActionEvent event) {
        System.out.println("Loading ViewAbout.fxml");
        controllerAbout = (ControllerAbout) loadPanel("ViewAbout.fxml",rightPane);
        this.modelMain = modelMain.getInstance();
        modelMain.setIndex(0);

        updatePanelOne();

        // Set button styles
        btnAbout.getStyleClass().clear();
        btnAbout.getStyleClass().add("main__selected-transparent-button");
        btnMap.getStyleClass().clear();
        btnMap.getStyleClass().add("main__unselected-transparent-button");
        btnStat.getStyleClass().clear();
        btnStat.getStyleClass().add("main__unselected-transparent-button");
        btnFour.getStyleClass().clear();
        btnFour.getStyleClass().add("main__unselected-transparent-button");
    }
    
    /**
     * Handles switching to Panel Two (Map).
     * 
     * @param event The ActionEvent associated with the button click.
     */
    @FXML
    private void handlePanelTwo(ActionEvent event) {
        if(dateSet){
            System.out.println("Loading ViewMap.fxml");
            loadPanel("ViewMap.fxml",rightPane);

            controllerMap = (ControllerMap) loadPanel("ViewMap.fxml",rightPane);

            this.modelMain = modelMain.getInstance();
            modelMain.setIndex(1);
            updatePanelTwo();
            btnAbout.getStyleClass().clear();
            btnAbout.getStyleClass().add("main__unselected-transparent-button");
            btnMap.getStyleClass().clear();
            btnMap.getStyleClass().add("main__selected-transparent-button");
            btnStat.getStyleClass().clear();
            btnStat.getStyleClass().add("main__unselected-transparent-button");
            btnFour.getStyleClass().clear();
            btnFour.getStyleClass().add("main__unselected-transparent-button");
        }
    }
    
    /**
     * Handles switching to Panel Three (Statistics).
     * 
     * @param event The ActionEvent associated with the button click.
     */
    @FXML
    private void handlePanelThree(ActionEvent event) {
        if(dateSet){
            System.out.println("Loading ViewStatistics.fxml");
            loadPanel("ViewStatistics.fxml",rightPane);

            controllerStatistic = (ControllerStatistic) loadPanel("ViewStatistics.fxml",rightPane);

            this.modelMain = modelMain.getInstance();
            modelMain.setIndex(2);

            updatePanelThree();

            btnAbout.getStyleClass().clear();
            btnAbout.getStyleClass().add("main__unselected-transparent-button");
            btnMap.getStyleClass().clear();
            btnMap.getStyleClass().add("main__unselected-transparent-button");
            btnStat.getStyleClass().clear();
            btnStat.getStyleClass().add("main__selected-transparent-button");
            btnFour.getStyleClass().clear();
            btnFour.getStyleClass().add("main__unselected-transparent-button");
        }
    }
    
    /**
     * Handles switching to Panel Four (Graph).
     * 
     * @param event The ActionEvent associated with the button click.
     */
    @FXML
    private void handlePanelFour(ActionEvent event) {
        if(dateSet){
            System.out.println("Loading ViewStatistics.fxml");
            loadPanel("ViewStatistics.fxml",rightPane);

            controllerGraph = (ControllerGraph) loadPanel("Graph.fxml",rightPane);

            this.modelMain = modelMain.getInstance();
            modelMain.setIndex(3);

            updatePanelFour();

            btnAbout.getStyleClass().clear();
            btnAbout.getStyleClass().add("main__unselected-transparent-button");
            btnMap.getStyleClass().clear();
            btnMap.getStyleClass().add("main__unselected-transparent-button");
            btnStat.getStyleClass().clear();
            btnStat.getStyleClass().add("main__unselected-transparent-button");
            btnFour.getStyleClass().clear();
            btnFour.getStyleClass().add("main__selected-transparent-button");
        }
    }
    
    /**
    * Handles changes in the start date selection.
    */
    @FXML
    private void handleStartDatePicker() {
        // Create instance of model for holding data
        this.modelMain = modelMain.getInstance();
        minDate = modelMain.findMinDate();
        maxDate = modelMain.findMaxDate();

        startDate = startDatePicker.getValue();

        if(this.startDate != null){
            modelMain.setStartDate(startDate);
            System.out.println("start date set to: " + startDate);
        }

        checkValidDate(minDate,maxDate);
    }
    
    /**
     * Handles changes in the end date selection.
     */
    @FXML
    private void handleEndDatePicker() {
        // Create instance of model for holding data
        this.modelMain = modelMain.getInstance();
        minDate = modelMain.findMinDate();
        maxDate = modelMain.findMaxDate();

        endDate = endDatePicker.getValue();

        if(this.endDate != null){
            modelMain.setEndDate(endDate);
            System.out.println("end date set to: " + endDate);
        }

        checkValidDate(minDate,maxDate);
    }
    
     /**
     * This method checks whether the selected start and end dates form a valid
     * date range and updates the GUI accordingly. If the selected date range is
     * invalid, a warning message is displayed to the user.
     * 
     * @param minDate The minimum allowable date.
     * @param maxDate The maximum allowable date.
     */
    private void checkValidDate(LocalDate minDate,LocalDate maxDate){
        if (this.startDate != null && this.endDate != null) {
            if( (endDate.isAfter(minDate)&& endDate.isBefore(maxDate))  ||  (endDate.isEqual(minDate) || endDate.isEqual(maxDate)) ){
                if( (startDate.isAfter(minDate)&& startDate.isBefore(maxDate))  ||  (startDate.isEqual(minDate) || startDate.isEqual(maxDate)) ){
                    if(endDate.isAfter(startDate)){
                        dateSet = true;
                        lblWarning.setVisible(false);
                    }
                    else{
                        dateSet = false;
                        lblWarning.setVisible(true);
                        showAlert("Invalid Date","Please select a valid date range from "+(minDate)+" to "+(maxDate));
                    }
                }
                else{
                    dateSet = false;
                    lblWarning.setVisible(true);
                    showAlert("Invalid Date","Please select a valid date range from "+(minDate)+" to "+(maxDate));
                }
            }
            else{
                dateSet = false;
                lblWarning.setVisible(true);
                showAlert("Invalid Date","Please select a valid date range from "+(minDate)+" to "+(maxDate));
            }
        }
        else {
            dateSet = false;
            lblWarning.setVisible(true);
        }
    }
    
    /**
     * Displays a warning alert with the specified title and content.
     *
     * @param title   The title of the alert.
     * @param content The content message of the alert.
     */
    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
    
     /**
     * Displays an information alert about the application.
     */
    private void showHelp() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("About the Application");
        alert.setHeaderText("COVID Statistics in London");
        alert.setContentText("This application is a GUI application designed to showcase covid statistics in London. Developed by Paul San Diego, Jia Cheng Lim, Argya Pramusakti, and Ahmed Almuallem.");
        alert.showAndWait();
    }

    /**
     * This sets the chosen pane and makes it load the chosen fxmlFile
     * 
     * @param fxmlFile The FXML file to load.
     * @param pane     The pane where the FXML file will be loaded.
     * @return         The controller associated with the loaded FXML file.
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
     * Gets the start date selected by the user.
     * 
     * @return The start date selected by the user.
     */
    public LocalDate getStartDate() {
        return startDate;
    }
    
    /**
     * Gets the end date selected by the user.
     * 
     * @return The end date selected by the user.
     */
    public LocalDate getEndDate() {
        return endDate;
    }
    /**
     * Displays an information alert about the application.
     * 
     * @param event The associated ActionEvent.
     */
    @FXML
    void showHelp(ActionEvent event) {
        showHelp();
    }
}


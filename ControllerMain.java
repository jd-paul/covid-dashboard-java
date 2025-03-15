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
 * This class is the main ControllerMain for the whole program. It will deal with the
 * loading of panels (Switching from panel to panel), and saving of the user's
 * inputted dates.
 */
public class ControllerMain implements Observer {
    // Singleton pattern
    private static ControllerMain instance;

    // Declare other controllers
    ControllerAbout controllerAbout;
    ControllerStatistic controllerStatistic;
    ControllerMap controllerMap;

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

    public ControllerMain() { initialize(); }

    public void initialize() {
        modelMain = modelMain.getInstance();
        LocalDate minDate = modelMain.findMinDate();
        LocalDate maxDate = modelMain.findMaxDate();
    }

    @Override
    public void update(Observable o, Object arg) {
        updatePanelOne();
        updatePanelThree();
    }

    public void updatePanelOne() {
        this.modelMain = modelMain.getInstance();

        if (modelMain.getIndex() == 0) {
            controllerAbout.updateDate(modelMain.getStartDate(), modelMain.getEndDate());
        }
    }

    public void updatePanelThree() {
        this.modelMain = modelMain.getInstance();

        if (modelMain.getIndex() == 2) {
            controllerStatistic = (ControllerStatistic) loadPanel("ViewStatistics.fxml",rightPane);
            controllerStatistic.updateCurrentPanel();
        }
    }

    private void panelCycler(int counter) {
        if(counter == 0) {handlePanelOne(null);}
        else if(counter==1) {handlePanelTwo(null);}
        else if(counter==2) {handlePanelThree(null);}
    }

    @FXML
    private void incrementPanel(ActionEvent event) {
        if(dateSet){
            modelMain.increaseIndex();
            if(modelMain.getIndex()>2){
                modelMain.resetIndex();
            }
            panelCycler(modelMain.getIndex());
        }
    }

    @FXML
    private void decrementPanel(ActionEvent event) {
        if(dateSet){
            modelMain.decreaseIndex();
            if(modelMain.getIndex()<0){
                modelMain.setIndex(2);
            }
            panelCycler(modelMain.getIndex());
        }
    }

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

    @FXML
    private void handlePanelTwo(ActionEvent event) {
        if(dateSet){
            System.out.println("Loading ViewMap.fxml");
            loadPanel("ViewMap.fxml",rightPane);

            controllerMap = (ControllerMap) loadPanel("ViewMap.fxml",rightPane);

            this.modelMain = modelMain.getInstance();
            modelMain.setIndex(1);

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

    private void checkValidDate(LocalDate minDate,LocalDate maxDate){
        if (this.startDate != null && this.endDate != null) {
            if( (endDate.isAfter(minDate)&& endDate.isBefore(maxDate))  ||  (endDate.isEqual(minDate) || endDate.isEqual(maxDate)) ){
                if( (startDate.isAfter(minDate)&& startDate.isBefore(maxDate))  ||  (startDate.isEqual(minDate) || startDate.isEqual(maxDate)) ){
                    if(endDate.isAfter(startDate)){
                        dateSet = true;
                    }
                    else{showAlert("Invalid date","please select a valid date range from"+(""+minDate)+" to "+(""+maxDate));
                        dateSet = false;
                    }
                }
                else{showAlert("Invalid date","please select a valid date range from"+(""+minDate)+" to "+(""+maxDate));
                    dateSet = false;
                }
            }
            else{showAlert("Invalid date","please select a valid date range from"+(""+minDate)+" to "+(""+maxDate));
                dateSet = false;
            }
        }
        else {
            dateSet = false;
            System.out.println("Please select both start and end dates.");
        }
    }

    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
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

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void doThis() {
        loadPanel("ViewAbout.fxml",rightPane);
    }

    @FXML
    void exitProgram(ActionEvent event) {

    }
}


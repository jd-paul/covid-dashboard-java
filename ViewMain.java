import java.net.URL;

import javafx.fxml.FXMLLoader;
import javafx.fxml.FXML;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.*;
import javafx.scene.layout.*;

/**
 * Main class for initializing and launching the application.
 * 
 * @author John Paul San Diego
 * @author Jia Cheng Lim
 * @author Argya Pramusakti
 * @author Ahmed Almuallem
 */
public class ViewMain extends Application {
    // Singleton pattern
    private static ViewMain instance;

    // Declare controllers and models
    private ModelMain modelMain;
    private Statistics statistics;
    
    
    private ControllerMain controllerMain;
    // private ControllerAbout controllerAbout;
    // private ControllerStatistic controllerStatistic;
    // private ControllerSubStat controllerSubStat;
    
    @Override
    public void start(Stage stage) throws Exception {
        // Load FXML file for the main view
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ViewMain.fxml"));
        
        Pane root = loader.load();
        
        // Initialize controllers
        controllerMain = (ControllerMain) loader.getController();
        
        // controllerAbout = (ControllerAbout) loader.getController();
        // controllerStatistic = (ControllerStatistic) loader.getController();
        // controllerSubStat = (ControllerSubStat) loader.getController();
        
        // Create scene and set root
        Scene scene = new Scene(root);

        // Set stage properties
        stage.setTitle("Covid Data");
        stage.setScene(scene);
        stage.show();

        // Set stage window size
        stage.setMinWidth(800);
        stage.setMinHeight(600);

        // Initialize observers
        initialize();
    }

    public static void main(String[] args) {
        launch(args);
    }

    /**
     * This method initializes all required methods.
     */
    private void initialize() {
        // Initialize model and controllers
        modelMain = ModelMain.getInstance();
        statistics = Statistics.getInstance();
        
        // Add observers
        modelMain.addObserver(controllerMain);

        // Clear terminal for debugging purposes
        // System. out. print('\u000C');
    }
}
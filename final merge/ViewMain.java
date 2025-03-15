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
    
    private ControllerMain controllerMain;
    
    /**
     * Override the start() method of the Application class to configure the primary stage (main window) of the application.
     * This method is called automatically when the application is launched.
     * @param stage The primary stage for the application.
     * @throws Exception If an error occurs during initialization.
     */
    @Override
    public void start(Stage stage) throws Exception {
        // Load FXML file for the main view
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ViewMain.fxml"));
        
        Pane root = loader.load();
        
        // Initialize controller
        controllerMain = (ControllerMain) loader.getController();
        
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
    
    /**
     * The main entry point for the JavaFX application.
     * @param args The command-line arguments passed to the application.
     */
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * This method sets up the controller, initializes observers, and performs any other necessary setup tasks.
     */
    private void initialize() {
        controllerMain.initialize();

        // Clear terminal for debugging purposes
        // System. out. print('\u000C');
    }
}
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
 * The ControllerAbout class controls the display of date information in the About panel.
 * It updates the labels showing the start and end dates based on changes in the underlying data.
 * 
 * @author John Paul San Diego
 * @author Jia Cheng Lim
 * @author Argya Pramusakti
 * @author Ahmed Almuallem
 */
public class ControllerAbout {
    // Singleton pattern
    private static ControllerAbout instance;
    
    // Texts
    @FXML
    private Label lblStartDate;
    @FXML
    private Label lblEndDate;
    
    /**
     * Constructor for objects of class ControllerAbout
     */
    public ControllerAbout() {}

    /**
     * Singleton getInstance() method
     * @return The singleton instance of ModelMain
     */ 
    public static ControllerAbout getInstance() {
        if (instance == null) {
            instance = new ControllerAbout();
        }
        return instance;
    }

    /**
     * Updates the labels displaying start and end dates.
     * This method is accessed by ControllerMain to update the date information.
     * 
     * @param start The start date to be displayed.
     * @param end The end date to be displayed.
     */
    public void updateDate(LocalDate start, LocalDate end) {
        if (start != null) {
            lblStartDate.setText(""+start);
        }
        else {
            lblStartDate.setText("Select valid start");
        }
        
        if (end != null) {
            lblEndDate.setText(""+end);
        }
        else {
            lblEndDate.setText("Select valid end");
        }
    }
}
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

public class ControllerAbout {
    // Singleton pattern
    private static ControllerAbout instance;
    
    // Texts
    @FXML
    private Label lblStartDate;
    @FXML
    private Label lblEndDate;

    public ControllerAbout() {}

    // Singleton getInstance() method
    public static ControllerAbout getInstance() {
        if (instance == null) {
            instance = new ControllerAbout();
        }
        return instance;
    }

    /**
     * This update method will accessed by ControllerMain
     */
    public void updateDate(LocalDate start, LocalDate end) {
        if (start != null) {
            //lblStartDate.setText("Choice 1");
            lblStartDate.setText(""+start);
        }
        else {
            lblStartDate.setText("Select valid start");
        }
        
        if (end != null) {
            //lblStartDate.setText("Choice 1");
             lblEndDate.setText(""+end);
        }
        else {
            lblEndDate.setText("Select valid end");
        }
    }
}
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

public class ControllerSubStat {
    // Singleton pattern
    private static ControllerSubStat instance;
    
    // Texts
    @FXML
    private Label lblStatText;

    /**
     * Constructor
     */
    public ControllerSubStat() {}

    // Singleton getInstance() method
    public static ControllerSubStat getInstance() {
        if (instance == null) {
            instance = new ControllerSubStat();
        }
        return instance;
    }

    /**
     * This update method will update the relevant texts
     */
    public void updateStatistic(String text) {
        lblStatText.setText("Statistic: " + text);
    }
}
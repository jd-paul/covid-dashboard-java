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
import java.util.HashMap;
import java.util.Map;
import javafx.scene.Parent;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Background;
import javafx.scene.control.Button;
import java.util.Arrays;
import java.util.List;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane; // Assuming rightPane is of type AnchorPane
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.time.format.DateTimeFormatter;
import javafx.scene.control.Alert;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javax.swing.table.TableRowSorter;
import javafx.scene.paint.Color;
/**
 * the controllerMap control the display of map and execute pop out window for each borough.
 * @author John Paul San Diego
 * @author Jia Cheng Lim
 * @author Argya Pramusakti
 * @author Ahmed Almuallem
 */
public class ControllerMap
{
    // Singleton pattern
    private static ControllerMap instance;

    // Data
    private static Object[][] data;
    // StatisticsBorough otherObject = new StatisticsBorough();

    // Declare other controllers
    StatisticsBorough statisticsBorough;

    // Declare the models (Source of data)
    ModelMain modelMain;

    // Declare the statistics class
    Statistics statistics;

    // Name of css files and ranges for cases
    private static final int[] caseRanges = {180, 140, 100, 60, 20};
    private static final String[] cssClasses = {"map__polygon-5", "map__polygon-4", "map__polygon-3", "map__polygon-2", "map__polygon-1"};

    // Declaration of all 33 buttons
    @FXML
    Button btnENFI;
    @FXML
    Button btnBARN;
    @FXML
    Button btnHRGY;
    @FXML
    Button btnWALT;
    @FXML
    Button btnHRRW;
    @FXML
    Button btnBREN;
    @FXML
    Button btnCAMD;
    @FXML
    Button btnISLI;
    @FXML
    Button btnHACK;
    @FXML
    Button btnREDB;
    @FXML
    Button btnHAVE;
    @FXML
    Button btnHILL;
    @FXML
    Button btnEALI;
    @FXML
    Button btnKENS;
    @FXML
    Button btnWSTM;
    @FXML
    Button btnTOWH;
    @FXML
    Button btnNEWH;
    @FXML
    Button btnBARK;
    @FXML
    Button btnHOUN;
    @FXML
    Button btnHAMM;
    @FXML
    Button btnWAND;
    @FXML
    Button btnCITY;
    @FXML
    Button btnGWCH;
    @FXML
    Button btnBEXL;
    @FXML
    Button btnRICH;
    @FXML
    Button btnMERT;
    @FXML
    Button btnLAMB;
    @FXML
    Button btnSTHW;
    @FXML 
    Button btnLEWS;
    @FXML
    Button btnKING;
    @FXML
    Button btnSUTT;
    @FXML
    Button btnCROY;
    @FXML
    Button btnBROM;

    //String[] mapColumnNames = StatisticsBorough columnNames;
    String[] columnNames = {"Date", "retail Recreation GMR","grocery and pharmacy GMR","park GMR","Transit Stations GMR", "Work Places GMR",
            "Residential GMR","new COVID cases", "total COVID cases ","new covid death"};

    List<String> buttonIds = Arrays.asList("btnENFI", "btnBARN", "btnHRGY", "btnWALT", "btnHRRW", "btnBREN",
            "btnCAMD", "btnISLI", "btnHACK", "btnREDB", "btnHAVE", "btnHILL",
            "btnEALI", "btnKENS", "btnWSTM", "btnTOWH", "btnNEWH", "btnBARK",
            "btnHOUN", "btnHAMM", "btnWAND", "btnCITY", "btnGWCH", "btnBEXL",
            "btnRICH", "btnMERT", "btnLAMB", "btnSTHW", "btnLEWS", "btnKING",
            "btnSUTT", "btnCROY", "btnBROM");

    String[] boroughNames = {"Enfield", "Barnet","Haringey","Waltham Forest","Harrow", "Brent","Camden","Islington", "Hackney","Redbridge",
            "Havering", "Hillingdon", "Ealing","Kensington And Chelsea","Westminster","Tower Hamlets","Newham","Barking And Dagenham","Hounslow", 
            "Hammersmith And Fulham","Wandsworth","City Of London", "Greenwich","Bexley", "Richmond Upon Thames","Merton","Lambeth","Southwark",
            "Lewisham","Kingston Upon Thames","Sutton","Croydon","Bromley"};

    /**
     * Constructor for objects of class ControllerMap
     */
    public ControllerMap() {}

    public static ControllerMap getInstance() {
        if (instance == null) {
            instance = new ControllerMap();
        }
        return instance;
    }

    /**
     * Updates the colors of buttons based on the average number of COVID cases in each borough.
     * Uses the StatisticsBorough singleton instance to calculate average cases for each borough.
     * Button colors are determined by predefined case ranges and corresponding CSS classes.
     */
    public void updateColors() {
        statisticsBorough = StatisticsBorough.getInstance();

        Map<String, String> mapBtnBorough = new HashMap<>();
        for (int i = 0; i < buttonIds.size(); i++) {
            mapBtnBorough.put(buttonIds.get(i), boroughNames[i]);
        }

        javafx.scene.Scene scene = btnENFI.getScene();

        // Iterate over the button IDs
        for (String buttonId : buttonIds) {
            Button button = (Button) scene.lookup("#" + buttonId);
            if (button != null) {

                String boroughString = mapBtnBorough.get(buttonId);
                int cases = statisticsBorough.averageCasesBorough(boroughString);

                for (int i = 0; i < caseRanges.length; i++) {
                    if (cases > caseRanges[i]) {
                        button.getStyleClass().clear();
                        button.getStyleClass().add(cssClasses[i]);
                        break;
                    }
                }
            }
        }
    }

    @FXML
    public void handleENFIButton(ActionEvent event){
        statisticsBorough = statisticsBorough.getInstance();
        String borough = "Enfield";
        data = statisticsBorough.boroughData(borough);
        showTable(borough);
    }

    @FXML
    public void handleBARNButton(ActionEvent event){
        statisticsBorough = statisticsBorough.getInstance();
        String borough = "Barnet";
        data = statisticsBorough.boroughData(borough);
        showTable(borough);
    }

    @FXML
    public void handleHRGYButton(ActionEvent event){
        statisticsBorough = statisticsBorough.getInstance();
        String borough = "Haringey";
        data = statisticsBorough.boroughData(borough);
        showTable(borough);
    }

    @FXML
    public void handleWALTButton(ActionEvent event){
        statisticsBorough = statisticsBorough.getInstance();
        String borough = "Waltham Forest";
        data = statisticsBorough.boroughData(borough);
        showTable(borough);
    }

    @FXML
    public void handleHRRWButton(ActionEvent event){
        statisticsBorough = statisticsBorough.getInstance();
        String borough = "Harrow";
        data = statisticsBorough.boroughData(borough);
        showTable(borough);
    }

    @FXML
    public void handleBRENButton(ActionEvent event){
        statisticsBorough = statisticsBorough.getInstance();
        String borough = "Brent";
        data = statisticsBorough.boroughData(borough);
        showTable(borough);
    }

    @FXML
    public void handleCAMDButton(ActionEvent event){
        statisticsBorough = statisticsBorough.getInstance();
        String borough = "Camden";
        data = statisticsBorough.boroughData(borough);
        showTable(borough);
    }

    @FXML
    public void handleISLIButton(ActionEvent event){
        statisticsBorough = statisticsBorough.getInstance();
        String borough = "Islington";
        data = statisticsBorough.boroughData(borough);
        showTable(borough);
    }

    @FXML
    public void handleHACKButton(ActionEvent event){
        statisticsBorough = statisticsBorough.getInstance();
        String borough = "Hackney";
        data = statisticsBorough.boroughData(borough);
        showTable(borough);
    }

    @FXML
    public void handleREDBButton(ActionEvent event){
        statisticsBorough = statisticsBorough.getInstance();
        String borough = "Redbridge";
        data = statisticsBorough.boroughData(borough);
        showTable(borough);
    }

    @FXML
    public void handleHAVEButton(ActionEvent event){
        statisticsBorough = statisticsBorough.getInstance();
        String borough = "Havering";
        data = statisticsBorough.boroughData(borough);
        showTable(borough);
    }

    @FXML
    public void handleHILLButton(ActionEvent event){
        statisticsBorough = statisticsBorough.getInstance();
        String borough = "Hillingdon";
        data = statisticsBorough.boroughData(borough);
        showTable(borough);
    }

    @FXML
    public void handleEALIButton(ActionEvent event){
        statisticsBorough = statisticsBorough.getInstance();
        String borough = "Ealing";
        data = statisticsBorough.boroughData(borough);
        showTable(borough);
    }

    @FXML
    public void handleKENSButton(ActionEvent event){
        statisticsBorough = statisticsBorough.getInstance();
        String borough = "Kensington And Chelsea";
        data = statisticsBorough.boroughData(borough);
        showTable(borough);
    }

    @FXML
    public void handleWSTMButton(ActionEvent event){
        statisticsBorough = statisticsBorough.getInstance();
        String borough = "Westminster";
        data = statisticsBorough.boroughData(borough);
        showTable(borough);
    }

    @FXML
    public void handleTOWHButton(ActionEvent event){
        statisticsBorough = statisticsBorough.getInstance();
        String borough = "Tower Hamlets";
        data = statisticsBorough.boroughData(borough);
        showTable(borough);
    }

    @FXML
    public void handleNEWHButton(ActionEvent event){
        statisticsBorough = statisticsBorough.getInstance();
        String borough = "Newham";
        data = statisticsBorough.boroughData(borough);
        showTable(borough);
    }

    @FXML
    public void handleBARKButton(ActionEvent event){
        statisticsBorough = statisticsBorough.getInstance();
        String borough = "Barking And Dagenham";
        data = statisticsBorough.boroughData(borough);
        showTable(borough);
    }

    @FXML
    public void handleHOUNButton(ActionEvent event){
        statisticsBorough = statisticsBorough.getInstance();
        String borough = "Hounslow";
        data = statisticsBorough.boroughData(borough);
        showTable(borough);
    }

    @FXML
    public void handleHAMMButton(ActionEvent event){
        statisticsBorough = statisticsBorough.getInstance();
        String borough = "Hammersmith And Fulham";
        data = statisticsBorough.boroughData(borough);
        showTable(borough);
    }

    @FXML
    public void handleWANDButton(ActionEvent event){
        statisticsBorough = statisticsBorough.getInstance();
        String borough = "Wandsworth";
        data = statisticsBorough.boroughData(borough);
        showTable(borough);
    }

    @FXML
    public void handleCITYButton(ActionEvent event){
        statisticsBorough = statisticsBorough.getInstance();
        String borough = "City Of London";
        data = statisticsBorough.boroughData(borough);
        showTable(borough);
    }

    @FXML
    public void handleGWCHButton(ActionEvent event){
        statisticsBorough = statisticsBorough.getInstance();
        String borough = "Greenwich";
        data = statisticsBorough.boroughData(borough);
        showTable(borough);
    }

    @FXML
    public void handleBEXLButton(ActionEvent event){
        statisticsBorough = statisticsBorough.getInstance();
        String borough = "Bexley";
        data = statisticsBorough.boroughData(borough);
        showTable(borough);
    }

    @FXML
    public void handleRICHButton(ActionEvent event){
        statisticsBorough = statisticsBorough.getInstance();
        String borough = "Richmond Upon Thames";
        data = statisticsBorough.boroughData(borough);
        showTable(borough);
    }

    @FXML
    public void handleMERTButton(ActionEvent event){
        statisticsBorough = statisticsBorough.getInstance();
        String borough = "Merton";
        data = statisticsBorough.boroughData(borough);
        showTable(borough);
    }

    @FXML
    public void handleLAMBButton(ActionEvent event){
        statisticsBorough = statisticsBorough.getInstance();
        String borough = "Lambeth";
        data = statisticsBorough.boroughData(borough);
        showTable(borough);
    }

    @FXML
    public void handleSTHWButton(ActionEvent event){
        statisticsBorough = statisticsBorough.getInstance();
        String borough = "Southwark";
        data = statisticsBorough.boroughData(borough);
        showTable(borough);
    }

    @FXML
    public void handleLEWSButton(ActionEvent event){
        statisticsBorough = statisticsBorough.getInstance();
        String borough = "Lewisham";
        data = statisticsBorough.boroughData(borough);
        showTable(borough);
    }

    @FXML
    public void handleKINGButton(ActionEvent event){
        statisticsBorough = statisticsBorough.getInstance();
        String borough = "Kingston Upon Thames";
        data = statisticsBorough.boroughData(borough);
        showTable(borough);
    }

    @FXML
    public void handleSUTTButton(ActionEvent event){
        statisticsBorough = statisticsBorough.getInstance();
        String borough = "Sutton";
        data = statisticsBorough.boroughData(borough);
        showTable(borough);
    }

    @FXML
    public void handleCROYButton(ActionEvent event){
        statisticsBorough = statisticsBorough.getInstance();
        String borough = "Croydon";
        data = statisticsBorough.boroughData(borough);
        showTable(borough);
    }

    @FXML
    public void handleBROMButton(ActionEvent event){
        statisticsBorough = statisticsBorough.getInstance();
        String borough = "Bromley";
        data = statisticsBorough.boroughData(borough);
        showTable(borough);
    }

    /**
     * Displays a table showing COVID data for a specified name (e.g., borough).
     * 
     * @param name The name (e.g., borough name) to be displayed in the table title.
     */
    public void showTable(String name){
        DefaultTableModel model = new DefaultTableModel(data, columnNames){
                @Override
                public Class getColumnClass(int column) {
                    switch (column) {
                        case 0:
                            return String.class;
                        case 1:
                            return Integer.class;
                        case 2:
                            return Integer.class;
                        case 3:
                            return Integer.class;
                        case 4:
                            return Integer.class;
                        case 5:
                            return Integer.class;
                        case 6:
                            return Integer.class;
                        case 7:
                            return Integer.class;
                        case 8:
                            return Integer.class;    
                        case 9:
                            return Integer.class;
                        default:
                            return String.class;
                    }
                }};
        JTable table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        table.setPreferredScrollableViewportSize(new Dimension(400, 200));

        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(model);
        table.setRowSorter(sorter);
        JFrame frame = new JFrame(name + " COVID Data");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.add(scrollPane, BorderLayout.CENTER);
        frame.pack();
        frame.setVisible(true);
    }
}
 
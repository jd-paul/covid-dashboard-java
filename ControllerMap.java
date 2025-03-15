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

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javax.swing.table.TableRowSorter;
/**
 * Write a description of class ControllerMap here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class ControllerMap
{
    // Singleton pattern
    private static ControllerMap instance;

    // Data
    private static Object[][] data;
    //StatisticsBorough otherObject = new StatisticsBorough();

    // Declare other controllers
    StatisticsBorough statisticsBorough;

    // Declare the models (Source of data)
    ModelMain modelMain;

    // Declare the statistics class generator
    Statistics statistics;

    //String[] mapColumnNames = StatisticsBorough columnNames;
    String[] columnNames = {"Date", "retail Recreation GMR","grocery and pharmacy GMR","park GMR","Transit Stations GMR", "Work Places GMR","Residential GMR","new COVID cases", "total COVID cases ","new covid death"};

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

    @FXML
    public void handleENFIButton(ActionEvent event){
        statisticsBorough = statisticsBorough.getInstance();

        String borough = "Enfield";
        data = statisticsBorough.boroughData(borough);
        showTable();
    }
    
    @FXML
    public void handleBARNButton(ActionEvent event){
        statisticsBorough = statisticsBorough.getInstance();

        String borough = "Barnet";
        data = statisticsBorough.boroughData(borough);
        showTable();
    }

    @FXML
    public void handleHRGYButton(ActionEvent event){
        statisticsBorough = statisticsBorough.getInstance();

        String borough = "Haringey";
        data = statisticsBorough.boroughData(borough);
        showTable();
    }
    
    @FXML
    public void handleWALTButton(ActionEvent event){
        statisticsBorough = statisticsBorough.getInstance();

        String borough = "Waltham Forest";
        data = statisticsBorough.boroughData(borough);
        showTable();
    }
    
    
    
    @FXML
    public void handleHRRWButton(ActionEvent event){
        statisticsBorough = statisticsBorough.getInstance();

        String borough = "Harrow";
        data = statisticsBorough.boroughData(borough);
        showTable();
    }
    
    @FXML
    public void handleBRENButton(ActionEvent event){
        statisticsBorough = statisticsBorough.getInstance();

        String borough = "Brent";
        data = statisticsBorough.boroughData(borough);
        showTable();
    }
    
    @FXML
    public void handleCAMDButton(ActionEvent event){
        statisticsBorough = statisticsBorough.getInstance();

        String borough = "Camden";
        data = statisticsBorough.boroughData(borough);
        showTable();
    }
    
    @FXML
    public void handleISLIButton(ActionEvent event){
        statisticsBorough = statisticsBorough.getInstance();

        String borough = "Islington";
        data = statisticsBorough.boroughData(borough);
        showTable();
    }
    
    @FXML
    public void handleHACKButton(ActionEvent event){
        statisticsBorough = statisticsBorough.getInstance();

        String borough = "Hackney";
        data = statisticsBorough.boroughData(borough);
        showTable();
    }
    
    @FXML
    public void handleREDBButton(ActionEvent event){
        statisticsBorough = statisticsBorough.getInstance();

        String borough = "Redbridge";
        data = statisticsBorough.boroughData(borough);
        showTable();
    }
    
    @FXML
    public void handleHAVEButton(ActionEvent event){
        statisticsBorough = statisticsBorough.getInstance();

        String borough = "Havering";
        data = statisticsBorough.boroughData(borough);
        showTable();
    }
    
    public void showTable(){
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
        JFrame frame = new JFrame("JTable Example");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.add(scrollPane, BorderLayout.CENTER);
        frame.pack();
        frame.setVisible(true);
    }
}
 
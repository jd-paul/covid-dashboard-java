import java.awt.event.ActionEvent;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
/**
 * Write a description of class CopyOfControllerMap here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class CopyOfControllerMap
{
    // Singleton pattern
    private static CopyOfControllerMap instance;
    
    private static Object[][] data;
    //StatisticsBorough otherObject = new StatisticsBorough();
    
    // Declare other controllers
    StatisticsBorough statisticsBorough;
    
    // Declare the models (Source of data)
    ModelMain modelMain;
    
    // Declare the statistics class generator
    Statistics statistics;
    
    //String[] mapColumnNames = StatisticsBorough columnNames;
    String[] columnNames = {"Date", "retail Recreation GMR","park GMR","Transit GMR", "Work Places GMR","Residential GMR","new COVID cases", "total COVID cases "};
    
    
    /**
     * Constructor for objects of class CopyOfControllerMap
     */
    public CopyOfControllerMap() {}
    
    public static CopyOfControllerMap getInstance() {
        if (instance == null) {
            instance = new CopyOfControllerMap();
        }
        return instance;
    }
    
    // @FXML
    // public void handleENFIButton(ActionEvent event){
        // // System.out.println("Button worked");
        
        // // statisticsBorough = statisticsBorough.getInstance();
        
        // // String borough = "Enfield";
        // // data = statisticsBorough.boroughData(borough);
        // // showTable();
    // }
    
    @FXML
    private void sampleEvent(ActionEvent event) {
        System.out.println("Button worked");
    }
    
    
    
}
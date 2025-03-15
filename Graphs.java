import java.util.ArrayList;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.Comparator;
/**
 * Write a description of class Graphs here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Graphs
{
    // Singleton pattern
    private static Graphs instance;

    // instance variables - replace the example below with your own
    CovidDataLoader loader = new CovidDataLoader();
    ArrayList<CovidData> records = loader.load();
    // private static ControllerMain instance; // Delete soon if unnecessary

    // Declare other controllers
    ModelMain modelMain;

    private LocalDate startDate;
    private LocalDate endDate;
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    
    private ArrayList<DataPoint> dataPoints = new ArrayList<>();
    
    /**
     * Constructor
     */
    public Graphs() {}
    
    //Returns a sorted list of all needed datapoints to populate a linechart, plotting date against deaths. 
        public ArrayList<DataPoint> deathTollParse() {
        // Clear existing data points
        dataPoints.clear();
        
        // Retrieve start and end dates from ModelMain
        modelMain = ModelMain.getInstance();
        startDate = modelMain.getStartDate();
        endDate = modelMain.getEndDate();
    
        // Iterate through records and add data points within the date range
        for (CovidData record : records) {
            LocalDate date = LocalDate.parse(record.getDate(), FORMATTER);
            if (date.isEqual(startDate) || date.isEqual(endDate) || (date.isAfter(startDate) && date.isBefore(endDate))) {
                    DataPoint dp = new DataPoint(date, record.getNewDeaths());
                dataPoints.add(dp);
            }
         }
        
        // Sort data points by date
        Collections.sort(dataPoints, Comparator.comparing(DataPoint::getDate));
        
        return dataPoints;
    }
    
    public ArrayList<DataPoint> getDataPoints() {
        return dataPoints;
    }    
    
    // Singleton getInstance() method
    public static Graphs getInstance() {
        if (instance == null) {
            instance = new Graphs();
        }
        return instance;
    }
}

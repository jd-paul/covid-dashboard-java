import java.util.ArrayList;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Arrays;
import javax.swing.table.DefaultTableModel;
/**
 * the StatisticsBorough calculate and give the data that ControllerMap need to make a certain condition to make the backend of the map.
 * @author John Paul San Diego
 * @author Jia Cheng Lim
 * @author Argya Pramusakti
 * @author Ahmed Almuallem
 */
public class StatisticsBorough
{
    // Singleton pattern
    private static StatisticsBorough instance;

    CovidDataLoader loader = new CovidDataLoader();
    ArrayList<CovidData> records = loader.load();
    ModelMain modelMain;

    private LocalDate startDate;
    private LocalDate endDate;
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    Object[][] data ;

    // String[] columnNames = {"Date", "retail Recreation GMR","park GMR","Transit GMR", "Work Places GMR","Residential GMR","new COVID cases", "total COVID cases "};

    /**
     * Constructor
     */
    public StatisticsBorough() {}

    // Singleton getInstance() method
    public static StatisticsBorough getInstance() {
        if (instance == null) {
            instance = new StatisticsBorough();
        }
        return instance;
    }

/**
 * Calculates the average number of COVID cases for a specific borough within a given time frame.
 * 
 * @param borough The name of the borough for which to calculate the average cases.
 * @return The average number of COVID cases for the specified borough.
 */
    public int averageCasesBorough(String borough){
        this.modelMain = modelMain.getInstance();
        startDate = modelMain.getStartDate();
        endDate = modelMain.getEndDate();
        int totalCases = 0;
        int numberOfDates = 0;
        for (CovidData record : records) {
            if(borough.equals(record.getBorough())) {
                LocalDate date = LocalDate.parse(record.getDate(), FORMATTER);
                if((date.isAfter(startDate) && date.isBefore(endDate)) || (date.isEqual(startDate) || date.isEqual(endDate))){
                    totalCases = totalCases + record.getNewCases();
                    numberOfDates++;
                }
            }
        }
        int averageCases = totalCases/numberOfDates;
        return averageCases;
    }

    /**
 * Retrieves COVID data for a specific borough within a given time frame.
 * 
 * @param borough The name of the borough for which to retrieve data.
 * @return A 2D array containing COVID data for the specified borough.
 */
    public Object[][] boroughData(String borough) {
        List<Object[]> dataList = new ArrayList<>();
        this.modelMain = modelMain.getInstance();
        startDate = modelMain.getStartDate();
        endDate = modelMain.getEndDate();
        for (CovidData record : records) {
            if(borough.equals(record.getBorough())) {
                LocalDate date = LocalDate.parse(record.getDate(), FORMATTER);
                if((date.isAfter(startDate) && date.isBefore(endDate)) || (date.isEqual(startDate) || date.isEqual(endDate))){
                    record.getNewDeaths();
                    Object[] newRow = {""+date, record.getRetailRecreationGMR(),record.getGroceryPharmacyGMR(),record.getParksGMR(),
                    record.getTransitGMR(),record.getWorkplacesGMR(),record.getResidentialGMR(),record.getNewCases(),
                    record.getTotalCases(),record.getNewDeaths()};    
                    dataList.add(newRow);
                }
            }
        data = dataList.toArray(new Object[0][0]);
        }
    return data;
    }
}


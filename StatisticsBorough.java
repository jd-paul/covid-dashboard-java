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
 * Write a description of class StatisticsBorough here.
 *
 * @author (your name)
 * @version (a version number or a date) 
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
    
    //String[] columnNames = {"Date", "retail Recreation GMR","park GMR","Transit GMR", "Work Places GMR","Residential GMR","new COVID cases", "total COVID cases "};

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
                    Object[] newRow = {""+date, record.getRetailRecreationGMR(),record.getGroceryPharmacyGMR(),record.getParksGMR(),record.getTransitGMR(),record.getWorkplacesGMR(),record.getResidentialGMR(),record.getNewCases(),record.getTotalCases(),record.getNewDeaths()};
                dataList.add(newRow);
            }
        }
        data = dataList.toArray(new Object[0][0]);
    }
    return data;
    }
}


import java.util.ArrayList;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
/**
 * The Statistics class calculates various statistics based on Covid data.
 * It includes methods to calculate the total number of deaths, average cases per day,
 * average mobility, and average workplace mobility.
 * 
 * @author John Paul San Diego
 * @author Jia Cheng Lim
 * @author Argya Pramusakti
 * @author Ahmed Almuallem
 */
public class Statistics
{
    // Singleton pattern
    private static Statistics instance;

    // Data loading
    CovidDataLoader loader = new CovidDataLoader();
    ArrayList<CovidData> records = loader.load();

    // Declare other controllers
    ModelMain modelMain;

    private LocalDate startDate;
    private LocalDate endDate;
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

     /**
     * Constructor for Statistics class.
     */
    public Statistics() {}

    /**
     * Singleton getInstance() method to retrieve the singleton instance of Statistics.
     * 
     * @return The singleton instance of Statistics
     */
    public static Statistics getInstance() {
        if (instance == null) {
            instance = new Statistics();
        }
        return instance;
    }
    
     /**
     * Calculates the total number of deaths within the specified date range.
     * 
     * @return The total number of deaths
     */
    public int totalDeath() {
        this.modelMain = modelMain.getInstance();
        startDate = modelMain.getStartDate();
        endDate = modelMain.getEndDate();
        int totalDeath = 0;
        for (CovidData record : records) {
            LocalDate date = LocalDate.parse(record.getDate(), FORMATTER);
            if((date.isAfter(startDate) && date.isBefore(endDate)) || (date.isEqual(startDate) || date.isEqual(endDate))){
                totalDeath = totalDeath + record.getNewDeaths();
            }
        }
        System.out.println(""+totalDeath);
        return totalDeath;
    } 
    
     /**
     * Calculates the average number of cases per day within the specified date range.
     * 
     * @return The average number of cases per day
     */
    public int averageCases(){
        ArrayList<CovidData> records = this.records;
        
        this.modelMain = modelMain.getInstance();
        startDate = modelMain.getStartDate();
        endDate = modelMain.getEndDate();
        int totalCases = 0;
        int numberOfDates = 0;
        for (CovidData record : records) {
            LocalDate date = LocalDate.parse(record.getDate(), FORMATTER);
            if((date.isAfter(startDate) && date.isBefore(endDate)) || (date.isEqual(startDate) || date.isEqual(endDate))){
                totalCases = totalCases + record.getNewCases();
                numberOfDates++;
            }
        }
        int averageCases = totalCases/numberOfDates;
        System.out.println(""+averageCases);
        return averageCases;
    }
    
    /**
     * Calculates the average mobility within the specified date range.
     * 
     * @return The average mobility
     */
    public int averageTransit(){
        this.modelMain = modelMain.getInstance();
        startDate = modelMain.getStartDate();
        endDate = modelMain.getEndDate();
        int totalTransit = 0;
        int numberOfDates = 0;
        for (CovidData record : records) {
            LocalDate date = LocalDate.parse(record.getDate(), FORMATTER);
            if((date.isAfter(startDate) && date.isBefore(endDate)) || (date.isEqual(startDate) || date.isEqual(endDate))){
                totalTransit = totalTransit + record.getTransitGMR();
                numberOfDates++;
            }
        }
        int averageTransit = totalTransit/numberOfDates;
        System.out.println(""+averageTransit);
        return averageTransit;
    }
    
     /**
     * Calculates the average workplace mobility within the specified date range.
     * 
     * @param records The list of CovidData records
     * @return The average workplace mobility
     */
    public int averageWorkPlace(ArrayList<CovidData> records){
        this.modelMain = modelMain.getInstance();
        startDate = modelMain.getStartDate();
        endDate = modelMain.getEndDate();
        int totalWorkPlace = 0;
        int numberOfDates = 0;
        for (CovidData record : records) {
            LocalDate date = LocalDate.parse(record.getDate(), FORMATTER);
            if((date.isAfter(startDate) && date.isBefore(endDate)) || (date.isEqual(startDate) || date.isEqual(endDate))){
                totalWorkPlace = totalWorkPlace + record.getWorkplacesGMR();
                numberOfDates++;
            }
        }
        int averageWorkPlace= totalWorkPlace/numberOfDates;
        System.out.println(""+averageWorkPlace);
        return averageWorkPlace;
    }
}

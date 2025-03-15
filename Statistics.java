import java.util.ArrayList;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
/**
 * Write a description of class Statistics here.
 *
 * @author (your name)
 * @version (a version number or a date) 
 */
public class Statistics
{
    // Singleton pattern
    private static Statistics instance;

    // instance variables - replace the example below with your own
    CovidDataLoader loader = new CovidDataLoader();
    ArrayList<CovidData> records = loader.load();
    // private static ControllerMain instance; // Delete soon if unnecessary

    // Declare other controllers
    ModelMain modelMain;

    private LocalDate startDate;
    private LocalDate endDate;
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    /**
     * Constructor
     */
    public Statistics() {}

    // Singleton getInstance() method
    public static Statistics getInstance() {
        if (instance == null) {
            instance = new Statistics();
        }
        return instance;
    }

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

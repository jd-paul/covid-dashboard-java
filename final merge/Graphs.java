import java.util.ArrayList;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.Comparator;
/**
 * The Graphs class is responsible for processing Covid data and generating statistics for graphical representation.
 * It includes methods to parse Covid data, aggregate death tolls, and manage instances of statistics.
 * 
 * @author John Paul San Diego
 * @author Jia Cheng Lim
 * @author Argya Pramusakti
 * @author Ahmed Almuallem
 * 
 */
public class Graphs
{
    // Singleton pattern
    private static Graphs instance;

    // Data loading
    CovidDataLoader loader = new CovidDataLoader();
    ArrayList<CovidData> records = loader.load();

    // Declare other controllers
    ModelMain modelMain;

    private LocalDate startDate;
    private LocalDate endDate;
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    
    
     /**
     * Constructor for Graphs class.
     */
    public Graphs() {}
    
     /**
     * Parses Covid data to generate a list of death statistics.
     * Data points are added within the specified date range.
     * 
     * @return ArrayList of StatisticsDeath objects
     */
        public ArrayList<StatisticsDeath> deathTollParse() {
        // Initialize and clear data points
        
        ArrayList<StatisticsDeath> StatisticsDeaths = new ArrayList<>();
        StatisticsDeaths.clear();
        
        // Retrieve start and end dates from ModelMain
        modelMain = ModelMain.getInstance();
        startDate = modelMain.getStartDate();
        endDate = modelMain.getEndDate();
    
        // Iterate through records and add data points within the date range
        for (CovidData record : records) {
            LocalDate date = LocalDate.parse(record.getDate(), FORMATTER);
            if (date.isEqual(startDate) || date.isEqual(endDate) || (date.isAfter(startDate) && date.isBefore(endDate))) {
                StatisticsDeath dp = new StatisticsDeath(date, record.getNewDeaths());
                StatisticsDeaths.add(dp);
            }
         }
        
        // Sort data points by date
        Collections.sort(StatisticsDeaths, Comparator.comparing(StatisticsDeath::getDate));
        
        return StatisticsDeaths;
    }
    
    /**
     * Aggregates death tolls by combining data of the same date.
     * 
     * @return ArrayList of aggregated StatisticsDeath objects
     */
    public ArrayList<StatisticsDeath> deathTollAggregated() {
        ArrayList<StatisticsDeath> StatisticsDeaths = deathTollParse();
        ArrayList<StatisticsDeath> returnList = new ArrayList();
        
        //Initialize
        LocalDate referenceDate = null;
        if(!StatisticsDeaths.isEmpty()){
            referenceDate = StatisticsDeaths.get(0).getDate();
        }
        
        int deathAtDate = 0;
        
        //Loops through data of the same date, and aggregates all deaths throughout the boroughs.
        for(StatisticsDeath dp : StatisticsDeaths){
            if(!dp.getDate().isEqual(referenceDate)){
                returnList.add(new StatisticsDeath(referenceDate, deathAtDate));
                referenceDate = dp.getDate();
                deathAtDate = dp.getDeaths();
            }
            else{
                deathAtDate += dp.getDeaths();
            }
        }
        
        //Add the final entry that isn't covered by the foreach loop.
         if (!StatisticsDeaths.isEmpty()) {
            returnList.add(new StatisticsDeath(referenceDate, deathAtDate));
        }
        
        
        return returnList;  
    }
    
    
    /**
     * Singleton getInstance() method to retrieve the singleton instance of Graphs.
     * 
     * @return The singleton instance of Graphs
     */
    public static Graphs getInstance() {
        if (instance == null) {
            instance = new Graphs();
        }
        return instance;
    }
    
     /**
     * Represents a data point for death statistics, containing the date and number of deaths.
     * Implements Comparable interface to enable sorting by date.
     */
        public class StatisticsDeath implements Comparable<StatisticsDeath> {
        private LocalDate date;
        private int deaths;
        
        /**
         * Constructor for StatisticsDeath class.
         * 
         * @param date The date of the data point
         * @param deaths The number of deaths
         */
        public StatisticsDeath(LocalDate date, int deaths) {
            this.date = date;
            this.deaths = deaths;
        }
        
        /**
         * Get the date of the data point.
         * 
         * @return The date
         */
        public LocalDate getDate() {
            return date;
        }
        
        /**
         * Get the number of deaths.
         * 
         * @return The number of deaths
         */
        public int getDeaths() {
            return deaths;
        }
        
        /**
         * Compares this StatisticsDeath object with another based on date.
         * 
         * @param other The other StatisticsDeath object to compare with
         * @return A negative integer, zero, or a positive integer as this object is less than, equal to, or greater than the specified object.
         */
        @Override
        public int compareTo(StatisticsDeath other) {
            return this.date.compareTo(other.getDate());
        }
    }
}
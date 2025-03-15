import java.util.Observable;
import java.util.Observer;
import java.time.LocalDate;
import java.util.ArrayList;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Main model for holding important values such as the start and end date.
 * Acts as an Observable to notify observers of changes.
 * 
 * @author John Paul San Diego
 * @author Jia Cheng Lim
 * @author Argya Pramusakti
 * @author Ahmed Almuallem
 */

public class ModelMain extends Observable {
    // Singleton pattern
    private static ModelMain instance;

    // Important universally shared variables
    private static LocalDate startDate;
    private static LocalDate endDate;
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    
    // Index for panel cycling
    private int index = 0;
    private int statIndex = 0;
    
    // Data loading
    CovidDataLoader loader = new CovidDataLoader();
    ArrayList<CovidData> records = loader.load();
    
    /**
     * Default constructor
     */
    public ModelMain() {}
    
     /**
     * Singleton getInstance() method
     * 
     * @return The singleton instance of ModelMain
     */
    public static ModelMain getInstance() {
        if (instance == null) {
            instance = new ModelMain();
        }
        return instance;
    }
    
     /**
     * Set the start date and notify observers of the change.
     * 
     * @param newDate The new start date
     */
    public void setStartDate(LocalDate newDate) {
        this.startDate = newDate;
        setChanged();
        notifyObservers();
    }
    
     /**
     * Set the end date and notify observers of the change.
     * 
     * @param newDate The new end date
     */
    public void setEndDate(LocalDate newDate) {
        this.endDate = newDate;
        setChanged();
        notifyObservers();
    }
    
     /**
     * Get the start date.
     * 
     * @return The start date
     */
    public LocalDate getStartDate() {return startDate;}
    
    /**
     * Get the end date.
     * 
     * @return The end date
     */
    public LocalDate getEndDate() {return endDate;}
    
    /**
     * Get the current index for panel cycling.
     * 
     * @return The current index
     */
    public int getIndex() {return index;}
    
     /**
     * Set the index for panel cycling.
     * 
     * @param num The index to set
     */
    public void setIndex(int num) {index = num;}
    
    /**
     * Reset the index for panel cycling to zero.
     */
    public void resetIndex() {index = 0;}
    
    /**
     * Increase the index for panel cycling.
     */
    public void increaseIndex() {index++;}
    
    /**
     * Decrease the index for panel cycling.
     */
    public void decreaseIndex(){index--;}
    
    /**
     * Get the current index for statistic panel cycling.
     * 
     * @return The current statistic index
     */
    public int getStatIndex() {return statIndex;}
    
    /**
     * Set the index for statistic panel cycling.
     * 
     * @param num The index to set
     */
    public void setStatIndex(int num) {statIndex = num;}
    
    /**
     * Reset the index for statistic panel cycling to zero.
     */
    public void resetStatIndex() {statIndex = 0;}
    
    /**
     * Increase the index for statistic panel cycling.
     */
    public void increaseStatIndex() {statIndex++;}
    
    /**
     * Decrease the index for statistic panel cycling.
     */
    public void decreaseStatIndex(){statIndex--;}
    
    /**
     * Find the minimum date in the loaded data records.
     * 
     * @return The minimum date
     */   
    public LocalDate findMinDate() {
        LocalDate minDate = null;
        for (CovidData record : records) {
            LocalDate date = LocalDate.parse(record.getDate(), FORMATTER);
            if (minDate == null || date.isBefore(minDate)) {
                minDate = date;
            }
        }
        return minDate;
    }
    
    /**
     * Find the maximum date in the loaded data records.
     * 
     * @return The maximum date
     */
    public LocalDate findMaxDate() {
        LocalDate maxDate = null;
        for (CovidData record : records) {
            LocalDate date = LocalDate.parse(record.getDate(), FORMATTER);
            if (maxDate == null || date.isAfter(maxDate)) {
                maxDate = date;
            }
        }
        return maxDate;
    }
}

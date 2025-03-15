import java.util.Observable;
import java.util.Observer;
import java.time.LocalDate;
import java.util.ArrayList;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Main model for holding important values such as the start and end date.
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
    
    private int index = 0;
    private int statIndex = 0;
    
    CovidDataLoader loader = new CovidDataLoader();
    ArrayList<CovidData> records = loader.load();
    
    public ModelMain() {}
    public static ModelMain getInstance() {
        if (instance == null) {
            instance = new ModelMain();
        }
        return instance;
    }

    public void setStartDate(LocalDate newDate) {
        this.startDate = newDate;
        setChanged();
        notifyObservers();
    }

    public void setEndDate(LocalDate newDate) {
        this.endDate = newDate;
        setChanged();
        notifyObservers();
    }

    public LocalDate getStartDate() {return startDate;}
    public LocalDate getEndDate() {return endDate;}
    
    public int getIndex() {return index;}
    public void setIndex(int num) {index = num;}
    public void resetIndex() {index = 0;}
    public void increaseIndex() {index++;}
    public void decreaseIndex(){index--;}
    
    public int getStatIndex() {return statIndex;}
    public void setStatIndex(int num) {statIndex = num;}
    public void resetStatIndex() {statIndex = 0;}
    public void increaseStatIndex() {statIndex++;}
    public void decreaseStatIndex(){statIndex--;}
    
    // Method to load and display the selected panel
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

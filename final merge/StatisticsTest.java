import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;
import java.time.LocalDate;
import java.util.ArrayList;

public class StatisticsTest {

    private Statistics statistics;
    private ModelMain modelMain;

    @Before
    public void setUp() {
        statistics = Statistics.getInstance();
        modelMain = ModelMain.getInstance();
    }

    @Test
    public void testTotalDeath() {
        // Set up start and end dates
        modelMain.setStartDate(LocalDate.of(2023, 1, 1));
        modelMain.setEndDate(LocalDate.of(2023, 1, 10));

        // Call the method and check the result
        assertEquals(135, statistics.totalDeath());
    }

    @Test
    public void testAverageCases() {
        // Set up start and end dates
        modelMain.setStartDate(LocalDate.of(2023, 1, 1));
        modelMain.setEndDate(LocalDate.of(2023, 1, 10));

        // Call the method and check the result
        assertEquals(11, statistics.averageCases());
    }

    @Test
    public void testAverageTransit() {
        // Set up start and end dates
        modelMain.setStartDate(LocalDate.of(2023, 1, 1));
        modelMain.setEndDate(LocalDate.of(2023, 1, 10));

        // Call the method and check the result
        assertEquals(0, statistics.averageTransit());
    }

    @Test
    public void testAverageWorkPlace() {
        // Set up start and end dates
        modelMain.setStartDate(LocalDate.of(2023, 1, 1));
        modelMain.setEndDate(LocalDate.of(2023, 1, 10));

        // Create a sample records list
        ArrayList<CovidData> records = new ArrayList<>();
        records.add(new CovidData("2023-01-01", "Borough1", 0, 0, 0, 0, 10, 0, 0, 0, 0, 0));
        records.add(new CovidData("2023-01-02", "Borough1", 0, 0, 0, 0, 20, 0, 0, 0, 0, 0));

        // Call the method and check the result
        assertEquals(15, statistics.averageWorkPlace(records));
    }
}

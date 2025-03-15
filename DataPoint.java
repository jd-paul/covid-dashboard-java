import java.time.LocalDate;

public class DataPoint implements Comparable<DataPoint> {
    private LocalDate date;
    private int deaths;

    public DataPoint(LocalDate date, int deaths) {
        this.date = date;
        this.deaths = deaths;
    }

    public LocalDate getDate() {
        return date;
    }

    public int getDeaths() {
        return deaths;
    }

    @Override
    public int compareTo(DataPoint other) {
        return this.date.compareTo(other.getDate());
    }
}
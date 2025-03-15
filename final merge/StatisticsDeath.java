import java.time.LocalDate;

public class StatisticsDeath implements Comparable<StatisticsDeath> {
    private LocalDate date;
    private int deaths;

    public StatisticsDeath(LocalDate date, int deaths) {
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
    public int compareTo(StatisticsDeath other) {
        return this.date.compareTo(other.getDate());
    }
    }
package au.com.seek.aips.challenge.domain;

import java.time.LocalDate;

public class DayRecord {

    private LocalDate date;

    private Integer numberOfCars;

    public DayRecord(LocalDate date, Integer numberOfCars) {
        this.date = date;
        this.numberOfCars = numberOfCars;
    }

    public LocalDate getDate() {
        return date;
    }

    public Integer getNumberOfCars() {
        return numberOfCars;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("DayRecord{");
        sb.append("date=").append(date);
        sb.append(", numberOfCars=").append(numberOfCars);
        sb.append('}');
        return sb.toString();
    }
}

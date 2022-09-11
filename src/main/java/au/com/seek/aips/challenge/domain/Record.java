package au.com.seek.aips.challenge.domain;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

public class Record {
    private LocalDate date;
    private LocalDateTime dateTime;
    private Integer numberOfCars;

    public Record(LocalDate date, LocalDateTime dateTime, Integer numberOfCars) {
        this.date = date;
        this.dateTime = dateTime;
        this.numberOfCars = numberOfCars;
    }

    public LocalDate getDate() {
        return date;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public Integer getNumberOfCars() {
        return numberOfCars;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Record record = (Record) o;
        return date.equals(record.date) && dateTime.equals(record.dateTime) && numberOfCars.equals(record.numberOfCars);
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Record{");
        sb.append("date=").append(date);
        sb.append(", dateTime=").append(dateTime);
        sb.append(", numberOfCars=").append(numberOfCars);
        sb.append('}');
        return sb.toString();
    }
}

package au.com.seek.aips.challenge;

import au.com.seek.aips.challenge.domain.DayRecord;
import au.com.seek.aips.challenge.domain.Record;
import io.vavr.collection.Vector;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class TestUtils {

    public static Vector<Record> DEFAULT_RECORDS;

    public static Vector<DayRecord> DEFAULT_DAY_RECORDS;

    static {
        DEFAULT_RECORDS = defaultRecords();
        DEFAULT_DAY_RECORDS = defaultDayRecords();
    }

    private static Vector<Record> defaultRecords() {
        return Vector.of(
                new Record(LocalDate.parse("2021-12-01"), LocalDateTime.parse("2021-12-01T05:00:00"), 5),
                new Record(LocalDate.parse("2021-12-01"), LocalDateTime.parse("2021-12-01T05:30:00"), 12),
                new Record(LocalDate.parse("2021-12-01"), LocalDateTime.parse("2021-12-01T06:00:00"), 14),
                new Record(LocalDate.parse("2021-12-01"), LocalDateTime.parse("2021-12-01T06:30:00"), 15),
                new Record(LocalDate.parse("2021-12-01"), LocalDateTime.parse("2021-12-01T07:00:00"), 25),
                new Record(LocalDate.parse("2021-12-01"), LocalDateTime.parse("2021-12-01T07:30:00"), 46),
                new Record(LocalDate.parse("2021-12-01"), LocalDateTime.parse("2021-12-01T08:00:00"), 42),
                new Record(LocalDate.parse("2021-12-01"), LocalDateTime.parse("2021-12-01T15:00:00"), 9),
                new Record(LocalDate.parse("2021-12-01"), LocalDateTime.parse("2021-12-01T15:30:00"), 11),
                new Record(LocalDate.parse("2021-12-01"), LocalDateTime.parse("2021-12-01T23:30:00"), 0),

                new Record(LocalDate.parse("2021-12-05"), LocalDateTime.parse("2021-12-05T09:30:00"), 18),
                new Record(LocalDate.parse("2021-12-05"), LocalDateTime.parse("2021-12-05T10:30:00"), 15),
                new Record(LocalDate.parse("2021-12-05"), LocalDateTime.parse("2021-12-05T11:30:00"), 7),
                new Record(LocalDate.parse("2021-12-05"), LocalDateTime.parse("2021-12-05T12:30:00"), 6),
                new Record(LocalDate.parse("2021-12-05"), LocalDateTime.parse("2021-12-05T13:30:00"), 9),
                new Record(LocalDate.parse("2021-12-05"), LocalDateTime.parse("2021-12-05T14:30:00"), 11),
                new Record(LocalDate.parse("2021-12-05"), LocalDateTime.parse("2021-12-05T15:30:00"), 15),

                new Record(LocalDate.parse("2021-12-08"), LocalDateTime.parse("2021-12-08T18:00:00"), 33),
                new Record(LocalDate.parse("2021-12-08"), LocalDateTime.parse("2021-12-08T19:00:00"), 28),
                new Record(LocalDate.parse("2021-12-08"), LocalDateTime.parse("2021-12-08T20:00:00"), 25),
                new Record(LocalDate.parse("2021-12-08"), LocalDateTime.parse("2021-12-08T21:00:00"), 21),
                new Record(LocalDate.parse("2021-12-08"), LocalDateTime.parse("2021-12-08T22:00:00"), 16),
                new Record(LocalDate.parse("2021-12-08"), LocalDateTime.parse("2021-12-08T23:00:00"), 11),

                new Record(LocalDate.parse("2021-12-09"), LocalDateTime.parse("2021-12-09T00:00:00"), 4)
        );
    }

    private static Vector<DayRecord> defaultDayRecords() {
        return Vector.of(
                new DayRecord(LocalDate.parse("2021-12-01"), 179),
                new DayRecord(LocalDate.parse("2021-12-05"), 81),
                new DayRecord(LocalDate.parse("2021-12-08"), 134),
                new DayRecord(LocalDate.parse("2021-12-09"), 4)
        );
    }

}

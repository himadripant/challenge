package au.com.seek.aips.challenge.services;

import au.com.seek.aips.challenge.domain.DayRecord;
import au.com.seek.aips.challenge.domain.Record;
import io.vavr.collection.Vector;

import java.util.Comparator;

public class TrafficCalculator {

    private static final TrafficCalculator INSTANCE;

    static {
        INSTANCE = new TrafficCalculator();
    }

    private TrafficCalculator() {
    }

    public static TrafficCalculator trafficCalculator() {
        return INSTANCE;
    }

    public Vector<DayRecord> dayRecords(final Vector<Record> records) {
        return records
                .groupBy(Record::getDate)
                .mapValues(recordValues ->
                        recordValues.map(Record::getNumberOfCars)
                                .reduce((i1, i2) -> i1 + i2))
                .map(tuple2 -> new DayRecord(tuple2._1, tuple2._2))
                .toVector();
    }

    public int totalVehicleTraffic(final Vector<DayRecord> dayRecords) {
        return dayRecords
                .map(DayRecord::getNumberOfCars)
                .reduce((e1, e2) -> e1 + e2);
    }

    public Vector<Record> top3RecordsWithMostTraffic(final Vector<Record> records) {
        return records
                .sorted(Comparator.comparingInt(Record::getNumberOfCars).reversed())
                .take(3);
    }

}

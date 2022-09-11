package au.com.seek.aips.challenge;

import au.com.seek.aips.challenge.domain.DayRecord;
import au.com.seek.aips.challenge.domain.Record;
import io.vavr.collection.Vector;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.stream.Collectors;

import static au.com.seek.aips.challenge.services.ContiguousChunkFinder.contiguousChunkFinder;
import static au.com.seek.aips.challenge.services.FileReader.fileReader;
import static au.com.seek.aips.challenge.services.TrafficCalculator.trafficCalculator;

public class ChallengeApplication {

    private static final Logger LOGGER = LogManager.getLogger();

    public static void main(String[] args) {
        LOGGER.info("Starting job with arguments: [{}]", args);
        final String filePath = args[0];
        LOGGER.info("Input filename: [{}]", filePath);
        (new ChallengeApplication()).startingJob(filePath);
    }

    protected void startingJob(final String fileName) {
        try {
            final Vector<Record> records = fileReader().readFileAsVector(fileName);
            final Vector<DayRecord> dayRecords = trafficCalculator().dayRecords(records);
            LOGGER.info("The number of cars seen in total: {}", trafficCalculator().totalVehicleTraffic(dayRecords));
            LOGGER.info("The number of cars seen on given dates: \n{}",
                    dayRecords
                            .map(dayRecord -> String.format("%s %d", dayRecord.getDate(), dayRecord.getNumberOfCars()))
                            .collect(Collectors.joining("\n")));
            LOGGER.info("The top 3 half hours with most cars: \n{}",
                    trafficCalculator().top3RecordsWithMostTraffic(records)
                            .map(record -> String.format("%s %d", record.getDateTime(), record.getNumberOfCars()))
                            .collect(Collectors.joining("\n")));
            LOGGER.info("The 1.5 hour period with least cars: \n{}",
                    contiguousChunkFinder().smallestTriple(records).apply(this::tripleToString));
        } catch (Exception exception) {
            LOGGER.error(String.format("Job failed due to error: [%s]", exception.getMessage()), exception);
        }
    }

    protected String tripleToString(Record record1, Record record2, Record record3) {
        return String.format("%s %s %s", record1.getDateTime().toString(),
                record2.getDateTime().toString(),
                record3.getDateTime().toString());
    }

}

package au.com.seek.aips.challenge.services;

import au.com.seek.aips.challenge.domain.Record;
import io.vavr.collection.Vector;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class FileReader {

    private static final FileReader INSTANCE;

    static {
        INSTANCE = new FileReader();
    }

    private FileReader() {
    }

    public static FileReader fileReader() {
        return INSTANCE;
    }

    public Vector<Record> readFileAsVector(final String filePath) throws IOException {
        return Files.lines(Paths.get(filePath))
                .map(str -> str.split(" "))
                .map(strings -> {
                    LocalDateTime localDateTime = LocalDateTime.parse(strings[0]);
                    LocalDate localDate = localDateTime.toLocalDate();
                    Integer numberOfCars = Integer.valueOf(strings[1]);
                    return new Record(localDate, localDateTime, numberOfCars);
                })
                .collect(Vector.collector());
    }

}

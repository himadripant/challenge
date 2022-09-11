package au.com.seek.aips.challenge.services;

import au.com.seek.aips.challenge.TestUtils;
import au.com.seek.aips.challenge.domain.Record;
import io.vavr.collection.Vector;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;

import static au.com.seek.aips.challenge.services.FileReader.fileReader;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.collection.IsIterableContainingInOrder.contains;
import static org.junit.jupiter.api.Assertions.assertAll;

public class FileReaderTest {

    @Test
    @DisplayName("verify that collection of traffic record is generated given a valid text file")
    public void test_readFileAsVector_givenValidFileName() throws IOException {
        final Vector<Record> records = fileReader().readFileAsVector("src/test/resources/input/data.txt");
        assertAll(() -> assertThat(records, is(notNullValue())),
                () -> assertThat(records.size(), is(TestUtils.DEFAULT_RECORDS.size())),
                () -> assertThat(records.toJavaList(), contains(TestUtils.DEFAULT_RECORDS.toJavaArray())));
    }

    @Test
    @DisplayName("verify that collection of traffic record is generated given a valid text file whilst asserted against different collection of traffic data")
    public void test_readFileAsVector_givenValidFileNameAssertingAgainstDifferentCollection() throws IOException {
        final Vector<Record> resultRecords = fileReader().readFileAsVector("src/test/resources/input/data.txt");
        final Vector<Record> expectedRecords = TestUtils.DEFAULT_RECORDS.prepend(new Record(LocalDate.parse("2021-12-01"), LocalDateTime.parse("2021-12-01T04:30:00"), 0));
        assertAll(() -> assertThat(resultRecords, is(notNullValue())),
                () -> assertThat(resultRecords.size(), is((expectedRecords.size() - 1))),
                () -> assertThat(resultRecords.toJavaList(), not(contains(expectedRecords.toJavaArray()))),
                () -> assertThat(resultRecords.toJavaList(), contains(expectedRecords.drop(1).toJavaArray())));
    }

}

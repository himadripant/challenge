package au.com.seek.aips.challenge.services;

import au.com.seek.aips.challenge.TestUtils;
import au.com.seek.aips.challenge.domain.Record;
import io.vavr.Tuple3;
import io.vavr.Tuple4;
import io.vavr.collection.Vector;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static au.com.seek.aips.challenge.services.ContiguousChunkFinder.contiguousChunkFinder;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ContiguousChunkFinderTest {

    @Test
    @DisplayName("verify if three records are a contiguous in 90 min interval")
    public void test_areContiguous() {
        assertTrue(contiguousChunkFinder().areContiguous(new Record(LocalDate.parse("2021-12-01"), LocalDateTime.parse("2021-12-01T03:00:00"), 1),
                new Record(LocalDate.parse("2021-12-01"), LocalDateTime.parse("2021-12-01T03:30:00"), 2),
                new Record(LocalDate.parse("2021-12-01"), LocalDateTime.parse("2021-12-01T04:00:00"), 3)));
        assertFalse(contiguousChunkFinder().areContiguous(new Record(LocalDate.parse("2021-12-01"), LocalDateTime.parse("2021-12-01T03:00:00"), 1),
                new Record(LocalDate.parse("2021-12-01"), LocalDateTime.parse("2021-12-01T03:30:00"), 2),
                new Record(LocalDate.parse("2021-12-01"), LocalDateTime.parse("2021-12-01T04:30:00"), 3)));
    }

    @Test
    @DisplayName("verify if three records and vector reduced by one from original vector are returned")
    public void test_firstTripleFromVector() {
        final Tuple4<Record, Record, Record, Vector<Record>> test1 =
                contiguousChunkFinder().firstTripleFromVector(TestUtils.DEFAULT_RECORDS);
        assertAll(() -> assertThat(test1._1.getDateTime(), equalTo(LocalDateTime.parse("2021-12-01T05:00:00"))),
                () -> assertThat(test1._2.getDateTime(), equalTo(LocalDateTime.parse("2021-12-01T05:30:00"))),
                () -> assertThat(test1._3.getDateTime(), equalTo(LocalDateTime.parse("2021-12-01T06:00:00"))),
                () -> assertTrue(test1._4.containsAll(TestUtils.DEFAULT_RECORDS.drop(1))));

        final Tuple4<Record, Record, Record, Vector<Record>> test2 =
                contiguousChunkFinder().firstTripleFromVector(TestUtils.DEFAULT_RECORDS.drop(10));
        assertAll(() -> assertTrue(test2._1.getDateTime().equals(LocalDateTime.parse("2021-12-05T09:30:00"))),
                () -> assertTrue(test2._2.getDateTime().equals(LocalDateTime.parse("2021-12-05T10:30:00"))),
                () -> assertTrue(test2._3.getDateTime().equals(LocalDateTime.parse("2021-12-05T11:30:00"))),
                () -> assertTrue(test2._4.containsAll(TestUtils.DEFAULT_RECORDS.drop(11))));
    }

    @Test
    public void test_smallestTriple_givenDefaultValidRecords() {
        Tuple3<Record, Record, Record> tuple3 = contiguousChunkFinder().smallestTriple(TestUtils.DEFAULT_RECORDS);
        assertThat((tuple3._1.getNumberOfCars() + tuple3._2.getNumberOfCars() + tuple3._3.getNumberOfCars()), is(31));
        assertThat(tuple3._2.getDate(), equalTo(tuple3._1.getDate()));
        assertThat(tuple3._3.getDate(), equalTo(tuple3._2.getDate()));
        assertThat(tuple3._2.getDateTime().minusMinutes(30), equalTo(tuple3._1.getDateTime()));
        assertThat(tuple3._3.getDateTime().minusMinutes(30), equalTo(tuple3._2.getDateTime()));
    }

    @Test
    public void test_smallestTriple_givenSmallerRecordsSubsetAppendedAtHead() {
        Tuple3<Record, Record, Record> tuple3 = contiguousChunkFinder().smallestTriple(TestUtils.DEFAULT_RECORDS
                .prepend(new Record(LocalDate.parse("2021-12-01"), LocalDateTime.parse("2021-12-01T04:30:00"), 0))
                .prepend(new Record(LocalDate.parse("2021-12-01"), LocalDateTime.parse("2021-12-01T04:00:00"), 88)));
        assertThat((tuple3._1.getNumberOfCars() + tuple3._2.getNumberOfCars() + tuple3._3.getNumberOfCars()), is(17));
        assertThat(tuple3._2.getDate(), equalTo(tuple3._1.getDate()));
        assertThat(tuple3._3.getDate(), equalTo(tuple3._2.getDate()));
        assertThat(tuple3._2.getDateTime().minusMinutes(30), equalTo(tuple3._1.getDateTime()));
        assertThat(tuple3._3.getDateTime().minusMinutes(30), equalTo(tuple3._2.getDateTime()));
    }

    @Test
    public void test_smallestTriple_givenSmallerRecordsSubsetInsertedInMiddle() {
        Tuple3<Record, Record, Record> tuple3 = contiguousChunkFinder().smallestTriple(TestUtils.DEFAULT_RECORDS
                .insert(9, new Record(LocalDate.parse("2021-12-01"), LocalDateTime.parse("2021-12-01T23:00:00"), 0))
                .insert(9, new Record(LocalDate.parse("2021-12-01"), LocalDateTime.parse("2021-12-01T22:30:00"), 1)));
        assertThat((tuple3._1.getNumberOfCars() + tuple3._2.getNumberOfCars() + tuple3._3.getNumberOfCars()), is(1));
        assertThat(tuple3._2.getDate(), equalTo(tuple3._1.getDate()));
        assertThat(tuple3._3.getDate(), equalTo(tuple3._2.getDate()));
        assertThat(tuple3._2.getDateTime().minusMinutes(30), equalTo(tuple3._1.getDateTime()));
        assertThat(tuple3._3.getDateTime().minusMinutes(30), equalTo(tuple3._2.getDateTime()));
    }
}

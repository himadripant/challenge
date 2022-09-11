package au.com.seek.aips.challenge.services;

import au.com.seek.aips.challenge.TestUtils;
import au.com.seek.aips.challenge.domain.DayRecord;
import au.com.seek.aips.challenge.domain.Record;
import org.junit.jupiter.api.Test;

import static au.com.seek.aips.challenge.services.TrafficCalculator.trafficCalculator;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsIterableContainingInAnyOrder.containsInAnyOrder;
import static org.hamcrest.collection.IsIterableContainingInOrder.contains;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TrafficCalculatorTest {

    @Test
    public void test_dayRecords_givenValidDefaultRecords() {
        assertThat(trafficCalculator().dayRecords(TestUtils.DEFAULT_RECORDS)
                        .map(DayRecord::getNumberOfCars)
                        .toJavaList(),
                containsInAnyOrder(TestUtils.DEFAULT_DAY_RECORDS.map(DayRecord::getNumberOfCars).toJavaArray()));
    }

    @Test
    public void test_totalVehicleTraffic_givenDefaultDayRecords() {
        assertEquals(trafficCalculator().totalVehicleTraffic(TestUtils.DEFAULT_DAY_RECORDS), 398);
    }

    @Test
    public void test_top3RecordsWithMostTraffic_givenDefaultRecords() {
        assertThat(trafficCalculator().top3RecordsWithMostTraffic(TestUtils.DEFAULT_RECORDS)
                        .map(Record::getNumberOfCars)
                        .toJavaList(),
                contains(46, 42, 33));
    }

}

package au.com.seek.aips.challenge.services;

import au.com.seek.aips.challenge.domain.Record;
import io.vavr.Tuple3;
import io.vavr.Tuple4;
import io.vavr.collection.Vector;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

//@Service
public class ContiguousChunkFinder {

    private ContiguousChunkFinder() {}

    private static ContiguousChunkFinder INSTANCE;

    static {
        INSTANCE = new ContiguousChunkFinder();
    }

    public static ContiguousChunkFinder contiguousChunkFinder() {
        return INSTANCE;
    }

    public Tuple3<Record, Record, Record> smallestTriple(Vector<Record> records) {
        Map<Tuple3<Record, Record, Record>, Integer> tuple3IntegerMap = new HashMap<>();
        Tuple4<Record, Record, Record, Vector<Record>> tuple4;
        while (records.size() >= 3) {
            tuple4 = firstTripleFromVector(records);
            records = tuple4._4;
            if (areContiguous(tuple4._1, tuple4._2, tuple4._3)) {
                tuple3IntegerMap.put(new Tuple3<>(tuple4._1, tuple4._2, tuple4._3),
                        tuple4._1.getNumberOfCars() + tuple4._2.getNumberOfCars() + tuple4._3.getNumberOfCars());
            }
        }
        return tuple3IntegerMap
                .entrySet()
                .stream()
                .min(Comparator.comparing(tuple3IntegerEntry -> tuple3IntegerEntry.getValue()))
                .get()
                .getKey();
    }

    protected boolean areContiguous(Record record1, Record record2, Record record3) {
        return record1.getDateTime().plusMinutes(30).equals(record2.getDateTime())
                && record2.getDateTime().plusMinutes(30).equals(record3.getDateTime());
    }

    protected Tuple4<Record, Record, Record, Vector<Record>> firstTripleFromVector(final Vector<Record> records) {
        return records
                .transform((vector) -> new Tuple4<>(vector.get(0), vector.get(1), vector.get(2), vector.drop(1)));
    }

}

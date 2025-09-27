package algorithms;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ClosestPairTest {

    @Test
    public void testSimple() {
        ClosestPair.Point[] points = {
                new ClosestPair.Point(0, 0),
                new ClosestPair.Point(3, 4),
                new ClosestPair.Point(7, 7),
                new ClosestPair.Point(1, 1)
        };

        double result = ClosestPair.findClosest(points);
        assertEquals(Math.sqrt(2), result, 1e-9);
    }

    @Test
    public void testTwoPoints() {
        ClosestPair.Point[] points = {
                new ClosestPair.Point(0, 0),
                new ClosestPair.Point(1, 0)
        };

        double result = ClosestPair.findClosest(points);
        assertEquals(1.0, result, 1e-9);
    }

    @Test
    public void testEmpty() {
        ClosestPair.Point[] points = {};
        assertEquals(Double.POSITIVE_INFINITY, ClosestPair.findClosest(points));
    }

    @Test
    public void testSinglePoint() {
        ClosestPair.Point[] points = { new ClosestPair.Point(0, 0) };
        assertEquals(Double.POSITIVE_INFINITY, ClosestPair.findClosest(points));
    }
}

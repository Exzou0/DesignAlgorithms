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


    private double bruteForce(ClosestPair.Point[] pts) {
        if (pts.length < 2) return Double.POSITIVE_INFINITY;
        double min = Double.POSITIVE_INFINITY;
        for (int i = 0; i < pts.length; i++) {
            for (int j = i + 1; j < pts.length; j++) {
                min = Math.min(min, pts[i].dist(pts[j]));
            }
        }
        return min;
    }

    @Test
    public void testRandomSmallAgainstBruteForce() {
        java.util.Random rand = new java.util.Random();
        for (int t = 0; t < 5; t++) {
            int n = rand.nextInt(500) + 10;
            ClosestPair.Point[] pts = new ClosestPair.Point[n];
            for (int i = 0; i < n; i++) {
                pts[i] = new ClosestPair.Point(rand.nextDouble() * 1000, rand.nextDouble() * 1000);
            }
            double fast = ClosestPair.findClosest(pts);
            double slow = bruteForce(pts);
            assertEquals(slow, fast, 1e-9, "Trial " + t + " mismatch");
        }
    }

    @Test
    public void testLargeRandomFastOnly() {
        java.util.Random rand = new java.util.Random();
        int n = 50000;
        ClosestPair.Point[] pts = new ClosestPair.Point[n];
        for (int i = 0; i < n; i++) {
            pts[i] = new ClosestPair.Point(rand.nextDouble() * 1000, rand.nextDouble() * 1000);
        }
        double fast = ClosestPair.findClosest(pts);
        assertTrue(fast >= 0.0, "Distance should be non-negative");
    }
}

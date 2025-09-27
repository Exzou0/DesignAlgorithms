package algorithms;

import algorithms.metrics.Metrics;
import java.util.*;

public class ClosestPair {

    public static class Point {
        public final double x, y;
        public Point(double x, double y) { this.x = x; this.y = y; }
        public double dist(Point p) { return Math.hypot(x - p.x, y - p.y); }
    }


    public static double findClosest(Point[] points) {
        return findClosest(points, null);
    }


    public static double findClosest(Point[] points, Metrics metrics) {
        if (points == null || points.length < 2) {
            return Double.POSITIVE_INFINITY;
        }
        if (metrics != null) metrics.reset();

        Point[] px = points.clone();
        Point[] py = points.clone();
        Arrays.sort(px, Comparator.comparingDouble(a -> a.x));
        Arrays.sort(py, Comparator.comparingDouble(a -> a.y));
        if (metrics != null) metrics.incAllocations(points.length * 2); // клоны

        return closest(px, py, 0, px.length - 1, metrics);
    }

    private static double closest(Point[] px, Point[] py, int l, int r, Metrics metrics) {
        if (metrics != null) metrics.enterRecursion();

        if (r - l <= 2) {
            double min = Double.POSITIVE_INFINITY;
            for (int i = l; i <= r; i++) {
                for (int j = i + 1; j <= r; j++) {
                    if (metrics != null) metrics.incComparisons();
                    min = Math.min(min, px[i].dist(px[j]));
                }
            }
            if (metrics != null) metrics.leaveRecursion();
            return min;
        }

        int m = (l + r) / 2;
        Point mid = px[m];

        List<Point> leftY = new ArrayList<>();
        List<Point> rightY = new ArrayList<>();
        for (Point p : py) {
            if (metrics != null) metrics.incComparisons();
            if (p.x <= mid.x) leftY.add(p); else rightY.add(p);
        }
        if (metrics != null) metrics.incAllocations(leftY.size() + rightY.size());

        double d = Math.min(
                closest(px, leftY.toArray(new Point[0]), l, m, metrics),
                closest(px, rightY.toArray(new Point[0]), m + 1, r, metrics)
        );

        List<Point> strip = new ArrayList<>();
        for (Point p : py) {
            if (metrics != null) metrics.incComparisons();
            if (Math.abs(p.x - mid.x) < d) strip.add(p);
        }
        if (metrics != null) metrics.incAllocations(strip.size());

        for (int i = 0; i < strip.size(); i++) {
            for (int j = i + 1; j < strip.size(); j++) {
                if (metrics != null) metrics.incComparisons();
                if (strip.get(j).y - strip.get(i).y < d) {
                    d = Math.min(d, strip.get(i).dist(strip.get(j)));
                } else {
                    break;
                }
            }
        }

        if (metrics != null) metrics.leaveRecursion();
        return d;
    }

    public static void main(String[] args) {
        Point[] points = {
                new Point(2.0, 3.0),
                new Point(12.0, 30.0),
                new Point(40.0, 50.0),
                new Point(5.0, 1.0),
                new Point(12.0, 10.0),
                new Point(3.0, 4.0)
        };

        Metrics m = new Metrics();
        double result = findClosest(points, m);
        System.out.println("Closest distance = " + result);
        System.out.println("Comparisons=" + m.getComparisons() +
                ", Allocations=" + m.getAllocations() +
                ", MaxDepth=" + m.getMaxDepth());
    }
}

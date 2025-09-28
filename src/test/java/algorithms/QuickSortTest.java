package algorithms;

import algorithms.metrics.Metrics;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class QuickSortTest {

    @Test
    public void testQuickSortCorrectness() {
        int[] arr = {9, 2, 7, 3, 1, 8, 5, 4, 6, 0};
        Quicksort.sort(arr);
        assertArrayEquals(new int[]{0,1,2,3,4,5,6,7,8,9}, arr);
    }

    @Test
    public void testQuickSortEmptyArray() {
        int[] arr = {};
        Quicksort.sort(arr);
        assertArrayEquals(new int[]{}, arr);
    }

    @Test
    public void testQuickSortSingleElement() {
        int[] arr = {42};
        Quicksort.sort(arr);
        assertArrayEquals(new int[]{42}, arr);
    }

    @Test
    public void testMetricsCollected() {
        int[] arr = {5, 1, 4, 2, 3};
        Metrics m = new Metrics();
        Quicksort.sort(arr, m);

        assertArrayEquals(new int[]{1,2,3,4,5}, arr);

        assertTrue(m.getComparisons() > 0, "Comparisons should be > 0");
        assertTrue(m.getAllocations() >= 0, "Allocations should be >= 0");
        assertTrue(m.getMaxDepth() > 0, "Max depth should be > 0");
    }
}

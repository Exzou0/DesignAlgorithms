package algorithms;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class QuickSortTest {
    @Test
    public void testQuickSort() {
        int[] arr = {9, 2, 7, 3, 1, 8, 5, 4, 6, 0};
        Quicksort.sort(arr);
        assertArrayEquals(new int[]{0,1,2,3,4,5,6,7,8,9}, arr);
    }

    @Test
    public void testEmptyArray() {
        int[] arr = {};
        Quicksort.sort(arr);
        assertArrayEquals(new int[]{}, arr);
    }

    @Test
    public void testSingleElement() {
        int[] arr = {42};
        Quicksort.sort(arr);
        assertArrayEquals(new int[]{42}, arr);
    }
}

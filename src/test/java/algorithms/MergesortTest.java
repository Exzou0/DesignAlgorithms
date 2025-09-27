package algorithms;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import java.util.Arrays;

public class MergesortTest {

    @Test
    public void testSort() {
        int[] arr = {5, 2, 9, 1, 5, 6, 3, 2, 8, 7, 4, 0};
        int[] expected = arr.clone();
        Arrays.sort(expected);

        MergeSort.sort(arr);

        assertArrayEquals(expected, arr);
    }

    @Test
    public void testEmptyArray() {
        int[] arr = {};
        int[] expected = {};
        MergeSort.sort(arr);
        assertArrayEquals(expected, arr);
    }

    @Test
    public void testSingleElement() {
        int[] arr = {42};
        int[] expected = {42};
        MergeSort.sort(arr);
        assertArrayEquals(expected, arr);
    }

    @Test
    public void testAlreadySorted() {
        int[] arr = {1, 2, 3, 4, 5};
        int[] expected = {1, 2, 3, 4, 5};
        MergeSort.sort(arr);
        assertArrayEquals(expected, arr);
    }
}

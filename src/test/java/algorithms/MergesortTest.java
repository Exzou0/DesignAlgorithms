package algorithms;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

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
    public void testEmpty() {
        int[] arr = {};
        int[] expected = {};
        MergeSort.sort(arr);
        assertArrayEquals(expected, arr);
    }

    @Test
    public void testSingle() {
        int[] arr = {42};
        int[] expected = {42};
        MergeSort.sort(arr);
        assertArrayEquals(expected, arr);
    }
}

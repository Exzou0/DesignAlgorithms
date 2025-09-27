package algorithms;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;

public class DeterministicSelectTest {

    @Test
    public void testSimple() {
        int[] arr = {12, 3, 5, 7, 4, 19, 26};
        Arrays.sort(arr.clone());
        assertEquals(5, DeterministicSelect.select(arr.clone(), 2));
    }

    @Test
    public void testFirstAndLast() {
        int[] arr = {10, 50, 20, 30, 40};
        assertEquals(10, DeterministicSelect.select(arr.clone(), 0));
        assertEquals(50, DeterministicSelect.select(arr.clone(), 4));
    }

    @Test
    public void testRandom() {
        int[] arr = {9, 1, 8, 2, 7, 3, 6, 4, 5};
        int[] sorted = arr.clone();
        Arrays.sort(sorted);
        for (int k = 0; k < arr.length; k++) {
            assertEquals(sorted[k], DeterministicSelect.select(arr.clone(), k));
        }
    }
}

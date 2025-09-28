package algorithms;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeterministicSelectTest {

    @Test
    public void testSimple() {
        int[] arr = {12, 3, 5, 7, 4, 19, 26};
        int[] sorted = arr.clone();
        Arrays.sort(sorted);
        assertEquals(sorted[2], DeterministicSelect.select(arr.clone(), 2));
    }

    @Test
    public void testFirstAndLast() {
        int[] arr = {10, 50, 20, 30, 40};
        int[] sorted = arr.clone();
        Arrays.sort(sorted);
        assertEquals(sorted[0], DeterministicSelect.select(arr.clone(), 0));
        assertEquals(sorted[4], DeterministicSelect.select(arr.clone(), 4));
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

    @Test
    public void testRandomized100Trials() {
        Random rand = new Random();
        for (int t = 0; t < 100; t++) {
            int n = rand.nextInt(100) + 1;
            int[] arr = new int[n];
            for (int i = 0; i < n; i++) {
                arr[i] = rand.nextInt(1000);
            }
            int k = rand.nextInt(n);
            int[] sorted = arr.clone();
            Arrays.sort(sorted);
            assertEquals(sorted[k], DeterministicSelect.select(arr.clone(), k),
                    "Failed on trial " + t + " with k=" + k);
        }
    }
}

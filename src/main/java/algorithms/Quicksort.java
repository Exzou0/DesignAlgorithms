package algorithms;

import java.util.Random;

public class Quicksort {
    private static final Random rand = new Random();

    public static void sort(int[] arr) {
        quicksort(arr, 0, arr.length - 1);
    }

    private static void quicksort(int[] arr, int low, int high) {
        while (low < high) {
            int pivotIndex = low + rand.nextInt(high - low + 1);
            int pivot = arr[pivotIndex];

            int i = low, j = high;
            while (i <= j) {
                while (arr[i] < pivot) i++;
                while (arr[j] > pivot) j--;
                if (i <= j) {
                    int temp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = temp;
                    i++;
                    j--;
                }
            }

            if (j - low < high - i) {
                if (low < j) quicksort(arr, low, j);
                low = i;
            } else {
                if (i < high) quicksort(arr, i, high);
                high = j;
            }
        }
    }

    public static void main(String[] args) {
        int[] arr = {9, 2, 7, 3, 1, 8, 5, 4, 6, 0};
        Quicksort.sort(arr);
        System.out.println(java.util.Arrays.toString(arr));
    }
}

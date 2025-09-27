package algorithms;

import algorithms.metrics.Metrics;
import java.util.Random;

public class Quicksort {
    private static final Random rand = new Random();


    public static void sort(int[] arr) {
        quicksort(arr, 0, arr.length - 1, null);
    }

    public static void sort(int[] arr, Metrics metrics) {
        if (metrics != null) metrics.reset();
        quicksort(arr, 0, arr.length - 1, metrics);
    }

    private static void quicksort(int[] arr, int low, int high, Metrics metrics) {
        while (low < high) {
            if (metrics != null) metrics.enterRecursion();

            int pivotIndex = low + rand.nextInt(high - low + 1);
            int pivot = arr[pivotIndex];

            int i = low, j = high;
            while (i <= j) {
                if (metrics != null) metrics.incComparisons();
                while (arr[i] < pivot) {
                    if (metrics != null) metrics.incComparisons();
                    i++;
                }
                if (metrics != null) metrics.incComparisons();
                while (arr[j] > pivot) {
                    if (metrics != null) metrics.incComparisons();
                    j--;
                }
                if (metrics != null) metrics.incComparisons();
                if (i <= j) {
                    swap(arr, i, j);
                    if (metrics != null) metrics.incAllocations(3); // temp + 2 assigns
                    i++;
                    j--;
                }
            }


            if (j - low < high - i) {
                if (low < j) quicksort(arr, low, j, metrics);
                low = i;
            } else {
                if (i < high) quicksort(arr, i, high, metrics);
                high = j;
            }

            if (metrics != null) metrics.leaveRecursion();
        }
    }

    private static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    public static void main(String[] args) {
        int[] arr = {9, 2, 7, 3, 1, 8, 5, 4, 6, 0};
        Metrics m = new Metrics();
        Quicksort.sort(arr, m);
        System.out.println(java.util.Arrays.toString(arr));
        System.out.println("Comparisons=" + m.getComparisons() +
                ", Allocations=" + m.getAllocations() +
                ", MaxDepth=" + m.getMaxDepth());
    }
}

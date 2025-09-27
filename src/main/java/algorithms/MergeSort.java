package algorithms;

import algorithms.metrics.Metrics;

public class MergeSort {
    private static final int CUTOFF = 16;

    public static void sort(int[] arr) {
        sort(arr, null);
    }


    public static void sort(int[] arr, Metrics metrics) {
        int[] temp = new int[arr.length];
        sort(arr, temp, 0, arr.length - 1, metrics);
    }

    private static void sort(int[] arr, int[] temp, int low, int high, Metrics metrics) {
        if (metrics != null) metrics.enterRecursion();

        if (high - low + 1 <= CUTOFF) {
            insertionSort(arr, low, high, metrics);
            if (metrics != null) metrics.leaveRecursion();
            return;
        }

        int mid = low + (high - low) / 2;
        sort(arr, temp, low, mid, metrics);
        sort(arr, temp, mid + 1, high, metrics);

        if (metrics != null) metrics.incComparisons();
        if (arr[mid] > arr[mid + 1]) {
            merge(arr, temp, low, mid, high, metrics);
        }

        if (metrics != null) metrics.leaveRecursion();
    }

    private static void merge(int[] arr, int[] temp, int low, int mid, int high, Metrics metrics) {
        System.arraycopy(arr, low, temp, low, high - low + 1);
        if (metrics != null) metrics.incAllocations(high - low + 1);

        int i = low, j = mid + 1;
        for (int k = low; k <= high; k++) {
            if (i > mid) arr[k] = temp[j++];
            else if (j > high) arr[k] = temp[i++];
            else if (temp[i] <= temp[j]) {
                if (metrics != null) metrics.incComparisons();
                arr[k] = temp[i++];
            } else {
                if (metrics != null) metrics.incComparisons();
                arr[k] = temp[j++];
            }
        }
    }

    private static void insertionSort(int[] arr, int low, int high, Metrics metrics) {
        for (int i = low + 1; i <= high; i++) {
            int key = arr[i];
            int j = i - 1;
            while (j >= low && arr[j] > key) {
                if (metrics != null) metrics.incComparisons();
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = key;
        }
    }
}

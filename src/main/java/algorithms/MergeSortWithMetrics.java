package algorithms;

import algorithms.metrics.Metrics;

public class MergeSortWithMetrics {
    private static final int CUTOFF = 16;

    public static void sort(int[] arr, Metrics m) {
        if (arr == null || arr.length <= 1) return;
        if (m != null) m.incAllocations(arr.length);
        int[] aux = new int[arr.length];
        sort(arr, aux, 0, arr.length - 1, m);
    }

    private static void sort(int[] arr, int[] aux, int low, int high, Metrics m) {
        if (m != null) m.enterRecursion();
        if (high - low + 1 <= CUTOFF) {
            insertionSort(arr, low, high, m);
            if (m != null) m.leaveRecursion();
            return;
        }

        int mid = low + (high - low) / 2;
        sort(arr, aux, low, mid, m);
        sort(arr, aux, mid + 1, high, m);

        if (m != null) m.incComparisons();
        if (arr[mid] <= arr[mid + 1]) {
            if (m != null) m.leaveRecursion();
            return;
        }

        merge(arr, aux, low, mid, high, m);
        if (m != null) m.leaveRecursion();
    }

    private static void merge(int[] arr, int[] aux, int low, int mid, int high, Metrics m) {
        if (m != null) m.incAllocations(high - low + 1);
        System.arraycopy(arr, low, aux, low, high - low + 1);

        int i = low, j = mid + 1;
        for (int k = low; k <= high; k++) {
            if (i > mid) arr[k] = aux[j++];
            else if (j > high) arr[k] = aux[i++];
            else {
                if (m != null) m.incComparisons();
                if (aux[i] <= aux[j]) arr[k] = aux[i++];
                else arr[k] = aux[j++];
            }
        }
    }

    private static void insertionSort(int[] arr, int low, int high, Metrics m) {
        for (int i = low + 1; i <= high; i++) {
            int key = arr[i];
            int j = i - 1;
            while (j >= low) {
                if (m != null) m.incComparisons();
                if (arr[j] > key) {
                    arr[j + 1] = arr[j];
                    j--;
                } else break;
            }
            arr[j + 1] = key;
        }
    }
}

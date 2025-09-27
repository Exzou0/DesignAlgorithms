package algorithms;

import algorithms.metrics.Metrics;
import java.util.Arrays;

public class DeterministicSelect {


    public static int select(int[] arr, int k) {
        return select(arr, k, null);
    }

    public static int select(int[] arr, int k, Metrics metrics) {
        if (k < 0 || k >= arr.length) throw new IllegalArgumentException("k out of bounds");
        if (metrics != null) metrics.reset();
        return select(arr, 0, arr.length - 1, k, metrics);
    }

    private static int select(int[] arr, int left, int right, int k, Metrics metrics) {
        while (true) {
            if (left == right) return arr[left];

            int pivotIndex = medianOfMedians(arr, left, right, metrics);
            pivotIndex = partition(arr, left, right, pivotIndex, metrics);

            if (k == pivotIndex) {
                return arr[k];
            } else if (k < pivotIndex) {
                right = pivotIndex - 1;
            } else {
                left = pivotIndex + 1;
            }
        }
    }

    private static int partition(int[] arr, int left, int right, int pivotIndex, Metrics metrics) {
        int pivotValue = arr[pivotIndex];
        swap(arr, pivotIndex, right, metrics);
        int storeIndex = left;
        for (int i = left; i < right; i++) {
            if (metrics != null) metrics.incComparisons();
            if (arr[i] < pivotValue) {
                swap(arr, storeIndex, i, metrics);
                storeIndex++;
            }
        }
        swap(arr, storeIndex, right, metrics);
        return storeIndex;
    }

    private static int medianOfMedians(int[] arr, int left, int right, Metrics metrics) {
        if (metrics != null) metrics.enterRecursion();

        int n = right - left + 1;
        if (n < 5) {
            Arrays.sort(arr, left, right + 1);
            if (metrics != null) {
                metrics.incAllocations(n);
                metrics.leaveRecursion();
            }
            return left + n / 2;
        }

        int numMedians = 0;
        for (int i = left; i <= right; i += 5) {
            int subRight = Math.min(i + 4, right);
            Arrays.sort(arr, i, subRight + 1);
            if (metrics != null) metrics.incAllocations(subRight - i + 1);
            int medianIndex = i + (subRight - i) / 2;
            swap(arr, left + numMedians, medianIndex, metrics);
            numMedians++;
        }

        int res = medianOfMedians(arr, left, left + numMedians - 1, metrics);
        if (metrics != null) metrics.leaveRecursion();
        return res;
    }

    private static void swap(int[] arr, int i, int j, Metrics metrics) {
        if (i == j) return;
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
        if (metrics != null) metrics.incAllocations(3);
    }

    public static void main(String[] args) {
        int[] arr = {12, 3, 5, 7, 4, 19, 26};
        int k = 3;
        Metrics m = new Metrics();
        int kth = DeterministicSelect.select(arr, k, m);
        System.out.println("k=" + k + " element = " + kth);
        System.out.println("Comparisons=" + m.getComparisons() +
                ", Allocations=" + m.getAllocations() +
                ", MaxDepth=" + m.getMaxDepth());
    }
}

package algorithms;

public class MergeSort {
    private static final int CUTOFF = 16;

    public static void sort(int[] arr) {
        int[] temp = new int[arr.length];
        sort(arr, temp, 0, arr.length - 1);
    }

    private static void sort(int[] arr, int[] temp, int low, int high) {
        if (high - low + 1 <= CUTOFF) {
            insertionSort(arr, low, high);
            return;
        }

        int mid = low + (high - low) / 2;
        sort(arr, temp, low, mid);
        sort(arr, temp, mid + 1, high);


        if (arr[mid] <= arr[mid + 1]) return;

        merge(arr, temp, low, mid, high);
    }

    private static void merge(int[] arr, int[] temp, int low, int mid, int high) {

        System.arraycopy(arr, low, temp, low, high - low + 1);

        int i = low;
        int j = mid + 1;

        for (int k = low; k <= high; k++) {
            if (i > mid) arr[k] = temp[j++];
            else if (j > high) arr[k] = temp[i++];
            else if (temp[i] <= temp[j]) arr[k] = temp[i++];
            else arr[k] = temp[j++];
        }
    }

    private static void insertionSort(int[] arr, int low, int high) {
        for (int i = low + 1; i <= high; i++) {
            int key = arr[i];
            int j = i - 1;
            while (j >= low && arr[j] > key) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = key;
        }
    }


    public static void main(String[] args) {
        int[] arr = {5, 2, 9, 1, 5, 6, 3, 2, 8, 7, 4, 0};
        MergeSort.sort(arr);
        System.out.println(java.util.Arrays.toString(arr));
    }
}

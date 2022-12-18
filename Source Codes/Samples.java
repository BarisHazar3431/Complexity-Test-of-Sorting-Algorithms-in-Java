import java.util.Arrays;

public final class Samples { // A utility class to create different samples for different algorithms to test

    private Samples(){}; // Prevent instantiation.


    /**Initializes an int array with
     given size and input range*/
    public static int[] getRandomArray(int size, int range) {
        int[] array = new int[size];

        for (int i = 0; i < size; i++) {
            array[i] = (int) (Math.random() * (range + 1)); //Initialize array with random sample with a specific range of values
        }
        return array;
    }
    /**Returns a sorted int array with
     * given size and input range */
    public static int[] getSortedArray(int size, int range) {
        int[] arr = getRandomArray(size, range);
        Arrays.sort(arr); // Just sort random array generated (Problem reduction)
        return arr;
    }
    /**Returns a reverse sorted sample array*/
    public static int[] getReverseSortedArr(int size, int range) {
        int[] arr = getRandomArray(size, range);
        reverseSort(arr);
        return arr;
    }

    /**Returns the worst case sample for mergesort */
    public static int[] getMergeSortWorst(int size, int range) {
        int[] arr = getSortedArray(size, range);
        alternateArray(arr);
        return arr;
    }

    /**Returns a sorted array with contiguous integers
     * ranging from 0 to size - 1*/
    public static int[] getSortedUnique(int size) {
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = i;
        }
        return arr;
    }
    /**Returns a reverse sorted sample with unique elements*/
    public static int[] getReverseSortedUnique(int size) {
        int[] arr = getSortedUnique(size);
        reverseSort(arr);
        return arr;
    }

    /**Merges two arrays into one array (used in implementation
     * for the worst case sample of mergesort)*/
    private static void merge(int[] arr, int[] left, int[] right) {
        int i;
        for (i = 0; i < left.length; i++) {
            arr[i] = left[i];
        }
        for (int j = 0; j < right.length; j++, i++) {
            arr[i] = right[j];
        }
    }
    /**Alternates a given sorted array such that
     * its left and right sub arrays will have alternating
     * elements of the original sorted array.*/
    private static void alternateArray(int[] arr) {
        if(arr.length < 2) {
            return;
        }
        if(arr.length == 2) {
            int temp = arr[0];
            arr[0] = arr[1];
            arr[1] = temp;
            return;
        }

        int middle = (arr.length + 1) / 2;
        int[] left = new int[middle];
        int[] right = new int[arr.length - middle];

        for (int i = 0, j = 0; j < left.length; i += 2, j++) { //Fill left sub array with every other element of arr
            left[j] = arr[i];
        }

        for (int i = 1, j = 0; j < right.length; i += 2, j++) { ////Fill right sub array with every other element of arr
            right[j] = arr[i];
        }

        alternateArray(left);
        alternateArray(right);
        merge(arr, left, right);
    }

    /**Sorts an array in descending order
     * (Used in creating a sample array)*/
    private static void reverseSort(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            int j = i - 1;
            int key = arr[i];

            while (j >= 0 && key > arr[j]) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = key;
        }
    }
}

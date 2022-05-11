/*This is a program to compare different sorting algorithms
by counting the executions of basic operations.
* */

public class SortingExperiment {

    public static final int STEP_SIZE = 500; //Step size for input sizes
    public static final int MEASUREMENTS = 15; // Number of measurements for each case
    public static final int MAX_RANGE = 10000; // Maximum range of values

    public static void main(String[] args) {

        testInsertionSort();
        testBinaryInsertSort();
        testMergeSort();
        testQuickSort();
        testQuickMedianOf3();
        testHeapSort();
        testCountingSort();

    }

    public static void testInsertionSort() {
        int range = MAX_RANGE;
        System.out.println("============= INSERTION SORT =============\n");

        System.out.println("-- Best Case: --");
        System.out.println("INPUT SIZE\t\tBASIC OPERATIONS");
        for (int i = 1; i <= MEASUREMENTS; i++) {
            int size = i * STEP_SIZE;
            int[] array = Samples.getSortedArray(size, range); //Best case occurs if we use a sorted sample
            int count = SortingAlgorithms.insertionSort(array);

            System.out.println(size + " \t\t\t" + count);
        }

        System.out.println("\n--- Worst Case: ---");
        System.out.println("INPUT SIZE\t\tBASIC OPERATIONS");
        for (int i = 1; i <= MEASUREMENTS; i++) {
            int size = i * STEP_SIZE;
            int[] arr = Samples.getReverseSortedArr(size, range); //Worst case occurs when we use reverse sorted sample
            int count = SortingAlgorithms.insertionSort(arr);
            System.out.println(size + " \t\t\t" + count);
        }

        System.out.println("\n--- Average Case: ---");
        System.out.println("INPUT SIZE\t\tBASIC OPERATIONS");
        for (int i = 1; i <= MEASUREMENTS; i++) {
            int size = i * STEP_SIZE;
            int count = 0;
            int iterations = 15;
            for (int j = 0; j < iterations; j++) { //Iterate it and take the average to get more precise result.
                int[] arr = Samples.getRandomArray(size, range); //Average case when sample is random
                count += SortingAlgorithms.insertionSort(arr);
            }
            count /= iterations; // Average of count is used in average case.
            System.out.println(size + " \t\t\t" + count);
        }

        System.out.println("\n\n");
    }

    public static void testBinaryInsertSort() {
        int range = MAX_RANGE;
        System.out.println("============= BINARY INSERTION SORT =============\n");

        System.out.println("-- Best Case: --");
        System.out.println("INPUT SIZE\t\tBASIC OPERATIONS");
        for (int i = 1; i <= MEASUREMENTS; i++) {
            int size = i * STEP_SIZE;
            int[] array = Samples.getSortedArray(size, range); //Best case occurs if we use a sorted sample
            int count = SortingAlgorithms.binaryInsertSort(array);

            System.out.println(size + " \t\t\t" + count);
        }

        System.out.println("\n--- Worst Case: ---");
        System.out.println("INPUT SIZE\t\tBASIC OPERATIONS");
        for (int i = 1; i <= MEASUREMENTS; i++) {
            int size = i * STEP_SIZE;
            int[] arr = Samples.getReverseSortedArr(size, range); //Worst case occurs when we use reverse sorted sample
            int count = SortingAlgorithms.binaryInsertSort(arr);
            System.out.println(size + " \t\t\t" + count);
        }

        System.out.println("\n--- Average Case: ---");
        System.out.println("INPUT SIZE\t\tBASIC OPERATIONS");
        for (int i = 1; i <= MEASUREMENTS; i++) {
            int size = i * STEP_SIZE;
            int count = 0;
            int iterations = 15;

            for (int j = 0; j < iterations; j++) { //Iterate more than one to get more precise results
                int[] arr = Samples.getRandomArray(size, range); //Average case when sample is random
                count += SortingAlgorithms.binaryInsertSort(arr);
            }
            count /= iterations; // Take the average of total count.
            System.out.println(size + " \t\t\t" + count);
        }
        System.out.println("\n\n");
    }

    public static void testMergeSort() {
        int range = MAX_RANGE;
        System.out.println("============= MERGE SORT =============\n");

        System.out.println("-- Best Case: --");
        System.out.println("INPUT SIZE\t\tBASIC OPERATIONS");
        for (int i = 1; i <= MEASUREMENTS; i++) {
            int size = i * STEP_SIZE;
            int[] array = Samples.getSortedArray(size, range); //Best case occurs if we use a sorted sample
            int count = SortingAlgorithms.mergeSort(array, 0, size - 1, 0);

            System.out.println(size + " \t\t\t" + count);
        }

        System.out.println("\n-- Worst Case: --");
        System.out.println("INPUT SIZE\t\tBASIC OPERATIONS");
        for (int i = 1; i <= MEASUREMENTS; i++) {
            int size = i * STEP_SIZE;
            int[] array = Samples.getMergeSortWorst(size, range); //Worst case occurs if we have a proper sample.
            int count = SortingAlgorithms.mergeSort(array, 0, size - 1, 0);

            System.out.println(size + " \t\t\t" + count);
        }

        System.out.println("\n-- Average Case: --");
        System.out.println("INPUT SIZE\t\tBASIC OPERATIONS");
        for (int i = 1; i <= MEASUREMENTS; i++) {
            int size = i * STEP_SIZE;
            int iterations = 15;
            int count = 0; //Basic operation counter

            for (int j = 0; j < iterations; j++) { // Iterate with random array 15 times to get a more precise result
                int[] array = Samples.getRandomArray(size, range); //Average case occurs if we use a random sample
                count += SortingAlgorithms.mergeSort(array, 0, size - 1, 0);
            }
            count /= iterations; // Take the average of basic operation count.
            System.out.println(size + " \t\t\t" + count);
        }
        System.out.println("\n\n");
    }

    public static void testQuickSort() {
        System.out.println("============= Quick SORT =============");

        System.out.println("\n-- Best Case: --");
        System.out.println("INPUT SIZE\t\tBASIC OPERATIONS");
        for (int i = 1; i <= MEASUREMENTS; i++) {
            int size = i * STEP_SIZE;
            int[] array = Samples.getSortedArray(size, 0); //Best case occurs if we use a sample with all the elements are the same (Hoare's partitioning.)
            int count = SortingAlgorithms.quickSort(array, 0, size - 1,false, 0);

            System.out.println(size + " \t\t\t" + count);
        }

        System.out.println("\n-- Worst Case: --");
        System.out.println("INPUT SIZE\t\tBASIC OPERATIONS");
        for (int i = 1; i <= MEASUREMENTS; i++) {
            int size = i * STEP_SIZE;

            int[] array = Samples.getSortedUnique(size); //Worst case occurs if we use a uniquely sorted sample array.
            int count = SortingAlgorithms.quickSort(array, 0, size - 1, false, 0);

            System.out.println(size + " \t\t\t" + count);
        }

        System.out.println("\n-- Average Case: --");
        System.out.println("INPUT SIZE\t\tBASIC OPERATIONS");
        for (int i = 1; i <= MEASUREMENTS; i++) {
            int size = i * STEP_SIZE;
            int iterations = 15;
            int count = 0; //Basic operation counter

            for (int j = 0; j < iterations; j++) { // Iterate with random array 15 times to get a more precise result
                //Average case occurs if we use a random sample
                int[] array = Samples.getRandomArray(size, MAX_RANGE);
                count += SortingAlgorithms.quickSort(array, 0, size - 1, false, 0);
            }
            count /= iterations; // Take the average of basic operation count.
            System.out.println(size + " \t\t\t" + count);
        }
        System.out.println("\n\n");
    }

    public static void testQuickMedianOf3() {
        System.out.println("============= Quick SORT (Median of-3) =============");

        System.out.println("\n-- Best Case: --");
        System.out.println("INPUT SIZE\t\tBASIC OPERATIONS");
        for (int i = 1; i <= MEASUREMENTS; i++) {
            int size = i * STEP_SIZE;
            int[] array = Samples.getRandomArray(size, 0); //Best case occurs if we use a sample with all the elements are the same (Hoare's partitioning.)
            int count = SortingAlgorithms.quickSort(array, 0, size - 1,true, 0);

            System.out.println(size + " \t\t\t" + count);
        }

        System.out.println("\n-- Worst Case: --");
        System.out.println("INPUT SIZE\t\tBASIC OPERATIONS");
        for (int i = 1; i <= MEASUREMENTS; i++) {
            int size = i * STEP_SIZE;

            int[] array = Samples.getReverseSortedUnique(size); //Worst case occurs if we use a uniquely & reverse sorted sample array.
            int count = SortingAlgorithms.quickSort(array, 0, size - 1, true, 0);

            System.out.println(size + " \t\t\t" + count);
        }

        System.out.println("\n-- Average Case: --");
        System.out.println("INPUT SIZE\t\tBASIC OPERATIONS");
        for (int i = 1; i <= MEASUREMENTS; i++) {
            int size = i * STEP_SIZE;
            int iterations = 15;
            int count = 0; //Basic operation counter

            for (int j = 0; j < iterations; j++) { // Iterate with random array 15 times to get a more precise result
                int[] array = Samples.getRandomArray(size, MAX_RANGE); //Average case occurs if we use a random sample
                count += SortingAlgorithms.quickSort(array, 0, size - 1, true, 0);
            }
            count /= iterations; // Take the average of basic operation count.
            System.out.println(size + " \t\t\t" + count);
        }
        System.out.println("\n\n");
    }

    public static void testHeapSort() {
        System.out.println("============= Heap Sort =============");

        System.out.println("\n-- Best Case: --");
        System.out.println("INPUT SIZE\t\tBASIC OPERATIONS");
        for (int i = 1; i <= MEASUREMENTS; i++) {
            int size = i * STEP_SIZE;
            int[] array = Samples.getSortedArray(size, 0); //Best case occurs if we use a sample with all elements are the same
            int count = SortingAlgorithms.heapSort(array);

            System.out.println(size + " \t\t\t" + count);
        }

        System.out.println("\n-- Worst Case: --");
        System.out.println("INPUT SIZE\t\tBASIC OPERATIONS");
        for (int i = 1; i <= MEASUREMENTS; i++) {
            int size = i * STEP_SIZE;

            int[] array = Samples.getSortedUnique(size); //Worst case nearly occurs when we use a sorted array with distinct keys.
            int count = SortingAlgorithms.heapSort(array);

            System.out.println(size + " \t\t\t" + count);
        }

        System.out.println("\n-- Average Case: --");
        System.out.println("INPUT SIZE\t\tBASIC OPERATIONS");
        for (int i = 1; i <= MEASUREMENTS; i++) {
            int size = i * STEP_SIZE;
            int iterations = 15;
            int count = 0; //Basic operation counter
            for (int j = 0; j < iterations; j++) { // Iterate with random array 15 times to get a more precise result
                int[] array = Samples.getRandomArray(size, MAX_RANGE); //Average case occurs if we use a random sample
                count += SortingAlgorithms.heapSort(array);
            }
            count /= iterations; // Take the average of basic operation count.
            System.out.println(size + " \t\t\t" + count);

        }
        System.out.println("\n\n");
    }

    public static void testCountingSort() {
        int range = MAX_RANGE;
        System.out.println("============= Counting Sort =============");

        System.out.println("\n-- Best, Worst and Average Case: --");
        System.out.println("INPUT SIZE\t\tBASIC OPERATIONS");
        for (int i = 1; i <= MEASUREMENTS; i++) {
            int size = i * STEP_SIZE;
            int iterations = 15;
            int count = 0; //Basic operation counter
            for (int j = 0; j < iterations; j++) { // Iterate with random array 15 times to get a more precise result
                int[] array = Samples.getRandomArray(size, range); //Average case occurs if we use a random sample
                count += SortingAlgorithms.countingSort(array, MAX_RANGE);
            }
            count /= iterations; // Take the average of basic operation count.
            System.out.println(size + " \t\t\t" + count);

        }
        System.out.println("\n\n");
    }
}

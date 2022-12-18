public final class SortingAlgorithms { // A utility class that consists of Sorting algorithms to be used in the experiment

    private SortingAlgorithms(){}; //Prevent instantiation.


    /**Insertion sort algorithm to sort the given array in increasing
     * order and return the number of basic operations T(n)*/
    public static int insertionSort(int[] arr) {
        int counter = 0; // Count of basic operation executions
        for (int i = 1; i < arr.length - 1; i++) {
            int j = i - 1;
            int key = arr[i];

            while (j >= 0 && key < arr[j]) {
                counter++;
                arr[j + 1] = arr[j];
                j--;
            }
            if (j >= 0) counter++; //If we didn't enter the while loop, increment counter. (Because we made a comparison)
            arr[j + 1] = key;
        }
        return counter;
    }
    /**Takes an array, its indexes and a key as input, then returns
     the possible index of that key in the array by applying a
     different kind of binary search*/
    private static int[] binarySearch(int[] arr, int left, int right, int key, int count) {
        int[] result = new int[2]; // Returns count of execution and result of binary search.
        result[0] = count;
        if (left >= right) { // Base case of the method.
            result[1] = (key < arr[left]) ? left : left + 1;
            result[0]++; // Increment counter because we made a comparison in above line
            return result;
        }
        int mid = left + (right - left) / 2;
        if (arr[mid] == key) {
            result[0]++;
            result[1] = mid + 1;
            return result;
        }

        if (key > arr[mid]) {
            return binarySearch(arr, mid+1, right, key, count + 1); //Increase count
        } else {
            return binarySearch(arr, left, mid-1, key, count + 1); //Increase count
        }
    }
    /**Binary Insertion sort algorithm to sort an array
     * and return number of basic operations T(n)*/
    public static int binaryInsertSort(int[] arr) {
        int count = 0; // Counter to count number of comparisons plus shifting operation.
        for (int i = 1; i < arr.length; i++) {
            int j = i - 1;
            int key = arr[i];

            int[] results = binarySearch(arr, 0, j, key, count);
            count = results[0];
            int index = results[1];

            while (j >= index) {
                count++; // Increment counter.
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j+1] = key;
        }
        return count;
    }
    /**Merges the left and right sub array of the given array in increasing
     * order and returns a counter of basic operations*/
    private static int merge(int[]arr, int left, int middle, int right) {
        int count = 0; // Counter for the number of comparisons when merging. (Copying is not included!!)

        int size1 = middle - left + 1; //size of left sub array
        int size2 = right - middle; //Size of right sub array

        int[] arr1 = new int[size1]; //Left part of the array
        int[] arr2 = new int[size2]; //Right part of the array

        for (int i = 0; i < size1; i++) { //Copy elements to the left part
            arr1[i] = arr[left + i]; //Copy elements to the right part
        }
        for (int i = 0; i < size2; i++) {
            arr2[i] = arr[middle + 1 + i];
        }

        int i1 = 0, i2 = 0; //Initial indexes of left and right sub arrays.
        int k = left;
        while (i1 < size1 && i2 < size2) { // Merge two sub arrays into arr.
            if (arr1[i1] < arr2[i2]) { //Basic op.
                arr[k] = arr1[i1];
                i1++;
            }else {
                arr[k] = arr2[i2];
                i2++;
            }
            k++;
            count++; //Increase counter at every comparison.
        }

        while (i1 < size1) { // Copy elements that are left in left sub array
            arr[k++] = arr1[i1++];
        }

        while (i2 < size2) { // Copy elements that are left in right sub array
            arr[k++] = arr2[i2++];
        }

        return count;
    }
    /**Mergesort algorithm to sort the given array in increasing order
     * and return the number of basic operations T(n)*/
    public static int mergeSort(int[] arr, int left, int right, int count) {
        if (left < right) {
            int middle = left + (right - left) / 2;
            count = mergeSort(arr, left, middle, count); //Sort left sub array
            count = mergeSort(arr, middle + 1, right, count); //Sort right sub array
            count += merge(arr, left, middle, right); // Merge them into one array
        }
        return count;
    }
    /**Takes an array[left.....right] and returns an array of
     * basic operation count [0] and median index [1]*/
    private static int[] getMedian(int[] arr, int left, int right) {
        int middle = (left + right) / 2;
        int[] results = new int[2]; // Result for {counts, median}

        if (arr[left] > arr[middle] ^ arr[left] > arr[right]) { //Xor operation is used for better performance
            results[0] = 1; // 1 comparison is made
            results[1] = left;
        }else if (arr[right] < arr[middle] ^ arr[right] < arr[left]) {
            results[0] = 2; // 2 comparisons are made up to this line
            results[1] = right;
        }else {
            results[0] = 2; // 2 comparisons made again (it didn't change)
            results[1] = middle;
        }
        return results;
    }

    /**Swaps arr[i] and arr[j]*/
    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
    /**Hoare's Partitioning Algorithm implementation to partition an array
     * with either first element, or median of three chosen as pivot according
     * to the boolean parameter median3. Returns an array of Tn and Pivot index*/
    private static int[] partition(int[] arr, int left, int right, boolean median3) {
        int[] result = new int[2]; // returns {count, pivot position}
        int count = 0; // Counter for array comparisons.

        if (median3) { // If median of three partitioning, choose median as pivot and put it in th first entry
            int[] results = getMedian(arr, left, right);
            count += results[0];
            int pivotIndex = results[1];

            swap(arr, left, pivotIndex);
        }

        int pivot = arr[left]; // Pivot is the first element.

        int i = left;
        int j = right + 1;
        while (true) {
            do {
                j--;
                count++;
            } while (j > left && arr[j] > pivot);
            do {
                i++;
                count++;
            } while (i < right && arr[i] < pivot);

            if (i < j) {
                swap(arr, i, j);
            }else {
                break;
            }
         }

        result[0] = count;
        result[1] = j;

        swap(arr, left, j);

        return result;
    }
    /**Quicksort algorithm to sort the given array in increasing order and return
     * a basic operation counter. It becomes median of three quicksort if the
     * boolean parameter median3 is true*/
    public static int quickSort(int[] arr, int left, int right, boolean median3, int count) {
        if (left < right) {
            int[] results = partition(arr, left, right, median3); // first index is counter, second index is pivot position.
            count += results[0];
            int pivot = results[1];

            count = quickSort(arr, left, pivot - 1, median3, count); //Sort left part of pivot
            count = quickSort(arr, pivot + 1, right, median3, count); //Sort right part of pivot
        }
        return count;
    }



    /**Sorts the given arr[0...n] with inputs ranging from
     * [0....range] in increasing order using counting sort*/
    public static int countingSort(int[] arr, int range) {
        int counter = 0;

        int[] counts = new int[range + 1];
        for (int i = 0; i < arr.length; i++) { // Initialize count array
            counts[arr[i]]++;
            counter++; //Increment counter for basic operation
        }

        for (int i = 1; i < counts.length; i++) { // Make count array a cumulative count array
            counts[i] += counts[i - 1];
            counter++;
        }

        int[] result = new int[arr.length]; // Result array to hold sorted array.
        for (int i = arr.length - 1; i >= 0; i--) { //Fill the result array with sorted values.
           result[--counts[arr[i]]] = arr[i];
           counter++;
        }

        for (int i = 0; i < arr.length; i++) { // Copy the result array into the original array.
            arr[i] = result[i];
            counter++;
        }

        return counter;
    }
    /**Heapify operation takes an array[0..size-1] and an index of the array
     * and arranges a max heap with top down approach and returns a counter*/
    private static int heapify(int[] arr, int size, int i, int counter) {
        int max = i; // Max is initialized with the root
        int left = i * 2 + 1; // Left child index
        int right = i * 2 + 2; // Right child index

        if (left < size && arr[left] > arr[max]) { // If left child is greater than max, update max
            max = left;
        }
        counter++;

        if (right < size && arr[right] > arr[max]) { // If right child is greater than max
            max = right;
        }
        counter++;

        if (max != i) { // If max changes
            swap(arr, i, max); // Swap max with the larger child
            counter = heapify(arr, size, max, counter) + 1; // Heapify the corresponding subtree recursively and increment counter
        }
        return counter;
    }
    /**Heapsort algorithm to sort the given array in increasing order and return
     * a basic operation counter T(n)*/
    public static int heapSort(int[] arr) {
        int size = arr.length;
        int counter = 0;
        for (int i = size / 2 - 1; i >= 0; i--) { // Convert the array into max heap by heapify operation
            counter++;
            counter = heapify(arr, size, i, counter); // Heapify all the nodes by starting from the first parental node
        }

        for (int i = size - 1; i > 0; i--)  { // DeleteMax operation until the array is fully sorted
            swap(arr, 0, i); // Swap root(arr[0]) with arr[i]
            counter++;
            counter = heapify(arr, i, 0, counter); // Heapify root again in case the heap order property is violated.
        }
        return counter; // Return T(n)
    }




}

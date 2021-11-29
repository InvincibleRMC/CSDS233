public class Sort {

    // Main
    public static void main(String[] args) {
        test();
    }

    public static void test() {
        System.out.println("Started testing");

        // Testing methods on Ascended
        System.out.println("Ascended testing");
        mergeSort(generateAscended());
        quickSort(generateAscended());
        insertionSort(generateAscended());
        upgradedQuickSort(generateAscended(),2,3);
        int k = 0;
        int[] input = generateAscended();
        System.out.println("The " + k + "th Largest element is: " + select(input, k));
        mergeSort(input);

        // Testing methods on Descended
        System.out.println("Descended testing");
        mergeSort(generateDescended());
        quickSort(generateDescended());
        insertionSort(generateDescended());
        upgradedQuickSort(generateDescended(),2,3);
        
        input = generateDescended();
        System.out.println("The " + k + "th Largest element is: " + select(input, k));
        mergeSort(input);

        // Testing methods on unsorted arrays
        System.out.println("Unsorted testing");
        mergeSort(generateRandom());
        quickSort(generateRandom());
        insertionSort(generateRandom());
        upgradedQuickSort(generateRandom(),2,3);

        input = generateRandom();
        System.out.println("The " + k + "th Largest element is: " + select(input, k));
        mergeSort(input);
        
    }

    
    // Starts mergeSort
    public static void mergeSort(int[] input) {
        System.out.println(toString(input));
        mergeSortRecursion(input, 0, input.length - 1);
        System.out.println("Break");
        System.out.println(toString(input));
        System.out.println("Is Sorted? " + isSorted(input));
    }

    // Splits array into chunks then merges them together
    public static void mergeSortRecursion(int[] input, int l, int r) {

        if (l < r) {
            int m = l + (r - l) / 2;
            mergeSortRecursion(input, l, m);
            mergeSortRecursion(input, m + 1, r);

            merge(input, l, m, r);
        }
        return;

    }

    // Merges two arrays together
    public static void merge(int[] input, int l, int m, int r) {

        // Sizes of subarrays
        int l1 = m - l + 1;
        int l2 = r - m;

        // Temp arrays
        int L[] = new int[l1];
        int R[] = new int[l2];

        // Dump elements into the temp arrays
        for (int i = 0; i < l1; i++) {
            L[i] = input[l + i];
        }
        for (int j = 0; j < l2; j++) {
            R[j] = input[m + 1 + j];
        }

        // indicies of the temp arrays
        int i = 0, j = 0;

        // Merge the temp arrays together
        int k = l;
        while (i < l1 && j < l2) {
            if (L[i] >= R[j]) {
                input[k] = L[i];
                i++;
            } else {
                input[k] = R[j];
                j++;
            }
            k++;
        }

        // Copy any spare elements
        while (i < l1) {
            input[k] = L[i];
            i++;
            k++;
        }
        while (j < l2) {
            input[k] = R[j];
            j++;
            k++;
        }
    }

    // Starts quick sort
    public static void quickSort(int[] input) {
        System.out.println(toString(input));
        quickSortRecursion(input, 0, input.length - 1);
        System.out.println("Break");
        System.out.println(toString(input));
        System.out.println("Is Sorted? " + isSorted(input));
    }

    public static void quickSortRecursion(int[] input, int l, int h) {

        if (l < h) {
            // Generates the partition
            int pi = partition(input, l, h);

            // quicksort into smaller and smaller chunks
            quickSortRecursion(input, l, pi - 1);
            quickSortRecursion(input, pi + 1, h);
        }
    }

    // Generates the partition
    public static int partition(int[] input, int l, int h) {

        // Chose a pivot key
        int pivot = input[h];

        int i = l - 1;

        // Generates the partition
        for (int j = l; j <= h; j++) {
            if (input[j] > pivot) {
                i++;
                swap(input, i, j);
            }
        }
        swap(input, i + 1, h);
        return (i + 1);
    }

    // Swaps to elements in an array
    public static void swap(int[] input, int i, int j) {
        int temp = input[i];
        input[i] = input[j];
        input[j] = temp;
    }

    // Insertion sort
    public static void insertionSort(int[] input) {

        System.out.println(toString(input));

        // Go through all n elements
        for (int i = 1 ; i < input.length; i++) {

            int currVal = input[i];

            int j = i;
            // Shuffles elements down
            while (j > 0 &&  input[j-1] < currVal ) {
                input[j] = input[j-1];
                j--;

            }

            input[j] = currVal;
        }

        System.out.println("Break");
        System.out.println(toString(input));
        System.out.println("Is Sorted? " + isSorted(input));
    }

    // Starts upgraded quick sort
    public static void upgradedQuickSort(int[] input, int d, int k) {
        System.out.println(toString(input));
        upgradedQuickSortRecursion(input, 0, input.length - 1, 0, d, k);
        System.out.println("Break");
        System.out.println(toString(input));
        System.out.println("Is Sorted? " + isSorted(input));
    }

    // Recrusive method of upgraded Quick Sort
    public static void upgradedQuickSortRecursion(int[] input, int l, int h, int counter, int d, int k) {

        // Switch to mergesort when the counter >= depth
        if (counter >= d) {
            mergeSort(input);
        }
        // Switch to  insertion when the arrays are smaller than k
        if (input.length < k) {
            insertionSort(input);
        }

        // Standard quicksort
        if (l < h) {
            //Generate a partition
            int pi = partition(input, l, h);

            upgradedQuickSortRecursion(input, l, pi - 1, counter++, d, k);
            upgradedQuickSortRecursion(input, pi + 1, h, counter++, d, k);
        }
    }

    // Kth largest element of an array
    // Handles unreasonable inputs
    public static int select(int[] input, int k) {

        System.out.println(toString(input));
        //Switches from smallest to largest
        k = input.length-k;
        return kthLargestRecursion(input, k, input.length-1, 0);
    }

    private static int kthLargestRecursion(int[] input, int k, int r, int l) {


        // Makes sure k is reasonable
        if(k <= -1){
            k=1;
        }
        if(k>=input.length){
            k=input.length;
        }


        if (k > 0 && k <= (r - l + 1)) {
            // Generates a random partition
            int pos = randomPartition(input, l, r);

            // If chunks are equal the pivot is the kth largest
            if ((pos - l) == (k - 1)) {
                return input[pos];
            }

            //Else partition the left or right chunk
            if ((pos - l) > (k - 1)) {
                return kthLargestRecursion(input, k, pos - 1, l);
            }
            return kthLargestRecursion(input, k - pos + l - 1, r, pos + 1);
        }

        // Error Statement
        // K should never get here
        throw new Error();

    }

    // Creates a random partition
    public static int randomPartition(int[] input, int l, int r) {

        int n = r - l + 1;
        int pivot = (int) (Math.random() * (n - 1));
        swap(input, l + pivot, r);
        return partition(input, l, r);
    }

    // Generates a random array for stronger testing
    // No duplicates
    public static int[] generateRandom() {
        System.out.println("Generating arrays");
        int[] random = new int[
        (int) (Math.random() * 100)];
        for (int i = 0; i < random.length; i++) {
            random[i] = i;
        }

        while (Math.random() < 0.95) {
            swap(random, (int) (Math.random() * random.length), (int) (Math.random() * random.length));
        }

        System.out.println("Finished generating arrays");
        return random;
    }

    // Generates arrays sorted in ascended order
    public static int[] generateAscended(){
        System.out.println("Generating arrays");
        int[] random = new int[
        (int) (Math.random() * 100)];
        for (int i = 0; i < random.length; i++) {
            random[i] = i;
        }

        System.out.println("Finished generating arrays");
        return random;
    }

    // Generates arrays sorted in descend order
    public static int[] generateDescended(){
        System.out.println("Generating arrays");
        int[] random = new int[
        (int) (Math.random() * 100)];
        for (int i = 0; i < random.length; i++) {
            random[i] = random.length -i;
        }

        System.out.println("Finished generating arrays");
        return random;
    }
    // Converts an array to a string
    public static String toString(int[] input) {

        StringBuilder str = new StringBuilder();

        for (int i = 0; i < input.length; i++) {
            str.append("Index: " + i + " Key: " + input[i] + " ");
        }
        return str.toString();
    }

    // Determines wether the array is sorted in descended order.
    public static boolean isSorted(int[] input) {
        for (int i = 0; i < input.length - 1; i++) {
            if (input[i] < input[i + 1]) {
                return false;
            }
        }
        return true;
    }
}

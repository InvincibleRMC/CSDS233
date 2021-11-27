public class Sort {

    public static void main(String[] args) {
        test();
    }

    public static void test() {
        System.out.println("Started testing");

        // mergeSort(generateRandom());
        // quickSort(generateRandom());
        // insertionSort(generateRandom());
        // upgradedQuickSort(generateRandom(),2,3);

        int k = 0;
        int[] input = generateRandom();
        System.out.println("The " + k + "th Largest element is: " + select(input, k));
        mergeSort(input);
    }

    public static void mergeSort(int[] input) {
        System.out.println(toString(input));
        mergeSortRecursion(input, 0, input.length - 1);
        System.out.println("Break");
        System.out.println(toString(input));
        System.out.println("Is Sorted? " + isSorted(input));
    }

    public static void mergeSortRecursion(int[] input, int l, int r) {

        if (l < r) {
            int m = l + (r - l) / 2;
            mergeSortRecursion(input, l, m);
            mergeSortRecursion(input, m + 1, r);

            merge(input, l, m, r);
        }
        return;

    }

    public static void merge(int[] input, int l, int m, int r) {

        int l1 = m - l + 1;
        int l2 = r - m;

        int L[] = new int[l1];
        int R[] = new int[l2];

        for (int i = 0; i < l1; i++) {
            L[i] = input[l + i];
        }
        for (int j = 0; j < l2; j++) {
            R[j] = input[m + 1 + j];
        }

        int i = 0, j = 0;
        int k = l;

        while (i < l1 && j < l2) {
            if (L[i] <= R[j]) {
                input[k] = L[i];
                i++;
            } else {
                input[k] = R[j];
                j++;
            }
            k++;
        }

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

    public static void quickSort(int[] input) {
        System.out.println(toString(input));
        quickSortRecursion(input, 0, input.length - 1);
        System.out.println("Break");
        System.out.println(toString(input));
        System.out.println("Is Sorted? " + isSorted(input));
    }

    public static void quickSortRecursion(int[] input, int l, int h) {

        if (l < h) {
            int pi = partition(input, l, h);

            quickSortRecursion(input, l, pi - 1);
            quickSortRecursion(input, pi + 1, h);
        }
    }

    public static int partition(int[] input, int l, int h) {
        int pivot = input[h];

        int i = l - 1;

        for (int j = l; j <= h; j++) {
            if (input[j] < pivot) {
                i++;
                swap(input, i, j);
            }
        }
        swap(input, i + 1, h);
        return (i + 1);
    }

    public static void swap(int[] input, int i, int j) {
        int temp = input[i];
        input[i] = input[j];
        input[j] = temp;
    }

    public static void insertionSort(int[] input) {

        System.out.println(toString(input));

        for (int i = 1; i < input.length; i++) {

            int currVal = input[i];

            int j = i - 1;
            while (j >= 0 && currVal < input[j]) {
                input[j + 1] = input[j];
                j--;

            }

            input[j + 1] = currVal;
        }

        System.out.println("Break");
        System.out.println(toString(input));
        System.out.println("Is Sorted? " + isSorted(input));
    }

    public static void upgradedQuickSort(int[] input, int d, int k) {
        System.out.println(toString(input));
        upgradedQuickSortRecursion(input, 0, input.length - 1, 0, d, k);
        System.out.println("Break");
        System.out.println(toString(input));
        System.out.println("Is Sorted? " + isSorted(input));
    }

    public static void upgradedQuickSortRecursion(int[] input, int l, int h, int counter, int d, int k) {

        if (counter >= d) {
            mergeSort(input);
        }
        if (input.length < k) {
            insertionSort(input);
        }

        if (l < h) {
            int pi = partition(input, l, h);

            upgradedQuickSortRecursion(input, l, pi - 1, counter++, d, k);
            upgradedQuickSortRecursion(input, pi + 1, h, counter++, d, k);
        }
    }

    public static int select(int[] input, int k) {

        System.out.println(toString(input));
        //Switches from smallest to largest
        k = input.length-k;
        return kthLargestRecursion(input, k, input.length-1, 0);
    }

    public static int kthLargestRecursion(int[] input, int k, int r, int l) {


        // Makes sure k is reasonable
        if(k <= -1){
            k=1;
        }
        if(k>=input.length){
            k=input.length;
        }


        if (k > 0 && k <= (r - l + 1)) {
            int pos = randomPartition(input, l, r);

            if ((pos - l) == (k - 1)) {
                return input[pos];
            }

            if ((pos - l) > (k - 1)) {
                return kthLargestRecursion(input, k, pos - 1, l);
            }
            return kthLargestRecursion(input, k - pos + l - 1, r, pos + 1);
        }

        // Error Statement
        return Integer.MAX_VALUE;

    }

    // Creates a random partition
    public static int randomPartition(int[] input, int l, int r) {

        int n = r - l + 1;
        int pivot = (int) (Math.random() * (n - 1));
        swap(input, l + pivot, r);
        return partition(input, l, r);
    }

    public static int[] generateRandom() {
        System.out.println("Generating arrays");
        int[] random = new int[
        //(int) (Math.random() * 100)
        10
        ];
        for (int i = 0; i < random.length; i++) {
            random[i] = i;
        }

        while (Math.random() < 0.95) {
            swap(random, (int) (Math.random() * random.length), (int) (Math.random() * random.length));
        }

        System.out.println("Finished generating arrays");
        return random;
    }

    public static String toString(int[] input) {

        StringBuilder str = new StringBuilder();

        for (int i = 0; i < input.length; i++) {
            str.append("Index: " + i + " Key: " + input[i] + " ");
        }
        return str.toString();
    }

    public static boolean isSorted(int[] input) {
        for (int i = 0; i < input.length - 1; i++) {
            if (input[i] < input[i + 1]) {
                return false;
            }
        }
        return true;
    }

    /*
    public static boolean isSorted(int[] input, int count) {
        for (int i = 0; i < count; i++) {
            if (input[i] < input[i + 1]) {
                return false;
            }
        }
        return true;
    }
    */
}

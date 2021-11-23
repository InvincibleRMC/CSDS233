public class Sort {

    public static void main(String[] args) {
        test();
    }

    public static void test() {
        System.out.println("Started testing");

        mergeSort(generateRandom());
        // quickSort();
        insertionSort(generateRandom());

        // upgradedQuickSort();
        // select();
    }

    public static void mergeSort(int[] input){
        System.out.println(toString(input));

        
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

    public static int[] generateRandom() {
        System.out.println("Generating arrays");
        int[] random = new int[
        // (int) (Math.random() * 100)
        10];
        for (int i = 0; i < random.length; i++) {
            random[i] = (int) (Math.random() * 10);
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
            if (input[i] > input[i + 1]) {
                return false;
            }
        }
        return true;
    }

    public static boolean isSorted(int[] input, int count) {
        for (int i = 0; i < count; i++) {
            if (input[i] > input[i + 1]) {
                return false;
            }
        }
        return true;
    }
}

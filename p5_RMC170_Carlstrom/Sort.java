public class Sort {

    public static void main(String[] args) {
        test();
    }

    public static void test() {
        System.out.println("Started testing");
        Sort sort = new Sort();

        // mergeSort();
        // quickSort();
        sort.insertionSort(generateRandom());

        // upgradedQuickSort();
        // select();
    }

    public Sort() {

    }

    public void insertionSort(int[] input) {

        System.out.println(toString(input));

        for (int i = 0; i < input.length; i++) {

            int currVal = input[i];
            int j = i;
            while (currVal < input[j]) {
                j--;
            }
            int temp = input[j];
            input[j] = currVal;
            input[i] = temp;
        }
        
        System.out.println("Break");
        System.out.println(toString(input));
        System.out.println("Is Sorted? " + isSorted(input));
    }

    public static int[] generateRandom() {
        System.out.println("Generating arrays");
        int[] random = new int[(int) (Math.random() * 100)];
        for (int i = 0; i < random.length; i++) {
            random[i] = i;
        }

        System.out.println("Finished generating arrays");
        return random;
    }

    public String toString(int[] input) {

        StringBuilder str = new StringBuilder();

        for (int i = 0; i < input.length; i++) {
            str.append("Index: " + i + " Key: " + input[i] + " ");
        }
        return str.toString();
    }

    public boolean isSorted(int[] input) {
        for (int i = 0; i < input.length - 1; i++) {
            if (input[i] > input[i + 1]) {
                return false;
            }
        }
        return true;
    }
}

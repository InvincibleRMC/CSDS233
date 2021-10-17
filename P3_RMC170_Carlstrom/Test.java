package P3_RMC170_Carlstrom;

public class Test {

    public static void main(String[] args) {
        test();
    }

    private static void test() {
        BinarySearchTree tree = new BinarySearchTree();
        tree.insert(25);
        // tree contains 25

        for (int i = 0; i < 50; i=i+4) {
            tree.insert(i);
        }
        // tree contains 0, 4, 8, 12, 16, 20, 24, 25, 28, 32, 36, 40, 44, 48

        // Traversal Testing
        System.out.println("Showing Traversal Methods");
        System.out.println("Preorder:  " + tree.toStringPreorder());
        System.out.println("Postorder: " + tree.toStringPostorder());
        System.out.println("Inorder:   " + tree.toStringInorder());

        // Search Testing
        System.out.println("Search testing");
        if (tree.search(3)) {
            throw new AssertionError("Unexpected 3 found");
        }
        if (!tree.search(4)) {
            throw new AssertionError("Expected 4 missing");
        }

        // Deletion Testing
        System.out.println("Deletion Testing of a Node");
        tree.delete(4);
        // tree contains 0, 8, 12, 16, 20, 24, 25, 28, 32, 36, 40, 44, 48
        System.out.println(tree.toStringInorder());
        if (tree.search(4)) {
            throw new AssertionError("Unexpected 4 found");
        }

        System.out.println("Deletion Testing of the head of the tree");
        tree.delete(25);
        // tree contains 0, 8, 12, 16, 20, 24, 28, 32, 36, 40, 44, 48
        System.out.println(tree.toStringPreorder());
        if (tree.search(25)) {
            throw new AssertionError("Unexpected 25 found");
        }

        // Sum Testing
        System.out.println("Sum Testing");
        System.out.println(tree.toStringPreorder());
        int before = tree.sum();
        System.out.println(before);
        tree.delete(8);
        // tree contains 0, 12, 16, 20, 24, 28, 32, 36, 40, 44, 48
        int after = tree.sum();
        System.out.println(after);
        if ((before - after) != 8) {
            throw new AssertionError("problem with sum");
        }

        // kth Smallest Testing
        int k = 7;
        System.out.println("kth Smallest Testing");
        System.out.println(tree.toStringInorder());
        if (tree.kthSmallest(0) != null) {
            throw new AssertionError("Expected not to find 0th smallest");
        }
        int kthSmallest = tree.kthSmallest(k);
        if (kthSmallest != 32) {
            throw new AssertionError("Unexpected kthSmallest found");
        }
        if (tree.kthSmallest(100) != null) {
            throw new AssertionError("Expected not to find 100th smallest");
        }
    }
}

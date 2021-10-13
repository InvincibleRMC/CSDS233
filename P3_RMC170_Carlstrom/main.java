package P3_RMC170_Carlstrom;

public class main {

    public static void main(String[] args) {
        BinarySearchTree tree = new BinarySearchTree(25);

        Node root =tree.getHead();
        for (int i = 0; i < 50; i=i+4) {
            
            tree.insert(root, i);
        }



        //Traversal Testing
        System.out.println("Showing Traversal Methods");
        System.out.println(tree.toStringPreorder());
        System.out.println(tree.toStringPostorder());
        System.out.println(tree.toStringInorder());

        //Search Testing
        System.out.println("Search testing");    
        System.out.println(tree.search(root,3));
        System.out.println(tree.search(root,4));

        //Deletion Testing
        System.out.println("Deletion Testing of a Node");
        System.out.println(tree.delete(root, 4));
        System.out.println(tree.toStringInorder());
        System.out.println(tree.search(root,4));

        System.out.println("Deletion Testing of the head of the tree");
        System.out.println(tree.delete(root, 25));
        System.out.println(tree.toStringPreorder());

        //Sum Testing
        System.out.println("Sum Testing");
        System.out.println(tree.toStringPreorder());
        System.out.println(tree.sum(root));
        tree.delete(root, 8);
        System.out.println(tree.sum(root));

        //kth Smallest Testing
        int k = 7;
        System.out.println("kth Smallest Testing");
        System.out.println(tree.toStringInorder());
        System.out.println(tree.kthSmallest(root, k));



    }
}

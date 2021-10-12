package P3_RMC170_Carlstrom;

public class main {

    public static void main(String[] args) {
        BinarySearchTree tree = new BinarySearchTree(25);

        for (int i = 0; i < 50; i = i + 4) {
            System.out.println(tree.insert(new Node(i), i));
        }

    }
}

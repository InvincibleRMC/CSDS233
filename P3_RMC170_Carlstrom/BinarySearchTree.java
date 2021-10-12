package P3_RMC170_Carlstrom;

public class BinarySearchTree {

    private Node head;

    // standard constructor
    public BinarySearchTree(int key) {

        head = new Node(null, null, key);

    }

    public Node insert(Node root, int key) {

        Node parent = null;
        Node trav = head;

        while (trav != null) {
            parent = trav;
            if (key < trav.getKey())
                trav = trav.getLeft();
            else
                trav = trav.getRight();
        }

        if (parent == null)
            head = root;
        else if (key < parent.getKey())
            parent.setLeft(root);
        else
            parent.setRight(root);

        return root;
    }
}

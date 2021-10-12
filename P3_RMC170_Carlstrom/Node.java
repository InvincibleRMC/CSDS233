package P3_RMC170_Carlstrom;

public class Node {

    // Node properties
    private Node right;
    private Node left;
    private int key;

    // Node Object
    public Node(Node left, Node right, int key) {

        this.left = left;
        this.right = right;
        this.key = key;

    }

    // Node Object without children
    public Node(int key) {

        left = null;
        right = null;
        this.key = key;

    }

    // Node getters
    public Node getRight() {
        return right;
    }

    public Node getLeft() {
        return left;
    }

    public int getKey() {
        return key;
    }

    // Node setters
    public void setLeft(Node newLeft) {
        left = newLeft;
    }

    public void setRight(Node newRight) {
        right = newRight;
    }

    public void setKey(int newKey) {
        key = newKey;
    }

    public String toString() {
        return "key: " + key;
    }

}

package P3_RMC170_Carlstrom;

import java.util.Stack;

public class BinarySearchTree {

    private Node head;

    /**
     * Default constructor to create an empty tree
     */
    public BinarySearchTree() {
    }

    // Insertation
    public void insert(int key) {
        head = insert(head, key);
    }

    /**
     * Inserts a node into a tree rooted at root, which may be
     * null. Returns the root, which maybe new.
     */
    private static Node insert(Node root, int key) {
        // Empty tree, return the new root.
        if (root == null) {
            return new Node(key);
        }

        Node parent = null;
        Node trav = root;

        while (trav != null) {
            parent = trav;
            if (key < trav.getKey()) {
                trav = trav.getLeft();
            } else {
                trav = trav.getRight();
            }
        }
        if (key < parent.getKey()) {
            parent.setLeft(new Node(key));
        } else {
            parent.setRight(new Node(key));
        }
        // Non-empty tree, just return the old root.
        return root;
    }

    // Search with the head
    public boolean search(int key) {
        return (search(head, key) != null);
    }

    // Generic search
    private static Node search(Node root, int key) {
        Node trav = root;
        while (trav != null) {
            if (key < trav.getKey()) {
                trav = trav.getLeft();
            } else if (key > trav.getKey()) {
                trav = trav.getRight();
            } else { // must be ==
                return trav;
            }
        }
        return null;
    }

    // Delete with the head
    public void delete(int key) {
        head = delete(head, key);
    }

    /**
     * Deletes a node from a tree rooted at root, which may be
     * null. Returns the root, which may have become null.
     */
    private static Node delete(Node root, int key) {

        // search to find find pointers to key node and its parent, if any
        Node parent = null;
        Node trav = root;
        while (trav != null && trav.getKey() != key) {
            parent = trav;
            if (key < trav.getKey()) {
                trav = trav.getLeft();
            } else if (key > trav.getKey()) {
                trav = trav.getRight();
            }
        }
        // key wasn't found, just return existing root
        if (trav == null) {
            return root;
        }

        // Case of node with no children
        if (trav.getLeft() == null && trav.getRight() == null) {
            // if the node is the root, return null as the new root.
            if (trav == root) {
                return null;
            }
            // otherwise null out the appropriate child in the parent to remove it
            if (parent.getLeft() == trav) {
                parent.setLeft(null);
            } else {
                parent.setRight(null);
            }
            return root;
        }

        // Case of node with 2 children
        if (trav.getLeft() != null && trav.getRight() != null) {
            // find logical successor
            Node succesor = findMinimum(trav.getLeft());
            // save the value
            int tmp = succesor.getKey();
            // recusively delete
            delete(root, tmp);
            // restore the saved value
            trav.setKey(tmp);
            return root;
        }

        // Case of node with 1 child
        Node child = (trav.getLeft() != null) ? trav.getLeft() : trav.getRight();
        // we are at the root, replace the root with the child
        if (trav == root) {
            return child;
        }
        // replace the current node with child in the parent
        if (trav == parent.getLeft()) {
            parent.setLeft(child);
        } else {
            parent.setRight(child);
        }
        return root;
    }

    /**
     * Finds the node with the minimum value rooted at trav
     */
    private static Node findMinimum(Node trav) {
        while (trav.getRight() != null) {
            trav = trav.getRight();
        }
        return trav;
    }

    // Finds sum for head
    public int sum() {
        return sum(head);
    }

    // Finds sum of any node
    private static int sum(Node root) {
        if (root == null) {
            return 0;
        }

        Stack<Node> nodeStack = new Stack<Node>();
        Node myNode = root;

        int sum = 0;
        while (myNode != null || nodeStack.size() > 0) {

            while (myNode != null) {
                nodeStack.push(myNode);
                myNode = myNode.getLeft();
            }
            myNode = nodeStack.pop();
            sum += myNode.getKey();
            myNode = myNode.getRight();
        }
        return sum;
    }

    // Note these methods are strange and only exist because the prompt requests them
    // toStringInorder() is more logical
    // Inorder traversal with head
    public void inorder() {
        inorder(head);
    }
    // Generic Inorder traversal
    private static void inorder(Node root) {
        if (root == null) {
            return;
        }

        Stack<Node> nodeStack = new Stack<Node>();
        Node myNode = root;

        while (myNode != null || nodeStack.size() > 0) {
            while (myNode != null) {
                nodeStack.push(myNode);
                myNode = myNode.getLeft();
            }
            myNode = nodeStack.pop();
            myNode = myNode.getRight();
        }
    }

    // Returns the key of the kth smallest node 
    public Integer kthSmallest(int k) {
        if (k <= 0) {
            return null;
        }
        Node trav = kthSmallest(head, k);
        if (trav == null) {
            return null;
        }
        return trav.getKey();
    }

    // finds the kth smallest node
    private static Node kthSmallest(Node root, int k) {
        if (k > getSize(root)) {
            return null;
        }

        Stack<Node> nodeStack = new Stack<Node>();
        Node myNode = root;

        int counter = 0;

        while (myNode != null || nodeStack.size() > 0) {

            while (myNode != null) {
                nodeStack.push(myNode);
                myNode = myNode.getLeft();
            }
            myNode = nodeStack.pop();
            counter++;
            if (counter >= k){
                return myNode;
            }

            myNode = myNode.getRight();
        }

        return myNode;
    }

    // gets the size of the tree.
    private static int getSize(Node root) {
        if (root == null) {
            return 0;
        }

        int counter = 0;
        Stack<Node> nodeStack = new Stack<Node>();
        Node myNode = root;
        while (myNode != null || nodeStack.size() > 0) {
            while (myNode != null) {
                nodeStack.push(myNode);
                myNode = myNode.getLeft();
            }
            myNode = nodeStack.pop();
            counter++;
            myNode = myNode.getRight();

        }
        return counter;
    }

    // returns the tree in a String traversed by pre-order
    public String toStringPreorder() {

        if (head == null) {
            return "empty tree";
        }

        Stack<Node> nodeStack = new Stack<Node>();
        nodeStack.push(head);

        StringBuilder str = new StringBuilder();

        str.append("{");

        while (nodeStack.empty() == false) {

            Node myNode = nodeStack.peek();

            str.append(myNode + " ");
            nodeStack.pop();

            if (myNode.getRight() != null) {
                nodeStack.push(myNode.getRight());
            }
            if (myNode.getLeft() != null) {
                nodeStack.push(myNode.getLeft());
            }

        }

        str.append("}");

        return str.toString();
    }

    // returns the tree in a String traversed by post-order
    public String toStringPostorder() {

        if (head == null) {
            return "empty tree";
        }

        Stack<Node> s1 = new Stack<Node>();
        Stack<Node> s2 = new Stack<Node>();
        s1.push(head);

        StringBuilder str = new StringBuilder();

        str.append("{");

        while (s1.empty() == false) {

            Node temp = s1.pop();
            s2.push(temp);

            if (temp.getLeft() != null) {
                s1.push(temp.getLeft());
            }
            if (temp.getRight() != null) {
                s1.push(temp.getRight());
            }

        }
        while (s2.empty() == false) {
            Node temp = s2.pop();
            str.append(temp + " ");
        }

        str.append("}");

        return str.toString();
    }

    // returns the tree in a String traversed by In-order
    public String toStringInorder() {

        if (head == null) {
            return "empty tree";
        }

        Stack<Node> nodeStack = new Stack<Node>();
        Node myNode = head;

        StringBuilder str = new StringBuilder();

        str.append("{");

        while (myNode != null || nodeStack.size() > 0) {

            while (myNode != null) {
                nodeStack.push(myNode);
                myNode = myNode.getLeft();
            }
            myNode = nodeStack.pop();
            str.append(myNode + " ");

            myNode = myNode.getRight();

        }
        str.append("}");

        return str.toString();
    }

}

package P3_RMC170_Carlstrom;

import java.util.Stack;

public class BinarySearchTree {

    private Node head;

    // standard constructor
    public BinarySearchTree(int key) {

        head = new Node(null, null, key);

    }

    public Node getHead() {
        return head;
    }

    // Finds the node directly before the current Node
    public Node getMinimumKey(Node trav) {
        while (trav.getRight() != null) {
            trav = trav.getRight();
        }
        return trav;
    }

    private int getSize() {
        if (head == null) {
            return 0;
        }

        int counter = 0;
        Stack<Node> nodeStack = new Stack<Node>();
        Node myNode = head;

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

    

    public Node insert(Node root, int key) {

        Node parent = null;
        Node trav = root;

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
            parent.setLeft(new Node(key));
        else
            parent.setRight(new Node(key));

        return root;
    }

    public Node search(Node root, int key) {

        Node trav = root;

        while (trav != null) {

            if (key < trav.getKey())
                trav = trav.getLeft();
            else if (key > trav.getKey())
                trav = trav.getRight();
            else
                return trav;

        }

        return null;
    }

    public Node delete(Node root, int key) {

        Node parent = null;
        Node trav = root;

        while (trav != null && trav.getKey() != key) {

            parent = trav;
            if (key < trav.getKey())
                trav = trav.getLeft();
            else if (key > trav.getKey())
                trav = trav.getRight();

        }

        if (trav == null) {
            return null;
        }

        return deletionLogic(parent, trav);

    }

    public Node deletionLogic(Node parent, Node trav) {

        // Node has no children
        if (trav.getLeft() == null && trav.getRight() == null) {

            if (trav != head) {
                if (parent.getLeft() == trav) {
                    parent.setLeft();
                } else {
                    parent.setRight();
                }
            }

            // Case for a tree with one element
            else {
                head = null;
            }
        }

        // Node has 2 children case
        else if (trav.getLeft() != null && trav.getRight() != null) {

            Node succesor = getMinimumKey(trav.getLeft());

            int val = succesor.getKey();

            delete(head, val);

            trav.setKey(val);

        }

        // Node has 1 child case
        else {

            Node child = (trav.getLeft() != null) ? trav.getLeft() : trav.getRight();

            if (trav != head) {
                if (trav == parent.getLeft()) {
                    parent.setLeft(child);
                } else {
                    parent.setRight(child);
                }
            }
            // When deleting the head of the tree with one child
            else {
                head = child;
            }

        }

        return trav;
    }

    public int sum(Node root) {

        if (head == null) {
            return (Integer) null;
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

    public void inorder(Node root) {

        if (head == null) {
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

    public Node kthSmallest(Node root, int k) {

        if (head == null) {
            
            return null;
        }
        if (k > getSize()) {
            
            return null;
        }

        Stack<Node> nodeStack = new Stack<Node>();
        Node myNode = root;

        int counter =0 ;

        while (myNode != null || nodeStack.size() > 0) {

            while (myNode != null) {
                nodeStack.push(myNode);
                myNode = myNode.getLeft();
            }
            myNode = nodeStack.pop();
            counter++;
            if(counter >= k){
                return myNode;
            }

            myNode = myNode.getRight();
            
            }

         return myNode;
    }

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

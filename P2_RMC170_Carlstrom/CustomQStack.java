package P2_RMC170_Carlstrom;

import java.util.Queue;
import java.util.Stack;
import java.util.LinkedList;

public class CustomQStack<E> {

    private Queue<E> queue;

    // default constructor
    public CustomQStack() {
        queue = new LinkedList<E>();
    }

    // returns true if Stack is empty
    public boolean isEmpty() {
        return queue.peek() == null;

    }

    // Pops the tail off the stack
    public E pop() {

        reverseQueue();
        E removedElement = queue.poll();
        reverseQueue();

        return removedElement;
    }

    // reverses the queue
    public void reverseQueue() {

        Stack<E> stack = new Stack<E>();

        while (!queue.isEmpty()) {
            stack.push(queue.poll());
        }

        while (!stack.isEmpty()) {
            queue.add(stack.pop());
        }

    }

    //pushes an element onto the stack
    public void push(E e) {
        queue.add(e);
    }

    //basic toString method for the Stack
    public String toString() {

        //default case
        if (isEmpty()) {
            return "[]";
        }

        StringBuilder s = new StringBuilder("[");
        Queue<E> saved = (Queue<E>) ((LinkedList<E>) queue).clone();

        reverseQueue();

        while (!isEmpty()) {
            s.append(pop() + ", ");
        }

        //restores the queue
        queue = saved;

        return s.replace(s.length() - 2, s.length(), "]").toString();
    }

}
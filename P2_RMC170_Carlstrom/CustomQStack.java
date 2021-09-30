package P2_RMC170_Carlstrom;

import java.util.Queue;
import java.util.Stack;
import java.util.LinkedList;

public class CustomQStack<E> {

    private Queue<E> queue;

    public CustomQStack() {
        queue = new LinkedList<E>();
    }

    public boolean isEmpty() {

        return queue.peek() == null;

    }

    public E pop() {

        reverseQueue();
        E removedElement = queue.poll();
        reverseQueue();

        return removedElement;
    }

    public void reverseQueue() {

        Stack<E> stack = new Stack<E>();

        while (!queue.isEmpty()) {
            stack.push(queue.poll());
        }

        while (!stack.isEmpty()) {
            queue.add(stack.pop());
        }

    }

    public void push(E e) {
        queue.add(e);
    }

    public String toString() {

        if (isEmpty()) {
            return "[]";
        }

        StringBuilder s = new StringBuilder("[");

        Queue<E> saved = (Queue<E>) ((LinkedList<E>) queue).clone();

        reverseQueue();

        while (!isEmpty()) {
            s.append(pop() + ", ");
        }

        queue = saved;

        return s.replace(s.length() - 2, s.length(), "]").toString();
    }

}
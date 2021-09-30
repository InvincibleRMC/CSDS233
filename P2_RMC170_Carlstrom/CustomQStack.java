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

        if(isEmpty()){
            return null;
        }

        Queue<E> temp = new LinkedList<E>();
        temp = (Queue<E>) ((LinkedList<E>) queue).clone();
        temp.poll();
        if(temp.peek()==null){
            return queue.poll();
        }

        E startingHead = queue.peek();
        
        do{

            shuffle();
            temp = (Queue<E>) ((LinkedList<E>) queue).clone();
            temp.poll();
           
        }
        while(temp.peek() != startingHead);
        return queue.poll();
    }
    
    public void shuffle(){
        queue.add(queue.poll());
    }

    // pushes an element onto the stack
    public E push(E e) {

        /*
        if (isEmpty()) {
            queue.add(e);
            return e;
        }
        E head = queue.peek();

        do {
            queue.add(queue.poll());
        } while (head != queue.peek());
        */
        queue.add(e);
        return e;
    }

    // basic toString method for the Stack
    public String toString() {

        return queue.toString();
    }

}
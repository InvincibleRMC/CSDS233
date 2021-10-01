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

        // base case for an empty Queue
        if(isEmpty()){
            return null;
        }


        // case for a Queue with one element
        Queue<E> temp = new LinkedList<E>();
        temp = (Queue<E>) ((LinkedList<E>) queue).clone();
        temp.poll();
        if(temp.peek()==null){
            return queue.poll();
        }

        // case for a Queue with n > 1 elememts
        E startingHead = queue.peek();
        do{

            shuffle();
            temp = (Queue<E>) ((LinkedList<E>) queue).clone();
            temp.poll();
           
        }
        while(temp.peek() != startingHead);
        return queue.poll();
    }
    
    // shuffles the queue by 1
    public void shuffle(){
        queue.add(queue.poll());
    }

    // pushes an element onto the stack
    public E push(E e) {
        queue.add(e);
        return e;
    }

    // basic toString method for the Stack
    public String toString() {
        return queue.toString();
    }

}
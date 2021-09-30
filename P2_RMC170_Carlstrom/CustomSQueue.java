package P2_RMC170_Carlstrom;

import java.util.Stack;

public class CustomSQueue<E> {
    private Stack<E> stack1,stack2;

    public CustomSQueue() {

        stack1 = new Stack<E>();
        stack2 = new Stack<E>();;

    }

    // Inserts at tail
    // returns true if a sucsess
    public boolean add(E e) {
        if (e != null) {
            stack1.push(e);
            return true;
        }
        return false;
    }

    // removes E from header and returns it
    public E poll() {

        E removedElement;

        while (!stack1.empty()) {
            stack2.push(stack1.pop());
        }

        
        removedElement = stack2.pop();

        while (!stack2.empty()) {
            stack1.push(stack2.pop());
        }

    
        
        return removedElement;
    }

    public String toString(){

        StringBuilder s = new StringBuilder("[");

        
        Stack<E> old = new Stack<E>();
        old = (Stack<E>) stack1.clone();

        while(!stack1.empty()){
            s.append(poll()+", ");
        }

        stack1=old;
        return s.replace(s.length()-2,s.length(),"]").toString();
    }

}

package P2_RMC170_Carlstrom;

import java.util.Queue;
import java.util.LinkedList;
import javax.lang.model.element.Element;

public class CustomQStack<E> implements Queue<E> {

    private Queue<E> stack; 
    

    public CustomQStack() {
        stack = new LinkedList<E>();
    }

    public boolean isEmpty() {

        return stack == null;

    }
    /*
     * public E pop(){
     * 
     * while(stack.next != null){ return stack; }
     * 
     * 
     * }
     */

    public void push(E e) {
        stack.add(e);

    }

    public static void main(String[] args) {

        CustomQStack<Integer> customStack = new CustomQStack<Integer>();

        customStack.push(1);
        customStack.push(2);
        customStack.push(3);

    }
}
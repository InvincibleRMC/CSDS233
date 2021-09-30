package P2_RMC170_Carlstrom;

public class ReverseLinkList<E> {

    private E element;
    private ReverseLinkList<E> next;

    // reverseLinkList constructor
    public ReverseLinkList(E element) {
        setElement(element);
        this.next = null;
    }

    // getter for E
    public E getElement() {
        return element;
    }

    // setter for E
    public void setElement(E element) {
        this.element = element;
    }

    // attaches element to end of the linked list
    public void append(E element) {
        append(this, element);
    }

    // attaches an element to the end of the linked list
    public static <E> ReverseLinkList<E> append(ReverseLinkList<E> list, E element) {

        ReverseLinkList<E> previous = null;
        ReverseLinkList<E> tail = list;

        // goes to the end to attach element
        while (tail != null) {
            previous = tail;
            tail = tail.next;
        }

        // create new end element
        ReverseLinkList<E> end = new ReverseLinkList<E>(element);
        if (previous == null) {
            return end;
        }
        // set end to be null
        previous.next = end;
        return list;
    }

    // reverse linked list
    public void reverse() {

        // saved for later, see below.
        ReverseLinkList<E> second = this.next;
        // null cases
        if (second == null) {
            return;
        }

        // old and reversed linked list creation
        ReverseLinkList<E> old = this;
        ReverseLinkList<E> reversed = null;

        // proceed through old and flip the pointers within old
        while (old != null) {
            ReverseLinkList<E> node = old;
            old = old.next;
            node.next = reversed;
            reversed = node;
        }

        // linked lists need for header swap
        ReverseLinkList<E> nextToLast = second;
        ReverseLinkList<E> newLast = reversed;
        ReverseLinkList<E> newSecond = reversed.next;

        // now the pointer swap

        // terminate the list
        newLast.next = null;
        // attach to the end
        nextToLast.next = newLast;
        // attach to the new head;
        this.next = newSecond;
        // now the value head/tail values
        E tmp = this.getElement();
        this.setElement(newLast.getElement());
        newLast.setElement(tmp);
    }

    // to string method for the reverse linked list
    public String toString() {
        // create string builder
        StringBuilder stringBuilder = new StringBuilder("[");
        // makes duplicate
        ReverseLinkList<E> list = this;

        // proceed throught the linked list and creates string from elements
        while (list != null) {
            stringBuilder.append(String.valueOf(list.getElement()));
            if (list.next != null) {
                stringBuilder.append(", ");
            }
            list = list.next;
        }

        stringBuilder.append(']');
        // returns string
        return stringBuilder.toString();
    }

}
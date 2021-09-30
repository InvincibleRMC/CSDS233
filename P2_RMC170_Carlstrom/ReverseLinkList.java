package P2_RMC170_Carlstrom;

/**
 * javac -Xlint ReverseLinkList.java && java ReverseLinkList
 */
public class ReverseLinkList<E> {
    private E element;
    private ReverseLinkList<E> next;

    /**
     * Make a new ReverseLinkList contaning element e to start.
     */
    public ReverseLinkList(E element) {
        setElement(element);
        this.next = null;
    }

    public E getElement() {
        return this.element;
    }

    public void setElement(E element) {
        this.element = element;
    }

    /**
     * Pushes a new value on the front of the ReverseLinkList, returning the new head of the ReverseLinkList.
     */
    public ReverseLinkList<E> push(E element) {
        ReverseLinkList<E> ReverseLinkList = new ReverseLinkList<E>(element);
        ReverseLinkList.next = this;
        return ReverseLinkList;
    }

    /**
     * Appends a new value to the end of the ReverseLinkList.
     */
    public void append(E element) {
        append(this, element);
    }

    /**
     * Appends a new value to the end of the provided ReverseLinkList, which may be null.
     */
    public static <E> ReverseLinkList<E> append(ReverseLinkList<E> ReverseLinkList, E element) {
        // try to find the end, we set previous to null to start and
        // if it remains null, we know we had an empty "null" ReverseLinkList to
        // start
        ReverseLinkList<E> previous = null;
        ReverseLinkList<E> tail = ReverseLinkList;
        while (tail != null) {
            previous = tail;
            tail = tail.next;
        }

        // uncondintionally create the new element
        ReverseLinkList<E> end = new ReverseLinkList<E>(element);
        if (previous == null) {
            return end; // this is the only one, and its the new ReverseLinkList
        }
        previous.next = end;
        return ReverseLinkList; // return original ReverseLinkList.
    }

    public String reverseToString() {

        String reversedLinkedList = "]";
        ReverseLinkList<E> reverseLinkList = this;

        if(reverseLinkList == null){
            return "[]";
        }

        while (reverseLinkList.next != null) {
            reversedLinkedList = ", "+String.valueOf(reverseLinkList.getElement()) + reversedLinkedList;
            reverseLinkList = reverseLinkList.next;
        }
        return "[" +reverseLinkList.getElement()+ reversedLinkedList ;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("[");
        ReverseLinkList<E> ReverseLinkList = this;
        while (ReverseLinkList != null) {
            stringBuilder.append(String.valueOf(ReverseLinkList.getElement()));
            if (ReverseLinkList.next != null) {
                stringBuilder.append(", ");
            }
            ReverseLinkList = ReverseLinkList.next;
        }
        stringBuilder.append(']');
        return stringBuilder.toString();
    }

    public static final void main(String[] args) {
        ReverseLinkList<Integer> lst = new ReverseLinkList<Integer>(9).push(13).push(56).push(37).push(44);
        System.out.println(lst.toString());
        
        System.out.println(lst.reverseToString());
    }
}
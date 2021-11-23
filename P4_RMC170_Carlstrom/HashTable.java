package P4_RMC170_Carlstrom;

public class HashTable {

    // Entry Class
    // Linked List
    public class Entry {
        // Instaniate Variables
        private Object key;
        private Object value;
        public Entry next;

        // Basic Constructor
        public Entry(Object key, Object value) {
            this.key = key;
            this.value = value;
        }

        // Getters
        public Object getKey() {
            return key;
        }
        public Object getValue() {
            return value;
        }

        // Setters
        public void setValue(Object value) {
            this.value = value;
        }

        // toString() method
        public String toString() {
            return "Key: " + key + " Value: " + value;
        }

    }

    // Instaniate variables
    private Entry[] table;
    private int tableSize;
    private static final double LOAD_FACTOR = 0.75;
    private int elementCount;

    // Constructs a HashTable with a min size of 1
    public HashTable(int size) {
        if (size <= 0) {
            size = 1; // has to be at least one to allow rehashing
        }
        this.tableSize = size;
        this.table = new Entry[size];
        this.elementCount = 0;
    }

    // A rehash function for when the LOAD_FACTOR is exceeded
    private void rehash() {
        Entry[] oldTable = this.table;
        this.tableSize = this.tableSize * 2;
        this.table = new Entry[tableSize];
        this.elementCount = 0; // since put() below will recount

        // Dumps all the elements into the new table
        for (int i = 0; i < oldTable.length; i++) {
            Entry temp = oldTable[i];
            while (temp != null) {
                put(temp.getKey(), temp.getValue());
                temp = temp.next;
            }
        }
    }

    // Gets an entry
    public Object get(Object key) {
        int h = calculateHashCode(key);
        Entry e = getEntry(h, key);
        if (e != null) {
            return e.getValue();
        }
        return null;
    }

    // Inserts an Entry
    public Object put(Object key, Object value) {
        int h = calculateHashCode(key);
        Entry e = getEntry(h, key);
        if (e != null) {
            Object oldValue = e.getValue();
            e.setValue(value);
            return oldValue;
        }
        // no entry found, add one to head of list
        Entry toBeAdded = new Entry(key, value);
        Entry head = table[h];
        toBeAdded.next = head;
        table[h] = toBeAdded;
        elementCount++;
        // automatically rehashes when necessary
        if (loadfactorExceeded()) {
            rehash();
        }
        return null;
    }

    // calculates the hash code of a given Key
    private int calculateHashCode(Object key) {
        return Math.abs(key.hashCode()) % tableSize;
    }

    // Fines a specfic Entry within a linked list
    private Entry getEntry(int hashCode, Object key) {
        Entry temp = table[hashCode];
        while (temp != null) {
            if (temp.getKey().equals(key)) {
                return temp;
            }
            temp = temp.next;
        }
        return null;
    }

    // method for determining if the load factor has been exceeded
    private boolean loadfactorExceeded() {
        return ((double) elementCount / tableSize) > LOAD_FACTOR;
    }

    // toString of the HashTable
    public String toString() {
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < tableSize; i++) {
            Entry temp = table[i];
            str.append("Elements for " + i + "th row of the Hashtable ");
            while (temp != null) {
                str.append(temp + " ");
                temp = temp.next;
            }
            str.append("\n");
        }
        return str.toString();
    }

    // Getters
    public int getElementCount() {
        return elementCount;
    }

    public Iterator getIterator() {
        return new Iterator(this);
    }

    // Iterator for iterating through the table
    public class Iterator {

        private HashTable table;
        private int index;
        private Entry entry;

        private Iterator(HashTable table) {
            this.table = table;
            this.index = 0;
            setNextEntry();
        }

        private void setNextEntry() {
            entry = table.table[index];
        }

        public Entry getNext() {
            while (true) {
                if (entry != null) {
                    Entry temp = entry;
                    entry = entry.next;
                    return temp;
                }
                index++;
                if (index == table.tableSize) {
                    return null;
                }
                setNextEntry();
            }
        }
    }
}

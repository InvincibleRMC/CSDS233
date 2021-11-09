package P4_RMC170_Carlstrom;

public class HashTable {

    public class Entry {
        private String key;
        private int count;
        public Entry next;

        public Entry(String key) {
            this.key = key;
            count = 1;
            next = null;
        }

        public int increase() {
            count++;
            return count;
        }

        public String getKey() {
            return key;
        }

        public String toString() {
            return "Key: " + key + " Count: " + count;
        }

    }

    private Entry[] table;
    public int tableSize;
    private double loadFactor;
    private int elementCount;

    public HashTable(int size) {
        tableSize = size;
        table = new Entry[size];
        loadFactor = 0.75;
        elementCount = 0;

    }

    public void wordCount(String input) {

        String[] splitInput = Split(input);

        for (int i = 0; i < splitInput.length; i++) {
            search(new Entry(splitInput[i]));

            if (check()) {
                rehash();
            }

        }

        System.out.println(toString());
    }

    public void rehash() {
        HashTable newTable = new HashTable(tableSize * 2);

        for (int i = 0; i < tableSize; i++) {

            Entry temp = table[i];

            while (temp != null) {
                newTable.search(temp);
                temp = temp.next;
            }
        }

        System.out.println(newTable);
        table = newTable.table;
        tableSize = newTable.tableSize;
    }

    private String[] Split(String input) {
        return input.split("\\P{Alpha}+");
    }

    public void search(Entry word) {

        int h = Math.abs(word.getKey().hashCode()) % tableSize;

        Entry prev = null;
        Entry temp = table[h];

        // System.out.println(temp);

        if (temp == null) {
            table[h] = word;
            elementCount++;
            System.out.println("Added link list header " + word);

            return;
        }

        // System.out.println(temp.next);

        while (temp != null) {

            if (temp.getKey() == word.getKey()) {
                System.out.println("Updated Count to " + temp.increase());
                return;
            }
            prev = temp;
            temp = temp.next;

        }

        prev.next = word;
        System.out.println("Added new Element " + word);
        System.out.println();
    }

    public boolean check() {

        return ((double) elementCount / tableSize) > loadFactor;

    }

    public String toString() {

        StringBuilder str = new StringBuilder();
        for (int i = 0; i < tableSize; i++) {

            Entry temp = table[i];
            str.append("Elements for " + i + "th row of the Hashtable ");

            if (temp == null) {

            } else {

                while (temp != null) {
                    str.append(temp + " ");
                    temp = temp.next;
                }
            }
            str.append("\n");
        }
        return str.toString();
    }

}

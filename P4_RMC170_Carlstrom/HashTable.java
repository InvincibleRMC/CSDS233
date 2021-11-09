package P4_RMC170_Carlstrom;

import java.util.ArrayList;
import java.util.LinkedList;

public class HashTable {

    private class Entry {
        private String key;
        private int count;
        private boolean removed;
        private Entry next;

        public void increase() {
            count++;
        }

        public String keyGet() {
            return key;
        }

        public boolean isEnd(){
            return next ==null;
        }
    }

    private Entry[] table;
    private int tableSize;

    public HashTable(int size) {
        tableSize = size;
        table = new Entry[size];
    }

    public void wordCount(String input) {

        String[] splitInput = Split(input);

        for (int i = 0; i < splitInput.length; i++) {
            search(splitInput[i]);
        }

        System.out.println(HashTableToString());
    }

    private String[] Split(String input) {
        return input.split("\\P{Alpha}+");
    }

    public void search(String word) {

        int h = Math.abs(word.hashCode()) % tableSize;

        while (table[h].isEnd()) {

            if (table[h].peek().keyGet() == word){
                table[h].peek().increase();
            }

        }
    

    check();

    }

    public void check() {

    }

    public String HashTableToString() {

        StringBuilder str = new StringBuilder();
        for (int i = 0; i < tableSize; i++) {

            LinkedList<Entry> temp = table[i];
            str.append("Elements for " + i + "th row of the Hashtable ");
            while (temp.peekFirst() != null) {
                str.append(temp.pollFirst() + ", ");
            }
        }
        return str.toString();
    }
}

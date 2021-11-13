package P4_RMC170_Carlstrom;

import P4_RMC170_Carlstrom.HashTable.Entry;
import P4_RMC170_Carlstrom.HashTable.Iterator;

public class Test {

    // Function for counting the words using a HashTable
    private static HashTable wordCount(String input) {
        String[] words = split(input);
        HashTable table = new HashTable(words.length);
        for (String word : words) {
            countWord(table, word);
        }
        System.out.println("Word counts for: " + input);
        printWordCounts(table);
        return table;
    }

    // Adds the words to the HashTable
    private static void countWord(HashTable table, String word) {
        word = makeCaseInsensitive(word);
        Integer count = (Integer)table.get(word);
        table.put(word, ((count == null) ? 1 : count + 1));
    }

    // Makes all words upper case to avoid "hi" and "HI" from being considered different
    private static String makeCaseInsensitive(String word) {
        return word.toUpperCase();
    }

    // Iterates through and prints the word counts of the table
    private static void printWordCounts(HashTable table) {
        Iterator i = table.getIterator();
        while (true) {
            Entry e = i.getNext();
            if (e == null) {
                break;
            }
            System.out.println(e.getKey() + " " + e.getValue());
        }
    }

    // Splits a String into a String[]
    private static String[] split(String input) {
        return input.split("\\P{Alpha}+");
    }
    // Main method
    public static void main(String[] args) {
        test();
    }

    // Tester methods
    public static void test() {
        testHashTable();
        testStaticWordCount();
        testRandomWordCount();
    }

    private static void testHashTable() {
        // empty table
        HashTable table = new HashTable(-1);
        assertEquals(0, table.getElementCount());
        assertEquals(null, table.get("a"));

        // try a couple elements
        table.put("a", "x");
        table.put("b", "y");
        assertEquals(2, table.getElementCount());
        assertEquals("x", table.get("a"));
        assertEquals("y", table.get("b"));

        // replace an element
        Object old = table.put("a", "1");
        assertEquals("x", old);
        assertEquals("1", table.get("a"));
        assertEquals(2,  table.getElementCount());

        // force collisons with all in 0 bucket
        table = new HashTable(10);
        assertEquals(0, ((Integer)0).hashCode());
        assertEquals(10, ((Integer)10).hashCode());
        assertEquals(20, ((Integer)20).hashCode());
        table.put(0, "zero");
        table.put(10, "ten");
        table.put(20, "twenty");
        assertEquals("zero", table.get(0));
        assertEquals("ten", table.get(10));
        assertEquals("twenty", table.get(20));
        assertEquals(3, table.getElementCount());

    }

    // checks if the keys match expected values
    private static void assertEquals(Object expected, Object actual) {
        boolean equals = ((expected == null && actual == null)
                          || expected.equals(actual));
        if (!equals) {
            throw new AssertionError("Expected " + expected + " does not equal actual " + actual);
        }
    }
    private static void assertEquals(int expected, int actual) {
        if (expected != actual) {
            throw new AssertionError("Expected " + expected + " does not equal actual " + actual);
        }
    }

    // Test a bunch of predetermined tests
    private static void testStaticWordCount() {
        HashTable table = wordCount("The");
        assertUniqueWords(table, 1);
        assertWordCount(table, "The", 1);

        table = wordCount("The quick");
        assertUniqueWords(table, 2);
        assertWordCount(table, "The", 1);
        assertWordCount(table, "quick", 1);

        table = wordCount("CSDS csds");
        assertUniqueWords(table, 1);
        assertWordCount(table, "csds", 2);

        table = wordCount("The quick brown fox jumped over the lazy dog");
        assertUniqueWords(table, 8);
        assertWordCount(table, "The", 2);
        assertWordCount(table, "quick", 1);
        assertWordCount(table, "brown", 1);
        assertWordCount(table, "fox", 1);
        assertWordCount(table, "jumped", 1);
        assertWordCount(table, "over", 1);
        assertWordCount(table, "lazy", 1);
        assertWordCount(table, "dog", 1);
    }

    // checks if the number of unique words is correct
    private static void assertUniqueWords(HashTable table, int count) {
        int actual = table.getElementCount();
        if (actual != count) {
            throw new AssertionError("Expected " + count + " unique words, found " + actual);
        }
    }

    // checks if the word count for an entry is correct
    private static void assertWordCount(HashTable table, String word, int frequency) {
        word = makeCaseInsensitive(word);
        int actual = (Integer)table.get(word);
        if (actual != frequency) {
            throw new AssertionError("Expected " + frequency + " unique words, found " + actual
                                     + " for word " + word);
        }
    }

    // Generates random sentence and tests it
    private static void testRandomWordCount() {

        StringBuilder str = new StringBuilder();

        String[] common = { "the", "of", "and", "a", "to", "in", "is", "you", "that", "it", "he",
            "was", "for", "on", "are", "as", "with", "his", "they", "I", "at", "be", "this", "have",
            "from", "or", "one", "had", "by", "word", "but", "not", "what", "all", "were", "we",
            "when", "your", "can", "said", "there", "use", "an", "each", "which", "she", "do",
            "how", "their", "if", "will", "up", "other", "about", "out", "many", "then", "them",
            "these", "so", "some", "her", "would", "make", "like", "him", "into", "time", "has",
            "look", "two", "more", "write", "go", "see", "number", "no", "way", "could", "people",
            "my", "than", "first", "water", "been", "call", "who", "oil", "its", "now", "find",
            "long", "down", "day", "did", "get", "come", "made", "may", "part"
        };

        
        while(Math.random() > 0.01){
            String word = common[(int) (Math.random() * 99.0)];
            str.append(word);
            str.append(" ");
        }
        wordCount(str.toString());
    }
}

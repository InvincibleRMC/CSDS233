package P4_RMC170_Carlstrom;

import P4_RMC170_Carlstrom.HashTable.Entry;

public class Test {

    public static void main(String[] args) {
        test();
    }

    public static void test() {
      //  HashTable table = generateTable(10);

      ///  System.out.println(table);
      //  table.rehash();
       // System.out.println(table);
        /*
         * HashTable test = new HashTable(1); test.search("hi"); test.search("hello");
         * test.search("yeet"); System.out.println(test);
         * 
         * 
         */
        randomWordCount(10);
    }

    public static HashTable generateTable(int size) {
        String[] common = { "the", "of", "and", "a", "to", "in", "is", "you", "that", "it", "he", "was", "for", "on",
                "are", "as", "with", "his", "they", "I", "at", "be", "this", "have", "from", "or", "one", "had", "by",
                "word", "but", "not", "what", "all", "were", "we", "when", "your", "can", "said", "there", "use", "an",
                "each", "which", "she", "do", "how", "their", "if", "will", "up", "other", "about", "out", "many",
                "then", "them", "these", "so", "some", "her", "would", "make", "like", "him", "into", "time", "has",
                "look", "two", "more", "write", "go", "see", "number", "no", "way", "could", "people", "my", "than",
                "first", "water", "been", "call", "who", "oil", "its", "now", "find", "long", "down", "day", "did",
                "get", "come", "made", "may", "part" };

        HashTable table = new HashTable(size);

        while (Math.random() > 0.01) {

            Entry element = table.new Entry(common[(int) (Math.random() * 99.0)]);

            table.search(element);
        }

        return table;
    }

    public static void randomWordCount(int size){

        StringBuilder str = new StringBuilder();

        HashTable table = new HashTable(size);

    String[] common = { "the", "of", "and", "a", "to", "in", "is", "you", "that", "it", "he", "was", "for", "on",
                "are", "as", "with", "his", "they", "I", "at", "be", "this", "have", "from", "or", "one", "had", "by",
                "word", "but", "not", "what", "all", "were", "we", "when", "your", "can", "said", "there", "use", "an",
                "each", "which", "she", "do", "how", "their", "if", "will", "up", "other", "about", "out", "many",
                "then", "them", "these", "so", "some", "her", "would", "make", "like", "him", "into", "time", "has",
                "look", "two", "more", "write", "go", "see", "number", "no", "way", "could", "people", "my", "than",
                "first", "water", "been", "call", "who", "oil", "its", "now", "find", "long", "down", "day", "did",
                "get", "come", "made", "may", "part" };

                int count =0;
        while(Math.random() > 0.01){

            str.append(common[(int) (Math.random() * 99.0)] + " ");
        count++;
        }

        table.wordCount(str.toString());
    }
}

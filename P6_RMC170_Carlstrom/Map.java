import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/**
 * javac P6_RMC170_Carlstrom/Map.java && java P6_RMC170_Carlstrom.Map
 * dot P6_RMC170_Carlstrom/graph.dot
 */
public class Map {

    private final HashMap<String, Building> map = new HashMap<String, Building>();

    public Map() {
    }

    public static void main(String[] args) {

        try {

            simpleTest();
            mediumTest();
            randomizedTest();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void simpleTest() throws IOException {

        Map m = new Map();
        m.addBuilding("the");
        m.addBuilding("hello");
        m.addRoad("hi", "hello", 5);
        m.removeBuilding("the");
        System.out.println(m);
        m.graphToTXT();

    }

    public static void mediumTest() throws IOException {
        Map m = generateGraph();

        m.addRoad("was", "the", 5);
        m.addRoad("was", "I", 16);
        m.addRoad("was", "other", 21);
        m.removeBuilding("the");

        System.out.println(m);
        m.graphToTXT();

    }

    public static void randomizedTest() throws IOException {
        Map m = generateRandomGraph(20);

        m.addRoad("I", "for", 10);
        m.addRoad("I", "be", 10);
        m.removeBuilding("a");

        System.out.println(m);
        m.graphToTXT();
    }

    public final boolean addBuilding(String name) {
        if (name == null) {
            throw new IllegalArgumentException("name == null");
        }
        if (map.containsKey(name)) {
            throw new IllegalArgumentException("map already contains building " + name);
        }
        map.put(name, new Building(name));
        return true;
    }

    /**
     * Throws IllegalArgumentException if the requested building name
     * is null or the building has not been added.
     */
    private Building getBuilding(String name) {
        if (name == null) {
            throw new IllegalArgumentException("name == null");
        }
        Building building = map.get(name);
        if (building == null) {
            addBuilding(name);
            building = getBuilding(name);
            // throw new IllegalArgumentException("no building found for " + name);
        }
        return building;
    }

    public final boolean addRoad(String fromBuilding, String toBuilding, int length) {
        System.out.println("Adding Road:" + fromBuilding + " " + toBuilding);

        Building from = getBuilding(fromBuilding);
        Building to = getBuilding(toBuilding);

        System.out.println(toString());

        if (length < 0) {
            throw new IllegalArgumentException("length < 0: " + length);
        }

        boolean step1 = from.addRoad(to, length);
        boolean step2 = to.addRoad(from, length);
        return step1 && step2;
    }

    public final boolean addRoads(String fromBuilding, Collection<String> toBuildings, int length) {
        boolean noDuplicateRoadFound = true;
        for (String toBuilding : toBuildings) {
            if (!addRoad(fromBuilding, toBuilding, length)) {
                noDuplicateRoadFound = false;
            }
        }
        return noDuplicateRoadFound;
    }

    public final boolean removeBuilding(String name) {

        Building building = getBuilding(name);
        map.remove(building);

        building.remove(this);

        return true;
    }

    public final boolean removeRoad(String fromBuilding, String toBuilding) {

        System.out.println("Removing Road:" + fromBuilding + " " + toBuilding);
        Building from = getBuilding(fromBuilding);
        Building to = getBuilding(toBuilding);
        boolean step1 = from.removeRoad(to);
        boolean step2 = to.removeRoad(from);
        return step1 && step2;
    }

    public final int shortestPathLength(String source, String destination) {
        List<String> path = shortestPath(source, destination);

        int length = 0;
        Iterator<String> i = path.iterator();
        String fromBuilding = i.next();
        while (i.hasNext()) {
            String toBuilding = i.next();
            length += getBuilding(fromBuilding).getRoads().get(toBuilding);
        }
        return length;
    }

    public final List<String> shortestPath(String source, String destination) {
        throw new IllegalArgumentException("TODO");
    }

    public final int minimumTotalLength() {
        throw new IllegalArgumentException("TODO");
    }

    public final int secondShortestPath(String source, String destination) {
        throw new IllegalArgumentException("TODO");
    }

    public static Map generateGraph() {
        String[] common = { "the", "of", "and", "a", "to", "in", "is", "you", "that", "it", "he",
                "was", "for", "on", "are", "as", "with", "his", "they", "I", "at", "be", "this",
                "have", "from", "or", "one", "had", "by", "word", "but", "not", "what", "all",
                "were", "we", "when", "your", "can", "said", "there", "use", "an", "each", "which",
                "she", "do", "how", "their", "if", "will", "up", "other", "about", "out", "many",
                "then", "them", "these", "so", "some", "her", "would", "make", "like", "him",
                "into", "time", "has", "look", "two", "more", "write", "go", "see", "number", "no",
                "way", "could", "people", "my", "than", "first", "water", "been", "call", "who",
                "oil", "its", "now", "find", "long", "down", "day", "did", "get", "come", "made",
                "may", "part"
        };
        int size = common.length;

        Map m = new Map();
        for (int i = 0; i < size; i++) {
            m.addBuilding(common[i]);
        }

        try {
            m.graphToTXT();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println(m);

        for (int i = 0; i < size; i++) {

            String fromBuilding = common[i];
            String toBuilding = common[size - i - 1];

            if (!fromBuilding.equals(toBuilding)) {
                m.addRoad(fromBuilding, toBuilding, (int) (Math.random() * 256));
                try {
                    m.graphToTXT();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                System.out.println(m);
            }

        }

        for (int i = 0; i < size * 5; i++) {

            String fromBuilding = common[(int) Math.random() * size];
            String toBuilding = common[(int) Math.random() * size];

            if (!fromBuilding.equals(toBuilding)) {
                m.addRoad(fromBuilding, toBuilding, (int) (Math.random() * 256));
                try {
                    m.graphToTXT();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                System.out.println(m);
            }

        }

        try {
            m.graphToTXT();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println(m);
        return m;
    }

    public static Map generateRandomGraph(int size) {
        String[] common = { "the", "of", "and", "a", "to", "in", "is", "you", "that", "it", "he",
                "was", "for", "on", "are", "as", "with", "his", "they", "I", "at", "be", "this",
                "have", "from", "or", "one", "had", "by", "word", "but", "not", "what", "all",
                "were", "we", "when", "your", "can", "said", "there", "use", "an", "each", "which",
                "she", "do", "how", "their", "if", "will", "up", "other", "about", "out", "many",
                "then", "them", "these", "so", "some", "her", "would", "make", "like", "him",
                "into", "time", "has", "look", "two", "more", "write", "go", "see", "number", "no",
                "way", "could", "people", "my", "than", "first", "water", "been", "call", "who",
                "oil", "its", "now", "find", "long", "down", "day", "did", "get", "come", "made",
                "may", "part"
        };

        if (size > common.length) {
            size = common.length;
        }

        Map m = new Map();

        for (int i = 0; i < size; i++) {
            m.addBuilding(common[i]);
        }

        try {
            m.graphToTXT();
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < size; i++) {

            while (Math.random() < 0.90) {
                String fromBuilding = common[i];
                String toBuilding = common[(int) (Math.random() * (common.length - size) + size)];

                if (!fromBuilding.equals(toBuilding)) {
                    m.addRoad(fromBuilding, toBuilding, (int) (Math.random() * 256));
                    try {
                        m.graphToTXT();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    System.out.println(m);
                }
            }
        }

        try {
            m.graphToTXT();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println(m);
        return m;
    }

    private void graphToTXT() throws IOException {
        try (Writer out = new BufferedWriter(new FileWriter("P6_RMC170_Carlstrom/graph.dot"))) {
            graphToTXT(out);
        }
    }

    // Visualization tool for the graph
    private void graphToTXT(Writer out) throws IOException {
        try {
            out.write("// dot file of graph\n");
            out.write("graph graphname {\n");

            for (Building building : map.values()) {
                String name = building.getName();
                HashMap<String, Integer> roads = building.getRoads();

                int counter = 0;

                for (HashMap.Entry<String, Integer> entry : roads.entrySet()) {
                    counter++;
                    String destination = entry.getKey();
                    int length = entry.getValue();
                   //System.out.println(name + " " + destination + " " + length + " " + counter);
                    out.write(name
                            + " -- " + destination
                            + " [label=\"length is: " + length + "\"];\n");
                }
            }
            out.write("}\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // To string of the graph
    public String toString() {
        StringWriter out = new StringWriter();
        try {
            graphToTXT(out);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return out.toString();
    }

}

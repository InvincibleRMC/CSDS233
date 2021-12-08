import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.lang.Comparable;

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
            // mediumTest();
            // randomizedTest();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Basic Test
    public static void simpleTest() throws IOException {

        Map m = new Map();
        m.addBuilding("the");
        m.addBuilding("hello");
        m.addRoad("hi", "hello", 5);
        m.removeBuilding("the");

        Collection<String> col = new ArrayList<String>();
        col.add("yes");
        col.add("nice");
        col.add("meet");
        m.addRoads("the", col, 10);
        System.out.println(m);
        m.graphToTXT();

        System.out.println("Testing algorithms");
        System.out.println(m.shortestPath("the", "nice"));
        System.out.println(m.shortestLength("the", "nice"));

        // Test not connectedess
        try {
            m.shortestLength("the", "hello");
            throw new AssertionError();
        } catch (IllegalArgumentException expected) {
        }

        // WARNING this does not currently work
        // It currently doubles back to far and reuses nodes which Dijkstra's does not
        // handle
        System.out.println(m.secondShortestPath("the", "nice"));
    }

    // Medium test
    public static void mediumTest() throws IOException {
        Map m = generateGraph();

        m.addRoad("was", "the", 5);
        m.addRoad("was", "I", 16);
        m.addRoad("was", "other", 21);
        m.removeBuilding("the");

        System.out.println(m);
        m.graphToTXT();

    }

    // Super randomize test
    public static void randomizedTest() throws IOException {
        Map m = generateRandomGraph(20);

        m.addRoad("I", "for", 10);
        m.addRoad("I", "be", 10);
        m.removeBuilding("a");

        System.out.println(m);
        m.graphToTXT();
    }

    // Adds building to the map
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
     * Throws IllegalArgumentException if the requested building name is null
     * Automatically adds new buildings when neccasary
     */
    private Building getBuilding(String name) {
        if (name == null) {
            throw new IllegalArgumentException("name == null");
        }
        Building building = map.get(name);
        if (building == null) {
            addBuilding(name);
            building = getBuilding(name);
        }
        return building;
    }

    // Adds a road from fromBuilding to toBuilding of a length length
    public final boolean addRoad(String fromBuilding, String toBuilding, int length) {
        System.out.println("Adding Road:" + fromBuilding + " " + toBuilding);

        // Gets buildings and adds them if they don't exists
        Building from = getBuilding(fromBuilding);
        Building to = getBuilding(toBuilding);

        System.out.println(toString());

        if (length < 0) {
            throw new IllegalArgumentException("length < 0: " + length);
        }

        // Adds Road one way then the other
        boolean step1 = from.addRoad(to, length);
        boolean step2 = to.addRoad(from, length);
        return step1 && step2;
    }

    // Adds mutiple roads by calling addRoad mutiple times
    public final boolean addRoads(String fromBuilding, Collection<String> toBuildings, int length) {
        boolean noDuplicateRoadFound = true;
        for (String toBuilding : toBuildings) {
            if (!addRoad(fromBuilding, toBuilding, length)) {
                noDuplicateRoadFound = false;
            }
        }
        return noDuplicateRoadFound;
    }

    // removes a building from a map
    // also removes roads connected to the building
    public final boolean removeBuilding(String name) {

        Building building = getBuilding(name);
        map.remove(building);

        building.remove(this);

        return true;
    }

    // removes road
    public final boolean removeRoad(String fromBuilding, String toBuilding) {

        System.out.println("Removing Road:" + fromBuilding + " " + toBuilding);
        Building from = getBuilding(fromBuilding);
        Building to = getBuilding(toBuilding);
        boolean step1 = from.removeRoad(to);
        boolean step2 = to.removeRoad(from);
        return step1 && step2;
    }

    // returns the shortestLength of
    public final int shortestLength(String source, String destination) {

        if (source == null) {
            throw new IllegalArgumentException("source == null");
        }
        if (destination == null) {
            throw new IllegalArgumentException("destionation == null");
        }
        List<String> path = shortestPath(source, destination);

        System.out.println(path);

        int length = 0;
        Iterator<String> i = path.iterator();
        String fromBuilding = i.next();
        while (i.hasNext()) {
            String toBuilding = i.next();
            length += getBuilding(fromBuilding).getRoads().get(toBuilding);
        }
        return length;
    }

    // QueueNode for getting shortest path
    public class QueueNode implements Comparable<QueueNode> {

        String buildingName;
        int currDistance;

        QueueNode(String name, int dist) {
            buildingName = name;
            currDistance = dist;
        }

        // comparing two QueueNode
        @Override
        public int compareTo(Map.QueueNode b) {
            if (currDistance > b.currDistance) {
                return 1;
            }
            if (currDistance < b.currDistance) {
                return -1;
            }
            return 0;
        }

    }

    // List of shortest path
    public final List<String> shortestPath(String source, String destination) {

        // find shortest distance num
        HashMap<String, Integer> distances = new HashMap<>();

        for (String buildingName : map.keySet()) {
            distances.put(buildingName, Integer.MAX_VALUE);
        }
        distances.put(source, 0);

        PriorityQueue<QueueNode> pq = new PriorityQueue<QueueNode>();
        pq.add(new QueueNode(source, 0));

        while (pq.size() > 0) {

            QueueNode current = pq.poll();
            Building currBuilding = getBuilding(current.buildingName);
            HashMap<String, Integer> currRoads = currBuilding.getRoads();

            for (HashMap.Entry<String, Integer> distanceToEntry : currRoads.entrySet()) {

                int currLength = distances.get(current.buildingName);
                int currWeight = distanceToEntry.getValue();
                int newLength = currLength + currWeight;
                if (newLength < 0) {
                    // System.out.
                    System.out.println(current.buildingName);
                    System.out.println(currLength + " " + currWeight);
                    throw new IllegalStateException();
                }
                int oldLength = distances.get(distanceToEntry.getKey());

                if (newLength < oldLength) {
                    String nodeName = distanceToEntry.getKey();
                    distances.put(nodeName, newLength);
                    pq.add(new QueueNode(nodeName, distances.get(nodeName)));
                }

            }

        }

        // find shortestDistance
        int shortestDistance = distances.get(destination);
        System.out.println(distances);
        // Now go back to find the path
        LinkedList<String> result = new LinkedList<String>();
        result.push(destination);

        if (shortestDistance == Integer.MAX_VALUE) {
            throw new IllegalArgumentException("Destination unreachable");
        }

        while (true) {

            String currentBuildingName = result.peek();
            Building currentBuilding = getBuilding(currentBuildingName);
            HashMap<String, Integer> currentRoads = currentBuilding.getRoads();

            for (HashMap.Entry<String, Integer> road : currentRoads.entrySet()) {

                String destinationBuilding = road.getKey();
                int distance = road.getValue();
                if (distance <= shortestDistance) {
                    result.push(destinationBuilding);
                    if (destinationBuilding.equals(source)) {
                        return result;
                    }
                }

            }

        }
    }

    public final int minimumTotalLength() {

        throw new IllegalArgumentException("TODO");
    }

    // Finds the second shortest path
    public final int secondShortestPath(String source, String destination) {

        List<String> path = shortestPath(source, destination);

        int distanceSoFar = 0;
        int secondShortestDistanceSoFar = Integer.MAX_VALUE;
        Iterator<String> i = path.iterator();
        String fromBuilding = i.next();

        while (i.hasNext()) {

            String toBuilding = i.next();

            HashMap<String, Integer> roads = getBuilding(fromBuilding).getRoads();

            distanceSoFar += roads.get(toBuilding);
            for (HashMap.Entry<String, Integer> entry : roads.entrySet()) {
                String roadDestination = entry.getKey();
                int roadLength = entry.getValue();

                if (roadDestination.equals(toBuilding)) {
                    continue;
                }
                int distanceToEnd = distanceSoFar + roadLength + shortestLength(roadDestination, destination);
                if (distanceToEnd < secondShortestDistanceSoFar) {
                    secondShortestDistanceSoFar = distanceToEnd;
                }

            }
        }
        return secondShortestDistanceSoFar;

        // throw new IllegalArgumentException("TODO");
    }

    // generate a stock graph
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

        try {
            m.graphToTXT();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println(m);
        return m;
    }

    // Generates a very super randomized graph
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

    // Writer for graphToTXT();
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

                for (HashMap.Entry<String, Integer> entry : roads.entrySet()) {

                    String destination = entry.getKey();
                    int length = entry.getValue();
                    // System.out.println(name + " " + destination + " " + length + " " + counter);
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

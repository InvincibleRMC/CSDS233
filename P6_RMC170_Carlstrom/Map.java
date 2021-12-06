import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;

public class Map {

    private Node[] map;
    private String[] buildingNames;
    private int buildingId;
    private int mapSize;
    private double loadFactor;
    private int buildingCount;

    public Map(String name, int size, double loadFactor) {

        // Min size of 1
        if (size <= 0) {
            size = 1;
        }

        // Percentage between 0 and 100
        if (loadFactor < 0) {
            loadFactor = 0;
        } else if (loadFactor > 1) {
            loadFactor = 1;
        }
        this.loadFactor = loadFactor;

        mapSize = size;

        map = new Node[size];
        map[calculateHashCode(name)] = new Node(name);

        buildingCount = 1;
    }

    public static void main(String[] args) {
        // Map m = new Map("The", 10, 0.75);
        // m.addBuilding("Hello");
        // m.addRoad("Hi", "Hello", 5);


        Map cry = generateGraph();
        cry.toString();


       // Map m = generateRandomGraph(10, 0.8);
        //m.toString();
        
    }

    // calculates the hash code of a given Key
    private int calculateHashCode(Object key) {
        return Math.abs(key.hashCode()) % mapSize;
    }

    // method for determining if the load factor has been exceeded
    private boolean loadfactorExceeded() {
        return ((double) buildingCount / mapSize) > loadFactor;
    }

    // A rehash function for when the LOAD_FACTOR is exceeded
    private void rehash() {

        System.out.println("Starting Rehash weary");
        Node[] oldTable = this.map;
        this.mapSize = this.mapSize * 2;
        this.map = new Node[mapSize];
        this.buildingCount = 0;

        // Dumps all the elements into the new table
        for (int i = 0; i < oldTable.length; i++) {
            Node temp = oldTable[i];
            String name = "";
            // temp = temp.getEdge();

            //System.out.println()
            String str="ith row " + i +".\n";
                while(temp !=null){
                    str= str +" " + temp;
                    temp = temp.getEdge();
                }
                System.out.println(str);

                temp=oldTable[i];
            while (temp != null) {

                

               // System.out.println(toString());
                System.out.println("Loop inside rehash() " + temp  );

                if (temp.getLength() == 0) {
                    name = temp.getName();
                    // System.out.println(name);
                    addBuilding(name);
                } else {
                    addRoad(name, temp.getName(), temp.getLength());
                }
                // if (temp.isEnd()) {
                // name = temp.getEdge().getName();
                // }

                temp = temp.getEdge();

            }
        }
    }

    public final boolean addBuilding(String name) {

        int h = calculateHashCode(name);
        Node n = getBuilding(h, name);

        if (buildingExists(name)) {
            System.out.println("Building already exists" + " cry now " + name);
            throw new IllegalArgumentException();
        }

        if (n != null) {
            map[h].findEnd().setEdge(n);
            return true;
        }

        Node toBeAdded = new Node(name);
        Node head = map[h];
        toBeAdded.setEdge(head);
        map[h] = toBeAdded;
        buildingCount++;

        if (loadfactorExceeded()) {
            rehash();
        }
        return true;

    }

    // Fines a specfic Node within a linked list
    private Node getBuilding(int hashCode, String name) {
        Node temp = map[hashCode];
        while (temp != null) {
            if (temp.getName().equals(name) && temp.getLength() == 0) {
                return temp;
            }
            temp = temp.getEdge();
        }
        return null;
    }

    // might be redundant with getbuilding
    public boolean buildingExists(String name) {

        System.out.println(toString());

        int h = calculateHashCode(name);

        System.out.println("Building Exists check? " + name + " hash value=" + h);

        Node temp = map[h];
        while (temp != null) {

            System.out.println("Inside buildingExists check loop " + temp);

            if (temp.getName().equals(name) && temp.getLength() == 0) {

                System.out.println("Code thinks " + temp + " exists");

                return true;
            }

            temp = temp.getEdge();

        }

        System.out.println("Code thinks " + temp + " does not exist");
        return false;

    }

    // might be redundant with getbuilding
    public boolean roadExists(String fromBuilding, String toBuilding) {

        int h = calculateHashCode(fromBuilding);

        Node temp = map[h];
        String currName= map[h].getName();


        while (temp != null) {

            //if(temp.)

            if (temp.getName().equals(toBuilding)) {
                return true;
            }
            temp = temp.findNextBuilding();
        }
        return false;

    }

    public final boolean addRoad(String fromBuilding, String toBuilding, int length) {

        System.out.println("Adding Road:" + fromBuilding + " " + toBuilding);

        if (roadExists(fromBuilding, toBuilding)) {
            System.out.println(" Road exists? " + fromBuilding + " " + toBuilding + " "
                    + roadExists(fromBuilding, toBuilding));
            return false;
        }

        boolean step1 = addRoadHelper(fromBuilding, toBuilding, length);
        boolean step2 = addRoadHelper(toBuilding, fromBuilding, length);


       // System.out.println("Roads added ");

        return step1 && step2;
    }

    public final boolean addRoadHelper(String fromBuilding, String toBuilding, int length) {

        System.out.println("Add road helper");
        int hFB = calculateHashCode(fromBuilding);

        System.out.println("Here10?");
        System.out.println(buildingExists(fromBuilding));

        if (!buildingExists(fromBuilding)) {
            addBuilding(fromBuilding);
        }

        int hTB = calculateHashCode(toBuilding);
        System.out.println("does the to building exist?: " + buildingExists(toBuilding));
        if (!buildingExists(toBuilding)) {
            System.out.println("Add building inside building check " + toBuilding);
            addBuilding(toBuilding);
        }

        hFB = calculateHashCode(fromBuilding);
        hTB = calculateHashCode(toBuilding);

        System.out.println("made it pass building checks");

        System.out.println("here?" + fromBuilding + " " + toBuilding);

        Node n = getBuilding(hFB, fromBuilding);
        System.out.println(n);
        n.setEdge(new Node(toBuilding, length));

        System.out.println("Here25?");

        Node temp = getBuilding(hFB, fromBuilding);
        System.out.println("Here27?");
        while (temp.getLength()!=0) {
            System.out.print(temp.isEnd());
            temp = temp.getEdge();
        }
        System.out.println("Here30?");
        Node toBuildingNode = new Node(toBuilding, length);
        temp.setEnd(false);
        System.out.println("Here40?");
        temp.setEdge(toBuildingNode);
        return true;

    }

    public final boolean addRoads(String fromBuilding, Collection<String> toBuildings, int length) {

        Iterator<String> check = toBuildings.iterator();

        while (check.hasNext()) {
            if (roadExists(fromBuilding, (String) check.next())) {
                return false;
            }
        }
        Iterator<String> buildings = toBuildings.iterator();
        while (buildings.hasNext()) {
            addRoad(fromBuilding, (String) buildings.next(), length);
        }
        return true;

    }

    /*
      
      public final boolean removeBuilding(String name) {
     * 
     * }
     * 
     * public final boolean removeRoad(String fromBuilding, String toBuilding) {
     * 
     * }
     * 
     * public final int shortestPath(String source, String destination) {
     * 
     * }
     * 
     * public final List<String> shortestPath(String soucre, String destination) {
     * 
     * }
     * 
     * public final int minimumTotalLength() {
     * 
     * }
     * 
     * public final int secondShortestPath(String source, String destination) {
     * 
     * }
     * 
     */

    public static Map generateGraph(){
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

int size =10;
double loadFactor= 0.9;

if (size > common.length) {
    size = common.length;
}

Map m = new Map(common[0], size, loadFactor);

for (int i = 1; i < size; i++) {
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
        String toBuilding = common[size-i];
        System.out.println("Building names equal? " + fromBuilding + " " + toBuilding);

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

    public static Map generateRandomGraph(int size, double loadFactor) {
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

        if (size > common.length) {
            size = common.length;
        }

        Map m = new Map(common[0], size, loadFactor);

        for (int i = 1; i < size; i++) {
            m.addBuilding(common[i]);
        }

        try {
            m.graphToTXT();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println(m);

        for (int i = 0; i < size; i++) {

            while (Math.random() < 0.9) {
                String fromBuilding = common[i];
                String toBuilding = common[(int) (Math.random() * (common.length - size) + size)];
                System.out.println("Building names equal? " + fromBuilding + " " + toBuilding);

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

    public void graphToTXT() throws IOException {

        try {

            FileWriter f = new FileWriter("P6_RMC170_Carlstrom/graph.dot");

            f.write("");

            StringBuilder str = new StringBuilder();

            str.append("// dot file of graph");
            str.append("\n");
            str.append("graph graphname {");
            str.append("\n");

            for (int i = 0; i < mapSize; i++) {

                System.out.println(map[i]);

                Node temp = map[i];

                String name;

                while (temp != null) {

                    //str.append(temp.getName());

                    if (temp.getLength() ==0) {
                        name = temp.getName();
                        //str.append(name);
                        str.append(name + " -- ");
                    } else {
                        str.append(temp.getName() + " [label=\"length is: " + temp.getLength() + "\"]");
                        str.append("\n");
                    }

                    temp = temp.getEdge();
                }

                // str.append("\n");

            }

            str.append("\n}");
            System.out.println(str.toString());
            f.write(str.toString());
            f.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public String toString() {

        StringBuilder str = new StringBuilder();

        for (int i = 0; i < mapSize; i++) {

            str.append("Line # " + i + " "); 
            Node temp = map[i];

            while (temp != null) {
                if(temp.getLength()==0){
                    str.append("Building: ");
                }
                else{
                    str.append("Road: ");
                }
                str.append(temp + " ");
                temp = temp.getEdge();
            }

            str.append("\n");

        }

        return str.toString();

    }

}
import java.util.Collection;

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
        Node[] oldTable = this.map;
        this.mapSize = this.mapSize * 2;
        this.map = new Node[mapSize];
        this.buildingCount = 0; // since put() below will recount

        // Dumps all the elements into the new table
        for (int i = 0; i < oldTable.length; i++) {
            Node temp = oldTable[i];
            String name = temp.getName();
            temp = temp.getEdge();
            while (temp != null) {

                if (temp.getLength() == 0) {
                    addBuilding(name);
                }

                addRoad(name, temp.getName(), temp.getLength());

                if (temp.isEnd()) {
                    name = temp.getEdge().getName();
                }

                temp = temp.getEdge();

            }
        }
    }

    public final boolean addBuilding(String name){

        int h = calculateHashCode(name);
        Node n = getBuilding(h,name);

        if(buildingExists(h, name)){
            throw new IllegalArgumentException();
        }
        
        if(n!=null){
            map[h].findEnd().setEdge(n);
            return true;
        }

        Node toBeAdded = new Node(name);
        Node head = map[h];
        (toBeAdded.getEdge()).setEdge(head);
        map[h] = toBeAdded;
        buildingCount++;

        if(loadfactorExceeded()){
            rehash();
        }
        return true;

    }

    // Fines a specfic Node within a linked list
    private Node getBuilding(int hashCode, String name) {
        Node temp = map[hashCode];
        while (temp != null) {
            if (temp.getName().equals(name)) {
                return temp;
            }
            temp = temp.findNextBuilding();
        }
        return null;
    }


    // might be redundant with getbuilding
    public boolean buildingExists(int h, String name){

        Node temp = map[h];
        while(temp !=null){
            
            if(temp.getName().equals(name)){
                return true;
            }
            
            temp = temp.findNextBuilding();
            
        }
        return false;

    }

    // might be redundant with getbuilding
    public boolean roadExists(String fromBuilding,String toBuilding){

        int h = calculateHashCode(fromBuilding);

       Node temp = getBuilding(h, fromBuilding);
        while(!temp.isEnd()){
            if(temp.getName().equals(toBuilding)){
                return true;
            }
        }
        return false;
        
    }
    

    public final boolean addRoad(String fromBuilding, String toBuilding, int length) {

        if(roadExists(fromBuilding,toBuilding)){
            return false;
        }

        int hFB = calculateHashCode(fromBuilding);
        int hTB = calculateHashCode(toBuilding);

        if(!buildingExists(hFB, fromBuilding)){
            addBuilding(fromBuilding);
        }
        if(!buildingExists(hTB, toBuilding)){
            addBuilding(toBuilding);
        }

        Node n = getBuilding(hFB, fromBuilding);
        n.setEdge(new Node(toBuilding,length));


        Node temp = getBuilding(hFB, fromBuilding);
        while(!temp.isEnd()){
            temp = temp.getEdge();
        }
        Node toBuildingNode = new Node(toBuilding,length);
        temp.setEnd(false);
        temp.setEdge(toBuildingNode);
        return true;

    }

    

    public final boolean addRoads(String fromBuilding, Collection<String> toBuildings, int length) {

    }

    public final boolean removeBuilding(String name) {

    }

    public final boolean removeRoad(String fromBuilding, String toBuilding) {

    }

    public final int shortestPath(String source, String destination) {

    }

    public final List<String> shortestPath(String soucre, String destination) {

    }

    public final int minimumTotalLength() {

    }

    public final int secondShortestPath(String source, String destination) {

    }

}
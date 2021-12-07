
import java.util.Comparator;
import java.util.HashMap;
import java.util.PriorityQueue;

public class Building {
    private final String name;

    // Map with keys of buildings reachable from here, with values of the path length
    private final HashMap<String,Integer> roads = new HashMap<String,Integer>();

    private static class Road {
        String destination;
        int length;

        Road(String destination, int length) {
            this.destination = destination;
            this.length = length;
        }
    }

    private static class RoadComparator implements Comparator<Road> {
        @Override
        public int compare(Road a, Road b) {
            if (a.length < b.length) {
                return 1;
            }
            if (a.length > b.length) {
                return -1;
            }
            return 0;
        }
    }

    private static final RoadComparator ROAD_COMPARATOR = new RoadComparator();

    // heap for finding shortest created when needed, remove when out of date
    private PriorityQueue<Road> heap = null;

    // package private for restricted access
    String getName() {
        return name;
    }

    // package private for restricted access
    HashMap<String,Integer> getRoads() {
        return roads;
    }

    // get package private for restricted access
    String getNearestBuilding() {
        if (heap == null) {
            heap = new PriorityQueue<Road>(roads.size(), ROAD_COMPARATOR);
            for (HashMap.Entry<String,Integer> entry : roads.entrySet()) {
                String destination = entry.getKey();
                int length = entry.getValue();
                heap.add(new Road(destination, length));
            }
        }
        Road road = heap.peek();
        return road.destination;
    }

    public Building(String name) {
        this.name = name;
    }

    // package private for restricted access
    boolean addRoad(Building to, int length) {
        if (roads.containsKey(to.name)) {
            // already conntected a road fom here to there
            return false;
        }
        roads.put(to.name, length);
        heap = null;

        

        return true;
    }

    /**
     * remove this building by removing roads connecting us to other buildings (and back)
     *
     * package private for restricted access
     */
    void remove(Map map) {

        Object[] input =(roads.keySet()).toArray();
        for (int i =0; i<input.length;i++) {
            System.out.println("remove road loop "+ (String) input[i]);
            
            map.removeRoad(name, (String) input[i]);
        }


    }

    // package private for restricted access
    boolean removeRoad(Building to) {
        if (!roads.containsKey(to.name)) {
            // no road connecting fom here to there
            return false;
        }
        roads.remove(to.name);
        heap = null;
        return true;
    }
}

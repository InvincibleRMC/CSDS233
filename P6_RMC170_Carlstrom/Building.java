
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

        System.out.println("Before Road exixts check");

        if (roads.containsKey(to.name)) {
            // already conntected a road fom here to there
            System.out.println("Code thinks road exists");
            return false;
        }
        System.out.println("roads.toString() " + roads.toString());
        roads.put(to.name, length);

        System.out.println("roads.toString() " + roads.toString());
        heap = null;

        System.out.println("printing out all roads");
        for (String key: roads.keySet()){  
			System.out.println(key+ " = " + roads.get(key));
		} 

        return true;
    }

    /**
     * remove this building by removing roads connecting us to other buildings (and back)
     *
     * package private for restricted access
     */
    void remove(Map map) {
        for (String to : roads.keySet()) {
            map.removeRoad(name, to);
        }
    }

    // package private for restricted access
    boolean removeRoad(Building to) {
        if (!roads.containsKey(to.name)) {
            // no road connectecting fom here to there
            return false;
        }
        roads.remove(to.name);
        heap = null;
        return true;
    }
}

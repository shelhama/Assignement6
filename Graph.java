import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;

/**
 * Graph class that implements a simple undirected graph with Towns as vertices
 * and Roads as edges. It supports adding, removing, and finding vertices and edges,
 * as well as finding the shortest path using Dijkstra's algorithm.
 * 
 * Author: Your Name
 */
public class Graph implements GraphInterface<Town, Road> {
    private Set<Town> towns;
    private Set<Road> roads;

    /**
     * Constructs an empty Graph with no towns or roads.
     */
    public Graph() {
        towns = new HashSet<>();
        roads = new HashSet<>();
    }

    /**
     * Retrieves a road (edge) connecting two given towns, if it exists.
     *
     * @param sourceVertex the source town
     * @param destinationVertex the destination town
     * @return the Road object if found, otherwise null
     */
    @Override
    public Road getEdge(Town sourceVertex, Town destinationVertex) {
        if (sourceVertex == null || destinationVertex == null) return null;
        for (Road road : roads) {
            if (road.contains(sourceVertex) && road.contains(destinationVertex)) {
                return road;
            }
        }
        return null;
    }

    /**
     * Adds a new road connecting two towns.
     *
     * @param sourceVertex the source town
     * @param destinationVertex the destination town
     * @param weight the weight (distance) of the road
     * @param description the name of the road
     * @return the newly added Road object
     */
    @Override
    public Road addEdge(Town sourceVertex, Town destinationVertex, int weight, String description) {
        if (sourceVertex == null || destinationVertex == null) throw new NullPointerException();
        if (!towns.contains(sourceVertex) || !towns.contains(destinationVertex)) throw new IllegalArgumentException();
        Road road = new Road(sourceVertex, destinationVertex, weight, description);
        roads.add(road);
        return road;
    }

    /**
     * Adds a town (vertex) to the graph.
     *
     * @param v the town to add
     * @return true if added successfully, false otherwise
     */
    @Override
    public boolean addVertex(Town v) {
        if (v == null) return false;
        return towns.add(v);
    }

    /**
     * Checks if a road (edge) exists between two towns.
     *
     * @param sourceVertex the source town
     * @param destinationVertex the destination town
     * @return true if an edge exists, false otherwise
     */
    @Override
    public boolean containsEdge(Town sourceVertex, Town destinationVertex) {
        return getEdge(sourceVertex, destinationVertex) != null;
    }

    /**
     * Checks if a town exists in the graph.
     *
     * @param v the town to check
     * @return true if the town exists, false otherwise
     */
    @Override
    public boolean containsVertex(Town v) {
        return towns.contains(v);
    }

    /**
     * Returns a set of all roads in the graph.
     *
     * @return a Set containing all Road objects
     */
    @Override
    public Set<Road> edgeSet() {
        return roads;
    }

    /**
     * Returns a set of all roads connected to a given town.
     *
     * @param vertex the town whose edges are to be returned
     * @return a Set of connected Road objects
     */
    @Override
    public Set<Road> edgesOf(Town vertex) {
        Set<Road> edges = new HashSet<>();
        for (Road road : roads) {
            if (road.contains(vertex)) {
                edges.add(road);
            }
        }
        return edges;
    }

    /**
     * Removes the road (edge) between two towns.
     *
     * @param sourceVertex the source town
     * @param destinationVertex the destination town
     * @param weight the weight of the road (ignored)
     * @param description the name of the road (ignored)
     * @return the Road object that was removed, or null if none
     */
    @Override
    public Road removeEdge(Town sourceVertex, Town destinationVertex, int weight, String description) {
        Road road = getEdge(sourceVertex, destinationVertex);
        if (road != null) {
            roads.remove(road);
            return road;
        }
        return null;
    }

    /**
     * Removes a town (vertex) from the graph, along with all connected roads.
     *
     * @param v the town to remove
     * @return true if removed successfully, false otherwise
     */
    @Override
    public boolean removeVertex(Town v) {
        if (towns.remove(v)) {
            roads.removeIf(road -> road.contains(v));
            return true;
        }
        return false;
    }

    /**
     * Returns a set of all towns (vertices) in the graph.
     *
     * @return a Set containing all Town objects
     */
    @Override
    public Set<Town> vertexSet() {
        return towns;
    }

    // -------------- DIJKSTRA'S ALGORITHM SECTION ---------------

    private Map<Town, Integer> distances;
    private Map<Town, Town> previous;
    private Set<Town> visited;

    /**
     * Implements Dijkstra's shortest path algorithm starting from a given source town.
     *
     * @param sourceVertex the starting town
     */
    @Override
    public void dijkstraShortestPath(Town sourceVertex) {
        distances = new HashMap<>();
        previous = new HashMap<>();
        visited = new HashSet<>();

        for (Town town : towns) {
            distances.put(town, Integer.MAX_VALUE);
        }
        distances.put(sourceVertex, 0);

        PriorityQueue<Town> queue = new PriorityQueue<>(Comparator.comparingInt(distances::get));
        queue.add(sourceVertex);

        while (!queue.isEmpty()) {
            Town current = queue.poll();
            visited.add(current);

            for (Road road : edgesOf(current)) {
                Town neighbor = road.getSource().equals(current) ? road.getDestination() : road.getSource();
                if (!visited.contains(neighbor)) {
                    int newDist = distances.get(current) + road.getDistance();
                    if (newDist < distances.get(neighbor)) {
                        distances.put(neighbor, newDist);
                        previous.put(neighbor, current);
                        queue.add(neighbor);
                    }
                }
            }
        }
    }

    /**
     * Returns the shortest path from the source town to the destination town
     * as a list of steps in string format.
     *
     * @param sourceVertex the starting town
     * @param destinationVertex the target town
     * @return an ArrayList of Strings describing the path
     */
    @Override
    public ArrayList<String> shortestPath(Town sourceVertex, Town destinationVertex) {
        ArrayList<String> path = new ArrayList<>();
        dijkstraShortestPath(sourceVertex);

        if (!previous.containsKey(destinationVertex) && !sourceVertex.equals(destinationVertex)) {
            return path; // No path
        }

        Stack<Town> stack = new Stack<>();
        Town step = destinationVertex;
        stack.push(step);

        while (previous.get(step) != null) {
            step = previous.get(step);
            stack.push(step);
        }

        while (stack.size() > 1) {
            Town from = stack.pop();
            Town to = stack.peek();
            Road road = getEdge(from, to);
            path.add(from + " via " + road.getName() + " to " + to + " " + road.getDistance() + " mi");
        }

        return path;
    }

    /**
     * Dummy method to satisfy GraphTest.java.
     * Not used in this class.
     *
     * @param file the file to read (ignored)
     * @throws FileNotFoundException if file is not found
     */
    public void populateTownGraph(File file) throws FileNotFoundException {
        // Dummy method to satisfy GraphTest.java
    }
}

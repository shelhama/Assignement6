import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * This class manages a graph of towns and roads using the Graph class.
 * It provides methods to add towns, add roads, find paths, delete connections, 
 * and populate the graph from a file.
 * 
 * Author: Your Name
 */
public class TownGraphManager implements TownGraphManagerInterface {
    private Graph graph;

    /**
     * Constructs a new TownGraphManager with an empty graph.
     */
    public TownGraphManager() {
        graph = new Graph();
    }

    /**
     * Adds a road (edge) between two towns.
     * 
     * @param town1 the name of the first town
     * @param town2 the name of the second town
     * @param weight the weight (distance) of the road
     * @param roadName the name of the road
     * @return true if the road was successfully added
     */
    @Override
    public boolean addRoad(String town1, String town2, int weight, String roadName) {
        Town source = getTown(town1);
        Town destination = getTown(town2);
        if (source == null) {
            source = new Town(town1);
            graph.addVertex(source);
        }
        if (destination == null) {
            destination = new Town(town2);
            graph.addVertex(destination);
        }
        graph.addEdge(source, destination, weight, roadName);
        return true;
    }

    /**
     * Retrieves the name of the road that connects two towns.
     * 
     * @param town1 the name of the first town
     * @param town2 the name of the second town
     * @return the road name if found, otherwise null
     */
    @Override
    public String getRoad(String town1, String town2) {
        Town source = getTown(town1);
        Town destination = getTown(town2);
        Road road = graph.getEdge(source, destination);
        return (road != null) ? road.getName() : null;
    }

    /**
     * Adds a town (vertex) to the graph.
     * 
     * @param v the name of the town
     * @return true if the town was successfully added
     */
    @Override
    public boolean addTown(String v) {
        Town town = new Town(v);
        return graph.addVertex(town);
    }

    /**
     * Retrieves a Town object by its name.
     * 
     * @param name the name of the town
     * @return the Town object if found, otherwise null
     */
    @Override
    public Town getTown(String name) {
        for (Town town : graph.vertexSet()) {
            if (town.getName().equals(name)) {
                return town;
            }
        }
        return null;
    }

    /**
     * Checks whether a town exists in the graph.
     * 
     * @param v the name of the town
     * @return true if the town exists, otherwise false
     */
    @Override
    public boolean containsTown(String v) {
        return graph.containsVertex(new Town(v));
    }

    /**
     * Checks whether a road connection exists between two towns.
     * 
     * @param town1 the name of the first town
     * @param town2 the name of the second town
     * @return true if the road exists, otherwise false
     */
    @Override
    public boolean containsRoadConnection(String town1, String town2) {
        Town source = getTown(town1);
        Town destination = getTown(town2);
        return graph.containsEdge(source, destination);
    }

    /**
     * Returns a list of all road names in the graph, sorted alphabetically.
     * 
     * @return an ArrayList of road names
     */
    @Override
    public ArrayList<String> allRoads() {
        ArrayList<String> roadList = new ArrayList<>();
        for (Road road : graph.edgeSet()) {
            roadList.add(road.getName());
        }
        Collections.sort(roadList);
        return roadList;
    }

    /**
     * Deletes a road connection between two towns.
     * 
     * @param town1 the name of the first town
     * @param town2 the name of the second town
     * @param road the name of the road
     * @return true if the road was successfully deleted, otherwise false
     */
    @Override
    public boolean deleteRoadConnection(String town1, String town2, String road) {
        Town source = getTown(town1);
        Town destination = getTown(town2);
        Road removedRoad = graph.removeEdge(source, destination, -1, road);
        return removedRoad != null;
    }

    /**
     * Deletes a town (vertex) from the graph.
     * 
     * @param v the name of the town
     * @return true if the town was successfully deleted, otherwise false
     */
    @Override
    public boolean deleteTown(String v) {
        Town town = getTown(v);
        return graph.removeVertex(town);
    }

    /**
     * Returns a list of all town names in the graph, sorted alphabetically.
     * 
     * @return an ArrayList of town names
     */
    @Override
    public ArrayList<String> allTowns() {
        ArrayList<String> townList = new ArrayList<>();
        for (Town town : graph.vertexSet()) {
            townList.add(town.getName());
        }
        Collections.sort(townList);
        return townList;
    }

    /**
     * Returns the shortest path between two towns as a list of steps.
     * 
     * @param town1 the name of the starting town
     * @param town2 the name of the destination town
     * @return an ArrayList of Strings describing the path
     */
    @Override
    public ArrayList<String> getPath(String town1, String town2) {
        Town source = getTown(town1);
        Town destination = getTown(town2);
        List<String> path = graph.shortestPath(source, destination);
        return new ArrayList<>(path);
    }

    /**
     * Populates the graph by reading towns and roads from a file.
     * Each line in the file should be in the format: road-name,distance;town1;town2
     * 
     * @param selectedFile the file to read
     * @throws FileNotFoundException if the file is not found
     */
    public void populateTownGraph(File selectedFile) throws FileNotFoundException {
        Scanner scanner = new Scanner(selectedFile);

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] parts = line.split(";");
            if (parts.length == 3) {
                String[] roadParts = parts[0].split(",");
                String roadName = roadParts[0];
                int distance = Integer.parseInt(roadParts[1]);
                String town1 = parts[1];
                String town2 = parts[2];

                addTown(town1);
                addTown(town2);
                addRoad(town1, town2, distance, roadName);
            }
        }
        scanner.close();
    }
}

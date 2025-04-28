import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import java.util.Set;
import java.util.ArrayList;

/**
 * This class tests the functionality of the Graph class,
 * including adding edges, finding shortest paths, and removing vertices.
 * 
 * It uses three sample Town objects and simulates a simple graph structure.
 * 
 * Author: Your Name
 */
public class Graph_STUDENT_Test {
    private Graph graph;
    private Town town1, town2, town3;

    /**
     * Sets up the test environment before each test.
     * Creates a new Graph and adds two Towns and one Road.
     */
    @Before
    public void setUp() {
        graph = new Graph();
        town1 = new Town("A");
        town2 = new Town("B");
        town3 = new Town("C");

        graph.addVertex(town1);
        graph.addVertex(town2);
        graph.addEdge(town1, town2, 10, "Road_AB");
    }

    /**
     * Tests adding an edge and retrieving it using getEdge.
     * Verifies that the correct Road is retrieved with expected properties.
     */
    @Test
    public void testAddEdgeAndGetEdge() {
        Road road = graph.getEdge(town1, town2);
        assertNotNull(road);
        assertEquals("Road_AB", road.getName());
        assertEquals(10, road.getDistance());
    }

    /**
     * Tests finding the shortest path between two towns.
     * Adds an additional vertex and edge and verifies that a path exists.
     */
    @Test
    public void testShortestPath() {
        graph.addVertex(town3);
        graph.addEdge(town2, town3, 5, "Road_BC");
        ArrayList<String> path = graph.shortestPath(town1, town3);

        assertFalse(path.isEmpty());
    }

    /**
     * Tests removing a vertex from the graph.
     * Verifies that the vertex no longer exists after removal.
     */
    @Test
    public void testRemoveVertex() {
        assertTrue(graph.removeVertex(town2));
        assertFalse(graph.containsVertex(town2));
    }
}

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;

/**
 * This class tests the functionality of the TownGraphManager class,
 * including adding towns and roads, listing all towns and roads, 
 * deleting road connections, and finding paths between towns.
 * 
 * It simulates a simple town graph and verifies the correct operations.
 * 
 * Author: Your Name
 */
public class TownGraphManager_STUDENT_Test {
    private TownGraphManager manager;

    /**
     * Sets up the test environment before each test.
     * Initializes a TownGraphManager and adds two towns and one road.
     */
    @Before
    public void setUp() {
        manager = new TownGraphManager();
        manager.addTown("X");
        manager.addTown("Y");
        manager.addRoad("X", "Y", 15, "XY Road");
    }

    /**
     * Tests adding a town and a road, and verifies that they exist in the graph.
     */
    @Test
    public void testAddTownAndRoad() {
        assertTrue(manager.containsTown("X"));
        assertEquals("XY Road", manager.getRoad("X", "Y"));
    }

    /**
     * Tests retrieving all towns and all roads from the graph,
     * ensuring that they are correctly listed.
     */
    @Test
    public void testAllTownsAndRoads() {
        ArrayList<String> towns = manager.allTowns();
        ArrayList<String> roads = manager.allRoads();
        assertTrue(towns.contains("X"));
        assertTrue(roads.contains("XY Road"));
    }

    /**
     * Tests deleting a road connection between two towns
     * and verifies that the connection no longer exists afterward.
     */
    @Test
    public void testDeleteRoadConnection() {
        assertTrue(manager.deleteRoadConnection("X", "Y", "XY Road"));
        assertFalse(manager.containsRoadConnection("X", "Y"));
    }

    /**
     * Tests finding the shortest path between two towns
     * after adding an additional town and road.
     */
    @Test
    public void testGetPath() {
        manager.addTown("Z");
        manager.addRoad("Y", "Z", 20, "YZ Road");
        ArrayList<String> path = manager.getPath("X", "Z");

        assertFalse(path.isEmpty());
    }
}

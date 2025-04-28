import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 * This class tests the functionality of the Road class,
 * including methods for contains, equals, compareTo, and toString.
 * 
 * It simulates two towns connected by roads and checks
 * the correct behavior of the Road class.
 * 
 * Author: Your Name
 */
public class Road_STUDENT_Test {
    private Town town1;
    private Town town2;
    private Road road1;
    private Road road2;

    /**
     * Sets up the test environment before each test.
     * Creates two towns and two roads (with reversed directions but same properties).
     */
    @Before
    public void setUp() {
        town1 = new Town("Bethesda");
        town2 = new Town("Silver Spring");
        road1 = new Road(town1, town2, 8, "Route 410");
        road2 = new Road(town2, town1, 8, "Route 410"); // same road, reversed
    }

    /**
     * Tests whether the road correctly identifies if it contains a specific town.
     */
    @Test
    public void testContains() {
        assertTrue(road1.contains(town1));
        assertTrue(road1.contains(town2));
    }

    /**
     * Tests the equals method to verify that roads connecting the same towns
     * (regardless of direction) are considered equal.
     */
    @Test
    public void testEquals() {
        assertTrue(road1.equals(road2)); // undirected
    }

    /**
     * Tests the compareTo method by comparing two roads based on their distance.
     */
    @Test
    public void testCompareTo() {
        Road shorterRoad = new Road(town1, town2, 5, "Short Road");
        assertTrue(shorterRoad.compareTo(road1) < 0);
    }

    /**
     * Tests the toString method to ensure the road description includes the road name.
     */
    @Test
    public void testToString() {
        assertTrue(road1.toString().contains("Route 410"));
    }
}

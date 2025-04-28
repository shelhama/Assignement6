import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 * This class tests the functionality of the Town class,
 * including equals, compareTo, and toString methods.
 * 
 * It uses sample Town objects to verify correct behavior.
 * 
 * Author: Your Name
 */
public class Town_STUDENT_Test {
    private Town town1;
    private Town town2;
    private Town town3;

    /**
     * Sets up the test environment before each test.
     * Initializes three Town objects, where town1 and town3 have the same name.
     */
    @Before
    public void setUp() {
        town1 = new Town("Rockville");
        town2 = new Town("Gaithersburg");
        town3 = new Town("Rockville"); // same name as town1
    }

    /**
     * Tests the equals method to verify that towns with the same name are considered equal,
     * and towns with different names are not considered equal.
     */
    @Test
    public void testEquals() {
        assertTrue(town1.equals(town3));
        assertFalse(town1.equals(town2));
    }

    /**
     * Tests the compareTo method to verify correct lexicographical ordering
     * based on the town names.
     */
    @Test
    public void testCompareTo() {
        assertTrue(town2.compareTo(town1) < 0);
        assertEquals(0, town1.compareTo(town3));
    }

    /**
     * Tests the toString method to ensure it returns the correct town name.
     */
    @Test
    public void testToString() {
        assertEquals("Rockville", town1.toString());
    }
}

import java.util.Objects;

/**
 * This class represents a road that connects two towns.
 * Each road has a name, a distance (in miles), a source town, and a destination town.
 * Roads are considered undirected (i.e., source to destination is the same as destination to source).
 * 
 * Author: Your Name
 */
public class Road implements Comparable<Road> {
    private Town source;
    private Town destination;
    private int distance;
    private String name;

    /**
     * Constructs a new Road object with specified source and destination towns, distance, and name.
     * 
     * @param source the source Town
     * @param destination the destination Town
     * @param distance the distance of the road
     * @param name the name of the road
     */
    public Road(Town source, Town destination, int distance, String name) {
        this.source = source;
        this.destination = destination;
        this.distance = distance;
        this.name = name;
    }

    /**
     * Constructs a new Road object with a default distance of 1 mile.
     * 
     * @param source the source Town
     * @param destination the destination Town
     * @param name the name of the road
     */
    public Road(Town source, Town destination, String name) {
        this(source, destination, 1, name); // Default distance is 1
    }

    /**
     * Gets the source town of this road.
     * 
     * @return the source Town
     */
    public Town getSource() {
        return source;
    }

    /**
     * Gets the destination town of this road.
     * 
     * @return the destination Town
     */
    public Town getDestination() {
        return destination;
    }

    /**
     * Gets the name of this road.
     * 
     * @return the road name
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the distance of this road in miles.
     * 
     * @return the distance
     */
    public int getDistance() {
        return distance;
    }

    /**
     * Compares two roads based on their distance.
     * 
     * @param other the other Road to compare
     * @return a negative integer, zero, or a positive integer as this road's distance
     *         is less than, equal to, or greater than the other road's distance
     */
    @Override
    public int compareTo(Road other) {
        return this.distance - other.distance;
    }

    /**
     * Determines whether two roads are equal.
     * Roads are considered equal if they connect the same two towns, regardless of direction.
     * 
     * @param obj the object to compare
     * @return true if the roads are equal, false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Road)) return false;
        Road other = (Road) obj;
        return (this.source.equals(other.source) && this.destination.equals(other.destination)) ||
               (this.source.equals(other.destination) && this.destination.equals(other.source));
    }

    /**
     * Generates a hash code based on the source and destination towns.
     * 
     * @return the hash code
     */
    @Override
    public int hashCode() {
        return Objects.hash(source, destination);
    }

    /**
     * Returns a string representation of the road, including its name,
     * source town, destination town, and distance.
     * 
     * @return a string describing the road
     */
    @Override
    public String toString() {
        return name + " connects " + source + " and " + destination + " and is " + distance + " miles long";
    }

    /**
     * Checks whether this road connects to a given town.
     * 
     * @param town the Town to check
     * @return true if the road contains the town, false otherwise
     */
    public boolean contains(Town town) {
        return source.equals(town) || destination.equals(town);
    }
}

import java.util.Objects;

/**
 * This class represents a town with a name.
 * It provides functionality to compare towns, check equality, 
 * and represent the town as a string.
 * 
 * Author: Your Name
 */
public class Town implements Comparable<Town> {
    private String name;

    /**
     * Constructs a new Town object with the specified name.
     * 
     * @param name the name of the town
     */
    public Town(String name) {
        this.name = name;
    }

    /**
     * Copy constructor that creates a new Town object based on another Town.
     * 
     * @param templateTown the Town to copy
     */
    public Town(Town templateTown) {
        this.name = templateTown.name;
    }

    /**
     * Gets the name of the town.
     * 
     * @return the name of the town
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the town.
     * 
     * @param name the new name of the town
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Compares this town to another town alphabetically by name.
     * 
     * @param other the other Town to compare to
     * @return a negative integer, zero, or a positive integer as this town's name
     *         is less than, equal to, or greater than the specified town's name
     */
    @Override
    public int compareTo(Town other) {
        return this.name.compareTo(other.name);
    }

    /**
     * Determines whether two towns are equal.
     * Two towns are considered equal if their names are the same.
     * 
     * @param obj the object to compare
     * @return true if the towns have the same name, false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Town)) return false;
        Town other = (Town) obj;
        return this.name.equals(other.name);
    }

    /**
     * Generates a hash code for the town based on its name.
     * 
     * @return the hash code
     */
    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    /**
     * Returns the name of the town as its string representation.
     * 
     * @return the name of the town
     */
    @Override
    public String toString() {
        return name;
    }
}

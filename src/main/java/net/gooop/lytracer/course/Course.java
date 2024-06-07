/*
 * Name: Course
 * Description: Class to hold course data.
 * Author(s): Gooop
 * License: MIT
 */
package net.gooop.lytracer.course;

// Bukkit/Spigot/Paper Specific Imports
import org.bukkit.Location;

public class Course {
    private Location startLocation;
    private Location endLocation;

    private int numCheckpoints;

    public Course() {
        numCheckpoints = 3;
        
    }

    /**
     * Getter for numCheckpoints
     */
    public int getNumCheckpoints() {
        return numCheckpoints;
    }
    
    /**
     * Getter for startLocation
     */
    public Location getStartLocation() {
        return startLocation;
    }
    
    /**
     * Getter for endLocation
     */
    public Location getEndLocation() {
        return endLocation;
    }

}

/*
 * Name: Course
 * Description: Class to hold course data.
 * Author(s): Gooop
 * License: MIT
 */
package net.gooop.lytracer.course;

// Bukkit/Spigot/Paper Specific Imports
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.Bukkit; // TODO: remove when locations are properly handled

public class Course {
    private Location startLocation;
    private Location endLocation;

    private int numCheckpoints;

    public Course() {
        numCheckpoints = 3;
        World world = Bukkit.getServer().getWorld("world");
        startLocation = new Location(world, 100.0, 150.0, 100.0);
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

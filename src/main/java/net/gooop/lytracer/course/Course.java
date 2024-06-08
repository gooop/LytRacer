/*
 * Name: Course
 * Description: Class to hold course data.
 * Author(s): Gooop
 * License: MIT
 */
package net.gooop.lytracer.course;

// Bukkit/Spigot/Paper Specific Imports
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.Bukkit; // TODO: remove when locations are properly handled
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class Course {
    private Location startLocation;
    private Location endLocation;
    private Inventory courseInv;

    private int numCheckpoints;

    public Course() {
        numCheckpoints = 3;
        World world = Bukkit.getServer().getWorld("world");
        startLocation = new Location(world, 100.0, 150.0, 100.0);
        courseInv = Bukkit.createInventory(null, InventoryType.PLAYER);
        courseInv.setItem(38, new ItemStack(Material.ELYTRA)); // Set chestplate
        for (int i = 1; i <= 9; i++) {
            courseInv.setItem(i, new ItemStack(Material.FIREWORK_ROCKET));
        }
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

    /**
     * Getter for PlayerInventory
     */
    public Inventory getCourseInv() {
        return courseInv;
    }

}

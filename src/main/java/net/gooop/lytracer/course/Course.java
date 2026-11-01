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
import org.bukkit.Bukkit; // TODO: remove when locations are properly handled
import org.bukkit.inventory.ItemStack;

public class Course {
    // Layout matches PlayerInventory#getContents():
    // 0-35 storage (0-8 is the hotbar), 36-39 armor, 40 off hand.
    private static final int PLAYER_INVENTORY_SIZE = 41;
    private static final int CHESTPLATE_SLOT = 38;

    private Location startLocation;
    private Location endLocation;
    private ItemStack[] courseContents;

    private int numCheckpoints;

    public Course() {
        numCheckpoints = 3;
        World world = Bukkit.getServer().getWorld("world");
        startLocation = new Location(world, 100.0, 150.0, 100.0);
        // Note: This used to be a Bukkit.createInventory(null, InventoryType.PLAYER)
        // inventory, but modern servers reject creating non-creatable inventory
        // types (see InventoryType#isCreatable), so the template is a plain
        // ItemStack array instead.
        courseContents = new ItemStack[PLAYER_INVENTORY_SIZE];
        courseContents[CHESTPLATE_SLOT] = new ItemStack(Material.ELYTRA); // Set chestplate
        for (int i = 1; i <= 9; i++) {
            courseContents[i] = new ItemStack(Material.FIREWORK_ROCKET);
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
     * Getter for the course inventory contents template
     */
    public ItemStack[] getCourseContents() {
        return courseContents;
    }

}

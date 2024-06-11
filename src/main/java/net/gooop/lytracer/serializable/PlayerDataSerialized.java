/*
 * Name: PlayerDataSerialized
 * Description: Helper class for serializing PlayerData
 * Author(s): Gooop
 * License: MIT
 */

package net.gooop.lytracer.serializable;

// Bukkit/Spigot/Paper Specific Imports
import org.bukkit.Location;
import org.bukkit.configuration.serialization.ConfigurationSerializable;
import org.bukkit.inventory.ItemStack;

// Misc Imports
import java.util.Map;
import java.util.HashMap;
import java.util.List;

/**
 * Helper class to store player data in yml.
 */
public class PlayerDataSerialized implements ConfigurationSerializable {
    public Location playerLocation;
    public ItemStack[] playerInventory;

    public PlayerDataSerialized(Location loc, ItemStack[] inv) {
        this.playerLocation = loc;
        this.playerInventory = inv;
    }

    @Override
    public Map<String, Object> serialize() {
        Map<String, Object> data = new HashMap<String, Object>();
        data.put("playerLocation", playerLocation);
        data.put("playerInventory", playerInventory);
        return data;
    }

    public static PlayerDataSerialized deserialize(Map<String, Object> data) {
        Location location = null;
        ItemStack[] inventory = null;
        if (data.containsKey("playerLocation")) {
            location = (Location) data.get("playerLocation");
        }
        if (data.containsKey("playerInventory")) {
            Object inventoryObject = data.get("playerInventory");
            if (inventoryObject instanceof List<?>) {
                List<?> inventoryList = (List<?>) inventoryObject;
                inventory = inventoryList.toArray(new ItemStack[0]);
            }
        }
        return new PlayerDataSerialized(location, inventory);
    }
}
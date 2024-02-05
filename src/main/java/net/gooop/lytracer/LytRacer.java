/*
 * Name: LytRacer
 * Description: Main class for the LytRacer plugin.
 * Author(s): Gooop
 * License: MIT
 */

// Package name
package net.gooop.lytracer;

// Bukkit/Spigot/Paper Specific Imports
import org.bukkit.plugin.java.JavaPlugin;

// LytRacer Specific Imports
import net.gooop.lytracer.commands.CommandLyt;

public class LytRacer extends JavaPlugin {
    // Called when plugin is first enabled
    @Override
    public void onEnable() {
        // Register our commands
        this.getCommand("lyt").setExecutor(new CommandLyt());
    }

    // Called when plugin is disabled
    @Override
    public void onDisable() {
    }
}
/*
 * Name: LytCommand
 * Description: Class for lyt command.
 * Author(s): Gooop
 * License: MIT (See LICENSE)
 */

package net.gooop.lytracer.commands;

// Bukkit/Spigot/Paper Specific Imports
import org.bukkit.command.CommandSender;
// import org.bukkit.Material;
// import org.bukkit.command.*;
// import org.bukkit.entity.Player;
// import org.bukkit.inventory.ItemStack;

import net.gooop.lytracer.LytRacer;

abstract public class LytCommand {
    /**
     * Get the command label. Trims the "Command" from the label
     * @return String, command label. 
     */
    public String getLabel() {
        return this.getClass().getSimpleName()
                .replaceFirst("Command$", "")
                .toLowerCase();
    }

    /**
     * Executes the command.
     * @param plugin The plugin singleton
     * @param sender The sender of the command
     * @param args Any args
     */
    public abstract void run(LytRacer plugin, CommandSender sender, String[] args);

    /**
     * Returns a usage string. Should be implemented by every command. The help instance for the command should show detailed usage.
     * @return String, usage string. Should be formatted like: "Usage: command <necessary> [optional|or|optional]"
     */
    public abstract String usage();
    
    /**
     * Should print help and detailed usage for a command.
     * @param plugin The plugin singleton
     * @param sender The sender of the command
     * @param args Any args
     */
    public abstract void help(LytRacer plugin, CommandSender sender, String[] args);
}

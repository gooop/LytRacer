/*
 * Name: LytCommand
 * Description: Class for lyt command.
 * Author(s): Gooop
 * License: MIT (See LICENSE)
 */

package net.gooop.lytracer.commands;

// Bukkit/Spigot/Paper Specific Imports
import org.bukkit.command.CommandSender;

// Misc Imports
import java.util.ArrayList;

// LytRacer Specific Imports
import net.gooop.lytracer.LytRacer;

abstract public class LytCommand {
    /**
     * Get the command label. Trims the "Command" from the label
     * 
     * @return String, command label.
     */
    public String getLabel() {
        return this.getClass().getSimpleName()
                .replaceFirst("Command$", "")
                .toLowerCase();
    }

    /**
     * Executes the command.
     * 
     * @param plugin The plugin singleton
     * @param sender The sender of the command
     * @param args   Any args
     */
    public abstract void run(LytRacer plugin, CommandSender sender, String[] args);

    /**
     * @return String, command description
     */
    public String getDescription() {
        return "Description for command not implemented.";
    }

    /**
     * Returns a usage string. Should be implemented by every command. The help
     * instance for the command should show detailed usage.
     * 
     * @return String, usage string. Should be formatted like: "Usage: command
     *         <necessary> [optional|or|optional]"
     */
    public abstract String usage();

    /**
     * Should print help and detailed usage for a command.
     * 
     * @param plugin The plugin singleton
     * @param sender The sender of the command
     * @param args   Any args
     */
    public abstract void help(LytRacer plugin, CommandSender sender, String[] args);

    /**
     * This function gets and returns the list of suggestions for the next arg of a
     * given command. This is for use in implementing onTabComplete.
     * For example, if the implementing class is "StartCommand," then this would
     * return the list of options available after
     * typing /lyt start. This should be true for n arguments, so if there are
     * options for an arg3 when typing "/lyt start arg2"
     * it should return the list of options for arg3.
     * 
     * @param args the args in the command starting with arg0 being the command
     *             itself.
     * @return ArrayList, options for the next arg.
     */
    public abstract ArrayList<String> tabCompleteSuggestions(String[] args);
}

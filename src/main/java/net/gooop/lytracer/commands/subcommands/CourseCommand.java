/*
 * Name: CourseCommand
 * Description: Command to show and edit courses. Has subcommands.
 * Author(s): Gooop
 * License: MIT
 */

package net.gooop.lytracer.commands.subcommands;

// Bukkit/Spigot/Paper Specific Imports
import org.bukkit.command.CommandSender;

// Misc Imports
import java.util.ArrayList;

// LytRacer Specific Imports
import net.gooop.lytracer.LytRacer;
import net.gooop.lytracer.commands.CommandInfo;
import net.gooop.lytracer.commands.LytCommand;

@CommandInfo(name = "course")
public class CourseCommand extends LytCommand {
    private final String description = "Command to show and edit courses.";

    @Override
    public void run(LytRacer plugin, CommandSender sender, String[] args) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'run'");
    }

    @Override
    public String usage() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'usage'");
    }

    @Override
    public void help(LytRacer plugin, CommandSender sender, String[] args) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'help'");
    }

    @Override
    public ArrayList<String> tabCompleteSuggestions(String[] args) {
        // TODO Auto-generated method stub
        ArrayList<String> suggestions = new ArrayList<>();
        suggestions.add("");
        return suggestions;
    }

    @Override
    public String getDescription() {
        return description;
    }
    
}

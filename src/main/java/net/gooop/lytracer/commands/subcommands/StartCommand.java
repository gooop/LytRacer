/*
 * Name: startCommand
 * Description: Command to start a game
 * Author(s): Gooop
 * License: MIT
 */
package net.gooop.lytracer.commands.subcommands;


// LytRacer Specific Imports
import net.gooop.lytracer.commands.LytCommand;
import net.gooop.lytracer.LytRacer;
import net.gooop.lytracer.commands.CommandInfo;

// Bukkit/Spigot/Paper Specific Imports
import org.bukkit.command.CommandSender;

@CommandInfo(name = "start")
public class StartCommand extends LytCommand {
    @Override
    public void run(LytRacer plugin, CommandSender sender, String[] args) {
        // TODO: implement
    }

    @Override
    public String usage() {
        //TODO: implement
        return "";
    }

    @Override
    public void help(LytRacer plugin, CommandSender sender, String[] args) {
        //TODO: implement
    }
}

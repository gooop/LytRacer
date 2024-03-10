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

// Misc imports
import java.util.ArrayList;

// Bukkit/Spigot/Paper Specific Imports
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@CommandInfo(name = "start")
public class StartCommand extends LytCommand {
    private final String description = "Starts a game.";

    @Override
    public void run(LytRacer plugin, CommandSender sender, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;

            plugin.getLogger().info("Calling startNewGame with UUID: " + player.getUniqueId().toString());
            boolean result = plugin.startNewGame(player);
            if (!result) {
                player.sendMessage("§3You have already started a game!§r");
            }
        }
        else {
            //TODO: Implement command block?
            plugin.getLogger().info("Error, sender of start command needs to be a player.");
        }
    }

    @Override
    public String getDescription() {
        return description;
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

    @Override
    public ArrayList<String> tabCompleteSuggestions(String[] args) {
        // TODO Auto-generated method stub
        ArrayList<String> suggestions = new ArrayList<>();
        suggestions.add("startsuggest");
        return suggestions;
    }
}

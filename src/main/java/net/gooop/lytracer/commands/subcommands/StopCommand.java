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
import java.util.UUID;

// Bukkit/Spigot/Paper Specific Imports
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@CommandInfo(name = "stop")
public class StopCommand extends LytCommand {
    private final String description = "Stops an ongoing game.";

    @Override
    public void run(LytRacer plugin, CommandSender sender, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            UUID gameId = player.getUniqueId();

            plugin.getLogger().info("Calling stopGame with UUID: " + gameId.toString());
            boolean result = plugin.stopGame(gameId);
            if (!result) {
                player.sendMessage("§3You are not in a race!§r");
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

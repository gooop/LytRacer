/*
 * Name: getGamesCommand
 * Description: Command to start a game
 * Author(s): Gooop
 * License: MIT
 */
package net.gooop.lytracer.commands.subcommands;

// Bukkit/Spigot/Paper Specific Imports
import org.bukkit.command.CommandSender;

// Misc imports
import java.util.ArrayList;
import java.util.UUID;
import java.util.Map.Entry;

// LytRacer Specific Imports
import net.gooop.lytracer.commands.LytCommand;
import net.gooop.lytracer.game.Game;
import net.gooop.lytracer.LytRacer;
import net.gooop.lytracer.commands.CommandInfo;

@CommandInfo(name = "listgames")
public class ListGamesCommand extends LytCommand {
    private final String description = "Lists active games.";

    @Override
    public void run(LytRacer plugin, CommandSender sender, String[] args) {
        sender.sendMessage("§3================== Active Games ==================§r");
        for (Entry<UUID, Game> game : plugin.getGames().entrySet()) {
            sender.sendMessage("Game: " + game.getKey().toString());
        }
        sender.sendMessage("§3================== Active Games ==================§r");
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public String usage() {
        // TODO: implement
        return "";
    }

    @Override
    public void help(LytRacer plugin, CommandSender sender, String[] args) {
        // TODO: implement
    }

    @Override
    public ArrayList<String> tabCompleteSuggestions(String[] args) {
        // TODO Auto-generated method stub
        ArrayList<String> suggestions = new ArrayList<>();
        suggestions.add("");
        return suggestions;
    }

}

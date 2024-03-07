/*
 * Name: VersionCommand
 * Description: 
 * Author(s): Gooop
 * License: MIT
 */

package net.gooop.lytracer.commands.subcommands;

// LytRacer Specific Imports
import net.gooop.lytracer.commands.LytCommand;
import net.gooop.lytracer.LytRacer;
import net.gooop.lytracer.commands.CommandInfo;

import java.util.ArrayList;

// Bukkit/Spigot/Paper Specific Imports
import org.bukkit.command.CommandSender;

@CommandInfo(name = "version")
public class VersionCommand extends LytCommand {
    @Override
    public void run(LytRacer plugin, CommandSender sender, String[] args) {
        String version = LytRacer.getVersion();
        sender.sendMessage("LytRacer Version " + version + " by Gooop. §2Donate: https://ko-fi.com/gooop");
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
        return new ArrayList<>();
    }
}

/*
 * Name: CommandLyt
 * Description: Class for lyt command.
 * Author(s): Gooop
 * License: MIT (See LICENSE)
 */


package net.gooop.lytracer.commands;

import org.bukkit.Material;
import org.bukkit.command.*;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class CommandLyt implements CommandExecutor {
    // Called when somebody uses our command
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        // If a player sends the command
        if (sender instanceof Player) {
            Player player = (Player) sender;

            ItemStack diamond = new ItemStack(Material.DIAMOND);
            ItemStack bricks = new ItemStack(Material.BRICK);
            bricks.setAmount(20);
            player.getInventory().addItem(bricks, diamond);
            return true;
        }

        // If the console sends the command
        if (sender instanceof ConsoleCommandSender) {
            ConsoleCommandSender console = (ConsoleCommandSender) sender;
        }

        // If a command block sends the command
        if (sender instanceof BlockCommandSender) {
            BlockCommandSender block = (BlockCommandSender) sender;
        }

        return false;
    }
}

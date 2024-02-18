/*
 * Name: CommandRouter
 * Description: Routes commands to their correct implementation
 * Author(s): Gooop
 * License: MIT
 */

// Package Name
package net.gooop.lytracer.commands;

// Bukkit/Spigot/Paper Specific Imports
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

// Misc Imports
import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;
import org.reflections.Reflections;

import net.gooop.lytracer.LytRacer;

public class CommandRouter implements CommandExecutor, TabCompleter {
    private Set<Class<?>> annotatedClasses;
    private Map<String, LytCommand> commands;

    private final LytRacer plugin;

    /**
     * Constructor for CommandRouter class that includes the plugin singleton
     * @param plugin The player associated with this game instance.
     */
    public CommandRouter(LytRacer plugin) {
        this.plugin = plugin;
        this.commands = new HashMap<String, LytCommand>();
    }

    /**
     * Uses reflection to grab all subcommands annotated with @CommandInfo values
     */
    public void scanForSubCommands() {
        Reflections reflections = new Reflections("net.gooop.lytracer.commands.subcommands");
        annotatedClasses = reflections.getTypesAnnotatedWith(CommandInfo.class);
    }

    /**
     * Initializes all the subcommands
     */
    public void initializeSubCommands() {
        // For each class that reflection found with the annotation, cast it to a class that extends LytCommand
        for (Class<?> annotatedClass : annotatedClasses) {
            if (LytCommand.class.isAssignableFrom(annotatedClass)) {
                Class<? extends LytCommand> subCommandClass = annotatedClass.asSubclass(LytCommand.class);

                try {
                    LytCommand subCommandInstance = subCommandClass.getDeclaredConstructor().newInstance();
                    String subCommandLabel = subCommandInstance.getLabel(); // TODO: implement this: ".getLabels().forEach(label -> commands.put(label, command));" for multiple aliases
                    commands.put(subCommandLabel, subCommandInstance);
                    plugin.getLogger().info("Registered sub-command: " + subCommandLabel);
                }
                catch (Exception e) {
                    System.out.println("Exception when trying to initialize subcommand subclass");
                    e.printStackTrace();
                }
            }
        }
    }
    
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 0) {
            // No args provided. Special case: do Info/Version command and tell user how to get help
            LytCommand version = commands.get("version");
            if (version != null) {
                version.run(plugin, sender, args);
                sender.sendMessage("For help with LytRacer commands, type /lyt help");
            }
            return true;
        }
        if (commands.containsKey(args[0])) {
            // Well-formed/Normal case
            commands.get(args[0]).run(plugin, sender, args);
            return true;
        }
        else {
            // Malformed case
            sender.sendMessage("§cError processing command. Unrecognized argument: " + args[0]);
            sender.sendMessage("For help with LytRacer commands, type /lyt help");
            return true;
        }
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        // TODO Auto-generated method stub
        List<String> dummyTabComplete = new ArrayList<>();
        dummyTabComplete.add("t");
        dummyTabComplete.add("o");
        dummyTabComplete.add("d");
        dummyTabComplete.add("o");
        return dummyTabComplete;
        //throw new UnsupportedOperationException("Unimplemented method 'onTabComplete'");
    }
    
}

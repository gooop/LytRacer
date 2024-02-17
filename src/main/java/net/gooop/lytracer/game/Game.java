/*
 * Name: Game
 * Description: Class to handle a race. All game logic is done here.
 * Author(s): Gooop
 * License: MIT
 */
package net.gooop.lytracer.game;

// Bukkit/Spigot/Paper Specific Imports
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.entity.Player;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;

// Misc imports
import java.util.UUID;

// LytRacer Specific Imports
import net.gooop.lytracer.*;
import net.gooop.lytracer.timer.*;
import net.gooop.lytracer.course.*;

public class Game {
    private Timer timer;
    private UUID id;
    private Course course;
    private Player player;
    private final LytRacer plugin;

    /**
     * Constructor for Game class that includes id and course
     * @param id The game ID assigned to this game instance.
     * @param course The course associated with this game instance.
     * @param player The player associated with this game instance.
     */
    public Game(UUID id, Course course, Player player, LytRacer plugin) {
        this.id = id;
        this.course = course;
        this.player = player;
        this.plugin = plugin;
    }

    /**
     * Starts the game
     */
    public void start() {
        timer = new Timer(course.getNumCheckpoints());

        // TODO: Arrest player movement
        // Anonymous repeating BukkitRunnable task
        new BukkitRunnable() {
            int counter = 3;

            @Override
            public void run() {
                if (counter > 0) {
                    player.sendTitle("§4" + String.valueOf(counter), "§lStarting!", 1, 20, 1);
                    player.spigot().sendMessage(ChatMessageType.ACTION_BAR,
                            TextComponent.fromLegacyText(String.valueOf(timer.getTimeSeconds())));
                    counter--;
                }
                else {
                    player.sendTitle("§2GO!", "", 1, 20, 1);
                    timer.startTimer();
                    player.spigot().sendMessage(ChatMessageType.ACTION_BAR,
                            TextComponent.fromLegacyText(String.valueOf(timer.getTimeSeconds())));
                    super.cancel();
                    // TODO: allow player to move
                }
            }
        }.runTaskTimer(this.plugin, 0L, 20L);
    }
    
    /**
     * Stops the game
     */
    public void stop() {
        timer.stopTimer();
    }

    /**
     * Get game instance ID.
     */
    public UUID getID() {
        return id;
    }

}

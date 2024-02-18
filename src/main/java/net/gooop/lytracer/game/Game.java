/*
 * Name: Game
 * Description: Class to handle a race. All game logic is done here.
 * Author(s): Gooop
 * License: MIT
 */
package net.gooop.lytracer.game;

// Bukkit/Spigot/Paper Specific Imports
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;
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
    private UUID id;
    private Course course;
    private Player player;
    private final LytRacer plugin;

    private Timer timer;
    private BukkitTask timerTask;
    

    /**
     * Constructor for Game class that includes id, course, player, and plugin singleton
     * @param id The game ID assigned to this game instance.
     * @param course The course associated with this game instance.
     * @param player The player associated with this game instance.
     * @param plugin The plugin singleton.
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

        // Anonymous repeating BukkitRunnable tasks
        anonStartGame();
        timerTask = anonStartTimerUI();
    }
    
    /**
     * Stops the game
     */
    public void stop() {
        timer.stopTimer();
        timerTask.cancel();
    }

    /**
     * Get game instance ID.
     */
    public UUID getID() {
        return id;
    }

    // Helpers
    /**
     * Function that creates an anonymous BukkitTask that starts the game
     * @return
     */
    private BukkitTask anonStartGame() {
        BukkitTask startGameTask = new BukkitRunnable() {
            int counter = 3;

            @Override
            public void run() {
                if (counter > 0) {
                    // UI
                    player.sendTitle("§4" + String.valueOf(counter), "§lStarting!", 1, 20, 1);
                    player.spigot().sendMessage(ChatMessageType.ACTION_BAR,
                            TextComponent.fromLegacyText("Timer Starting..."));
                                
                    // Game
                    counter--;
                } else {
                    // UI
                    player.sendTitle("§2GO!", "", 1, 20, 1);

                    // Game
                    timer.startTimer();
                    // TODO: allow player to move

                    // Cancel runner
                    super.cancel();
                    

                }
            }
        }.runTaskTimer(this.plugin, 0L, 20L);

        return startGameTask;
    }

    /**
     * Function that creates an anonymous BukkitTask that repeatedly calls the timer UI
     * It ouputs the total time and last split time.
     * @return BukkitTask timer task object
     */
    private BukkitTask anonStartTimerUI() {
        BukkitTask timerTask = new BukkitRunnable() {
            @Override
            public void run() {
                // UI
                float lastSplit = timer.getLastSplitSeconds();
                String timerString = "Time: §e" + String.valueOf(timer.getTimeSeconds()) + "(s) §f§l - §r Last Split: §e" + String.valueOf(lastSplit);
                player.spigot().sendMessage(ChatMessageType.ACTION_BAR,
                        TextComponent.fromLegacyText(timerString));
                }
        }.runTaskTimer(this.plugin, 61L, 1L);
        return timerTask;
    }
}

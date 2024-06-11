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
import org.bukkit.inventory.ItemStack;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Location;

// Misc imports
import java.util.UUID;

// LytRacer Specific Imports
import net.gooop.lytracer.*;
import net.gooop.lytracer.timer.*;
import net.gooop.lytracer.course.*;
import net.gooop.lytracer.serializable.PlayerDataSerialized;

public class Game {
    private static final int TITLE_FADE = 1;
    private static final int TITLE_STAY = 20;
    private static final int START_COUNTDOWN = 3;
    private static final long SECOND_IN_TICKS = 20L; // 20 ticks is one second
    private static final long TIMER_UI_UPDATE_PERIOD = 1L;

    private UUID id;
    private Course course;
    private Player player;
    private final LytRacer plugin;
    private Location preGameLocation;
    private ItemStack[] preGameInventory;

    private Timer timer;
    private BukkitTask timerTask;
    private Boolean gameStarted = false;

    /**
     * Constructor for Game class that includes id, course, player, and plugin
     * singleton
     * 
     * @param id     The game ID assigned to this game instance.
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

    // Gameplay Functions
    /**
     * Starts the game
     */
    public void start() {
        timer = new Timer(course.getNumCheckpoints());

        // Handle player location
        preGameLocation = player.getLocation();
        player.teleport(course.getStartLocation());

        // Handle player inventory
        preGameInventory = player.getInventory().getContents();
        player.getInventory().clear();
        player.getInventory().setContents(course.getCourseInv().getContents());

        // Save player data (pos, inv) in case they disconnect
        PlayerDataSerialized playerData = new PlayerDataSerialized(preGameLocation, preGameInventory);
        plugin.getPlayerConfig().savePlayer(playerData, player.getUniqueId());

        // Anonymous repeating BukkitRunnable tasks
        anonStartGame();
        timerTask = anonStartTimerUI();
    }

    /**
     * Stops the game
     */
    public void stop() {
        // Return player to original state
        if (player.isOnline()) {
            // Handle player location
            player.teleport(preGameLocation);

            // Handle player inventory
            player.getInventory().clear();
            player.getInventory().setContents(preGameInventory);

            // Remove player data entry from yml file
            if (!plugin.getPlayerConfig().removePlayer(player.getUniqueId())) {
                plugin.getLogger().warning("Could not clear player (player UUID: " + player.getUniqueId().toString()
                        + ") data. This could cause inventory issues. Please manually remove the data for the UUID from LytRacer/playerdata.yml");
            } else {
                plugin.getPlayerConfig().save();
            }
        }

        // Stop timer and cancel task
        timer.stopTimer();
        timerTask.cancel();
    }

    // Helpers
    /**
     * Function that creates an anonymous BukkitTask that starts the game
     * 
     * @return
     */
    private BukkitTask anonStartGame() {
        BukkitTask startGameTask = new BukkitRunnable() {
            int counter = START_COUNTDOWN;

            @Override
            public void run() {
                if (counter > 0) {
                    // UI
                    player.sendTitle("§4" + String.valueOf(counter), "§lStarting!", TITLE_FADE, TITLE_STAY, TITLE_FADE);
                    player.spigot().sendMessage(ChatMessageType.ACTION_BAR,
                            TextComponent.fromLegacyText("Timer Starting..."));

                    // Game
                    counter--;
                } else {
                    // UI
                    player.sendTitle("§2GO!", "", TITLE_FADE, TITLE_STAY, TITLE_FADE);

                    // Game
                    timer.startTimer();
                    gameStarted = true;

                    // Cancel runner
                    super.cancel();

                }
            }
        }.runTaskTimer(this.plugin, 0L, SECOND_IN_TICKS);

        return startGameTask;
    }

    /**
     * Function that creates an anonymous BukkitTask that repeatedly calls the timer
     * UI
     * It ouputs the total time and last split time.
     * 
     * @return BukkitTask timer task object
     */
    private BukkitTask anonStartTimerUI() {
        BukkitTask timerTask = new BukkitRunnable() {
            @Override
            public void run() {
                // UI
                float lastSplit = timer.getLastSplitSeconds();
                String timerString = "Time: §e" + String.valueOf(timer.getTimeSeconds())
                        + "(s) §f§l - §r Last Split: §e" + String.valueOf(lastSplit);
                player.spigot().sendMessage(ChatMessageType.ACTION_BAR,
                        TextComponent.fromLegacyText(timerString));
            }
        }.runTaskTimer(this.plugin, (SECOND_IN_TICKS * START_COUNTDOWN) + 1L, TIMER_UI_UPDATE_PERIOD);
        return timerTask;
    }

    // Getters and Setters
    /**
     * Get game instance ID.
     */
    public UUID getID() {
        return id;
    }

    /**
     * Get game instance ID.
     */
    public Boolean getGameStarted() {
        return gameStarted;
    }
}

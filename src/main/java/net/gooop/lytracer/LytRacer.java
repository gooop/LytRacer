/*
 * Name: LytRacer
 * Description: Main class for the LytRacer plugin.
 * Author(s): Gooop
 * License: MIT
 */

// Package name
package net.gooop.lytracer;

// Bukkit/Spigot/Paper Specific Imports
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.configuration.serialization.ConfigurationSerialization;
import org.bukkit.entity.Player;

// Misc imports
import java.util.UUID;
import java.util.HashMap;
import java.io.File;

// LytRacer Specific Imports
import net.gooop.lytracer.commands.*;
import net.gooop.lytracer.game.Game;
import net.gooop.lytracer.serializable.PlayerDataSerialized;
import net.gooop.lytracer.course.Course;
import net.gooop.lytracer.events.EventListener;

public class LytRacer extends JavaPlugin {
    // LytRacer singleton
    private static LytRacer instance;

    // commandRouter instance
    private CommandRouter commandRouter;

    // Member variables
    private HashMap<UUID, Game> games = new HashMap<>();
    private File courseDataFile;
    private FileConfiguration courseData;
    private File playerDataFile;
    private FileConfiguration playerData;

    // Virtual Functions
    // Called when plugin is first enabled
    @Override
    public void onEnable() {
        // Assign value to singleton
        instance = this;

        this.getLogger().info("======== INITIALIZING LytRacer ========");

        // Start command router, initialize commands, and register the router
        this.getLogger().info("== Registering Commands ==");
        commandRouter = new CommandRouter(instance);
        commandRouter.scanForSubCommands();
        commandRouter.initializeSubCommands();
        this.getCommand("lyt").setExecutor(commandRouter);

        // Register event listener
        this.getLogger().info("==  Registering Events  ==");
        getServer().getPluginManager().registerEvents(new EventListener(instance), this);
        this.getLogger().info("Success");

        // Do config stuff
        this.getLogger().info("==   Creating Configs   ==");
        // Default config
        saveDefaultConfig();
        this.reloadConfig();
        // Course data config
        // ConfigurationSerialization.registerClass(CourseData.class); Not implemented yet
        createCourseData();
        // Player data config
        ConfigurationSerialization.registerClass(PlayerDataSerialized.class);
        createPlayerData();
        this.getLogger().info("Success");

        this.getLogger().info("======== LytRacer INITIALIZED ========");
    }

    // Called when plugin is disabled
    @Override
    public void onDisable() {
    }

    // Gameplay Functions
    /**
     * Starts a new game with the same UUID of the player.
     * 
     * @param player The player who is playing the game.
     */
    public boolean startNewGame(Player player) {
        Course course = new Course();
        UUID gameId = player.getUniqueId();
        Game game = new Game(gameId, course, player, this);

        boolean alreadyExists = games.containsKey(gameId);
        if (alreadyExists) {
            // This player has already started a game.
            return false;
        }

        // Add game to map and start game
        games.put(gameId, game);
        game.start();
        return true;
    }

    /**
     * Ends a game
     * 
     * @param gameId The game ID used to look up the game instance from the hashmap.
     */
    public Boolean stopGame(UUID gameId) {
        Game game = games.get(gameId);
        if (game != null) {
            game.stop();
            games.remove(gameId);
            return true;
        }
        return false;
    }

    // Helpers
    public void createCourseData() {
        String filename = "coursedata.yml";
        courseDataFile = new File(getDataFolder(), filename);
        if (!courseDataFile.exists()) {
            courseDataFile.getParentFile().mkdirs();
            saveResource(filename, false);
        }

        courseData = YamlConfiguration.loadConfiguration(courseDataFile);
    }

    public void createPlayerData() {
        String filename = "playerdata.yml";
        playerDataFile = new File(getDataFolder(), filename);
        if (!playerDataFile.exists()) {
            playerDataFile.getParentFile().mkdirs();
            saveResource(filename, false);
        }

        playerData = YamlConfiguration.loadConfiguration(playerDataFile);
    }

    // Getters and Setters
    /**
     * LytRacer singleton getter
     */
    public static LytRacer getLytRacer() {
        return instance;
    }

    /**
     * Getter for the course data
     * 
     * @param gameId
     * @return
     */
    public FileConfiguration getCourseData() {
        return courseData;
    }

    /**
     * Getter for the player data
     * 
     * @param gameId
     * @return
     */
    public FileConfiguration getPlayerData() {
        return playerData;
    }

    /**
     * Getter for course data file
     */
    public File getCourseDataFile() {
        return courseDataFile;
    }

    /**
     * Getter for player data file
     */
    public File getPlayerDataFile() {
        return playerDataFile;
    }

    /**
     * Getter for a game based on ID
     * 
     * @param gameId The ID of the game to get.
     * @return The game instance with the associated ID.
     */
    public Game getGame(UUID gameId) {
        return games.get(gameId);
    }

    /**
     * Getter for the games map.
     * 
     * @return The games map.
     */
    public HashMap<UUID, Game> getGames() {
        return games;
    }

    /**
     * LytRacer version getter
     */
    public static String getVersion() {
        Package pkg = LytRacer.class.getPackage();
        String version = (pkg != null) ? pkg.getImplementationVersion() : null;
        return (version != null) ? version : "Unknown";
    }

}
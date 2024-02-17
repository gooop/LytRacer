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

// Misc imports
import java.util.UUID;
import java.util.HashMap;

// LytRacer Specific Imports
import net.gooop.lytracer.commands.*;
import net.gooop.lytracer.game.Game;
import net.gooop.lytracer.course.Course;

public class LytRacer extends JavaPlugin {
    // Member variables
    private HashMap<UUID, Game> games = new HashMap<>();

    // Called when plugin is first enabled
    @Override
    public void onEnable() {
        // Register our commands
        this.getCommand("lyt").setExecutor(new CommandLyt());
    }

    // Called when plugin is disabled
    @Override
    public void onDisable() {
    }

    /**
     * Starts a new game
     * @param gameId The game ID to use for the game instance
     */
    public void startNewGame(UUID gameId) {
        Course course = new Course();
        Game game = new Game(gameId, course);
        games.put(gameId, game);
        game.start();
    }

    /**
     * Ends a game
     * @param gameId The game ID used to look up the game instance from the hashmap.
     */
    public void endGame(UUID gameId) {
        Game game = games.get(gameId);
        if (game != null) {
            game.stop();
            games.remove(gameId);
        }
    }

    /**
     * Getter for a game based on ID
     * @param gameId The ID of the game to get.
     * @return The game instance with the associated ID.
     */
    public Game getGame(UUID gameId) {
        return games.get(gameId);
    }
}
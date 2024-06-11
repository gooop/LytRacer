/*
 * Name: PlayerConfig
 * Description: Wrapper class for custom player config
 * Author(s): Gooop
 * License: MIT
 */

package net.gooop.lytracer.config;

// Bukkit/Spigot/Paper Specific Imports
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

// Misc Imports
import java.io.File;
import java.io.IOException;
import java.util.UUID;

// LytRacer Specific Imports
import net.gooop.lytracer.LytRacer;
import net.gooop.lytracer.serializable.PlayerDataSerialized;

// TODO: use as a starting point to implement custom wrapper for FileConfiguration class. Quick and dirty for now.

/**
 * Player Data Configuration Class
 * Implements save, reload, and load
 * Also implements bespoke save and remove methods
 */
public class PlayerConfig {
    private File playerConfigFile;
    private FileConfiguration playerConfig;
    public final LytRacer plugin;

    public PlayerConfig(LytRacer plugin) {
        this.plugin = plugin;
    }

    /**
     * Loads the Player Data File into the Player Data Configuration
     */
    public void load() {
        String filename = "playerdata.yml";
        playerConfigFile = new File(plugin.getDataFolder(), filename);
        if (!playerConfigFile.exists()) {
            playerConfigFile.getParentFile().mkdirs();
            plugin.saveResource(filename, false);
        }

        playerConfig = YamlConfiguration.loadConfiguration(playerConfigFile);
    }

    /**
     * Gets the requested object by path, returning a default value if not found.
     * <p>
     * If the Object does not exist then the specified default value will returned
     * regardless of if a default has been identified in the root Configuration.
     * 
     * @param path Path of the Object to get.
     * @param def  The default value to return if the path is not found.
     * @return Requested Object.
     */
    public Object get(String path, Object def) {
        return playerConfig.get(path, def);
    }

    /**
     * Gets the requested Object by path.
     * <p>
     * If the Object does not exist but a default value has been specified, this
     * will return the default value. If the Object does not exist and no default
     * value was specified, this will return null.
     * 
     * @param path Path of the Object to get.
     * @return Requested Object.
     */
    public Object get(String path) {
        return playerConfig.get(path);
    }

    /**
     * Checks if this ConfigurationSection contains the given path.
     * <p>
     * If the value for the requested path does not exist, the boolean parameter of
     * true has been specified, a default value for the path exists, this will
     * return true.
     * <p>
     * If a boolean parameter of false has been specified, true will only be
     * returned if there is a set value for the specified path.
     * 
     * @param path          Path to check for existence.
     * @param ignoreDefault Whether or not to ignore if a default value for the
     *                      specified path exists.
     * @return True if this section contains the requested path, or if a default
     *         value exist and the boolean parameter for this method is true.
     */
    public Boolean contains(String path, Boolean ignoreDefault) {
        return playerConfig.contains(path, ignoreDefault);
    }

    /**
     * Saves the Player Data Configuration to the Player Data File
     * 
     */
    public void save() {
        try {
            playerConfig.save(playerConfigFile);
        } catch (IOException e) {
            System.out.println("Couldn't load playerdata.yml");
        }
    }

    /**
     * Reloads the Player Data Configuration from the Player Data File
     *
     */
    public void reload() {
        playerConfig = YamlConfiguration.loadConfiguration(playerConfigFile);
    }

    /**
     * Saves the Player info to the Player Data File
     * 
     * @param player
     */
    public void savePlayer(PlayerDataSerialized playerData, UUID playerId) {
        // Possibly make async for larger servers
        this.playerConfig.set(playerId.toString(), playerData);
        this.save();
    }

    /**
     * Remove a player from the Player Data Configuration
     */
    public Boolean removePlayer(UUID playerId) {
        // Possibly make async for larger servers
        if (this.playerConfig.contains(playerId.toString())) {
            this.playerConfig.set(playerId.toString(), null);
            this.save();
            return true;
        }
        return false;
    }

    // Getters and Setters
    /**
     * Getter for the player data file
     * 
     * @return the player data file
     */
    public File getPlayerConfigFile() {
        return this.playerConfigFile;
    }

    /**
     * Getter for the player data FileConfiguration
     * 
     * @return the player data FileConfiguration
     */
    public FileConfiguration getPlayerConfig() {
        return this.playerConfig;
    }
}

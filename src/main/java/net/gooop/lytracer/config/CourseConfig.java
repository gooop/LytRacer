/*
 * Name: CourseConfig
 * Description: Wrapper class for custom course config
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

// LytRacer Specific Imports
import net.gooop.lytracer.LytRacer;
//import net.gooop.lytracer.serializable.CourseDataSerialized;

/**
 * Wrapper class for custom course config
 * <p>
 * Implements save, contains, get, reload, and load from FileConfiguration
 * <p>
 */
public class CourseConfig {
    private File courseConfigFile;
    private FileConfiguration courseConfig;
    private final LytRacer plugin;

    public CourseConfig(LytRacer plugin) {
        this.plugin = plugin;
    }

    /**
     * Loads the coursedata File into the Course Data Configuration
     */
    public void load() {
        String filename = "coursedata.yml";
        courseConfigFile = new File(plugin.getDataFolder(), filename);
        if (!courseConfigFile.exists()) {
            courseConfigFile.getParentFile().mkdirs();
            plugin.saveResource(filename, false);
        }

        courseConfig = YamlConfiguration.loadConfiguration(courseConfigFile);
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
        return courseConfig.get(path, def);
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
        return courseConfig.get(path);
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
        return courseConfig.contains(path, ignoreDefault);
    }


    /**
     * Saves the Course Data Configuration to the Course Data File
     * 
     */
    public void save() {
        try {
            courseConfig.save(courseConfigFile);
        } catch (IOException e) {
            System.out.println("Couldn't load coursedata.yml");
        }
    }


    /**
     * Reloads the Course Data Configuration from the Course Data File
     *
     */
    public void reload() {
        courseConfig = YamlConfiguration.loadConfiguration(courseConfigFile);
    }

    // Getters and Setters
    /**
     * Getter for the course data file
     * 
     * @return the course data file
     */
    public File getCourseConfigFile() {
        return this.courseConfigFile;
    }

    /**
     * Getter for the course data FileConfiguration
     * 
     * @return the course data FileConfiguration
     */
    public FileConfiguration getCourseConfig() {
        return this.courseConfig;
    }

    
}

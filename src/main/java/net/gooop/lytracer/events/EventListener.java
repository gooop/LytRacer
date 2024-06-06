/*
 * Name: EventListener
 * Description: Event listener for LytRacer
 * Author(s): Gooop
 * License: MIT
 */

package net.gooop.lytracer.events;

// Bukkit/Spigot/Paper Specific Imports
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.EventHandler;

// LytRacer Specific Imports
import net.gooop.lytracer.LytRacer;

public class EventListener implements Listener {
    private final LytRacer plugin;

    /**
     * Constructor for EventListener class that includes plugin singleton
     * @param plugin The plugin singleton.
     */
    public EventListener(LytRacer plugin) {
        this.plugin = plugin;
    }
    //TODO: override onDamageEvent for players in a game
    //TODO: override block break and place events for players in a game

    /**
     * Stop a game when a player in a game leaves.
     */
    @EventHandler
    public void onPlayerLeave(PlayerQuitEvent event) {
        plugin.stopGame(event.getPlayer().getUniqueId());
    }

    /**
     * Arrest player movement if they're in a game
     */
    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        // Arrest movement if game player is in has not started
        if (plugin.getGame(event.getPlayer().getUniqueId()).getGameStarted() == false) {
            event.setTo(event.getFrom().setDirection(event.getTo().getDirection()));
        }

    }
}

/*
 * Name: EventListener
 * Description: Event listener for LytRacer
 * Author(s): Gooop
 * License: MIT
 */

package net.gooop.lytracer.events;

// Bukkit/Spigot/Paper Specific Imports
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;

// LytRacer Specific Imports
import net.gooop.lytracer.LytRacer;
import net.gooop.lytracer.data.PlayerData;
import net.gooop.lytracer.game.Game;

public class EventListener implements Listener {
    private final LytRacer plugin;

    /**
     * Constructor for EventListener class that includes plugin singleton
     * 
     * @param plugin The plugin singleton.
     */
    public EventListener(LytRacer plugin) {
        this.plugin = plugin;
    }
    // TODO: override block break and place events for players in a game

    /**
     * Stop a game when a player in a game leaves.
     */
    @EventHandler
    public void onPlayerLeave(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        plugin.getLogger().info(Boolean.toString(plugin.getPlayerData().contains(player.getUniqueId().toString(), false)));
        plugin.stopGame(event.getPlayer().getUniqueId());
    }

    /**
     * Give player items back and reset location when player joins.
     * @param event
     */
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        plugin.getLogger().info("playerjoin");
        Player player = event.getPlayer();
        plugin.getLogger().info(player.getUniqueId().toString());
        PlayerData playerData = (PlayerData) plugin.getPlayerData().get(player.getUniqueId().toString());
        plugin.getLogger().info(Boolean.toString(plugin.getPlayerData().contains(player.getUniqueId().toString(), false)));
        if (playerData != null) {
            plugin.getLogger().info("hello");
            player.teleport(playerData.playerLocation);
            player.getInventory().clear();
            player.getInventory().setContents(playerData.playerInventory);
        }
        else {
            plugin.getLogger().info("null");
        }
        //TODO: Cast object and teleport player and set inv
    }

    /**
     * Arrest player movement if they're in a game
     */
    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        // Arrest movement if game player is in has not started (allows them to look
        // around).
        Game playerGameInstance = plugin.getGame(event.getPlayer().getUniqueId());
        if (playerGameInstance != null && !playerGameInstance.getGameStarted()) {
            event.setTo(event.getFrom().setDirection(event.getTo().getDirection()));
        }
    }

    /**
     * Prevent player from taking damage in a game.
     */
    @EventHandler
    public void onPlayerDamage(EntityDamageEvent event) {
        if (event.getEntity() instanceof Player) {
            Player player = (Player) event.getEntity();
            // Prevent damage to player in games.
            Game playerGameInstance = plugin.getGame(player.getUniqueId());
            if (playerGameInstance != null) {
                event.setCancelled(true);
            }
        }
    }
}

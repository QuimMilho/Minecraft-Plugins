package net.quimmilho.plugin.lobby.events;

import net.quimmilho.plugin.lobby.Core;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;

public class OnPlayerItemDrop implements Listener {

    @EventHandler
    public void itemDropEvent(PlayerDropItemEvent event) {
        if (event.getPlayer().getLocation().getWorld() == Bukkit.getWorld("world"))
            event.setCancelled(true);
        if (Core.getInstance().getPlayerManager().getPlayer(event.getPlayer().getName()).canBreak()) {
            event.setCancelled(false);
        }
    }

}

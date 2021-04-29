package net.quimmilho.plugin.lobby.events;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

public class OnPlayerDamage implements Listener {

    @EventHandler
    public void playerDamageEvent(EntityDamageEvent event) {
        if (event.getEntity().getLocation().getWorld() == Bukkit.getWorld("world"))
            event.setCancelled(true);
    }

}

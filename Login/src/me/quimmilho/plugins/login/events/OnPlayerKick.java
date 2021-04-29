package me.quimmilho.plugins.login.events;

import me.quimmilho.plugins.login.Core;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerKickEvent;

public class OnPlayerKick implements Listener {

    @EventHandler
    public void onPlayerKick(PlayerKickEvent event) {
        if (event.getReason().equals("disconnect.spam")) {
            event.setCancelled(true);
            Core.instance.getLogger().info("Player not kicked due to this plugin! disconnect.spam");
        }
    }

}

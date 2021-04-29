package me.quimmilho.plugins.login.events;

import me.quimmilho.plugins.login.Core;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class OnPlayerLeft implements Listener {

    @EventHandler
    public void onPlayerLeft(PlayerQuitEvent event) {
        Player p = event.getPlayer();
        if (Core.instance.getPlayer().containsPlayer(p))
            Core.instance.getPlayer().removePlayer(p);
        else
            Core.instance.getLogger().info("Player logged in! " + p.getName());
        event.setQuitMessage("");
    }

}

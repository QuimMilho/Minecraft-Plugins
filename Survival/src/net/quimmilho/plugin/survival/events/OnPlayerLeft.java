package net.quimmilho.plugin.survival.events;

import net.quimmilho.plugin.survival.Core;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class OnPlayerLeft implements Listener {

    @EventHandler
    public void onPlayerLeft(PlayerQuitEvent event) {
        if (Core.getInstance().getPlayerManager().getPlayer(event.getPlayer().getName()).isVanished()) {
            event.setQuitMessage("");
        } else {
            event.setQuitMessage(ChatColor.GRAY + "[" + ChatColor.RED + "-" + ChatColor.GRAY + "] " +
                    event.getPlayer().getName());
        }
        Core.getInstance().getPlayerManager().removePlayer(event.getPlayer().getName());
    }

}

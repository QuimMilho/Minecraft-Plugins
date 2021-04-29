package net.quimmilho.plugin.lobby.events;

import net.quimmilho.plugin.lobby.Core;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class OnPlayerLeft implements Listener {

    @EventHandler
    public void onPlayerLeft(PlayerQuitEvent event) {
        event.setQuitMessage(ChatColor.GRAY + "[" + ChatColor.RED + "-" + ChatColor.GRAY + "] " +
                event.getPlayer().getName());
        Core.getInstance().getPlayerManager().removePlayer(event.getPlayer().getName());
    }

}

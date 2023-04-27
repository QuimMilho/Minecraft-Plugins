package me.quimmilho.plugins.survival.events;

import me.quimmilho.plugins.survival.Core;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class OnPlayerJoin implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player p = event.getPlayer();
        if (p.getName().equalsIgnoreCase("admin")) {
            p.kickPlayer(ChatColor.RED + "That name is invalid!");
        }
        if (!Core.getInstance().getMoneyManager().accountExists(p.getName())) {
            Core.getInstance().getMoneyManager().createAccount(p.getName());
        }
    }

}

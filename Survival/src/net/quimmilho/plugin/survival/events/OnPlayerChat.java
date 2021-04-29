package net.quimmilho.plugin.survival.events;

import net.quimmilho.plugin.survival.Core;
import net.quimmilho.plugin.survival.roles.Role;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class OnPlayerChat implements Listener {

    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent event) {
        Player p = event.getPlayer();
        if (!Core.getInstance().getPlayerManager().getPlayer(p.getName()).isLoggedIn()) {
            p.sendMessage(ChatColor.RED + "First you have to login!");
            event.setCancelled(true);
        }
        Role role = Core.getInstance().getPlayerManager().getPlayer(p.getName()).getRole();
        event.setFormat("" + role.getColor() + role.getTag() + " " + ChatColor.WHITE + p.getName() + ChatColor.AQUA + "" +
                ChatColor.BOLD + " ► " + ChatColor.WHITE + event.getMessage());
    }

}

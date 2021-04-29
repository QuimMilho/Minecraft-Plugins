package net.quimmilho.plugin.lobby.events;

import net.quimmilho.plugin.lobby.Core;
import net.quimmilho.plugin.lobby.roles.Role;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class OnPlayerChat implements Listener {

    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent event) {
        Player p = event.getPlayer();
        Role role = Core.getInstance().getPlayerManager().getPlayer(p.getName()).getRole();
        event.setFormat("" + role.getColor() + role.getTag() + " " + ChatColor.WHITE + p.getName() + ChatColor.AQUA + "" +
                ChatColor.BOLD + " ► " + ChatColor.WHITE + event.getMessage());
    }

}

package net.quimmilho.plugin.survival.events;

import net.quimmilho.plugin.survival.Core;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

public class OnPlayerCommand implements Listener {

    @EventHandler
    public void onPlayerCommand(PlayerCommandPreprocessEvent event) {
        Player p = event.getPlayer();
        if (!Core.getInstance().getPlayerManager().getPlayer(p.getName()).isLoggedIn()) {
            String abc = event.getMessage();
            String cmd = getCmd(abc);
            if (cmd.equalsIgnoreCase("login") || cmd.equalsIgnoreCase("l")
                    || cmd.equalsIgnoreCase("register") || cmd.equalsIgnoreCase("r")) {
                event.setCancelled(false);
            } else {
                p.sendMessage(ChatColor.RED + "First you have to login!");
                event.setCancelled(true);
            }
        }
    }

    private String getCmd(String abc) {
        String cmd = "";
        for (int i = 1; i < abc.length(); i++) {
            if (abc.charAt(i) != ' ') {
                cmd += "" + abc.charAt(i);
            } else {
                return cmd;
            }
        }
        return cmd;
    }

}

package me.quimmilho.plugins.login.events;

import me.quimmilho.plugins.login.commands.CmdLogin;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

public class OnPlayerCommand implements Listener {

    @EventHandler
    public void onPlayerCommand(PlayerCommandPreprocessEvent event) {
        String abc = event.getMessage();
        String cmd = getCmd(abc);
        if (!(cmd.equalsIgnoreCase("login") || cmd.equalsIgnoreCase("register"))) {
            event.getPlayer().sendMessage(ChatColor.RED + "You don't have permission!");
            event.setCancelled(true);
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

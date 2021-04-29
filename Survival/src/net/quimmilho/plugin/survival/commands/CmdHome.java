package net.quimmilho.plugin.survival.commands;

import net.quimmilho.plugin.survival.Core;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CmdHome implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {
        if (sender instanceof Player) {
            Player p = (Player) sender;
            Location to = null;
            if (args.length == 1) {
                if (Core.getInstance().getPlayerManager().getPlayer(p.getName()).homeExists(args[0])) {
                    to = Core.getInstance().getPlayerManager().getPlayer(p.getName()).getHomeLocation(args[0]);
                } else {
                    p.sendMessage(ChatColor.RED + "That home doesn't exist!");
                    return false;
                }
            } else if (args.length == 0) {
                if (Core.getInstance().getPlayerManager().getPlayer(p.getName()).homeExists("home")) {
                    to = Core.getInstance().getPlayerManager().getPlayer(p.getName()).getHomeLocation("home");
                } else {
                    p.sendMessage(ChatColor.RED + "That home doesn't exist!");
                    return false;
                }
            }
            Location from = p.getLocation();
            Core.getInstance().getTeleportManager().addTeleport(p, to, from);
        }
        return false;
    }

}

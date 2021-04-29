package net.quimmilho.plugin.survival.commands;

import net.quimmilho.plugin.survival.Core;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CmdTPA implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {
        if (sender instanceof Player) {
            Player from = (Player) sender;
            if (Core.getInstance().getTpaManager().playerNotAvailableFrom(from)) {
                from.sendMessage(ChatColor.RED + "You already have one pending tpa request, try /tpacancel to cancel!");
                return false;
            }
            if (args.length == 1) {
                Player to;
                if (from.getName().equalsIgnoreCase(args[0])) {
                    from.sendMessage(ChatColor.RED + "You cannot send a tpa request to yourself");
                    return false;
                }
                try {
                    to = Bukkit.getPlayer(args[0]);
                } catch (Exception e) {
                    from.sendMessage(ChatColor.RED + "That player isn't online!");
                    return false;
                }
                if (Bukkit.getOnlinePlayers().contains(to)) {
                    if (Core.getInstance().getTpaManager().playerNotAvailableTo(to)) {
                        from.sendMessage(ChatColor.RED + "This player has one pending tpa request, try again later!");
                        return false;
                    }
                    Core.getInstance().getTpaManager().addTeleport(from, to);
                    to.sendMessage(ChatColor.BLUE + "You have one pending tpa request from " + from.getName());
                    to.sendMessage(ChatColor.BLUE + "Use /tpaccept to accept and /tpadeny to deny the request!");
                    to.sendMessage(ChatColor.BLUE + "You have 120 seconds");
                    from.sendMessage(ChatColor.BLUE + "You have sent one tpa request to " + to.getName());
                    from.sendMessage(ChatColor.BLUE + "To cancel use /tpacancel");
                } else {
                    from.sendMessage(ChatColor.RED + "That player isn't online!");
                }
            } else {
                from.sendMessage(ChatColor.RED + "Try /tpa <name>");
            }
        }
        return false;
    }

}

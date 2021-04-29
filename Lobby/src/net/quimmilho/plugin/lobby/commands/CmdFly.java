package net.quimmilho.plugin.lobby.commands;

import net.quimmilho.plugin.lobby.Core;
import net.quimmilho.plugin.lobby.roles.Role;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CmdFly implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {
        if (sender instanceof Player) {
            Player p = (Player) sender;
            if (Core.getInstance().getPlayerManager().getPlayer(p.getName()).getRole() == Role.ADMIN ||
                    Core.getInstance().getPlayerManager().getPlayer(p.getName()).getRole() == Role.MOD ||
                    Core.getInstance().getPlayerManager().getPlayer(p.getName()).getRole() == Role.DOUTOR) {
                if (args.length == 0) {
                    Core.getInstance().getPlayerManager().getPlayer(p.getName()).setFlying(p);
                } else if (args.length == 1) {
                    if (args[0].equalsIgnoreCase("list")) {
                        p.sendMessage(ChatColor.AQUA + "================= Fly list ================");
                        for (Player player : Bukkit.getOnlinePlayers()) {
                            if (Core.getInstance().getPlayerManager().getPlayer(player.getName()).getFlying()) {
                                p.sendMessage(ChatColor.AQUA + player.getName() + " can fly!");
                            }
                        }
                        p.sendMessage(ChatColor.AQUA + "===========================================");
                        return false;
                    }
                    if (Bukkit.getOnlinePlayers().contains(Bukkit.getPlayer(args[0]))) {
                        Core.getInstance().getPlayerManager().getPlayer(args[0]).setFlying(Bukkit.getPlayer(args[0]));
                    } else {
                        p.sendMessage(ChatColor.RED + "That player isn't online!");
                    }
                } else {

                }
            } else {
                p.sendMessage(ChatColor.RED + "You don't have permissions!");
            }
        }
        return false;
    }

}

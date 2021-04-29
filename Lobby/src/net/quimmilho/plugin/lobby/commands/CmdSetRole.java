package net.quimmilho.plugin.lobby.commands;

import net.quimmilho.plugin.lobby.Core;
import net.quimmilho.plugin.lobby.roles.Role;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CmdSetRole implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {
        if (sender instanceof Player) {
            Player p = (Player) sender;
            if (Core.getInstance().getPlayerManager().getPlayer(p.getName()).getRole() == Role.MOD ||
                    Core.getInstance().getPlayerManager().getPlayer(p.getName()).getRole() == Role.ADMIN) {
                if (Bukkit.getOnlinePlayers().contains(Bukkit.getPlayer(args[0]))) {
                    if (args[1].equalsIgnoreCase("doutor") || args[1].equalsIgnoreCase("convidado") ||
                            args[1].equalsIgnoreCase("CALOIRO")) {
                        String newRole = args[1].toUpperCase();
                        Core.getInstance().getPlayerManager().getPlayer(args[0]).setRole(Role.valueOf(newRole));
                        sender.sendMessage("Success! Now " + args[0] + " is a " + newRole + "!");
                    } else {
                        sender.sendMessage(ChatColor.RED + "You don't have permissions! You can only set DOUTOR, MEMBER " +
                                "and CONVIDADO!");
                    }
                } else {
                    sender.sendMessage(ChatColor.RED + "That player isn't online!");
                }
            }
            sender.sendMessage(ChatColor.RED + "You don't have permissions!");
        } else {
            if (args.length == 2) {
                if (Bukkit.getOnlinePlayers().contains(Bukkit.getPlayer(args[0]))) {
                    if (args[1].equalsIgnoreCase("CALOIRO") || args[1].equalsIgnoreCase("admin") ||
                            args[1].equalsIgnoreCase("doutor") || args[1].equalsIgnoreCase("mod")
                            || args[1].equalsIgnoreCase("CONVIDADO")) {
                        String newRole = args[1].toUpperCase();
                        Core.getInstance().getPlayerManager().getPlayer(args[0]).setRole(Role.valueOf(newRole));
                        sender.sendMessage("Success! Now " + args[0] + " is a " + newRole + "!");
                    } else {
                        sender.sendMessage("There are only four roles, CALOIRO, DOUTOR, MOD, CONVIDADO and ADMIN");
                    }
                } else {
                    sender.sendMessage("That player isn't online!");
                }
            } else {
                sender.sendMessage("Try /setrole <name> <role>");
            }
        }
        return false;
    }

}

package net.quimmilho.plugin.lobby.commands;

import net.quimmilho.plugin.lobby.Core;
import net.quimmilho.plugin.lobby.roles.Role;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CmdMaintenance implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {
        if (sender instanceof Player) {
            Player p = (Player) sender;
            if (args.length == 0) {
                if (Core.getInstance().getPlayerManager().getPlayer(p.getName()).getRole() == Role.ADMIN) {
                    p.sendMessage(ChatColor.RED + "Try /m <server>");
                } else {
                    p.sendMessage(ChatColor.RED + "You don't have permissions");
                }
            } else {
                if (Core.getInstance().getPlayerManager().getPlayer(p.getName()).getRole() == Role.ADMIN) {
                    if (args[0].equalsIgnoreCase("survival")) {
                        if (Core.getInstance().getMaintenance().isSurvival()) {
                            Core.getInstance().getMaintenance().setSurvival(false);
                            p.sendMessage(ChatColor.GOLD + "Survival is no longer in maintenance");
                        } else {
                            Core.getInstance().getMaintenance().setSurvival(true);
                            p.sendMessage(ChatColor.GOLD + "Survival is now in maintenance");
                        }
                    } else if (args[0].equalsIgnoreCase("skywars")) {
                        if (Core.getInstance().getMaintenance().isSkyWars()) {
                            Core.getInstance().getMaintenance().setSkyWars(false);
                            p.sendMessage(ChatColor.GOLD + "SkyWars is no longer in maintenance");
                        } else {
                            Core.getInstance().getMaintenance().setSkyWars(true);
                            p.sendMessage(ChatColor.GOLD + "SkyWars is now in maintenance");
                        }
                    } else {
                        p.sendMessage(ChatColor.RED + "That server doesn't exist!");
                    }
                } else {
                    p.sendMessage(ChatColor.RED + "You don't have permissions");
                }
            }
        } else {
            if (args[0].equalsIgnoreCase("survival")) {
                if (Core.getInstance().getMaintenance().isSurvival()) {
                    Core.getInstance().getMaintenance().setSurvival(false);
                    sender.sendMessage(ChatColor.GOLD + "Survival is no longer in maintenance");
                } else {
                    Core.getInstance().getMaintenance().setSurvival(true);
                    sender.sendMessage(ChatColor.GOLD + "Survival is now in maintenance");
                }
            } else {
                sender.sendMessage(ChatColor.RED + "That server doesn't exist!");
            }
        }
        return false;
    }

}

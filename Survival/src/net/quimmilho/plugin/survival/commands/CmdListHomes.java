package net.quimmilho.plugin.survival.commands;

import net.quimmilho.plugin.survival.Core;
import net.quimmilho.plugin.survival.roles.Role;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;


public class CmdListHomes implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {
        if (sender instanceof Player) {
            Player p = (Player) sender;
            if (args.length == 0) {
                p.sendMessage(ChatColor.AQUA + "=============== Homes list ================");
                p.sendMessage(ChatColor.AQUA + "You have " + ChatColor.WHITE + Core.getInstance().getPlayerManager().
                        getPlayer(p.getName()).homesNumber());
                String[] homes = Core.getInstance().getPlayerManager().getPlayer(p.getName()).homesList();
                for (int i = 0; i < homes.length; i++) {
                    p.sendMessage(ChatColor.WHITE + "" + (i + 1) + ": " + ChatColor.AQUA + homes[i]);
                }
                p.sendMessage(ChatColor.AQUA + "===========================================");
            } else if (args.length == 1){
                if (Core.getInstance().getPlayerManager().getPlayer(p.getName()).getRole() == Role.ADMIN) {
                    if (Bukkit.getOnlinePlayers().contains(Bukkit.getPlayer(args[0]))) {
                        p.sendMessage(ChatColor.AQUA + "=============== Homes list ================");
                        p.sendMessage(ChatColor.AQUA + args[0] + " has " + ChatColor.WHITE + Core.getInstance().
                                getPlayerManager().getPlayer(p.getName()).homesNumber());
                        String[] homes = Core.getInstance().getPlayerManager().getPlayer(args[0]).homesList();
                        for (int i = 0; i < homes.length; i++) {
                            p.sendMessage(ChatColor.WHITE + "" + (i + 1) + ": " + ChatColor.AQUA + homes[i]);
                        }
                        p.sendMessage(ChatColor.AQUA + "===========================================");
                    } else {
                        try {
                            net.quimmilho.plugin.survival.player.Player player = new net.quimmilho.plugin.survival.player.Player
                                    (Core.getInstance().getMainPath() + "/players/" + args[0] + "/",
                                    Core.getInstance().getPluginMainPath() + "/players/" + args[0] + "/");
                            Core.getInstance().getLogger().info("Player created successfully!");
                            String[] homes = player.homesList();
                            p.sendMessage(ChatColor.AQUA + "=============== Homes list ================");
                            p.sendMessage(ChatColor.AQUA + args[0] + " has " + ChatColor.WHITE + Core.getInstance().
                                    getPlayerManager().getPlayer(p.getName()).homesNumber());
                            for (int i = 0; i < homes.length; i++) {
                                p.sendMessage(ChatColor.WHITE + "" + (i + 1) + ": " + ChatColor.AQUA + homes[i]);
                            }
                            p.sendMessage(ChatColor.AQUA + "===========================================");
                        } catch (Exception e) {
                            p.sendMessage(ChatColor.RED + "That player doesn't exist!");
                        }
                    }
                } else {
                    p.sendMessage(ChatColor.RED + "You don't have permission!");
                }
            } else {
                p.sendMessage(ChatColor.RED + "Try /listhomes");
            }
        }
        return false;
    }

}

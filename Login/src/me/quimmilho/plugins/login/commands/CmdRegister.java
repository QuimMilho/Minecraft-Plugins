package me.quimmilho.plugins.login.commands;


import me.quimmilho.plugins.login.Core;
import me.quimmilho.plugins.login.Utils;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.HashMap;

public class CmdRegister implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {
        if (sender instanceof Player) {
            Player p = (Player) sender;
            if (Core.instance.getLogin().playerExists(p.getUniqueId().toString())) {
                p.sendMessage(ChatColor.RED + "You are already registered!");
            } else {
                if (args.length != 2) {
                    p.sendMessage(ChatColor.RED + "Use /register <newPassword> <repeatPassword>");
                    return false;
                }
                if (args[0].equals(args[1])) {
                    if (args[0].length() >= 4) {
                        try {
                            String newPass = Core.instance.getEncrypt().encryptPassword(p, args[0]);
                            HashMap<String, String> map = new HashMap<>();
                            map.put("password", newPass);
                            String path = Core.instance.getMainPath() + "/" + p.getName() + "/login.dat";
                            Core.instance.getLogger().info("path: " + path);
                            Utils.saveFile(map, path);
                            String player = p.getName() + ">" + "lobby";
                            Utils.saveFile(player, Core.instance.getPathStr() + "/info/bungee/info.dat", true);
                            p.sendMessage(ChatColor.GREEN + "Success!");
                            Core.instance.getPlayer().removePlayer(p);
                        } catch (Exception e) {
                            e.printStackTrace();
                            p.kickPlayer(ChatColor.RED + "Some internal error happened, please contact one admin! " +
                                    "Error:Login:1");
                        }
                    } else {
                        p.sendMessage(ChatColor.RED + "Your password must be ate least 4 characters long!");
                    }
                } else {
                    p.sendMessage(ChatColor.RED + "Your passwords must coincide!");
                }
            }
        } else {
            sender.sendMessage("You don't have permission!");
        }
        return true;
    }

}

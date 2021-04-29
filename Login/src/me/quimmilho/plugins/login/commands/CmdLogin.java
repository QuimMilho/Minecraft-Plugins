package me.quimmilho.plugins.login.commands;

import me.quimmilho.plugins.login.Core;
import me.quimmilho.plugins.login.Utils;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.HashMap;

public class CmdLogin implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {
        if (sender instanceof Player) {
            Player p = (Player) sender;
            String path = Core.instance.getMainPath() + "/" + p.getName() + "/login.dat";
            if (Core.instance.getLogin().playerExists(p.getName())) {
                if (args.length != 1) {
                    p.sendMessage(ChatColor.RED + "Use /login <password>");
                    return false;
                }

                HashMap<String, String> map = Utils.readFile(path);
                if (args[0].length() >= 4) {
                    String encryptedPassword = map.get("password");
                    String originalPassword = args[0];
                    try {
                        boolean login = Core.instance.getEncrypt().decryptPassword(p, originalPassword, encryptedPassword);
                        if (login) {
                            String player = p.getName() + ">" + "lobby";
                            Utils.saveFile(player, Core.instance.getPathStr() + "/info/bungee/info.dat", true);
                            p.sendMessage(ChatColor.GREEN + "Success!");
                            Core.instance.getPlayer().removePlayer(p);
                        } else {
                            p.kickPlayer(ChatColor.RED + "Wrong password!");
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    p.kickPlayer(ChatColor.RED + "Your password must be longer than 4 characters!");
                }
            } else {
                p.sendMessage(ChatColor.RED + "You need to register first!");
            }
        } else {
            sender.sendMessage("You don't have permission!");
        }
        return true;
    }

}

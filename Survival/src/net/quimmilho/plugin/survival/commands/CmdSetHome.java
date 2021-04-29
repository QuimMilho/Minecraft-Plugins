package net.quimmilho.plugin.survival.commands;

import net.quimmilho.plugin.survival.Core;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CmdSetHome implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {
        if (sender instanceof Player) {
            Player p = (Player) sender;
            if (args.length == 1) {
                if (!Core.getInstance().getPlayerManager().getPlayer(p.getName()).homeExists(args[0])) {
                    Core.getInstance().getPlayerManager().getPlayer(p.getName()).addHome(args[0], p.getLocation());
                    p.sendMessage(ChatColor.GREEN + "Home created!");
                } else {
                    p.sendMessage(ChatColor.RED + "That home already exists!");
                }
            } else if (args.length == 0) {
                if (!Core.getInstance().getPlayerManager().getPlayer(p.getName()).homeExists("home")) {
                    Core.getInstance().getPlayerManager().getPlayer(p.getName()).addHome("home", p.getLocation());
                    p.sendMessage(ChatColor.GREEN + "Home created!");
                } else {
                    p.sendMessage(ChatColor.RED + "That home already exists!");
                }
            }
        }
        return false;
    }

}

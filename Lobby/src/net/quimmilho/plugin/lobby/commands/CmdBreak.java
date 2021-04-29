package net.quimmilho.plugin.lobby.commands;

import net.quimmilho.plugin.lobby.Core;
import net.quimmilho.plugin.lobby.roles.Role;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CmdBreak implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {
        if (sender instanceof Player) {
            Player p = (Player) sender;
            if (Core.getInstance().getPlayerManager().getPlayer(p.getName()).getRole() == Role.ADMIN) {
                if (Core.getInstance().getPlayerManager().getPlayer(p.getName()).canBreak()) {
                    Core.getInstance().getPlayerManager().getPlayer(p.getName()).setBreak(false);
                    p.sendMessage(ChatColor.GREEN + "Now you can't break blocks!");
                } else {
                    Core.getInstance().getPlayerManager().getPlayer(p.getName()).setBreak(true);
                    p.sendMessage(ChatColor.GREEN + "Now you can break blocks!");
                }
            } else {
                p.sendMessage(ChatColor.RED + "You don't have permissions!");
            }
        }
        return false;
    }
}

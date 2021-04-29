package net.quimmilho.plugin.survival.commands;

import net.quimmilho.plugin.survival.Core;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CmdTPACancel implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {
        if (sender instanceof Player) {
            Player p = (Player) sender;
            if (Core.getInstance().getTpaManager().playerNotAvailableFrom(p)) {
                Core.getInstance().getTpaManager().teleportCancel(p);
            } else {
                p.sendMessage(ChatColor.RED + "You don't have a TPA request!");
            }
        }
        return false;
    }

}

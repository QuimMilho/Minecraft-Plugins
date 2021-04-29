package net.quimmilho.plugin.survival.commands;

import net.quimmilho.plugin.survival.Core;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CmdHub implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {
        if (sender instanceof Player) {
            Player p = (Player) sender;
            Core.getInstance().getPlayerManager().changeServer(p.getName(), "lobby");
        }
        return false;
    }

}

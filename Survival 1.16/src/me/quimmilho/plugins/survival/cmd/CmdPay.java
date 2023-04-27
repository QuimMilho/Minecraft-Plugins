package me.quimmilho.plugins.survival.cmd;

import me.quimmilho.plugins.survival.Core;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CmdPay implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {
        if (sender instanceof Player) {
            if (args.length < 2) {
                sender.sendMessage(ChatColor.RED + "Erro! Usa /pay <to> <amount>");
            } else {
                Player p = (Player) sender;
                if (args[0].equalsIgnoreCase(p.getName())) {
                    sender.sendMessage(ChatColor.RED + "Não podes enviar dinheiro para ti mesmo!");
                    return false;
                }
                if (!Core.getInstance().getMoneyManager().accountExists(args[0])) {
                    sender.sendMessage(ChatColor.RED + "Essa conta não existe!");
                } else {
                    try {
                        double amount = Double.parseDouble(args[1]);
                        Core.getInstance().getMoneyManager().transferMoney(p.getName(), args[0], amount);
                    } catch (Exception e) {
                        sender.sendMessage(ChatColor.RED + "Erro! A quantia introduzida é inválida!");
                        Core.getInstance().getLogger().info(args[1]);
                        e.printStackTrace();
                        new CmdShop().cona();
                    }
                }
            }
        }
        return false;
    }

}

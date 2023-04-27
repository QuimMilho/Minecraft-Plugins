package me.quimmilho.plugins.survival.cmd;

import me.quimmilho.plugins.survival.Core;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.text.DecimalFormat;

public class CmdMoney implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {
        if (sender instanceof Player) {
            Player p = (Player) sender;
            if (args.length == 0) {
                double money = Core.getInstance().getMoneyManager().getMoney(p.getName());
                DecimalFormat df = new DecimalFormat(format(money));
                p.sendMessage(ChatColor.LIGHT_PURPLE + "Tu tens " + ChatColor.YELLOW + df.format(money) +
                        ChatColor.LIGHT_PURPLE + "€");
            } else {
                if (Core.getInstance().getMoneyManager().accountExists(args[0])) {
                    if (args[0].equalsIgnoreCase(p.getName())) {
                        double money = Core.getInstance().getMoneyManager().getMoney(args[0]);
                        DecimalFormat df = new DecimalFormat(format(money));
                        p.sendMessage(ChatColor.LIGHT_PURPLE + "Tu tens " + ChatColor.YELLOW + df.format(money) +
                                ChatColor.LIGHT_PURPLE + "€");
                    } else {
                        double money = Core.getInstance().getMoneyManager().getMoney(args[0]);
                        DecimalFormat df = new DecimalFormat(format(money));
                        p.sendMessage(ChatColor.YELLOW + args[0] + ChatColor.LIGHT_PURPLE + " tem " +
                                ChatColor.YELLOW + df.format(money) + ChatColor.LIGHT_PURPLE + "€");
                    }
                } else {
                    p.sendMessage(ChatColor.RED + "Erro! Essa conta não existe!");
                }
            }
        }
        return false;
    }

    public String format(double money) {
        String format = "0";
        while (money / 1000 > 1000) {
            money /= 1000;
            format += ",000";
        }
        format += ".00";
        return format;
    }

}

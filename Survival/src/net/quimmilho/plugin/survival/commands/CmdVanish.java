package net.quimmilho.plugin.survival.commands;

import net.quimmilho.plugin.survival.Core;
import net.quimmilho.plugin.survival.roles.Role;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CmdVanish implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {
        if (sender instanceof Player) {
            Player p = (Player) sender;
            if (Core.getInstance().getPlayerManager().getPlayer(p.getName()).getRole() == Role.ADMIN) {
                if (args.length == 0) {
                    Core.getInstance().getPlayerManager().getPlayer(p.getName()).setVanished();
                    if (Core.getInstance().getPlayerManager().getPlayer(p.getName()).isVanished()) {
                        for (Player player : Bukkit.getOnlinePlayers()) {
                            if (!Core.getInstance().getPlayerManager().getPlayer(player.getName()).isVanished()) {
                                player.hidePlayer(Core.getInstance(), p);
                            } else {
                                p.showPlayer(Core.getInstance(), player);
                            }
                        }
                        Bukkit.broadcastMessage(ChatColor.GRAY + "[" + ChatColor.RED + "-" + ChatColor.GRAY + "] " +
                                p.getName());
                    } else {
                        for (Player player : Bukkit.getOnlinePlayers()) {
                            if (Core.getInstance().getPlayerManager().getPlayer(player.getName()).isVanished()) {
                                p.hidePlayer(Core.getInstance(), player);
                            } else {
                                player.showPlayer(Core.getInstance(), p);
                            }
                        }
                        Bukkit.broadcastMessage(ChatColor.GRAY + "[" + ChatColor.GREEN + "+" + ChatColor.GRAY + "] " +
                                p.getName());
                    }
                } else if (args.length == 1) {
                    if (args[0].equalsIgnoreCase("list")) {
                        p.sendMessage(ChatColor.AQUA + "=============== Vanish list ===============");
                        for (Player player : Bukkit.getOnlinePlayers()) {
                            if (Core.getInstance().getPlayerManager().getPlayer(player.getName()).isVanished()) {
                                p.sendMessage(ChatColor.AQUA + player.getName() + " is vanished!");
                            }
                        }
                        p.sendMessage(ChatColor.AQUA + "===========================================");
                    } else {
                        if (Bukkit.getOnlinePlayers().contains(Bukkit.getPlayer(args[0]))) {
                            Player pl = Bukkit.getPlayer(args[0]);
                            Core.getInstance().getPlayerManager().getPlayer(pl.getName()).setVanished();
                            if (Core.getInstance().getPlayerManager().getPlayer(pl.getName()).isVanished()) {
                                for (Player player : Bukkit.getOnlinePlayers()) {
                                    if (!Core.getInstance().getPlayerManager().getPlayer(player.getName()).isVanished()) {
                                        player.hidePlayer(Core.getInstance(), pl);
                                    } else {
                                        p.showPlayer(Core.getInstance(), player);
                                    }
                                }
                                Bukkit.broadcastMessage(ChatColor.GRAY + "[" + ChatColor.RED + "-" + ChatColor.GRAY + "] " +
                                        pl.getName());
                            } else {
                                for (Player player : Bukkit.getOnlinePlayers()) {
                                    if (Core.getInstance().getPlayerManager().getPlayer(player.getName()).isVanished()) {
                                        pl.hidePlayer(Core.getInstance(), player);
                                    } else {
                                        player.showPlayer(Core.getInstance(), pl);
                                    }
                                }
                                Bukkit.broadcastMessage(ChatColor.GRAY + "[" + ChatColor.GREEN + "+" + ChatColor.GRAY + "] " +
                                        pl.getName());
                            }
                        } else {
                            p.sendMessage(ChatColor.RED + "That player isn't online!");
                        }
                    }
                } else {
                    p.sendMessage(ChatColor.RED + "");
                }
            } else {
                p.sendMessage(ChatColor.RED + "You don't have permission!");
            }
        }
        return false;
    }

}

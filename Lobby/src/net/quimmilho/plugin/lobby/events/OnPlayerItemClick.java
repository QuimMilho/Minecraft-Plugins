package net.quimmilho.plugin.lobby.events;

import net.quimmilho.plugin.lobby.Core;
import net.quimmilho.plugin.lobby.roles.Role;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

public class OnPlayerItemClick implements Listener {

    @EventHandler
    public void itemClick(InventoryClickEvent event) {
        Player p = (Player) event.getWhoClicked();
        if (p.getLocation().getWorld() == Bukkit.getWorld("world"))
            event.setCancelled(true);
        /*
        Inventory compassInventory = Bukkit.createInventory(null, 27, ChatColor.RED + "Select Server:");
        if (event.getInventory().getName().equals(compassInventory.getName())) {
            if (event.getCurrentItem().getType() == Material.GRASS) {
                if (Core.getInstance().getPlayerManager().getPlayer(p.getName()).getRole() == Role.CONVIDADO) {
                    p.sendMessage(ChatColor.RED + "You don't have permissions!");
                    p.closeInventory();
                    Core.getInstance().createPlayerInventory(p);
                    return;
                }
                if (Core.getInstance().getMaintenance().isSurvival()) {
                    p.sendMessage(ChatColor.GOLD + "That server is in maintenance!");
                    if (Core.getInstance().getPlayerManager().getPlayer(p.getName()).getRole() == Role.ADMIN) {
                        p.sendMessage(ChatColor.RED + "Server is down to other players!");
                        Core.getInstance().getPlayerManager().changeServer(p.getName(), "survival");
                    } else {
                        p.sendMessage(ChatColor.RED + "You don't have permission to join!");
                    }
                }
                else
                    Core.getInstance().getPlayerManager().changeServer(p.getName(), "survival");
            } else if (event.getCurrentItem().getType() == Material.BOW) {
                if (Core.getInstance().getMaintenance().isSkyWars()) {
                    p.sendMessage(ChatColor.GOLD + "That server is in maintenance!");
                    if (Core.getInstance().getPlayerManager().getPlayer(p.getName()).getRole() == Role.ADMIN) {
                        p.sendMessage(ChatColor.RED + "Server is down to other players!");
                        Core.getInstance().getPlayerManager().changeServer(p.getName(), "skywars");
                    } else {
                        p.sendMessage(ChatColor.RED + "You don't have permission to join!");
                    }
                }
                else
                    Core.getInstance().getPlayerManager().changeServer(p.getName(), "skywars");
            }
            p.closeInventory();


         }
        */
        if (Core.getInstance().getPlayerManager().getPlayer(p.getName()).canBreak()) {
            event.setCancelled(false);
        }

    }

}

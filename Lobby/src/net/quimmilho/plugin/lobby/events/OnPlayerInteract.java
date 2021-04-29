package net.quimmilho.plugin.lobby.events;

import net.quimmilho.plugin.lobby.Core;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class OnPlayerInteract implements Listener {

    @EventHandler
    public void playerInteract(PlayerInteractEvent event) {
        Player p = event.getPlayer();
        if (p.getLocation().getWorld() == Bukkit.getWorld("world"))
            event.setCancelled(true);
        /*
        if (p.getItemInHand().getType() == Material.COMPASS) {
            p.openInventory(createCompassInventory());
            event.setCancelled(true);
        } else if (p.getItemInHand().getType() == Material.REDSTONE) {
            event.setCancelled(true);
            p.closeInventory();
            Bukkit.getScheduler().runTaskLater(Core.getInstance(), new Runnable() {
                @Override
                public void run() {
                    Core.getInstance().getServer().dispatchCommand(p, "pg");
                }
            }, 1);

        }

         */
        if (Core.getInstance().getPlayerManager().getPlayer(p.getName()).canBreak()) {
            event.setCancelled(false);
        }


    }

    public Inventory createCompassInventory() {
        Inventory inventory = Bukkit.createInventory(null, 27, ChatColor.RED + "Select Server:");
        ItemStack GRASS = new ItemStack(Material.GRASS);
        ItemMeta grassMeta = GRASS.getItemMeta();
        grassMeta.setDisplayName(ChatColor.GOLD + "Survival");
        ArrayList<String> grassLore = new ArrayList<>();
        grassLore.add(ChatColor.YELLOW + "A survival adventure!");
        grassLore.add(ChatColor.RED + "You can't be CONVIDADO to join!");
        grassLore.add(ChatColor.RED + "Please contact one Mod or Admin");
        if (Core.getInstance().getMaintenance().isSurvival()) {
            grassLore.add(ChatColor.GOLD + "" + ChatColor.BOLD + "Maintenance");
        }
        grassMeta.setLore(grassLore);
        GRASS.setItemMeta(grassMeta);
        inventory.setItem(12, GRASS);
        ItemStack BOW = new ItemStack(Material.BOW);
        ItemMeta bowMeta = GRASS.getItemMeta();
        bowMeta.setDisplayName(ChatColor.GREEN + "Sky Wars");
        ArrayList<String> bowLore = new ArrayList<>();
        bowLore.add(ChatColor.YELLOW + "A fun Minigame!");
        if (Core.getInstance().getMaintenance().isSkyWars()) {
            bowLore.add(ChatColor.GOLD + "" + ChatColor.BOLD + "Maintenance");
        }
        bowMeta.setLore(bowLore);
        BOW.setItemMeta(bowMeta);
        inventory.setItem(14, BOW);
        return inventory;
    }


}

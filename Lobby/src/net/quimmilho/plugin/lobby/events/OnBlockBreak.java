package net.quimmilho.plugin.lobby.events;

import net.quimmilho.plugin.lobby.Core;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class OnBlockBreak implements Listener {

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        if (event.getBlock().getWorld() == Bukkit.getWorld("world"))
            event.setCancelled(true);
        if (Core.getInstance().getPlayerManager().getPlayer(event.getPlayer().getName()).canBreak()) {
            event.setCancelled(false);
        }
    }

}

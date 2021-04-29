package me.quimmilho.plugins.login.events;

import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class OnBlockBreak implements Listener {

    public void onBlockBreak(BlockBreakEvent event) {
        event.setCancelled(true);
    }

}

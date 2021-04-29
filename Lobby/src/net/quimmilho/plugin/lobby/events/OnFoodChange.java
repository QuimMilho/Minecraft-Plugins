package net.quimmilho.plugin.lobby.events;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.FoodLevelChangeEvent;

public class OnFoodChange implements Listener {

    @EventHandler
    public void foodEvent(FoodLevelChangeEvent event) {
        if (event.getEntity().getLocation().getWorld() == Bukkit.getWorld("world"))
            event.setFoodLevel(20);
    }

}

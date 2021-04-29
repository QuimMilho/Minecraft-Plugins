package me.quimmilho.plugins.login.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.FoodLevelChangeEvent;

public class OnPlayerFoodDrop implements Listener {

    @EventHandler
    public void foodEvent(FoodLevelChangeEvent event) {
        event.setFoodLevel(20);
    }

}

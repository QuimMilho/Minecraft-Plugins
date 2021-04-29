package net.quimmilho.plugin.lobby.events;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class OnPlayerMove implements Listener {

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        Player p = event.getPlayer();
        if (p.getLocation().getWorld() == Bukkit.getWorld("world")) {
            if (p.getLocation().getY() <= 100 || p.getLocation().getY() >= 200) {
                p.teleport(new Location(Bukkit.getWorld("world"), -32.5, 143, 1.5));
            } else if (p.getLocation().getX() <= -90 || p.getLocation().getX() >= 50) {
                p.teleport(new Location(Bukkit.getWorld("world"), -32.5, 143, 1.5));
            } else if (p.getLocation().getZ() <= -140 || p.getLocation().getZ() >= 90) {
                p.teleport(new Location(Bukkit.getWorld("world"), -32.5, 143, 1.5));
            }
        }
    }

}

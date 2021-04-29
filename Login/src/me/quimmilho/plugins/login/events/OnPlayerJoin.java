package me.quimmilho.plugins.login.events;

import me.quimmilho.plugins.login.Core;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class OnPlayerJoin implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player p = event.getPlayer();
        Core.instance.getPlayer().addPlayer(p);
        event.setJoinMessage("");
        p.teleport(new Location(Bukkit.getWorld("world"), -32.5, 143, 1.5));
        p.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 10000000, 100, false));
        p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_DIGGING, 10000000, 100, false));
        p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 10000000, 100, false));
        p.getInventory().clear();
    }

}

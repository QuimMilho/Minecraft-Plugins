package net.quimmilho.plugin.survival.teleports;

import net.quimmilho.plugin.survival.Core;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public class Teleport {

    private Location to;
    private Location from;
    private Player p;
    private int timeLeft;

    public Teleport(Location to, Location from, Player p) {
        timeLeft = 4;
        this.to = to;
        this.from = from;
        this.p = p;
    }

    private void teleport() {
        Location loc = p.getLocation();
        if (from.getBlockX() != loc.getBlockX() || from.getBlockY() != loc.getBlockY() || from.getBlockZ() != loc.getBlockZ()) {
            p.sendMessage(ChatColor.RED + "You cannot move to teleport!");
        } else {
            p.teleport(to);
            p.sendMessage(ChatColor.GREEN + "You were teleported!");
        }
    }

    public boolean removeTime() {
        timeLeft--;
        if (timeLeft <= 0) {
            teleport();
            return true;
        }
        return false;
    }

    public int getTime() {
        return timeLeft;
    }

}

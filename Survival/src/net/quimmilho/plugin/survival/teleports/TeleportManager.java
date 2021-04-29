package net.quimmilho.plugin.survival.teleports;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.HashMap;

public class TeleportManager {

    private HashMap<Player, Teleport> teleportList;

    public TeleportManager() {
        teleportList = new HashMap<>();
    }

    public void update() {
        try {
            for (Player p : teleportList.keySet()) {
                if (Bukkit.getOnlinePlayers().contains(p))
                    if (teleportList.get(p).removeTime()) {
                        removeTeleport(p);
                    } else {
                        p.sendMessage(ChatColor.GOLD + "Teleporting in " + teleportList.get(p).getTime());
                    }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addTeleport(Player p, Location to, Location from) {
        if (teleportList.containsKey(p)) {
            p.sendMessage("You are already teleporting!");
            return;
        }
        teleportList.put(p, new Teleport(to, from, p));
    }

    public void removeTeleport(Player p) {
        teleportList.remove(p);
    }

}

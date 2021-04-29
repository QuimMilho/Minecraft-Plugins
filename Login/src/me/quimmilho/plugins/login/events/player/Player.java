package me.quimmilho.plugins.login.events.player;

import me.quimmilho.plugins.login.Core;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

import java.util.HashMap;

public class Player {

    private HashMap<String, Integer> list;

    public Player() {
        list = new HashMap<>();
    }

    public void addPlayer(org.bukkit.entity.Player player) {
        list.put(player.getName(), 0);
    }

    public void removePlayer(org.bukkit.entity.Player player) {
        list.remove(player.getName());
    }

    public boolean containsPlayer(org.bukkit.entity.Player player) {
        return list.containsKey(player.getName());
    }

    public void update() {
        for (String name : list.keySet()) {
            org.bukkit.entity.Player p = Bukkit.getPlayer(name);
            if (list.get(name) >= 30) {
                Bukkit.getScheduler().runTask(Core.instance, new Runnable() {
                    public void run() {
                        p.kickPlayer(ChatColor.RED + "You have 30 seconds to login!" );
                    }
                });
            }
            if (list.get(name) % 5 == 0) {
                if (Core.instance.getLogin().playerExists(p.getName())) {
                    p.sendMessage(ChatColor.BLUE + "Use /login <password>");
                } else {
                    p.sendMessage(ChatColor.BLUE + "Use /register <newPassword> <repeatPassword>");
                }
            }
            list.put(name, list.get(name) + 1);
        }
    }

}

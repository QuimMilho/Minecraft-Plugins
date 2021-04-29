package net.quimmilho.plugin.survival.teleports;

import net.quimmilho.plugin.survival.Core;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.HashMap;

public class TpaManager {

    private HashMap<Player, Tpa> teleportList;
    private HashMap<Player, Player> playerToFrom;

    public TpaManager() {
        teleportList = new HashMap<>();
        playerToFrom = new HashMap<>();
    }

    public void update() {
        try {
            for (Player p : teleportList.keySet()) {
                teleportList.get(p).removeTime();
                if (teleportList.get(p).getTime() <= 0) {
                    teleportList.get(p).getTo().sendMessage(ChatColor.BLUE + "Teleport request expired!");
                    removeTeleport(p);
                    p.sendMessage(ChatColor.BLUE + "Your teleport request expired!");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void addTeleport(Player from, Player to) {
        teleportList.put(from, new Tpa(from, to));
        playerToFrom.put(to, from);
    }

    private void removeTeleport(Player from) {
        playerToFrom.remove(teleportList.get(from).getTo());
        teleportList.remove(from);
    }

    public void teleport(Player to) {
        Core.getInstance().getTeleportManager().addTeleport(playerToFrom.get(to), to.getLocation(),
                playerToFrom.get(to).getLocation());
        playerToFrom.get(to).sendMessage(ChatColor.GREEN + "Your teleport request was accepted! Don't move!");
        to.sendMessage(ChatColor.GREEN + "Teleporting in 3 seconds!");
    }

    public void teleportAccept(Player to) {
        teleport(to);
        removeTeleport(playerToFrom.get(to));
    }

    public void teleportDeny(Player to) {
        playerToFrom.get(to).sendMessage(ChatColor.RED + "Your teleport request was denied!");
        to.sendMessage(ChatColor.GREEN + "Teleport request denied!");
        removeTeleport(playerToFrom.get(to));
    }

    public void teleportCancel(Player from) {
        from.sendMessage(ChatColor.GREEN + "Your teleport request was canceled!");
        teleportList.get(from).getTo().sendMessage(ChatColor.RED + "Teleport request canceled!");
        removeTeleport(from);
    }

    public boolean playerNotAvailableTo(Player to) {
        return playerToFrom.containsKey(to);
    }

    public boolean playerNotAvailableFrom(Player from) {
        return teleportList.containsKey(from);
    }

}

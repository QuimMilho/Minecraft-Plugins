package net.quimmilho.plugin.survival.events;

import net.quimmilho.plugin.survival.Core;
import net.quimmilho.plugin.survival.Utils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class OnPlayerJoin implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player p = event.getPlayer();
        checkPlayer(p);
        Core.getInstance().getPlayerManager().addPlayer(p.getName());
        if (Core.getInstance().getPlayerManager().getPlayer(p.getName()).isVanished()) {
            event.setJoinMessage("");
            for (Player player : Bukkit.getOnlinePlayers()) {
                if (!Core.getInstance().getPlayerManager().getPlayer(player.getName()).isVanished()) {
                    player.hidePlayer(Core.getInstance(), p);
                }
            }
            p.sendMessage(ChatColor.LIGHT_PURPLE + "You are still Vanished!");
        } else {
            event.setJoinMessage(ChatColor.GRAY + "[" + ChatColor.GREEN + "+" + ChatColor.GRAY + "] " +
                    event.getPlayer().getName());
            for (Player player : Bukkit.getOnlinePlayers()) {
                if (Core.getInstance().getPlayerManager().getPlayer(player.getName()).isVanished()) {
                    p.hidePlayer(Core.getInstance(), player);
                }
            }
        }
        Core.getInstance().getPlayerManager().getPlayer(p.getName()).loadFly(p);
    }

    private void checkPlayer(Player p) {
        String pathStr = Core.getInstance().getMainPath() + "/players/" + p.getName();
        Path path = Paths.get(pathStr);
        if (!Files.exists(path)) {
            try {
                Files.createDirectory(path);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        String playerPath = Core.getInstance().getMainPath() + "/players/" + p.getName();
        pathStr = playerPath + "/homes.dat";
        path = Paths.get(pathStr);
        if (!Files.exists(path)) {
            try {
                Files.createFile(path);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        pathStr = playerPath + "/vanish.dat";
        path = Paths.get(pathStr);
        if (!Files.exists(path)) {
            try {
                Files.createFile(path);
                Utils.saveFile("vanish>false", pathStr, false);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        pathStr = playerPath + "/fly.dat";
        path = Paths.get(pathStr);
        if (!Files.exists(path)) {
            try {
                Files.createFile(path);
                Utils.saveFile("fly>false", pathStr, false);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        pathStr = Core.getInstance().getPluginMainPath() + "/players/" + p.getName() + "/role.dat";
        path = Paths.get(pathStr);
        if (!Files.exists(path)) {
            try {
                Files.createFile(path);
                Utils.saveFile("role>CONVIDADO", pathStr, false);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}

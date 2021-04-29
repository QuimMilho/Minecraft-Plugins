package net.quimmilho.plugin.lobby.events;

import net.quimmilho.plugin.lobby.Core;
import net.quimmilho.plugin.lobby.Utils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

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
        Core.getInstance().getPlayerManager().getPlayer(p.getName()).loadFly(p);
        event.setJoinMessage(ChatColor.GRAY + "[" + ChatColor.GREEN + "+" + ChatColor.GRAY + "] " +
                event.getPlayer().getName());
        //Core.getInstance().createPlayerInventory(p);
        p.teleport(new Location(Bukkit.getWorld("world"), -32.5, 143, 1.5));
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

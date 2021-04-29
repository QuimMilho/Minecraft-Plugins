package net.quimmilho.plugin.lobby.messages;

import net.quimmilho.plugin.lobby.Core;
import net.quimmilho.plugin.lobby.Utils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;

public class Messages {

    private String path;
    private int i;

    public Messages(String path) {
        this.path = path + "/messages.dat";
        createFile();
        i = 1;
        run();
    }

    private void run() {
        Bukkit.getScheduler().runTaskLater(Core.getInstance(), new Runnable() {
            @Override
            public void run() {
                HashMap<String, String> map = Utils.readFile(path);
                if (map.size() != 0) {
                    if (map.containsKey("" + i)) {
                        i = 1;
                    }
                    Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', map.get("" + i)));
                }
            }
        }, 300);
    }

    private void createFile() {
        Path path = Paths.get(this.path);
        if (!Files.exists(path)) {
            try {
                Files.createFile(path);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}

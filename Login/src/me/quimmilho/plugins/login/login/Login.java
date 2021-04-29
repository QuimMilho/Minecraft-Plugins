package me.quimmilho.plugins.login.login;

import me.quimmilho.plugins.login.Core;
import me.quimmilho.plugins.login.Utils;
import org.bukkit.Bukkit;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;

public class Login {

    private String pathStr;

    public void load(String pathStr) {
        this.pathStr = pathStr;
    }

    public boolean playerExists(String name) {
        String pathStr = this.pathStr + "/" + name + "/login.dat";
        Path path = Paths.get(pathStr);
        if (Files.exists(path)) {
            HashMap<String, String> map = Utils.readFile(pathStr);
            if (map.containsKey("password")) {
                return true;
            }
            return false;
        } else {
            try {
                pathStr = this.pathStr + "/" + name;
                path = Paths.get(pathStr);
                if (!Files.exists(path)) {
                    Files.createDirectory(path);
                }
                Core.instance.getLogger().info("Created player directory!");
                pathStr += "/login.dat";
                path = Paths.get(pathStr);
                if (!Files.exists(path)) {
                    Files.createFile(path);
                }
                Core.instance.getLogger().info("Created login file!");
            } catch (IOException e) {
                e.printStackTrace();
            }
            return false;
        }
    }

    public void update() {
        Bukkit.getScheduler().runTaskLaterAsynchronously(Core.instance, new Runnable() {
            @Override
            public void run() {
                Core.instance.getPlayer().update();
                update();
            }
        }, 20);
    }
    
}

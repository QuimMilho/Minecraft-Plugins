package net.quimmilho.plugins.bungee;

import net.quimmilho.plugins.bungee.player.PlayerServerManager;
import net.md_5.bungee.api.plugin.Plugin;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Core extends Plugin {

    public static Core instance;
    private String mainPath;
    private PlayerServerManager playerServerManager;

    @Override
    public void onEnable() {
        instance = this;
        load();
    }

    private void load() {
        Utils.start();
        String pathStr = mainPath + "/info";
        Path path = Paths.get(pathStr);
        if (!Files.exists(path)) {
            try {
                Files.createDirectory(path);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        pathStr += "/bungee";
        path = Paths.get(pathStr);
        if (!Files.exists(path)) {
            try {
                Files.createDirectory(path);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        pathStr += "/info.dat";
        path = Paths.get(pathStr);
        if (!Files.exists(path)) {
            try {
                Files.createFile(path);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        playerServerManager = new PlayerServerManager();
        playerServerManager.teleportPlayers();
    }

    //SETTERS AND GETTERS


    public String getMainPath() {
        return mainPath;
    }

    public void setMainPath(String mainPath) {
        this.mainPath = mainPath;
    }
}

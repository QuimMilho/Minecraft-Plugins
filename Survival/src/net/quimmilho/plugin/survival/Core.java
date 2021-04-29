package net.quimmilho.plugin.survival;

import net.quimmilho.plugin.survival.commands.*;
import net.quimmilho.plugin.survival.events.*;
import net.quimmilho.plugin.survival.teleports.TeleportManager;
import net.quimmilho.plugin.survival.player.PlayerManager;
import net.quimmilho.plugin.survival.teleports.TpaManager;
import net.quimmilho.plugin.survival.timer.Timer;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Filter;
import java.util.logging.LogRecord;

public class Core extends JavaPlugin {

    private static Core instance;

    private PlayerManager playerManager;
    private TeleportManager tpManager;
    private String mainPath;
    private String pluginMainPath;
    private Timer timer;
    private TpaManager tpaManager;

    @Override
    public void onEnable() {
        instance = this;
        Utils.start();
        registerCommands();
        registerEvents();
        checkDirectories();
        playerManager = new PlayerManager();
        tpManager = new TeleportManager();
        timer = new Timer();
        tpaManager = new TpaManager();
    }

    @Override
    public void onDisable() {

    }

    private void registerCommands() {
        //this.getCommand("").setExecutor(new ());
        this.getCommand("home").setExecutor(new CmdHome());
        this.getCommand("sethome").setExecutor(new CmdSetHome());
        this.getCommand("delhome").setExecutor(new CmdDelHome());
        this.getCommand("listhomes").setExecutor(new CmdListHomes());
        this.getCommand("tpa").setExecutor(new CmdTPA());
        this.getCommand("tpaccept").setExecutor(new CmdTPAccept());
        this.getCommand("tpadeny").setExecutor(new CmdTPADeny());
        this.getCommand("tpacancel").setExecutor(new CmdTPACancel());
        this.getCommand("vanish").setExecutor(new CmdVanish());
        this.getCommand("setrole").setExecutor(new CmdSetRole());
        this.getCommand("fly").setExecutor(new CmdFly());
        this.getCommand("hub").setExecutor(new CmdHub());
    }

    private void registerEvents() {
        //getServer().getPluginManager().registerEvents(new (), this);
        getServer().getPluginManager().registerEvents(new OnPlayerJoin(), this);
        getServer().getPluginManager().registerEvents(new OnPlayerLeft(), this);
        getServer().getPluginManager().registerEvents(new OnPlayerCommand(), this);
        getServer().getPluginManager().registerEvents(new OnPlayerChat(), this);
    }

    private void checkDirectories() {
        checkDirectory(mainPath + "/survival");
        mainPath += "/survival";
        checkDirectory(mainPath + "/players");
        checkDirectory(pluginMainPath);
        checkDirectory(pluginMainPath + "/players");
    }

    private void checkDirectory(String pathStr) {
        Path path = Paths.get(pathStr);
        if (!Files.exists(path)) {
            try {
                Files.createDirectory(path);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

//GETTERS AND SETTERS

    public static Core getInstance() {
        return instance;
    }

    public String getMainPath() {
        return mainPath;
    }

    public void setMainPath(String mainPath) {
        this.mainPath = mainPath;
    }

    public PlayerManager getPlayerManager() {
        return playerManager;
    }

    public TeleportManager getTeleportManager() {
        return tpManager;
    }

    public TpaManager getTpaManager() {
        return tpaManager;
    }

    public String getPluginMainPath() {
        return pluginMainPath;
    }

    public void setPluginMainPath(String pluginMainPath) {
        this.pluginMainPath = pluginMainPath;
    }
}

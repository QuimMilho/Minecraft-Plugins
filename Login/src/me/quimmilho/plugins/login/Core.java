package me.quimmilho.plugins.login;

import me.quimmilho.plugins.login.commands.CmdLogin;
import me.quimmilho.plugins.login.commands.CmdRegister;
import me.quimmilho.plugins.login.encryptation.Encrypt;
import me.quimmilho.plugins.login.events.*;
import me.quimmilho.plugins.login.events.player.Player;
import me.quimmilho.plugins.login.login.Login;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Core extends JavaPlugin {

    public static Core instance;
    private String mainPath;
    private String pathStr;
    private Login login;
    private Player player;
    private Encrypt encrypt;

    @Override
    public void onEnable() {
        instance = this;
        encrypt = new Encrypt();
        player = new Player();

        Utils.start();

        try {
            load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        registerCommands();
        registerEvents();

        this.getLogger().info(ChatColor.GOLD +     "=========================================");
        this.getLogger().info(ChatColor.GREEN+     "             Login started!              ");
        this.getLogger().info(ChatColor.GREEN+     "    Plugin by: QuimMilho/Twice_Games!    ");
        this.getLogger().info(ChatColor.GOLD +     "=========================================");
    }

    @Override
    public void onDisable() {
        this.getLogger().info(ChatColor.GOLD +     "=========================================");
        this.getLogger().info(ChatColor.RED  +     "            Login disabled!              ");
        this.getLogger().info(ChatColor.RED  +     "    Plugin by: QuimMilho/Twice_Games!    ");
        this.getLogger().info(ChatColor.GOLD +     "=========================================");
    }

    public void registerCommands() {
        this.getCommand("login").setExecutor(new CmdLogin());
        this.getCommand("register").setExecutor(new CmdRegister());
    }

    public void registerEvents() {
        getServer().getPluginManager().registerEvents(new OnPlayerJoin(), this);
        getServer().getPluginManager().registerEvents(new OnPlayerLeft(), this);
        getServer().getPluginManager().registerEvents(new OnPlayerKick(), this);
        getServer().getPluginManager().registerEvents(new OnPlayerInteract(), this);
        getServer().getPluginManager().registerEvents(new OnPlayerCommand(), this);
        getServer().getPluginManager().registerEvents(new OnPlayerMove(), this);
        getServer().getPluginManager().registerEvents(new OnBlockBreak(), this);
        getServer().getPluginManager().registerEvents(new OnPlayerFoodDrop(), this);
    }

    public void load() throws IOException {
        this.pathStr = mainPath;
        login = new Login();
        login.update();
        String pathStr = mainPath + "info";
        Path path = Paths.get(pathStr);
        if (!Files.exists(path)) {
            Files.createDirectory(path);
        }
        pathStr += "/players";
        path = Paths.get(pathStr);
        if (!Files.exists(path)) {
            Files.createDirectory(path);
        }
        login.load(pathStr);
        mainPath = pathStr;
    }

    //GETTERS AND SETTERS

    public void setMainPath(String mainPath) {
        this.mainPath = mainPath;
    }

    public Login getLogin() {
        return login;
    }

    public Player getPlayer() {
        return player;
    }

    public String getMainPath() {
        return mainPath;
    }

    public Encrypt getEncrypt() {
        return encrypt;
    }

    public String getPathStr() {
        return pathStr;
    }

}

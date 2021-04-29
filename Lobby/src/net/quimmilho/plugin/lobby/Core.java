package net.quimmilho.plugin.lobby;

import net.quimmilho.plugin.lobby.commands.*;
import net.quimmilho.plugin.lobby.events.*;
import net.quimmilho.plugin.lobby.maintenance.Maintenance;
import net.quimmilho.plugin.lobby.messages.Messages;
import net.quimmilho.plugin.lobby.players.PlayerManager;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Core extends JavaPlugin {

    private static Core instance;

    private String mainPath;
    private String pluginMainPath;
    private PlayerManager playerManager;
    private Maintenance maintenance;
    private Messages messages;

    @Override
    public void onEnable() {
        instance = this;
        Utils.start();
        registerCommands();
        registerEvents();
        checkDirectories();
        playerManager = new PlayerManager();
        //maintenance = new Maintenance();
        //messages = new Messages(mainPath);
    }

    @Override
    public void onDisable() {

    }

    private void registerCommands() {
        //this.getCommand("").setExecutor(new ());
        this.getCommand("setrole").setExecutor(new CmdSetRole());
        this.getCommand("fly").setExecutor(new CmdFly());
        this.getCommand("break").setExecutor(new CmdBreak());
        //this.getCommand("maintenance").setExecutor(new CmdMaintenance());
        this.getCommand("hub").setExecutor(new CmdHub());
    }

    private void registerEvents() {
        //getServer().getPluginManager().registerEvents(new (), this);
        getServer().getPluginManager().registerEvents(new OnPlayerJoin(), this);
        getServer().getPluginManager().registerEvents(new OnPlayerLeft(), this);
        getServer().getPluginManager().registerEvents(new OnFoodChange(), this);
        getServer().getPluginManager().registerEvents(new OnBlockBreak(), this);
        getServer().getPluginManager().registerEvents(new OnPlayerMove(), this);
        getServer().getPluginManager().registerEvents(new OnPlayerDamage(), this);
        getServer().getPluginManager().registerEvents(new OnPlayerInteract(), this);
        getServer().getPluginManager().registerEvents(new OnPlayerItemDrop(), this);
        getServer().getPluginManager().registerEvents(new OnPlayerItemClick(), this);
        getServer().getPluginManager().registerEvents(new OnPlayerChat(), this);
        getServer().getPluginManager().registerEvents(new OnBlockPlace(), this);
    }

    private void checkDirectories() {
        checkDirectory(mainPath + "/skywars");
        mainPath += "/skywars";
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

    public void createPlayerInventory(Player p) {
        p.getInventory().clear();
        p.getInventory().setHeldItemSlot(4);
        ItemStack compass = new ItemStack(Material.COMPASS);
        ItemMeta compassMeta = compass.getItemMeta();
        compassMeta.setDisplayName(ChatColor.RED + "Server Selector");
        ArrayList<String> compassLore = new ArrayList<String>();
        compassLore.add(0, ChatColor.GREEN + "Use this to select one server!");
        compassMeta.setLore(compassLore);
        compass.setItemMeta(compassMeta);
        p.getInventory().setItem(4, compass);
        ItemStack painting = new ItemStack(Material.REDSTONE);
        ItemMeta paintingMeta = painting.getItemMeta();
        paintingMeta.setDisplayName(ChatColor.GOLD + "Pocket Games");
        ArrayList<String> paintingLore = new ArrayList<String>();
        paintingLore.add(ChatColor.GREEN + "Click to open some minigames menu!");
        paintingMeta.setLore(paintingLore);
        painting.setItemMeta(paintingMeta);
        p.getInventory().setItem(1, painting);
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

    public String getPluginMainPath() {
        return pluginMainPath;
    }

    public void setPluginMainPath(String pluginMainPath) {
        this.pluginMainPath = pluginMainPath;
    }

    public PlayerManager getPlayerManager() {
        return playerManager;
    }

    public Maintenance getMaintenance() {
        return maintenance;
    }

    public Messages getMessages() {
        return messages;
    }

}

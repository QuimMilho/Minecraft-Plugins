package net.quimmilho.plugins.bungee19;

import net.md_5.bungee.api.plugin.Plugin;
import net.quimmilho.plugins.bungee19.events.PlayerJoin;
import net.quimmilho.plugins.bungee19.events.PlayerLeave;
import net.quimmilho.plugins.bungee19.players.PlayerManager;
import net.quimmilho.plugins.bungee19.servers.ServerManager;

import java.util.concurrent.TimeUnit;

public final class Core extends Plugin {

    private static Core instance;

    private MySQL mySQL;

    private PlayerManager playerManager;
    private ServerManager serverManager;

    @Override
    public void onEnable() {
        instance = this;

        registerEvents();
        getLogger().info("Events registered!");

        startUp();
        getLogger().info("Started!");
    }

    @Override
    public void onDisable() {
        mySQL.closeConnection();
    }

    private void registerEvents() {
        getProxy().getPluginManager().registerListener(this, new PlayerJoin());
        getProxy().getPluginManager().registerListener(this, new PlayerLeave());
    }

    private void startUp() {
        mySQL = new MySQL();
        playerManager = new PlayerManager();
        serverManager = new ServerManager();
        getProxy().getScheduler().schedule(this, () -> {
            playerManager.updateTimePlayed();
            serverManager.sendPlayers();
        }, 0, 1, TimeUnit.SECONDS);
    }

    //GETTERS AND SETTERS

    public static Core getInstance() {
        return instance;
    }

    public MySQL getMySQL() {
        return mySQL;
    }

    public PlayerManager getPlayerManager() {
        return playerManager;
    }

}


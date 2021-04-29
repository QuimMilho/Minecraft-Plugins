package net.quimmilho.plugin.survival.player;

import net.quimmilho.plugin.survival.Core;
import net.quimmilho.plugin.survival.Utils;

import java.io.IOException;
import java.util.HashMap;

public class PlayerManager {

    private HashMap<String, Player> playerList;

    public PlayerManager() {
        playerList = new HashMap<>();
    }

    public void addPlayer(String player) {
        playerList.put(player, new Player(Core.getInstance().getMainPath() + "/players/" + player + "/",
                Core.getInstance().getPluginMainPath() + "/players/" + player + "/"));
    }

    public void removePlayer(String player) {
        playerList.remove(player);
    }

    public Player getPlayer(String player) {
        return playerList.get(player);
    }

    public void changeServer(String playerName, String server) {
        String player = playerName + ">" + server;
        try {
            Utils.saveFile(player, Core.getInstance().getPluginMainPath() + "/bungee/info.dat", true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

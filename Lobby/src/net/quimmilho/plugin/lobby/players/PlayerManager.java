package net.quimmilho.plugin.lobby.players;

import net.quimmilho.plugin.lobby.Core;
import net.quimmilho.plugin.lobby.Utils;

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
            String path = Core.getInstance().getPluginMainPath() + "/bungee/info.dat";
            Core.getInstance().getLogger().info(path);
            Utils.saveFile(player, path, true);
            Core.getInstance().getLogger().info("Sending " + playerName + " to server " + server);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

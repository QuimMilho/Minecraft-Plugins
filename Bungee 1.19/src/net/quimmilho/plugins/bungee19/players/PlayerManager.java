package net.quimmilho.plugins.bungee19.players;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.quimmilho.plugins.bungee19.Core;

import java.util.HashMap;

public class PlayerManager {

    private HashMap<String, Integer> timePlayedMap;

    public PlayerManager() {
        timePlayedMap = new HashMap<>();
    }

    public void addPlayer(String nick, String timePlayed) {
        int time = Integer.parseInt(timePlayed);
        timePlayedMap.put(nick, time);
    }

    public int removePlayer(String nick) {
        int temp = timePlayedMap.get(nick);
        timePlayedMap.remove(nick);
        return temp;
    }

    public void updateTimePlayed() {
        for (ProxiedPlayer player : Core.getInstance().getProxy().getPlayers()) {
            String pName = player.getName();
            timePlayedMap.put(pName, timePlayedMap.get(pName) + 1);
        }
    }

}

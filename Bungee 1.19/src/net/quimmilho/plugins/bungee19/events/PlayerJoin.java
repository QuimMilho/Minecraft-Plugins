package net.quimmilho.plugins.bungee19.events;


import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.PostLoginEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;
import net.quimmilho.plugins.bungee19.Core;

import java.util.ArrayList;

public class PlayerJoin implements Listener {

    @EventHandler
    public void playerJoinEvent(PostLoginEvent e) {
        ProxiedPlayer p = e.getPlayer();
        String pName = p.getName();
        ArrayList<String> rs = Core.getInstance().getMySQL().selectFromDatabase("players",
                "playtime", "nick LIKE '" + pName + "'");
        int size = Core.getInstance().getMySQL().getRows(rs);
        if (size == 0) {
            Core.getInstance().getPlayerManager().addPlayer(pName, "0");
        } else if (size == 1) {
            Core.getInstance().getPlayerManager().addPlayer(pName, rs.get(0));
        } else {
            Core.getInstance().getLogger().warning("There are multiple players with nick: " + pName);
        }
    }

}

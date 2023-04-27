package net.quimmilho.plugins.bungee19.events;


import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.PlayerDisconnectEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;
import net.quimmilho.plugins.bungee19.Core;

public class PlayerLeave implements Listener {

    @EventHandler
    public void playerLeaveEvent(PlayerDisconnectEvent e) {
        ProxiedPlayer p = e.getPlayer();
        String pName = p.getName();
        int time = Core.getInstance().getPlayerManager().removePlayer(pName);
        Core.getInstance().getMySQL().updateDatabase("players", "playtime",
                "" + time + "", "nick LIKE '" + pName + "'");
    }

}

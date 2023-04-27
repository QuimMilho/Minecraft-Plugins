package net.quimmilho.plugins.bungee19.servers;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.config.ServerInfo;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.quimmilho.plugins.bungee19.Core;

import java.util.ArrayList;

public class ServerManager {

    public void sendPlayers() {
        ArrayList<String> rs = Core.getInstance().getMySQL().selectAllFromDatabase("sendto");
        for (int i = 0; i < Core.getInstance().getMySQL().getRows(rs); i++) {
            ProxiedPlayer p = Core.getInstance().getProxy().getPlayer(rs.get(i * 2));
            ServerInfo server = Core.getInstance().getProxy().getServerInfo(rs.get(i * 2 + 1));
            if (p == null) {
                Core.getInstance().getLogger().warning("Player " + rs.get(i * 2) + " disconnected!");
                continue;
            }
            if (p.isConnected()) {
                if (p.getServer().getInfo().getName().equals(server.getName())) {
                    p.sendMessage(new ComponentBuilder("You are already connected to " + server.getName())
                            .color(ChatColor.RED).create());
                    continue;
                }
                p.connect(server);
            } else {
                Core.getInstance().getLogger().warning("Player " + rs.get(i * 2) + " disconnected!");
            }
        }
        Core.getInstance().getMySQL().deleteAllTableContents("sendto");
    }

}


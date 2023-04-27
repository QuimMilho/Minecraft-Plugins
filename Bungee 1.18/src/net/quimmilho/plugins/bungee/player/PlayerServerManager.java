package net.quimmilho.plugins.bungee.player;

import net.quimmilho.plugins.bungee.Core;
import net.quimmilho.plugins.bungee.Utils;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.config.ServerInfo;
import net.md_5.bungee.api.connection.ProxiedPlayer;

import java.io.IOException;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

public class PlayerServerManager {

    public void teleportPlayers() {
        Core.instance.getProxy().getScheduler().schedule(Core.instance, new Runnable() {
            @Override
            public void run() {
                String pathStr = Core.instance.getMainPath() + "/info/bungee/info.dat";
                HashMap<String, String> map = Utils.readFile(pathStr);
                try {
                    Utils.saveFile("", pathStr, false);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if (map.size() != 0) {
                    for (String s : map.keySet()) {
                        ProxiedPlayer player = ProxyServer.getInstance().getPlayer(s);
                        ServerInfo target = ProxyServer.getInstance().getServerInfo(map.get(s));
                        player.connect(target);
                        Core.instance.getLogger().info("Player "  + s + "detected to go to " + map.get(s) + "!");
                    }
                }
            }
        }, 1, 1, TimeUnit.SECONDS);
    }

}

package net.quimmilho.plugin.lobby.players;

import net.quimmilho.plugin.lobby.Utils;
import net.quimmilho.plugin.lobby.roles.Role;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

import java.io.IOException;
import java.util.HashMap;

public class Player {

    private String playerPath;
    private String playerMainPath;
    private Role role;
    private boolean flying;
    private boolean canBreak;

    public Player(String playerPath, String playerMainPath) {
        this.playerPath = playerPath;
        this.playerMainPath = playerMainPath;
        loadRole();
        canBreak = false;
    }

    //BREAK

    public void setBreak(boolean canBreak) {
        this.canBreak = canBreak;
    }

    public boolean canBreak() {
        return canBreak;
    }

    //FILES

    public String getPlayerPath() {
        return playerPath;
    }

    public String getPlayerMainPath() {
        return playerMainPath;
    }

    //ROLES

    private void loadRole() {
        HashMap<String, String> map = Utils.readFile(playerMainPath + "role.dat");
        if (map.containsKey("role")) {
            if (map.get("role").equalsIgnoreCase("member")) {
                map.put("role", "CALOIRO");
                role = Role.valueOf(map.get("role"));
                saveRole();
                return;
            }
            role = Role.valueOf(map.get("role"));
        } else {
            setRole(Role.CONVIDADO);
        }
    }

    private void saveRole() {
        HashMap<String, String> map = new HashMap<>();
        map.put("role", role.toString());
        try {
            Utils.saveFile(map, playerMainPath + "role.dat");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
        saveRole();
    }

    //FLY

    public void loadFly(org.bukkit.entity.Player player) {
        HashMap<String, String> map = Utils.readFile(playerPath + "fly.dat");
        if (map.containsKey("fly")) {
            if (map.get("fly").equalsIgnoreCase("true")) {
                flying = true;
                player.setAllowFlight(true);
            } else {
                flying = false;
            }
        } else {
            flying = false;
            saveFly();
        }
    }

    private void saveFly() {
        HashMap<String, String> map = new HashMap<>();
        if (flying) {
            map.put("fly", "true");
        } else {
            map.put("fly", "false");
        }
        try {
            Utils.saveFile(map, playerPath + "fly.dat");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean getFlying() {
        return flying;
    }

    public void setFlying(org.bukkit.entity.Player p) {
        if (flying) {
            flying = false;
            p.setAllowFlight(false);
            p.sendMessage(ChatColor.RED + "Now you cannot fly!");
        } else {
            flying = true;
            p.setAllowFlight(true);
            p.sendMessage(ChatColor.GREEN + "Now you can fly!");
        }
        saveFly();
    }

}

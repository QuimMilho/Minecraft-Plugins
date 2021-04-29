package net.quimmilho.plugin.survival.player;

import net.quimmilho.plugin.survival.Utils;
import net.quimmilho.plugin.survival.roles.Role;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;

import java.io.IOException;
import java.util.HashMap;

public class Player {

    private HashMap<String, Location> homes;
    private boolean loggedIn;
    private boolean isVanished;
    private String playerPath;
    private String playerMainPath;
    private int time;
    private Role role;
    private boolean flying;

    public Player(String playerPath, String playerMainPath) {
        this.playerPath = playerPath;
        this.playerMainPath = playerMainPath;
        loggedIn = true;
        loadVanished();
        homes = new HashMap<>();
        loadHomes();
        time = 0;
        loadRole();
    }

    //LOGIN

    public void setLoggedIn() {
        loggedIn = true;
    }

    public boolean isLoggedIn() {
        return loggedIn;
    }

    public int homesNumber() {
        return homes.size();
    }

    public String[] homesList() {
        String[] str = new String[homes.size()];
        int i = 0;
        for (String s : homes.keySet()) {
            str[i] = s;
            i++;
        }
        return str;
    }

    //VANISH

    private void loadVanished() {
        HashMap<String, String> map = Utils.readFile(playerPath + "vanish.dat");
        if (map.containsKey("vanish")) {
            if (map.get("vanish").equalsIgnoreCase("true")) {
                isVanished = true;
            } else {
                isVanished = false;
            }
        } else {
            isVanished = false;
            saveVanished();
        }
    }

    public void saveVanished() {
        HashMap<String, String> map = new HashMap<>();
        if (isVanished) {
            map.put("vanish", "true");
        } else {
            map.put("vanish", "false");
        }
        try {
            Utils.saveFile(map, playerPath + "vanish.dat");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean isVanished() {
        return isVanished;
    }

    public void setVanished() {
        if (isVanished) {
            isVanished = false;
        } else {
            isVanished = true;
        }
        saveVanished();
    }

    //HOMES

    public boolean homeExists(String name) {
        return homes.containsKey(name);
    }

    public void addHome(String name, Location location) {
        homes.put(name, location);
        saveHomes();
    }

    public void delHome(String name) {
        homes.remove(name);
        saveHomes();
    }

    public void loadHomes() {
        HashMap<String, String> map = Utils.readFile(playerPath + "homes.dat");
        for (String s : map.keySet()) {
            String[] strs = map.get(s).split(",");
            double x = Double.parseDouble(strs[0]);
            double y = Double.parseDouble(strs[1]);
            double z = Double.parseDouble(strs[2]);
            String world = strs[3];
            Location location = new Location(Bukkit.getWorld(world), x, y ,z);
            homes.put(s, location);
        }
    }

    public void saveHomes() {
        HashMap<String, String> map = new HashMap<>();
        for (String s : homes.keySet()) {
            Location location = homes.get(s);
            String str = "" + location.getBlockX() + "," + location.getBlockY() + "," + location.getBlockZ() + ","
                    + location.getWorld().getName();
            map.put(s, str);
        }
        try {
            Utils.saveFile(map, playerPath + "homes.dat");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Location getHomeLocation(String name) {
        if (homes.containsKey(name))
            return homes.get(name);
        else
            return null;
    }

    //FILES

    public String getPlayerPath() {
        return playerPath;
    }

    public String getPlayerMainPath() {
        return playerMainPath;
    }

    //TIME

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public void addTime(int time) {
        this.time += time;
    }

    public void addTime() {
        this.time++;
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

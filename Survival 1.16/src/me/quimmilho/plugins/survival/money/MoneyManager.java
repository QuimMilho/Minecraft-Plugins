package me.quimmilho.plugins.survival.money;

import me.quimmilho.plugins.survival.Core;
import me.quimmilho.plugins.survival.Utils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;

public class MoneyManager {

    private HashMap<String, Double> moneylist;

    public MoneyManager() {
        moneylist = new HashMap<>();
        String pathStr = Core.getInstance().getMainPath() + "money.dat";
        Path path = Paths.get(pathStr);
        if (!Files.exists(path)) {
            try {
                Files.createFile(path);
            } catch (IOException e) {
                Core.getInstance().getLogger().info("Error while creating file!");
                e.printStackTrace();
            }
        }
        load();
    }

    //ACCOUNT EXISTS

    public boolean accountExists(String playerName) {
        String name = playerName.toLowerCase();
        if (moneylist.containsKey(name)) {
            return true;
        }
        return false;
    }

    //CREATE ACCOUNT

    public void createAccount(String playerName) {
        String name = playerName.toLowerCase();
        moneylist.put(name, 10000.0);
        Bukkit.getPlayer(playerName).sendMessage(ChatColor.GOLD + "Bem vindo pela primeira vez ao servidor!");
    }

    //GET MONEY

    public double getMoney(String playerName) {
        String name = playerName.toLowerCase();
        return moneylist.get(name);
    }

    //TRANSFER MONEY

    public void transferMoney(String playerNameFrom, String playerNameTo, double amount) {
        if (amount < 10.0) {
            Bukkit.getPlayer(playerNameFrom).sendMessage(ChatColor.RED + "Erro! Deves enviar pelo menos 10€");
            return;
        } else if (getMoney(playerNameFrom) < amount) {
            Bukkit.getPlayer(playerNameFrom).sendMessage(ChatColor.RED + "Erro! Não tens dinheiro para isso!");
            return;
        } else if (!accountExists(playerNameTo)) {
            Bukkit.getPlayer(playerNameFrom).sendMessage(ChatColor.RED + "Erro! Essa conta não existe!");
        } else {
            addMoney(playerNameTo, amount);
            removeMoney(playerNameFrom, amount);
            Bukkit.getPlayer(playerNameFrom).sendMessage(ChatColor.LIGHT_PURPLE + "Enviaste " +
                    ChatColor.YELLOW + amount + ChatColor.LIGHT_PURPLE + "€ a " + ChatColor.YELLOW + playerNameTo);
            if (Bukkit.getPlayer(playerNameTo).isOnline()) {
                Bukkit.getPlayer(playerNameTo).sendMessage(ChatColor.LIGHT_PURPLE + "Recebeste " +
                        ChatColor.YELLOW + amount + ChatColor.LIGHT_PURPLE + "€ de " + ChatColor.YELLOW + playerNameFrom);
            }
        }
    }

    //ADD MONEY

    public void addMoney(String playerName, double amount) {
        setMoney(playerName, getMoney(playerName) + amount);
        Bukkit.getPlayer(playerName).sendMessage(ChatColor.LIGHT_PURPLE + "Foram adicionados " +
                ChatColor.YELLOW + amount + ChatColor.LIGHT_PURPLE + "€ à tua conta!");
    }

    //REMOVE MONEY

    public void removeMoney(String playerName, double amount) {
        setMoney(playerName, getMoney(playerName) - amount);
        Bukkit.getPlayer(playerName).sendMessage(ChatColor.LIGHT_PURPLE + "Foram removidos " +
                ChatColor.YELLOW + amount + ChatColor.LIGHT_PURPLE + "€ da tua conta!");
    }

    //SET MONEY

    public void setMoney(String playerName, double amount) {
        String name = playerName.toLowerCase();
        moneylist.put(name, amount);
    }

    //LOAD AND SAVE

    private void load() {
        HashMap<String, String> map = Utils.readFile(Core.getInstance().getMainPath() + "money.dat");
        for(String str : map.keySet()) {
            moneylist.put(str, Double.parseDouble(map.get(str)));
        }
    }

    public void save() {
        HashMap<String, String> map = new HashMap<>();
        for (String str : moneylist.keySet()) {
            map.put(str, "" + moneylist.get(str));
        }
        try {
            Utils.saveFile(map, Core.getInstance().getMainPath() + "money.dat");
        } catch (IOException e) {
            Core.getInstance().getLogger().info("Error while saving the economy!");
            e.printStackTrace();
        }
    }

}

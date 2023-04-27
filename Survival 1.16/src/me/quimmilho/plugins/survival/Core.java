package me.quimmilho.plugins.survival;

import me.quimmilho.plugins.survival.cmd.CmdMoney;
import me.quimmilho.plugins.survival.cmd.CmdPay;
import me.quimmilho.plugins.survival.cmd.CmdShop;
import me.quimmilho.plugins.survival.events.OnPlayerJoin;
import me.quimmilho.plugins.survival.money.MoneyManager;
import org.bukkit.plugin.java.JavaPlugin;

public class Core extends JavaPlugin {

    private MoneyManager moneyManager;
    private static Core instance;

    private String mainPath;

    @Override
    public void onEnable() {
        instance = this;
        Utils.start();
        moneyManager = new MoneyManager();
        getLogger().info("Plugin started!");
        getLogger().info("Path: " + mainPath);

        //REGISTER COMMANDS

        this.getCommand("money").setExecutor(new CmdMoney());
        this.getCommand("pay").setExecutor(new CmdPay());

        //REGISTER EVENTS

        getServer().getPluginManager().registerEvents(new OnPlayerJoin(), this);

    }

    @Override
    public void onDisable() {
        moneyManager.save();
        getLogger().info("Plugin saved!");
    }

    //SETTERS AND GETTERS

    public static Core getInstance() {
        return instance;
    }

    public String getMainPath() {
        return mainPath;
    }

    public void setMainPath(String mainPath) {
        this.mainPath = mainPath;
    }

    public MoneyManager getMoneyManager() {
        return moneyManager;
    }

}

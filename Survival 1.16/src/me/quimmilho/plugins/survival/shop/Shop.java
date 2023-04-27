package me.quimmilho.plugins.survival.shop;

import org.bukkit.Material;

import java.util.HashMap;

public class Shop {

    private Material material;

    private HashMap<String, Seller> sellerList;
    private String pathStr;

    public Shop(Material material, String path) {
        sellerList = new HashMap<>();
        this.material = material;
        this.pathStr = path;
        load();
    }



    //LOAD AND SAVE

    private void load() {

    }

    public void save() {

    }

}

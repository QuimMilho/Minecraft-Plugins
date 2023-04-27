package me.quimmilho.plugins.survival.shop;

import me.quimmilho.plugins.survival.Core;
import org.bukkit.Material;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.LinkedHashMap;

public class ShopManager {

    private HashMap<Type, LinkedHashMap<String, Shop>> shopList;
    private String pathStr;

    public ShopManager() {
        shopList = new HashMap<>();
        pathStr = Core.getInstance().getMainPath() + "shop/";
        Path path = Paths.get(pathStr);
        if (!Files.exists(path)) {
            try {
                Files.createDirectory(path);
            } catch (IOException e) {
                Core.getInstance().getLogger().info("Error while creating directory!");
                e.printStackTrace();
            }
        }
        load();
    }

    //CREATE SHOP

    public void createShop(Material material, Type type) {
        String pathStr = this.pathStr + material.toString() + "/";
        Path path = Paths.get(pathStr);
        if (!Files.exists(path)) {
            try {
                Files.createDirectory(path);
            } catch (IOException e) {
                Core.getInstance().getLogger().info("Error while creating directory!");
                e.printStackTrace();
            }
        }
        if (shopList.containsKey(type)) {
            shopList.get(type).put(material.toString(), new Shop(material, pathStr));
        } else {
            shopList.put(type, new LinkedHashMap<>());
            shopList.get(type).put(material.toString(), new Shop(material, pathStr));
        }
    }

    //LOAD AND SAVE

    private void load() {
        createShop(Material.COBBLESTONE, Type.BLOCKS);
        createShop(Material.STONE, Type.BLOCKS);
        createShop(Material.STONE_BRICKS, Type.BLOCKS);
        createShop(Material.GRASS_BLOCK, Type.BLOCKS);
        createShop(Material.DIRT, Type.BLOCKS);
        createShop(Material.GRANITE, Type.BLOCKS);
        createShop(Material.DIORITE, Type.BLOCKS);
        createShop(Material.ANDESITE, Type.BLOCKS);
        createShop(Material.COARSE_DIRT, Type.BLOCKS);
        createShop(Material.PODZOL, Type.BLOCKS);
        createShop(Material.CRIMSON_NYLIUM, Type.BLOCKS);
        createShop(Material.WARPED_NYLIUM, Type.BLOCKS);
        createShop(Material.OAK_LOG, Type.BLOCKS);
        createShop(Material.SPRUCE_LOG, Type.BLOCKS);
        createShop(Material.BIRCH_LOG, Type.BLOCKS);
        createShop(Material.JUNGLE_LOG, Type.BLOCKS);
        createShop(Material.ACACIA_LOG, Type.BLOCKS);
        createShop(Material.DARK_OAK_LOG, Type.BLOCKS);
        createShop(Material.CRIMSON_STEM, Type.BLOCKS);
        createShop(Material.WARPED_STEM, Type.BLOCKS);
        createShop(Material.SPONGE, Type.BLOCKS);
        createShop(Material.SANDSTONE, Type.BLOCKS);
        createShop(Material.SMOOTH_SANDSTONE, Type.BLOCKS);
        createShop(Material.RED_SANDSTONE, Type.BLOCKS);
        createShop(Material.SMOOTH_RED_SANDSTONE, Type.BLOCKS);
        createShop(Material.SMOOTH_STONE, Type.BLOCKS);
        createShop(Material.BRICKS, Type.BLOCKS);
        createShop(Material.BOOKSHELF, Type.BLOCKS);
        createShop(Material.PRISMARINE, Type.BLOCKS);
        createShop(Material.PRISMARINE_BRICKS, Type.BLOCKS);
        createShop(Material.DARK_PRISMARINE, Type.BLOCKS);
        createShop(Material.SEA_LANTERN, Type.BLOCKS);
        createShop(Material.BONE_BLOCK, Type.BLOCKS);
        createShop(Material.ICE, Type.BLOCKS);
        createShop(Material.PACKED_ICE, Type.BLOCKS);
        createShop(Material.BLUE_ICE, Type.BLOCKS);
    }

    public void save() {
        
    }

}

package net.quimmilho.plugin.lobby.roles;

import org.bukkit.ChatColor;

public enum Role {

    CALOIRO("CALOIRO", ChatColor.GRAY),
    ADMIN("Admin", ChatColor.RED),
    MOD("MOD", ChatColor.GREEN),
    DOUTOR("DOUTOR", ChatColor.DARK_GRAY),
    CONVIDADO("CONVIDADO", ChatColor.LIGHT_PURPLE);

    String tag;
    ChatColor color;

    Role(String tag, ChatColor color) {
        this.tag = tag;
        this.color = color;
    }

    public String getTag() {
        return tag;
    }

    public ChatColor getColor() {
        return color;
    }

}

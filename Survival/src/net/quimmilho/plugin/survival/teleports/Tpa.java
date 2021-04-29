package net.quimmilho.plugin.survival.teleports;

import org.bukkit.entity.Player;

public class Tpa {

    private Player from;
    private Player to;
    private int timeLeft;

    public Tpa(Player from, Player to) {
        this.from = from;
        this.to = to;
        timeLeft = 120;
    }

    //GETTERS AND SETTERS

    public Player getFrom() {
        return from;
    }

    public Player getTo() {
        return to;
    }

    public int getTime() {
        return timeLeft;
    }

    public void removeTime() {
        timeLeft--;
    }

}

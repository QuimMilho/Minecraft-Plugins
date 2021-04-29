package net.quimmilho.plugin.lobby.maintenance;

public class Maintenance {

    private boolean survival;
    private boolean skywars;

    public Maintenance() {
        survival = false;
        skywars = false;
    }

    //GETTERS AND SETTERS

    public boolean isSurvival() {
        return survival;
    }

    public void setSurvival(boolean survival) {
        this.survival = survival;
    }

    public boolean isSkyWars() {
        return skywars;
    }

    public void setSkyWars(boolean skywars) {
        this.skywars = skywars;
    }
}

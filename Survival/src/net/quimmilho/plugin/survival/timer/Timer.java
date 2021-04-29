package net.quimmilho.plugin.survival.timer;

import net.quimmilho.plugin.survival.Core;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.entity.Player;

public class Timer {

    private long i;

    public Timer() {
        i = 0;
        timer();
    }

    public void timer() {
        Bukkit.getScheduler().runTaskLater(Core.getInstance(), new Runnable() {
            @Override
            public void run() {
                Core.getInstance().getTeleportManager().update();
                Core.getInstance().getTpaManager().update();
                timer();
                i++;
                if (i % 10 == 0) {
                    checkNight();
                }
            }
        }, 20);
    }

    private void checkNight() {
        if (Bukkit.getWorld("world").getTime() % 24000 >= 13500) {
            double playersSleeping = 0;
            double playersTotal = 0;
            for (Player p : Bukkit.getOnlinePlayers()) {
                if (p.isSleeping())
                    playersSleeping += 1.0;
                if (p.getLocation().getWorld() == Bukkit.getWorld("world"))
                    playersTotal += 1;
                if (playersSleeping / playersTotal >= 0.5) {
                    World world = Bukkit.getWorld("world");
                    while (world.getTime() % 24000 >= 1000) {
                        world.setTime(world.getTime() + 100);
                    }
                    world.setTime(world.getTime() + 1000);
                    Bukkit.broadcastMessage(ChatColor.YELLOW + "Daylight is approaching!");
                    break;
                }
            }
        }
    }

}

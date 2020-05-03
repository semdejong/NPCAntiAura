package com.Semdej.NPCAntiAura.Handlers;

import com.Semdej.NPCAntiAura.Main;
import com.Semdej.NPCAntiAura.Math.circularCoordinatesMath;
import com.Semdej.NPCAntiAura.Math.timeCalculator;
import net.citizensnpcs.api.npc.NPC;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.scheduler.BukkitRunnable;

public class botMovement {

    circularCoordinatesMath coordinates = new circularCoordinatesMath();
    timeCalculator t = new timeCalculator();

    private Main plugin;

    public botMovement(Main pl){
        plugin = pl;
    }


    public void botM(Player killAuraHacker, NPC bot, double radius, long speed, double time, boolean ccw){

        new BukkitRunnable() {

            double degree = 1;

            double countdown = t.seconds(speed, time);

            @Override
            public void run() {

                if (countdown <= 0 || !killAuraHacker.isOnline()) { //countdown is over or player left the server, just two reasons to exit

                    new disposeBot(plugin).endOfBot(killAuraHacker, bot);

                    this.cancel(); //cancel the repeating task
                    return; //exit the method

                } else {

                    double x = coordinates.X(Math.toRadians(degree), radius, false);

                    double y = coordinates.Y(Math.toRadians(degree), radius, false);

                    Bukkit.getServer().getConsoleSender().sendMessage(Double.toString(degree));

                    Location loc = killAuraHacker.getLocation().add(x, 2.1, y);

                    bot.teleport(loc, PlayerTeleportEvent.TeleportCause.PLUGIN);

                    if(degree >= 360)
                        degree = 0;

                    degree = degree + 20;

                }

                countdown--; //decrement

            }
        }.runTaskTimer(plugin, 0, speed);
    }

}

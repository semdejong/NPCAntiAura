package com.Semdej.NPCAntiAura.Handlers;

import com.Semdej.NPCAntiAura.Main;
import com.Semdej.NPCAntiAura.Math.circularCoordinatesMath;
import com.Semdej.NPCAntiAura.Math.timeCalculator;
import net.citizensnpcs.api.npc.NPC;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.scheduler.BukkitRunnable;

public class botMovement {

    private circularCoordinatesMath coordinates;

    private Main plugin;

    public botMovement(Main pl){
        plugin = pl;
    }


    public void botM(Player killAuraHacker, NPC bot, double radius, long speed, double time,  boolean ccw){

        new BukkitRunnable() {

            double radial = 1;

            double countdown = new timeCalculator().seconds(speed, time);


            @Override
            public void run() {

                if (countdown <= 0 || !killAuraHacker.isOnline()) { //countdown is over or player left the server, just two reasons to exit

                    //Early exit

                    //Set the amount of hit to 0.

                    this.cancel(); //cancel the repeating task
                    return; //exit the method

                } else {

                    double x = coordinates.X(radial, radius, ccw);

                    double y = coordinates.Y(radial, radius, ccw);

                    Location loc = killAuraHacker.getLocation().add(x, 2.1, y);

                    bot.teleport(loc, PlayerTeleportEvent.TeleportCause.PLUGIN);

                    if(radial >= 360)
                        radial = 0;

                    radial++;

                }

                countdown--; //decrement

            }
        }.runTaskTimer(plugin, 0, speed);
    }

}

package com.Semdej.NPCAntiAura.Handlers;

import com.Semdej.NPCAntiAura.Main;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;


public class setBotInvisible {

    private entityHider entityHider;

    private Main plugin;

    public setBotInvisible(Main pl){
        plugin = pl;
    }

    public void setInvisible(Entity entity){
        for (Player player : plugin.getServer().getOnlinePlayers()) {
            entityHider.toggleEntity(player, entity);
        }
    }

}

package com.Semdej.NPCAntiAura.Events;

import com.Semdej.NPCAntiAura.Config.configHandler;
import com.Semdej.NPCAntiAura.Handlers.punishHandler;
import com.Semdej.NPCAntiAura.Main;
import net.citizensnpcs.api.event.NPCLeftClickEvent;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class botHitEvent implements Listener {

    punishHandler punish = new punishHandler();
    private Main plugin;

    public botHitEvent(Main pl){
        plugin = pl;
    }

    @EventHandler
    public void onHit(NPCLeftClickEvent event){
        int hit = 1;
        Player player = event.getClicker();
        if(plugin.playerBot.containsKey(player.getUniqueId())){
            if(plugin.hitBot.containsKey(player.getUniqueId())){
                hit = plugin.hitBot.get(player.getUniqueId());
                hit++;
                plugin.hitBot.remove(player.getUniqueId());
                plugin.hitBot.put(player.getUniqueId(), hit);
                if(hit >= new configHandler().botSettingsHits){
                    plugin.hitBot.remove(player.getUniqueId());
                    plugin.playerBot.remove(player.getUniqueId());
                    punish.Punish(player, "KillAura");
                }
            }else{
                plugin.hitBot.put(player.getUniqueId(), 1);
            }
        }
    }


}

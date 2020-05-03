package com.Semdej.NPCAntiAura.Events;

import com.Semdej.NPCAntiAura.Config.configHandler;
import com.Semdej.NPCAntiAura.Handlers.punishHandler;
import com.Semdej.NPCAntiAura.Holders.hashMapHolder;
import com.Semdej.NPCAntiAura.Main;
import net.citizensnpcs.api.event.NPCLeftClickEvent;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

public class botHitEvent implements Listener {

    hashMapHolder hashMaps = new hashMapHolder();

    private Main plugin;

    public botHitEvent(Main pl){
        plugin = pl;
    }

    private void onHit(NPCLeftClickEvent event){
        int hit = 1;
        Player player = event.getClicker();
        if(hashMaps.playerBot.containsKey(player.getUniqueId())){
            if(hashMaps.hitBot.containsKey(player.getUniqueId())){
                hit = hashMaps.hitBot.get(player.getUniqueId());
                hit++;
                hashMaps.hitBot.remove(player.getUniqueId());
                if(hit >= new configHandler().botSettingsHits){
                    hashMaps.hitBot.remove(player.getUniqueId());
                    hashMaps.playerBot.remove(player.getUniqueId());
                    new punishHandler().Punish(player, "KillAura");
                }
            }else{
                hashMaps.hitBot.put(player.getUniqueId(), 1);
            }
        }
    }


}

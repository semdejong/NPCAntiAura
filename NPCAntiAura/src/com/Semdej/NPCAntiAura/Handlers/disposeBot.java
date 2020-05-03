package com.Semdej.NPCAntiAura.Handlers;

import com.Semdej.NPCAntiAura.Main;
import net.citizensnpcs.api.npc.NPC;
import org.bukkit.entity.Player;

public class disposeBot {

    private Main plugin;

    public disposeBot(Main pl){
        plugin = pl;
    }

    public void endOfBot(Player killAuraHacker, NPC botToDispose){

        botToDispose.despawn();
        botToDispose.destroy();

        plugin.hitBot.remove(killAuraHacker.getUniqueId());
        plugin.allNPCBots.remove(killAuraHacker.getUniqueId());
        plugin.playerBot.remove(killAuraHacker.getUniqueId());

    }

}

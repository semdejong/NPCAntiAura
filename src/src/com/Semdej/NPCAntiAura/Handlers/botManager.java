package com.Semdej.NPCAntiAura.Handlers;

import com.Semdej.NPCAntiAura.Main;

import net.citizensnpcs.api.npc.NPC;
import org.bukkit.entity.Player;

public class botManager {

    private Main plugin;

    public botManager(Main pl){
        plugin = pl;
    }

    public void startBot(Player killAuraHacker){
        NPC bot = new createBot(plugin).launchBotOnPlayer(killAuraHacker);

        bot.despawn();
        bot.destroy();
    }

}

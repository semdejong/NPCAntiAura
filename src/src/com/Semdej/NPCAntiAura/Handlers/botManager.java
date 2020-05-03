package com.Semdej.NPCAntiAura.Handlers;

import com.Semdej.NPCAntiAura.Config.configHandler;
import com.Semdej.NPCAntiAura.Main;

import net.citizensnpcs.api.npc.NPC;
import org.bukkit.entity.Player;

public class botManager {
    configHandler config = new configHandler(); // Call it once.

    private Main plugin;

    public botManager(Main pl){
        plugin = pl;
    }

    public void startBot(Player killAuraHacker){
        NPC bot = new createBot(plugin).launchBotOnPlayer(killAuraHacker , config.botSettingsName, config.botSettingsSetInvisible);

        new botMovement(plugin).botM(killAuraHacker, bot, config.botSettingsRadius, config.botSettingsSpeed, config.botSettingsTimeInSeconds, config.botSettingsCounterClockWise);



        bot.despawn();
        bot.destroy();
    }


}

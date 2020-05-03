package com.Semdej.NPCAntiAura.Handlers;

import com.Semdej.NPCAntiAura.Config.configHandler;
import com.Semdej.NPCAntiAura.Main;

import com.Semdej.NPCAntiAura.Math.timeCalculator;
import net.citizensnpcs.api.npc.NPC;
import org.bukkit.entity.Player;

import java.util.concurrent.CompletableFuture;

import static java.util.concurrent.CompletableFuture.completedFuture;

public class botManager {
    configHandler config = new configHandler(); // Call it once.

    private Main plugin;

    public botManager(Main pl){
        plugin = pl;
    }

    public void startBot(Player killAuraHacker){

        NPC bot = new createBot(plugin).launchBotOnPlayer(killAuraHacker , config.botSettingsName, config.botSettingsSetInvisible);

        new botMovement(plugin).botM(killAuraHacker, bot, config.botSettingsRadius, config.botSettingsSpeed, config.botSettingsTimeInSeconds, config.botSettingsCounterClockWise);

    }


/*
        plugin.playerBot.remove(killAuraHacker.getUniqueId());
        plugin.allNPCBots.remove(killAuraHacker.getUniqueId());
        plugin.hitBot.remove(killAuraHacker.getUniqueId());

        bot.despawn();
        bot.destroy();*/
}

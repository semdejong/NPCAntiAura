package com.Semdej.NPCAntiAura.Handlers;

import com.Semdej.NPCAntiAura.Config.configHandler;
import com.Semdej.NPCAntiAura.Main;
import net.citizensnpcs.api.CitizensAPI;
import net.citizensnpcs.api.npc.NPC;
import net.citizensnpcs.api.npc.NPCRegistry;
import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

import java.util.UUID;

public class createBot {
    configHandler config = new configHandler();

    private Main plugin;

    public createBot(Main pl){
        plugin = pl;
    }


    public NPC launchBotOnPlayer(Player killAuraHacker, String botName, boolean Invisible){

        Location loc = killAuraHacker.getLocation(); //We save the location in a variable, because it is less intensive to save and call a variable then calling the full player class every time!
        UUID playerUUID = killAuraHacker.getUniqueId();//We save the uuid in a variable, because it is less intensive to save and call a variable then calling the full player class every time!

        NPCRegistry registry = CitizensAPI.getNPCRegistry();

        NPC hNpc = registry.createNPC(EntityType.PLAYER, botName);
        hNpc.spawn(loc);

        if(Invisible)
            new setBotInvisible(plugin).setInvisible(hNpc.getEntity(), killAuraHacker);

        if(plugin.playerBot.containsKey(playerUUID))
            plugin.playerBot.remove(playerUUID);

        plugin.playerBot.put(playerUUID, hNpc.getUniqueId());

        plugin.playerBot.put(playerUUID, hNpc.getUniqueId());

        plugin.allNPCBots.add(hNpc.getUniqueId());

        return hNpc;

    }




}

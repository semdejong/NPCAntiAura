package com.Semdej.NPCAntiAura;

import com.Semdej.NPCAntiAura.Commands.botCommand;
import com.Semdej.NPCAntiAura.Config.setupConfig;
import com.Semdej.NPCAntiAura.Events.botHitEvent;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

public class Main extends JavaPlugin implements Listener {

    public HashMap<UUID, UUID> playerBot;
    public HashMap<UUID, Integer> hitBot;
    public ArrayList<UUID> allNPCBots;

    @Override
    public void onEnable() {
        registerCommands();
        registerEvents();
        configuration();
        loadVariableHolders();
    }

    @Override
    public void onDisable() {

    }

    private void registerCommands(){
        getCommand("bot").setExecutor(new botCommand(this));
    }

    private void registerEvents(){
        getServer().getPluginManager().registerEvents(new botHitEvent(this), this);
    }

    private void configuration() {
        getConfig().options().copyDefaults(true);
        saveConfig();
        new setupConfig(this).setupConfigs();
    }

    private void loadVariableHolders(){
        playerBot = new HashMap<>();
        hitBot = new HashMap<>();
        allNPCBots = new ArrayList<>();
    }
}

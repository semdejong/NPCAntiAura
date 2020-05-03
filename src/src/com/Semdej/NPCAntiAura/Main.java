package com.Semdej.NPCAntiAura;

import com.Semdej.NPCAntiAura.Commands.botCommand;
import com.Semdej.NPCAntiAura.Config.setupConfig;
import com.Semdej.NPCAntiAura.Events.botHitEvent;
import com.Semdej.NPCAntiAura.Holders.arrayListHolder;
import com.Semdej.NPCAntiAura.Holders.hashMapHolder;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public class Main extends JavaPlugin implements Listener {

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
        if (!new File(this.getDataFolder(), "config.yml").exists()) {
            saveResource("config.yml", false);
            new setupConfig(this).setupConfigs();
        }
    }

    private void loadVariableHolders(){
        new hashMapHolder().loadHashMaps();
        new arrayListHolder().loadArrayLists();
    }
}

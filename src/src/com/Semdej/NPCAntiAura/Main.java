package com.Semdej.NPCAntiAura;

import com.Semdej.NPCAntiAura.Config.setupConfig;
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

    }

    private void registerEvents(){

    }

    private void configuration() {
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

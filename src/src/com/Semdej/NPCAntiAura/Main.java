package com.Semdej.NPCAntiAura;

import com.Semdej.NPCAntiAura.Config.setupConfig;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public class Main extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        registerCommands();
        registerEvents();
        configuration();
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
            new setupConfig().setupConfig(getConfig());
        }
    }
}

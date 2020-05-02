package com.Semdej.NPCAntiAura.Config;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public class setupConfig {


    //config.set("NPCAntiAura", );

    public void setupConfig(FileConfiguration config){
        config.set("NPCAntiAura.Main.Enabled", true);
        config.set("NPCAntiAura.Main.Proticollib", false);
        config.set("NPCAntiAura.BotSettings.Name", "semdej");
        config.set("NPCAntiAura.BotSettings.SkinName", "semdej");
        config.set("NPCAntiAura.BotSettings.UseArmour", true);
        config.set("NPCAntiAura.BotSettings.RandomSwing", true);
        config.set("NPCAntiAura.BotSettings.MoveDirection.Random", true);
        config.set("NPCAntiAura.BotSettings.MoveDirection.ClockWise", false );
        config.set("NPCAntiAura.BotSettings.MoveDirection.CounterClockWise", false );
        //more coming soon!
    }

}

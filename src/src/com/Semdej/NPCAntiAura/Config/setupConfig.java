package com.Semdej.NPCAntiAura.Config;

import com.Semdej.NPCAntiAura.Main;

public class setupConfig {

    private Main plugin;

    public setupConfig(Main pl) {
        plugin = pl;
    }
    

    //plugin.getConfig().set("NPCAntiAura", );

    public void setupConfigs(){
        plugin.getConfig().set("NPCAntiAura.Main.Enabled", true);
        plugin.getConfig().set("NPCAntiAura.Main.Proticollib", false);
        plugin.getConfig().set("NPCAntiAura.BotSettings.Name", "semdej");
        plugin.getConfig().set("NPCAntiAura.BotSettings.SkinName", "semdej");
        plugin.getConfig().set("NPCAntiAura.BotSettings.SetInvisible", true);
        plugin.getConfig().set("NPCAntiAura.BotSettings.UseArmour", true);
        plugin.getConfig().set("NPCAntiAura.BotSettings.RandomSwing", true);
        plugin.getConfig().set("NPCAntiAura.BotSettings.MoveDirection.Random", true);
        plugin.getConfig().set("NPCAntiAura.BotSettings.MoveDirection.ClockWise", false );
        plugin.getConfig().set("NPCAntiAura.BotSettings.MoveDirection.CounterClockWise", false );
        plugin.saveConfig();
        //more coming soon!
    }

}

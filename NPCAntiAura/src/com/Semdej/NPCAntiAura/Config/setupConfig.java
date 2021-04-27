package com.Semdej.NPCAntiAura.Config;

import com.Semdej.NPCAntiAura.Main;

public class setupConfig {

    private Main plugin;

    public setupConfig(Main pl) {
        plugin = pl;
    }
    

    //plugin.getConfig().set("NPCAntiAura", );

    public void setupConfigs(){
        if(!plugin.getConfig().contains("NPCAntiAura.Main.Punish.Kick")) {
            plugin.getConfig().set("NPCAntiAura.Main.Enabled", true);
            plugin.getConfig().set("NPCAntiAura.Main.Proticollib", false);
            plugin.getConfig().set("NPCAntiAura.Main.Prefix", "&l&b[NAA]");
            plugin.getConfig().set("NPCAntiAura.Main.BotName", "&l&bNAABOT");
            plugin.getConfig().set("NPCAntiAura.Main.Punish.Notify", false);
            plugin.getConfig().set("NPCAntiAura.Main.Punish.Kick", false);
            plugin.getConfig().set("NPCAntiAura.Main.Punish.Ban.Ban", false);
            plugin.getConfig().set("NPCAntiAura.Main.Punish.Ban.Duration", "7");
            plugin.getConfig().set("NPCAntiAura.BotSettings.Name", "semdej");
            plugin.getConfig().set("NPCAntiAura.BotSettings.SkinName", "semdej");
            plugin.getConfig().set("NPCAntiAura.BotSettings.Radius", 1.5);
            plugin.getConfig().set("NPCAntiAura.BotSettings.Speed", 1);
            plugin.getConfig().set("NPCAntiAura.BotSettings.AmountOfHits", 7);
            plugin.getConfig().set("NPCAntiAura.BotSettings.AmountOfSeconds", 5);
            plugin.getConfig().set("NPCAntiAura.BotSettings.SetInvisible", true);
            plugin.getConfig().set("NPCAntiAura.BotSettings.UseArmour", true);
            plugin.getConfig().set("NPCAntiAura.BotSettings.RandomSwing", true);
            plugin.getConfig().set("NPCAntiAura.BotSettings.MoveDirection.Random", true);
            plugin.getConfig().set("NPCAntiAura.BotSettings.MoveDirection.ClockWise", false);
            plugin.getConfig().set("NPCAntiAura.BotSettings.MoveDirection.CounterClockWise", false);
            plugin.saveConfig();
        }
        //more coming soon!
    }

}

package com.Semdej.NPCAntiAura.Config;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;

public class configHandler {

    //"NPCAntiAura.Main"
    //"NPCAntiAura.BotSettings"

    public static File datafile = new File("plugins"+File.separator+"NPCAntiAura"+File.separator+"config.yml");
    public static YamlConfiguration config = YamlConfiguration.loadConfiguration(datafile);

    //Main
    public boolean mainEnabled = config.getBoolean("NPCAntiAura.Main.Enabled");
    public boolean mainProticallib = config.getBoolean("NPCAntiAura.Main.Proticollib");

    //BotSettings
    public String botSettingsName = config.getString("NPCAntiAura.BotSettings.Name");
    public String botSettingsSkinName = config.getString("NPCAntiAura.BotSettings.SkinName");
    public boolean botSettingsSetInvisible = config.getBoolean("NPCAntiAura.BotSettings.SetInvisible");
    public boolean botSettingsUseArmour = config.getBoolean("NPCAntiAura.BotSettings.UseArmour");
    public boolean botSettingsRandomSwing = config.getBoolean("NPCAntiAura.BotSettings.RandomSwing");
    public boolean botSettingsRandom = config.getBoolean("NPCAntiAura.BotSettings.Random");
    public boolean botSettingsClockWise = config.getBoolean("NPCAntiAura.BotSettings.ClockWise");
    public boolean botSettingsCounterClockWise = config.getBoolean("NPCAntiAura.BotSettings.CounterClockWise");

}

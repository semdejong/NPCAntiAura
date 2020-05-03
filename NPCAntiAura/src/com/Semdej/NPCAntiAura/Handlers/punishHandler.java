package com.Semdej.NPCAntiAura.Handlers;

import com.Semdej.NPCAntiAura.Config.configHandler;
import com.sun.scenario.effect.impl.prism.PrReflectionPeer;
import org.bukkit.BanList;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.Date;

public class punishHandler {

    configHandler config = new configHandler();
    colorTranslate color = new colorTranslate();

    public void Punish(Player player, String hackType){
        String Prefix = config.mainPrefix;
        String BotName = config.mainBotName;
        if(config.punishNotify){
            for (Player p : Bukkit.getServer().getOnlinePlayers()){
                if(p.hasPermission("NAA.Log")){
                    p.sendMessage("" + color.colorTranslate(Prefix) +" "+ ChatColor.RED + player.getName()  +ChatColor.GREEN + " is using " + ChatColor.BOLD + ChatColor.UNDERLINE + ChatColor.GOLD + hackType + ChatColor.RED + "!");
                }
            }
        }

        if(config.punishKick){
            Bukkit.broadcastMessage("" + color.colorTranslate(Prefix) + ChatColor.BOLD + ChatColor.GREEN + player.getName() + ChatColor.RED + " has been kicked by "+ color.colorTranslate(BotName) +" for "+ hackType + "!");
            player.kickPlayer("" + color.colorTranslate(Prefix) + ChatColor.RED + "you have been kicked for " + ChatColor.BOLD + ChatColor.UNDERLINE + ChatColor.GOLD + hackType +ChatColor.RED + "!");
        }

        if(config.punishBan){
            if(!config.punishBanDuration.equalsIgnoreCase("permanent")) {

                int days = Integer.parseInt(config.punishBanDuration);

                Date date = new Date(System.currentTimeMillis() + 60 * 60 * 1000 * 24 * days);

                Bukkit.broadcastMessage("" + color.colorTranslate(Prefix) + " " + ChatColor.BOLD + ChatColor.GREEN + player.getName() + ChatColor.RED + " has been banned by "+color.colorTranslate(BotName)+" for "+ hackType + "!");
                player.kickPlayer("" + color.colorTranslate(Prefix) + " " + ChatColor.RED + "you have been banned for " + ChatColor.BOLD + ChatColor.UNDERLINE + ChatColor.GOLD + hackType +ChatColor.RED + "!  \n" + ChatColor.BOLD + ChatColor.WHITE +"Duration: " + config.punishBanDuration);
                Bukkit.getBanList(BanList.Type.NAME).addBan(player.getName(), "" + color.colorTranslate(Prefix) + " " + ChatColor.RED + "you have been banned for " + ChatColor.BOLD + ChatColor.UNDERLINE + ChatColor.GOLD + hackType +ChatColor.RED + "!  \n" + ChatColor.BOLD + ChatColor.WHITE +"Duration: " + config.punishBanDuration,date,"NAA");
            }else{
                Bukkit.broadcastMessage("" + color.colorTranslate(Prefix) + " " + ChatColor.BOLD + ChatColor.GREEN + player.getName() + ChatColor.RED + " has been banned by "+color.colorTranslate(BotName)+" for "+ hackType + "!");
                player.kickPlayer("" + color.colorTranslate(Prefix) + " " + ChatColor.RED + "you have been banned for " + ChatColor.BOLD + ChatColor.UNDERLINE + ChatColor.GOLD + hackType +ChatColor.RED + "!  \n" + ChatColor.BOLD + ChatColor.WHITE +"Duration: " + config.punishBanDuration);
                Bukkit.getBanList(BanList.Type.NAME).addBan(player.getName(), "" + color.colorTranslate(Prefix) + " " +  ChatColor.RED + "you have been banned for " + ChatColor.BOLD + ChatColor.UNDERLINE + ChatColor.GOLD + hackType +ChatColor.RED + "!  \n" + ChatColor.BOLD + ChatColor.WHITE +"Duration: " + config.punishBanDuration,null,"EEAC");
            }
        }
    }


}

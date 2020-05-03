package com.Semdej.NPCAntiAura.Handlers;

import com.Semdej.NPCAntiAura.Config.configHandler;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class permsHandler {

    configHandler config = new configHandler();
    colorTranslate color = new colorTranslate();

    public boolean canAccessBotCommand(CommandSender player, String[] args){
        if(player.hasPermission("NAA.Spawn")){
            if(args.length > 0){
                try{
                    Player KillAuraHacker = Bukkit.getPlayer(args[0]);
                    return true;
                }catch (Exception e){
                    player.sendMessage(""+ color.colorTranslate(config.mainPrefix)+ ChatColor.RED + " That player isn't online!");
                    return false;
                }
            }else{
                player.sendMessage(""+ color.colorTranslate(config.mainPrefix)+ ChatColor.RED + " You need to parse the player!");
                return false;
            }

        }else{
            player.sendMessage(""+ color.colorTranslate(config.mainPrefix)+ ChatColor.RED + " No permission!");
            return false;
        }
    }

}

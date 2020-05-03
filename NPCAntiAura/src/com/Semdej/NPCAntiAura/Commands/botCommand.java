package com.Semdej.NPCAntiAura.Commands;

import com.Semdej.NPCAntiAura.Config.configHandler;
import com.Semdej.NPCAntiAura.Handlers.botManager;
import com.Semdej.NPCAntiAura.Handlers.permsHandler;
import com.Semdej.NPCAntiAura.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class botCommand implements CommandExecutor {

    permsHandler perms = new permsHandler();
    private Main plugin;

    public botCommand(Main pl){
        plugin = pl;
    }

    @Override
    public boolean onCommand(CommandSender Sender, Command command, String s, String[] strings) {
        if(perms.canAccessBotCommand(Sender, strings)){
            Player KillAuraHacker = Bukkit.getPlayer(strings[0]);
            new botManager(plugin).startBot(KillAuraHacker);
            Sender.sendMessage(new configHandler().mainPrefix + ChatColor.GREEN + " watching semdej ;)");
        }
        return false;
    }
}

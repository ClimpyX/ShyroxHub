package com.shyrox.commands;

import java.util.Iterator;
import org.apache.commons.lang.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ClearChatCommand implements CommandExecutor {
    public ClearChatCommand() {
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] arguments) {
        Player player = (Player) sender;
        if (!player.hasPermission("shyrox.commands.clearchat") && !player.hasPermission("shyrox.admin")) {
            player.sendMessage("§dShyrox ► §cYetkiye sahip değilsin.");
            return true;
        }
        if(arguments.length == 0) {
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cKullanım: /" + label + " <sebep>"));
        } else {
            Iterator var6 = Bukkit.getServer().getOnlinePlayers().iterator();

            while(var6.hasNext()) {
                Player online = (Player)var6.next();
                online.sendMessage(new String[101]);online.sendMessage(ChatColor.translateAlternateColorCodes('&', "§dShyrox ► " + "&6&l" + sender.getName() + " &fadlı yetkili sohbeti &e'" + StringUtils.join(arguments, ' ') + "' &fsebebiyle sildi."));
            }
        }
        return false;
    }
}

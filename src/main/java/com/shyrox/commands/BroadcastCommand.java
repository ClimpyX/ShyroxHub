package com.shyrox.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.awt.*;

public class BroadcastCommand implements CommandExecutor {
    public BroadcastCommand() {
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] arguments) {

        Player player = (Player) sender;
        if (!player.hasPermission("shyrox.commands.broadcast") && !player.hasPermission("shyrox.admin")) {
            player.sendMessage("§dShyrox ► §cYetkiye sahip değilsin.");
            return true;
        }

        if (command.getName().equalsIgnoreCase("broadcast")) {
            if (arguments.length == 0) {
                sender.sendMessage(ChatColor.RED + "Kullanım: /" + label + " <Mesaj..>");
                return true;
            }

            String bc = "";
            for (int i = 0; i < arguments.length; i++) {
                bc = bc + arguments[i] + " ";
            }


            bc = ChatColor.translateAlternateColorCodes('&', bc);
            Bukkit.broadcastMessage("§e§lDUYURU: §7" + bc);

        }
        return false;
    }
}
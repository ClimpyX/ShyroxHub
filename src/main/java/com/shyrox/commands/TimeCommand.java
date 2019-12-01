package com.shyrox.commands;

import org.bukkit.ChatColor;
import org.bukkit.Effect;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;


public class TimeCommand implements CommandExecutor {
	
    @Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
	if (!(sender instanceof Player)) {
		sender.sendMessage(ChatColor.RED + "Sadece oyuncular kullanabilir.");
		return true;
	}

	Player player = (Player) sender;
	if (!player.hasPermission("shyrox.commands.time") && !player.hasPermission("shyrox.admin")) {
		player.sendMessage("§cBuna izniniz yok.");
		return true;
	}
	
        if (sender instanceof Player) {
            if (cmd.getName().equalsIgnoreCase("day")) {
                if (args.length == 0) {
                    player.getWorld().setFullTime(0);
                    player.sendMessage("§dShyrox ► §fOyun zamanı §6§lgündüz §folarak değiştirildi.");

                    return true;
                }
            }
            else if ((cmd.getName().equalsIgnoreCase("night")) && (args.length == 0)) {
                player.getWorld().setFullTime(16000);
                player.sendMessage("§dShyrox ► §fOyun zamanı §6§lgece §folarak değiştirildi.");
                return true;
            }
        } else {
            sender.sendMessage("§cSadece oyuncular kullanabilir.");
        }
        return true;
    }
}
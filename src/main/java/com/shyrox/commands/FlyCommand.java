package com.shyrox.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class FlyCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
		if (s.equalsIgnoreCase("fly")) {
			if (!(sender instanceof Player)) {
				sender.sendMessage(ChatColor.RED + "Konsol bunu kullanamaz");
				return true;
			}

			Player player = (Player) sender;
			if (!player.hasPermission("elice.commands.fly") && !player.hasPermission("shyrox.admin")) {
				player.sendMessage("§dShyrox ► §cUçabilmek için §6VIP §cve üstü yetkiye sahip olmalısınız.");
				return true;
			}

			if (args.length == 0) {
				player.setAllowFlight(!player.getAllowFlight());
				player.sendMessage("§dShyrox ►" + ChatColor.WHITE + "Uçuş modunuz " + (player.getAllowFlight() ? "§a§laçıldı." : "§c§lkapatıldı."));
			} else {
				if (args[0].equalsIgnoreCase("on")) {
					if (player.getAllowFlight()) {
						player.sendMessage("§dShyrox ► " + ChatColor.RED + "Zaten uçuş modunuz açık.");
					} else {
						player.setAllowFlight(true);
							player.sendMessage("§dShyrox ► §fUçuş modunuz §a§laçıldı.");
					}
				} else if (args[0].equalsIgnoreCase("off")) {
					if (!player.getAllowFlight()) {
						player.sendMessage("§dShyrox ► " + ChatColor.RED + "Zaten uçuş modunuz kapalı.");
					} else {
						player.setAllowFlight(false);
						player.sendMessage("§dShyrox ► §fUçuş modunuz §c§ckapatıldı.");
					}
				}
			}
			return true;
		}
		return false;

	}
}
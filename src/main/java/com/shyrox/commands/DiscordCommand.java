package com.shyrox.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class DiscordCommand implements CommandExecutor {

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (label.equalsIgnoreCase("discord")) {
			sender.sendMessage( "§9▇▇▇▇▇▇▇▇");
			sender.sendMessage( "§9▇§f▇▇§9▇▇§f▇▇§9▇");
			sender.sendMessage( "§9▇§f▇▇▇▇▇▇§9▇         §b§l§nDiscord§b§l:");
			sender.sendMessage( "§9▇§f▇§9▇§f▇▇§9▇§f▇§9▇");
			sender.sendMessage( "§9▇§f▇▇▇▇▇▇§9▇  §fhttps://discord.gg/penEfmZ");
			sender.sendMessage( "§9▇§f▇§9▇▇▇▇§f▇§9▇");
			sender.sendMessage( "§9▇§f▇▇§9▇▇§f▇▇§9▇");
			sender.sendMessage( "§9▇▇§9▇▇▇▇▇▇");

		}
		return false;
	}
}

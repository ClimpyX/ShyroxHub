package com.shyrox.commands;

import java.util.Collections;
import java.util.List;

import com.shyrox.ShyroxHub;
import com.shyrox.utils.PingUtil;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

public class PingCommand implements CommandExecutor, TabCompleter {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] arguments) {
		Player p = (Player) sender;
		if (arguments.length > 1) {
			sender.sendMessage(ChatColor.RED + "Kullanım: /" + label + " <oyuncuAdı>");
			return true;
		} else {
			if (arguments.length == 0) {
				if (sender instanceof Player) {
					sender.sendMessage(("§dShyrox ► " + ChatColor.WHITE + "Ping değeri: " + ChatColor.GOLD  + ChatColor.BOLD+ PingUtil.getPing((Player)sender)) + "ms.");
				//	ShyroxHub.getInstance().serverInfoMessage.sendToServer(p, "CakmaLobi");
				} else {
					sender.sendMessage(ChatColor.RED + "Bu komutu yanlizca oyundan kullanabilirsiniz");
				}
			}

			if (arguments.length == 1) {
				Player target = Bukkit.getServer().getPlayerExact(arguments[0]);
				if (target == null) {
					sender.sendMessage(ChatColor.RED + "Böyle bir oyuncu yok.");
				} else {
					sender.sendMessage("§dShyrox ► " + ChatColor.GOLD + target.getName() + ChatColor.WHITE + " adlı oyuncunun ping değeri: " + ChatColor.GOLD
							 + ChatColor.BOLD + PingUtil.getPing(target) + "ms.");
				}
			}

			return true;
		}
	}

	@Override
	public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] arguments) {
		if (arguments.length > 1) {
			return Collections.emptyList();
		}

		return null;
	}
}

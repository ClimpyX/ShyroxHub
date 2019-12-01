package com.shyrox.listener;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class FakeAnswers implements Listener {
    public void Commands(AsyncPlayerChatEvent event) {
        String block = event.getMessage();
        Player player = (Player) Bukkit.getOnlinePlayers();

        if (block.equalsIgnoreCase("/?")) {
            player.sendMessage(ChatColor.RED + "Yardım almak istiyorsan; discord.shyrox.network");
            event.setCancelled(true);
        }

        if (block.equalsIgnoreCase("/ver")) {
            player.sendMessage(ChatColor.GREEN + "Bu sunucu ShyroxLib 1.0 sürümünü yürütüyor.\ncom.shyrox.ShyroxServer (MC: 1.8 - 1.14)");
            event.setCancelled(true);
        }

        if (block.equalsIgnoreCase("/bukkit:ver")) {
            player.sendMessage(ChatColor.GREEN + "Bu sunucu ShyroxLib 1.0 sürümünü yürütüyor.\ncom.shyrox.ShyroxServer (MC: 1.8 - 1.14)");
            event.setCancelled(true);
        }

        if (block.equalsIgnoreCase("/about")) {
            player.sendMessage(ChatColor.GREEN + "Bu sunucu ShyroxLib 1.0 sürümünü yürütüyor.\ncom.shyrox.ShyroxServer (MC: 1.8 - 1.14)");
            event.setCancelled(true);
        }
    }
}
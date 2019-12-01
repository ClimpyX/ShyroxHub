package com.shyrox.listener;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class CommandBlocker implements Listener {
    public void Commands(AsyncPlayerChatEvent event) {
        String block = event.getMessage();
        Player player = (Player) Bukkit.getOnlinePlayers();

        if (block.equalsIgnoreCase("/me") && block.equalsIgnoreCase("/bukkit:pl") && block.equalsIgnoreCase("/say") && block.equalsIgnoreCase("/bukkit:pl")) {
            player.sendMessage("Bilinmeyen bir komut kullanıldı.");
            event.setCancelled(true);
        }
    }
}

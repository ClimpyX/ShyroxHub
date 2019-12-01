package com.shyrox.listener;

import java.sql.SQLException;

import com.shyrox.ShyroxHub;
import mysql.SQLiteManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;


public class NameBugFixListener implements Listener {
    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onPlayerLogin(PlayerLoginEvent event) {
        final Player player = event.getPlayer();

        if (ShyroxHub.getNamebug().containsKey(player.getName().toLowerCase())) {
            String name = (String)ShyroxHub.getNamebug().get(player.getName().toLowerCase());
            if (!name.equals(player.getName()))
                event.disallow(PlayerLoginEvent.Result.KICK_OTHER, ChatColor.GREEN + "Bağlanmaya çalıştığın isim " + ChatColor.DARK_AQUA + player.getName() +
                        ChatColor.GREEN + " fakat olması gereken isim " + ChatColor.DARK_AQUA + name);
        } else {
            ShyroxHub.getNamebug().put(player.getName().toLowerCase(), player.getName());
            Bukkit.getScheduler().runTaskAsynchronously(ShyroxHub.getInstance(), new Runnable()
            {
                public void run() {
                    try {
                        SQLiteManager.recordNew(player);
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }
}
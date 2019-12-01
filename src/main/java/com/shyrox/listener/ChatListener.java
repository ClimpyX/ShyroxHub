package com.shyrox.listener;

import java.util.Map;
import java.util.UUID;
import java.util.regex.Pattern;

import com.shyrox.ShyroxHub;
import me.lucko.luckperms.LuckPerms;
import me.lucko.luckperms.api.LuckPermsApi;
import me.lucko.luckperms.api.User;
import me.lucko.luckperms.api.caching.MetaData;
import net.milkbowl.vault.chat.Chat;
import org.apache.commons.lang.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import com.google.common.collect.MapMaker;


public class ChatListener implements Listener {
    private static final Pattern PATTERN = Pattern.compile("\\W");
    private final Map<UUID, String> messageHistory;

    public ChatListener() {
        this.messageHistory = (new MapMaker()).makeMap();
    }

    @EventHandler(ignoreCancelled = true, priority = EventPriority.HIGHEST)
    public void onPlayerChat(AsyncPlayerChatEvent event) {
        String message = event.getMessage();
        Player player = event.getPlayer();
        Player pl = event.getPlayer();


        String lastMessage = (String) this.messageHistory.get(player.getUniqueId());
        String cleanedMessage = PATTERN.matcher(message).replaceAll("");
        if (lastMessage != null && (message.equals(lastMessage) || StringUtils.getLevenshteinDistance(cleanedMessage, lastMessage) <= 1) && !player.hasPermission("shyrox.doublepost.bypas")) {
            player.sendMessage("§cAynı mesajı §l2 §ckere göndermeyin.");
            event.setCancelled(true);
        } else {
            this.messageHistory.put(player.getUniqueId(), cleanedMessage);
            event.setCancelled(true);

                ConsoleCommandSender console = Bukkit.getConsoleSender();
                console.sendMessage(this.getFormattedMessage(player, message, console));

                for (Player recipient : event.getRecipients()) {
                    recipient.sendMessage(this.getFormattedMessage(player, message, recipient));
                }

        }
    }

    @SuppressWarnings("deprecation")
	private String getFormattedMessage(Player player, String message, CommandSender viewer) {
        return (ShyroxHub.getChat().getPlayerPrefix(player) + "" + player.getName() + ChatColor.DARK_GRAY + ": " + ChatColor.RESET + message.replaceAll('&', '.'));
    }
}

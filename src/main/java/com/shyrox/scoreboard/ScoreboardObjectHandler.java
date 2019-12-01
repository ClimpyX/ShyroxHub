package com.shyrox.scoreboard;

import com.shyrox.ShyroxHub;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.scoreboard.Scoreboard;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class ScoreboardObjectHandler extends Handler implements Listener {
    private Map<UUID, ScoreboardObject> sbData;
    
    public ScoreboardObjectHandler(final ShyroxHub plugin) {
        super(plugin);
        this.sbData = new HashMap<UUID, ScoreboardObject>();
    }
    
    @Override
    public void enable() {
        Player[] onlinePlayers;
        for (int length = (onlinePlayers = Bukkit.getServer().getOnlinePlayers().toArray(new Player[0])).length, i = 0; i<length; ++i) {
            final Player player = onlinePlayers[i];
            this.loadData(player);
        }
        ShyroxHub.getInstance().getServer().getPluginManager().registerEvents((Listener)this, (Plugin)ShyroxHub.getInstance());
    }
    
    public void reload() {
        Player[] onlinePlayers;
        for (int length = (onlinePlayers = Bukkit.getServer().getOnlinePlayers().toArray(new Player[0])).length, i = 0; i<length; ++i) {
            final Player player = onlinePlayers[i];
            this.reloadData(player);
        }
    }
    
    public ScoreboardObject getScoreboardFor(final Player player) {
        return this.sbData.get(player.getUniqueId());
    }
    
    public void loadData(final Player player) {
        final Scoreboard scoreboard = ShyroxHub.getInstance().getServer().getScoreboardManager().getNewScoreboard();
        player.setScoreboard(scoreboard);
        this.sbData.put(player.getUniqueId(), new ScoreboardObject(scoreboard, ChatColor.valueOf(ShyroxHub.getInstance().getConfig().getString("scoreboard.renk")).toString() + ChatColor.BOLD + ShyroxHub.getInstance().getConfig().getString("scoreboard.baslik").toString()));
    }
    
    public void reloadData(final Player player) {
        if (this.sbData.containsKey(player.getUniqueId())) {
            final Scoreboard scoreaboard = player.getScoreboard();
            player.setScoreboard(scoreaboard);
            this.sbData.put(player.getUniqueId(), new ScoreboardObject(scoreaboard, ChatColor.valueOf(ShyroxHub.getInstance().getConfig().getString("scoreboard.renk")).toString() + ChatColor.BOLD + ShyroxHub.getInstance().getConfig().getString("scoreboard.baslik").toString()));
        }
    }
    
    @EventHandler
    public void onPlayerJoin(final PlayerJoinEvent event) {
        final Player player = event.getPlayer();
        this.loadData(player);
    }
    
    @EventHandler
    public void onPlayerQuit(final PlayerQuitEvent event) {
        final Player player = event.getPlayer();
        for (final String entries : player.getScoreboard().getEntries()) {
            player.getScoreboard().resetScores(entries);
        }
        this.sbData.remove(player);
    }
    
    @EventHandler
    public void onPlayerKick(final PlayerKickEvent event) {
        final Player player = event.getPlayer();
        for (final String entries : player.getScoreboard().getEntries()) {
            player.getScoreboard().resetScores(entries);
        }
        this.sbData.remove(player);
    }

}

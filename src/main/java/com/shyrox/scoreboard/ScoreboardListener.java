package com.shyrox.scoreboard;

import com.shyrox.ShyroxHub;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.scheduler.BukkitRunnable;

public class ScoreboardListener implements Listener {

    public void setupScoreboard() {
        (new BukkitRunnable() {
            public void run() {
                Player[] onlinePlayers;
                int length = (onlinePlayers = (Player[]) Bukkit.getServer().getOnlinePlayers().toArray(new Player[0])).length;

                for(int i = 0; i < length; ++i) {
                    Player player = onlinePlayers[i];
                 //   int ping = ((CraftPlayer) player).getHandle().ping;
                    ScoreboardObject scoreboard = ShyroxHub.getInstance().getScoreboardDataHandler().getScoreboardFor(player);
                    scoreboard.clear();
                    //scoreboard.add("§7Lobi");
                    scoreboard.add("");
                    scoreboard.add("§6➥ Sunucu");
                    scoreboard.add(" §a► §fToplam &b" + ShyroxHub.getInstance().getProxyMessage().getOnlineCount("ALL"));
                //    scoreboard.add(" §a► §fPing §7" + PingUtil.getPing(player));
                    scoreboard.add("");
                    scoreboard.add(" §a▪ Rütben: §f");
                    scoreboard.add("");
                    scoreboard.add("§6➥ Çevrimiçi Bilgisi");
                    scoreboard.add(" §a► §fOPSkyBlock &b " + ShyroxHub.getInstance().getProxyMessage().getOnlineCount("OPSkyBlock"));
                    scoreboard.add(" §a► §fSkyBlock " + ChatColor.AQUA + ShyroxHub.getInstance().getProxyMessage().getOnlineCount("SkyBlock"));
                    scoreboard.add("");
                    scoreboard.add("§6✎ " + ShyroxHub.getInstance().mainConfig.getFileConfiguration().getString("scoreboard.website"));

                  //  scoreboard.add("§c§lÖldürme§7: " + player.getStatistic(Statistic.PLAYER_KILLS));
                   // scoreboard.add("§c§lÖlme§7: " + player.getStatistic(Statistic.DEATHS));
                    scoreboard.update(player);


                }

            }
        }).runTaskTimer(ShyroxHub.getInstance(), 2L, 2L);
    }

}

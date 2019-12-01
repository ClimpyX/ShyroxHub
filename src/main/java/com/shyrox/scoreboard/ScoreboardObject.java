package com.shyrox.scoreboard;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;
import com.google.common.base.Preconditions;

public class ScoreboardObject {
    private List<ScoreboardInput> list;
    private Scoreboard scoreboard;

    private Team owner;
    private Team developer;
    private Team admin;
    private Team mod;
    private Team seniormod;
    private Team builder;
    private Team partner;
    private Team famous;
    private Team youtuber;
    private Team emperor;
    private Team kingpin;
    private Team titan;
    private Team immortal;
    private Team oyuncu;

    private Objective objective;
    private String title;
    private int lastSentCount;
    
    public ScoreboardObject(final Scoreboard scoreBoard) {
        this.list = new ArrayList<ScoreboardInput>();
        this.title = "DefaultTitle";
        this.lastSentCount = -1;
        this.scoreboard = scoreBoard;
        (this.objective = this.getOrCreateObjective(this.title)).setDisplaySlot(DisplaySlot.SIDEBAR);
    }

    public ScoreboardObject(final Scoreboard scoreBoard, final String title) {
        this.list = new ArrayList<ScoreboardInput>();
        this.title = "PlaceHolder";
        this.lastSentCount = -1;
        Preconditions.checkState(title.length() <= 32, (Object)"Max characters for Title is 32");
        this.title = ChatColor.translateAlternateColorCodes('&', title);
        this.scoreboard = scoreBoard;
        (this.owner = scoreboard.registerNewTeam("owner")).setPrefix("§4Owner §c");
        (this.developer = scoreboard.registerNewTeam("developer")).setPrefix("§aDeveloper §a");
        (this.admin = scoreboard.registerNewTeam("admin")).setPrefix("§cAdmin §c");
        (this.mod = scoreboard.registerNewTeam("mod")).setPrefix("§dMod §d");
        (this.seniormod = scoreboard.registerNewTeam("seniormod")).setPrefix("§3Sr. Mod §3");
        (this.builder = scoreboard.registerNewTeam("builder")).setPrefix("§9Builder §9");
        (this.partner = scoreboard.registerNewTeam("partner")).setPrefix("§dPartner §d");
        (this.famous  = scoreboard.registerNewTeam("famaous")).setPrefix("§dFamous §d");
        (this.youtuber = scoreboard.registerNewTeam("youtuber")).setPrefix("§dYouTube §d");
        (this.emperor = scoreboard.registerNewTeam("VIP")).setPrefix(ChatColor.DARK_RED.toString() + ChatColor.BOLD);
        (this.kingpin = scoreboard.registerNewTeam("VIPplus")).setPrefix(ChatColor.RED.toString() + ChatColor.BOLD);
        (this.titan = scoreboard.registerNewTeam("")).setPrefix(ChatColor.WHITE.toString() + ChatColor.BOLD);
        (this.immortal = scoreboard.registerNewTeam("immortal")).setPrefix(ChatColor.DARK_PURPLE.toString() + ChatColor.BOLD);
        (this.oyuncu = scoreboard.registerNewTeam("oyuncu")).setPrefix(ChatColor.GREEN.toString());

        (this.objective = this.getOrCreateObjective(this.title)).setDisplaySlot(DisplaySlot.SIDEBAR);
    }
    
    public void add(String input) {
        input = ChatColor.translateAlternateColorCodes('&', input);
        ScoreboardInput text = null;
        if (input.length() <= 16) {
            text = new ScoreboardInput(input, "");
        }
        else {
            String first = input.substring(0, 16);
            String second = input.substring(16, input.length());
            if (first.endsWith(String.valueOf('�'))) {
                first = first.substring(0, first.length() - 1);
                second = String.valueOf(String.valueOf('�')) + second;
            }
            final String lastColors = ChatColor.getLastColors(first);
            second = String.valueOf(String.valueOf(lastColors)) + second;
            text = new ScoreboardInput(first, StringUtils.left(second, 16));
        }
        this.list.add(text);
    }
    
    public void clear() {
        this.list.clear();
    }
    
    public void remove(final int index) {
        final String name = this.getNameForIndex(index);
        this.scoreboard.resetScores(name);
        final Team team = this.getOrCreateTeam(String.valueOf(String.valueOf(ChatColor.stripColor(StringUtils.left(this.title, 14)))) + index, index);
        team.unregister();
    }
    
    public void update(final Player player) {
        player.setScoreboard(this.scoreboard);
        for (int sentCount = 0; sentCount < this.list.size(); ++sentCount) {
            final Team i = this.getOrCreateTeam(String.valueOf(String.valueOf(ChatColor.stripColor(StringUtils.left(this.title, 14)))) + sentCount, sentCount);
            final ScoreboardInput str = this.list.get(this.list.size() - sentCount - 1);
            i.setPrefix(str.getLeft());
            i.setSuffix(str.getRight());
            this.objective.getScore(this.getNameForIndex(sentCount)).setScore(sentCount + 1);
        }
        if (this.lastSentCount != -1) {
            for (int sentCount = this.list.size(), var4 = 0; var4 < this.lastSentCount - sentCount; ++var4) {
                this.remove(sentCount + var4);
            }
        }
        this.lastSentCount = this.list.size();
    }
    
    public Team getOrCreateTeam(final String team, final int i) {
        Team value = this.scoreboard.getTeam(team);
        if (value == null) {
            value = this.scoreboard.registerNewTeam(team);
            value.addEntry(this.getNameForIndex(i));
        }
        return value;
    }
    
    public Objective getOrCreateObjective(final String objective) {
        Objective value = this.scoreboard.getObjective("notoriuos");
        if (value == null) {
            value = this.scoreboard.registerNewObjective("notoriuos", "dummy");
        }
        value.setDisplayName(objective);
        return value;
    }
    
    public String getNameForIndex(final int index) {
        return String.valueOf(String.valueOf(ChatColor.values()[index].toString())) + ChatColor.RESET;
    }


}

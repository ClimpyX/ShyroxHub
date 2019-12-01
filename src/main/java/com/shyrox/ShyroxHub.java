package com.shyrox;

import com.shyrox.commands.*;
import com.shyrox.config.FileConfig;
import com.shyrox.listener.*;
import com.shyrox.modules.PlayerVisibility;
import com.shyrox.scoreboard.ScoreboardListener;
import com.shyrox.scoreboard.ScoreboardObjectHandler;
import lombok.Getter;
import lombok.Setter;
import net.milkbowl.vault.chat.Chat;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.messaging.PluginMessageListener;
import org.bukkit.scheduler.BukkitRunnable;

import java.sql.SQLException;
import java.util.HashMap;

@Getter @Setter
public class ShyroxHub extends JavaPlugin implements Listener {

    @Getter public static ShyroxHub instance;

    @Getter public FileConfig mainConfig;

    @Getter public MessageChannelListener proxyMessage;

    private HashMap<Player, BukkitRunnable> cooldownTask;
    private HashMap<Player, Integer> cooldownTime;

    @Getter public static HashMap<String, Integer> playerCount = new HashMap<String, Integer>();
    @Getter public static HashMap<String, String> namebug = new HashMap();

    @Getter private ScoreboardObjectHandler scoreboardDataHandler = new ScoreboardObjectHandler(this);
    @Getter private ScoreboardListener scoreboard;
    private static Chat chat = null;


    @Override
    public void onEnable() {
        instance = this;

       // if(!new AdvancedLicense("04ER-0C52-43FQ-N6O3", "https://slatternly-skew.000webhostapp.com/verify.php", this).register()) return;

        this.playerCount = new HashMap<String, Integer>();
        this.cooldownTime = new HashMap<Player, Integer>();
        this.cooldownTask = new HashMap<Player, BukkitRunnable>();

        registerCommands();
        registerManagers();
        registerListeners();

        Bukkit.getServer().getMessenger().registerOutgoingPluginChannel(this,"BungeeCord");
        Bukkit.getServer().getMessenger().registerIncomingPluginChannel(this, "BungeeCord", new MessageChannelListener());

        cooldownTime = new HashMap<Player, Integer>();
        cooldownTask = new HashMap<Player, BukkitRunnable>();

        getServer().getPluginManager().registerEvents(this, this);


        setupChat();
      //  scoreboard = new ScoreboardListener();
      //  Bukkit.getServer().getPluginManager().registerEvents(scoreboard, this);

     //   scoreboardDataHandler.enable();
    //    scoreboard.setupScoreboard();

        World world = Bukkit.getServer().getWorld("Lobi");
        world.setTime(6000L);
        world.setGameRuleValue("doDaylightCycle", "false");

        this.updateCount();

        Bukkit.getConsoleSender().sendMessage(ChatColor.GREEN + "[ShyroxHub] SQLite baglaniyor..");
        Bukkit.getConsoleSender().sendMessage(ChatColor.GREEN + "[ShyroxHub] SQLite baglantisi saglandi.");
        Bukkit.getConsoleSender().sendMessage(ChatColor.GREEN + "[ShyroxHub] ClimpyX tarafindan yapilmistir.");
        Bukkit.getConsoleSender().sendMessage(ChatColor.GREEN + "[ShyroxHub] Plugin aktif edildi.");
    }



    @Override
    public void onDisable() {
        instance = null;
        this.playerCount = null;

        Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "[ShyroxHub] Plugin de-aktif edildi.");
    }

    private boolean setupChat() {
        RegisteredServiceProvider<Chat> rsp = getServer().getServicesManager().getRegistration(Chat.class);
        chat = rsp.getProvider();
        return chat != null;
    }

    private void registerCommands() {
        getCommand("broadcast").setExecutor(new BroadcastCommand());
        getCommand("clearchat").setExecutor(new ClearChatCommand());
        getCommand("fly").setExecutor(new FlyCommand());
        getCommand("gamemode").setExecutor(new GamemodeCommand());
        getCommand("ping").setExecutor(new PingCommand());
        getCommand("setspawn").setExecutor(new SetSpawnCommand());
        getCommand("spawn").setExecutor(new SpawnCommand());
        getCommand("day").setExecutor(new TimeCommand());
        getCommand("night").setExecutor(new TimeCommand());
        getCommand("world").setExecutor(new WorldTeleportCommand());
        getCommand("discord").setExecutor(new DiscordCommand());
    }

    private void registerListeners() {
        this.mainConfig = new FileConfig(this, "config.yml");

        Bukkit.getServer().getPluginManager().registerEvents(new AllowedListener(), this);
        Bukkit.getServer().getPluginManager().registerEvents(new AntiVoidListener(), this);
     //   Bukkit.getServer().getPluginManager().registerEvents(new BorderListener(), this);
        Bukkit.getServer().getPluginManager().registerEvents(new BugListener(), this);
        Bukkit.getServer().getPluginManager().registerEvents(new DoubleJump(), this);
        Bukkit.getServer().getPluginManager().registerEvents(new JoinListener(), this);
        Bukkit.getServer().getPluginManager().registerEvents(new JumpPads(), this);
        Bukkit.getServer().getPluginManager().registerEvents(new SelectorListener(), this);
        Bukkit.getServer().getPluginManager().registerEvents(new SignListener(), this);
        Bukkit.getServer().getPluginManager().registerEvents(new ChatListener(), this);
        Bukkit.getServer().getPluginManager().registerEvents(new FakeAnswers(), this);
        Bukkit.getServer().getPluginManager().registerEvents(new CommandBlocker(), this);
        Bukkit.getServer().getPluginManager().registerEvents(new CrashProtectionListener(), this);
        //Bukkit.getServer().getPluginManager().registerEvents(new NameBugFixListener(), this);
        Bukkit.getServer().getPluginManager().registerEvents(new ClimpyAPI(), this);

        getServer().getPluginManager().registerEvents(new PlayerVisibility(), this);
    }

    private void registerManagers() {
        this.proxyMessage = new MessageChannelListener();
    }

    public ScoreboardObjectHandler getScoreboardDataHandler() {
        return scoreboardDataHandler;
    }

    public HashMap<Player,Integer> getCooldownTime() {
        return cooldownTime;
    }

    public HashMap<Player,BukkitRunnable> getCooldownTask() {
        return cooldownTask;
    }



    public final void updateCount() {
        (new BukkitRunnable() {
            @Override
            public void run() {
                if (Bukkit.getOnlinePlayers().size() > 0) {
                    for (Player player : Bukkit.getOnlinePlayers()) {
                        proxyMessage.getCount(player, null);
                    }
                }
            }

        }).runTaskTimerAsynchronously(ShyroxHub.getInstance(), 20L, 20L);
    }

    public static Chat getChat() {
        return chat;
    }

}

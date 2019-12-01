package com.shyrox.listener;

import com.connorlinfoot.titleapi.TitleAPI;
import com.shyrox.ShyroxHub;
import com.shyrox.listener.SelectorListener;
import org.bukkit.*;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import sk.Adin.PMenu.API.UltraMenuAPI;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class JoinListener implements Listener {

    private final Set<Item> epItems;
    public JoinListener() {
        this.epItems = new HashSet<Item>();
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e){
        e.setQuitMessage(null);
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        event.setJoinMessage(null);

        World w = Bukkit.getServer().getWorld(ShyroxHub.getInstance().getConfig().getString("spawn.world"));
        double x = ShyroxHub.getInstance().getConfig().getDouble("spawn.x");
        double y = ShyroxHub.getInstance().getConfig().getDouble("spawn.y");
        double z = ShyroxHub.getInstance().getConfig().getDouble("spawn.z");
        float yaw = (float) ShyroxHub.getInstance().getConfig().getDouble("spawn.yaw");
        float pitch = (float) ShyroxHub.getInstance().getConfig().getDouble("spawn.pitch");
        player.teleport(new Location(w, x, y, z, yaw, pitch));

        player.getInventory().clear();
        player.setWalkSpeed(0.3f);
        player.setHealth(20);
        player.setFoodLevel(20);
        player.setSaturation(10);
        player.setHealth(20);
        player.setFoodLevel(20);
        player.setGameMode(GameMode.ADVENTURE);

        player.getInventory().setHelmet((ItemStack)null);
        player.getInventory().setChestplate((ItemStack)null);
        player.getInventory().setLeggings((ItemStack)null);
        player.getInventory().setBoots((ItemStack)null);

        player.getInventory().setItem(0, new SelectorListener().getSelector());
        ItemStack VisInkSack = new ItemStack(Material.INK_SACK, 1, (short) 8);
        ItemMeta vis = VisInkSack.getItemMeta();
        vis.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&bÜyeler: &aGösteriliyor"));
        VisInkSack.setItemMeta(vis);
        player.getInventory().setItem(1, VisInkSack);

        player.getInventory().setItem(0, new SelectorListener().getSelector());

       // TitleAPI.sendTabTitle(player, "&e&m*----|---&f &d&lSHYROX &e&m----|----*\n","\n&3&m-----------------------\n§§6shyrox.network");
        TitleAPI.sendTabTitle(player, "\n&d&lShyrox Network\n&7www.shyrox.network &c┃ &7shyrox.network/magaza\n", "\n&7&oHer zaman en iyisi, en hatasız olanı için.\n");
        TitleAPI.sendFullTitle(player,5,100,5," ","§6Sunucuya hoş geldin! §f" + player.getName());


        player.sendMessage("§8§m-*-------------------------------------*-");
        player.sendMessage("§d§lShyrox §dNetwork ");
        player.sendMessage("");
        player.sendMessage("§dWebsite: §fshyrox.network");
        player.sendMessage("§dDiscord: §fdiscord.gg");
        player.sendMessage("§dMarket: §fshyrox.network/market");
        player.sendMessage("§8§m-*-------------------------------------*-");


        if (player.getName().equalsIgnoreCase("ClimpyX")) {
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&aMerhaba! &cClimpyX &abu sunucudaki pluginler sana ait olmakla beraber sana hizmet ederler."));
            Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', "&7(ShyroxHub) &aYüce &cClimpyX &asunucuya giriş yaptı, selamlayın!"));

        }
    }


}

package com.shyrox.listener;

import org.bukkit.ChatColor;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

import static org.bukkit.Bukkit.getPlayer;
import static org.bukkit.Bukkit.getServer;

public class CrashProtectionListener implements Listener {


    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onchat(AsyncPlayerChatEvent olay) {
        if (olay.getPlayer() instanceof Player) {

            Player oyuncu = olay.getPlayer();
            if (olay.getMessage().contains("İ")) {

                olay.setCancelled(true);
                oyuncu.sendMessage((ChatColor.RED + "Sunucuya crash atmak mı? Daha fazla denersen yasaklanacaksın."));
                log(oyuncu.getName() + " isimli oyuncu crash atmayı denedi. &7(CHAT UYARISI)");
            }
            if (olay.getMessage().contains("İ")) {

                olay.setCancelled(true);
                oyuncu.sendMessage((ChatColor.RED + "Sunucuya crash atmak mı? Daha fazla denersen yasaklanacaksın."));
                log(oyuncu.getName() + " isimli oyuncu crash atmayı denedi. &7(CHAT UYARISI)");
            }
        }
    }


    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void oncommand(PlayerCommandPreprocessEvent olay) {
        if (olay.getPlayer() instanceof Player) {

            Player oyuncu = olay.getPlayer();
            if (olay.getMessage().contains("İ")) {

                olay.setCancelled(true);
                oyuncu.sendMessage((ChatColor.RED + "Sunucuya crash atmak mı? Daha fazla denersen yasaklanacaksın."));
                log(oyuncu.getName() + " isimli oyuncu crash atmayı denedi. &7(KOMUT UYARISI)");
            }
            if (olay.getMessage().contains("İ")) {

                olay.setCancelled(true);
                oyuncu.sendMessage((ChatColor.RED + "Sunucuya crash atmak mı? Daha fazla denersen yasaklanacaksın."));
                log(oyuncu.getName() + " isimli oyuncu crash atmayı denedi. &7(KOMUT UYARISI)");
            }
        }
    }



    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onmove(PlayerMoveEvent olay) {
        try {
            Player oyuncu = olay.getPlayer();
            if (olay.getPlayer() instanceof Player &&
                    oyuncu.getItemInHand() != null) {

                String itemadi = oyuncu.getItemInHand().getItemMeta().getDisplayName();
                if (itemadi != null &&
                        itemadi.contains("İ")) {

                    oyuncu.getInventory().setItemInHand(null);
                    oyuncu.sendMessage((ChatColor.RED + "Sunucuya crash atmak mı? Daha fazla denersen yasaklanacaksın."));
                    log(oyuncu.getName() + " isimli oyuncu crash atmayı denedi. &7(ITEM UYARISI)");
                }
                List<String> lore = oyuncu.getItemInHand().getItemMeta().getLore();
                if (lore != null &&
                        lore.contains("İ"))
                {
                    oyuncu.getInventory().setItemInHand(null);
                    oyuncu.sendMessage((ChatColor.RED + "Sunucuya crash atmak mı? Daha fazla denersen yasaklanacaksın."));
                    log(oyuncu.getName() + " isimli oyuncu crash atmayı denedi. &7(ITEM UYARISI)");
                }

            }
        } catch (Exception exception) {}
    }


    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onclick(InventoryClickEvent e) {
        if (!e.isCancelled()) {

            HumanEntity ent = e.getWhoClicked();
            if (ent instanceof Player) {

                Player player = (Player)ent;
                Inventory inv = e.getInventory();
                if (inv instanceof org.bukkit.inventory.AnvilInventory) {

                    InventoryView view = e.getView();
                    int rawSlot = e.getRawSlot();
                    if (rawSlot == view.convertSlot(rawSlot) &&
                            rawSlot == 2) {

                        ItemStack item = e.getCurrentItem();
                        if (item != null) {

                            ItemMeta meta = item.getItemMeta();
                            if (meta != null &&
                                    meta.hasDisplayName()) {

                                String displayName = meta.getDisplayName();
                                if (displayName.contains("İ")) {

                                    e.setCancelled(true);
                                    player.sendMessage((ChatColor.RED + "Sunucuya crash atmak mı? Daha fazla denersen yasaklanacaksın."));
                                    log(player.getName() + " isimli oyuncu crash atmayı denedi. &7(ITEM UYARISI)");
                                }
                                if (displayName.contains("İ")) {

                                    e.setCancelled(true);
                                    player.sendMessage((ChatColor.RED + "Sunucuya crash atmak mı? Daha fazla denersen yasaklanacaksın."));
                                    log(player.getName() + " isimli oyuncu crash atmayı denedi. &7(ITEM UYARISI)");
                                }
                            }
                        }
                    }
                }
            }
        }
    }


    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onsignchange(SignChangeEvent e) {
        for (int i = 0; i < 4; i++) {
            if (e.getLine(i).matches("^[a-zA-Z0-9_]*$")) {

                if (e.getLine(i).length() > 20)
                {
                    e.setCancelled(true);
                    e.getPlayer().sendMessage((ChatColor.RED + "Sunucuya crash atmak mı? Daha fazla denersen yasaklanacaksın."));
                    log(e.getPlayer().getName() + " isimli oyuncu crash atmayı denedi. &7(TABELA UYARISI)");
                }

            } else if (e.getLine(i).length() > 50) {

                e.setCancelled(true);
                e.getPlayer().sendMessage((ChatColor.RED + "Sunucuya crash atmak mı? Daha fazla denersen yasaklanacaksın."));
                log(e.getPlayer().getName() + " isimli oyuncu crash atmayı denedi. &7(TABELA UYARISI)");
            }
        }
    }


    public void log(String message) {
        for(Player OPlar : getServer().getOnlinePlayers()) {
            if(OPlar.isOp()){
                OPlar.sendMessage(ChatColor.translateAlternateColorCodes('&', "§7[Log] ► §f " + message));
            }
        }
    }
}

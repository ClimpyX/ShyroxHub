package com.shyrox.modules;

import com.shyrox.ShyroxHub;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;

public class PlayerVisibility implements Listener {

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent e) {
        Player p = e.getPlayer();

        if (e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK) {
            if (p.getItemInHand().getType() == Material.INK_SACK && p.getItemInHand().getDurability() == 8) {
                if (ShyroxHub.getInstance().getCooldownTime().containsKey(p)) {
                    p.sendMessage("§cTekrar kullanım için §e§l" + ShyroxHub.getInstance().getCooldownTime().get(p) + "s §cbekleyiniz");
                    p.playSound(p.getLocation(), Sound.CAT_MEOW, 1F, 1F);
                    return;
                } else {
                    for (Player hplayer : Bukkit.getOnlinePlayers())
                        p.hidePlayer(hplayer);
                    p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&bSunucuda bulunan oyuncular başarıyla gizlendi!"));
                    ItemStack VisOff = new ItemStack(Material.INK_SACK, 1, (short) 10);
                    ItemMeta voff = VisOff.getItemMeta();
                    voff.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&bÜyeler: &cGizli"));
                    VisOff.setItemMeta(voff);
                    p.getInventory().getItemInHand();
                    p.getInventory().setItemInHand(VisOff);
                    ShyroxHub.getInstance().getCooldownTime().put(p, 5);
                    ShyroxHub.getInstance().getCooldownTask().put(p, new BukkitRunnable() {
                        public void run() {
                            ShyroxHub.getInstance().getCooldownTime().put(p, ShyroxHub.getInstance().getCooldownTime().get(p) - 1);
                            if (ShyroxHub.getInstance().getCooldownTime().get(p) == 0) {
                                ShyroxHub.getInstance().getCooldownTime().remove(p);
                                ShyroxHub.getInstance().getCooldownTask().remove(p);
                                cancel();
                            }
                        }
                    });

                    ShyroxHub.getInstance().getCooldownTask().get(p).runTaskTimer(ShyroxHub.getInstance(), 20, 20);
                    return;
                }
            }

            if (p.getItemInHand().getType() == Material.INK_SACK && p.getItemInHand().getDurability() == 10) {
                if (ShyroxHub.getInstance().getCooldownTime().containsKey(p)) {
                    p.sendMessage("§cTekrar kullanım için §e§l" + ShyroxHub.getInstance().getCooldownTime().get(p) + "s §cbekleyiniz");
                    p.playSound(p.getLocation(), Sound.CAT_MEOW, 1F, 1F);
                    return;
                } else {
                    for (Player hplayer : Bukkit.getOnlinePlayers())
                        p.showPlayer(hplayer);
                    p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&dSunucuda bulunan oyuncular görünür hale geldi!"));
                    ItemStack VisOn = new ItemStack(Material.INK_SACK, 1, (short) 8);
                    ItemMeta von = VisOn.getItemMeta();
                    von.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&bÜyeler: &aGösteriliyor"));
                    VisOn.setItemMeta(von);
                    p.getInventory().getItemInHand();
                    p.getInventory().setItemInHand(VisOn);
                    ShyroxHub.getInstance().getCooldownTime().put(p, 5);
                    ShyroxHub.getInstance().getCooldownTask().put(p, new BukkitRunnable() {
                        public void run() {
                            ShyroxHub.getInstance().getCooldownTime().put(p, ShyroxHub.getInstance().getCooldownTime().get(p) - 1);
                            if (ShyroxHub.getInstance().getCooldownTime().get(p) == 0) {
                                ShyroxHub.getInstance().getCooldownTime().remove(p);
                                ShyroxHub.getInstance().getCooldownTask().remove(p);
                                cancel();
                            }
                        }
                    });

                    ShyroxHub.getInstance().getCooldownTask().get(p).runTaskTimer(ShyroxHub.getInstance(), 20, 20);
                    return;
                }
            }
        }
    }
}

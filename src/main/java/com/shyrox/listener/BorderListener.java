package com.shyrox.listener;

import com.shyrox.ShyroxHub;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.player.*;

public class BorderListener implements Listener {
    private static final int BORDER_OFFSET_TELEPORTS = 50;
    
    public static boolean isWithinBorder(final Location location) {
        final int borderSize = (int) ShyroxHub.getInstance().getConfig().getDouble("border.size");
        return Math.abs(location.getBlockX()) <= borderSize && Math.abs(location.getBlockZ()) <= borderSize;
    }
    
    @EventHandler(ignoreCancelled = true, priority = EventPriority.HIGH)
    public void onCreaturePreSpawn(final CreatureSpawnEvent event) {
        if (!isWithinBorder(event.getLocation())) {
            event.setCancelled(true);
        }
    }
    
    @EventHandler(ignoreCancelled = true, priority = EventPriority.HIGH)
    public void onBucketEmpty(final PlayerBucketFillEvent event) {
        if (!isWithinBorder(event.getBlockClicked().getLocation())) {
            event.setCancelled(true);
            event.getPlayer().sendMessage(ChatColor.RED + "Sınırın sonunda boş kovaları dolduramazsınız.");
        }
    }
    
    @EventHandler(ignoreCancelled = true, priority = EventPriority.HIGH)
    public void onBucketEmpty(final PlayerBucketEmptyEvent event) {
        if (!isWithinBorder(event.getBlockClicked().getLocation())) {
            event.setCancelled(true);
            event.getPlayer().sendMessage(ChatColor.RED + "Kovaları sınırdan sonra boşaltamazsın.");
        }
    }
    
    @EventHandler(ignoreCancelled = true, priority = EventPriority.HIGH)
    public void onBlockPlace(final BlockPlaceEvent event) {
        if (!isWithinBorder(event.getBlock().getLocation())) {
            event.setCancelled(true);
            event.getPlayer().sendMessage(ChatColor.RED + "Sınırın ötesine blok yerleştiremezsiniz.");
        }
    }
    
    @EventHandler(ignoreCancelled = true, priority = EventPriority.HIGH)
    public void onBlockBreak(final BlockBreakEvent event) {
        if (!isWithinBorder(event.getBlock().getLocation())) {
            event.setCancelled(true);
            event.getPlayer().sendMessage(ChatColor.RED + "Sınırın ötesine geçen blokları kıramazsın.");
        }
    }
    
    @EventHandler(ignoreCancelled = true, priority = EventPriority.HIGH)
    public void onPlayerMove(final PlayerMoveEvent event) {
        final Location from = event.getFrom();
        final Location to = event.getTo();
        if (from.getBlockX() == to.getBlockX() && from.getBlockZ() == to.getBlockZ()) {
            return;
        }
		
        if (!isWithinBorder(to) && isWithinBorder(from)) {
            final Player player = event.getPlayer();
            player.sendMessage(ChatColor.RED + "Karşınızda bulunan sınırı geçemezsiniz");
            event.setTo(from);
            final Entity vehicle = player.getVehicle();
            if (vehicle != null) {
                vehicle.eject();
                vehicle.teleport(from);
                vehicle.setPassenger((Entity)player);
            }
        }
    }

    @EventHandler(ignoreCancelled = true, priority = EventPriority.HIGH)
    public void onPlayerPearl(PlayerTeleportEvent e) {
        if (e.getCause() == PlayerTeleportEvent.TeleportCause.ENDER_PEARL) {
            Player player = e.getPlayer();
            Location to = e.getTo();
            if (!isWithinBorder(to)) {
                player.sendMessage(ChatColor.RED + "Sınırın dışına ender incisi atamazsın.");
                e.setCancelled(true);
            }
        }

    }

    public void onPlayerPortal(PlayerPortalEvent event) {
        Location to = event.getTo();
        if (!isWithinBorder(to)) {
            PlayerTeleportEvent.TeleportCause cause = event.getCause();
            if (cause != PlayerTeleportEvent.TeleportCause.NETHER_PORTAL || cause == PlayerTeleportEvent.TeleportCause.ENDER_PEARL && isWithinBorder(event.getFrom())) {
                event.setCancelled(true);
                event.getPlayer().sendMessage(ChatColor.RED + "Sınırın dışına geçemezsiniz.");
            } else {
                World.Environment toEnvironment = to.getWorld().getEnvironment();
                if (toEnvironment != World.Environment.NORMAL) {
                    return;
                }

                int x = to.getBlockX();
                int z = to.getBlockZ();
        //        int borderSize = ((Integer) ConfigurationService.BORDER_SIZES.get(toEnvironment)).intValue();
                int borderSize = ((Integer) (int) ShyroxHub.getInstance().getConfig().getDouble("border.size"));
                boolean extended = false;
                if (Math.abs(x) > borderSize) {
                    to.setX(x > 0 ? (double)(borderSize - 50) : (double)(-borderSize + 50));
                    extended = true;
                }

                if (Math.abs(z) > borderSize) {
                    to.setZ(z > 0 ? (double)(borderSize - 50) : (double)(-borderSize + 50));
                    extended = true;
                }

                if (extended) {
                    to.add(0.5D, 0.0D, 0.5D);
                    event.setTo(to);
                    event.getPlayer().sendMessage(ChatColor.RED + "Girdiğiniz portalların seyahat yeri sınırın üstündeydi, içeriye taşındınız.");
                }
            }
        }

    }
    
}

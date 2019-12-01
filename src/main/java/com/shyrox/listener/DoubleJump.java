package com.shyrox.listener;

import com.shyrox.ShyroxHub;
import org.bukkit.entity.*;
import org.bukkit.event.*;
import org.bukkit.event.player.*;
import org.bukkit.event.entity.*;
import org.bukkit.*;
import org.bukkit.plugin.*;

public class DoubleJump implements Listener {
    @EventHandler
    public void onPlayerToggleFlight(final PlayerToggleFlightEvent event) {
        final Player player = event.getPlayer();
        final Sound sound = Sound.valueOf("BLAZE_DEATH");
        final Effect effect = Effect.valueOf("MOBSPAWNER_FLAMES");
        if (player.getGameMode() == GameMode.CREATIVE) {
            return;
        }

        event.setCancelled(true);
        player.setAllowFlight(false);
        player.setFlying(false);
        player.setVelocity(player.getLocation().getDirection().multiply(1.5).setY(1));
        player.playSound(player.getLocation(), sound, 1.0f, 0.0f);
        player.playEffect(player.getLocation(), effect, (Object)null);
    }
    
    @EventHandler
    public void onPlayerMove(final PlayerMoveEvent event) {
        final Player player = event.getPlayer();
        if (player.getGameMode() != GameMode.CREATIVE && player.getLocation().subtract(0.0, 1.0, 0.0).getBlock().getType() != Material.AIR && !player.isFlying()) {
            player.setAllowFlight(true);
        }
    }
    
    @EventHandler
    public void onFallDamage(final EntityDamageEvent e) {
        if (e.getEntity() instanceof Player && e.getCause() == EntityDamageEvent.DamageCause.FALL) {
            e.setCancelled(true);
        }
    }
    
    @EventHandler
    public void move(final PlayerMoveEvent e) {
        final Player f = e.getPlayer();
        if (e.getTo().getY() < 2.0) {
            ShyroxHub.instance.getServer().getScheduler().scheduleSyncDelayedTask((Plugin) ShyroxHub.instance, (Runnable)new Runnable() {
                @Override
                public void run() {
                    final double y = f.getLocation().getY() - 2.0;
                    final Location l = new Location(f.getLocation().getWorld(), f.getLocation().getX(), y, f.getLocation().getZ(), f.getLocation().getYaw(), f.getLocation().getPitch());
                    f.getWorld().playEffect(l, Effect.ENDER_SIGNAL, 50, 30);
                }
            }, 10L);
        }
    }
}

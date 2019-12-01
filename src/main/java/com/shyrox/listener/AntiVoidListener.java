package com.shyrox.listener;

import com.shyrox.ShyroxHub;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

public class AntiVoidListener implements Listener {

    @EventHandler
    public void onDamage(EntityDamageEvent e) {
        if(e.getCause() != EntityDamageEvent.DamageCause.VOID) return;
        if(!(e.getEntity() instanceof Player)) return;
        Player player = (Player) e.getEntity();
        e.setCancelled(true);
        e.setDamage(0.0);

        World w = Bukkit.getServer().getWorld(ShyroxHub.instance.getConfig().getString("spawn.world"));
        double x = ShyroxHub.instance.getConfig().getDouble("spawn.x");
        double y = ShyroxHub.instance.getConfig().getDouble("spawn.y");
        double z = ShyroxHub.instance.getConfig().getDouble("spawn.z");
        float yaw = (float) ShyroxHub.instance.getConfig().getDouble("spawn.yaw");
        float pitch = (float) ShyroxHub.instance.getConfig().getDouble("spawn.pitch");
        player.teleport(new Location(w, x, y, z, yaw, pitch));
    }

}

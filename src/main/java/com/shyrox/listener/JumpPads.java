package com.shyrox.listener;

import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.util.Vector;

public class JumpPads implements Listener {
	
	@SuppressWarnings("deprecation")
	@EventHandler
	public void onMove(PlayerMoveEvent e) {
		Player p = e.getPlayer();
		
		if (p.getLocation().getBlock().getType() == Material.GOLD_PLATE) {
	      Vector v = p.getLocation().getDirection().multiply(3.5D).setY(1.5D);
	      p.setVelocity(v);
	      p.playSound(p.getLocation(), Sound.ENDERDRAGON_WINGS, 4.0F, 3.0F);
	      
	      for (Player players : Bukkit.getOnlinePlayers()) {
	        players.playEffect(p.getLocation(), Effect.PORTAL, 2000);
	      }
	   }
	}

}

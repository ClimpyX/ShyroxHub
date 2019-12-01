package com.shyrox.commands;

import com.shyrox.ShyroxHub;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SetSpawnCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
        Player p = (Player) sender;
        if (cmd.getName().equalsIgnoreCase("setspawn")) {

            if (!(p.hasPermission("utilities.command.setspawn") && p.hasPermission("shyrox.admin"))) {
                p.sendMessage("§cBunun için izniniz yok.");
                return true;
            }

            if (!(sender instanceof Player)) {
                sender.sendMessage("§4Bunu yapamazsın.");
                return true;
            }

            ShyroxHub.getInstance().getConfig().set("spawn.world", p.getLocation().getWorld().getName());
            ShyroxHub.getInstance().getConfig().set("spawn.x", p.getLocation().getX());
            ShyroxHub.getInstance().getConfig().set("spawn.y", p.getLocation().getY());
            ShyroxHub.getInstance().getConfig().set("spawn.z", p.getLocation().getZ());
            ShyroxHub.getInstance().getConfig().set("spawn.yaw", p.getLocation().getYaw());
            ShyroxHub.getInstance().getConfig().set("spawn.pitch", p.getLocation().getPitch());
            ShyroxHub.getInstance().saveConfig();
            p.sendMessage("§aSpawn noktası başarıyla ayarlandı.");
            return true;
        }
        return true;
    }
}

package com.shyrox.commands;

import com.shyrox.ShyroxHub;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SpawnCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
        Player p = (Player) sender;
        if (cmd.getName().equalsIgnoreCase("spawn")) {

            if (!(sender instanceof Player)) {
                sender.sendMessage("Bu komutu sadece oyuncular kullanabilir.");
                return true;
            }

            if (ShyroxHub.getInstance().getConfig().getConfigurationSection("spawn") == null) {
                p.sendMessage(ChatColor.RED + "Spawn noktası ayarlanmamış, yöneticiye bildiriniz.");
                return true;
            }


            World w = Bukkit.getServer().getWorld(ShyroxHub.getInstance().getConfig().getString("spawn.world"));
            double x = ShyroxHub.getInstance().getConfig().getDouble("spawn.x");
            double y = ShyroxHub.getInstance().getConfig().getDouble("spawn.y");
            double z = ShyroxHub.getInstance().getConfig().getDouble("spawn.z");
            float yaw = (float) ShyroxHub.getInstance().getConfig().getDouble("spawn.yaw");
            float pitch = (float) ShyroxHub.getInstance().getConfig().getDouble("spawn.pitch");
            p.teleport(new Location(w, x, y, z, yaw, pitch));
        }
        return false;
    }
}

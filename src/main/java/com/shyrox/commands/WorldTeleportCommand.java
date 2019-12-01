package com.shyrox.commands;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class WorldTeleportCommand implements CommandExecutor {


    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[]args){
        Player player = (Player)sender;
        if(cmd.getName().equalsIgnoreCase("world")){
            if (!player.hasPermission("elice.commands.worldteleport") && !sender.hasPermission("elice.admin") && !sender.hasPermission("elice.mod")) {
                player.sendMessage("§dShyrox ► §cBir oyunucunun dünyalar arasında geçiş yapmasından daha tehlikeli bir şey olabilir mi?");
                return true;
            }

            if(args.length == 0){
                player.sendMessage("§cDoğru Kullanım: /world <Dünya Adı..>");
                return false;
            }

            String worldName = "";
            for(String arg : args)
                worldName = arg;
            World world = Bukkit.getWorld(worldName);
            if(world != null) {
                player.teleport(new Location(world, 0, 80, 0));
                Bukkit.broadcastMessage("§dShyrox ► §6" + player.getName() + " §foyuncusu §6§l" + world.getName() + " §fdünyasına ışınlandı.");
            }



            else if(world == null){

                player.sendMessage("§cBöyle bir dünya bulunamadı.");

            }

            return true;

        }

        return false;


    }



}

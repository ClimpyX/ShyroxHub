package com.shyrox.commands;

import java.util.Locale;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GamemodeCommand implements CommandExecutor {
    public GamemodeCommand() {
    }

    public static boolean canSee(CommandSender sender, Player target) {
        return target != null && (!(sender instanceof Player) || ((Player) sender).canSee(target));
    }

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        Player player = (Player) sender;
        if (!player.hasPermission("shyrox.commands.gamemode") && !player.hasPermission("shyrox.admin")) {
            player.sendMessage("§dShyrox ► §cYetkiye sahip değilsin.");
            return true;
        }

        if (args.length < 1) {
            sender.sendMessage(ChatColor.RED + "Kullanım: /" + label + " <oyun modu> [Oyuncu Adı]");
            return true;
        } else {
            GameMode mode = this.getGameModeByName(args[0]);
            if (mode == null) {
                sender.sendMessage(ChatColor.RED + "Oyun modu '" + ChatColor.WHITE + args[0] + ChatColor.RED + "' bulunamadı.");
                return true;
            } else {
                Player target;
                if (args.length > 1) {
                    if (sender.hasPermission(command.getPermission() + ".others")) {
                        target = Bukkit.getPlayer(args[1]);
                    } else {
                        target = null;
                    }
                } else {
                    if (!(sender instanceof Player)) {
                        sender.sendMessage(ChatColor.RED + "Kullanım: /" + label + " <oyun modu> [Oyuncu Adı]");
                        return true;
                    }

                    target = (Player) sender;
                }

                if (target != null && canSee(sender, target)) {
                    if (target.getGameMode() == mode) {
                        sender.sendMessage("§dShyrox ► " + ChatColor.WHITE + target.getName() + ChatColor.RED + " oyunusunun oyun modu zaten " + ChatColor.WHITE + mode.name() + '.');
                        return true;
                    } else {
                        target.setGameMode(mode);
                        Bukkit.broadcastMessage("§dShyrox ► " + ChatColor.GREEN + target.getName() + ChatColor.WHITE + " oyuncusunun oyun modu değiştirildi. (" + mode.name() + ")");
                        return true;
                    }
                } else {
                    sender.sendMessage(String.format("§dShyrox ► " + ChatColor.RED + "Oyuncu adı '" + ChatColor.WHITE + args[1] + ChatColor.BLUE + "' bulunamadı."));
                    return true;
                }
            }
        }
    }




    private GameMode getGameModeByName(String id) {
        id = id.toLowerCase(Locale.ENGLISH);
        return !id.equalsIgnoreCase("gmc") && !id.contains("creat") && !id.equalsIgnoreCase("1") && !id.equalsIgnoreCase("c")?(!id.equalsIgnoreCase("gms") && !id.contains("survi") && !id.equalsIgnoreCase("0") && !id.equalsIgnoreCase("s")?(!id.equalsIgnoreCase("gma") && !id.contains("advent") && !id.equalsIgnoreCase("2") && !id.equalsIgnoreCase("a")?(!id.equalsIgnoreCase("gmt") && !id.contains("toggle") && !id.contains("cycle") && !id.equalsIgnoreCase("t")?null:null):GameMode.ADVENTURE):GameMode.SURVIVAL):GameMode.CREATIVE;
    }
}


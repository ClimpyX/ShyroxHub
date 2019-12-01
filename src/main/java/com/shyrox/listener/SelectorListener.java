package com.shyrox.listener;

import com.shyrox.ShyroxHub;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import sk.Adin.PMenu.API.UltraMenuAPI;

import java.util.Arrays;

public class SelectorListener implements Listener {
    private final ShyroxHub hub = ShyroxHub.getInstance();

    public ItemStack getSelector() {
        ItemStack compass = new ItemStack(Material.COMPASS, 1);
        ItemMeta compassMeta = compass.getItemMeta();
        compassMeta.setDisplayName("§aSunucu Menüsü");
        compass.setItemMeta(compassMeta);
        return compass;
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if (event.getClickedInventory() != null) {
            Player player = (Player)event.getWhoClicked();

                if (event.getClickedInventory().getTitle().contains("§aSunucu Menüsü")) {
                event.setCancelled(true);
                if (event.getRawSlot() == 12) {
                    Bukkit.getServer().dispatchCommand(player, "server SkyBlock");
                    player.playSound(player.getLocation(), Sound.NOTE_BASS, 1F, 1F);
                }


                if (event.getRawSlot() == 14) {
                    Bukkit.getServer().dispatchCommand(player, "server OPSkyBlock");
                    player.playSound(player.getLocation(), Sound.NOTE_BASS, 1F, 1F);
                }
            }
        }

    }

    public void openServerSelectorMenu(Player player) {
        Inventory inventory = Bukkit.getServer().createInventory(player,InventoryType.CHEST, ("§8§l» §8Sunucu Menüsü"));
        ItemStack opskyblock = new ItemStack(Material.DIAMOND_CHESTPLATE, 1);
        ItemMeta opskyblockMeta = opskyblock.getItemMeta();
        opskyblockMeta.setDisplayName("§b§lOPSkyBlock §bSunucusu");
        opskyblockMeta.setLore(Arrays.asList(" ", "§7Kolay olarak kasılarak kocaman adalar inşa mı etmek istiyorsunuz?!", "§7O zaman tam size göre bi tercihimiz var; §bOPSkyBlock", " ", "§aOyuna giriş yapmak için tıklayın!"));
        opskyblock.setItemMeta(opskyblockMeta);

        ItemStack skyblock = new ItemStack(Material.CHEST, 1);
        ItemMeta skyblockMeta = skyblock.getItemMeta();
        skyblockMeta.setDisplayName("§6§lSkyBlock §6Sunucusu");
        skyblockMeta.setLore(Arrays.asList(" ", "§7Herkes gibi kolay olarak değil,", "§7kendi emeklerin ile kendi omuzlarına çıkabilirsin!"," ", "§aOyuna giriş yapmak için tıklayın!"));
        skyblock.setItemMeta(skyblockMeta);

        inventory.setItem(12, opskyblock);
        inventory.setItem(14, skyblock);
        player.openInventory(inventory);


    }

    //@EventHandler
   // public void onPlayerInteract(PlayerInteractEvent event) {
    //    Action action = event.getAction();
     //   if (action == Action.RIGHT_CLICK_AIR || action == Action.RIGHT_CLICK_BLOCK) {
      //      Player player = event.getPlayer();
      //      ItemStack itemStack = player.getItemInHand();
         //   if (itemStack != null && itemStack.getType() == Material.BEDROCK) {
         //       this.openServerSelectorMenu(player);
         //   }
       // }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        Action action = event.getAction();
        if (action == Action.RIGHT_CLICK_AIR || action == Action.RIGHT_CLICK_BLOCK) {
            Player player = event.getPlayer();
            ItemStack itemStack = player.getItemInHand();
            if (itemStack != null && itemStack.getType() == Material.COMPASS) {
                UltraMenuAPI.openNormalMenu(player, "servers.yml");
            }
        }
    }
}

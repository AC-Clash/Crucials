package com.acclash.crucials.events;

import com.acclash.crucials.utils.BanMenuUtils;
import org.bukkit.BanList;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class BanInventoryListener implements Listener {

    @EventHandler
    public void onMenuClick(InventoryClickEvent e) {

        Player player = (Player) e.getWhoClicked();
        if (e.getView().getTitle().equalsIgnoreCase(ChatColor.BLUE + "Player List")) {
            e.setCancelled(true);
            if (e.getCurrentItem().getType() == Material.PLAYER_HEAD) {
                Player name = player.getServer().getPlayerExact(ChatColor.stripColor(e.getCurrentItem().getItemMeta().getDisplayName()));
                if (name.isBanned()) {
                    BanMenuUtils.openUnBanMenu(player, name);
                } else {
                    BanMenuUtils.openConfirmBanMenu(player, name);
                }
            }
        } else if (e.getView().getTitle().equalsIgnoreCase(ChatColor.RED + "Ban this player?")) {
            e.setCancelled(true);

            if (e.getCurrentItem().getType() == Material.BARRIER) {

                BanMenuUtils.openBanMenu(player);

            } else if (e.getCurrentItem().getType() == Material.COCOA_BEANS) {
                String name = ChatColor.stripColor(e.getClickedInventory().getItem(14).getItemMeta().getDisplayName());
                player.getServer().getBanList(BanList.Type.NAME).addBan(ChatColor.stripColor(name), "You are banned for unacceptable behavior.", null, null);
                player.sendMessage("Banned " + name);

                BanMenuUtils.openBanMenu(player);
            }
        } else if (e.getView().getTitle().equalsIgnoreCase(ChatColor.RED + "Unban this player?")) {
            e.setCancelled(true);

            if (e.getCurrentItem().getType() == Material.BARRIER) {

                BanMenuUtils.openBanMenu(player);

            } else if (e.getCurrentItem().getType() == Material.COCOA_BEANS) {
                String name = ChatColor.stripColor(e.getClickedInventory().getItem(14).getItemMeta().getDisplayName());
                Bukkit.getBanList(BanList.Type.NAME).pardon(name);
                player.sendMessage("Unbanned " + name);

                BanMenuUtils.openBanMenu(player);
            }

        }
    }

}

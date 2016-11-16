package com.njdaeger.essentials.bannermanager.listeners;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;

import com.njdaeger.essentials.Core;
import com.njdaeger.essentials.bannermanager.Banner;

public class Listener extends Banner implements org.bukkit.event.Listener{
	Plugin plugin = Bukkit.getPluginManager().getPlugin("EssentialCommands");
	public Listener(Core plugin) {
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
	}
	@EventHandler
	public void onInvClick(InventoryClickEvent e) {
		if (editmode.contains(e.getWhoClicked().getName())) {
			int s = e.getSlot();
			if (s == 8) {
				e.setCancelled(true);
				e.getWhoClicked().closeInventory();
				return;
			}
			if ((s == 53) || (s == 52) || (s == 51) || 
					(s == 42) || (s == 44) || (s == 35) || 
					(s == 34) || (s == 33) || (s == 4) || 
					(s == 7) || (s == 13) || (s == 14) ||
					(s == 15) || (s == 16) || (s == 17) ||
					(s == 22) || (s == 26) || (s == 31) ||
					(s == 32) || (s == 36) || (s == 37) ||
					(s == 38) || (s == 39) || (s == 40) ||
					(s == 41)) {
				e.setCancelled(true);
				return;
			}
			if ((s == 0) || (s == 1) || (s == 2) ||
					(s == 3) || (s == 9) || (s == 10) ||
					(s == 11) || (s == 12) || (s == 18) ||
					(s == 19) || (s == 20) || (s == 21) || 
					(s == 27) || (s == 28) || (s == 29) ||
					(s == 30)) {
				e.getInventory().setItem(43, this.getPreview(e.getCurrentItem().getItemMeta(), e.getCurrentItem()));
				e.setCancelled(true);
			}
			if (s == 43) {
				e.getWhoClicked().getInventory().addItem(this.getPreview(e.getCurrentItem().getItemMeta(), e.getCurrentItem()));
				return;
			}
		}
		Bukkit.getLogger().info(editmode.toString());
		return;
	}
	@EventHandler
	public void onInvLeave(InventoryCloseEvent e) {
		if (editmode.contains(e.getPlayer().getName())) {
			removeEditMode(e.getPlayer().getName());
			return;
		}
		return;
	}
	private ItemStack getPreview(ItemMeta m, ItemStack s) {
		ItemStack stack = new ItemStack(Material.BANNER, 16, s.getDurability());
		stack.setItemMeta(m);
		return stack;
	}
}

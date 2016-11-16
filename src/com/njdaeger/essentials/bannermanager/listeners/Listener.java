package com.njdaeger.essentials.bannermanager.listeners;

import java.util.ArrayList;

import net.md_5.bungee.api.ChatColor;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;

import com.njdaeger.essentials.Core;
import com.njdaeger.essentials.bannermanager.Banner;
import com.njdaeger.essentials.bannermanager.GuiType;
import com.njdaeger.essentials.bannermanager.utils.BannerGUI;

public class Listener extends Banner implements org.bukkit.event.Listener{
	Plugin plugin = Bukkit.getPluginManager().getPlugin("EssentialCommands");
	public Listener(Core plugin) {
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
	}
	BannerGUI bGui = new BannerGUI();
	
	@EventHandler
	public void onInvClick(InventoryClickEvent e) {
		if (editmode.containsKey(e.getWhoClicked().getName())) {
			int s = e.getSlot();
			if (editmode.get(e.getWhoClicked().getName()).equals(GuiType.COLOR)) {	
				if (s == 8) {
					e.setCancelled(true);
					e.getWhoClicked().closeInventory();
					editmode.remove(e.getWhoClicked().getName(), GuiType.COLOR);
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
				if (s == 48) {
					e.setCancelled(true);
					if (e.getInventory().getItem(43) == null) {
						Player player = (Player) e.getWhoClicked();
						player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_PLACE, 1, 1);
						player.sendMessage(ChatColor.RED + "Pick a base color.");
						return;
					}
					ItemStack stack = this.getBannerOnClose(e.getInventory().getItem(43));
					ArrayList<ItemStack> tempStack = new ArrayList<ItemStack>();
					tempStack.set(0, stack);
					e.getWhoClicked().closeInventory();
					
					bGui.newBannerGui((Player)e.getWhoClicked(), GuiType.EFFECTS);
					editmode.replace(e.getWhoClicked().getName(), GuiType.COLOR, GuiType.EFFECTS);
					e.getInventory().setItem(25, tempStack.get(0));
					tempStack.remove(0);
					return;
				}
			}
			if (editmode.get(e.getWhoClicked().getName()).equals(GuiType.EFFECTS)) {
				if (s == 0) {
					e.getWhoClicked().sendMessage("Ye.");
					return;
				}
			}
		}
		Bukkit.getLogger().info(editmode.toString());
		return;
	}
	private ItemStack getPreview(ItemMeta m, ItemStack s) {
		ItemStack stack = new ItemStack(Material.BANNER, 16, s.getDurability());
		stack.setItemMeta(m);
		return stack;
	}
	private ItemStack getBannerOnClose(ItemStack s) {
		ItemStack stack = new ItemStack(Material.BANNER, 1, s.getDurability());
		return stack;
	}
}

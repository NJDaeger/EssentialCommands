package com.njdaeger.java.descrete.bannermanager.listeners;

import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import com.njdaeger.java.descrete.bannermanager.Banner;
import com.njdaeger.java.descrete.bannermanager.GuiType;
import com.njdaeger.java.descrete.bannermanager.utils.BannerGUI;

public class Effects2GUI extends Banner{
	BannerGUI bGui = new BannerGUI();
	public void listen(InventoryClickEvent e) {
		int s = e.getSlot();
		Player player = (Player) e.getWhoClicked();
		if (s == 0) {
			e.setCancelled(true);
			ItemStack stack = new ItemStack(Material.BANNER, 1, e.getInventory().getItem(25).getDurability());
			e.getInventory().setItem(25, stack);
			player.playSound(player.getLocation(), Sound.UI_BUTTON_CLICK, 1, 1);
			return;
		}
		if (s == 1) {
			/*
			 * Change the color of the effect currently chosen. If no effect is chosen then dont allow it to pass.
			 * 
			 */
		}
		if (s == 2) {
			/*
			 * Save the banner you are currently on. (possibly have a banner gui pop up and  have the player type in a name in the text box
			 * 
			 */
		}
		if (s == 3) {
			/*
			 * Create a new layer. 
			 * 
			 */
		}
		if (s == 4) {
			main.add(player.getName());
			effects2.remove(player.getName());
			e.setCancelled(true);
			player.playSound(player.getLocation(), Sound.UI_BUTTON_CLICK, 1, 1);
			bGui.newBannerGui(player, GuiType.COLOR, this.getPreview(e.getInventory().getItem(25).getItemMeta(),  e.getInventory().getItem(25)), 0);
			return;
		}
		if (s == 5) {
			effects1.add(player.getName());
			effects2.remove(player.getName());
			e.setCancelled(true);
			player.playSound(player.getLocation(), Sound.UI_BUTTON_CLICK, 1, 1);
			bGui.newBannerGui(player, GuiType.EFFECTS, this.getPreview(e.getInventory().getItem(25).getItemMeta(), e.getInventory().getItem(25)), 1);
			return;
		}
		if (s == 6) {
			e.setCancelled(true);
			player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_PLACE, 1, 1);
			return;
		}
		if (s == 8) {
			e.setCancelled(true);
			e.getWhoClicked().closeInventory();
			player.playSound(player.getLocation(), Sound.UI_BUTTON_CLICK, 1, 1);
			return;
		}
		if (s == 15 || s == 16 || s == 17 || 
				s == 24 || s == 26 || s == 33 || 
				s == 34 || s == 35 || s == 42 || 
				s == 43 || s == 44 || s == 51) {
			player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_PLACE, 1, 1);
			e.setCancelled(true);
			return;
		}
		if (s == 18 || s == 19 || s == 20 || 
				s == 21 || s == 22 || s == 23 || 
				s == 27 || s == 28 || s == 29 || 
				s == 30 || s == 31 || s == 32 || 
				s == 38 || s == 39) {
			e.setCancelled(true);
			e.getInventory().setItem(25, this.getPreview(e.getCurrentItem().getItemMeta(), e.getCurrentItem()));
			player.playSound(player.getLocation(), Sound.UI_BUTTON_CLICK, 1, 1);
			return;
		}
	}
}

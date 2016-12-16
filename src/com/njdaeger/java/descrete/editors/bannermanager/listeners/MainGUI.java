package com.njdaeger.java.descrete.editors.bannermanager.listeners;

import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.meta.BannerMeta;

import com.njdaeger.java.descrete.editors.bannermanager.Banner;
import com.njdaeger.java.descrete.editors.bannermanager.GuiType;
import com.njdaeger.java.descrete.editors.bannermanager.utils.BannerGUI;
import com.njdaeger.java.essentials.enums.Error;

public class MainGUI extends Banner{
	BannerGUI bGui = new BannerGUI();
	public void listen(InventoryClickEvent e) {
		int s = e.getSlot();
		Player player = (Player) e.getWhoClicked();
		if (s == 8) {
			e.setCancelled(true);
			e.getWhoClicked().closeInventory();
			player.playSound(player.getLocation(), Sound.UI_BUTTON_CLICK, 1, 1);
			return;
		}
		if ((s == 53) || (s == 52) || (s == 51) || 
				(s == 42) || (s == 44) || (s == 35) || 
				(s == 34) || (s == 33) || (s == 4)  || 
				(s == 7)  || (s == 13) || (s == 14) ||
				(s == 15) || (s == 16) || (s == 17) ||
				(s == 22) || (s == 26) || (s == 31) ||
				(s == 32) || (s == 36) || (s == 37) ||
				(s == 38) || (s == 39) || (s == 40) ||
				(s == 41)) {
			player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_PLACE, 1, 1);
			e.setCancelled(true);
			return;
		}
		if ((s == 0) || (s == 1) || (s == 2) ||
				(s == 3)  || (s == 9)  || (s == 10) ||
				(s == 11) || (s == 12) || (s == 18) ||
				(s == 19) || (s == 20) || (s == 21) || 
				(s == 27) || (s == 28) || (s == 29) ||
				(s == 30)) {
			if (e.getInventory().getItem(43) == null) {
				player.playSound(player.getLocation(), Sound.UI_BUTTON_CLICK, 1, 1);
				e.getInventory().setItem(43, this.getPreview(e.getCurrentItem().getItemMeta(), e.getCurrentItem()));
				e.setCancelled(true);
				return;
			}
			else {
				BannerMeta m = (BannerMeta) e.getInventory().getItem(43).getItemMeta();
				if (m.numberOfPatterns() == 0) {
					player.playSound(player.getLocation(), Sound.UI_BUTTON_CLICK, 1, 1);
					e.getInventory().setItem(43, this.getPreview(e.getCurrentItem().getItemMeta(), e.getCurrentItem()));
					e.setCancelled(true);
					return;
				}
				else {
					m.setBaseColor(this.getDyeColor(e.getCurrentItem().getDurability()));
					e.getInventory().getItem(43).setItemMeta(m);
					player.playSound(player.getLocation(), Sound.UI_BUTTON_CLICK, 1, 1);
					e.setCancelled(true);
					return;
				}
				
			}
			
		}
		if (s == 43) {
			player.playSound(player.getLocation(), Sound.ITEM_FLINTANDSTEEL_USE, 1, 1);
			e.getWhoClicked().getInventory().addItem(this.getPreview(e.getCurrentItem().getItemMeta(), e.getCurrentItem()));
			return;
		}
		if (s == 48) {
			e.setCancelled(true);
			if (e.getInventory().getItem(43) == null) {
				player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_PLACE, 1, 1);
				player.sendMessage(Error.NO_BASE_COLOR.sendError());
				return;
			}
			effects1.add(e.getWhoClicked().getName());
			main.remove(e.getWhoClicked().getName());
			player.playSound(player.getLocation(), Sound.UI_BUTTON_CLICK, 1, 1);
			bGui.newBannerGui(player, GuiType.EFFECTS, this.getPreview(e.getInventory().getItem(43).getItemMeta(), e.getInventory().getItem(43)), 1);
			return;
		}
		if (s == 5) {
			e.setCancelled(true);
			player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_PLACE, 1, 1);
			player.sendMessage(Error.NO_PREVIOUS_LAYER.sendError());
			return;
		}
		if (s == 6) {
			e.setCancelled(true);
			player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_PLACE, 1, 1);
			player.sendMessage(Error.NO_NEXT_LAYER.sendError());
			return;
		}
		if (s == 23) {
			e.setCancelled(true);
			return;
		}
		if (s == 24) {
			e.setCancelled(true);
			return;
		}
		if (s == 25) {
			e.setCancelled(true);
			return;
		}
		if (s == 45) {
			e.setCancelled(true);
			e.getInventory().setItem(43, null);
			player.playSound(player.getLocation(), Sound.UI_BUTTON_CLICK, 1, 1);
			return;
		}
		if ((s == 46) || (s == 47)) {
			e.setCancelled(true);
			player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_PLACE, 1, 1);
			player.sendMessage(Error.LAYER_CANNOT_BUMP.sendError());
			return;
		}
		if (s == 49) {
			e.setCancelled(true);
			player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_PLACE, 1, 1);
			player.sendMessage(Error.ALREADY_ON_PALLET.sendError());
			return;
		}
		if (s == 50) {
			e.setCancelled(true);
			if (e.getInventory().getItem(43) == null) {
				player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_PLACE, 1, 1);
				player.sendMessage(Error.NO_BASE_COLOR.sendError());
				return;
			}
			effects1.add(e.getWhoClicked().getName());
			main.remove(e.getWhoClicked().getName());
			bGui.newBannerGui(player, GuiType.EFFECTS, this.getPreview(e.getInventory().getItem(43).getItemMeta(), e.getInventory().getItem(43)), 1);
			return;
		}
	}
}

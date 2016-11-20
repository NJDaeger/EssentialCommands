package com.njdaeger.essentials.bannermanager.listeners;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
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
	BannerGUI bGui;
	MainGUI mainGui;
	
	@EventHandler
	public void onInvClick(InventoryClickEvent e) {
		int s = e.getSlot();
		Player player = (Player) e.getWhoClicked();
		if (main.contains(player.getName())) {
			mainGui.listen(e);
			return;
			/*
			 * 
			 * 
			 * Quit button.
			 * 
			 * 
			 
			if (s == 8) {
				e.setCancelled(true);
				e.getWhoClicked().closeInventory();
				player.playSound(player.getLocation(), Sound.UI_BUTTON_CLICK, 1, 1);
				main.remove(player.getName());
				return;
			}
			
			/*
			 * 
			 * 
			 * All bedrock
			 * 
			 * 
			 
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
			
			/*
			 * 
			 * 
			 * All banners
			 * 
			 * 
			 
			if ((s == 0) || (s == 1) || (s == 2) ||
					(s == 3)  || (s == 9)  || (s == 10) ||
					(s == 11) || (s == 12) || (s == 18) ||
					(s == 19) || (s == 20) || (s == 21) || 
					(s == 27) || (s == 28) || (s == 29) ||
					(s == 30)) {
				/*
				 * 
				 * Means the player just opened the GUI and hasnt selected a color yet.
				 
				if (e.getInventory().getItem(43) == null) {
					player.playSound(player.getLocation(), Sound.UI_BUTTON_CLICK, 1, 1);
					e.getInventory().setItem(43, this.getPreview(e.getCurrentItem().getItemMeta(), e.getCurrentItem()));
					e.setCancelled(true);
					return;
				}
				/*
				 * 
				 * Has already selected the color but either wants to change it with or without a design on it.
				 
				else {
					BannerMeta m = (BannerMeta) e.getInventory().getItem(43).getItemMeta();
					m.setBaseColor(this.getDyeColor(e.getCurrentItem().getDurability()));
					return;
				}
				
			}
			
			/*
			 * 
			 * Get banner button.
			 * 
			 
			if (s == 43) {
				player.playSound(player.getLocation(), Sound.ITEM_FLINTANDSTEEL_USE, 1, 1);
				e.getWhoClicked().getInventory().addItem(this.getPreview(e.getCurrentItem().getItemMeta(), e.getCurrentItem()));
				return;
			}
			
			/*
			 * 
			 * Effects button
			 * 
			 
			if (s == 48) {
				e.setCancelled(true);
				if (e.getInventory().getItem(43) == null) {
					player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_PLACE, 1, 1);
					player.sendMessage(Error.NO_BASE_COLOR.sendError());
					return;
				}
				main.remove(e.getWhoClicked().getName());
				effects1.add(e.getWhoClicked().getName());
				bGui.newBannerGui(player, GuiType.EFFECTS, this.getPreview(e.getInventory().getItem(43).getItemMeta(), e.getInventory().getItem(43)), 1);
				return;
			}
			
			/*
			 * 
			 * Next or previous layer buttons. (both return not allowed due to it being the base color layer)
			 * 
			 
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
			/*
			 * 
			 * Saved banners
			 * 
			 
			if (s == 23) {
				e.setCancelled(true);
				return;
			}
			
			/*
			 * 
			 * Allow a banner to be loaded from the inventory
			 * 
			 
			if (s == 24) {
				e.setCancelled(true);
				return;
			}
			
			/*
			 * 
			 * Save the selected banner (need to figure out how to name the banner) (possibly with anvil ui??)
			 * 
			 
			if (s == 25) {
				e.setCancelled(true);
				return;
			}
			
			/*
			 * 
			 * Reset the current layer.
			 * 
			 
			if (s == 45) {
				e.setCancelled(true);
				e.getInventory().setItem(43, null);
				player.playSound(player.getLocation(), Sound.UI_BUTTON_CLICK, 1, 1);
				return;
			}
			
			/*
			 * 
			 * Bump the layer up in order. (disabled due to it being base color layer)
			 * 
			 
			if ((s == 46) || (s == 47)) {
				e.setCancelled(true);
				player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_PLACE, 1, 1);
				player.sendMessage(Error.LAYER_CANNOT_BUMP.sendError());
				return;
			}
			
			/*
			 * 
			 * Base color pallet (disabled due to already being on it)
			 * 
			 
			if (s == 49) {
				e.setCancelled(true);
				player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_PLACE, 1, 1);
				player.sendMessage(Error.ALREADY_ON_PALLET.sendError());
				return;
			}
			
			/*
			 * 
			 * new layer button. (will open effects page)
			 * 
			 
			if (s == 50) {
				e.setCancelled(true);
				if (e.getInventory().getItem(43) == null) {
					player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_PLACE, 1, 1);
					player.sendMessage(Error.NO_BASE_COLOR.sendError());
					return;
				}
				main.remove(e.getWhoClicked().getName());
				effects1.add(e.getWhoClicked().getName());
				bGui.newBannerGui(player, GuiType.EFFECTS, this.getPreview(e.getInventory().getItem(43).getItemMeta(), e.getInventory().getItem(43)), 1);
				return;
			}
			*/
		}
		
		/*
		 * 
		 * 
		 * 
		 * 
		 * 
		 * 
		 * 
		 * 
		 * 
		 * 
		 * 
		 * 
		 * Effects page 1
		 * 
		 * 
		 */
		if (effects1.contains(player.getName())) {
			if (s == 0) {
				e.setCancelled(true);
				ItemStack stack = new ItemStack(Material.BANNER, 1, e.getInventory().getItem(25).getDurability());
				e.getInventory().setItem(25, stack);
				player.playSound(player.getLocation(), Sound.UI_BUTTON_CLICK, 1, 1);
				return;
				/*
				 * take off the current layer in slot 25. 
				 * IDEA: possibly quick layer selector? move the effects down one slot and add wool in layers.
				 */
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
				e.setCancelled(true);
				player.playSound(player.getLocation(), Sound.UI_BUTTON_CLICK, 1, 1);
				effects1.remove(player.getName());
				main.add(player.getName());
				bGui.newBannerGui(player, GuiType.COLOR, this.getPreview(e.getInventory().getItem(25).getItemMeta(), e.getInventory().getItem(25)), 0);
				return;
			}
			if (s == 5) {
				/*
				 * previous effect page (disabled)
				 * 
				 */
			}
			if (s == 6) {
				e.setCancelled(true);
				player.playSound(player.getLocation(), Sound.UI_BUTTON_CLICK, 1, 1);
				effects1.remove(player.getName());
				effects2.add(player.getName());
				bGui.newBannerGui(player, GuiType.EFFECTS2, this.getPreview(e.getInventory().getItem(25).getItemMeta(), e.getInventory().getItem(25)), 1);
				return;
			}
			if (s == 8) {
				e.setCancelled(true);
				e.getWhoClicked().closeInventory();
				player.playSound(player.getLocation(), Sound.UI_BUTTON_CLICK, 1, 1);
				effects1.remove(player.getName());
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
		
		/*
		 * 
		 * 
		 * Effects page 2
		 * 
		 * 
		 */
		if (effects2.contains(player.getName())) {
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
				/*
				 * Reset the base color. 
				 * 
				 */
			}
			if (s == 5) {
				e.setCancelled(true);
				player.playSound(player.getLocation(), Sound.UI_BUTTON_CLICK, 1, 1);
				effects2.remove(player.getName());
				effects1.add(player.getName());
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
				effects2.remove(player.getName());
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
					s == 36 || s == 37 || s == 38 || 
					s == 39 || s == 40 || s == 41 || 
					s == 45 || s == 46 || s == 47 || 
					s == 48 || s == 49 || s == 50) {
				e.setCancelled(true);
				e.getInventory().setItem(25, this.getPreview(e.getCurrentItem().getItemMeta(), e.getCurrentItem()));
				player.playSound(player.getLocation(), Sound.UI_BUTTON_CLICK, 1, 1);
				return;
			}
		}
		
		/*
		 * 
		 * 
		 * Change effects color
		 * 
		 * 
		 */
		if (effectcolor.contains(player.getName())) {
			
		}
		
		/*
		 * 
		 * 
		 * Saves gui
		 * 
		 * 
		 */
		if (saves.contains(player.getName())) {
			
		}
		return;
	}
}

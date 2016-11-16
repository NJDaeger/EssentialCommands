package com.njdaeger.essentials.bannermanager.utils;

import net.md_5.bungee.api.ChatColor;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.njdaeger.essentials.bannermanager.Banner;
import com.njdaeger.essentials.bannermanager.GuiType;

public class BannerGUI extends Banner{
	
	/**
	 * @param player Player to create the inventory for
	 * @param type Type of GUI to make. 
	 */
	public void newBannerGui(Player player, GuiType type) {
		
		
		if (type == GuiType.COLOR) {
			Inventory i = Bukkit.createInventory(player, 54, "Banner Creator - Color");
			i.setItem(8, this.item(ChatColor.GRAY + "Leave Editor", Material.BARRIER, (short)0));
			i.setItem(5, this.item(ChatColor.GRAY + "Previous Layer", Material.ENDER_PEARL, (short) 0 ));
			i.setItem(6, this.item(ChatColor.GRAY + "Next Layer", Material.EYE_OF_ENDER, (short) 0 ));
			i.setItem(45, this.item(ChatColor.GRAY + "Reset Layer", Material.CAULDRON_ITEM, (short) 0 ));
			i.setItem(46, this.item(ChatColor.GRAY + "Bump Layer Down", Material.ENDER_PEARL, (short) 0 ));
			i.setItem(47, this.item(ChatColor.GRAY + "Bump Layer Up", Material.EYE_OF_ENDER, (short) 0 ));
			i.setItem(48, this.item(ChatColor.GRAY + "Effects", Material.NETHER_STAR, (short) 0 ));
			i.setItem(49, this.item(ChatColor.GRAY + "Base Color", Material.INK_SACK, (short) 1 ));
			i.setItem(50, this.item(ChatColor.GRAY + "New Layer", Material.PAPER, (short) 0 ));
			
			i.setItem(4, this.item(" ", Material.BEDROCK, (short) 0 ));
			i.setItem(7, this.item(" ", Material.BEDROCK, (short) 0 ));
			i.setItem(13, this.item(" ", Material.BEDROCK, (short) 0 ));
			i.setItem(14, this.item(" ", Material.BEDROCK, (short) 0 ));
			i.setItem(15, this.item(" ", Material.BEDROCK, (short) 0 ));
			i.setItem(16, this.item(" ", Material.BEDROCK, (short) 0 ));
			i.setItem(17, this.item(" ", Material.BEDROCK, (short) 0 ));
			i.setItem(22, this.item(" ", Material.BEDROCK, (short) 0 ));
			i.setItem(26, this.item(" ", Material.BEDROCK, (short) 0 ));
			i.setItem(31, this.item(" ", Material.BEDROCK, (short) 0 ));
			i.setItem(32, this.item(" ", Material.BEDROCK, (short) 0 ));
			i.setItem(33, this.item(" ", Material.BEDROCK, (short) 0 ));
			i.setItem(34, this.item(" ", Material.BEDROCK, (short) 0 ));
			i.setItem(35, this.item(" ", Material.BEDROCK, (short) 0 ));
			i.setItem(36, this.item(" ", Material.BEDROCK, (short) 0 ));
			i.setItem(37, this.item(" ", Material.BEDROCK, (short) 0 ));
			i.setItem(38, this.item(" ", Material.BEDROCK, (short) 0 ));
			i.setItem(39, this.item(" ", Material.BEDROCK, (short) 0 ));
			i.setItem(40, this.item(" ", Material.BEDROCK, (short) 0 ));
			i.setItem(41, this.item(" ", Material.BEDROCK, (short) 0 ));
			i.setItem(42, this.item(" ", Material.BEDROCK, (short) 0 ));
			i.setItem(44, this.item(" ", Material.BEDROCK, (short) 0 ));
			i.setItem(51, this.item(" ", Material.BEDROCK, (short) 0 ));
			i.setItem(52, this.item(" ", Material.BEDROCK, (short) 0 ));
			i.setItem(53, this.item(" ", Material.BEDROCK, (short) 0 ));
			
			i.setItem(0, this.item(ChatColor.GRAY + "" + ChatColor.BOLD + "White", Material.BANNER, (short) 15 ));
			i.setItem(1, this.item(ChatColor.GRAY + "" + ChatColor.BOLD + "Orange", Material.BANNER, (short) 14 ));
			i.setItem(2, this.item(ChatColor.GRAY + "" + ChatColor.BOLD + "Magenta", Material.BANNER, (short) 13 ));
			i.setItem(3, this.item(ChatColor.GRAY + "" + ChatColor.BOLD + "Light Blue", Material.BANNER, (short) 12 ));
			i.setItem(9, this.item(ChatColor.GRAY + "" + ChatColor.BOLD + "Yellow", Material.BANNER, (short) 11 ));
			i.setItem(10, this.item(ChatColor.GRAY + "" + ChatColor.BOLD + "Lime", Material.BANNER, (short) 10 ));
			i.setItem(11, this.item(ChatColor.GRAY + "" + ChatColor.BOLD + "Pink", Material.BANNER, (short) 9 ));
			i.setItem(12, this.item(ChatColor.GRAY + "" + ChatColor.BOLD + "Gray", Material.BANNER, (short) 8 ));
			i.setItem(18, this.item(ChatColor.GRAY + "" + ChatColor.BOLD + "Light Gray", Material.BANNER, (short) 7 ));
			i.setItem(19, this.item(ChatColor.GRAY + "" + ChatColor.BOLD + "Cyan", Material.BANNER, (short) 6 ));
			i.setItem(20, this.item(ChatColor.GRAY + "" + ChatColor.BOLD + "Purple", Material.BANNER, (short) 5 ));
			i.setItem(21, this.item(ChatColor.GRAY + "" + ChatColor.BOLD + "Blue", Material.BANNER, (short) 4 ));
			i.setItem(27, this.item(ChatColor.GRAY + "" + ChatColor.BOLD + "Brown", Material.BANNER, (short) 3 ));
			i.setItem(28, this.item(ChatColor.GRAY + "" + ChatColor.BOLD + "Green", Material.BANNER, (short) 2 ));
			i.setItem(29, this.item(ChatColor.GRAY + "" + ChatColor.BOLD + "Red", Material.BANNER, (short) 1 ));
			i.setItem(30, this.item(ChatColor.GRAY + "" + ChatColor.BOLD + "Black", Material.BANNER, (short) 0 ));
			
			i.setItem(23, this.item(ChatColor.GRAY + "Saved Banners", Material.ENCHANTED_BOOK, (short) 0 ));
			i.setItem(24, this.item(ChatColor.GRAY + "Load from inventory", Material.BOOK, (short) 0 ));
			i.setItem(25, this.item(ChatColor.GRAY + "Save Banner", Material.BOOK_AND_QUILL, (short) 0 ));
			player.openInventory(i);
			return;
		}
		if (type == GuiType.EFFECTS) {
			Inventory i = Bukkit.createInventory(player, 54, "Banner Creator - Effects");
			i.setItem(0, this.item(ChatColor.GRAY + "Reset Layer", Material.CAULDRON_ITEM , (short) 0 ));
			player.openInventory(i);
			return;
		}
		if (type == GuiType.EFFECTS2) {
			
		}
		if (type == GuiType.EFFECT_COLOR) {
			
		}
		if (type == GuiType.SAVES) {
			
		} else
			try {
				throw new UnknownGUIType();
			} catch (UnknownGUIType e) {
				e.printStackTrace();
			}
	}
	/*
	 * make it so when they select the color/pattern or whatever, the banner gets a red border around it.
	 * Once they select it there will be color options on the bottom of the gui that allow them to change the color 
	 * of whatever thejy just did to the banner. 
	 * Have a button on the bottom that says next layer or layer (layer number) 
	 * have a square on the bottom that contains the final product or an overview of waht has been made so far. 
	 */
	private ItemStack item(String customName, Material material, short data) {
		ItemStack stack = new ItemStack(material, 1, data);
		ItemMeta meta = stack.getItemMeta();
		meta.setDisplayName(customName);
		stack.setItemMeta(meta);
		return stack;
	}
}

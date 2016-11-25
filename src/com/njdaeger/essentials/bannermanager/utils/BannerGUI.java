package com.njdaeger.essentials.bannermanager.utils;

import net.md_5.bungee.api.ChatColor;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.banner.PatternType;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import com.njdaeger.essentials.bannermanager.Banner;
import com.njdaeger.essentials.bannermanager.GuiType;

/**
 * Main class to generate the banner. Contains little banner logic. 
 * @author Noah
 *
 */
public class BannerGUI extends Banner{
	

	/**
	 * Updates and creates a new banner GUI for a player.
	 * @param player Player to give the gui too.
	 * @param type Type of GUI to make 
	 * @param stack The stack to get the banner from for going to the next page. 
	 * @param layer What layer the banner is on.
	 */
	public void newBannerGui(Player player, GuiType type, ItemStack stack, int layer) {
		Inventory i = Bukkit.createInventory(player, 54, "Banner Creator");

		if (type == GuiType.COLOR) {
			i.setItem(8, this.item(ChatColor.GRAY + "Leave Editor", Material.BARRIER, (short)0));
			i.setItem(5, this.item(ChatColor.GRAY + "Previous Layer", Material.STRUCTURE_VOID, (short) 0 ));
			i.setItem(6, this.item(ChatColor.GRAY + "Next Layer", Material.STRUCTURE_VOID, (short) 0 ));
			i.setItem(45, this.item(ChatColor.GRAY + "Reset Layer", Material.CAULDRON_ITEM, (short) 0 ));
			i.setItem(46, this.item(ChatColor.GRAY + "Bump Layer Down", Material.ENDER_PEARL, (short) 0 ));
			i.setItem(47, this.item(ChatColor.GRAY + "Bump Layer Up", Material.EYE_OF_ENDER, (short) 0 ));
			i.setItem(48, this.item(ChatColor.GRAY + "Effects", Material.NETHER_STAR, (short) 0 ));
			i.setItem(49, this.item(ChatColor.GRAY + "Base Color", Material.PAINTING, (short) 0 ));
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
			if (stack != null) {
				i.setItem(43, stack);
			}
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
		}
		if (type == GuiType.EFFECTS) {
			i.setItem(0, this.item(ChatColor.GRAY + "Reset Layer", Material.CAULDRON_ITEM, (short) 0 ));
			i.setItem(1, this.item(ChatColor.GRAY + "Effect Color", Material.INK_SACK, (short) 1 ));
			i.setItem(2, this.item(ChatColor.GRAY + "Save Banner", Material.BOOK_AND_QUILL, (short) 0 ));
			i.setItem(3, this.item(ChatColor.GRAY + "New Layer", Material.PAPER, (short) 0 ));
			i.setItem(4, this.item(ChatColor.GRAY + "Base Color", Material.PAINTING, (short) 0 ));
			i.setItem(5, this.item(ChatColor.GRAY + "Previous Effect Page", Material.STRUCTURE_VOID, (short) 0 ));
			i.setItem(6, this.item(ChatColor.GRAY + "Next Effect Page", Material.EYE_OF_ENDER, (short) 0 ));
			i.setItem(8, this.item(ChatColor.GRAY + "Leave Editor", Material.BARRIER, (short) 0 ));
			i.setItem(52, this.item(ChatColor.GRAY + "Bump Layer Down", Material.ENDER_PEARL, (short) 0 ));
			i.setItem(53, this.item(ChatColor.GRAY + "Bump Layer Up", Material.EYE_OF_ENDER, (short) 0 ));
			
			if (layer == 1) {
				i.setItem(9, this.item(ChatColor.GRAY + "Layer 1", Material.STAINED_GLASS_PANE, (short) 13 ));
				i.setItem(10, this.item(ChatColor.GRAY + "Layer 2", Material.STAINED_GLASS_PANE, (short) 14 ));
				i.setItem(11, this.item(ChatColor.GRAY + "Layer 3", Material.STAINED_GLASS_PANE, (short) 14 ));
				i.setItem(12, this.item(ChatColor.GRAY + "Layer 4", Material.STAINED_GLASS_PANE, (short) 14 ));
				i.setItem(13, this.item(ChatColor.GRAY + "Layer 5", Material.STAINED_GLASS_PANE, (short) 14 ));
				i.setItem(14, this.item(ChatColor.GRAY + "Layer 6", Material.STAINED_GLASS_PANE, (short) 14 ));
			}
			if (layer == 2) {
				i.setItem(9, this.item(ChatColor.GRAY + "Layer 1", Material.STAINED_GLASS_PANE, (short) 14 ));
				i.setItem(10, this.item(ChatColor.GRAY + "Layer 2", Material.STAINED_GLASS_PANE, (short) 13 ));
				i.setItem(11, this.item(ChatColor.GRAY + "Layer 3", Material.STAINED_GLASS_PANE, (short) 14 ));
				i.setItem(12, this.item(ChatColor.GRAY + "Layer 4", Material.STAINED_GLASS_PANE, (short) 14 ));
				i.setItem(13, this.item(ChatColor.GRAY + "Layer 5", Material.STAINED_GLASS_PANE, (short) 14 ));
				i.setItem(14, this.item(ChatColor.GRAY + "Layer 6", Material.STAINED_GLASS_PANE, (short) 14 ));
			}
			if (layer == 3) {
				i.setItem(9, this.item(ChatColor.GRAY + "Layer 1", Material.STAINED_GLASS_PANE, (short) 14 ));
				i.setItem(10, this.item(ChatColor.GRAY + "Layer 2", Material.STAINED_GLASS_PANE, (short) 14 ));
				i.setItem(11, this.item(ChatColor.GRAY + "Layer 3", Material.STAINED_GLASS_PANE, (short) 13 ));
				i.setItem(12, this.item(ChatColor.GRAY + "Layer 4", Material.STAINED_GLASS_PANE, (short) 14 ));
				i.setItem(13, this.item(ChatColor.GRAY + "Layer 5", Material.STAINED_GLASS_PANE, (short) 14 ));
				i.setItem(14, this.item(ChatColor.GRAY + "Layer 6", Material.STAINED_GLASS_PANE, (short) 14 ));
			}
			if (layer == 4) {
				i.setItem(9, this.item(ChatColor.GRAY + "Layer 1", Material.STAINED_GLASS_PANE, (short) 14 ));
				i.setItem(10, this.item(ChatColor.GRAY + "Layer 2", Material.STAINED_GLASS_PANE, (short) 14 ));
				i.setItem(11, this.item(ChatColor.GRAY + "Layer 3", Material.STAINED_GLASS_PANE, (short) 14 ));
				i.setItem(12, this.item(ChatColor.GRAY + "Layer 4", Material.STAINED_GLASS_PANE, (short) 13 ));
				i.setItem(13, this.item(ChatColor.GRAY + "Layer 5", Material.STAINED_GLASS_PANE, (short) 14 ));
				i.setItem(14, this.item(ChatColor.GRAY + "Layer 6", Material.STAINED_GLASS_PANE, (short) 14 ));
			}
			if (layer == 5) {
				i.setItem(9, this.item(ChatColor.GRAY + "Layer 1", Material.STAINED_GLASS_PANE, (short) 14 ));
				i.setItem(10, this.item(ChatColor.GRAY + "Layer 2", Material.STAINED_GLASS_PANE, (short) 14 ));
				i.setItem(11, this.item(ChatColor.GRAY + "Layer 3", Material.STAINED_GLASS_PANE, (short) 14 ));
				i.setItem(12, this.item(ChatColor.GRAY + "Layer 4", Material.STAINED_GLASS_PANE, (short) 14 ));
				i.setItem(13, this.item(ChatColor.GRAY + "Layer 5", Material.STAINED_GLASS_PANE, (short) 13 ));
				i.setItem(14, this.item(ChatColor.GRAY + "Layer 6", Material.STAINED_GLASS_PANE, (short) 14 ));
			}
			if (layer == 6) {
				i.setItem(9, this.item(ChatColor.GRAY + "Layer 1", Material.STAINED_GLASS_PANE, (short) 14 ));
				i.setItem(10, this.item(ChatColor.GRAY + "Layer 2", Material.STAINED_GLASS_PANE, (short) 14 ));
				i.setItem(11, this.item(ChatColor.GRAY + "Layer 3", Material.STAINED_GLASS_PANE, (short) 14 ));
				i.setItem(12, this.item(ChatColor.GRAY + "Layer 4", Material.STAINED_GLASS_PANE, (short) 14 ));
				i.setItem(13, this.item(ChatColor.GRAY + "Layer 5", Material.STAINED_GLASS_PANE, (short) 14 ));
				i.setItem(14, this.item(ChatColor.GRAY + "Layer 6", Material.STAINED_GLASS_PANE, (short) 13 ));
			}
			i.setItem(7, this.item(" ", Material.BEDROCK, (short) 0 ));
			i.setItem(15, this.item(" ", Material.BEDROCK, (short) 0 ));
			i.setItem(16, this.item(" ", Material.BEDROCK, (short) 0 ));
			i.setItem(17, this.item(" ", Material.BEDROCK, (short) 0 ));
			i.setItem(24, this.item(" ", Material.BEDROCK, (short) 0 ));
			i.setItem(26, this.item(" ", Material.BEDROCK, (short) 0 ));
			i.setItem(33, this.item(" ", Material.BEDROCK, (short) 0 ));
			i.setItem(34, this.item(" ", Material.BEDROCK, (short) 0 ));
			i.setItem(35, this.item(" ", Material.BEDROCK, (short) 0 ));
			i.setItem(42, this.item(" ", Material.BEDROCK, (short) 0 ));
			i.setItem(43, this.item(" ", Material.BEDROCK, (short) 0 ));
			i.setItem(44, this.item(" ", Material.BEDROCK, (short) 0 ));
			i.setItem(51, this.item(" ", Material.BEDROCK, (short) 0 ));
			
			i.setItem(25, stack);
			i.setItem(18, this.setBanner(ChatColor.GRAY + "", stack, PatternType.BORDER));
			i.setItem(19, this.setBanner(ChatColor.GRAY + "", stack, PatternType.CURLY_BORDER));
			i.setItem(20, this.setBanner(ChatColor.GRAY + "", stack, PatternType.DIAGONAL_LEFT));
			i.setItem(21, this.setBanner(ChatColor.GRAY + "", stack, PatternType.DIAGONAL_LEFT_MIRROR));
			i.setItem(22, this.setBanner(ChatColor.GRAY + "", stack, PatternType.DIAGONAL_RIGHT));
			i.setItem(23, this.setBanner(ChatColor.GRAY + "", stack, PatternType.DIAGONAL_RIGHT_MIRROR));
			i.setItem(27, this.setBanner(ChatColor.GRAY + "", stack, PatternType.GRADIENT));
			i.setItem(28, this.setBanner(ChatColor.GRAY + "", stack, PatternType.GRADIENT_UP));
			i.setItem(29, this.setBanner(ChatColor.GRAY + "", stack, PatternType.HALF_HORIZONTAL));
			i.setItem(30, this.setBanner(ChatColor.GRAY + "", stack, PatternType.HALF_HORIZONTAL_MIRROR));
			i.setItem(31, this.setBanner(ChatColor.GRAY + "", stack, PatternType.HALF_VERTICAL));
			i.setItem(32, this.setBanner(ChatColor.GRAY + "", stack, PatternType.HALF_VERTICAL_MIRROR));
			i.setItem(36, this.setBanner(ChatColor.GRAY + "", stack, PatternType.TRIANGLE_BOTTOM));
			i.setItem(37, this.setBanner(ChatColor.GRAY + "", stack, PatternType.TRIANGLE_TOP));
			i.setItem(38, this.setBanner(ChatColor.GRAY + "", stack, PatternType.TRIANGLES_BOTTOM));
			i.setItem(39, this.setBanner(ChatColor.GRAY + "", stack, PatternType.TRIANGLES_TOP));
			i.setItem(40, this.setBanner(ChatColor.GRAY + "", stack, PatternType.STRIPE_BOTTOM));
			i.setItem(41, this.setBanner(ChatColor.GRAY + "", stack, PatternType.STRIPE_TOP));
			i.setItem(45, this.setBanner(ChatColor.GRAY + "", stack, PatternType.STRIPE_LEFT));
			i.setItem(46, this.setBanner(ChatColor.GRAY + "", stack, PatternType.STRIPE_RIGHT));
			i.setItem(47, this.setBanner(ChatColor.GRAY + "", stack, PatternType.SQUARE_BOTTOM_LEFT));
			i.setItem(48, this.setBanner(ChatColor.GRAY + "", stack, PatternType.SQUARE_BOTTOM_RIGHT));
			i.setItem(49, this.setBanner(ChatColor.GRAY + "", stack, PatternType.SQUARE_TOP_LEFT));
			i.setItem(50, this.setBanner(ChatColor.GRAY + "", stack, PatternType.SQUARE_TOP_RIGHT));
			
		}
		if (type == GuiType.EFFECTS2) {
			i.setItem(0, this.item(ChatColor.GRAY + "Reset Layer", Material.CAULDRON_ITEM , (short) 0 ));
			i.setItem(1, this.item(ChatColor.GRAY + "Effect Color", Material.INK_SACK, (short) 1 ));
			i.setItem(2, this.item(ChatColor.GRAY + "Save Banner", Material.BOOK_AND_QUILL, (short) 0 ));
			i.setItem(3, this.item(ChatColor.GRAY + "New Layer", Material.PAPER, (short) 0 ));
			i.setItem(4, this.item(ChatColor.GRAY + "Base Color", Material.PAINTING, (short) 0 ));
			i.setItem(5, this.item(ChatColor.GRAY + "Previous Effect Page", Material.ENDER_PEARL, (short) 0 ));
			i.setItem(6, this.item(ChatColor.GRAY + "Next Effect Page", Material.STRUCTURE_VOID, (short) 0 ));
			i.setItem(8, this.item(ChatColor.GRAY + "Leave Editor", Material.BARRIER, (short) 0 ));
			i.setItem(52, this.item(ChatColor.GRAY + "Bump Layer Down", Material.ENDER_PEARL, (short) 0 ));
			i.setItem(53, this.item(ChatColor.GRAY + "Bump Layer Up", Material.EYE_OF_ENDER, (short) 0 ));
			
			if (layer == 1) {
				i.setItem(9, this.item(ChatColor.GRAY + "Layer 1", Material.STAINED_GLASS_PANE, (short) 13 ));
				i.setItem(10, this.item(ChatColor.GRAY + "Layer 2", Material.STAINED_GLASS_PANE, (short) 14 ));
				i.setItem(11, this.item(ChatColor.GRAY + "Layer 3", Material.STAINED_GLASS_PANE, (short) 14 ));
				i.setItem(12, this.item(ChatColor.GRAY + "Layer 4", Material.STAINED_GLASS_PANE, (short) 14 ));
				i.setItem(13, this.item(ChatColor.GRAY + "Layer 5", Material.STAINED_GLASS_PANE, (short) 14 ));
				i.setItem(14, this.item(ChatColor.GRAY + "Layer 6", Material.STAINED_GLASS_PANE, (short) 14 ));
			}
			if (layer == 2) {
				i.setItem(9, this.item(ChatColor.GRAY + "Layer 1", Material.STAINED_GLASS_PANE, (short) 14 ));
				i.setItem(10, this.item(ChatColor.GRAY + "Layer 2", Material.STAINED_GLASS_PANE, (short) 13 ));
				i.setItem(11, this.item(ChatColor.GRAY + "Layer 3", Material.STAINED_GLASS_PANE, (short) 14 ));
				i.setItem(12, this.item(ChatColor.GRAY + "Layer 4", Material.STAINED_GLASS_PANE, (short) 14 ));
				i.setItem(13, this.item(ChatColor.GRAY + "Layer 5", Material.STAINED_GLASS_PANE, (short) 14 ));
				i.setItem(14, this.item(ChatColor.GRAY + "Layer 6", Material.STAINED_GLASS_PANE, (short) 14 ));
			}
			if (layer == 3) {
				i.setItem(9, this.item(ChatColor.GRAY + "Layer 1", Material.STAINED_GLASS_PANE, (short) 14 ));
				i.setItem(10, this.item(ChatColor.GRAY + "Layer 2", Material.STAINED_GLASS_PANE, (short) 14 ));
				i.setItem(11, this.item(ChatColor.GRAY + "Layer 3", Material.STAINED_GLASS_PANE, (short) 13 ));
				i.setItem(12, this.item(ChatColor.GRAY + "Layer 4", Material.STAINED_GLASS_PANE, (short) 14 ));
				i.setItem(13, this.item(ChatColor.GRAY + "Layer 5", Material.STAINED_GLASS_PANE, (short) 14 ));
				i.setItem(14, this.item(ChatColor.GRAY + "Layer 6", Material.STAINED_GLASS_PANE, (short) 14 ));
			}
			if (layer == 4) {
				i.setItem(9, this.item(ChatColor.GRAY + "Layer 1", Material.STAINED_GLASS_PANE, (short) 14 ));
				i.setItem(10, this.item(ChatColor.GRAY + "Layer 2", Material.STAINED_GLASS_PANE, (short) 14 ));
				i.setItem(11, this.item(ChatColor.GRAY + "Layer 3", Material.STAINED_GLASS_PANE, (short) 14 ));
				i.setItem(12, this.item(ChatColor.GRAY + "Layer 4", Material.STAINED_GLASS_PANE, (short) 13 ));
				i.setItem(13, this.item(ChatColor.GRAY + "Layer 5", Material.STAINED_GLASS_PANE, (short) 14 ));
				i.setItem(14, this.item(ChatColor.GRAY + "Layer 6", Material.STAINED_GLASS_PANE, (short) 14 ));
			}
			if (layer == 5) {
				i.setItem(9, this.item(ChatColor.GRAY + "Layer 1", Material.STAINED_GLASS_PANE, (short) 14 ));
				i.setItem(10, this.item(ChatColor.GRAY + "Layer 2", Material.STAINED_GLASS_PANE, (short) 14 ));
				i.setItem(11, this.item(ChatColor.GRAY + "Layer 3", Material.STAINED_GLASS_PANE, (short) 14 ));
				i.setItem(12, this.item(ChatColor.GRAY + "Layer 4", Material.STAINED_GLASS_PANE, (short) 14 ));
				i.setItem(13, this.item(ChatColor.GRAY + "Layer 5", Material.STAINED_GLASS_PANE, (short) 13 ));
				i.setItem(14, this.item(ChatColor.GRAY + "Layer 6", Material.STAINED_GLASS_PANE, (short) 14 ));
			}
			if (layer == 6) {
				i.setItem(9, this.item(ChatColor.GRAY + "Layer 1", Material.STAINED_GLASS_PANE, (short) 14 ));
				i.setItem(10, this.item(ChatColor.GRAY + "Layer 2", Material.STAINED_GLASS_PANE, (short) 14 ));
				i.setItem(11, this.item(ChatColor.GRAY + "Layer 3", Material.STAINED_GLASS_PANE, (short) 14 ));
				i.setItem(12, this.item(ChatColor.GRAY + "Layer 4", Material.STAINED_GLASS_PANE, (short) 14 ));
				i.setItem(13, this.item(ChatColor.GRAY + "Layer 5", Material.STAINED_GLASS_PANE, (short) 14 ));
				i.setItem(14, this.item(ChatColor.GRAY + "Layer 6", Material.STAINED_GLASS_PANE, (short) 13 ));
			}
			i.setItem(7, this.item(" ", Material.BEDROCK, (short) 0 ));
			i.setItem(15, this.item(" ", Material.BEDROCK, (short) 0 ));
			i.setItem(16, this.item(" ", Material.BEDROCK, (short) 0 ));
			i.setItem(17, this.item(" ", Material.BEDROCK, (short) 0 ));
			i.setItem(24, this.item(" ", Material.BEDROCK, (short) 0 ));
			i.setItem(26, this.item(" ", Material.BEDROCK, (short) 0 ));
			i.setItem(33, this.item(" ", Material.BEDROCK, (short) 0 ));
			i.setItem(34, this.item(" ", Material.BEDROCK, (short) 0 ));
			i.setItem(35, this.item(" ", Material.BEDROCK, (short) 0 ));
			i.setItem(42, this.item(" ", Material.BEDROCK, (short) 0 ));
			i.setItem(43, this.item(" ", Material.BEDROCK, (short) 0 ));
			i.setItem(44, this.item(" ", Material.BEDROCK, (short) 0 ));
			i.setItem(51, this.item(" ", Material.BEDROCK, (short) 0 ));
			i.setItem(36, this.item(" ", Material.BEDROCK, (short) 0 ));
			i.setItem(37, this.item(" ", Material.BEDROCK, (short) 0 ));
			i.setItem(41, this.item(" ", Material.BEDROCK, (short) 0 ));
			i.setItem(40, this.item(" ", Material.BEDROCK, (short) 0 ));
			i.setItem(45, this.item(" ", Material.BEDROCK, (short) 0 ));
			i.setItem(46, this.item(" ", Material.BEDROCK, (short) 0 ));
			i.setItem(47, this.item(" ", Material.BEDROCK, (short) 0 ));
			i.setItem(48, this.item(" ", Material.BEDROCK, (short) 0 ));
			i.setItem(49, this.item(" ", Material.BEDROCK, (short) 0 ));
			i.setItem(50, this.item(" ", Material.BEDROCK, (short) 0 ));
			
			i.setItem(25, stack);
			i.setItem(18, this.setBanner(ChatColor.GRAY + "", stack, PatternType.STRIPE_DOWNLEFT));
			i.setItem(19, this.setBanner(ChatColor.GRAY + "", stack, PatternType.STRIPE_DOWNRIGHT));
			i.setItem(20, this.setBanner(ChatColor.GRAY + "", stack, PatternType.STRIPE_MIDDLE));
			i.setItem(21, this.setBanner(ChatColor.GRAY + "", stack, PatternType.STRIPE_CENTER));
			i.setItem(22, this.setBanner(ChatColor.GRAY + "", stack, PatternType.STRIPE_SMALL));
			i.setItem(23, this.setBanner(ChatColor.GRAY + "", stack, PatternType.STRAIGHT_CROSS));
			i.setItem(27, this.setBanner(ChatColor.GRAY + "", stack, PatternType.MOJANG));
			i.setItem(28, this.setBanner(ChatColor.GRAY + "", stack, PatternType.SKULL));
			i.setItem(29, this.setBanner(ChatColor.GRAY + "", stack, PatternType.CIRCLE_MIDDLE));
			i.setItem(30, this.setBanner(ChatColor.GRAY + "", stack, PatternType.RHOMBUS_MIDDLE));
			i.setItem(31, this.setBanner(ChatColor.GRAY + "", stack, PatternType.CREEPER));
			i.setItem(32, this.setBanner(ChatColor.GRAY + "", stack, PatternType.CROSS));
			i.setItem(38, this.setBanner(ChatColor.GRAY + "", stack, PatternType.BRICKS));
			i.setItem(39, this.setBanner(ChatColor.GRAY + "", stack, PatternType.FLOWER));
		}
		if (type == GuiType.EFFECT_COLOR) {
			
		}
		if (type == GuiType.SAVES) {
			
		}
		if (type == GuiType.BASECOLOR) {
			
		}
		player.openInventory(i);
	}
	/*
	 * make it so when they select the color/pattern or whatever, the banner gets a red border around it.
	 * Once they select it there will be color options on the bottom of the gui that allow them to change the color 
	 * of whatever thejy just did to the banner. 
	 * Have a button on the bottom that says next layer or layer (layer number) 
	 * have a square on the bottom that contains the final product or an overview of waht has been made so far. 
	 */
}

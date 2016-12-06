package com.njdaeger.java.descrete.editors.bannermanager.utils;

import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.block.banner.PatternType;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public interface IBannerHandler {
	
	
	
	/**
	 * @param sender Bannersaver
	 * @param bannername Name of saved banner
	 */
	void saveBanner(Player sender, String bannername);
	
	/**
	 * @param sender Bannerdeleter
	 * @param bannername Name of banner being deleted
	 */
	void deleteBanner(Player sender, String bannername);
	
	/**
	 * Banner must exist to be able to get.
	 * @param bannername Name of banner to get.
	 * @return
	 */
	YamlConfiguration getSavedBanner(String bannername);
	
	/**
	 * Lists all banners saved.
	 * @return
	 */
	String listBanners();
	
	/**
	 * Used to get the banner to move from one slot to another slot.
	 * @param m Get the item meta of a stack.
	 * @param s Get the stack.
	 * @return
	 * Do not close the inventory for this to work properly.
	 */
	ItemStack getPreview(ItemMeta m, ItemStack s);
	
	/**
	 * @param banner The target banner (in itemstack form) Do not have it as a banner object.
	 * @param layer What layer you are suppose to clear from the banner. (0 - 5)
	 * @return
	 */
	ItemStack clearLayer(ItemStack banner, int layer);
	
	/**
	 * Returns dye color based on value. Value must be 0-15
	 * @param d Durability. Sets the color of the banner.
	 * @return
	 */
	DyeColor getDyeColor(short d);
	
	/**
	 * @param customName Custom name to set item as.
	 * @param material The material the item will be.
	 * @param data Material metadata.
	 * @return
	 */
	ItemStack item(String customName, Material material, short data);
	
	/**
	 * @param customname What to name the banner.
	 * @param stack This should be a 
	 * @param type Pattern to put on the banner.
	 * @return
	 */
	ItemStack setBanner(String customname, ItemStack stack, PatternType type);
}

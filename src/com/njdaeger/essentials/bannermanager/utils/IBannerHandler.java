package com.njdaeger.essentials.bannermanager.utils;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import com.njdaeger.essentials.bannermanager.GuiType;

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
	 * @param player Player to remove from edit mode.
	 */
	void removeEditMode(String player, GuiType type);
	
	/**
	 * @param player Player to add to edit mode. 
	 */
	void addEditMode(String player, GuiType type);
	
	/**
	 * @param player Player to check if in edit mode.
	 * @return
	 */
	boolean isEditMode(String player);
	
}

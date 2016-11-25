package com.configapi.configuration.interfaces;

import java.io.IOException;

import org.bukkit.configuration.file.YamlConfiguration;

public interface IConfiguration {
	
	/**
	 * Generates a new configuration file if it exists. 
	 * @throws IOException 
	 */
	void newConfig() throws IOException;
	
	/**
	 * Gets the configuration.
	 * @return Returns configuration.
	 */
	YamlConfiguration getConfig();
	
	/**
	 * Checks if GroupManager is enabled. 
	 * @return Returns true or false if enabled or not.
	 */
	boolean isGroupmanagerEnabled();
	
	/**
	 * Enable or disable GroupManager.
	 * @param enable True enables it, false disables it. 
	 */
	void setGroupmanagerEnabled(boolean enable);
	
	/**
	 * Checks if BannerManager is enabled.
	 * @return Returns true or false if enabled or not.
	 */
	boolean isBannermanagerEnabled();
	
	/**
	 * Enable or disable BannerManager.
	 * @param enable True enables it, false disables it. 
	 */
	void setBannermanagerEnabled(boolean enable);
	
	/**
	 * Checks if ChatColor is enabled.
	 * @return Returns true or false if enabled or not.
	 */
	boolean isChatcolorEnabled();
	
	/**
	 * Enable or disable ChatColor.
	 * @param enable True enables it, false disables it. 
	 */
	void setChatcolorEnabled(boolean enable);
	
	/**
	 * Checks if Homes is enabled.
	 * @return Returns true or false if enabled or not. 
	 */
	boolean isHomesEnabled();
	
	/**
	 * Enable or disable Homes.
	 * @param enable True enables it, false disables it.
	 */
	void setHomesrEnabled(boolean enable);
	
	/**
	 * Checks if Warps is enabled.
	 * @return Returns true or false if enabled or not.
	 */
	boolean isWarpsEnabled();
	
	/**
	 * Enable or disable Warps.
	 * @param enable True enables it, false disables it.
	 */
	void setWarpsEnabled(boolean enable);
}

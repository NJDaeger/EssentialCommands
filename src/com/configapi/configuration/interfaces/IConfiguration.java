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
	
	/**
	 * Checks if the server has a warp limit.
	 * @return if server-warps are limited.
	 * 
	 * <p>Configuration value: warps.warp-limit
	 */
	boolean isWarpLimit();
	
	/**
	 * Enable or disable the server-wide warp limit.
	 * @param enable True enables it, false disables it.
	 * 
	 * <p>Configuration value: warps.warp-limit
	 */
	void setWarpLimitEnabled(boolean enable);
	
	/**Get the server-wide warp limit.
	 * @return the server-wide warp limit.
	 * 
	 * <p>Configuration value: warps.warp-limit
	 */
	String getWarpLimit(); 
	
	/**
	 * Sets the server-wide warp limit.
	 * @param value The value to set the server-wide warp limit.
	 * NOTE: Only "~" (for infinite) and integers are accepted for this value
	 * 
	 * <p>Configuration value: warps.max-for-server-value
	 */
	void setWarpLimit(String value);
	
	/**
	 * Check if worlds are limited to warps or not. 
	 * @return True if warp limit is set to per world and if it isnt enabled its false. 
	 * 
	 * <p>Configuration value: warps.max-per-world
	 */
	boolean isWarpLimitWorld();
	
	 /**
	 * Enable or disable world warp limits.
	 * @param enable True enables it, false disables it.
	 * 
	 * <p>Configuration value: warps.max-per-world
	 */
	void setWorldWarpLimitEnabled(boolean enable);
	
	/**
	 * Get the per world warp limit.
	 * @return Returns the per world warp limit value
	 * 
	 * <p>Configuration value: warps.max-per-world-value
	 */
	String getWorldWarpLimit();
	
	/**
	 * Sets the per-world warp limit.
	 * @param value The value to set the per-world warp limit.
	 * NOTE: Only "~" (for infinite) and integers are accepted for this value
	 * 
	 * <p>Configuration value: warps.max-per-world-value
	 */
	void setWorldWarpLimit(String value);
	
	
	
	
}

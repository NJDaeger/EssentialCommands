package com.njdaeger.java.configuration.interfaces;

import java.io.IOException;
import java.util.List;

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
	 * <p>NOTE: Only "~" (for infinite) and integers are accepted for this value
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
	 * <p>NOTE: Only "~" (for infinite) and integers are accepted for this value
	 * 
	 * <p>Configuration value: warps.max-per-world-value
	 */
	void setWorldWarpLimit(String value);
	
	/**
	 * Get the operator name color. If "none" it will not be read. 
	 * @return Returns the color of operator names.
	 * 
	 * <p>Configuration value: main.operators.opnamecolor
	 */
	String getOpNameColor();
	
	/**
	 * Set the operator name color.
	 * @param value The value to set this option.
	 * <p>NOTE: Only "none" (for no color) and color codes.
	 * 
	 * <p>Configuration value: main.operators.opnamecolor
	 */
	void setOpNameColor(String value);
	
	/**
	 * Get the prefix that goes before a nickname.
	 * @return Returns the prefix before someones name if they have a nickname.
	 * 
	 * <p>Configuration value: main.nickname.nickprefix
	 */
	String getNicknamePrefix();
	
	/**
	 * Set the prefix before a nickname.
	 * @param value The prefix before the name
	 * <p>NOTE: This can be anything but "none". none means it wont have one.
	 * 
	 * <p>Configuration value: main.nickname.nickprefix
	 */
	void setNicknamePrefix(String value);
	
	/**
	 * Get the max nickname length
	 * @return Returns the max length a nickname can be.
	 * 
	 * <p>Configuration value: main.nickname.max-nickname-length
	 */
	int getMaxNicknameLength();
	
	/**
	 * Set the max nickname length.
	 * @param value the new max nickname length
	 * 
	 * <p>Configuration value: main.nickname.max-nickname-length
	 */
	void setMaxNicknameLength(int value);
	
	/**
	 * Get all blacklisted commands.
	 * @return List of all the blacklisted commands
	 * 
	 * <p>Configuration value: main.blacklist.commands.command-list
	 */
	List<String> getBlacklistedCommands();
	
	/**
	 * Add more commands to the command blacklist
	 * @param commands List of commands to add
	 * 
	 * <p>Configuration value: main.blacklist.commands.command-list
	 */
	void addBlacklistedCommands(List<String> commands);
	
	/**
	 * Remove commands from the blacklist
	 * @param commands List of commands to remove.
	 * 
	 * <p>Configuration value: main.blacklist.commands.command-list
	 */
	void removeBlacklistedCommands(List<String> commands);
	
	/**
	 * Check if the console is notified for non-permitted command use.
	 * @return Returns if the console is notified for non-permitted command use.
	 * 
	 * <p>Configuration value: main.blacklist.commands.enable-console-warn
	 */
	boolean isCommandNotifyCsl();
	
	/**
	 * Enable or disable the console warn for non-permitted commands.
	 * @param enable True enables console warning false disables it.
	 * 
	 * <p>Configuration value: main.blacklist.commands.enable-console-warn
	 */
	void setCommandNotifyCsl(boolean enable);
	
	/**
	 * The message sent to the console for a non permitted command.
	 * @return Returns the message sent to the console when a non-permitted command is executed.
	 * 
	 * <p>Configuration value: main.blacklist.commands.console-warn-message
	 */
	String getCommandNotifyCslMessage();
	
	 /**
	  * Sets the console warn message.
	 * @param message the message to sent the console warn to.
	 * 
	 * <p>Configuration value: main.blacklist.commands.console-warn-message
	 */
	void setNotifyCslMessage(String message);
	
	
}

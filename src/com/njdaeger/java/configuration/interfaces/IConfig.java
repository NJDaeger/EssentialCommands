package com.njdaeger.java.configuration.interfaces;

import java.util.List;

import com.njdaeger.java.configuration.ConfigType;

public interface IConfig {

	/**
	 * Gets the configuration type of the plugin.
	 * 
	 * @return The type of configuration.
	 */
	ConfigType getConfigType();

	/**
	 * Set the configuration type plugin.
	 * 
	 * @param type The type wanting to be used.
	 */
	void setConfigType(ConfigType type);

	/**
	 * Checks if NJPerms is enabled.
	 * 
	 * @return True if enabled, false otherwise.
	 */
	boolean isNJPermsEnabled();

	/**
	 * Sets NJPerms enabled or not.
	 * 
	 * @param valueTrue enables false disables.
	 */
	void setNJPermsEnabled(boolean value);

	/**
	 * Checks if Codes is enabled.
	 * 
	 * @return True if enabled, false otherwise.
	 */
	boolean isCodesEnabled();

	/**
	 * Sets Codes enabled or not.
	 * 
	 * @param valueTrue enables false disables.
	 */
	void setCodesEnabled(boolean value);

	/**
	 * Checks if Annotations is enabled.
	 * 
	 * @return True if enabled, false otherwise.
	 */
	boolean isAnnotationsEnabled();

	/**
	 * Sets Annotations enabled or not.
	 * 
	 * @param valueTrue enables false disables.
	 */
	void setAnnotationsEnabled(boolean value);

	/**
	 * Checks if ServerProtect is enabled.
	 * 
	 * @return True if enabled, false otherwise.
	 */
	boolean isServerProtectEnabled();

	/**
	 * Sets ServerProtect enabled or not.
	 * 
	 * @param valueTrue enables false disables.
	 */
	void setServerProtectEnabled(boolean value);

	/**
	 * Checks if LoginClearance is enabled.
	 * 
	 * @return True if enabled, false otherwise.
	 */
	boolean isLoginClearanceEnabled();

	/**
	 * Sets LoginClearance enabled or not.
	 * 
	 * @param valueTrue enables false disables.
	 */
	void setLoginClearanceEnabled(boolean value);

	/**
	 * Set the server warp limit.
	 * 
	 * @param limit The warp limit of the server.
	 * 
	 *            <p>
	 *            NOTE: If the value is less than 0 then their isn't a limit.
	 */
	void setWarpLimit(int limit);

	/**
	 * Gets the server warp limit.
	 * 
	 * @return The max amount of warps allowed on the server.
	 */
	int getWarpLimit();

	/**
	 * Gets the custom Operator name color.
	 * 
	 * @return The color code for operator names.
	 */
	String getOpNameColor();

	/**
	 * Set the operator name color.
	 * 
	 * @param colorCode Color for operator names.
	 * 
	 *            <p>
	 *            NOTE: This must be a color code such as "&4", or it must be
	 *            set to "none" for no effect.
	 */
	void setOpNameColor(String colorCode);

	/**
	 * Sets the prefix for players with a nickname.
	 * 
	 * @param prefix The prefix before a players nickname.
	 */
	void setNickPrefix(String prefix);

	/**
	 * Gets the prefix before a nickname.
	 * 
	 * @return The prefix going before a nickname.
	 */
	String getNickPrefix();

	/**
	 * Sets the max length for a nickname.
	 * 
	 * @param max The max length for a nickname.
	 * 
	 *            <p>
	 *            NOTE: If the value is less than 0 then their isn't a limit.
	 */
	void setMaxNickLength(int max);

	/**
	 * Gets the max length for a nickname.
	 * 
	 * @return The maximum size for a nickname.
	 */
	int getMaxNickLength();

	/**
	 * Get all blacklisted commands.
	 * 
	 * @return List of all the blacklisted commands
	 * 
	 *         <p>
	 *         Configuration value: main.blacklist.commands.command-list
	 */
	List<String> getBlacklistedCommands();

	/**
	 * Add more commands to the command blacklist
	 * 
	 * @param commands List of commands to add
	 * 
	 *            <p>
	 *            Configuration value: main.blacklist.commands.command-list
	 */
	void addBlacklistedCommands(List<String> commands);

	/**
	 * Remove commands from the blacklist
	 * 
	 * @param commands List of commands to remove.
	 * 
	 *            <p>
	 *            Configuration value: main.blacklist.commands.command-list
	 */
	void removeBlacklistedCommands(List<String> commands);

	/**
	 * Check if the console is notified for non-permitted command use.
	 * 
	 * @return Returns if the console is notified for non-permitted command use.
	 * 
	 *         <p>
	 *         Configuration value: main.blacklist.commands.enable-console-warn
	 */
	boolean isConsoleNotified();

	/**
	 * Enable or disable the console warn for non-permitted commands.
	 * 
	 * @param enable True enables console warning false disables it.
	 * 
	 *            <p>
	 *            Configuration value:
	 *            main.blacklist.commands.enable-console-warn
	 */
	void setConsoleNotified(boolean enable);

}

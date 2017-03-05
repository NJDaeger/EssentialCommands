package com.njdaeger.java.wrapper;

import org.bukkit.Location;

import com.njdaeger.java.essentials.enums.Error;
import com.njdaeger.java.essentials.enums.Permission;

public interface IUser extends IUserConf {

	/**
	 * A set of various tasks to do when a user joins the server.
	 */
	IUser loginUpdate();

	/**
	 * A set of various tasks to do when a user quits the server.
	 */
	void logoutUpdate();

	/**
	 * Checks if the user is allowed to fly.
	 * 
	 * @return True if the user can fly, false otherwise.
	 */
	boolean isFlyingMode();

	/**
	 * Allows the user to fly.
	 * 
	 * @param value True allows to fly, false doesn't allow flying.
	 */
	void setFlyingMode(boolean value);

	/**
	 * Checks if the user has the infobar enabled.
	 * 
	 * @return
	 */
	boolean hasInfobar();

	/**
	 * Enables or disables the infobar for the player.
	 * 
	 * @param value True enables it, false disables it.
	 */
	void setInfobar(boolean value);

	/**
	 * Forces the user to run a command.
	 * 
	 * @param command The command to run (with arguments, without slash)
	 */
	void sudo(String command);

	/**
	 * Sends the user an error.
	 * 
	 * @param error The error to send to the user.
	 */
	void sendError(Error error);

	/**
	 * Sends a message to the specified user.
	 * 
	 * @param message The message to send to the user.
	 */
	void sendMessage(String message);

	/**
	 * Checks if a user has a permission derived from EssentialCommands.
	 * 
	 * @param permission The permission you're checking for.
	 * @return True if the user has permission, false otherwise.
	 */
	boolean hasPermission(Permission... permission);

	/**
	 * Checks if a user has a permission.
	 * 
	 * @param permission The permission to check for.
	 * @return True if the user has permission, false otherwise.
	 */
	boolean hasPermission(String permission);

	/**
	 * Teleports a user to a specific location.
	 * 
	 * @param location The new location for the user.
	 */
	void tp(Location location);

	/**
	 * Gets all the current locations of the player.
	 * 
	 * @return The location of the player.
	 */
	Location getLocation();
}

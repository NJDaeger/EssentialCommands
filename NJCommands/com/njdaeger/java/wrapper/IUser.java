package com.njdaeger.java.wrapper;

import org.bukkit.Location;

import com.njdaeger.java.enums.Error;
import com.njdaeger.java.enums.Permission;
import com.njdaeger.java.njcommands.commands.player.TimeUnit;
import com.njdaeger.java.njcommands.commands.player.WeatherUnit;

public interface IUser extends IOfflineUser {

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

	/**
	 * Kicks a user for the default kick reason.
	 */
	void kick();

	/**
	 * Kicks a user for a custom reason.
	 * 
	 * @param reason The reason the user was kicked.
	 */
	void kick(String reason);

	/**
	 * Sets the user time.
	 * 
	 * @param time The time of day to set the user to.
	 */
	void setUserTime(TimeUnit time);

	/**
	 * Sets the user time.
	 * 
	 * @param time The time of day to set the user to.
	 */
	void setUserTime(long time);

	/**
	 * Gets the users time.
	 * 
	 * @return The user time.
	 */
	long getUserTime();

	/**
	 * Gets the TimeUnit closest to the current user time.
	 * 
	 * @return The closest TimeUnit.
	 */
	TimeUnit getUserTimeUnit();

	/**
	 * Sets the user time back to the server time.
	 */
	void resetUserTime();

	/**
	 * Sets the user time
	 * 
	 * @param unit
	 */
	void setUserWeather(WeatherUnit unit);

	/**
	 * Gets the current weather type of the user.
	 * 
	 * @return The current weather for the user.
	 */
	WeatherUnit getUserWeather();

	/**
	 * Resets the user's weather to the server weather.
	 */
	void resetUserWeather();
}

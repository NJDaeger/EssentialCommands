package com.njdaeger.java.configuration.interfaces;

import com.njdaeger.java.wrapper.IUserConf;
import com.njdaeger.java.wrapper.User;

public interface IOfflineHome extends IValues, ISetValues {

	/**
	 * Checks if the home exists.
	 * 
	 * @return Returns true if it exists, false otherwise.
	 */
	boolean exists();

	/**
	 * Removes the home specified
	 */
	void remove();

	/**
	 * List the homes of the specified player.
	 */
	String listHomes();

	/**
	 * Send someone to another home.
	 * 
	 * @param target Player to send.
	 */
	void sendOtherHome(User target);

	/**
	 * Get the user that owns the home.
	 * 
	 * @return The home owner.
	 */
	IUserConf getOwner();

	/**
	 * Get the name of the home.
	 * 
	 * @return The home.
	 */
	String getName();

}

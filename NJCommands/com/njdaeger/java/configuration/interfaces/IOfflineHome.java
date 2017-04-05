package com.njdaeger.java.configuration.interfaces;

import com.njdaeger.java.wrapper.IOfflineUser;
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
	 * Send someone to another home.
	 * 
	 * @param target
	 *            Player to send.
	 */
	void sendOtherHome(User target);

	/**
	 * Get the user that owns the home.
	 * 
	 * @return The home owner.
	 */
	IOfflineUser getOwner();

	/**
	 * Get the name of the home.
	 * 
	 * @return The home.
	 */
	String getName();

}

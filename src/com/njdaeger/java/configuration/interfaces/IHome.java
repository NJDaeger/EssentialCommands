package com.njdaeger.java.configuration.interfaces;

import com.njdaeger.java.wrapper.User;

public interface IHome extends IOfflineHome {

	/**
	 * Checks if the home exists.
	 * 
	 * @return Returns true if exists, false otherwise.
	 */
	@Override
	boolean exists();

	/**
	 * Creates the home specified
	 */
	void create();

	/**
	 * Removes the home specified
	 */
	@Override
	void remove();

	/**
	 * List the homes of the specified player.
	 */
	@Override
	String listHomes();

	/**
	 * Send the specified player to their home.
	 */
	void sendHome();

	/**
	 * Send someone to another home.
	 * 
	 * @param target Player to send.
	 */
	@Override
	void sendOtherHome(User target);
}

package com.njdaeger.java.configuration.interfaces;

import org.bukkit.entity.Player;

public interface IHomeHandler {

	/**
	 * Creates the home specified
	 */
	void create();

	/**
	 * Removes the home specified
	 */
	void remove();

	/**
	 * List the homes of the specified player.
	 */
	String listHomes();

	/**
	 * Send the specified player to their home.
	 */
	void sendHome();

	/**
	 * Send someone to another home.
	 * 
	 * @param target
	 *            Player to send.
	 */
	void sendOtherHome(Player target);
}

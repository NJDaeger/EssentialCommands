package com.njdaeger.java.configuration.interfaces;

import org.bukkit.entity.Player;

public interface IOfflineHome {

	/**
	 * Removes the home specified
	 */
	public void remove();

	/**
	 * List the homes of the specified player.
	 */
	public String listHomes();

	/**
	 * Send someone to another home.
	 * 
	 * @param target
	 *            Player to send.
	 */
	public void sendOtherHome(Player target);

}

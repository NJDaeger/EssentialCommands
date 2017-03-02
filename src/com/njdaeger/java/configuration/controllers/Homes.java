package com.njdaeger.java.configuration.controllers;

import org.bukkit.entity.Player;

import com.njdaeger.java.configuration.data.OfflineHomeData;

public class Homes {

	public static String home;
	public static String offlinetarget;
	public static Player target;

	/**
	 * Gets the home of an online player.
	 * 
	 * @param home Name of home. Note: this can be null because not all
	 *            accessible methods use it.
	 * @param target Target to get home from.
	 * @return
	 */
	/*public static HomeData getHome(String home, Player target) {
		Homes.home = home;
		Homes.target = target;
		return new HomeData();
	}*/

	/**
	 * Gets an offline player's home. Must get target from player database.
	 * 
	 * @param home Name of home. Note: this can be null because not all
	 *            accessible methods use it.
	 * @param target Name of target.
	 * @return
	 */
	public static OfflineHomeData getOfflineHome(String home, String offlinetarget) {
		Homes.home = home;
		Homes.offlinetarget = offlinetarget;
		return new OfflineHomeData();
	}
}

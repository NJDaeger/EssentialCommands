package com.njdaeger.java.configuration.interfaces;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

public interface IHomeHandler {
	
	/**
	 * Gets the home of an online player.
	 * @param home Name of home.
	 * @param target Target to get home from.
	 * @return
	 */
	YamlConfiguration getHome(String home, Player target);
	
	/**
	 * Gets an offline player's home. Must get target from player database.
	 * @param home Name of home.
	 * @param target Name of target.
	 * @return
	 */
	YamlConfiguration getOfflineHome(String home, String target);
	
	/**
	 * Creates a home for an online player.
	 * @param home Name of home.
	 * @param target Target to get home from.
	 */
	void createHome(String home, Player target);
	
	/**
	 * Removes a home from an online player.
	 * @param home Name of home.
	 * @param target Target to get home from.
	 */
	void removeHome(String home, Player target);
	
	/**
	 * Removes a home from an offline player.
	 * @param home Name of home.
	 * @param target Target to get home from.
	 */
	void removeOfflineHome(String home, String target);
	
	/**
	 * List homes of an online player.
	 * @param target Target to get the list from.
	 * @return
	 */
	String listHomes(Player target);
	
	/**
	 * List homes of an offline player.
	 * @param target Target to get list from.
	 * @return
	 */
	String listOfflineHomes(String target);
	
	/**
	 * Send a player to their own home. 
	 * @param home Home to send too. 
	 * @param target Target to send.
	 */
	void sendHome(String home, Player target);
	
	/**
	 * Send an online player to another online player's home.
	 * @param home Home to send too.
	 * @param target Target to get home from.
	 * @param sender Person to send to home.
	 */
	void sendOtherHome(String home, Player target, Player sender);
	
	/**
	 * Send on online player to an offline player's home.
	 * @param home Home to send too.
	 * @param target Target to get home from.
	 * @param sender Player to send.
	 */
	void sendOfflineHome(String home, String target, Player sender);
	
}

package com.njdaeger.java.configuration.controllers;

import org.bukkit.entity.Player;

import com.njdaeger.java.configuration.data.PlayerConfigData;

public class PlayerConfig {

	/**
	 * Gets an online players configuration.
	 * 
	 * @param player The name of the player.
	 */
	public static PlayerConfigData getConfig(Player player) {
		return new PlayerConfigData(player);
	}

	/**
	 * Gets the configuration of an offline player.
	 * 
	 * @param player The name of the offline player.
	 */
	public static PlayerConfigData getOfflineConfig(String player) {
		return new PlayerConfigData(player);
	}
}
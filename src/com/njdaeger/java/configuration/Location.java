package com.njdaeger.java.configuration;

import org.bukkit.entity.Player;

import com.njdaeger.java.configuration.locations.LastLocationData;
import com.njdaeger.java.configuration.locations.LogoutLocationData;

public class Location {
	
	/**
	 * Get the last location values from a player individually. 
	 * @param player Player to get the values from. 
	 * @return
	 */
	public static LastLocationData getLastLocation(Player player) {
		return Location.getLastLocation(player);
	}
	/**
	 * Get the logout location values from a player individually.
	 * @param player Player to get the values from.
	 * @return
	 */
	public static LogoutLocationData getLogoutLocation(Player player) {
		return Location.getLogoutLocation(player);
	}
	
}

package com.njdaeger.java.configuration;

import org.bukkit.entity.Player;

import com.njdaeger.java.configuration.data.LastLocationData;
import com.njdaeger.java.configuration.data.LogoutLocationData;
import com.njdaeger.java.configuration.data.SetLastLocationData;
import com.njdaeger.java.configuration.data.SetLogoutLocationData;

public class Location {

	private Player player;

	public Location(Player player) {
		this.player = player;
	}

	/**
	 * Get the last location values from a player individually.
	 * 
	 * @return The last locations of the player.
	 */
	public LastLocationData getLastLocation() {
		return new LastLocationData(player);
	}

	/**
	 * Get the logout location values from a player individually.
	 * 
	 * @return The logout locations of the player.
	 */
	public LogoutLocationData getLogoutLocation() {
		return new LogoutLocationData(player);
	}

	/**
	 * Set the last location values for a player.
	 */
	public SetLastLocationData setLastLocation() {
		return new SetLastLocationData(player);
	}

	/**
	 * Set the last logout values for a player.
	 */
	public SetLogoutLocationData setLogoutLocation() {
		return new SetLogoutLocationData(player);
	}

}

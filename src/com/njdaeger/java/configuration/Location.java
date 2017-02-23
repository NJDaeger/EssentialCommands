package com.njdaeger.java.configuration;

import org.bukkit.entity.Player;

import com.njdaeger.java.configuration.data.LastLocation;
import com.njdaeger.java.configuration.data.LogoutLocation;
import com.njdaeger.java.configuration.data.PlayerConfigData;
import com.njdaeger.java.configuration.enums.PlayerPaths;

public class Location {

	private Player player;
	private PlayerConfigData config;

	public Location(Player player) {
		this.player = player;
		config = new PlayerConfigData(player);
	}

	/**
	 * Get the last location values from a player individually.
	 * 
	 * @return The last locations of the player.
	 */
	public LastLocation lastLocation() {
		return new LastLocation(player);
	}

	/**
	 * Get the logout location values from a player individually.
	 * 
	 * @return The logout locations of the player.
	 */
	public LogoutLocation logoutLocation() {
		return new LogoutLocation(player);
	}

	/**
	 * Sets the last movement time to the current time in millis.
	 */
	public void setLastMovement() {
		config.setValue(PlayerPaths.LASTMOVE.getPath(), System.currentTimeMillis());
	}

	/**
	 * Gets the last time the player moved.
	 * 
	 * @return The time in milliseconds for when the last movement the player
	 *         did.
	 */
	public long getLastMovement() {
		return (long) config.getValue(PlayerPaths.LASTMOVE.getPath());
	}

}

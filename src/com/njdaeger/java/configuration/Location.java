package com.njdaeger.java.configuration;

import com.njdaeger.java.configuration.controllers.PlayerConfig;
import com.njdaeger.java.configuration.data.LastLocationData;
import com.njdaeger.java.configuration.data.LogoutLocationData;

public class Location extends PlayerConfig {
	
	/**
	 * Get the last location values from a player individually. 
	 * @param player Player to get the values from. 
	 * @return
	 */
	public LastLocationData getLastLocation() {
		return getLastLocation();
	}
	/**
	 * Get the logout location values from a player individually.
	 * @param player Player to get the values from.
	 * @return
	 */
	public LogoutLocationData getLogoutLocation() {
		return getLogoutLocation();
	}
	
}

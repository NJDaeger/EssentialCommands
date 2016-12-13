package com.njdaeger.java.configuration;

import com.njdaeger.java.configuration.data.LastLocationData;
import com.njdaeger.java.configuration.data.LogoutLocationData;
import com.njdaeger.java.configuration.data.SetLastLocationData;
import com.njdaeger.java.configuration.data.SetLogoutLocationData;

public class Location {
	
	/**
	 * Get the last location values from a player individually. 
	 * @return
	 */
	public LastLocationData getLastLocation() {
		return new LastLocationData();
	}
	/**
	 * Get the logout location values from a player individually.
	 * @return
	 */
	public LogoutLocationData getLogoutLocation() {
		return new LogoutLocationData();
	}
	
	/**
	 * Set the last location values for a player.
	 * @return
	 */
	public SetLastLocationData setLastLocation() {
		return new SetLastLocationData();
	}
	
	/**
	 * Set the last logout values for a player.
	 * @return
	 */
	public SetLogoutLocationData setLogoutLocation() {
		return new SetLogoutLocationData();
	}
	
}

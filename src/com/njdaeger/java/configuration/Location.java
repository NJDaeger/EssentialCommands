package com.njdaeger.java.configuration;

import org.bukkit.entity.Player;

public class Location {
	
	public static LastLocation getLastLocation(Player player) {
		return LastLocation.getLastLocation(player);
	}
	public static LogoutLocation getLogoutLocation(Player player) {
		return LogoutLocation.getLogoutLocation(player);
	}
	
}

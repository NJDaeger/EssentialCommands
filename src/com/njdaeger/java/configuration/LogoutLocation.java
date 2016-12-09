package com.njdaeger.java.configuration;

import org.bukkit.entity.Player;

import com.njdaeger.java.configuration.controllers.PlayerConfig;
import com.njdaeger.java.configuration.locations.LogoutLocationData;

public class LogoutLocation extends PlayerConfig{
	
	public static Player player;
	
	public static LogoutLocationData getLogoutLocation(Player player) {
		return getLogoutLocation(player);
	}
	
}

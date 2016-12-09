package com.njdaeger.java.configuration;

import org.bukkit.entity.Player;

import com.njdaeger.java.configuration.controllers.PlayerConfig;
import com.njdaeger.java.configuration.locations.LastLocationData;


public class LastLocation extends PlayerConfig {
	
	public static Player player;
	
	public static LastLocationData getLastLocation(Player player) {
		return getLastLocation(player);
	}
}

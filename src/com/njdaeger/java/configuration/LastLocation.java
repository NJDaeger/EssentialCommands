package com.njdaeger.java.configuration;

import org.bukkit.entity.Player;

import com.njdaeger.java.configuration.controllers.PlayerConfig;
import com.njdaeger.java.configuration.interfaces.LastValues;


public class LastLocation extends PlayerConfig {
	
	public static Player player;
	
	public LastValues getLastLocation(Player player) {
		return this.getLastLocation(player);
	}
}

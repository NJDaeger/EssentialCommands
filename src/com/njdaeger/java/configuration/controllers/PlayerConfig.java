package com.njdaeger.java.configuration.controllers;

import org.bukkit.entity.Player;

import com.njdaeger.java.configuration.data.PlayerConfigData;

public class PlayerConfig {
	
	public static Player player;
	
	public static PlayerConfigData getConfig(Player player) {
		PlayerConfig.player = player;
		return new PlayerConfigData();
	}
	
}
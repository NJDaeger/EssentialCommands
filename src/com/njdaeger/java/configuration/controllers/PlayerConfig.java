package com.njdaeger.java.configuration.controllers;

import org.bukkit.entity.Player;

import com.njdaeger.java.configuration.data.PlayerConfigData;

public class PlayerConfig {

	public static PlayerConfigData getConfig(Player player) {
		return new PlayerConfigData(player);
	}

	public static PlayerConfigData getOfflineConfig(String player) {
		return new PlayerConfigData(player);
	}
}
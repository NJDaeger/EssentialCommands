package com.njdaeger.java.configuration.controllers;

import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import com.njdaeger.java.configuration.data.PlayerConfigData;

public class PlayerConfig {

	public static Player player;

	public static PlayerConfigData getConfig(Player player) {
		PlayerConfig.player = player;
		return new PlayerConfigData();
	}

	public static PlayerConfigData getOfflineConfig(String player) {
		if (Database.getDatabase("playerdata").getBase() == null) {
			Database.getDatabase("playerdata").create();
			UUID id = UUID.fromString(Database.getDatabase("playerdata").getEntry(player));
			PlayerConfig.player = (Player) Bukkit.getOfflinePlayer(id);
		} else {
			UUID id = UUID.fromString(Database.getDatabase("playerdata").getEntry(player));
			PlayerConfig.player = (Player) Bukkit.getOfflinePlayer(id);
		}
		return new PlayerConfigData();
	}
}
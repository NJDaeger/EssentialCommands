package com.njdaeger.java.configuration.controllers;

import java.io.IOException;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import com.njdaeger.java.configuration.data.PlayerConfigData;
import com.njdaeger.java.configuration.exceptions.DatabaseExists;
import com.njdaeger.java.configuration.exceptions.DatabaseNotFound;

public class PlayerConfig {
	
	private static Database database = new Database();
	public static Player player;
	
	public static PlayerConfigData getConfig(Player player) {
		PlayerConfig.player = player;
		return new PlayerConfigData();
	}
	public static PlayerConfigData getOfflineConfig(String player) {
		try {
			if (database.getDatabase("playerdata") == null) {
				database.createDatabase("playerdata");
				UUID id = UUID.fromString(database.getDatabaseEntry("playerdata", player));
				PlayerConfig.player = (Player) Bukkit.getOfflinePlayer(id);
			}
			else {
				UUID id = UUID.fromString(database.getDatabaseEntry("playerdata", player));
				PlayerConfig.player = (Player) Bukkit.getOfflinePlayer(id);
			}
		} catch (DatabaseNotFound | DatabaseExists | IOException e) {
			e.printStackTrace();
		}
		return new PlayerConfigData();
	}
}
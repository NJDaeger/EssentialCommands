package com.njdaeger.java.configuration;

import java.util.HashMap;

import org.bukkit.entity.Player;

import com.njdaeger.java.configuration.controllers.PlayerConfig;
import com.njdaeger.java.configuration.data.PlayerConfigData;
import com.njdaeger.java.configuration.enums.PlayerPaths;

public class Transform {

	private static Player player;
	private static PlayerConfigData conf;
	private static HashMap<Player, HashMap<PlayerPaths, Object>> memconf = new HashMap<>();
	private static HashMap<PlayerPaths, Object> values = new HashMap<>();

	/**
	 * When the player joins the configuration wants to be loaded into memory.
	 * 
	 * @param player
	 */
	public Transform(Player player) {
		Transform.player = player;
		Transform.conf = PlayerConfig.getConfig(player);
		for (PlayerPaths paths : PlayerPaths.values()) {
			values.put(paths, getConfig().getValue(paths.getPath()));
			System.out.println(values.get(paths));
			memconf.put(player, values);
		}

	}

	public static Player getPlayer() {
		return player;
	}

	public static PlayerConfigData getConfig() {
		return conf;
	}

	public static boolean isLoaded(Player player) {
		if (memconf.get(player) == null) {
			return false;
		}
		return true;
	}

	public static Object getValue(Player player, PlayerPaths path) {
		return memconf.get(player).get(path);
	}

}

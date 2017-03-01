package com.njdaeger.java.configuration;

import java.util.HashMap;
import java.util.UUID;

import org.bukkit.entity.Player;

import com.njdaeger.java.Core;
import com.njdaeger.java.configuration.data.UserFile;
import com.njdaeger.java.configuration.enums.PlayerPaths;
import com.njdaeger.java.configuration.exceptions.PlayerNotInMemory;
import com.njdaeger.java.wrapper.User;

/**
 * Loads a player into memory and gets the player configuration from memory.
 *
 */
public class Transform {

	//The player's YAML configuration to get the default values from.
	private static UserFile conf;
	//The memory configuration.
	private static HashMap<UUID, HashMap<PlayerPaths, Object>> memconf = new HashMap<>();

	/**
	 * When the player joins the configuration wants to be loaded into memory.
	 * 
	 * @param player
	 */
	//This needs to be the last thing to do when the player joins
	public Transform(Player player) {
		User user = Core.getUser(player);
		System.out.println("Loading " + player.getName() + "'s configuration into memory.");
		Transform.conf = user.getUserFile();
		HashMap<PlayerPaths, Object> values = new HashMap<>();
		for (PlayerPaths paths : PlayerPaths.values()) {
			values.put(paths, conf.getValue(paths.getPath()));
		}
		memconf.put(user.getId(), values);

	}

	/**
	 * Checks if the players configuration is loaded in memory.
	 * 
	 * @param player Player to check.
	 * @return True if its in memory, false otherwise.
	 */
	public static boolean isLoaded(User user) {
		if (memconf.get(user.getId()) == null) {
			return false;
		}
		return true;
	}

	/**
	 * Unloads a players configuration from memory.
	 * 
	 * @param player Player to take out of memory.
	 */
	public static void unload(User user) {
		if (isLoaded(user)) {
			for (PlayerPaths paths : PlayerPaths.values()) {
				conf.setValue(paths.getPath(), memconf.get(user.getId()).get(paths));
			}
			memconf.remove(user.getId()).clear();
			System.out.println("Unloaded " + user.getName() + " from memory.");
			return;
		}
		try {
			throw new PlayerNotInMemory();
		} catch (PlayerNotInMemory e) {
			e.printStackTrace();
		}
	}

	/**
	 * Reloads the players memory configuration and syncs it with the YAML
	 * configuration.
	 * 
	 * @param player Player whos configuration to reload.
	 */
	public static void reload(User user) {
		if (isLoaded(user)) {
			unload(user);
			new Transform(user.getBase());
			return;
		}
		try {
			throw new PlayerNotInMemory();
		} catch (PlayerNotInMemory e) {
			e.printStackTrace();
		}

	}

	/**
	 * Gets the value from a players memory configuration.
	 * 
	 * @param player Player to get the value from.
	 * @param path Path to get the value from.
	 * @return The object value of the path.
	 */
	public static Object getValue(Player player, PlayerPaths path) {
		return memconf.get(Core.getUser(player).getId()).get(path);
	}

	/**
	 * Set the value of the memory configuration for a player.
	 * 
	 * @param player Player to set the value to.
	 * @param path Path in the config.
	 * @param value The new value of the path.
	 */
	public static void setValue(Player player, PlayerPaths path, Object value) {
		memconf.get(Core.getUser(player).getId()).put(path, value);
	}

}

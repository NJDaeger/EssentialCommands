package com.njdaeger.java;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import com.njdaeger.java.chat.MessageFile;
import com.njdaeger.java.configuration.Transform;
import com.njdaeger.java.configuration.controllers.Database;
import com.njdaeger.java.configuration.data.Config;
import com.njdaeger.java.essentials.commands.CommandCore;
import com.njdaeger.java.essentials.listeners.CoreListener;
import com.njdaeger.java.essentials.listeners.PlayerJoinListener;
import com.njdaeger.java.essentials.listeners.PlayerLeaveListener;
import com.njdaeger.java.essentials.utils.BanAPI;
import com.njdaeger.java.essentials.utils.Util;
import com.njdaeger.java.tasks.InfoTask;
import com.njdaeger.java.tasks.MemoryTask;
import com.njdaeger.java.wrapper.User;

public class Core extends JavaPlugin {

	private static Core INSTANCE;
	private static Config CFGINSTANCE;
	private static BanAPI BANINSTANCE;
	private static User user;
	private static HashMap<UUID, User> onlineUserMap = new HashMap<>();
	private static ArrayList<User> onlineUsers = new ArrayList<>();
	private static boolean reloading = false;

	public void registerListeners() {
		new PlayerLeaveListener(this);
		new PlayerJoinListener(this);
		new CoreListener(this);
	}

	private void enableSubplugins() {
		if (getConf().isAnnotationsEnabled()) {
			Bukkit.getLogger().info("[EssentialCommands] Annotations is now Enabled.");
		}
		if (getConf().isLoginClearanceEnabled()) {
			Bukkit.getLogger().info("[EssentialCommands] LoginClearance is now Enabled.");
		}
		if (getConf().isNJPermsEnabled()) {
			Bukkit.getLogger().info("[EssentialCommands] NJPerms is now Enabled.");
		}
		if (getConf().isServerProtectEnabled()) {
			Bukkit.getLogger().info("[EssentialCommands] ServerProtect is now Enabled.");
		} else
			Bukkit.getLogger().info("No sub-plugins have been enabled in EssentialCommands.");
	}

	private void registerPermissions() {
		Util.generatePermissions();
		Bukkit.getLogger().info("[EssentialCommands] Version " + this.getDescription().getVersion() + " by " + this
				.getDescription().getAuthors() + " is now Enabled!");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.bukkit.plugin.java.JavaPlugin#onEnable() check if the plugin is
	 * enabled with a checkenabled method and call it in the onenable.
	 */
	@Override
	public void onEnable() {
		INSTANCE = this;
		CFGINSTANCE = new Config();
		BANINSTANCE = new BanAPI();
		MessageFile.create();
		CommandCore.registerCommands();
		getConf().createConfig();
		TPS.getTPSClass();
		new InfoTask(this).run();
		new MemoryTask();
		enableSubplugins();
		registerListeners();
		registerPermissions();
		if (getConf().loadInMemory()) {
			setReloading(false);
			for (Player players : Bukkit.getOnlinePlayers()) {
				user = new User(players);
				onlineUserMap.put(players.getUniqueId(), user);
				onlineUsers.add(user);
				new Transform(players);
			}
		}
	}

	@Override
	public void onDisable() {
		if (isReloading()) {
			if (getConf().loadInMemory()) {
				for (Player players : Bukkit.getOnlinePlayers()) {
					Transform.unload(players);
				}
			}
			return;
		}
	}

	/**
	 * Get the instance of the plugin core.
	 * 
	 * @return The plugin core.
	 */
	public static Core getInstance() {
		return INSTANCE;
	}

	/**
	 * Get the plugin configuration.
	 *
	 * @return Returns the plugin configuration.
	 */
	public static Config getConf() {
		return CFGINSTANCE;
	}

	/**
	 * Checks if the server is reloading.
	 * 
	 * @return True if reloading, false otherwise.
	 */
	public static boolean isReloading() {
		return reloading;
	}

	/**
	 * Sets the reloading variable.
	 * 
	 * @param reloading True if reloading, false otherwise
	 */
	public static void setReloading(boolean reloading) {
		Core.reloading = reloading;
	}

	/**
	 * Gets the plugin BanAPI
	 * 
	 * @return The instance of the BanAPI.
	 */
	public static BanAPI getBanAPI() {
		return BANINSTANCE;
	}

	/**
	 * Gets an online User via their in game name.
	 * 
	 * @param name The name of the player you want to get.
	 * @return Null if the player doesn't exist, otherwise it returns the user.
	 */
	public static User getUser(String name) {
		Player player = Bukkit.getPlayer(name);
		if (player == null) {
			return null;
		}
		return onlineUserMap.get(player.getUniqueId());
	}

	/**
	 * Gets an online User via player object.
	 * 
	 * @param player The player to convert into a user.
	 * @return The user.
	 */
	public static User getUser(Player player) {
		return onlineUserMap.get(player.getUniqueId());
	}

	/**
	 * Get an online User via their UUID
	 * 
	 * @param userUUID The players UUID.
	 * @return The user.
	 */
	public static User getUser(UUID userUUID) {
		return onlineUserMap.get(userUUID);
	}

	/**
	 * Attempts to get an offline player from the player database.
	 * 
	 * @param name The name of the offline player.
	 * @return Null if the database doesn't contain the player, it returns the
	 *         user otherwise.
	 */
	public static User getOfflineUser(String name) {
		UUID uuid = UUID.fromString(Database.getDatabase("playerdata").getEntry(name));
		if (uuid == null) {
			return null;
		}
		return new User(Bukkit.getOfflinePlayer(uuid).getPlayer());
	}

	/**
	 * Get a list of the online users.
	 * 
	 * @return The online users.
	 */
	public static List<User> getOnlineUsers() {
		return onlineUsers;
	}
}

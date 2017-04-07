package com.njdaeger.java;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import com.njdaeger.java.chat.MessageFile;
import com.njdaeger.java.command.util.BukkitCommonLib;
import com.njdaeger.java.command.util.commands.CommandInfo;
import com.njdaeger.java.command.util.commands.CommandReg;
import com.njdaeger.java.configuration.data.Config;
import com.njdaeger.java.configuration.data.Database;
import com.njdaeger.java.configuration.enums.InternalDatabase;
import com.njdaeger.java.configuration.interfaces.IDatabase;
import com.njdaeger.java.listeners.PlayerJoinListener;
import com.njdaeger.java.listeners.PlayerLeaveListener;
import com.njdaeger.java.register.CommandCore;
import com.njdaeger.java.register.CoreListener;
import com.njdaeger.java.tasks.InfoTask;
import com.njdaeger.java.tasks.MemoryTask;
import com.njdaeger.java.utils.BanAPI;
import com.njdaeger.java.utils.Util;
import com.njdaeger.java.wrapper.OfflineUser;
import com.njdaeger.java.wrapper.User;

public class Core extends JavaPlugin {
	
	private static Core INSTANCE;
	private static Config CFGINSTANCE;
	private static BanAPI BANINSTANCE;
	private static User user;
	private static HashMap<UUID, User> onlineUserMap = new HashMap<>();
	private static Collection<User> onlineUsers = new ArrayList<>();
	private static Collection<IDatabase> databases = new ArrayList<>();
	private static boolean reloading = false;
	
	/*
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 */
	
	/**
	 * Registers listeners
	 */
	private void registerListeners() {
		new PlayerLeaveListener(this);
		new PlayerJoinListener(this);
		new CoreListener(this);
	}
	
	/**
	 * Registers tasks
	 */
	private void registerTasks() {
		new InfoTask();
		new MemoryTask();
	}
	
	/**
	 * Registers various objects.
	 */
	private void registerUtils() {
		MessageFile.create();
		CommandCore.registerCommands();
		Util.generatePermissions();
		getConf().createConfig();
	}
	
	/**
	 * Registers all the users if a reload is present.
	 */
	private void registerUsers() {
		if (getConf().loadInMemory()) {
			setReloading(false);
			for (Player players : Bukkit.getOnlinePlayers()) {
				user = new User(players);
				user.loginUpdate();
			}
		}
	}
	
	/**
	 * Registers the permissions.
	 */
	private void registerPermissions() {
		Util.generatePermissions();
		Bukkit.getLogger().info("[EssentialCommands] Version " + this.getDescription().getVersion() + " by "
				+ this.getDescription().getAuthors() + " is now Enabled!");
	}
	
	/*
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 */
	
	@Override
	public void onEnable() {
		INSTANCE = this;
		CFGINSTANCE = new Config();
		BANINSTANCE = new BanAPI();
		new BukkitCommonLib(this);
		registerTasks();
		registerUtils();
		registerListeners();
		registerPermissions();
		registerUsers();
	}
	
	@Override
	public void onDisable() {
		if (isReloading()) {
			if (getConf().loadInMemory()) {
				for (Player players : Bukkit.getOnlinePlayers()) {
					User user = getUser(players);
					user.logoutUpdate();
					user = null;
					onlineUsers.clear();
					onlineUserMap.clear();
				}
			}
			return;
		}
	}
	
	/*
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 */
	
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
	 * @param reloading
	 *            True if reloading, false otherwise
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
	
	/*
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 */
	
	/**
	 * Gets an online User via their in game name.
	 * 
	 * @param name
	 *            The name of the player you want to get.
	 * @return Null if the player doesn't exist, otherwise it returns the user.
	 */
	public static User getUser(String name) {
		if (Bukkit.getPlayer(name) != null) {
			return onlineUserMap.get(Bukkit.getPlayer(name).getUniqueId());
		}
		for (User user : getOnlineUsers()) {
			if (Util.checkIfSimilar(name, user.getNickname())) {
				return user;
			}
		}
		return null;
	}
	
	/**
	 * Gets an online User via player object.
	 * 
	 * @param player
	 *            The player to convert into a user.
	 * @return The user.
	 */
	public static User getUser(Player player) {
		return onlineUserMap.get(player.getUniqueId());
	}
	
	/**
	 * Get an online User via their UUID
	 * 
	 * @param userUUID
	 *            The players UUID.
	 * @return The user.
	 */
	public static User getUser(UUID userUUID) {
		return onlineUserMap.get(userUUID);
	}
	
	/**
	 * Attempts to get an offline player from the player database.
	 * 
	 * @param name
	 *            The name of the offline player.
	 * @return Null if the database doesn't contain the player, it returns the
	 *         user otherwise.
	 */
	public static OfflineUser getOfflineUser(String name) {
		Database database = (Database) getDatabase(InternalDatabase.PLAYERDATA);
		if (database.getEntry(name) == null) {
			return null;
		}
		if (database.getEntry(name).getValue() == null) {
			return null;
		}
		/*
		 * if (Database.getDatabase("playerdata").getEntry(name) == null) {
		 * return null; } UUID uuid =
		 * UUID.fromString(Database.getDatabase("playerdata").getEntry(name));
		 * if (uuid == null) { return null; }
		 */
		return new OfflineUser(name);
	}
	
	/*
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 */
	
	/**
	 * Get a list of the online users.
	 * 
	 * @return The online users.
	 */
	public static Collection<User> getOnlineUsers() {
		return onlineUsers;
	}
	
	/**
	 * Gets the online user map.
	 * 
	 * @return The online users.
	 * 
	 *         <p>
	 *         Do not mess with this unless you know what you're doing.
	 */
	public static HashMap<UUID, User> getOnlineUserMap() {
		return onlineUserMap;
	}
	
	/**
	 * Gets a collection of all the registered databases.
	 * 
	 * @return A collection of databases.
	 */
	public static Collection<IDatabase> getRegisterdDatabases() {
		return databases;
	}
	
	/**
	 * Gets a list of currently afk users.
	 * 
	 * @return A list of afk users.
	 */
	public static Collection<User> getAfkUsers() {
		Collection<User> list = new ArrayList<>();
		for (Player users : Groups.afk) {
			list.add(getUser(users));
		}
		return list;
	}
	
	/**
	 * Gets a list of currently spying users.
	 * 
	 * @return All the users with socialspy enabled.
	 */
	public static Collection<User> getSpyingUsers() {
		Collection<User> users = new ArrayList<>();
		for (Player user : Groups.socialspy) {
			users.add(getUser(user));
		}
		return users;
	}
	
	/*
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 */
	
	/**
	 * Gets a command based from its name.
	 * 
	 * @param name
	 *            The name of the command.
	 * @return The command information.
	 */
	public static CommandInfo getRegisteredCommand(String name) {
		if (CommandReg.commands.get(name) == null) {
			return null;
		}
		return CommandReg.commands.get(name);
	}
	
	/**
	 * Gets a database from its name.
	 * 
	 * @param name
	 *            The name of the database to retrieve.
	 * @return The database.
	 */
	public static IDatabase getDatabase(String name) {
		for (IDatabase database : databases) {
			if (database.getName().equals(name)) {
				return database;
			}
		}
		return new Database(name);
	}
	
	/**
	 * Gets an internal database.
	 * 
	 * @param internalBase
	 *            The internal database to retrieve.
	 * @return The internal database.
	 */
	public static IDatabase getDatabase(InternalDatabase internalBase) {
		for (IDatabase database : databases) {
			if (database.getName().equals(internalBase.getName())) {
				return database;
			}
		}
		return new Database(internalBase);
	}
}

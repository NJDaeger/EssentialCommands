package com.njdaeger.java;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import com.njdaeger.java.chat.MessageFile;
import com.njdaeger.java.configuration.Transform;
import com.njdaeger.java.configuration.data.Config;
import com.njdaeger.java.essentials.commands.CommandCore;
import com.njdaeger.java.essentials.listeners.CoreListener;
import com.njdaeger.java.essentials.listeners.PlayerJoinListener;
import com.njdaeger.java.essentials.listeners.PlayerLeaveListener;
import com.njdaeger.java.essentials.utils.BanAPI;
import com.njdaeger.java.essentials.utils.Util;
import com.njdaeger.java.tasks.InfoTask;
import com.njdaeger.java.tasks.MemoryTask;

public class Core extends JavaPlugin {

	private static Core INSTANCE;
	private static Config CFGINSTANCE;
	private static BanAPI BANINSTANCE;
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
}

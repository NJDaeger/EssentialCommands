package com.njdaeger.java;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import com.njdaeger.java.chat.MessageFile;
import com.njdaeger.java.configuration.data.Config;
import com.njdaeger.java.essentials.commands.CommandCore;
import com.njdaeger.java.essentials.listeners.CoreListener;
import com.njdaeger.java.essentials.listeners.PlayerJoinListener;
import com.njdaeger.java.essentials.listeners.PlayerLeaveListener;
import com.njdaeger.java.essentials.utils.Util;
import com.njdaeger.java.tasks.InfoTask;

public class Core extends JavaPlugin {

	private static Core INSTANCE;
	private static Config CFGINSTANCE;

	public void registerListeners() {
		new PlayerLeaveListener(this);
		new PlayerJoinListener(this);
		new CoreListener(this);
	}

	public void enableSubplugins() {
		if (getConf().isAnnotationsEnabled()) {
			Bukkit.getLogger().info("[EssentialCommands] Annotations is now Enabled.");
		}
		if (getConf().isCodesEnabled()) {
			Bukkit.getLogger().info("[EssentialCommands] Codes is now Enabled.");
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

	public void registerPermissions() {
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
		MessageFile.create();
		CommandCore.registerCommands();
		getConf().createConfig();
		TPS.getTPSClass();
		new InfoTask(this).run();
		enableSubplugins();
		registerListeners();
		registerPermissions();

	}

	@Override
	public void onDisable() {

	}

	public static Core getInstance() {
		return INSTANCE;
	}

	public static Config getConf() {
		return CFGINSTANCE;
	}
}

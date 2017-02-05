package com.njdaeger.java;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import com.njdaeger.api.json.JsonAPI;
import com.njdaeger.api.json.data.JsonHandler;
import com.njdaeger.java.chat.MessageFile;
import com.njdaeger.java.configuration.controllers.Config;
import com.njdaeger.java.essentials.commands.CommandCore;
import com.njdaeger.java.essentials.listeners.CoreListener;
import com.njdaeger.java.essentials.listeners.PlayerJoinListener;
import com.njdaeger.java.essentials.listeners.PlayerLeaveListener;
import com.njdaeger.java.essentials.utils.Util;

public class Core extends JavaPlugin {

	public void registerListeners() {
		new PlayerLeaveListener(this);
		new PlayerJoinListener(this);
		new CoreListener(this);
	}

	public void enableSubplugins() {
		if (Config.getConfig().isAnnotationsEnabled() == true) {
			Bukkit.getLogger().info("[EssentialCommands] Annotations is now Enabled.");
		}
		if (Config.getConfig().isCodesEnabled() == true) {
			Bukkit.getLogger().info("[EssentialCommands] Codes is now Enabled.");
		}
		if (Config.getConfig().isLoginclearanceEnabled() == true) {
			Bukkit.getLogger().info("[EssentialCommands] LoginClearance is now Enabled.");
		}
		if (Config.getConfig().isNJPermsEnabled() == true) {
			Bukkit.getLogger().info("[EssentialCommands] NJPerms is now Enabled.");
		}
		if (Config.getConfig().isServerprotectEnabled() == true) {
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
		JsonHandler handler = new JsonAPI(this).create("test");
		handler.set("test", false).set("messages", true);
		MessageFile.create();
		CommandCore.registerCommands();
		Config.getConfig().newConfig();
		TPS.getTPSClass();
		new InfoTask(this).run();
		enableSubplugins();
		registerListeners();
		registerPermissions();

	}

	@Override
	public void onDisable() {

	}
}

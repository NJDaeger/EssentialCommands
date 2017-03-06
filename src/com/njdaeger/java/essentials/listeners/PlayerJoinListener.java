package com.njdaeger.java.essentials.listeners;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.Plugin;

import com.njdaeger.java.Core;
import com.njdaeger.java.configuration.controllers.Database;
import com.njdaeger.java.configuration.data.DatabaseData;
import com.njdaeger.java.essentials.utils.BanAPI;
import com.njdaeger.java.wrapper.User;

public class PlayerJoinListener implements Listener {

	private BanAPI essBan = Core.getBanAPI();

	Plugin plugin = Bukkit.getPluginManager().getPlugin("EssentialCommands");

	public PlayerJoinListener(Core plugin) {
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
	}

	@EventHandler(priority = EventPriority.HIGHEST)
	public void onJoin(PlayerJoinEvent e) {
		new User(e.getPlayer());
		Core.getUser(e.getPlayer()).loginUpdate();
		if (e.getPlayer().isBanned()) {
			if (essBan.isBanExpired(e.getPlayer().getName())) {
				essBan.unban(e.getPlayer().getName());
			}
		}
		return;
		/*
		 * if (PlayerConfig.getPlayerFile(e.getPlayer()) == null) {
		 * PlayerConfig.create(e.getPlayer()); Bukkit.getLogger().info(
		 * "Config file for " + e.getPlayer().getName() + " is being created.");
		 * return; } else { PlayerConfig.loginUpdate(e.getPlayer());
		 * Bukkit.getLogger().info("Config file for " + e.getPlayer().getName()
		 * + " loaded."); return; }
		 */
	}

	@EventHandler
	public void updateDatabase(PlayerJoinEvent e) {
		User player = Core.getUser(e.getPlayer());
		DatabaseData base = Database.getDatabase("playerdata");
		if (base.getBase() == null) {
			base.create();
		}
		if (base.getEntry(player.getName()) == null) {
			base.addEntry(player.getName(), player.getId().toString());
			return;
		}
		if (base.getEntry(player.getName()).matches(player.getId().toString())) {
			return;
		} else {
			base.removeEntry(player.getName());
			base.addEntry(player.getName(), player.getId().toString());
			return;
		}
	}

	/*
	 * Check if player file and path exist on join.
	 * 
	 * Get all the values they had and set them. So, displayname, rank, etc..
	 * 
	 * Things to put in player file: - Playername: String - Displayname: String
	 * - AFK: boolean - Hidden: boolean - PMEnabled: boolean - Rank: String -
	 * Muted: boolean - Socialspy: boolean - tptoggled: boolean - ip: String -
	 * flying: boolean - gamemode: String - god: boolean - flyspeed: int -
	 * walkspeed: int - op: boolean - banned: boolean - login: int - logout: int
	 * - lastlocation: - world: String - x: double - y: double - z: double -
	 * yaw: double - pitch: double - logoutlocation: - world: String - x: double
	 * - y: double - z: double - yaw: double - pitch: double
	 * 
	 * 
	 * EssentialCommands/users/(UserUUID)/user.yml
	 * EssentialCommands/users/(UserUUID)/homes/homename.yml
	 * EssentialCommands/users/(UserUUID)/messages/#.yml
	 * EssentialCommands/warps/warpname.yml EssentialCommands/motd.txt
	 * 
	 */

}

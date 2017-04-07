package com.njdaeger.java.listeners;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.Plugin;

import com.njdaeger.java.Core;
import com.njdaeger.java.configuration.data.Database;
import com.njdaeger.java.configuration.data.Entry;
import com.njdaeger.java.configuration.enums.InternalDatabase;
import com.njdaeger.java.utils.BanAPI;
import com.njdaeger.java.wrapper.User;

public class PlayerJoinListener implements Listener {
	
	private BanAPI essBan = Core.getBanAPI();
	
	Plugin plugin = Bukkit.getPluginManager().getPlugin("EssentialCommands");
	
	public PlayerJoinListener(Core plugin) {
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
	}
	
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onJoin(PlayerJoinEvent e) {
		if (e.getPlayer().isBanned()) {
			if (essBan.isBanExpired(e.getPlayer().getName())) {
				essBan.unban(e.getPlayer().getName());
			}
		}
		new User(e.getPlayer());
		Core.getUser(e.getPlayer()).loginUpdate();
		User user = Core.getUser(e.getPlayer());
		Database database = (Database) Core.getDatabase(InternalDatabase.PLAYERDATA);
		if (database.getEntry(user.getName()) == null) {
			database.addEntry(new Entry(database, user.getName(), user.getId().toString()));
		}
		if (database.getEntryFromValue(user.getId().toString()) != null) {
			Entry ent = (Entry) database.getEntryFromValue(user.getId());
			if (!ent.getPath().equals(user.getName())) {
				ent.setPath(user.getName());
				return;
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
	
	/*
	 * @EventHandler public void updateDatabase(PlayerJoinEvent e) { User user =
	 * Core.getUser(e.getPlayer()); // DatabaseData base =
	 * Database.getDatabase("playerdata"); Database database = (Database)
	 * Core.getDatabase(InternalDatabase.PLAYERDATA);
	 * System.out.println(database.getName());
	 * System.out.println(user.getName()); if (database.getEntry(user.getName())
	 * == null) { database.addEntry(new Entry(database, user.getName(),
	 * user.getId())); } if (database.getEntryFromValue(user.getId()) != null) {
	 * Entry ent = (Entry) database.getEntryFromValue(user.getId()); if
	 * (!ent.getPath().equals(user.getName())) { ent.setPath(user.getName());
	 * return; } }
	 * 
	 * if (base.getBase() == null) { base.create(); } if
	 * (base.getEntry(player.getName()) == null) {
	 * base.addEntry(player.getName(), player.getId().toString()); return; } if
	 * (base.getEntry(player.getName()).matches(player.getId().toString())) {
	 * return; } else { base.removeEntry(player.getName());
	 * base.addEntry(player.getName(), player.getId().toString()); return; }
	 * 
	 * }
	 */
	
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

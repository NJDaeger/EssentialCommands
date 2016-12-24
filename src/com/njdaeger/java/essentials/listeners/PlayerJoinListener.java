package com.njdaeger.java.essentials.listeners;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.bukkit.BanList.Type;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.Plugin;

import com.njdaeger.java.Core;
import com.njdaeger.java.configuration.controllers.Database;
import com.njdaeger.java.configuration.controllers.PlayerConfig;
import com.njdaeger.java.configuration.data.DatabaseData;

import net.md_5.bungee.api.ChatColor;

public class PlayerJoinListener implements Listener {
	Plugin plugin = Bukkit.getPluginManager().getPlugin("EssentialCommands");

	public PlayerJoinListener(Core plugin) {
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
	}

	@EventHandler
	public void onJoin(PlayerJoinEvent e) {
		PlayerConfig.getConfig(e.getPlayer()).createConfig();
		Player player = e.getPlayer();
		if (player.isBanned()) {
			Date expire = Bukkit.getServer().getBanList(Type.NAME).getBanEntry(player.getName()).getExpiration();
			if (expire.equals(null)) {
				return;
			} else {
				DateFormat format = new SimpleDateFormat("MM:dd:yy hh:mm:ss");
				Date date = new Date(System.currentTimeMillis());
				try {
					long expiration = format.parse(format.format(expire)).getTime();
					long current = format.parse(format.format(date)).getTime();
					if (expiration >= current) {
						e.setJoinMessage(null);
						player.kickPlayer(ChatColor.RED
								+ "You are still banned. If you believe this is an error, contact an administrator to resolve the issue.");
						return;
					} else {
						Bukkit.getServer().getBanList(Type.NAME).pardon(player.getName());
						return;
					}
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		}
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
		Player player = e.getPlayer();
		DatabaseData base = Database.getDatabase("playerdata");
		if (base.getBase() == null) {
			base.create();
			base.addEntry(player.getName(), player.getUniqueId().toString());
			return;
		} else if (base.getEntry(player.getName()) == null) {
			base.addEntry(player.getName(), player.getUniqueId().toString());
			return;
		} else {
			if (base.getEntry(player.getName()).matches(player.getUniqueId().toString())) {
				return;
			} else {
				base.removeEntry(player.getName());
				base.addEntry(player.getName(), player.getUniqueId().toString());
				return;
			}
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

package com.njdaeger.java.configuration.controllers;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import net.md_5.bungee.api.ChatColor;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import com.njdaeger.java.essentials.exceptions.UnknownStatusException;
import com.njdaeger.java.essentials.utils.Util;
import com.njdaeger.java.essentials.utils.status.MuteStatus;
import com.njdaeger.java.essentials.utils.status.SpyStatus;
import com.njdaeger.java.essentials.utils.status.Status;

public class PlayerConfig {
	
	public static void warn(String message) {
		Bukkit.getServer().getLogger().warning(message);
		return;
	}
	/**<p>Creates a new config file for the player if it doesn't exist already.</p>
	 * @param player - Target player to create config for.
	 */
	public static void create(Player player) {
		UUID userID = player.getUniqueId();
		File dir = new File("plugins"+File.separator+"EssentialCommands"+File.separator+"users"+File.separator+userID);
		File dir1 = new File(dir+File.separator+"user.yml");
		if (dir.exists()) {
			if (!dir1.exists()) {
				try {
					dir1.createNewFile();
					YamlConfiguration uconfig = YamlConfiguration.loadConfiguration(dir1);
					uconfig.set("playername", player.getName());
					uconfig.set("displayname", player.getDisplayName());
					uconfig.set("afk", false);
					uconfig.set("hidden", false);
					uconfig.set("messaging", true);
					uconfig.set("rank", null);
					uconfig.set("muted", false);
					uconfig.set("socialspy", false);
					uconfig.set("tptoggled", false);
					uconfig.set("ip", player.getAddress().getHostName());
					uconfig.set("flying", player.isFlying());
					uconfig.set("gamemode", player.getGameMode().name().toLowerCase());
					uconfig.set("god", false);
					uconfig.set("flyspeed", player.getFlySpeed());
					uconfig.set("walkspeed", player.getWalkSpeed());
					uconfig.set("op", player.isOp());
					uconfig.set("login", System.currentTimeMillis());
					uconfig.set("logout", null);
					uconfig.set("lastlocation.world", player.getWorld().getName());
					uconfig.set("lastlocation.x", player.getLocation().getX());
					uconfig.set("lastlocation.y", player.getLocation().getY());
					uconfig.set("lastlocation.z", player.getLocation().getZ());
					uconfig.set("lastlocation.yaw", player.getLocation().getYaw());
					uconfig.set("lastlocation.pitch", player.getLocation().getPitch());
					uconfig.set("logoutlocation.world", null);
					uconfig.set("logoutlocation.x", null);
					uconfig.set("logoutlocation.y", null);
					uconfig.set("logoutlocation.z", null);
					uconfig.set("logoutlocation.yaw", null);
					uconfig.set("logoutlocation.pitch", null);
					uconfig.save(dir1);
					return;
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			else {
				return;
			}
		}
		else {
			dir.mkdirs();
			try {
				dir1.createNewFile();
				YamlConfiguration uconfig = YamlConfiguration.loadConfiguration(dir1);
				uconfig.set("playername", player.getName());
				uconfig.set("displayname", player.getDisplayName());
				uconfig.set("afk", false);
				uconfig.set("hidden", false);
				uconfig.set("messaging", true);
				uconfig.set("rank", null);
				uconfig.set("muted", false);
				uconfig.set("socialspy", false);
				uconfig.set("tptoggled", false);
				uconfig.set("ip", player.getAddress().getHostName());
				uconfig.set("flying", player.isFlying());
				uconfig.set("gamemode", player.getGameMode().name().toLowerCase());
				uconfig.set("god", false);
				uconfig.set("flyspeed", player.getFlySpeed());
				uconfig.set("walkspeed", player.getWalkSpeed());
				uconfig.set("op", player.isOp());
				uconfig.set("login", System.currentTimeMillis());
				uconfig.set("logout", null);
				uconfig.set("lastlocation.world", player.getWorld().getName());
				uconfig.set("lastlocation.x", player.getLocation().getX());
				uconfig.set("lastlocation.y", player.getLocation().getY());
				uconfig.set("lastlocation.z", player.getLocation().getZ());
				uconfig.set("lastlocation.yaw", player.getLocation().getYaw());
				uconfig.set("lastlocation.pitch", player.getLocation().getPitch());
				uconfig.set("logoutlocation.world", null);
				uconfig.set("logoutlocation.x", null);
				uconfig.set("logoutlocation.y", null);
				uconfig.set("logoutlocation.z", null);
				uconfig.set("logoutlocation.yaw", null);
				uconfig.set("logoutlocation.pitch", null);
				uconfig.save(dir1);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	/**<p>Logout update is a player update that should activate when the target player leaves the server. </p>
	 * @param player - Player getting updated.
	 */
	public static void logoutUpdate(Player player) {
		UUID userID = player.getUniqueId();
		File dir = new File("plugins"+File.separator+"EssentialCommands"+File.separator+"users"+File.separator+userID);
		File dir1 = new File(dir+File.separator+"user.yml");
		if (dir.exists()) {
			if (dir1.exists()) {
				YamlConfiguration uconfig = YamlConfiguration.loadConfiguration(dir1);
				uconfig.set("playername", player.getName());
				uconfig.set("displayname", player.getDisplayName());
				uconfig.set("afk", false);
				uconfig.set("hidden", false);
				uconfig.set("messaging", Util.allowsMessaging(player));
				uconfig.set("rank", null);
				uconfig.set("muted", Util.isMuted(player));
				uconfig.set("socialspy", Util.isSpying(player));
				uconfig.set("tptoggled", Util.canTP(player));
				uconfig.set("ip", player.getAddress().getHostName());
				uconfig.set("flying", player.isFlying());
				uconfig.set("gamemode", player.getGameMode().name().toLowerCase());
				uconfig.set("god", Util.isGod(player));
				uconfig.set("flyspeed", player.getFlySpeed());
				uconfig.set("walkspeed", player.getWalkSpeed());
				uconfig.set("op", player.isOp());
				uconfig.set("logout", System.currentTimeMillis());
				uconfig.set("logoutlocation.world", player.getWorld().getName());
				uconfig.set("logoutlocation.x", player.getLocation().getX());
				uconfig.set("logoutlocation.y", player.getLocation().getY());
				uconfig.set("logoutlocation.z", player.getLocation().getZ());
				uconfig.set("logoutlocation.yaw", player.getLocation().getYaw());
				uconfig.set("logoutlocation.pitch", player.getLocation().getPitch());
				try {
					uconfig.save(dir1);
				} catch (IOException e) {
					e.printStackTrace();
				}
			} else {
				PlayerConfig.create(player);
				warn("Player file was not found while logging out.");
				return;
			}	
		}
		else {
			PlayerConfig.create(player);
			warn("Player file was not found while logging out.");
			return;
		}
	}
	/**<p>Login update is for when a player logs into the server. The server will update the player according to how his or her config file is setup.</p>
	 * @param player - Player getting updated.
	 */
	public static void loginUpdate(Player player) {
		UUID userID = player.getUniqueId();
		File dir = new File("plugins"+File.separator+"EssentialCommands"+File.separator+"users"+File.separator+userID);
		File dir1 = new File(dir+File.separator+"user.yml");
		if (dir.exists()) {
			if (dir1.exists()) {
				YamlConfiguration uconfig = YamlConfiguration.loadConfiguration(dir1);
				uconfig.set("playername", player.getName());
				player.setDisplayName(ChatColor.translateAlternateColorCodes('&', uconfig.get("displayname").toString()));
				uconfig.set("messaging", Util.allowsMessaging(player));
				uconfig.set("rank", null);
				PlayerConfig.setMuted(player, uconfig);
				PlayerConfig.setSpying(player, uconfig);
				uconfig.set("tptoggled", Util.canTP(player));
				uconfig.set("ip", player.getAddress().getHostName());
				uconfig.set("flying", player.isFlying());
				uconfig.set("gamemode", player.getGameMode().name().toLowerCase());
				uconfig.set("god", Util.isGod(player));
				uconfig.set("flyspeed", player.getFlySpeed());
				uconfig.set("walkspeed", player.getWalkSpeed());
				uconfig.set("op", player.isOp());
				uconfig.set("login", System.currentTimeMillis());
				try {
					uconfig.save(dir1);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			else {
				PlayerConfig.create(player);
				warn("Player file was not found while logging in.");
				return;
			}
		
		} else {
			PlayerConfig.create(player);
			warn("Player file was not found while logging in.");
			return;
		}
	}
	/**
	 * @param player - Gets this player's configuration file.
	 * @return
	 */
	public static YamlConfiguration getPlayerFile(Player player) {
		UUID userID = player.getUniqueId();
		File dir = new File("plugins"+File.separator+"EssentialCommands"+File.separator+"users"+File.separator+userID);
		File dir1 = new File(dir+File.separator+"user.yml");
		if (!dir.exists()) {
			return null;
		}
		if (!dir1.exists()) {
			return null;
		}
		else return YamlConfiguration.loadConfiguration(dir1);
	}
	/**
	 * @param player - Target player to set muted.
	 * @param config - Target player's config file.
	 */
	public static void setMuted(Player player, YamlConfiguration config) {
		if (config.get("muted").equals(true)) {
			MuteStatus.setMuted(player, Status.TRUE);
		}
		if (config.get("muted").equals(false)) {
			MuteStatus.setMuted(player, Status.FALSE);
		}
		else throw new UnknownStatusException();
	}
	/**
	 * @param player - Target player to set socialspy.
	 * @param config - Target player's config file.
	 */
	public static void setSpying(Player player, YamlConfiguration config) {
		if (config.get("socialspy").equals(true)) {
			SpyStatus.setSpying(player, Status.TRUE);
		}
		if (config.get("socialspy").equals(false)) {
			SpyStatus.setSpying(player, Status.FALSE);
		}
		else throw new UnknownStatusException();
	}
	/**
	 * @param player - Target player to set god mode.
	 * @param config - Target player's config file.
	 */
	public static void setGod(Player player, YamlConfiguration config) {
		
	}
	/**
	 * @param player - Target player to set messageable
	 * @param config - Target player's config file.
	 */
	public static void setMessageable(Player player, YamlConfiguration config) {
	}
}

	

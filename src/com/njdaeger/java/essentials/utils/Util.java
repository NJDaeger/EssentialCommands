package com.njdaeger.java.essentials.utils;

import java.io.File;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;

import com.njdaeger.java.Holder;
import com.njdaeger.java.essentials.enums.Permission;

/**
 * @author Noah
 *
 */
public class Util {

	//#####MISC#####//
	public static void broadcast(String message, Permission... permission) {
		for (Player players : Bukkit.getOnlinePlayers()) {
			if (Holder.hasPermission(players, permission)) {
				players.sendMessage(message);
				return;
			} else
				return;
		}
	}

	public static void generatePermissions() {
		PluginManager pm = Bukkit.getServer().getPluginManager();
		for (Permission perm : Permission.values()) {
			pm.addPermission(perm.registerPermission());
		}
	}

	public static boolean hasFullInventory(Player p) {
		if (p.getInventory().firstEmpty() == -1) {
			return true;
		} else
			return false;
	}

	public static boolean isNumber(String input) {
		try {
			Integer.parseInt(input);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}

	public static boolean isDouble(String input) {
		try {
			Double.parseDouble(input);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}

	public static Object getPlayerLogoutTime(Player target) {
		UUID userID = target.getUniqueId();
		File dir = new File("plugins" + File.separator + "EssentialCommands" + File.separator + "users" + File.separator
				+ userID);
		File dir1 = new File(dir + File.separator + "user.yml");
		YamlConfiguration configuration = YamlConfiguration.loadConfiguration(dir1);
		if (!dir.exists()) {
			return null;
		}
		if (!dir1.exists()) {
			return null;
		}
		Object time = configuration.get("logout");
		return time;
	}

	public static Object getPlayerLoginTime(Player target) {
		UUID userID = target.getUniqueId();
		File dir = new File("plugins" + File.separator + "EssentialCommands" + File.separator + "users" + File.separator
				+ userID);
		File dir1 = new File(dir + File.separator + "user.yml");
		YamlConfiguration configuration = YamlConfiguration.loadConfiguration(dir1);
		if (!dir.exists()) {
			return null;
		}
		if (!dir1.exists()) {
			return null;
		}
		Object time = configuration.get("login");
		return time;
	}

	public static Object getCurrentTime() {
		Object time = System.currentTimeMillis();
		return time;
	}
}

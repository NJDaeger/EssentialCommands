package com.njdaeger.java.utils;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;

import com.njdaeger.java.Holder;
import com.njdaeger.java.enums.Permission;

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

	public static Object getCurrentTime() {
		Object time = System.currentTimeMillis();
		return time;
	}
}

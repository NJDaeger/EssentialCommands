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
	
	public static void broadcast(String message, Permission... permission) {
		for (Player players : Bukkit.getOnlinePlayers()) {
			if (Holder.hasPermission(players, permission)) {
				players.sendMessage(message);
			}
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
		return p.getInventory().firstEmpty() == -1;
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
		return System.currentTimeMillis();
	}
	
	public static boolean checkIfSimilar(String base, String checked) {
		int matches = 0;
		int max = 0;
		int size = base.length();
		int size1 = checked.length();
		if (size > size1 || size == size1) {
			max = size;
		} else {
			max = size1;
		}
		for (Character basechars : base.toLowerCase().toCharArray()) {
			for (Character checkedchars : checked.toLowerCase().toCharArray()) {
				if (basechars == null || checkedchars == null) {
					break;
				}
				if (basechars.compareTo(checkedchars) == 0) {
					matches++;
				}
			}
		}
		return matches / max >= .50;
	}
}

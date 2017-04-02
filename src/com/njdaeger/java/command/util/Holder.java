package com.njdaeger.java.command.util;

import org.bukkit.command.CommandSender;

public class Holder {
	
	/**
	 * Checks if the player has any of the needed permissions to execute the command.
	 * @param sender Sender to check for the permissions.
	 * @param permissions The permission nodes the player needs.
	 * @return True if the player has permission.
	 */
	public static boolean hasPermission(CommandSender sender, String... permissions) {
		if (sender.isOp()) {
			return true;
		}
		int i = 0;
		for (String node : permissions) {
			if (i >= 1) {
				return true;
			}
			if (sender.hasPermission(node)) {
				i++;
				return true;
			}
			continue;
		}
		return false;
	}
}

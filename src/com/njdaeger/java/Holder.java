package com.njdaeger.java;

import org.bukkit.entity.Player;

import com.njdaeger.java.essentials.enums.Permission;

public class Holder {
	
	public static boolean hasPermission(Player player, Permission... permission) {
		int i = 0;
		for (Permission perm : permission) {
			if (i > 1) {
				return true;
			}
			if (player.hasPermission(perm.getPermission()) || player.hasPermission(Permission.ESS_ALL.getPermission()) || player.isOp()) {
				i++;
				return true;
			}
			else return false;
		}
		return false;
	}
	
}

package com.njdaeger.essentials.utils;

import org.bukkit.entity.Player;

import com.njdaeger.essentials.enums.Permission;

public class TargetHasPermission {
	
	public static boolean check(Player player, Permission... permission) {
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

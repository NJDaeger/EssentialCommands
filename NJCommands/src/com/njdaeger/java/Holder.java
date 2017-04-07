package com.njdaeger.java;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.njdaeger.java.enums.Permission;
import com.njdaeger.java.wrapper.Sender;

public class Holder {

	public static boolean hasPermission(Player player, Permission... permission) {
		int i = 0;
		for (Permission perm : permission) {
			if (i > 1) {
				return true;
			}
			if (player.hasPermission(perm.getPermission()) || player.hasPermission(Permission.ESS_ALL.getPermission())
					|| player.isOp()) {
				i++;
				return true;
			} else
				return false;
		}
		return false;
	}

	public static boolean hasPermission(CommandSender sndr, Permission... permission) {
		int i = 0;
		for (Permission perm : permission) {
			if (i > 1) {
				return true;
			}
			if (sndr.hasPermission(perm.getPermission()) || sndr.hasPermission(Permission.ESS_ALL.getPermission())
					|| sndr.isOp()) {
				i++;
				return true;
			} else
				return false;
		}
		return false;
	}

	public static boolean hasPermission(Sender sender, Permission... permissions) {
		if (sender.isOp() || sender.hasPermission(Permission.ESS_ALL)) {
			return true;
		}
		int i = 0;
		for (Permission node : permissions) {
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

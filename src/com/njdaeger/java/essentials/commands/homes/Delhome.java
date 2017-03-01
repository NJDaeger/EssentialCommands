package com.njdaeger.java.essentials.commands.homes;

import com.njdaeger.java.Core;
import com.njdaeger.java.command.util.Cmd;
import com.njdaeger.java.command.util.EssCommand;
import com.njdaeger.java.configuration.Parser;
import com.njdaeger.java.configuration.controllers.Database;
import com.njdaeger.java.configuration.controllers.Homes;
import com.njdaeger.java.essentials.enums.Error;
import com.njdaeger.java.essentials.enums.Permission;
import com.njdaeger.java.wrapper.Sender;
import com.njdaeger.java.wrapper.User;

import net.md_5.bungee.api.ChatColor;

public class Delhome extends EssCommand {

	@Override
	@Cmd(
			name = "delhome",
			desc = "Delete an existing home.",
			usage = "/delhome <homename> [player]",
			max = 2,
			min = 1,
			aliases = { "deletehome", "removehome", "clearhome" },
			permissions = { Permission.ESS_DELHOME, Permission.ESS_DELHOME_OTHER })
	public boolean run(Sender sndr, String label, String[] args) {
		if (args.length == 1) {
			if (sndr.isUser()) {
				User user = sndr.asUser();
				if (!Homes.getHome(args[0], user.getBase()).exists()) {
					sndr.sendMessage(Error.HOME_NOTEXIST.sendError());
					sndr.sendMessage(ChatColor.GRAY + "Current homes: " + ChatColor.GREEN + Homes.getHome(null, user
							.getBase()));
					return true;
				}
				Homes.getHome(args[0], user.getBase()).remove();
				sndr.sendMessage(ChatColor.GRAY + "Removed home " + ChatColor.GREEN + args[0] + ChatColor.GRAY
						+ " from homes.");
				return true;
			}
			sndr.sendMessage(Error.NOT_ENOUGH_ARGS.sendError());
			return true;
		}
		if (sndr.hasPermission(Permission.ESS_DELHOME_OTHER)) {
			User target = Core.getUser(args[1]);
			if (target == null) {
				if (Database.getDatabase("playerdata").getEntry(args[1]) == null) {
					sndr.sendMessage(Error.UNKNOWN_PLAYER.sendError());
					return true;
				}
				if (!Homes.getOfflineHome(args[0], args[1]).exists()) {
					sndr.sendMessage(Error.HOME_NOTEXIST.sendError());
					sndr.sendMessage(ChatColor.GRAY + "Current homes: " + ChatColor.GREEN + Homes.getOfflineHome(null,
							args[1]));
					return true;
				}
				Homes.getOfflineHome(args[0], args[1]).remove();
				sndr.sendMessage(ChatColor.GRAY + "Removed home " + ChatColor.GREEN + args[0] + ChatColor.GRAY
						+ " from " + args[1] + "'s homes.");
				return true;
			}
			if (!Homes.getHome(args[0], target.getBase()).exists()) {
				sndr.sendMessage(Error.HOME_NOTEXIST.sendError());
				sndr.sendMessage(ChatColor.GRAY + "Current homes: " + ChatColor.GREEN + Homes.getHome(null, target
						.getBase()));
				return true;
			}
			Homes.getHome(args[0], target.getBase()).remove();
			sndr.sendMessage(ChatColor.GRAY + "Removed home " + ChatColor.GREEN + args[0] + ChatColor.GRAY
					+ " from homes.");
			return true;

		}
		sndr.sendMessage(Parser.parse(Error.NO_PERMISSION.getError(), sndr.asPlayer(), "Unknown",
				Permission.ESS_DELHOME_OTHER));
		return true;
	}
}

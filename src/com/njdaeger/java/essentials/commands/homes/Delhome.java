package com.njdaeger.java.essentials.commands.homes;

import com.njdaeger.java.Core;
import com.njdaeger.java.command.util.Cmd;
import com.njdaeger.java.command.util.EssCommand;
import com.njdaeger.java.configuration.Parser;
import com.njdaeger.java.essentials.enums.Error;
import com.njdaeger.java.essentials.enums.Permission;
import com.njdaeger.java.wrapper.OfflineUser;
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
				if (!user.getHome(args[0]).exists()) {
					sndr.sendMessage(Error.HOME_NOTEXIST.sendError());
					sndr.sendMessage(ChatColor.GRAY + "Current homes: " + ChatColor.GREEN + user.getHome(null)
							.listHomes());
					return true;
				}
				user.getHome(args[0]).remove();
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
				OfflineUser user = Core.getOfflineUser(args[1]);
				if (user == null) {
					sndr.sendMessage(Error.UNKNOWN_PLAYER.sendError());
					return true;
				}

				if (user.getHome(args[0]).exists()) {
					sndr.sendMessage(Error.HOME_NOTEXIST.sendError());
					sndr.sendMessage(ChatColor.GRAY + "Current homes: " + ChatColor.GREEN + user.getHome(null)
							.listHomes());
					return true;
				}
				user.getHome(args[0]).remove();
				sndr.sendMessage(ChatColor.GRAY + "Removed home " + ChatColor.GREEN + args[0] + ChatColor.GRAY
						+ " from " + args[1] + "'s homes.");
				return true;
			}
			if (!target.getHome(args[0]).exists()) {
				sndr.sendMessage(Error.HOME_NOTEXIST.sendError());
				sndr.sendMessage(ChatColor.GRAY + "Current homes: " + ChatColor.GREEN + target.getHome(null)
						.listHomes());
				return true;
			}
			target.getHome(args[0]).remove();
			sndr.sendMessage(ChatColor.GRAY + "Removed home " + ChatColor.GREEN + args[0] + ChatColor.GRAY
					+ " from homes.");
			return true;

		}
		sndr.sendMessage(Parser.parse(Error.NO_PERMISSION.getError(), sndr.asPlayer(), "Unknown",
				Permission.ESS_DELHOME_OTHER));
		return true;
	}
}

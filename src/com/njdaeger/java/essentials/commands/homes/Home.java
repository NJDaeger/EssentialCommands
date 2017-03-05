package com.njdaeger.java.essentials.commands.homes;

import com.njdaeger.java.Core;
import com.njdaeger.java.command.util.Cmd;
import com.njdaeger.java.command.util.EssCommand;
import com.njdaeger.java.command.util.Executor;
import com.njdaeger.java.configuration.Parser;
import com.njdaeger.java.configuration.data.OfflineHome;
import com.njdaeger.java.essentials.enums.Error;
import com.njdaeger.java.essentials.enums.Permission;
import com.njdaeger.java.wrapper.OfflineUser;
import com.njdaeger.java.wrapper.Sender;
import com.njdaeger.java.wrapper.User;

import net.md_5.bungee.api.ChatColor;

public class Home extends EssCommand {

	@Override
	@Cmd(
			name = "home",
			desc = "Go to a saved home.",
			usage = "/home <homename> <player>",
			max = 2,
			min = 1,
			executor = Executor.PLAYER,
			aliases = { "gohome", "tphome", "tohome" },
			permissions = { Permission.ESS_HOME, Permission.ESS_HOME_OTHER })
	public boolean run(Sender sndr, String label, String[] args) {
		User user = sndr.asUser();
		if (args.length == 1) {
			com.njdaeger.java.configuration.data.Home home = (com.njdaeger.java.configuration.data.Home) user.getHome(
					args[0]);
			if (!home.exists()) {
				sndr.sendMessage(Error.HOME_NOTEXIST.sendError());
				return true;
			}
			home.sendHome();
			sndr.sendMessage(ChatColor.GRAY + "You have successfully been sent to " + ChatColor.GREEN + args[0]);
			return true;
		}
		if (sndr.hasPermission(Permission.ESS_HOME_OTHER)) {
			User target = Core.getUser(args[1]);
			if (target == null) {
				OfflineUser otarget = Core.getOfflineUser(args[1]);
				if (otarget == null) {
					sndr.sendMessage(Error.UNKNOWN_PLAYER.sendError());
					return true;
				}
				OfflineHome home = (OfflineHome) otarget.getHome(args[0]);
				if (!home.exists()) {
					sndr.sendMessage(Error.HOME_NOTEXIST.sendError());
					return true;
				}
				home.sendOtherHome(user);
				sndr.sendMessage(ChatColor.GRAY + "You have successfully been sent to " + ChatColor.GREEN + args[0]);
				return true;
			}
			com.njdaeger.java.configuration.data.Home home = (com.njdaeger.java.configuration.data.Home) target.getHome(
					args[0]);
			if (!home.exists()) {
				sndr.sendMessage(Error.HOME_NOTEXIST.sendError());
				return true;
			}
			home.sendOtherHome(user);
			sndr.sendMessage(ChatColor.GRAY + "You have successfully been sent to " + ChatColor.GREEN + args[0]);
			return true;

		}
		sndr.sendMessage(Parser.parse(Error.NO_PERMISSION.getError(), sndr.asPlayer(), "Unknown",
				Permission.ESS_HOME_OTHER));
		return true;
	}
}

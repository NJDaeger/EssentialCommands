package com.njdaeger.java.essentials.commands.homes;

import com.njdaeger.java.Core;
import com.njdaeger.java.command.util.Cmd;
import com.njdaeger.java.command.util.EssCommand;
import com.njdaeger.java.command.util.Executor;
import com.njdaeger.java.configuration.Parser;
import com.njdaeger.java.essentials.enums.Error;
import com.njdaeger.java.essentials.enums.Permission;
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
			if (user.getHome(args[0]).exists()) {
				sndr.sendMessage(Error.HOME_NOTEXIST.sendError());
				return true;
			}
			user.getHome(args[0]).sendHome();
			sndr.sendMessage(ChatColor.GRAY + "You have successfully been sent to " + ChatColor.GREEN + args[0]);
			return true;
		}
		if (sndr.hasPermission(Permission.ESS_HOME_OTHER)) {
			User target = Core.getUser(args[1]);
			if (target == null) {
				User otarget = Core.getOfflineUser(args[1]);
				if (otarget == null) {
					sndr.sendMessage(Error.UNKNOWN_PLAYER.sendError());
					return true;
				}
				if (otarget.getHome(args[0]).exists()) {
					sndr.sendMessage(Error.HOME_NOTEXIST.sendError());
					return true;
				}
				otarget.getHome(args[0]).sendOtherHome(user);
				sndr.sendMessage(ChatColor.GRAY + "You have successfully been sent to " + ChatColor.GREEN + args[0]);
				return true;
			}
			if (!target.getHome(args[0]).exists()) {
				sndr.sendMessage(Error.HOME_NOTEXIST.sendError());
				return true;
			}
			target.getHome(args[0]).sendOtherHome(user);
			sndr.sendMessage(ChatColor.GRAY + "You have successfully been sent to " + ChatColor.GREEN + args[0]);
			return true;

		}
		sndr.sendMessage(Parser.parse(Error.NO_PERMISSION.getError(), sndr.asPlayer(), "Unknown",
				Permission.ESS_HOME_OTHER));
		return true;
	}
}

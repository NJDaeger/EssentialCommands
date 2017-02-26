package com.njdaeger.java.essentials.commands.player;

import org.bukkit.ChatColor;

import com.njdaeger.java.Core;
import com.njdaeger.java.command.util.Cmd;
import com.njdaeger.java.command.util.EssCommand;
import com.njdaeger.java.configuration.Parser;
import com.njdaeger.java.essentials.enums.Error;
import com.njdaeger.java.essentials.enums.Permission;
import com.njdaeger.java.wrapper.Sender;
import com.njdaeger.java.wrapper.User;

public class VanishCommand extends EssCommand {

	@Cmd(
			name = "vanish",
			desc = "Hide yourself from others.",
			usage = "/vanish [player]",
			max = 1,
			aliases = { "v", "hide" },
			permissions = { Permission.ESS_VANISH, Permission.ESS_VANISH_OTHER })
	@Override
	public boolean run(Sender sndr, String commandLabel, String[] args) {
		if (args.length == 0) {
			if (!sndr.isUser()) {
				sndr.sendError(Error.NOT_ENOUGH_ARGS);
				return true;
			}
			User user = sndr.asUser();
			user.setHidden(!user.isHidden());
			if (!user.isHidden()) {
				sndr.sendMessage(ChatColor.GRAY + "You are no longer hidden.");
				return true;
			}
			sndr.sendMessage(ChatColor.GRAY + "You are now hidden.");
			return true;
		}
		if (sndr.hasPermission(Permission.ESS_VANISH_OTHER)) {
			User user = Core.getUser(args[0]);
			if (user == null) {
				sndr.sendError(Error.UNKNOWN_PLAYER);
				return true;
			}
			user.setHidden(!user.isHidden());
			if (!user.isHidden()) {
				sndr.sendMessage(ChatColor.GREEN + user.getNickname() + ChatColor.GRAY + " is no longer hidden.");
				user.sendMessage(ChatColor.GRAY + "You are no longer hidden.");
				return true;
			}
			sndr.sendMessage(ChatColor.GREEN + user.getNickname() + ChatColor.GRAY + " is now hidden.");
			user.sendMessage(ChatColor.GRAY + "You are now hidden.");
			return true;
		}
		sndr.sendMessage(Parser.parse(Error.NO_PERMISSION.getError(), sndr.asPlayer(), "Unknown",
				Permission.ESS_VANISH_OTHER));
		return true;
	}
}

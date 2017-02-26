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

public class FlyCommand extends EssCommand {

	@Override
	@Cmd(
			name = "fly",
			desc = "Make yourself fly!",
			usage = "/fly [player]",
			max = 1,
			permissions = { Permission.ESS_FLY, Permission.ESS_FLY_OTHER })
	public boolean run(Sender sndr, String label, String[] args) {
		if (args.length == 0) {
			if (sndr.isUser()) {
				User user = sndr.asUser();
				if (user.isFlying()) {
					user.setFlying(false);
					sndr.sendMessage(ChatColor.GRAY + "You are no longer flying.");
					return true;
				}
				user.setFlying(true);
				sndr.sendMessage(ChatColor.GRAY + "You are now flying.");
				return true;
			}
			sndr.sendMessage(Error.NOT_ENOUGH_ARGS.sendError());
			return true;
		}
		if (sndr.hasPermission(Permission.ESS_FLY_OTHER)) {
			User user = Core.getUser(args[0]);
			if (user == null) {
				sndr.sendMessage(Error.UNKNOWN_PLAYER.sendError());
				return true;
			}
			if (user.isFlying()) {
				user.setFlying(false);
				user.sendMessage(ChatColor.GRAY + "You are no longer flying.");
				sndr.sendMessage(ChatColor.GREEN + user.getNickname() + ChatColor.GRAY + " is no longer flying.");
				return true;
			}
			user.setFlying(true);
			user.sendMessage(ChatColor.GRAY + "You are now flying.");
			sndr.sendMessage(ChatColor.GREEN + user.getNickname() + ChatColor.GRAY + " is now flying.");
			return true;
		}
		sndr.sendMessage(Parser.parse(Error.NO_PERMISSION.getError(), sndr.asPlayer(), "Unknown",
				Permission.ESS_FLY_OTHER));
		return true;
	}
}

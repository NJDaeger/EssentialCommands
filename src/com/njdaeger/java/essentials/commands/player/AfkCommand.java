package com.njdaeger.java.essentials.commands.player;

import com.njdaeger.java.Core;
import com.njdaeger.java.Holder;
import com.njdaeger.java.command.util.Cmd;
import com.njdaeger.java.command.util.EssCommand;
import com.njdaeger.java.essentials.enums.Error;
import com.njdaeger.java.essentials.enums.Permission;
import com.njdaeger.java.wrapper.Sender;
import com.njdaeger.java.wrapper.User;

public class AfkCommand extends EssCommand {

	@Override
	@Cmd(
			name = "afk",
			desc = "Mark yourself as away from keyboard.",
			usage = "/afk [player]",
			max = 1,
			aliases = { "akf", "away", "brb" },
			permissions = { Permission.ESS_AFK, Permission.ESS_AFK_OTHER })
	public boolean run(Sender sndr, String label, String[] args) {
		if (args.length == 0) {
			if (sndr.isUser()) {
				User u = sndr.asUser();
				//Player player = sndr.asPlayer();
				//PlayerConfig.getConfig(player).setAfk();
				u.setAfk(!u.isAfk());
				return true;
			}
			sndr.sendMessage(Error.NOT_ENOUGH_ARGS.sendError());
			return true;
		}
		if (sndr.isUser()) {
			if (Holder.hasPermission(sndr, Permission.ESS_AFK_OTHER)) {
				User u = Core.getUser(args[0]);
				if (u == null) {
					sndr.sendMessage(Error.UNKNOWN_PLAYER.sendError());
					return true;
				}
				u.setAfk(!u.isAfk());
				return true;
			}
			sndr.sendMessage(Error.NO_PERMISSION.sendError());
		}
		return true;
	}
}
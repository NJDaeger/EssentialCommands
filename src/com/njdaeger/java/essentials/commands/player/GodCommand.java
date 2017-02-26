package com.njdaeger.java.essentials.commands.player;

import org.bukkit.ChatColor;

import com.njdaeger.java.Core;
import com.njdaeger.java.command.util.Cmd;
import com.njdaeger.java.command.util.EssCommand;
import com.njdaeger.java.essentials.enums.Error;
import com.njdaeger.java.essentials.enums.Permission;
import com.njdaeger.java.wrapper.Sender;
import com.njdaeger.java.wrapper.User;

public class GodCommand extends EssCommand {

	@Override
	@Cmd(
			name = "god",
			desc = "Make yourself unkillable.",
			usage = "/god [player]",
			max = 0,
			aliases = { "invincible" },
			permissions = { Permission.ESS_GOD, Permission.ESS_GOD_OTHER })
	public boolean run(Sender sndr, String label, String[] args) {
		if (args.length == 0) {
			if (sndr.isPlayer()) {
				User user = sndr.asUser();
				if (user.isGod()) {
					user.setGod(false);
					sndr.sendMessage(ChatColor.GRAY + "You are no longer in God mode.");
					return true;
				}
				user.setGod(true);
				sndr.sendMessage(ChatColor.GRAY + "You are now in God mode.");
				return true;
			}
			sndr.sendMessage(Error.NOT_ENOUGH_ARGS.sendError());
			return true;
		}
		if (sndr.hasPermission(Permission.ESS_GOD_OTHER)) {
			User target = Core.getUser(args[0]);
			if (target == null) {
				sndr.sendMessage(Error.UNKNOWN_PLAYER.sendError());
				return true;
			}
			if (target.isGod()) {
				target.setGod(false);
				target.sendMessage(ChatColor.GRAY + "You are no longer in God mode.");
				sndr.sendMessage(ChatColor.GREEN + target.getNickname() + ChatColor.GRAY
						+ " is no longer in God mode.");
				return true;
			}
			target.setGod(true);
			target.sendMessage(ChatColor.GRAY + "You are now in God mode.");
			sndr.sendMessage(ChatColor.GREEN + target.getNickname() + ChatColor.GRAY + " is now in God mode.");
			return true;
		}
		sndr.sendMessage(Error.NO_PERMISSION.sendError());
		return true;
	}
}

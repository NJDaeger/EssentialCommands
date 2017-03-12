package com.njdaeger.java.essentials.commands.player;

import org.bukkit.ChatColor;

import com.njdaeger.java.Core;
import com.njdaeger.java.command.util.Cmd;
import com.njdaeger.java.command.util.EssCommand;
import com.njdaeger.java.essentials.enums.Error;
import com.njdaeger.java.essentials.enums.Permission;
import com.njdaeger.java.wrapper.Sender;
import com.njdaeger.java.wrapper.User;

public class RealnameCommand extends EssCommand {

	@Override
	@Cmd(
		name = "realname",
		desc = "Gets the realname of a player.",
		usage = "/realname [player]",
		max = 1,
		aliases = { "getname" },
		permissions = Permission.ESS_REALNAME)
	public boolean run(Sender sender, String label, String[] args) {
		if (args.length == 1) {
			User user = Core.getUser(args[0]);
			if (user == null) {
				sender.sendError(Error.UNKNOWN_PLAYER);
				return true;
			}
			sender.sendMessage(ChatColor.GRAY + args[0] + "'s realname is " + ChatColor.GREEN + user.getName());
			return true;
		}
		sender.sendMessage(ChatColor.GRAY + "Your realname is " + ChatColor.GREEN + sender.getName());
		return true;
	}

}

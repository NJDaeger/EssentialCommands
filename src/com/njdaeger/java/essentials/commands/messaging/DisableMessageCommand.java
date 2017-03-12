package com.njdaeger.java.essentials.commands.messaging;

import org.bukkit.ChatColor;

import com.njdaeger.java.Core;
import com.njdaeger.java.command.util.Cmd;
import com.njdaeger.java.command.util.EssCommand;
import com.njdaeger.java.essentials.enums.Error;
import com.njdaeger.java.essentials.enums.Permission;
import com.njdaeger.java.wrapper.Sender;
import com.njdaeger.java.wrapper.User;

public class DisableMessageCommand extends EssCommand {

	@Override
	@Cmd(
		name = "msgtoggle",
		desc = "Disable incoming private messages.",
		usage = "/msgtoggle [player]",
		max = 1,
		aliases = { "disablemsg", "nomsg", "togglemsg" },
		permissions = Permission.ESS_DISABLE_MSG)
	public boolean run(Sender sender, String label, String[] args) {
		if (args.length == 1) {
			User user = Core.getUser(args[0]);
			if (user == null) {
				sender.sendError(Error.UNKNOWN_PLAYER);
				return true;
			}
			boolean val = !user.isMessageable();
			String msg = ChatColor.GRAY + "Private messaging is disabled.";
			user.setMessageable(val);
			if (val) {
				msg = ChatColor.GRAY + "Private messaging is enabled.";
			}
			user.sendMessage(msg);
			return true;
		}
		if (!sender.isUser()) {
			sender.sendError(Error.NOT_ENOUGH_ARGS);
			return true;
		}
		sender.asUser().setMessageable(!sender.asUser().isMessageable());
		boolean val = !sender.asUser().isMessageable();
		String msg = ChatColor.GRAY + "Private messaging is disabled.";
		sender.asUser().setMessageable(val);
		if (val) {
			msg = ChatColor.GRAY + "Private messaging is enabled.";
		}
		sender.sendMessage(msg);
		return true;
	}
}

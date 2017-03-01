package com.njdaeger.java.essentials.commands.player;

import com.njdaeger.java.Core;
import com.njdaeger.java.command.util.Cmd;
import com.njdaeger.java.command.util.EssCommand;
import com.njdaeger.java.command.util.Executor;
import com.njdaeger.java.wrapper.Sender;
import com.njdaeger.java.wrapper.User;

public class DebugCommand extends EssCommand {

	@Cmd(
			name = "db",
			usage = "/db",
			desc = "Debug player configurations.",
			aliases = { "debug", "bug" },
			executor = Executor.PLAYER)

	@Override
	public boolean run(Sender sender, String label, String[] args) {
		User u = sender.asUser();
		u.sendMessage("User works");
		for (User user : Core.getOnlineUsers()) {
			u.sendMessage(user.getName());
		}
		sender.sendMessage("Afk: " + u.isAfk());
		sender.sendMessage("Flyspeed: " + u.getFlyingSpeed());
		sender.sendMessage("Rank: " + u.getGroup());
		sender.sendMessage("Displayname: " + u.getName());
		sender.sendMessage("Toggled: " + u.isTeleportable());
		sender.sendMessage("Walkspeed: " + u.getWalkingSpeed());
		sender.sendMessage("God: " + u.isGod());
		return true;
	}

}

package com.njdaeger.java.essentials.commands.player;

import com.njdaeger.java.command.util.Cmd;
import com.njdaeger.java.command.util.EssCommand;
import com.njdaeger.java.essentials.enums.Permission;
import com.njdaeger.java.wrapper.Sender;

public class PingCommand extends EssCommand {
	
	@Override
	@Cmd(
			name = "ping",
			desc = "Pong!",
			usage = "/ping",
			max = 0,
			permissions = Permission.ESS_PING)
	public boolean run(Sender sender, String label, String[] args) {
		sender.sendMessage("§7Pong!");
		return true;
	}
}
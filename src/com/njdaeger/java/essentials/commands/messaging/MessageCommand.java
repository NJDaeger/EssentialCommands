package com.njdaeger.java.essentials.commands.messaging;

import com.njdaeger.java.Plugin;
import com.njdaeger.java.command.util.Cmd;
import com.njdaeger.java.command.util.EssCommand;
import com.njdaeger.java.essentials.enums.Permission;
import com.njdaeger.java.essentials.utils.messages.Messenger;
import com.njdaeger.java.wrapper.Sender;

public class MessageCommand extends EssCommand {

	@Override
	public void register() {
		Plugin.getCommand(this);
	}

	@Override
	@Cmd(
			name = "message",
			desc = "Send a private message to a player.",
			usage = "/message <player> <message>",
			min = 2,
			aliases = { "msg", "pm", "write", "private" },
			permissions = { Permission.ESS_MESSAGE })
	public boolean run(Sender sndr, String label, String[] args) {
		String msg = "";
		String finalmsg = "";
		for (String message : args) {
			msg = (msg + message + " ");
			String split[] = msg.split(" ", 2);
			finalmsg = split[1];
		}
		Messenger.sendMessage(sndr, args[0], finalmsg);
		return true;
	}
}

package com.njdaeger.java.essentials.commands.messaging;

import com.njdaeger.java.command.util.Cmd;
import com.njdaeger.java.command.util.EssCommand;
import com.njdaeger.java.essentials.enums.Permission;
import com.njdaeger.java.essentials.utils.messages.Messenger;
import com.njdaeger.java.wrapper.Sender;

public class ReplyCommand extends EssCommand {

	@Override
	@Cmd(
			name = "reply",
			desc = "Reply to the person you messaged last.",
			usage = "/reply <message>",
			min = 1,
			aliases = { "r", "writeback" },
			permissions = { Permission.ESS_MESSAGE })
	public boolean run(Sender sndr, String label, String[] args) {
		String msg = "";
		for (String message : args) {
			msg = (msg + message + " ");
		}
		Messenger.sendReply(sndr, msg);
		return true;
	}

}

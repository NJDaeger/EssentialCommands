package com.njdaeger.java.essentials.commands.messaging;

import java.util.Arrays;
import java.util.List;

import org.bukkit.command.CommandSender;

import com.njdaeger.java.Plugin;
import com.njdaeger.java.command.util.Cmd;
import com.njdaeger.java.command.util.EssCommand;
import com.njdaeger.java.essentials.enums.Permission;
import com.njdaeger.java.essentials.utils.messages.Messenger;

public class MessageCommand extends EssCommand {

	public MessageCommand() {
		super("message");
		List<String> a = Arrays.asList("msg", "pm", "write", "private");
		this.description = "Send a private message to a player.";
		this.usageMessage = "/message <player> <message>";
		this.setAliases(a);
	}

	@Override
	public void register() {
		Plugin.getCommand(this);
	}

	@Cmd(min = 2, permissions = { Permission.ESS_MESSAGE })
	@Override
	public boolean execute(CommandSender sndr, String label, String[] args) {
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

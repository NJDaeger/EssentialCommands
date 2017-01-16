package com.njdaeger.java.essentials.commands.messaging;


import java.util.Arrays;
import java.util.List;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.njdaeger.java.Holder;
import com.njdaeger.java.Plugin;
import com.njdaeger.java.command.util.EssCommand;
import com.njdaeger.java.essentials.enums.Error;
import com.njdaeger.java.essentials.enums.Permission;
import com.njdaeger.java.essentials.utils.messages.Messenger;

public class ReplyCommand extends EssCommand {
	
	public static String name = "reply";
	
	public ReplyCommand() {
		super(name);
		List<String> a = Arrays.asList("r", "writeback");
		this.description = "Reply to the person you messaged last.";
		this.usageMessage = "/reply <message>";
		this.setAliases(a);
	}
	@Override
	public void register() {
		Plugin.getCommand(name, this);
	}
	@Override
	public boolean execute(CommandSender sndr, String label, String[] args) {
		if (args.length < 1) {
			sndr.sendMessage(Error.NOT_ENOUGH_ARGS.sendError());
			return true;
		}
		String msg = "";
		for (String message : args) {
			msg = (msg + message + " ");
		}
		if (sndr instanceof Player) {
			Player player = (Player) sndr;
			if (Holder.hasPermission(player, Permission.ESS_MESSAGE, Permission.ESS_MESSAGE_CHATCOLOR)) {
			}
			else {
				sndr.sendMessage(Error.NO_PERMISSION.sendError());
				return true;
			}
		}
		Messenger.sendReply(sndr, msg);
		return true;
	}

}

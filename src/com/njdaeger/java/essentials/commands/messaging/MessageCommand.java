package com.njdaeger.java.essentials.commands.messaging;

import java.util.Arrays;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.command.defaults.BukkitCommand;
import org.bukkit.entity.Player;

import com.njdaeger.java.Holder;
import com.njdaeger.java.essentials.enums.Error;
import com.njdaeger.java.essentials.enums.Permission;
import com.njdaeger.java.essentials.utils.Util;
import com.njdaeger.java.essentials.utils.messages.Messenger;

public class MessageCommand extends BukkitCommand {
	
	public MessageCommand() {
		super("message");
		List<String> a = Arrays.asList("msg", "pm", "write", "private");
		this.description = "Send a private message to a player.";
		this.usageMessage = "/message <player> <message>";
		this.setAliases(a);
	}
	@Override
	public boolean execute(CommandSender sndr, String label, String[] args) {
		if (args.length < 2) {
			sndr.sendMessage(Error.NOT_ENOUGH_ARGS.sendError());
			return true;
		}
		Player target = Bukkit.getPlayer(args[0]);
		String msg = "";
		String finalmsg = "";
		for (String message : args) {
			msg = (msg + message + " ");
			String split[] = msg.split(" ", 2);
			finalmsg = split[1];
		}
		if (sndr instanceof Player) {
			Player player = (Player) sndr;
			if (Holder.hasPermission(player, Permission.ESS_MESSAGE, Permission.ESS_MESSAGE_CHATCOLOR)) {
				if (Util.isHidden(target) == true) {
					sndr.sendMessage(Error.UNKNOWN_PLAYER.sendError());
					return true;
				}
				if (Util.allowsMessaging(target) == false) {
					sndr.sendMessage(Error.MESSAGING_DISABLED_TARGET.sendError());
					return true;
				}
				if (Util.allowsMessaging(player) == false) {
					sndr.sendMessage(Error.MESSAGING_DISABLED_SENDER.sendError());
					return true;
				}
				else {
					Messenger.sendPM(target, sndr, finalmsg);
					return true;
				}
			}
			sndr.sendMessage(Error.NO_PERMISSION.sendError());
			return true;
		}
		Messenger.sendPM(target, sndr, finalmsg);
		return true;
	}
}

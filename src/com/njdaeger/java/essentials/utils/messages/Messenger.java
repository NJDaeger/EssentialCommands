package com.njdaeger.java.essentials.utils.messages;

import java.util.HashMap;

import org.bukkit.entity.Player;

import com.njdaeger.java.Core;
import com.njdaeger.java.Holder;
import com.njdaeger.java.configuration.Parser;
import com.njdaeger.java.enums.Error;
import com.njdaeger.java.enums.Permission;
import com.njdaeger.java.wrapper.Sender;
import com.njdaeger.java.wrapper.User;

public class Messenger {

	public static HashMap<String, String> conversation = new HashMap<String, String>();

	/**
	 * Sends a private message to a player or console.
	 * 
	 * @param sndr The message sender.
	 * @param target The target the message is going to.
	 * @param message The message being sent.
	 */
	public static void sendMessage(Sender sndr, String target, String message) {
		if (!sndr.isPlayer()) {
			User player = Core.getUser(target);
			if (target.equalsIgnoreCase("console") && player == null) {
				conversation.put(sndr.getName(), target);
				conversation.put(target, sndr.getName());
				new Message(sndr, true, message);
				return;
			}
			if (player == null) {
				sndr.sendMessage(Error.UNKNOWN_PLAYER.sendError());
				return;
			}
			if (!player.isMessageable()) {
				sndr.sendError(Error.CANNOT_SEND_PM);
				return;
			}
			conversation.put(sndr.getName(), player.getName());
			conversation.put(player.getName(), sndr.getName());
			new Message(sndr, player, true, message);
			return;
		}
		User player = Core.getUser(target);
		if (target.equalsIgnoreCase("console") && player == null) {
			if (Holder.hasPermission(sndr, Permission.ESS_MESSAGE_CONSOLE)) {
				conversation.put(sndr.getName(), target);
				conversation.put(target, sndr.getName());
				if (Holder.hasPermission(sndr, Permission.ESS_MESSAGE_CHATCOLOR)) {
					new Message(sndr, true, message);
					return;
				}
				new Message(sndr, false, message);
				return;
			}
			sndr.sendMessage(Parser.parse(Error.NO_PERMISSION.getError(), (Player) sndr, "Unknown",
					Permission.ESS_MESSAGE_CONSOLE));
			return;
		}
		if (player == null) {
			sndr.sendMessage(Error.UNKNOWN_PLAYER.sendError());
			return;
		}
		if (!player.isMessageable()) {
			sndr.sendError(Error.CANNOT_SEND_PM);
			return;
		}
		conversation.put(sndr.getName(), target);
		conversation.put(target, sndr.getName());
		if (Holder.hasPermission(sndr, Permission.ESS_MESSAGE_CHATCOLOR)) {
			new Message(sndr, player, true, message);
			return;
		}
		new Message(sndr, player, false, message);
		return;
	}

	/*
	 * Add checks for disabled messaing.
	 */

	public static void sendReply(Sender sender, String message) {
		if (conversation.get(sender.getName()) == null) {
			sender.sendMessage(Error.throwError("No messages have been sent to anyone recently."));
			return;
		}
		if (!sender.isPlayer()) {
			User player = Core.getUser(conversation.get(sender.getName()));
			conversation.put(sender.getName(), conversation.get(sender.getName()));
			conversation.put(conversation.get(sender.getName()), sender.getName());
			if (player == null) {
				if (conversation.get(sender.getName()).equalsIgnoreCase("CONSOLE")) {
					new Message(sender, true, message);
					return;
				}
				sender.sendMessage(Error.CANNOT_SEND_PM.sendError());
				return;
			}
			if (!player.isMessageable()) {
				sender.sendError(Error.CANNOT_SEND_PM);
				return;
			}
			new Message(sender, player, true, message);
			return;
		}
		User player = Core.getUser(conversation.get(sender.getName()));
		boolean a = false;
		if (Holder.hasPermission(sender, Permission.ESS_MESSAGE_CHATCOLOR)) {
			a = true;
		}
		conversation.put(sender.getName(), conversation.get(sender.getName()));
		conversation.put(conversation.get(sender.getName()), sender.getName());
		if (player == null) {
			if (conversation.get(sender.getName()).equalsIgnoreCase("CONSOLE")) {
				if (Holder.hasPermission(sender, Permission.ESS_MESSAGE_CONSOLE)) {
					new Message(sender, a, message);
					return;
				}
				sender.sendMessage(Parser.parse(Error.NO_PERMISSION.getError(), (Player) sender, "Unknown",
						Permission.ESS_MESSAGE_CONSOLE));
				return;
			}
			sender.sendMessage(Error.CANNOT_SEND_PM.sendError());
			return;
		}
		if (!player.isMessageable()) {
			sender.sendError(Error.CANNOT_SEND_PM);
			return;
		}
		new Message(sender, player, a, message);
		return;
	}
}

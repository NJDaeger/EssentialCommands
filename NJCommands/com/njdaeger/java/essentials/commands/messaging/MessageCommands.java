package com.njdaeger.java.essentials.commands.messaging;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

import com.njdaeger.java.Core;
import com.njdaeger.java.command.util.commands.Cmd;
import com.njdaeger.java.command.util.commands.Executor;
import com.njdaeger.java.enums.Error;
import com.njdaeger.java.enums.Permission;
import com.njdaeger.java.essentials.utils.messages.Messenger;
import com.njdaeger.java.wrapper.Sender;
import com.njdaeger.java.wrapper.User;

public class MessageCommands {

	/**
	 * 
	 * 
	 * REPLY COMMAND
	 * 
	 * 
	 */
	@Cmd(
		name = "reply",
		desc = "Reply to the person you messaged last.",
		usage = "/reply <message>",
		min = 1,
		aliases = { "r", "writeback" },
		permissions = { Permission.ESS_MESSAGE })
	public void reply(Sender sndr, String label, String[] args) {
		String msg = "";
		for (String message : args) {
			msg = (msg + message + " ");
		}
		Messenger.sendReply(sndr, msg);
		return;
	}

	/**
	 * 
	 * 
	 * BROADCAST COMMAND
	 * 
	 * 
	 */
	@Cmd(
		name = "broadcast",
		desc = "Send a message to the entire server.",
		usage = "/broadcast <message>",
		min = 1,
		aliases = { "bc", "servermessage", "announce" },
		permissions = { Permission.ESS_BROADCAST, Permission.ESS_BROADCAST_COLOR })
	public void broadcast(Sender sndr, String label, String[] args) {
		boolean color = sndr.hasPermission(Permission.ESS_BROADCAST_COLOR);
		String message = "";
		for (String bc : args) {
			message = (message + bc + " ");
		}
		if (color) {
			Bukkit.broadcastMessage(ChatColor.GOLD + "[" + ChatColor.DARK_RED + "Broadcast" + ChatColor.GOLD + "] "
					+ ChatColor.GREEN + ChatColor.translateAlternateColorCodes('&', message));
			return;
		}
		Bukkit.broadcastMessage(ChatColor.GOLD + "[" + ChatColor.DARK_RED + "Broadcast" + ChatColor.GOLD + "] "
				+ ChatColor.GREEN + message);
		return;
	}

	/**
	 * 
	 * 
	 * ME COMMAND
	 * 
	 * 
	 */
	@Cmd(
		name = "me",
		desc = "Describe what you're doing.",
		usage = "/me <message>",
		min = 1,
		executor = { Executor.PLAYER },
		permissions = { Permission.ESS_ME, Permission.ESS_ME_CHATCOLOR })
	public void me(Sender sndr, String label, String[] args) {
		boolean color = sndr.hasPermission(Permission.ESS_ME_CHATCOLOR);
		String me = "";
		for (String message : args) {
			me = (me + message + " ");
			if (color) {
				Bukkit.broadcastMessage(ChatColor.DARK_PURPLE + "* " + ChatColor.RESET + (sndr.asPlayer())
						.getDisplayName() + " " + ChatColor.GRAY + ChatColor.translateAlternateColorCodes('&', me));
				return;
			}
			Bukkit.broadcastMessage(ChatColor.DARK_PURPLE + "* " + ChatColor.RESET + (sndr.asPlayer()).getDisplayName()
					+ " " + ChatColor.GRAY + me);

		}
		return;
	}

	/**
	 * 
	 * 
	 * MESSAGE COMMAND
	 * 
	 * 
	 */
	@Cmd(
		name = "message",
		desc = "Send a private message to a player.",
		usage = "/message <player> <message>",
		min = 2,
		aliases = { "msg", "pm", "write", "private" },
		permissions = { Permission.ESS_MESSAGE })
	public void message(Sender sndr, String label, String[] args) {
		String msg = "";
		String finalmsg = "";
		for (String message : args) {
			msg = (msg + message + " ");
			String split[] = msg.split(" ", 2);
			finalmsg = split[1];
		}
		Messenger.sendMessage(sndr, args[0], finalmsg);
		return;
	}

	/**
	 * 
	 * 
	 * DISABLE MESSAGING COMMAND
	 * 
	 * 
	 */
	@Cmd(
		name = "msgtoggle",
		desc = "Disable incoming private messages.",
		usage = "/msgtoggle [player]",
		max = 1,
		aliases = { "disablemsg", "nomsg", "togglemsg" },
		permissions = Permission.ESS_DISABLE_MSG)
	public void disablemsg(Sender sender, String label, String[] args) {
		if (args.length == 1) {
			User user = Core.getUser(args[0]);
			if (user == null) {
				sender.sendError(Error.UNKNOWN_PLAYER);
				return;
			}
			boolean val = !user.isMessageable();
			String msg = ChatColor.GRAY + "Private messaging is disabled.";
			user.setMessageable(val);
			if (val) {
				msg = ChatColor.GRAY + "Private messaging is enabled.";
			}
			user.sendMessage(msg);
			return;
		}
		if (!sender.isUser()) {
			sender.sendError(Error.NOT_ENOUGH_ARGS);
			return;
		}
		sender.asUser().setMessageable(!sender.asUser().isMessageable());
		boolean val = !sender.asUser().isMessageable();
		String msg = ChatColor.GRAY + "Private messaging is disabled.";
		sender.asUser().setMessageable(val);
		if (val) {
			msg = ChatColor.GRAY + "Private messaging is enabled.";
		}
		sender.sendMessage(msg);
		return;
	}

	/**
	 * 
	 * 
	 * SOCIALSPY COMMAND
	 * 
	 * 
	 */
	@Cmd(
		name = "socialspy",
		desc = "Spy on player command executions.",
		usage = "/socialspy [player]",
		max = 1,
		aliases = { "ss" },
		permissions = { Permission.ESS_SOCIALSPY, Permission.ESS_SOCIALSPY_OTHER })
	public void socialspy(Sender sender, String label, String[] args) {
		if (args.length == 0) {
			if (!sender.isUser()) {
				sender.sendError(Error.NOT_ENOUGH_ARGS);
				return;
			}
			User user = sender.asUser();
			if (user.isSpying()) {
				sender.asUser().setSpying(false);
				sender.sendMessage(ChatColor.GRAY + "Socialspy is now disabled.");
				return;
			}
			sender.asUser().setSpying(true);
			sender.sendMessage(ChatColor.GRAY + "Socialspy is now enabled.");
			return;
		}
		User user = Core.getUser(args[0]);
		if (user == null) {
			sender.sendError(Error.UNKNOWN_PLAYER);
			return;
		}
		if (user.isSpying()) {
			user.setSpying(false);
			user.sendMessage(ChatColor.GRAY + "Socialspy is now disabled.");
			sender.sendMessage(ChatColor.GRAY + "Socialspy is now disabled for " + ChatColor.GREEN + user
					.getNickname());
			return;
		}
		user.setSpying(true);
		user.sendMessage(ChatColor.GRAY + "Socialspy is now enabled.");
		sender.sendMessage(ChatColor.GRAY + "Socialspy is now enabled for " + ChatColor.GREEN + user.getNickname());
		return;
	}

	/**
	 * HELPOP COMMAND
	 */
	@Cmd(
		name = "helpop",
		desc = "Message online admins.",
		usage = "/helpop <message>",
		min = 1,
		aliases = { "admins", "staff" },
		permissions = { Permission.ESS_HELPOP, Permission.ESS_HELPOP_RECEIVE })
	public void helpop(Sender sndr, String label, String[] args) {
		String message = "";
		for (String msg : args) {
			message += msg;
		}
		Bukkit.broadcast(ChatColor.GRAY + "[" + ChatColor.DARK_RED + "HELPOP" + ChatColor.GRAY + "] " + sndr.getName()
				+ ": " + ChatColor.BLUE + message, Permission.ESS_HELPOP_RECEIVE.getPermission());
		if (!sndr.hasPermission(Permission.ESS_HELPOP_RECEIVE.getPermission())) {
			sndr.sendMessage(ChatColor.GRAY + "[" + ChatColor.DARK_RED + "HELPOP" + ChatColor.GRAY + "] " + sndr
					.getName() + ": " + ChatColor.BLUE + message);
		}
		return;
	}
}

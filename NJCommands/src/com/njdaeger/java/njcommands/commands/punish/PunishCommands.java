package com.njdaeger.java.njcommands.commands.punish;

import com.njdaeger.java.Core;
import com.njdaeger.java.command.util.commands.Cmd;
import com.njdaeger.java.enums.Error;
import com.njdaeger.java.enums.Permission;
import com.njdaeger.java.wrapper.Sender;
import com.njdaeger.java.wrapper.User;

import net.md_5.bungee.api.ChatColor;

public class PunishCommands {

	/**
	 * 
	 * 
	 * BAN COMMAND
	 * 
	 * 
	 */
	@Cmd(
		name = "ban",
		desc = "Ban a player from the server.",
		usage = "/ban <player> [reason]",
		min = 1,
		aliases = { "perm", "banhammer" },
		permissions = { Permission.ESS_BAN })
	public void ban(Sender sndr, String label, String[] args) {
		User user = Core.getUser(args[0]);
		if (user == null) {
			sndr.sendMessage(Error.UNKNOWN_PLAYER.sendError());
			return;
		}
		if (user.hasPermission(Permission.ESS_BAN_EXEMPT) && sndr.isUser()) {
			sndr.sendMessage(Error.CANNOT_BAN_TARGET.sendError());
			return;
		}
		if (args.length == 1) {
			Core.getBanAPI().addBan(user.getName(), sndr.asCommandSender(), null, null);
			user.kick("You have been banned.");
			return;
		}
		StringBuilder builder = new StringBuilder();
		for (int i = 1; i < args.length; i++)
			builder.append(args[i]).append(' ');

		String reason = builder.toString();
		Core.getBanAPI().addBan(user.getName(), sndr.asCommandSender(), null, reason);
		user.kick("You have been banned for " + reason);
		return;
	}

	/**
	 * 
	 * 
	 * KICKALL COMMAND
	 * 
	 * 
	 */
	@Cmd(
		name = "kickall",
		desc = "Kick all players from the server.",
		usage = "/kickall [reason]",
		permissions = { Permission.ESS_KICKALL })
	public void kickall(Sender sndr, String label, String[] args) {
		if (Core.getOnlineUsers().isEmpty()) {
			sndr.sendMessage(Error.NO_PLAYERS_ONLINE.sendError());
			return;
		}
		if (args.length == 0) {
			for (User players : Core.getOnlineUsers()) {
				if (!players.hasPermission(Permission.ESS_KICK_EXEMPT)) {
					players.kick(sndr.getName() + " kicked all players.");
				}
			}
			return;
		}
		String reason = "";
		for (String msg : args) {
			reason += msg;
		}
		for (User players : Core.getOnlineUsers()) {
			players.kick(ChatColor.translateAlternateColorCodes('&', reason));
		}
		return;
	}

	/**
	 * KICK COMMAND
	 */
	@Cmd(
		name = "kick",
		desc = "Kick a player from the server.",
		usage = "/kick <player> [reason]",
		min = 1,
		permissions = { Permission.ESS_KICK })
	public void kick(Sender sender, String alias, String[] args) {
		if (args.length == 1) {
			User user = Core.getUser(args[0]);
			user.kick();
			return;
		}
		String reason = "";
		for (String msg : args) {
			reason += msg;
		}
		User user = Core.getUser(args[0]);
		user.kick(reason);

	}

	/**
	 * 
	 * 
	 * TEMPBAN COMMAND
	 * 
	 * 
	 */
	@Cmd(
		name = "tempban",
		desc = "Temp ban a player.",
		usage = "/tempban <player> <time:<d/h/m/s>> [reason...]",
		min = 2,
		aliases = { "temp", "tb", "bantemp" },
		permissions = { Permission.ESS_TEMPBAN })
	public void tempban(Sender sndr, String label, String[] args) {
		User target = Core.getUser(args[1]);
		if (target == null) {
			sndr.sendMessage(Error.UNKNOWN_PLAYER.sendError());
			return;
		}
		if (target.equals(sndr.asUser()) || target.hasPermission(Permission.ESS_BAN_EXEMPT)) {
			sndr.sendMessage(Error.CANNOT_BAN_TARGET.sendError());
			return;
		}
		switch (args.length) {
		case 2:
			Core.getBanAPI().addBan(target.getName(), sndr.asCommandSender(), args[1], null);
			target.kick("You have been temp banned.");
			return;
		default:
			StringBuilder builder = new StringBuilder();
			for (int i = 2; i < args.length; i++)
				builder.append(args[i]).append(' ');
			String reason = builder.toString();
			Core.getBanAPI().addBan(target.getName(), sndr.asCommandSender(), args[1], ChatColor
					.translateAlternateColorCodes('&', reason));
			target.kick("You have been temp banned for " + reason);
			return;
		}
	}

	/**
	 * 
	 * 
	 * UNBAN COMMAND
	 * 
	 * 
	 */
	@Cmd(
		name = "unban",
		desc = "Unban a banned player.",
		usage = "/unban <player>",
		min = 1,
		max = 1,
		aliases = { "pardon", "removeban" },
		permissions = { Permission.ESS_UNBAN })
	public void unban(Sender sndr, String label, String[] args) {
		if (!Core.getBanAPI().isBanned(args[0])) {
			sndr.sendMessage(Error.PLAYER_NOT_BANNED.sendError());
			return;
		}
		Core.getBanAPI().unban(args[0]);
		sndr.sendMessage(ChatColor.GRAY + "Successfully unbanned " + ChatColor.GREEN + args[0]);
		return;
	}

}

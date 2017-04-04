package com.njdaeger.java.njcommands.commands.player;

import org.bukkit.ChatColor;

import com.njdaeger.java.Core;
import com.njdaeger.java.command.util.commands.Cmd;
import com.njdaeger.java.enums.Error;
import com.njdaeger.java.enums.Permission;
import com.njdaeger.java.utils.Util;
import com.njdaeger.java.wrapper.Sender;
import com.njdaeger.java.wrapper.User;

public class PlayerEnvironmentCommands {

	/**
	 * 
	 * 
	 * PLAYER TIME COMMAND
	 * 
	 * 
	 */
	@Cmd(
		name = "ptime",
		desc = "Set a players client time.",
		usage = "/ptime <timeunit> [player]",
		min = 1,
		max = 2,
		aliases = { "playertime" },
		permissions = { Permission.ESS_PTIME, Permission.ESS_PTIME_OTHER })
	public void ptime(Sender sender, String label, String[] args) {
		if (args.length == 1) {
			if (!sender.isPlayer()) {
				sender.sendMessage(Error.NOT_ENOUGH_ARGS.sendError());
				return;
			}
			TimeUnit unit = TimeUnit.getAliasUsed(args[0]);
			User user = sender.asUser();
			if (unit != null) {
				user.getBase().setPlayerTime(unit.getTime(), false);
				user.getBase().sendMessage(ChatColor.GRAY + "Client time changed to " + ChatColor.GREEN + args[0]);
				return;
			}
			setTime(sender, user, args[0]);
			return;
		}
		TimeUnit unit = TimeUnit.getAliasUsed(args[0]);
		User user = Core.getUser(args[1]);
		if (user == null) {
			sender.sendMessage(Error.UNKNOWN_PLAYER.sendError());
			return;
		}
		if (unit != null) {
			user.getBase().setPlayerTime(unit.getTime(), false);
			user.getBase().sendMessage(ChatColor.GRAY + "Client time changed to " + ChatColor.GREEN + args[0]);
			return;
		}
		setTime(sender, user, args[0]);
		return;
	}

	private void setTime(Sender sender, User user, String input) {
		if (input.startsWith("@") || input.endsWith("ticks") || input.equalsIgnoreCase("reset")) {
			String[] a;
			if (input.startsWith("@")) {
				a = input.split("@");
				if (Util.isNumber(a[1])) {
					user.getBase().setPlayerTime(Long.parseLong(a[1]), false);
					user.getBase().sendMessage(ChatColor.GRAY + "Client time changed to " + ChatColor.GREEN + a[1]
							+ " ticks");
					return;
				}
				sender.sendMessage(Error.INPUT_NOT_NUM.sendError());
				return;
			}
			if (input.endsWith("ticks")) {
				a = input.split("test");
				if (Util.isNumber(a[0])) {
					user.getBase().setPlayerTime(Long.parseLong(a[0]), false);
					user.getBase().sendMessage(ChatColor.GRAY + "Client time changed to " + ChatColor.GREEN + a[0]
							+ " ticks");
					return;
				}
				sender.sendMessage(Error.INPUT_NOT_NUM.sendError());
				return;

			}
			user.getBase().resetPlayerTime();
			return;
		}
		sender.sendMessage(Error.CANNOT_CHANGE_TO_TIME.sendError());
		return;
	}

	/**
	 * 
	 * 
	 * PLAYER WEATHER COMMAND
	 * 
	 * 
	 */
	@Cmd(
		name = "pweather",
		desc = "Set your current client weather.",
		usage = "/pweather <weatherType> [player]",
		min = 1,
		max = 2,
		aliases = { "playerwather", "myweather" },
		permissions = { Permission.ESS_PWEATHER, Permission.ESS_PWEATHER_OTHER })
	public void pweather(Sender sender, String label, String[] args) {
		WeatherUnit unit = WeatherUnit.getAliasUsed(args[0]);
		if (unit == null) {
			sender.sendError(Error.CANNOT_CHANGE_WEATHER);
			return;
		}
		if (args.length == 1) {
			if (!sender.isPlayer()) {
				sender.sendMessage(Error.NOT_ENOUGH_ARGS.sendError());
				return;
			}
			sender.asPlayer().setPlayerWeather(unit.getType());
			sender.sendMessage(ChatColor.GRAY + "Client weather changed to " + ChatColor.GREEN + args[0]);
			return;
		}
		if (!sender.hasPermission(Permission.ESS_PWEATHER_OTHER)) {
			sender.sendError(Error.NO_PERMISSION);
			return;
		}
		User user = Core.getUser(args[1]);
		if (user == null) {
			sender.sendMessage(Error.UNKNOWN_PLAYER.sendError());
			return;
		}
		user.getBase().setPlayerWeather(unit.getType());
		user.getBase().sendMessage(ChatColor.GRAY + "Client weather changed to " + ChatColor.GREEN + args[0]);
		return;
	}

}

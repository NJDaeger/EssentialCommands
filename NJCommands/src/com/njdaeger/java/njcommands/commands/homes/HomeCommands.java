package com.njdaeger.java.njcommands.commands.homes;

import org.bukkit.ChatColor;

import com.njdaeger.java.Core;
import com.njdaeger.java.Holder;
import com.njdaeger.java.command.util.commands.Cmd;
import com.njdaeger.java.command.util.commands.Executor;
import com.njdaeger.java.configuration.Parser;
import com.njdaeger.java.configuration.data.Home;
import com.njdaeger.java.configuration.data.OfflineHome;
import com.njdaeger.java.enums.Error;
import com.njdaeger.java.enums.Permission;
import com.njdaeger.java.wrapper.OfflineUser;
import com.njdaeger.java.wrapper.Sender;
import com.njdaeger.java.wrapper.User;

public class HomeCommands {

	/**
	 * 
	 * 
	 * DELETE HOME COMMAND
	 * 
	 * 
	 */
	@Cmd(
		name = "delhome",
		desc = "Delete an existing home.",
		usage = "/delhome <homename> [player]",
		max = 2,
		min = 1,
		completer = true,
		aliases = { "deletehome", "removehome", "clearhome" },
		permissions = { Permission.ESS_DELHOME, Permission.ESS_DELHOME_OTHER })
	public void delhome(Sender sndr, String label, String[] args) {
		if (args.length == 1) {
			if (sndr.isUser()) {
				User user = sndr.asUser();
				if (!user.getHome(args[0]).exists()) {
					sndr.sendMessage(Error.HOME_NOTEXIST.sendError());
					sndr.sendMessage(ChatColor.GRAY + "Current homes: " + ChatColor.GREEN + user.listHomes());
					return;
				}
				user.getHome(args[0]).remove();
				sndr.sendMessage(ChatColor.GRAY + "Removed home " + ChatColor.GREEN + args[0] + ChatColor.GRAY
						+ " from homes.");
				return;
			}
			sndr.sendMessage(Error.NOT_ENOUGH_ARGS.sendError());
			return;
		}
		if (!sndr.hasPermission(Permission.ESS_DELHOME_OTHER)) {
			sndr.sendMessage(Parser.parse(Error.NO_PERMISSION.getError(), sndr.asPlayer(), "Unknown",
					Permission.ESS_DELHOME_OTHER));
			return;
		}
		User target = Core.getUser(args[1]);
		if (target == null) {
			OfflineUser user = Core.getOfflineUser(args[1]);
			if (user == null) {
				sndr.sendMessage(Error.UNKNOWN_PLAYER.sendError());
				return;
			}

			if (user.getHome(args[0]).exists()) {
				sndr.sendMessage(Error.HOME_NOTEXIST.sendError());
				sndr.sendMessage(ChatColor.GRAY + "Current homes: " + ChatColor.GREEN + user.listHomes());
				return;
			}
			user.getHome(args[0]).remove();
			sndr.sendMessage(ChatColor.GRAY + "Removed home " + ChatColor.GREEN + args[0] + ChatColor.GRAY + " from "
					+ args[1] + "'s homes.");
			return;
		}
		if (!target.getHome(args[0]).exists()) {
			sndr.sendMessage(Error.HOME_NOTEXIST.sendError());
			sndr.sendMessage(ChatColor.GRAY + "Current homes: " + ChatColor.GREEN + target.listHomes());
			return;
		}
		target.getHome(args[0]).remove();
		sndr.sendMessage(ChatColor.GRAY + "Removed home " + ChatColor.GREEN + args[0] + ChatColor.GRAY
				+ " from homes.");
		return;
	}

	/**
	 * 
	 * 
	 * TELEPORT HOME COMMAND
	 * 
	 * 
	 */
	@Cmd(
		name = "home",
		desc = "Go to a saved home.",
		usage = "/home <homename> <player>",
		max = 2,
		min = 1,
		executor = { Executor.PLAYER },
		aliases = { "gohome", "tphome", "tohome" },
		permissions = { Permission.ESS_HOME, Permission.ESS_HOME_OTHER })
	public void home(Sender sndr, String label, String[] args) {
		User user = sndr.asUser();
		if (args.length == 1) {
			Home home = (Home) user.getHome(args[0]);
			if (!home.exists()) {
				sndr.sendMessage(Error.HOME_NOTEXIST.sendError());
				return;
			}
			home.sendHome();
			sndr.sendMessage(ChatColor.GRAY + "You have successfully been sent to " + ChatColor.GREEN + args[0]);
			return;
		}
		if (sndr.hasPermission(Permission.ESS_HOME_OTHER)) {
			User target = Core.getUser(args[1]);
			if (target == null) {
				OfflineUser otarget = Core.getOfflineUser(args[1]);
				if (otarget == null) {
					sndr.sendMessage(Error.UNKNOWN_PLAYER.sendError());
					return;
				}
				OfflineHome home = (OfflineHome) otarget.getHome(args[0]);
				if (!home.exists()) {
					sndr.sendMessage(Error.HOME_NOTEXIST.sendError());
					return;
				}
				home.sendOtherHome(user);
				sndr.sendMessage(ChatColor.GRAY + "You have successfully been sent to " + ChatColor.GREEN + args[0]);
				return;
			}
			Home home = (Home) target.getHome(args[0]);
			if (!home.exists()) {
				sndr.sendMessage(Error.HOME_NOTEXIST.sendError());
				return;
			}
			home.sendOtherHome(user);
			sndr.sendMessage(ChatColor.GRAY + "You have successfully been sent to " + ChatColor.GREEN + args[0]);
			return;

		}
		sndr.sendMessage(Parser.parse(Error.NO_PERMISSION.getError(), sndr.asPlayer(), "Unknown",
				Permission.ESS_HOME_OTHER));
		return;
	}

	/**
	 * 
	 * 
	 * LISTHOMES COMMAND
	 * 
	 * 
	 */
	@Cmd(
		name = "homes",
		desc = "Gets a list of your homes.",
		usage = "/homes [player] [home]",
		max = 2,
		completer = true,
		aliases = { "listhomes", "seehomes", "homelist" },
		permissions = { Permission.ESS_LISTHOMES, Permission.ESS_LISTHOMES_OTHER, Permission.ESS_LISTHOMES_DETAIL })
	public void listhomes(Sender sndr, String label, String[] args) {
		switch (args.length) {
		case 0:
			if (sndr.isUser()) {
				User user = sndr.asUser();
				if (user.listHomes() == null) {
					sndr.sendMessage(Error.NO_HOMES.sendError());
					return;
				}
				sndr.sendMessage(ChatColor.GRAY + "Current homes: " + ChatColor.GREEN + user.listHomes());
				return;
			}
			sndr.sendMessage(Error.NOT_ENOUGH_ARGS.sendError());
			return;
		case 1:
			if (sndr.hasPermission(Permission.ESS_LISTHOMES_OTHER)) {
				User target = Core.getUser(args[0]);
				if (target == null) {
					OfflineUser otarget = Core.getOfflineUser(args[0]);
					if (otarget == null) {
						sndr.sendMessage(Error.UNKNOWN_PLAYER.sendError());
						return;
					}
					if (otarget.listHomes() == null) {
						sndr.sendMessage(Error.NO_HOMES.sendError());
						return;
					}
					sndr.sendMessage(ChatColor.GRAY + args[0] + "'s current homes: " + ChatColor.GREEN + otarget
							.listHomes());
					return;
				}
				if (target.listHomes() == null) {
					sndr.sendMessage(Error.NO_HOMES.sendError());
					return;
				}
				sndr.sendMessage(ChatColor.GRAY + args[0] + "'s current homes: " + ChatColor.GREEN + target
						.listHomes());
				return;
			}
			sndr.sendMessage(Parser.parse(Error.NO_PERMISSION.getError(), sndr.asPlayer(), "Unknown",
					Permission.ESS_LISTHOMES_OTHER));
			return;
		default:
			if (Holder.hasPermission(sndr, Permission.ESS_LISTHOMES_DETAIL)) {
				User target = Core.getUser(args[0]);
				if (target == null) {
					OfflineUser otarget = Core.getOfflineUser(args[0]);
					OfflineHome home;
					if (otarget == null) {
						sndr.sendMessage(Error.UNKNOWN_PLAYER.sendError());
						return;
					}
					home = (OfflineHome) otarget.getHome(args[1]);
					if (!home.exists()) {
						sndr.sendMessage(Error.HOME_NOTEXIST.sendError());
						return;
					}
					sndr.sendMessage(ChatColor.GRAY + "Home \"" + args[1] + "\" information.");
					sndr.sendMessage(ChatColor.GRAY + "x: " + ChatColor.GREEN + home.getX());
					sndr.sendMessage(ChatColor.GRAY + "y: " + ChatColor.GREEN + home.getY());
					sndr.sendMessage(ChatColor.GRAY + "z: " + ChatColor.GREEN + home.getZ());
					sndr.sendMessage(ChatColor.GRAY + "yaw: " + ChatColor.GREEN + home.getYaw());
					sndr.sendMessage(ChatColor.GRAY + "pitch: " + ChatColor.GREEN + home.getPitch());
					sndr.sendMessage(ChatColor.GRAY + "world: " + ChatColor.GREEN + home.getWorld());
					return;
				}
				if (!target.getHome(args[0]).exists()) {
					sndr.sendMessage(Error.HOME_NOTEXIST.sendError());
					return;
				}
				Home home = new Home(target, args[1]);
				sndr.sendMessage(ChatColor.GRAY + "Home \"" + args[1] + "\" information.");
				sndr.sendMessage(ChatColor.GRAY + "x: " + ChatColor.GREEN + home.getX());
				sndr.sendMessage(ChatColor.GRAY + "y: " + ChatColor.GREEN + home.getY());
				sndr.sendMessage(ChatColor.GRAY + "z: " + ChatColor.GREEN + home.getZ());
				sndr.sendMessage(ChatColor.GRAY + "yaw: " + ChatColor.GREEN + home.getYaw());
				sndr.sendMessage(ChatColor.GRAY + "pitch: " + ChatColor.GREEN + home.getPitch());
				sndr.sendMessage(ChatColor.GRAY + "world: " + ChatColor.GREEN + home.getWorld());
				return;
			}
			sndr.sendMessage(Parser.parse(Error.NO_PERMISSION.getError(), sndr.asPlayer(), "Unknown",
					Permission.ESS_LISTHOMES_OTHER));
			return;
		}
	}

	/**
	 * 
	 * 
	 * CREATEHOME COMMAND
	 * 
	 * 
	 */
	@Cmd(
		name = "sethome",
		desc = "Create a new home.",
		usage = "/sethome <name>",
		min = 1,
		max = 1,
		executor = Executor.PLAYER,
		aliases = { "newhome", "addhome" },
		permissions = { Permission.ESS_SETHOME })
	public void sethome(Sender sndr, String label, String[] args) {
		User user = sndr.asUser();
		Home home = (Home) user.getHome(args[0]);
		if (home.exists()) {
			sndr.sendMessage(Error.HOME_EXISTS.sendError());
			sndr.sendMessage(ChatColor.GRAY + "Current homes: " + ChatColor.GREEN + user.listHomes());
			return;
		}
		home.create();
		sndr.sendMessage(ChatColor.GRAY + "Created a new home at " + ChatColor.GREEN + "x:" + home.getX() + " y:" + home
				.getY() + " z:" + home.getZ());
		return;
	}

}

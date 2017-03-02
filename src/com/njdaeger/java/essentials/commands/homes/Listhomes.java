package com.njdaeger.java.essentials.commands.homes;

import com.njdaeger.java.Core;
import com.njdaeger.java.Holder;
import com.njdaeger.java.command.util.Cmd;
import com.njdaeger.java.command.util.EssCommand;
import com.njdaeger.java.configuration.Parser;
import com.njdaeger.java.essentials.enums.Error;
import com.njdaeger.java.essentials.enums.Permission;
import com.njdaeger.java.wrapper.Sender;
import com.njdaeger.java.wrapper.User;

import net.md_5.bungee.api.ChatColor;

public class Listhomes extends EssCommand {

	@Override
	@Cmd(
			name = "homes",
			desc = "Gets a list of your homes.",
			usage = "/homes [player] [home]",
			max = 2,
			aliases = { "listhomes", "seehomes", "homelist" },
			permissions = { Permission.ESS_LISTHOMES, Permission.ESS_LISTHOMES_OTHER, Permission.ESS_LISTHOMES_DETAIL })
	public boolean run(Sender sndr, String label, String[] args) {
		switch (args.length) {
		case 0:
			if (sndr.isUser()) {
				User user = sndr.asUser();
				if (user.getHome(null).listHomes() == null) {
					sndr.sendMessage(Error.NO_HOMES.sendError());
					return true;
				}
				sndr.sendMessage(ChatColor.GRAY + "Current homes: " + ChatColor.GREEN + user.getHome(null).listHomes());
				return true;
			}
			sndr.sendMessage(Error.NOT_ENOUGH_ARGS.sendError());
			return true;
		case 1:
			if (sndr.hasPermission(Permission.ESS_LISTHOMES_OTHER)) {
				User target = Core.getUser(args[0]);
				if (target == null) {
					User otarget = Core.getOfflineUser(args[0]);
					if (otarget == null) {
						sndr.sendMessage(Error.UNKNOWN_PLAYER.sendError());
						return true;
					}
					if (otarget.getHome(null).listHomes() == null) {
						sndr.sendMessage(Error.NO_HOMES.sendError());
						return true;
					}
					sndr.sendMessage(ChatColor.GRAY + args[0] + "'s current homes: " + ChatColor.GREEN + otarget
							.getHome(null).listHomes());
					return true;
				}
				if (target.getHome(null).listHomes() == null) {
					sndr.sendMessage(Error.NO_HOMES.sendError());
					return true;
				}
				sndr.sendMessage(ChatColor.GRAY + args[0] + "'s current homes: " + ChatColor.GREEN + target.getHome(
						null).listHomes());
				return true;
			}
			sndr.sendMessage(Parser.parse(Error.NO_PERMISSION.getError(), sndr.asPlayer(), "Unknown",
					Permission.ESS_LISTHOMES_OTHER));
			return true;
		default:
			if (Holder.hasPermission(sndr, Permission.ESS_LISTHOMES_DETAIL)) {
				User target = Core.getUser(args[0]);
				if (target == null) {
					User otarget = Core.getOfflineUser(args[0]);
					com.njdaeger.java.configuration.data.Home home;
					if (otarget == null) {
						sndr.sendMessage(Error.UNKNOWN_PLAYER.sendError());
						return true;
					}
					if (!otarget.getHome(args[0]).exists()) {
						sndr.sendMessage(Error.HOME_NOTEXIST.sendError());
						return true;
					}
					home = new com.njdaeger.java.configuration.data.Home(otarget, args[1]);
					sndr.sendMessage(ChatColor.GRAY + "Home \"" + args[1] + "\" information.");
					sndr.sendMessage(ChatColor.GRAY + "x: " + ChatColor.GREEN + home.getX());
					sndr.sendMessage(ChatColor.GRAY + "y: " + ChatColor.GREEN + home.getY());
					sndr.sendMessage(ChatColor.GRAY + "z: " + ChatColor.GREEN + home.getZ());
					sndr.sendMessage(ChatColor.GRAY + "yaw: " + ChatColor.GREEN + home.getYaw());
					sndr.sendMessage(ChatColor.GRAY + "pitch: " + ChatColor.GREEN + home.getPitch());
					sndr.sendMessage(ChatColor.GRAY + "world: " + ChatColor.GREEN + home.getWorld());
					return true;
				}
				if (!target.getHome(args[0]).exists()) {
					sndr.sendMessage(Error.HOME_NOTEXIST.sendError());
					return true;
				}
				com.njdaeger.java.configuration.data.Home home = new com.njdaeger.java.configuration.data.Home(target,
						args[1]);
				sndr.sendMessage(ChatColor.GRAY + "Home \"" + args[1] + "\" information.");
				sndr.sendMessage(ChatColor.GRAY + "x: " + ChatColor.GREEN + home.getX());
				sndr.sendMessage(ChatColor.GRAY + "y: " + ChatColor.GREEN + home.getY());
				sndr.sendMessage(ChatColor.GRAY + "z: " + ChatColor.GREEN + home.getZ());
				sndr.sendMessage(ChatColor.GRAY + "yaw: " + ChatColor.GREEN + home.getYaw());
				sndr.sendMessage(ChatColor.GRAY + "pitch: " + ChatColor.GREEN + home.getPitch());
				sndr.sendMessage(ChatColor.GRAY + "world: " + ChatColor.GREEN + home.getWorld());
				return true;
			}
			sndr.sendMessage(Parser.parse(Error.NO_PERMISSION.getError(), sndr.asPlayer(), "Unknown",
					Permission.ESS_LISTHOMES_OTHER));
			return true;
		}
	}
}
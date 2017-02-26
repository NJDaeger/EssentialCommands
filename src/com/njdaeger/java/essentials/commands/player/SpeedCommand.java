package com.njdaeger.java.essentials.commands.player;

import org.bukkit.ChatColor;

import com.njdaeger.java.Core;
import com.njdaeger.java.command.util.Cmd;
import com.njdaeger.java.command.util.EssCommand;
import com.njdaeger.java.configuration.Parser;
import com.njdaeger.java.essentials.enums.Error;
import com.njdaeger.java.essentials.enums.Permission;
import com.njdaeger.java.essentials.utils.Util;
import com.njdaeger.java.wrapper.Sender;
import com.njdaeger.java.wrapper.User;

public class SpeedCommand extends EssCommand {

	@Override
	@Cmd(
			max = 3,
			min = 1,
			permissions = { Permission.ESS_SPEED, Permission.ESS_SPEED_OTHER },
			name = "speed",
			desc = "Change your speed limits",
			usage = "/speed <speed> [player] [type]",
			aliases = { "flyspeed", "walkspeed", "setspeed" })
	public boolean run(Sender sndr, String label, String[] args) {
		switch (args.length) {
		case 1:
			if (!sndr.isUser()) {
				sndr.sendMessage(Error.NOT_ENOUGH_ARGS.sendError());
				return true;
			}
			User user = sndr.asUser();
			if (!Util.isDouble(args[0])) {
				if (args[0].equalsIgnoreCase("reset") || args[0].equalsIgnoreCase("default")) {
					user.sendMessage(ChatColor.GRAY + "Your speed has been reset.");
					if (user.isFlying()) {
						user.setFlyingSpeed(1);
						return true;
					}
					user.setWalkingSpeed(1);
					return true;
				}
				sndr.sendMessage(Error.INPUT_NOT_NUM.sendError());
				return true;
			}
			if (label.equalsIgnoreCase("flypseed")) {
				user.setFlyingSpeed(Double.parseDouble(args[0]));
				user.sendMessage(ChatColor.GRAY + "Your fly speed has been set to " + ChatColor.GREEN + args[0]);
				return true;
			}
			if (label.equalsIgnoreCase("walkspeed")) {
				user.setWalkingSpeed(Double.parseDouble(args[0]));
				user.sendMessage(ChatColor.GRAY + "Your walk speed has been set to " + ChatColor.GREEN + args[0]);
				return true;
			}
			if (user.isFlying()) {
				user.setFlyingSpeed(Double.parseDouble(args[0]));
				user.sendMessage(ChatColor.GRAY + "Your fly speed has been set to " + ChatColor.GREEN + args[0]);
				return true;
			}
			user.setWalkingSpeed(Double.parseDouble(args[0]));
			user.sendMessage(ChatColor.GRAY + "Your walk speed has been set to " + ChatColor.GREEN + args[0]);
			return true;
		case 2:
			if (sndr.hasPermission(Permission.ESS_SPEED_OTHER)) {
				User target = Core.getUser(args[1]);
				if (target == null) {
					sndr.sendError(Error.UNKNOWN_PLAYER);
					return true;
				}
				if (!Util.isDouble(args[0])) {
					if (args[0].equalsIgnoreCase("reset") || args[0].equalsIgnoreCase("default")) {
						target.sendMessage(ChatColor.GRAY + "Your speed has been reset.");
						sndr.sendMessage(ChatColor.GRAY + "You reset " + ChatColor.GREEN + target.getName()
								+ ChatColor.GRAY + "'s speed.");

						if (target.isFlying()) {
							target.setFlyingSpeed(1);
							return true;
						}
						target.setWalkingSpeed(1);
						return true;
					}
					sndr.sendMessage(Error.INPUT_NOT_NUM.sendError());
					return true;
				}
				if (label.equalsIgnoreCase("flypseed")) {
					target.setFlyingSpeed(Double.parseDouble(args[0]));
					target.sendMessage(ChatColor.GRAY + "Your fly speed has been set to " + ChatColor.GREEN + args[0]);
					sndr.sendMessage(ChatColor.GRAY + "You set " + ChatColor.GREEN + target.getName() + ChatColor.GRAY
							+ "'s fly speed to " + ChatColor.GREEN + args[0]);
					return true;
				}
				if (label.equalsIgnoreCase("walkspeed")) {
					target.setWalkingSpeed(Double.parseDouble(args[0]));
					target.sendMessage(ChatColor.GRAY + "Your walk speed has been set to " + ChatColor.GREEN + args[0]);
					sndr.sendMessage(ChatColor.GRAY + "You set " + ChatColor.GREEN + target.getName() + ChatColor.GRAY
							+ "'s walk speed to " + ChatColor.GREEN + args[0]);
					return true;
				}
				if (target.isFlying()) {
					target.setFlyingSpeed(Double.parseDouble(args[0]));
					target.sendMessage(ChatColor.GRAY + "Your fly speed has been set to " + ChatColor.GREEN + args[0]);
					sndr.sendMessage(ChatColor.GRAY + "You set " + ChatColor.GREEN + target.getName() + ChatColor.GRAY
							+ "'s walk speed to " + ChatColor.GREEN + args[0]);
					return true;
				}
				target.setWalkingSpeed(Double.parseDouble(args[0]));
				target.sendMessage(ChatColor.GRAY + "Your walk speed has been set to " + ChatColor.GREEN + args[0]);
				sndr.sendMessage(ChatColor.GRAY + "You set " + ChatColor.GREEN + target.getName() + ChatColor.GRAY
						+ "'s walk speed to " + ChatColor.GREEN + args[0]);
				return true;
			}
			sndr.sendMessage(Parser.parse(Error.NO_PERMISSION.getError(), sndr.asPlayer(), "Unknown",
					Permission.ESS_SPEED_OTHER));
			return true;
		default:
			User target = Core.getUser(args[1]);
			if (target == null) {
				sndr.sendError(Error.UNKNOWN_PLAYER);
				return true;
			}
			if (sndr.hasPermission(Permission.ESS_SPEED_OTHER)) {
				if (!Util.isDouble(args[0])) {
					if (args[0].equalsIgnoreCase("reset") || args[0].equalsIgnoreCase("default")) {
						if (args[2].equalsIgnoreCase("fly")) {
							target.sendMessage(ChatColor.GRAY + "Your fly speed has been reset.");
							sndr.sendMessage(ChatColor.GRAY + "You reset " + ChatColor.GREEN + target.getName()
									+ ChatColor.GRAY + "'s fly speed.");
							target.setFlyingSpeed(1);
							return true;
						}
						if (args[2].equalsIgnoreCase("walk")) {
							target.sendMessage(ChatColor.GRAY + "Your walk speed has been reset.");
							sndr.sendMessage(ChatColor.GRAY + "You reset " + ChatColor.GREEN + target.getName()
									+ ChatColor.GRAY + "'s walk speed.");
							target.setWalkingSpeed(1);
							return true;
						}
						sndr.sendError(Error.UNKNOWN_WALK_TYPE);
						return true;
					}
					sndr.sendMessage(Error.INPUT_NOT_NUM.sendError());
					return true;
				}
				if (label.equalsIgnoreCase("flyspeed")) {
					sndr.sendError(Error.TOO_MANY_ARGS);
					return true;
				}
				if (label.equalsIgnoreCase("walkspeed")) {
					sndr.sendError(Error.TOO_MANY_ARGS);
					return true;
				}
				if (args[2].equalsIgnoreCase("fly")) {
					target.sendMessage(ChatColor.GRAY + "Your fly speed has been set to " + ChatColor.GREEN + args[0]);
					sndr.sendMessage(ChatColor.GRAY + "You set " + ChatColor.GREEN + target.getName() + ChatColor.GRAY
							+ "'s fly speed to " + ChatColor.GREEN + args[0]);
					target.setFlyingSpeed(Double.parseDouble(args[0]));
					return true;
				}
				if (args[2].equalsIgnoreCase("walk")) {
					target.sendMessage(ChatColor.GRAY + "Your walk speed has been set to " + ChatColor.GREEN + args[0]);
					sndr.sendMessage(ChatColor.GRAY + "You set " + ChatColor.GREEN + target.getName() + ChatColor.GRAY
							+ "'s walk speed to " + ChatColor.GREEN + args[0]);
					target.setWalkingSpeed(Double.parseDouble(args[0]));
					return true;
				}
				sndr.sendError(Error.UNKNOWN_WALK_TYPE);
				return true;
			}
			sndr.sendMessage(Parser.parse(Error.NO_PERMISSION.getError(), sndr.asPlayer(), "Unknown",
					Permission.ESS_SPEED_OTHER));
			return true;
		}
	}
}

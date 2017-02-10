package com.njdaeger.java.essentials.commands.player;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import com.njdaeger.java.Holder;
import com.njdaeger.java.command.util.Cmd;
import com.njdaeger.java.command.util.EssCommand;
import com.njdaeger.java.configuration.controllers.PlayerConfig;
import com.njdaeger.java.essentials.enums.Error;
import com.njdaeger.java.essentials.enums.Permission;
import com.njdaeger.java.essentials.utils.Util;
import com.njdaeger.java.wrapper.Sender;

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
			if (!sndr.isPlayer()) {
				sndr.sendMessage(Error.NOT_ENOUGH_ARGS.sendError());
				return true;
			}
			Player player = sndr.asPlayer();
			if (!Util.isDouble(args[0])) {
				if (args[0].equalsIgnoreCase("reset") || args[0].equalsIgnoreCase("default")) {
					PlayerConfig.getConfig(player).setFlySpeed(1);
					PlayerConfig.getConfig(player).setWalkingSpeed(1);
					return true;
				}
				sndr.sendMessage(Error.INPUT_NOT_NUM.sendError());
				return true;
			}
			if (label.equalsIgnoreCase("flypseed")) {
				PlayerConfig.getConfig(player).setFlySpeed(Double.parseDouble(args[0]));
				return true;
			}
			if (label.equalsIgnoreCase("walkspeed")) {
				PlayerConfig.getConfig(player).setWalkingSpeed(Double.parseDouble(args[0]));
				return true;
			}
			if (player.isFlying()) {
				PlayerConfig.getConfig(player).setFlySpeed(Double.parseDouble(args[0]));
				return true;
			}
			PlayerConfig.getConfig(player).setWalkingSpeed(Double.parseDouble(args[0]));
			return true;
		case 2:
		default:
			break;
		}
		if (args.length == 1) {
			if (!sndr.isPlayer()) {
				sndr.sendMessage(Error.NOT_ENOUGH_ARGS.sendError());
				return true;
			}
			Player player = sndr.asPlayer();
			if (!Util.isDouble(args[0])) {
				if (args[0].equalsIgnoreCase("reset") || args[0].equalsIgnoreCase("default")) {
					if (player.isFlying()) {
						PlayerConfig.getConfig(player).setFlySpeed(1);
						return true;
					}
					PlayerConfig.getConfig(player).setWalkingSpeed(1);
					return true;
				}
				sndr.sendMessage(Error.INPUT_NOT_NUM.sendError());
				return true;
			}
			if (Holder.hasPermission(player, Permission.ESS_SPEED, Permission.ESS_SPEED_OTHER)) {
				if (label.equalsIgnoreCase("flypseed")) {
					if (!player.isFlying()) {
						sndr.sendMessage(Error.throwError("You must be flying to set your flying speed."));
						return true;
					}
					PlayerConfig.getConfig(player).setFlySpeed(Double.parseDouble(args[0]));
					return true;
				}
				if (label.equalsIgnoreCase("walkspeed")) {
					if (player.isFlying()) {
						sndr.sendMessage(Error.throwError("You must be walking to set your walking speed."));
						return true;
					}
					PlayerConfig.getConfig(player).setWalkingSpeed(Double.parseDouble(args[0]));
					return true;
				} else {
					if (player.isFlying()) {
						PlayerConfig.getConfig(player).setFlySpeed(Double.parseDouble(args[0]));
						return true;
					}
					PlayerConfig.getConfig(player).setWalkingSpeed(Double.parseDouble(args[0]));
					return true;
				}
			}
		}
		if (args.length == 2) {
			if (sndr.isPlayer()) {
				Player player = sndr.asPlayer();
				if (Holder.hasPermission(player, Permission.ESS_SPEED_OTHER)) {

				} else {
					sndr.sendMessage(Error.NO_PERMISSION.sendError());
					return true;
				}
			}
			Player target = Bukkit.getPlayer(args[1]);
			if (!Util.isDouble(args[0])) {
				if (args[0].equalsIgnoreCase("reset") || args[0].equalsIgnoreCase("default")) {
					if (target.isFlying()) {
						PlayerConfig.getConfig(target).setFlySpeed(1);
						return true;
					}
					PlayerConfig.getConfig(target).setWalkingSpeed(1);
					return true;
				}
				sndr.sendMessage(Error.INPUT_NOT_NUM.sendError());
				return true;
			}
			if (target == null) {
				sndr.sendMessage(Error.UNKNOWN_PLAYER.sendError());
				return true;
			}
			if (label.equalsIgnoreCase("flypseed")) {
				if (!target.isFlying()) {
					sndr.sendMessage(Error.throwError("Target must be flying in order to set their fly speed."));
					return true;
				}
				PlayerConfig.getConfig(target).setFlySpeed(Double.parseDouble(args[0]));
				return true;
			}
			if (label.equalsIgnoreCase("walkspeed")) {
				if (target.isFlying()) {
					sndr.sendMessage(Error.throwError("Target must be walking in order to set their walk speed."));
					return true;
				}
				PlayerConfig.getConfig(target).setWalkingSpeed(Double.parseDouble(args[0]));
				return true;
			} else {
				if (target.isFlying()) {
					PlayerConfig.getConfig(target).setFlySpeed(Double.parseDouble(args[0]));
					return true;
				}
				PlayerConfig.getConfig(target).setWalkingSpeed(Double.parseDouble(args[0]));
				return true;
			}
		}
		if (sndr.isPlayer()) {
			Player player = sndr.asPlayer();
			if (Holder.hasPermission(player, Permission.ESS_SPEED_OTHER)) {

			} else {
				sndr.sendMessage(Error.NO_PERMISSION.sendError());
				return true;
			}
		}
		Player target = Bukkit.getPlayer(args[1]);
		if (!Util.isDouble(args[0])) {
			if (args[0].equalsIgnoreCase("reset") || args[0].equalsIgnoreCase("default")) {
				if (target.isFlying()) {
					PlayerConfig.getConfig(target).setFlySpeed(1);
					return true;
				}
				PlayerConfig.getConfig(target).setWalkingSpeed(1);
				return true;
			}
			sndr.sendMessage(Error.INPUT_NOT_NUM.sendError());
			return true;
		}
		if (target == null) {
			sndr.sendMessage(Error.UNKNOWN_PLAYER.sendError());
			return true;
		}
		if (label.equalsIgnoreCase("walkspeed") || label.equalsIgnoreCase("flyspeed")) {
			sndr.sendMessage(Error.TOO_MANY_ARGS.sendError());
			return true;
		}
		if (args[2].equalsIgnoreCase("fly") || args[2].equalsIgnoreCase("flying")) {
			PlayerConfig.getConfig(target).setFlySpeed(Double.parseDouble(args[0]));
			return true;
		}
		if (args[2].equalsIgnoreCase("walk") || args[2].equalsIgnoreCase("walking")) {
			PlayerConfig.getConfig(target).setWalkingSpeed(Double.parseDouble(args[0]));
			return true;
		} else {
			sndr.sendMessage(Error.throwError("Unknown walking type."));
			return true;
		}
	}
}

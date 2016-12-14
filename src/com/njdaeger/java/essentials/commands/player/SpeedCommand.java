package com.njdaeger.java.essentials.commands.player;

import java.util.Arrays;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.njdaeger.java.EssCommand;
import com.njdaeger.java.Holder;
import com.njdaeger.java.Plugin;
import com.njdaeger.java.configuration.controllers.PlayerConfig;
import com.njdaeger.java.essentials.enums.Error;
import com.njdaeger.java.essentials.enums.Permission;
import com.njdaeger.java.essentials.utils.Util;

public class SpeedCommand extends EssCommand {
	
	static String name = "speed";
	
	public SpeedCommand() {
		super("speed");
		List<String> a = Arrays.asList("flyspeed", "walkspeed", "setspeed");
		this.description = "Set player speed.";
		this.usageMessage = "/speed <speed> [player] [type]";
		this.setAliases(a);
		
	}
	
	@Override
	public void register() {
		Plugin.getCommand(name, this);
	}

	@Override
	public boolean execute(CommandSender sndr, String label, String[] args) {
		if (args.length == 0) {
			sndr.sendMessage(Error.NOT_ENOUGH_ARGS.sendError());
			return true;
		}
		if (args.length > 3) {
			sndr.sendMessage(Error.TOO_MANY_ARGS.sendError());
			return true;
		}
		if (args.length == 1) {
			if (!(sndr instanceof Player)) {
				sndr.sendMessage(Error.NOT_ENOUGH_ARGS.sendError());
				return true;
			}
			Player player = (Player) sndr;
			if (!Util.isDouble(args[0])) {
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
				}
				else {
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
			if (sndr instanceof Player) {
				Player player = (Player) sndr;
				if (Holder.hasPermission(player, Permission.ESS_SPEED_OTHER)) {
					
				}
				else {
					sndr.sendMessage(Error.NO_PERMISSION.sendError());
					return true;
				}
			}
			Player target = Bukkit.getPlayer(args[1]);
			if (!Util.isDouble(args[0])) {
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
			}
			else {
				if (target.isFlying()) {
					PlayerConfig.getConfig(target).setFlySpeed(Double.parseDouble(args[0]));
					return true;
				}
				PlayerConfig.getConfig(target).setWalkingSpeed(Double.parseDouble(args[0]));
				return true;
			}
		}
		if (sndr instanceof Player) {
			Player player = (Player) sndr;
			if (Holder.hasPermission(player, Permission.ESS_SPEED_OTHER)) {
				
			}
			else {
				sndr.sendMessage(Error.NO_PERMISSION.sendError());
				return true;
			}
		}
		Player target = Bukkit.getPlayer(args[1]);
		if (!Util.isDouble(args[0])) {
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
		}
		else {
			sndr.sendMessage(Error.throwError("Unknown walking type."));
			return true;
		}
	}
}

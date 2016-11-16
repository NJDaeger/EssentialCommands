package com.njdaeger.essentials.commands.player;

import java.util.Arrays;
import java.util.List;

import net.md_5.bungee.api.ChatColor;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.command.defaults.BukkitCommand;
import org.bukkit.entity.Player;

import com.njdaeger.essentials.enums.Error;
import com.njdaeger.essentials.enums.Permission;
import com.njdaeger.essentials.utils.TargetHasPermission;
import com.njdaeger.essentials.utils.Util;

public class SpeedCommand extends BukkitCommand {
	
	/*
	 * All code in this file is owned by NJDaeger. 
	 */
	
	public SpeedCommand() {
		super("speed");
		List<String> a = Arrays.asList("flyspeed", "walkspeed", "setspeed");
		this.description = "Set player speed.";
		this.usageMessage = "/speed <speed> [player] [type]";
		this.setAliases(a);
		
	}

	@Override
	public boolean execute(CommandSender sndr, String label, String[] args) {
		if (sndr instanceof Player) {
			Player player = (Player) sndr;
			if (TargetHasPermission.check(player, Permission.ESS_SPEED, Permission.ESS_SPEED_OTHER)) {
				if (args.length == 0) {
					sndr.sendMessage(Error.NOT_ENOUGH_ARGS.sendError());
					return true;
				}
				if (args.length == 1) {
					if (args[0].equalsIgnoreCase("reset")) {
						if (!player.isFlying()) {
							player.setWalkSpeed(0.2f);
							sndr.sendMessage(ChatColor.GRAY + "Your walking speed is now reset.");
							return true;
						}
						else {
							player.setFlySpeed(0.1f);
							sndr.sendMessage(ChatColor.GRAY + "Your flying speed is now reset.");
							return true;
						}
					}
					if (Util.isNumber(args[0]) == false) {
						sndr.sendMessage(Error.INPUT_NOT_NUM.sendError());
						return true;
					}
					else {
						if (!player.isFlying()) {
							double x = Double.parseDouble(args[0]);
							if (x > 10) {
								sndr.sendMessage(Error.INPUT_TOO_LARGE.sendError());
								return true;
							}
							if (x < 0) {
								sndr.sendMessage(Error.INPUT_TOO_SMALL.sendError());
								return true;
							}
							else {
								double e = ((19 * x) - Math.pow(x, 2)) / 90;
								String a = Double.toString(e);
								float speed = Float.parseFloat(a);
								player.setWalkSpeed(speed);
								sndr.sendMessage(ChatColor.GRAY + "Your walking speed is now " + ChatColor.GREEN + args[0] + ChatColor.GRAY + ".");
								return true;
							}
						}
						else {
							double x = Double.parseDouble(args[0]);
							if (x > 10) {
								sndr.sendMessage(Error.INPUT_TOO_LARGE.sendError());
								return true;
							}
							if (x < 0) {
								sndr.sendMessage(Error.INPUT_TOO_SMALL.sendError());
								return true;
							}
							float fspeed = Float.parseFloat(args[0]) / 10;
							player.setFlySpeed(fspeed);
							sndr.sendMessage(ChatColor.GRAY + "Your flying speed is now " + ChatColor.GREEN + args[0] + ChatColor.GRAY + ".");
							return true;
						}
					}
				}
				if (args.length == 2) {
					if (TargetHasPermission.check(player, Permission.ESS_SPEED_OTHER)) {
						Player target = Bukkit.getPlayer(args[1]);
						if (target == null) {
							sndr.sendMessage(Error.UNKNOWN_PLAYER.sendError());
							return true;
						}
						if (args[0].equalsIgnoreCase("reset")) {
							if (!target.isFlying()) {
								target.setWalkSpeed(0.2f);
								sndr.sendMessage(ChatColor.GRAY + "You reset " + ChatColor.GREEN + target.getName() + ChatColor.GRAY + "'s walk speed.");
								target.sendMessage(ChatColor.GRAY + "Your walking speed is now reset.");
								return true;
							}
							else {
								target.setFlySpeed(0.1f);
								sndr.sendMessage(ChatColor.GRAY + "You reset " + ChatColor.GREEN + target.getName() + ChatColor.GRAY + "'s flying speed.");
								target.sendMessage(ChatColor.GRAY + "Your flying speed is now reset.");
								return true;
							}
						}
						if (Util.isNumber(args[0]) == false) {
							sndr.sendMessage(Error.INPUT_NOT_NUM.sendError());
							return true;
						}
						double x = Double.parseDouble(args[0]);
						if (x > 10) {
							sndr.sendMessage(Error.INPUT_TOO_LARGE.sendError());
							return true;
						}
						if (x < 0) {
							sndr.sendMessage(Error.INPUT_TOO_SMALL.sendError());
							return true;
						}
						else {
							if (!target.isFlying()) {
								double e = ((19 * x) - Math.pow(x, 2)) / 90;
								String a = Double.toString(e);
								float speed = Float.parseFloat(a);
								target.setWalkSpeed(speed);
								sndr.sendMessage(ChatColor.GRAY + "You set " + ChatColor.GREEN + target.getName() + ChatColor.GRAY + "'s walking speed to " + ChatColor.GREEN + args[0] + ChatColor.GRAY + ".");
								target.sendMessage(ChatColor.GRAY + "Your walking speed is now " + ChatColor.GREEN + args[0] + ChatColor.GRAY + ".");
								return true;
							}
							else {
								float fspeed = Float.parseFloat(args[0]) / 10;
								target.setFlySpeed(fspeed);
								sndr.sendMessage(ChatColor.GRAY + "You set " + ChatColor.GREEN + target.getName() + ChatColor.GRAY + "'s flying speed to " + ChatColor.GREEN + args[0] + ChatColor.GRAY + ".");
								target.sendMessage(ChatColor.GRAY + "Your flying speed is now " + ChatColor.GREEN + args[0] + ChatColor.GRAY + ".");
								return true;
							}
						}
					}
					else {
						sndr.sendMessage(Error.NO_PERMISSION.sendError());
						return true;
					}
				}
				if (args.length == 3) {
					if (TargetHasPermission.check(player, Permission.ESS_SPEED_OTHER)) {
						Player target = Bukkit.getPlayer(args[1]);
						if (target == null) {
							sndr.sendMessage(Error.UNKNOWN_PLAYER.sendError());
							return true;
						}
						if (args[0].equalsIgnoreCase("reset")) {
							if (args[2].equalsIgnoreCase("fly") || args[2].equalsIgnoreCase("flying")) {
								target.setFlySpeed(0.1f);
								sndr.sendMessage(ChatColor.GRAY + "You reset " + ChatColor.GREEN + target.getName() + ChatColor.GRAY + "'s flying speed.");
								target.sendMessage(ChatColor.GRAY + "Your flying speed is now reset.");
								return true;
							}
							if (args[2].equalsIgnoreCase("walk") || args[2].equalsIgnoreCase("walking")) {
								target.setWalkSpeed(0.2f);
								sndr.sendMessage(ChatColor.GRAY + "You reset " + ChatColor.GREEN + target.getName() + ChatColor.GRAY + "'s walk speed.");
								target.sendMessage(ChatColor.GRAY + "Your walking speed is now reset.");
								return true;
							}
							else {
								sndr.sendMessage(Error.UNKNOWN_WALK_TYPE.sendError());
								return true;
							}
						}
						if (Util.isNumber(args[0]) == false) {
							sndr.sendMessage(Error.INPUT_NOT_NUM.sendError());
							return true;
						}
						double x = Double.parseDouble(args[0]);
						if (x > 10) {
							sndr.sendMessage(Error.INPUT_TOO_LARGE.sendError());
							return true;
						}
						if (x < 0) {
							sndr.sendMessage(Error.INPUT_TOO_SMALL.sendError());
							return true;
						}
						else {
							if (args[2].equalsIgnoreCase("fly") || args[2].equalsIgnoreCase("flying")) {
								float fspeed = Float.parseFloat(args[0]) / 10;
								target.setFlySpeed(fspeed);
								sndr.sendMessage(ChatColor.GRAY + "You set " + ChatColor.GREEN + target.getName() + ChatColor.GRAY + "'s flying speed to " + ChatColor.GREEN + args[0] + ChatColor.GRAY + ".");
								target.sendMessage(ChatColor.GRAY + "Your flying speed is now " + ChatColor.GREEN + args[0] + ChatColor.GRAY + ".");
								return true;
							}
							if (args[2].equalsIgnoreCase("walk") || args[2].equalsIgnoreCase("walking")) {
								double e = ((19 * x) - Math.pow(x, 2)) / 90;
								String a = Double.toString(e);
								float speed = Float.parseFloat(a);
								target.setWalkSpeed(speed);
								sndr.sendMessage(ChatColor.GRAY + "You set " + ChatColor.GREEN + target.getName() + ChatColor.GRAY + "'s walking speed to " + ChatColor.GREEN + args[0] + ChatColor.GRAY + ".");
								target.sendMessage(ChatColor.GRAY + "Your walking speed is now " + ChatColor.GREEN + args[0] + ChatColor.GRAY + ".");
								return true;
							}
							else {
								sndr.sendMessage(Error.UNKNOWN_WALK_TYPE.sendError());
								return true;
							}
						}
					}
					else {
						sndr.sendMessage(Error.NO_PERMISSION.sendError());
						return true;
					}
				}
				else {
					sndr.sendMessage(Error.TOO_MANY_ARGS.sendError());
					return true;
				}
			}
			else {
				sndr.sendMessage(Error.NO_PERMISSION.sendError());
				return true;
			}
		}
		else {
			if (args.length <= 1) {
				sndr.sendMessage(Error.NOT_ENOUGH_ARGS.sendError());
				return true;
			}
			if (args.length == 2) {
				Player target = Bukkit.getPlayer(args[1]);
				if (target == null) {
					sndr.sendMessage(Error.UNKNOWN_PLAYER.sendError());
					return true;
				}
				if (args[0].equalsIgnoreCase("reset")) {
					if (!target.isFlying()) {
						target.setWalkSpeed(0.2f);
						sndr.sendMessage(ChatColor.GRAY + "You reset " + ChatColor.GREEN + target.getName() + ChatColor.GRAY + "'s walk speed.");
						target.sendMessage(ChatColor.GRAY + "Your walking speed is now reset.");
						return true;
					}
					else {
						target.setFlySpeed(0.1f);
						sndr.sendMessage(ChatColor.GRAY + "You reset " + ChatColor.GREEN + target.getName() + ChatColor.GRAY + "'s flying speed.");
						target.sendMessage(ChatColor.GRAY + "Your flying speed is now reset.");
						return true;
					}
				}
				if (Util.isNumber(args[0]) == false) {
					sndr.sendMessage(Error.INPUT_NOT_NUM.sendError());
					return true;
				}
				double x = Double.parseDouble(args[0]);
				if (x > 10) {
					sndr.sendMessage(Error.INPUT_TOO_LARGE.sendError());
					return true;
				}
				if (x < 0) {
					sndr.sendMessage(Error.INPUT_TOO_SMALL.sendError());
					return true;
				}
				else {
					if (!target.isFlying()) {
						double e = ((19 * x) - Math.pow(x, 2)) / 90;
						String a = Double.toString(e);
						float speed = Float.parseFloat(a);
						target.setWalkSpeed(speed);
						sndr.sendMessage(ChatColor.GRAY + "You set " + ChatColor.GREEN + target.getName() + ChatColor.GRAY + "'s walking speed to " + ChatColor.GREEN + args[0] + ChatColor.GRAY + ".");
						target.sendMessage(ChatColor.GRAY + "Your walking speed is now " + ChatColor.GREEN + args[0] + ChatColor.GRAY + ".");
						return true;
					}
					else {
						float fspeed = Float.parseFloat(args[0]) / 10;
						target.setFlySpeed(fspeed);
						sndr.sendMessage(ChatColor.GRAY + "You set " + ChatColor.GREEN + target.getName() + ChatColor.GRAY + "'s flying speed to " + ChatColor.GREEN + args[0] + ChatColor.GRAY + ".");
						target.sendMessage(ChatColor.GRAY + "Your flying speed is now " + ChatColor.GREEN + args[0] + ChatColor.GRAY + ".");
						return true;
					}
				}
			}
			if (args.length == 3) {
				Player target = Bukkit.getPlayer(args[1]);
				if (target == null) {
					sndr.sendMessage(Error.UNKNOWN_PLAYER.sendError());
					return true;
				}
				if (args[0].equalsIgnoreCase("reset")) {
					if (args[2].equalsIgnoreCase("fly") || args[2].equalsIgnoreCase("flying")) {
						target.setFlySpeed(0.1f);
						sndr.sendMessage(ChatColor.GRAY + "You reset " + ChatColor.GREEN + target.getName() + ChatColor.GRAY + "'s flying speed.");
						target.sendMessage(ChatColor.GRAY + "Your flying speed is now reset.");
						return true;
					}
					if (args[2].equalsIgnoreCase("walk") || args[2].equalsIgnoreCase("walking")) {
						target.setWalkSpeed(0.2f);
						sndr.sendMessage(ChatColor.GRAY + "You reset " + ChatColor.GREEN + target.getName() + ChatColor.GRAY + "'s walk speed.");
						target.sendMessage(ChatColor.GRAY + "Your walking speed is now reset.");
						return true;
					}
					else {
						sndr.sendMessage(Error.UNKNOWN_WALK_TYPE.sendError());
						return true;
					}
				}
				if (Util.isNumber(args[0]) == false) {
					sndr.sendMessage(Error.INPUT_NOT_NUM.sendError());
					return true;
				}
				double x = Double.parseDouble(args[0]);
				if (x > 10) {
					sndr.sendMessage(Error.INPUT_TOO_LARGE.sendError());
					return true;
				}
				if (x < 0) {
					sndr.sendMessage(Error.INPUT_TOO_SMALL.sendError());
					return true;
				}
				else {
					if (args[2].equalsIgnoreCase("fly") || args[2].equalsIgnoreCase("flying")) {
						float fspeed = Float.parseFloat(args[0]) / 10;
						target.setFlySpeed(fspeed);
						sndr.sendMessage(ChatColor.GRAY + "You set " + ChatColor.GREEN + target.getName() + ChatColor.GRAY + "'s flying speed to " + ChatColor.GREEN + args[0] + ChatColor.GRAY + ".");
						target.sendMessage(ChatColor.GRAY + "Your flying speed is now " + ChatColor.GREEN + args[0] + ChatColor.GRAY + ".");
						return true;
					}
					if (args[2].equalsIgnoreCase("walk") || args[2].equalsIgnoreCase("walking")) {
						double e = ((19 * x) - Math.pow(x, 2)) / 90;
						String a = Double.toString(e);
						float speed = Float.parseFloat(a);
						target.setWalkSpeed(speed);
						sndr.sendMessage(ChatColor.GRAY + "You set " + ChatColor.GREEN + target.getName() + ChatColor.GRAY + "'s walking speed to " + ChatColor.GREEN + args[0] + ChatColor.GRAY + ".");
						target.sendMessage(ChatColor.GRAY + "Your walking speed is now " + ChatColor.GREEN + args[0] + ChatColor.GRAY + ".");
						return true;
					}
					else {
						sndr.sendMessage(Error.UNKNOWN_WALK_TYPE.sendError());
						return true;
					}
				}
			}
			else {
				sndr.sendMessage(Error.TOO_MANY_ARGS.sendError());
				return true;
			}
		}
	}
}

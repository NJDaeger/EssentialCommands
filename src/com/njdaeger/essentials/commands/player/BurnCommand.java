package com.njdaeger.essentials.commands.player;

import net.md_5.bungee.api.ChatColor;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.command.defaults.BukkitCommand;
import org.bukkit.entity.Player;

import com.njdaeger.essentials.enums.Error;
import com.njdaeger.essentials.enums.Permission;
import com.njdaeger.essentials.utils.TargetHasPermission;
import com.njdaeger.essentials.utils.Util;

public class BurnCommand extends BukkitCommand {
	
	public BurnCommand() {
		super("burn");
		this.description = "Let them burn.";
		this.usageMessage = "/burn [player] [seconds]";
		this.setPermission(Permission.ESS_BURN.getPermission());
		this.setPermission(Permission.ESS_BURN_OTHER.getPermission());
	}

	@Override
	public boolean execute(CommandSender sndr, String label, String[] args) {
		if (sndr instanceof Player) {
			Player player = (Player) sndr;
			if (TargetHasPermission.check(player, Permission.ESS_BURN, Permission.ESS_BURN_OTHER)) {
				if (args.length == 0) {
					player.setFireTicks(100);
					player.sendMessage(ChatColor.GRAY + "You are now on fire.");
					return true;
				}
				if (args.length == 1) {
					if (Bukkit.getPlayer(args[0]) == null) {
						sndr.sendMessage(Error.UNKNOWN_PLAYER.sendError());
						return true;
					}
					Player target = Bukkit.getPlayer(args[0]);
					if (target.isOp()) {
						if (target.equals(sndr)) {
							player.setFireTicks(100);
							player.sendMessage(ChatColor.GRAY + "You are now on fire.");
							return true;
						}
						else {
							sndr.sendMessage(Error.PLAYER_IS_OPPED.sendError());
							return true;
						}
					}
					else {
						target.setFireTicks(100);
						sndr.sendMessage(ChatColor.GRAY + "You set " + ChatColor.GREEN + target.getName() + ChatColor.GRAY + " on fire for " + ChatColor.GREEN + "5 " + ChatColor.GRAY + "seconds.");
						return true;
					}
				}
				if (args.length == 2) {
					if (TargetHasPermission.check(player, Permission.ESS_BURN_OTHER)) {
						if (Bukkit.getPlayer(args[0]) == null) {
							sndr.sendMessage(Error.UNKNOWN_PLAYER.sendError());
							return true;
						}
						if (Util.isNumber(args[1]) == false) {
							sndr.sendMessage(Error.INPUT_NOT_NUM.sendError());
							return true;
						}
						Player target = Bukkit.getPlayer(args[0]);
						if (target.isOp()) {
							if (target.equals(sndr)) {
								int time = Integer.parseInt(args[1]);
								target.setFireTicks(time * 20);
								sndr.sendMessage(ChatColor.GRAY + "You set " + ChatColor.GREEN + target.getName() + ChatColor.GRAY + " on fire for " + ChatColor.GREEN + time + ChatColor.GRAY + " seconds.");
								return true;
							}
							else {
								sndr.sendMessage(Error.PLAYER_IS_OPPED.sendError());
								return true;
							}
						}
						else {
							int time = Integer.parseInt(args[1]);
							target.setFireTicks(time * 20);
							sndr.sendMessage(ChatColor.GRAY + "You set " + ChatColor.GREEN + target.getName() + ChatColor.GRAY + " on fire for " + ChatColor.GREEN + time + ChatColor.GRAY + " seconds.");
							return true;
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
			if (args.length > 2) {
				sndr.sendMessage(Error.TOO_MANY_ARGS.sendError());
				return true;
			}
			if (args.length < 1) {
				sndr.sendMessage(Error.NOT_ENOUGH_ARGS.sendError());
				return true;
			}
			else {
				if (args.length == 1) {
					if (Bukkit.getPlayer(args[0]) == null) {
						sndr.sendMessage(Error.UNKNOWN_PLAYER.sendError());
						return true;
					}
					else {
						Player target = Bukkit.getPlayer(args[0]);
						target.setFireTicks(100);
						sndr.sendMessage(ChatColor.GRAY + "You set " + ChatColor.GREEN + target.getName() + ChatColor.GRAY + " on fire for " + ChatColor.GREEN + "5 " + ChatColor.GRAY + "seconds.");
						return true;
					}
				}
				else {
					if (Bukkit.getPlayer(args[0]) == null) {
						sndr.sendMessage(Error.UNKNOWN_PLAYER.sendError());
						return true;
					}
					if (Util.isNumber(args[1]) == false) {
						sndr.sendMessage(Error.INPUT_NOT_NUM.sendError());
						return true;
					}
					Player target = Bukkit.getPlayer(args[0]);
					if (target.isOp()) {
						if (target.equals(sndr)) {
							int time = Integer.parseInt(args[1]);
							target.setFireTicks(time * 20);
							sndr.sendMessage(ChatColor.GRAY + "You set " + ChatColor.GREEN + target.getName() + ChatColor.GRAY + " on fire for " + ChatColor.GREEN + time + ChatColor.GRAY + " seconds.");
							return true;
						}
						else {
							sndr.sendMessage(Error.PLAYER_IS_OPPED.sendError());
							return true;
						}
					}
					else {
						int time = Integer.parseInt(args[1]);
						target.setFireTicks(time * 20);
						sndr.sendMessage(ChatColor.GRAY + "You set " + ChatColor.GREEN + target.getName() + " on fire for " + ChatColor.GREEN + time + ChatColor.GRAY + " seconds.");
						return true;
					}
				}
			}
		}
	}
}
